package com.keytracker;

public interface StoreMechanism {

    enum Store {
        FILE, MAIL
    }

    void store();

    void registerAppender(KeyAppender keyAppender);
}
