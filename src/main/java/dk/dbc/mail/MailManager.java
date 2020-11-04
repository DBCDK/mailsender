package dk.dbc.mail;


import javax.mail.Session;

public class MailManager {
    private Session mailSession;
    public MailManager(Session mailSession) {
        this.mailSession = mailSession;
    }

    public MailSender newMail() {
        return new MailSender(mailSession);
    }
}
