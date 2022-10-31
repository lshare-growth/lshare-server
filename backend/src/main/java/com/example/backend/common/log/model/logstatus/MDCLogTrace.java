package com.example.backend.common.log.model.logstatus;

import com.example.backend.common.configuration.common.log.model.logstatus.StartTime;
import com.example.backend.common.configuration.common.log.model.logstatus.TraceStatus;
import com.example.backend.common.log.LogTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MDCLogTrace implements LogTrace {

    private static final Logger log = LoggerFactory.getLogger(MDCLogTrace.class);
    private static final ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(final Object object) {
        final TraceId getTraceId = findTraceId();
        log.info("[{}] {} {}", getTraceId, addSpace(START_PREFIX, getTraceId.getLevel()), object);
        return new TraceStatus(getTraceId, StartTime.now(), object);
    }

    private TraceId findTraceId() {
        TraceId findTraceId = traceIdHolder.get();
        if (findTraceId == null) {
            traceIdHolder.set(TraceId.from(UUID.randomUUID().toString(), 0));
            findTraceId = traceIdHolder.get();
        } else {
            traceIdHolder.set(findTraceId.createNextId());
            findTraceId = traceIdHolder.get();
        }
        return findTraceId;
    }

    @Override
    public void end(final TraceStatus status, final Object object) {
        complete(status, object);
    }

    @Override
    public void exception(final TraceStatus status, final Exception e) {
        complete(status, e);
    }

    private void complete(final TraceStatus status, final Object object) {
        final TransactionEndTime transactionEndTime = TransactionEndTime.from(status.getStartTime());
        final long totalExecutionTime = transactionEndTime.getTotalTime();
        final TraceId traceId = status.getTraceId();
        if (object != null) {
            log.info("[{}] {}{} time={}ms", traceId.getTraceId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), object, totalExecutionTime);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getTraceId(), addSpace(ERROR_PREFIX, traceId.getLevel()), null, totalExecutionTime, object.toString());
        }
        releaseTraceId(traceId);
    }

    private void releaseTraceId(final TraceId traceId) {
        if (traceId.isFirst()) {
            traceIdHolder.remove();
        } else {
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

    private static String addSpace(final String prefix, final int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|        ");
        }
        return sb.toString();
    }
}
