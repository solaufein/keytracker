package com.keytracker;

import com.keytracker.file.MyWriter;

public class FileStoreMechanism implements StoreMechanism {

    private final KeyAppender keyAppender;
    private final MyWriter myWriter;

    public FileStoreMechanism(KeyAppender keyAppender, MyWriter myWriter) {
        this.keyAppender = keyAppender;
        this.myWriter = myWriter;
    }

    @Override
    public void store() {
//        System.out.print(keyAppender.value());

        myWriter.write(keyAppender);
    }
}
