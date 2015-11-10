package com.keytracker;

import com.keytracker.file.Writer;

public class SingleStoreMechanism implements StoreMechanism {
    private Writer writer;
    private KeyAppender keyAppender;

    public SingleStoreMechanism() {
        writer = null;
    }

    @Override
    public void store() {
        if (writer != null) writer.write(keyAppender.value());
    }

    @Override
    public void registerAppender(KeyAppender keyAppender) {
        this.keyAppender = keyAppender;
    }

    @Override
    public void registerWriter(Writer writer) {
        this.writer = writer;
    }
}
