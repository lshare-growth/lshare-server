package com.example.backend.common.log.model.logstatus;

import java.util.Objects;

public final class TraceId {

    private final String traceId;
    private int level;

    private TraceId() {
        throw new AssertionError("올바른 방식으로 TraceId를 생성해주세요.");
    }

    private TraceId(final String traceId, final int level) {
        this.traceId = traceId;
        this.level = level;
    }

    public static TraceId from(final String traceId, final int index) {
        return new TraceId(traceId, index);
    }

    public String getTraceId() {
        return traceId;
    }

    public int getLevel() {
        return level;
    }

    public TraceId createNextId() {
        return new TraceId(traceId, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(traceId, level - 1);
    }

    public boolean isFirst() {
        return level == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraceId)) return false;
        TraceId traceId1 = (TraceId) o;
        return getTraceId().equals(traceId1.getTraceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTraceId());
    }

    @Override
    public String toString() {
        return traceId;
    }
}
