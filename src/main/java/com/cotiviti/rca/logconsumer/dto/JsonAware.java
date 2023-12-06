package com.cotiviti.rca.logconsumer.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonAware {
    default String toJson() throws Throwable {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(this);
    }
}
