package com.cotiviti.rca.logconsumer.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Log implements JsonAware {

    public enum LogLevel {
        INFO,
        ERROR,
        WARNING
    }

    private Long logId;

    private String correlationId;
    private String businessUnit;
    private String applicationName;
    private String clientName;
    private String upn;
    private String ipAddress;

    private String logLevel;    //Info, Error, Warning
    private String logMessage;
    private String errorCause;
    private String errorMessage;
    private Date createDate;

    public static Log fromJson(String json) throws Throwable {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(json, Log.class);
    }
}
