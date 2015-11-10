package com.keytracker.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
            SecretKey secretKey = keygenerator.generateKey();
            System.out.println("key encoded array = " + Arrays.toString(secretKey.getEncoded()));
            System.out.println("key encoded string = " + new String(secretKey.getEncoded()));
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
