package com.keytracker.storing;

import com.keytracker.ValueProvider;
import com.keytracker.file.Writer;

public interface StoreMechanism {

    enum Store {
        FILE, MAIL;
    }

    void store();

    StoreParameters getStoreParameters();

    void registerWriter(Writer writer);
}
