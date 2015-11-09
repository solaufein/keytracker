package com.keytracker.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encryptor {

    private final String defaultCipher = "DES";

    public void encrypt(String textToEncrypt, SecretKey myDesKey, String cipher) {
        try {
            Cipher desCipher = Cipher.getInstance(cipher);

            byte[] text = textToEncrypt.getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);

            String s = new String(textEncrypted);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
