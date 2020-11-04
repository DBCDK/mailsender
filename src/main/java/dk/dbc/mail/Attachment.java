package dk.dbc.mail;


import java.io.IOException;
import java.io.InputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

public class Attachment {
    private String mimetype;
    private String filename;
    private DataSource dataSource;

    public Attachment withMimetype(String mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    public Attachment withBodyPart(InputStream bodyPartInputStream, String filename) throws IOException {
        dataSource = (DataSource) new ByteArrayDataSource(bodyPartInputStream, mimetype);
        this.filename = filename;
        return this;
    }

    public Attachment withBodyPart(String bodyPart, String filename) throws IOException {
        dataSource = (DataSource) new ByteArrayDataSource(bodyPart, mimetype);
        this.filename = filename;
        return this;
    }

    public Attachment withBodyPartFromFile(String bodyPartFilename) {
        this.filename = filename;
        dataSource = new FileDataSource(filename);
        return this;
    }

    public MimeBodyPart build() throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setFileName(filename);
        mimeBodyPart.setDataHandler(new DataHandler(dataSource));
        return mimeBodyPart;
    }

}