package dk.dbc.mail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

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
