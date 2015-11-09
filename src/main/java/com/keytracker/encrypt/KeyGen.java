package com.keytracker.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGen {
    public SecretKey generate(String cipher) {
        return getSecretKey(cipher);
    }

    public SecretKey generate() {
        return getSecretKey("DES");
    }

    private SecretKey getSecretKey(String s) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance(s);
            return keygenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
