package com.keytracker.storing;

import com.keytracker.ValueProvider;
import com.keytracker.file.Writer;

public class SingleStoreMechanism implements StoreMechanism {
    private final StoreParameters storeParameters;
    private Writer writer;
    private ValueProvider writerValue;

    public SingleStoreMechanism(ValueProvider writerValue, StoreParameters storeParameters) {
        this.writerValue = writerValue;
        this.storeParameters = storeParameters;
        writer = null;
    }

    @Override
    public void store() {
        if (writer != null) writer.write(writerValue);
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
