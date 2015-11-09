package com.keytracker;

public class KeyAppender {
    private final StringBuffer stringBuffer;

    public KeyAppender() {
        stringBuffer = new StringBuffer("");
    }

    public void append(String key) {
        if ("{BS}".equals(key)) {
            backspace();
        } else {
            stringBuffer.append(key);
        }
    }

    public String value() {
        return stringBuffer.toString();
    }

    public void clear() {
        stringBuffer.setLength(0);
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
