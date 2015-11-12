package com.keytracker.storing;

import com.keytracker.ValueProvider;
import com.keytracker.file.Writer;

public class SingleStoreMechanism implements StoreMechanism {
    private final StoreParameters storeParameters;
    private Writer writer;
    private ValueProvider value;

    public SingleStoreMechanism(ValueProvider value, StoreParameters storeParameters) {
        this.value = value;
        this.storeParameters = storeParameters;
        writer = null;
    }

    @Override
    public void store() {
        if (writer != null) writer.write(value);
    }

    @Override
    public StoreParameters getStoreParameters() {
        return storeParameters;
    }

    @Override
    public void registerWriter(Writer writer) {
        this.writer = writer;
    }
}
