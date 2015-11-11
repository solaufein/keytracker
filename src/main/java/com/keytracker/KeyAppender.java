package com.keytracker;

public class KeyAppender implements Appender, ValueProvider {
    private final StringBuffer stringBuffer;
    private final StringBuffer stringBufferExtended;

    public KeyAppender() {
        stringBuffer = new StringBuffer("");
        this.stringBufferExtended = new StringBuffer("");
    }

    @Override
    public void append(String key) {
        if ("{BS}".equals(key)) {
            backspace();        // todo
        } else {
            stringBuffer.append(key);
            stringBufferExtended.append(key);
        }
    }

    @Override
    public String value() {
        return stringBuffer.toString();
    }

    @Override
    public void clear() {
        stringBuffer.setLength(0);
    }

    @Override
    public String extValue() {
        return stringBufferExtended.toString();
    }

    @Override
    public void clearExt() {
        stringBufferExtended.setLength(0);
    }

    private void backspace() {
        if (!isBufferEmpty()) {
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
    }

    private boolean isBufferEmpty() {
        return stringBuffer.length() == 0;
    }
}
