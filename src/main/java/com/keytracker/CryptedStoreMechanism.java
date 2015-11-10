package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.file.Writer;

import java.util.ArrayList;
import java.util.List;

public class CryptedStoreMechanism implements StoreMechanism {
    private final Encryptor encryptor;
    private final List<Writer> writers;
    private KeyAppender keyAppender;

    public CryptedStoreMechanism(Encryptor encryptor) {
        this.encryptor = encryptor;
        this.writers = new ArrayList<>();
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

    @Override
    public void registerWriter(Writer writer) {
        this.writers.add(writer);
    }

    private String encryptValue(KeyAppender keyAppender, Encryptor encryptor) {
        String value = keyAppender.value();
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}

