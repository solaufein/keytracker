package com.keytracker;

import com.keytracker.encrypt.Encryptor;

public interface StoreMechanism {

    enum Store {
        FILE, MAIL
    }

    void store();

    void registerAppender(KeyAppender keyAppender);

    void registerEncryptor(Encryptor encryptor);
}
