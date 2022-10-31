package com.example.backend.common.log;

import com.example.backend.common.configuration.common.log.model.logstatus.TraceStatus;

public interface LogTrace {

    String START_PREFIX = "----->";
    String COMPLETE_PREFIX = "<-----";
    String ERROR_PREFIX = "<X-----";

    TraceStatus begin(Object object);

    void end(TraceStatus status, Object object);

    void exception(TraceStatus status, Exception e);
}
