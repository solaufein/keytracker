package com.keytracker.file;

import com.keytracker.KeyAppender;
import com.keytracker.encrypt.Encryptor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileWriter implements Writer {
    private File file;

    public MyFileWriter(String fileName) {
        String fileDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm'.txt'").format(new Date());
        String dateFileName = fileName + fileDateFormat;
        this.file = new File(dateFileName);
    }

    @Override
    public void write(KeyAppender keyAppender, Encryptor encryptor) {
        String value = retrieveValue(keyAppender, encryptor);

        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            printWriter.write(value);
        } catch (IOException ex) {
            System.err.println("Couldn't write this to file. IOException msg: " + ex.getMessage());
        }
    }

    private String retrieveValue(KeyAppender keyAppender, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(keyAppender.value()) : keyAppender.value();
    }
}