package com.keytracker.file;

import com.keytracker.KeyAppender;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWriter {
    private final String dateFileName;
    private File file;

    public MyWriter(String fileName) {
        String fileDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm'.txt'").format(new Date());
        this.dateFileName = fileName + fileDateFormat;
        this.file = new File(dateFileName);
    }

    public void write(KeyAppender keyAppender) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            printWriter.write(keyAppender.value());
        } catch (IOException ex) {
            System.err.println("Couldn't write this to file. IOException msg: " + ex.getMessage());
        } finally {
            keyAppender.clear();
        }
    }
}