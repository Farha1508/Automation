package Base;

import org.junit.Assert;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.mail.search.SubjectTerm;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Emails {

    private Properties mailProperties;
    private String username;
    private String password;
    private Folder inbox;
    private Store store;
    private Session emailSession;

    public Emails(String username, String password) {
        this.username = username;
        this.password = password;
        String mailProp = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "mail.properties";
        this.mailProperties = new Properties();
        try {
            this.mailProperties.load(new FileInputStream(mailProp));
        } catch (IOException e) {
            Assert.fail("Could not load mail.properties file. Ending test\n" + e);
        }
        this.inbox = getInbox();
    }

    /**
     * Finds any unread emails with the given subject and returns the message body.
     * @param subject a String containing the subject to be searched for.
     * @return Returns a String with the full body of the email.
     */
    public String getEmailBySubject(String subject) {
        String emailBody = null;
        Assert.assertNotNull("inbox returned null from getInbox function", inbox);
        try {
            // Checks if the inbox Folder object is open and
            // makes the emails inside able to be read and edited if it is not.
            if(!inbox.isOpen()) inbox.open(Folder.READ_WRITE);
            // Retrieve the messages from the inbox folder in an array and print it.
            // This will only retrieve the emails that have not been read (SEEN).
            Message[] seen = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            // This will narrow down to only emails that contain the subject
            Message[] messages = inbox.search(new SubjectTerm(subject), seen);
            if (messages.length == 0) return null;
            Message message = messages[0];
            message.setFlag(Flags.Flag.SEEN, true);
            // Checking if message type is "text/plain" and printing it.
            if (message.isMimeType("text/plain")) {
                emailBody = message.getContent().toString();
                // Checking if message is a multipart object.
            } else if (message.isMimeType("multipart/*")) {
                // Sending the object to getTextFromMimeMultipart method.
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                emailBody = getTextFromMimeMultipart(mimeMultipart);
            }
        } catch (MessagingException | IOException e) {
            Assert.fail("Could not retrieve email from " + username + " inbox\n" + e);
        }

        return emailBody;

    }

    /**
     * Checks how many messages (read and unread) are in all folders
     * @return returns the number found or -1 if there is an issue
     */
    public int inboxMessageCount() {
        int messageCount = -1;
        try {
            messageCount = inbox.getMessageCount();
        } catch (MessagingException e) {
            System.out.println("Error:\n"+e);
        }
        return messageCount;
    }

    /**
     * Initializes and returns the inbox folder
     * @return Returns the inbox Folder object
     */
    private Folder getInbox() {
        Folder inbox = null;
        String host = mailProperties.getProperty("mail.imap.host");
        // setInstance will not check for default before creating a new instance.
        emailSession = Session.getDefaultInstance(mailProperties);
        try {
            // Create the imap store object to hold emails retrieved from gMail
            store = emailSession.getStore("imaps");
            // Connect to the gMail imap server.
            store.connect(host, username, password);
            // Create inbox Folder object which retrieves and stores the Inbox folder from gMail.
            inbox = store.getFolder("Inbox");
        } catch (MessagingException e) {
            Assert.fail("Could not get inbox for " + username + ". Exception: \n" + e);
        }


        return inbox;

    }

    /**
     * This method will check a MultiPart body to find and return the text.
     * Checks for message bodies that use either plain text or HTML.
     *
     * @param mimeMultipart The multipart message body
     * @return Returns any found message body as a String
     * @throws MessagingException
     * @throws IOException
     */
    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {

        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            // Checking if body is plain text.
            if (bodyPart.isMimeType("text/plain")) {
                return bodyPart.getContent().toString();
//                return (String) bodyPart.getContent();
                // Checking if body is in text with html.
            } else if (bodyPart.isMimeType("text/html")) {
                // Casting the HTML content to a String before returning
                return bodyPart.getContent().toString();
                // Checking if body is another MultiPart object.
            } else if (bodyPart.isMimeType("multipart/*")) {
                // Recursively using method to extract MultiPart body.
                return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return "BODY CONTENT COULD NOT BE FOUND!";
    }
}
