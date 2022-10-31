package com.example.backend.common.configuration.common.log.model.logstatus;

import java.util.Objects;

public final class StartTime {

    private final Long startTime;

    private StartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public static StartTime now() {
        return new StartTime();
    }

    public Long getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StartTime)) return false;
        StartTime startTime1 = (StartTime) o;
        return startTime.equals(startTime1.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime);
    }

    @Override
    public String toString() {
        return startTime.toString();
    }
}
