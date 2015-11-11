package com.keytracker.storing;

import com.keytracker.ValueProvider;
import com.keytracker.file.Writer;

import java.util.ArrayList;
import java.util.List;

public class BundledStoreMechanism implements StoreMechanism {
    private final List<Writer> writers;
    private final StoreParameters storeParameters;
    private final ValueProvider writerValue;

    public BundledStoreMechanism(StoreParameters storeParameters, ValueProvider writerValue) {
        this.storeParameters = storeParameters;
        this.writerValue = writerValue;
        this.writers = new ArrayList<>();
    }

    @Override
    public void store() {
        writers.stream().filter(writer -> writer != null).forEach(writer -> writer.write(writerValue));
    }

    @Override
    public StoreParameters getStoreParameters() {
        return storeParameters;
    }

    @Override
    public void registerWriter(Writer writer) {
        this.writers.add(writer);
    }
}
