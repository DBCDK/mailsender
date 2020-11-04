package dk.dbc.mail;
import java.io.IOException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import org.junit.jupiter.api.Test;

public class TestMailmanager {

    @Test
    public void simple_send() throws MessagingException, IOException {
        Properties props = System.getProperties();

        props.put("mail.smtp.host", "mailhost.dbc.dk");

        Session session = Session.getInstance(props, null);
        MailManager mailManager = new MailManager(session);

        mailManager.newMail()
                .withRecipients("jbr@dbc.dk")
                .withFromAddress("hej@dbc.dk")
                .withBodyText("<h1>some text</h1>")
                .withSubject("test")
                .withHeaders(new Headers()
                        .withHeader("Content-type", "text/HTML; charset=UTF-8").build())
                .withAttachments(
                        new Attachments()
                                .withAttachment(
                                        new Attachment()
                                                .withMimetype("application/pdf")
                                                .withBodyPart(TestMailmanager.class.getResourceAsStream("README.pdf"), "README.pdf")

                                ).build()
                )
                .build().send();
    }
}