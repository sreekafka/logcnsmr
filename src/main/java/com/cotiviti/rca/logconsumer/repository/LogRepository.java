package com.cotiviti.rca.logconsumer.repository;

import com.cotiviti.rca.logconsumer.dto.Log;

public interface LogRepository {
    int saveLogData(Log log);
}
