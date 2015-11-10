package com.keytracker.file;

import com.keytracker.mail.MailSender;

public class MyMailWriter implements Writer {
    protected final MailSender mailSender;
    protected final String to;

    public MyMailWriter(MailSender mailSender, String to) {
        this.mailSender = mailSender;
        this.to = to;
    }

    @Override
    public void write(String value) {
        mailSender.send(value, to);
    }
}