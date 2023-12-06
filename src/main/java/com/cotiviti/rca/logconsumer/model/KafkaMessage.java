package com.cotiviti.rca.logconsumer.model;

import lombok.Data;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Data
public class KafkaMessage {

    // Custom Header values
    public final static String x_businessUnit = "x-businessUnit";
    public final static String x_clientName = "x-clientName";
    public final static String x_applicationName = "x-applicationName";
    public final static String x_upn = "x-upn";
    public final static String x_ipAddress = "x-ipAddress";
    public final static String x_createDate = "x-createDate";

    // Headers
    private String header_topic;
    private String header_correlationId;
    private String header_businessUnit;
    private String header_clientName;
    private String header_applicationName;
    private String header_upn;
    private String header_ipAddress;

    // Payload message
    private String payload;

    /**
     * Construct Kafka message
     */
    public Message<String> build() {
        return MessageBuilder
                .withPayload(this.payload)
                .setHeader(KafkaHeaders.TOPIC, this.header_topic)
                .setHeader(KafkaHeaders.CORRELATION_ID, this.header_correlationId)
                .setHeader(KafkaMessage.x_businessUnit, this.header_businessUnit)
                .setHeader(KafkaMessage.x_clientName, this.header_clientName)
                .setHeader(KafkaMessage.x_applicationName, this.header_applicationName)
                .setHeader(KafkaMessage.x_upn, this.header_upn)
                .setHeader(KafkaMessage.x_ipAddress, this.header_ipAddress)
                .setHeader(KafkaMessage.x_createDate, System.currentTimeMillis())
                .build();
    }
}
