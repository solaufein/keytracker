package com.keytracker.file;

import com.keytracker.KeyAppender;
import com.keytracker.encrypt.Encryptor;

public interface Writer {
    void write(KeyAppender keyAppender, Encryptor encryptor);
}