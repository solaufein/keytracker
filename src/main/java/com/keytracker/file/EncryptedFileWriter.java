package com.keytracker.file;

import com.keytracker.ValueProvider;
import com.keytracker.encrypt.Encryptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EncryptedFileWriter extends MyFileWriter {

    private final Encryptor encryptor;

    public EncryptedFileWriter(String fileName, Encryptor encryptor) {
        super(fileName);
        this.encryptor = encryptor;
    }

    @Override
    public void write(ValueProvider valueProvider) {
        String encryptedValue = encryptValue(valueProvider.value(), encryptor);

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            printWriter.write(encryptedValue);
        } catch (IOException ex) {
            System.err.println("Couldn't write this to file. IOException msg: " + ex.getMessage());
        }

        valueProvider.clear();
    }

    private String encryptValue(String value, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}
