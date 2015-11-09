package com.keytracker;

import de.ksquared.system.keyboard.KeyEvent;

public interface KeyResolver {
    enum MOD {
        SHIFT, ALT, CTRL, OTHER
    }

    String resolve(KeyEvent keyEvent);
}
