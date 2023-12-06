package com.cotiviti.rca.logconsumer.repository;

import com.cotiviti.rca.logconsumer.dto.Log;
import com.cotiviti.rca.logconsumer.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepositoryImpl implements LogRepository {

    private final JdbcTemplate jdbcTemplate;
    private final Helper helper;

    @Autowired
    public LogRepositoryImpl(JdbcTemplate jdbcTemplate, Helper helper){
        this.jdbcTemplate = jdbcTemplate;
        this.helper = helper;
    }


    @Override
    public int saveLogData(Log log) {
        String createLogSql = "INSERT INTO [dbo].[COBLog] ([LogLevel], [LogMessage], [CorrelationId], [BusinessUnit], [ApplicationName], [ClientName], [UPN], [IpAddress], [ErrorCause], [ErrorMessage], [CreateDate]) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(createLogSql, log.getLogLevel(), log.getLogMessage(), log.getCorrelationId(), log.getBusinessUnit(),
                                                 log.getApplicationName(), log.getClientName(), log.getUpn(), log.getIpAddress(),
                                                 log.getErrorCause(), log.getErrorMessage(), java.time.LocalDateTime.now());
    }

}
