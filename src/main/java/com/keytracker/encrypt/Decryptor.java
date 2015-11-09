package com.keytracker.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Decryptor {

    private final String defaultCipher = "DES";

    public void decrypt(String textEncrypted, SecretKey myDesKey, String cipher) {
        try {
            Cipher desCipher = Cipher.getInstance(cipher);

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

            byte[] text = textEncrypted.getBytes("UTF8");
            byte[] textDecrypted = desCipher.doFinal(text);

            String s = new String(textDecrypted);
            System.out.println(s);

        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
