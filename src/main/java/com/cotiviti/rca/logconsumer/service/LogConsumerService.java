package com.cotiviti.rca.logconsumer.service;

import com.cotiviti.rca.logconsumer.dto.Log;
import com.cotiviti.rca.logconsumer.model.KafkaMessage;
import com.cotiviti.rca.logconsumer.repository.LogRepository;
import com.cotiviti.rca.logconsumer.utility.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class LogConsumerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.kafka.consumer.topic}")
    private String topicName;

    private final Helper helper;
    private final LogRepository logRepository;

    @Autowired
    public LogConsumerService(Helper helper, LogRepository logRepository) {
        this.helper = helper;
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}", groupId = "#{'${spring.kafka.consumer.groupId}'}")
    public void consume(@Payload String message, Acknowledgment ack, @Headers MessageHeaders headers) {

        String correlationId = "";
        String clientName = "";
        String upn = "";
        String businessUnit = "";
        String applicationName;
        String ipAddress;


        try {

            correlationId = headers.get(KafkaHeaders.CORRELATION_ID, String.class);
            clientName = headers.get(KafkaMessage.x_clientName, String.class);
            upn = headers.get(KafkaMessage.x_upn, String.class);
            businessUnit = headers.get(KafkaMessage.x_businessUnit, String.class);
            applicationName = headers.get(KafkaMessage.x_applicationName, String.class);
            ipAddress = headers.get(KafkaMessage.x_ipAddress, String.class);

            // convert kafka message to Java Object
            Log log = Log.fromJson(message);

            //Set log values
            log.setCorrelationId(correlationId);
            log.setClientName(clientName);
            log.setUpn(upn);
            log.setBusinessUnit(businessUnit);
            log.setApplicationName(applicationName);
            log.setIpAddress(ipAddress);

            logger.info("-------In Log consumer Service-------");
            logger.info("Topic: {}", topicName);
            logger.info("CorrelationId:" + correlationId);
            logger.info("ClientName: " + clientName);
            logger.info("UPN: " + upn);
            logger.info("Message: {}", message);


            // Insert data into client server
            logRepository.saveLogData(log);

            logger.info("Completed remote call to config with client name = " + clientName);
        }
        catch (Throwable ex) {
            logger.error("Error: " + ex);
        }
        finally {
            ack.acknowledge();
        }
    }

}
