package com.example.backend.common.log.model.logstatus;

import java.util.Objects;

public class TransactionEndTime {

    private final long startTime;
    private final long endTime;

    private TransactionEndTime() {
        throw new AssertionError("올바른 방식으로 TransactionEndTime를 생성해주세요.");
    }

    private TransactionEndTime(final long startTime) {
        this.startTime = startTime;
        this.endTime = System.currentTimeMillis();
    }

    public static TransactionEndTime from(final long startTime) {
        return new TransactionEndTime(startTime);
    }

    public long getTotalTime() {
        return endTime - startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionEndTime)) return false;
        TransactionEndTime that = (TransactionEndTime) o;
        return startTime == that.startTime && endTime == that.endTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return String.valueOf(endTime - startTime);
    }
}
