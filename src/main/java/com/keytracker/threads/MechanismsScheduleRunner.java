package com.keytracker.threads;

import com.keytracker.storing.StoreMechanism;
import com.keytracker.storing.StoreParameters;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MechanismsScheduleRunner {
    private final ScheduledExecutorService scheduledExecutorService;
    private final List<StoreMechanism> storeMechanisms;

    public MechanismsScheduleRunner(List<StoreMechanism> storeMechanisms) {
        this.storeMechanisms = storeMechanisms;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
    }

    public MechanismsScheduleRunner schedule(StoreMechanism storeMechanism, long initialDelay, long period, TimeUnit timeUnit) {
        this.scheduledExecutorService.scheduleAtFixedRate(storeMechanism::store, initialDelay, period, timeUnit);
        return this;
    }

    public MechanismsScheduleRunner schedule(StoreMechanism storeMechanism) {
        StoreParameters storeParameters = storeMechanism.getStoreParameters();
        this.scheduledExecutorService.scheduleAtFixedRate(storeMechanism::store, storeParameters.getInitialDelay(), storeParameters.getPeriod(), storeParameters.getTimeUnit());
        return this;
    }

    public void scheduleAll() {
        storeMechanisms.forEach(this::schedule);
    }

    public MechanismsScheduleRunner addShutdownHook(StoreMechanism storeMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(storeMechanism::store, "Shutdown-thread"));
        return this;
    }

    public MechanismsScheduleRunner addShutdownHook() {
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