package com.keytracker.file;

import com.keytracker.ValueProvider;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFileWriter implements Writer {
    protected File file;

    public MyFileWriter(String fileName) {
        String fileDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm'.txt'").format(new Date());
        String dateFileName = fileName + fileDateFormat;
        this.file = new File(dateFileName);
    }

    @Override
    public void write(ValueProvider valueProvider) {
        String value = getValueAndClear(valueProvider);
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            printWriter.write(value);
        } catch (IOException ex) {
            System.err.println("Couldn't write this to file. IOException msg: " + ex.getMessage());
        }
    }

    private String getValueAndClear(ValueProvider valueProvider) {
        String value = valueProvider.value();
        valueProvider.clear();
        return value;
    }
}