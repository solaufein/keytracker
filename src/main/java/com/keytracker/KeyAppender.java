package com.keytracker;

public class KeyAppender implements Appender, ValueProvider {
    private static final String BS = "{BS}";
    private final StringBuilder stringBuffer;
    private final StringBuilder stringBufferExtended;

    public KeyAppender() {
        this.stringBuffer = new StringBuilder("");
        this.stringBufferExtended = new StringBuilder("");
    }

    @Override
    public void append(String key) {
        if (BS.equals(key)) {
            backspace();
        } else {
            appends(key);
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

    private void appends(String key) {
        stringBuffer.append(key);
        stringBufferExtended.append(key);
    }

    private void backspace() {
        delete(stringBuffer);
        delete(stringBufferExtended);
    }

    private void delete(StringBuilder stringBuffer) {
        if (!isBufferEmpty(stringBuffer)) {
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
    }

    private boolean isBufferEmpty(StringBuilder stringBuffer) {
        return stringBuffer.length() == 0;
    }
}
