package dk.dbc.mail;

import java.util.HashSet;
import java.util.Set;

public class Attachments {
    private final Set<Attachment> attachments = new HashSet<>();

    public Attachments withAttachment(Attachment attachment) {
        attachments.add(attachment);
        return this;
    }

    public Set<Attachment> build() {
        return attachments;
    }

}
