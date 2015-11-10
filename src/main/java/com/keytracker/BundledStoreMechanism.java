package com.keytracker;

import com.keytracker.file.Writer;

import java.util.ArrayList;
import java.util.List;

public class BundledStoreMechanism implements StoreMechanism {
    private final List<Writer> writers;
    private KeyAppender keyAppender;

    public BundledStoreMechanism() {
        this.writers = new ArrayList<>();
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

    @Override
    public void registerWriter(Writer writer) {
        this.writers.add(writer);
    }
}
