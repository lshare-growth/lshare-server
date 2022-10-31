package com.example.backend.common.configuration.common.log.model.logstatus;

import com.example.backend.common.log.model.logstatus.TraceId;

public class TraceStatus {

    private final TraceId traceId;
    private final StartTime startTime;
    private final Object object;

    public TraceStatus(final TraceId traceId, final StartTime startTime, final Object object) {
        this.traceId = traceId;
        this.startTime = startTime;
        this.object = object;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public long getStartTime() {
        return startTime.getStartTime();
    }

    @Override
    public String toString() {
        return String.format("%s", traceId);
    }
}
