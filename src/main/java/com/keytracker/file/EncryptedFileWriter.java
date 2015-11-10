package com.keytracker.file;

import com.keytracker.encrypt.Encryptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Deprecated
public class EncryptedFileWriter extends MyFileWriter {

    private final Encryptor encryptor;

    public EncryptedFileWriter(String fileName, Encryptor encryptor) {
        super(fileName);
        this.encryptor = encryptor;
    }

    @Override
    public void write(String value) {
        String encryptedValue = encryptValue(value, encryptor);

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            printWriter.write(encryptedValue);
        } catch (IOException ex) {
            System.err.println("Couldn't write this to file. IOException msg: " + ex.getMessage());
        }
    }

    private String encryptValue(String value, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}
