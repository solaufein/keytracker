package com.keytracker.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Decryptor {

    private String defaultCipher = "DES";
    private final SecretKey myDesKey;

    public Decryptor(SecretKey myDesKey) {
        this.myDesKey = myDesKey;
    }

    public Decryptor(SecretKey myDesKey, String cipher) {
        this.myDesKey = myDesKey;
        if (!cipher.isEmpty()) {
            this.defaultCipher = cipher;
        }
    }

    public String decrypt(String textEncrypted) {
        try {
            Cipher desCipher = Cipher.getInstance(defaultCipher);

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

            byte[] text = textEncrypted.getBytes("UTF8");
            byte[] textDecrypted = desCipher.doFinal(text);

            return new String(textDecrypted);
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return textEncrypted;
    }
}