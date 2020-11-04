package dk.dbc.mail;


import java.util.Map;
import java.util.Set;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
    private String fromAddress;
    private String recipients;
    private Set<Attachment> attachments;
    private String subject;
    private String bodyText;
    private Session session;
    private Map<String, String> headers;

    public MailSender(Session mailSession) {
        session = mailSession;
    }

    public MailSender withFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        return this;
    }

    public MailSender withRecipients(String recipients) {
        this.recipients = recipients;
        return this;
    }

    public MailSender withAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public MailSender withBodyText(String bodyText) {
        this.bodyText = bodyText;
        return this;
    }

    public MailSender withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailSender withHeaders(Map<String,String> headers) {
        this.headers = headers;
        return this;
    }


    public Sender build() throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeBodyPart.setText(bodyText);
        message.setFrom(fromAddress);
        message.setSubject(subject);
        message.addRecipients(Message.RecipientType.TO, recipients);
        if (headers != null) {
            for (Map.Entry<String, String > header : headers.entrySet()  ) {
                message.addHeader(header.getKey(), header.getValue());
                mimeBodyPart.addHeader(header.getKey(), header.getValue());
            }
        }
        mimeMultipart.addBodyPart(mimeBodyPart);

        if (attachments != null) {
            for (Attachment attachment : attachments) {
                mimeMultipart.addBodyPart(attachment.build());
            }
        }
        message.setContent(mimeMultipart);
        return new Sender().withMessage(message).withSession(session);
    }
}