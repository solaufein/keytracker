package com.keytracker.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Encryptor {

    private String defaultCipher = "DES";
    private final SecretKey myDesKey;

    public Encryptor(SecretKey myDesKey) {
        this.myDesKey = myDesKey;
    }

    public Encryptor(SecretKey myDesKey, String cipher) {
        this.myDesKey = myDesKey;
        if (!cipher.isEmpty()) {
            this.defaultCipher = cipher;
        }
    }

    public String encrypt(String textToEncrypt) {
        try {
            Cipher desCipher = Cipher.getInstance(defaultCipher);

            byte[] text = textToEncrypt.getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            return new String(textEncrypted);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return textToEncrypt;
    }
}