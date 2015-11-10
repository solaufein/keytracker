package com.keytracker;

import com.keytracker.file.Writer;

public class SingleStoreMechanism implements StoreMechanism {

    private final Writer writer;
    private KeyAppender keyAppender;

    public SingleStoreMechanism(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void store() {
        if (writer != null) writer.write(keyAppender.value());
    }

    @Override
    public void registerAppender(KeyAppender keyAppender) {
        this.keyAppender = keyAppender;
    }
}
