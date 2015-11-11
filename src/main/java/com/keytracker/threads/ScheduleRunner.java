package com.keytracker.threads;

import com.keytracker.StoreMechanism;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleRunner {
    private final StoreMechanism storeMechanism;
    private final ScheduledExecutorService scheduledExecutorService;

    public ScheduleRunner(StoreMechanism storeMechanism) {
        this.storeMechanism = storeMechanism;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    public ScheduleRunner schedule(StoreMechanism storeMechanism, long initialDelay, long period, TimeUnit timeUnit) {
        this.scheduledExecutorService.scheduleAtFixedRate(storeMechanism::store, initialDelay, period, timeUnit);
        return this;
    }

    public ScheduleRunner scheduleAll(long initialDelay, long period, TimeUnit timeUnit) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(storeMechanism::store, initialDelay, period, timeUnit);
        return this;
    }

    public ScheduleRunner addShutdownHook(StoreMechanism storeMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(storeMechanism::store, "Shutdown-thread"));
        return this;
    }

    public ScheduleRunner addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Performing some shutdown cleanup...");
            scheduledExecutorService.shutdown();
            while (true) {
                try {
                    System.out.println("Waiting for the service to terminate...");
                    if (scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                        break;
                    }
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException" + e.getMessage());
                }
            }
            System.out.println("Done cleaning");
        }));
        return this;
    }
}