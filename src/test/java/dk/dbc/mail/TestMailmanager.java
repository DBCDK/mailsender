package dk.dbc.mail;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMultipart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TestMailmanager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestMailmanager.class);

    @Test
    public void simple_check_of_mimemessage() throws MessagingException, IOException {
        MailManager mailManager = new MailManager(null);

        Sender sender = mailManager.newMail()
                .withRecipients("someone_out_there@outthere.dk")
                .withFromAddress("someone_in_here@inhere.dk")
                .withBodyText("<h1>some text</h1>")
                .withSubject("test")
                .withHeaders(new Headers()
                        .withHeader("Content-type", "text/HTML; charset=UTF-8").build())
                .withAttachments(
                        new Attachments()
                                .withAttachment(
                                        new Attachment()
                                                .withMimetype("text/html")
                                                .withBodyPart(TestMailmanager.class.getResourceAsStream("test.html"), "test.html")

                                ).build()
                )
                .build();

        Message receivedMail = sender.message;
        assertThat("Subject", receivedMail.getSubject(), equalTo("test"));

        MimeMultipart mimeMultipart = (MimeMultipart) receivedMail.getContent();
        String bodytext = (String) mimeMultipart.getBodyPart(0).getContent();
        String attachmentAsText = (String) mimeMultipart.getBodyPart(1).getContent();

        assertThat("Bodytext", bodytext, equalTo("<h1>some text</h1>"));
        String expectedAttachment = new String(TestMailmanager.class.getResourceAsStream("test.html").readAllBytes());
        assertThat("Atachment", attachmentAsText, equalTo(expectedAttachment));

    }
}