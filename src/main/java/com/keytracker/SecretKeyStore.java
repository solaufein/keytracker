package com.keytracker;

import com.keytracker.encrypt.KeyGen;
import com.keytracker.mail.MailSender;

import javax.crypto.SecretKey;
import java.util.Arrays;

public class SecretKeyStore {
    private final KeyGen keyGen;
    private final MailSender mailSender;
    private final ArgsResolver argsResolver;

    public SecretKeyStore(KeyGen keyGen, MailSender mailSender, ArgsResolver argsResolver) {
        this.keyGen = keyGen;
        this.mailSender = mailSender;
        this.argsResolver = argsResolver;
    }

    public SecretKey secretKey() {
        SecretKey secretKey = keyGen.generate();
        store(secretKey);

        return secretKey;
    }

    private void store(SecretKey secretKey) {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("key encoded array = ").append(Arrays.toString(secretKey.getEncoded())).append("\n")
                     .append("key encoded string = ").append(new String(secretKey.getEncoded()));


        mailSender.send(stringBuilder.toString(), argsResolver.getEmail());
    }
}
