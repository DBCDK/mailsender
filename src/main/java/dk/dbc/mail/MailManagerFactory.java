package dk.dbc.mail;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.mail.Session;
import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MailManagerFactory factory
 * <p>
 * Synopsis:
 * </p>
 * <pre>
 *    // New instance
 *    MailManagerFactory MailManager = MailManagerFactoryFactory.create("smtp-sender-host");
 *
 *    // Singleton instance in CDI enabled environment
 *    {@literal @}Inject
 *    MailManagerFactoryFactory factory;
 *    ...
 *    MailManagerFactory MailManager = factory.getInstance();
 *
 *    // or simply
 *    {@literal @}Inject
 *    MailManagerFactory MailManager;
 * </pre>
 * <p>
 * The CDI case depends on the MailManager mailhost being defined as
 * the value of either a system property or environment variable
 * named MAIL_HOST.
 * </p>
 */
@ApplicationScoped
public class MailManagerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailManagerFactory.class);
    public static MailManager create(Session mailSession) {
        return new MailManager(mailSession);
    }

    @Resource(lookup = "mail/delivery")
    Session mailSession;

    MailManager MailManager;

    @PostConstruct
    public void initializManager() {
        MailManager = new MailManager(mailSession);
    }

    @Produces
    public MailManager getInstance() {
        return MailManager;
    }

}