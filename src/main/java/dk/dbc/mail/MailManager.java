package dk.dbc.mail;


import jakarta.mail.Session;

public class MailManager {
    private final Session mailSession;
    public MailManager(Session mailSession) {
        this.mailSession = mailSession;
    }

    public MailSender newMail() {
        return new MailSender(mailSession);
    }
}
