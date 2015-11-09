package com.keytracker;

import com.keytracker.file.MyWriter;

import java.util.HashMap;
import java.util.Map;

public class ArgsResolver {
    private final String[] args;

    private final Map<String, StoreMechanism> storeMechanismMap = new HashMap<>();

    public ArgsResolver(String[] args) {
        this.args = args;
        storeMechanismMap.put("file", new FileStoreMechanism(keyAppender, writer));
    }

    public StoreMechanism getStoreMechanism(KeyAppender keyAppender, MyWriter writer) {
        for (String arg : args) {
            System.out.println(arg);
        }

        storeMechanismMap.get()

        return new FileStoreMechanism(keyAppender, writer);
    }
}
