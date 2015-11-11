package com.keytracker;

public class KeyAppenderExt extends KeyAppender {
    private final StringBuffer stringBufferExtended;

    public KeyAppenderExt() {
        super();
        this.stringBufferExtended = new StringBuffer("");
    }

    @Override
    public void append(String key) {
        super.append(key);
        stringBufferExtended.append(super.value());
    }

    public String extValue() {
        return stringBufferExtended.toString();
    }

    public void clearExt() {
        stringBufferExtended.setLength(0);
    }
}
