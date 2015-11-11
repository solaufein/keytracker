package com.keytracker.storing;

import java.util.concurrent.TimeUnit;

public class StoreParameters {
    private long period;
    private long initialDelay;
    private TimeUnit timeUnit;

    public StoreParameters(long initialDelay, long period, TimeUnit timeUnit) {
        this.initialDelay = initialDelay;
        this.period = period;
        this.timeUnit = timeUnit;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public long getPeriod() {
        return period;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
