package Steps;

import Base.BaseUtil;
import Base.Emails;
import io.cucumber.java.en.And;
import org.testng.Assert;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSteps extends BaseUtil {


    @And("I send an email with the following information")
    public void iSendAnEmailWithTheFollowingInformation(Map<String, String> emailDetails) {
        HashMap<String, String> emailContent = new HashMap<>(emailDetails);
        try {
            emails.sendEmail(emailContent);
        } catch (MessagingException | IOException e) {
            Assert.fail("Sending email failed with the following message:\n" + e);
        }
        System.out.println("Email with subject \"" + emailContent.get("Subject") + "\" sent to " + emailContent.get("To") + " from " + login.getLogins().getProperty("gmail.email"));
    }

    @And("I verify the email for {string} was received")
    public void iVerifyTheEmailForWasReceived(String emailType) {
        System.out.println("Retrieving email for \"" + emailType + "\" from " + login.getLogins().getProperty("gmail.email"));
        String subject = "";
        Pattern linkPattern = null;
        switch (emailType.toLowerCase()) {
            case "account creation" -> {
                subject = "Welcome to Your " + valueStore.get("Business Name") + " Account";
                linkPattern = Pattern.compile("(?<=Open )(.*?)(?= from your browser)");
            }
            case "forgot password" -> {
                subject = "Patra One Password Reset Request";
                linkPattern = Pattern.compile("(http.*?)(?=<)");
            }
            case "carrier Creation" -> subject = "PLACE_CC_EMAIL_SUBJECT_HERE";
            case "Agency Creation" -> subject = "PLACE_AC_EMAIL_SUBJECT_HERE";
            default -> Assert.fail("No subject found for " + emailType + " email type");
        }
        int msgCount = emails.inboxMessageCount();
        System.out.println("message count: " + msgCount);
        Assert.assertNotEquals(msgCount, -1, "Could not retrieve number of messages from inbox");
        String emailMessage = emails.getEmailBySubject(subject);
        if (emailMessage == null) {
            System.out.println("Message not found, waiting for up to one minute for message");
            long startTime = System.currentTimeMillis();
            while (emails.inboxMessageCount() == msgCount && (System.currentTimeMillis() - startTime) < 60000) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {

                }
            }
            emailMessage = emails.getEmailBySubject(subject);
        }
        Assert.assertNotNull(emailMessage,
                "Email with subject \"" + subject + "\" could not be found after waiting for one minute");
        System.out.println(emailMessage);
        assert linkPattern != null;
        Matcher msgBody = linkPattern.matcher(emailMessage);
        if (msgBody.find()) {
            String msgLink = msgBody.group();
            System.out.println("found following link: " + msgLink);
            valueStore.put("Email Link", msgLink);
        }
    }

    @And("I open the link from the email")
    public void iOpenTheLinkFromTheEmail() {
        driver.manage().deleteAllCookies();
        driver.get(valueStore.get("Email Link"));
    }
}
