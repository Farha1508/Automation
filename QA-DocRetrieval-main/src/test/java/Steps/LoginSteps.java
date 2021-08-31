package Steps;

import Base.BaseUtil;
import Pages.Login;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginSteps extends BaseUtil {

    public LoginSteps() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }


    @When("I enter the email and password for the \"([^\"]*)\"$")
    public void iEnterTheEmailAndPasswordForThe(String user) throws Exception {
        currentLogin = user;
        login.EnterCredentials(user);
    }

    @When("I enter a(n) {string} email address and {string} password")
    public void iEnterEmail(String userType, String passType) throws Exception {
        // should this switch statement instead be in the enterEmail() function?
       switch (userType) {
            case "invalid" -> login.enterEmail(login.getLogins().getProperty("invalidUser"));
            case "valid" -> login.enterEmail(login.getLogins().getProperty("validUser"));
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }

        // should this switch statement instead be in the enterPass() function?
        switch (passType) {
            case "invalid" -> login.enterPass(login.getLogins().getProperty("invalidPassword"));
            case "valid" -> login.enterPass(login.getLogins().getProperty("validPassword"));
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }
    }

    /**
     * Javadoc for a step.
     * */
    @Then("I see the Login Failed message")
    public void iSeeTheLoginFailedMessage() throws Exception {
        System.out.println("Checking the error message.");
        boolean check = login.confirmErrorMsg();
        Assert.assertTrue(login.confirmErrorMsg(), "The error message was incorrect or not found.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }



}
