package dk.dbc.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

public class Sender {
    protected MimeMessage message;
    private Session session;

    public Sender withMessage(MimeMessage message) {
        this.message = message;
        return this;
    }

    public Sender withSession(Session session) {
        this.session = session;
        return this;
    }

    public void send() throws MessagingException {
        Transport.send(message);
    }
}
