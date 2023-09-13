# mailsender
Simple mailsending client for payara micro.

Maven dependency:

```` xml
 <dependency>
      <groupId>dk.dbc</groupId>
      <artifactId>mailsender</artifactId>
      <version>2.0-SNAPSHOT</version>
    </dependency>
````


Payara micro service.json:
````json
"mail": {
    "mail/delivery": {
      "mailhost": "${MAIL_HOST}",
      "from": "${MAIL_FROM}",
      "user": "${MAIL_USER}"
    }
  }
````

Env vars MAIL_HOST and MAIL_FROM must be set.

Use:
```` java
package dk.dbc.promat.service.api;

import dk.dbc.mail.MailManager;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

public class testete {

    @Inject
    MailManager mailManager;

    @GET
        @Path("/mail/test")
        public Response testMail() throws MessagingException, IOException {
            mailManager.newMail()
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
                                                    .withBodyPart(testete.class.getResourceAsStream("test.html"), "test.html")
    
                                    ).build()
                    )
                    .build().send();
            return Response.ok().build();
        }
}
````
