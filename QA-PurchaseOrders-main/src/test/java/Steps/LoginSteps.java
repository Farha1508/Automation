package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginSteps extends BaseUtil {

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {

        login.navigateToLogin();
        driver.manage().window().maximize();
    }

    @Given("I am on the {string} login page")
    public void iAmOnTheLoginPage(String environment) {
        login.navigateToLogin(environment);
        driver.manage().window().maximize();
    }

    @When("I enter a(n) {string} email address and {string} password")
    public void iEnterEmail(String userType, String passType) throws Exception {
        // should this switch statement instead be in the enterEmail() function?
        switch (userType) {
            case "empty" -> login.enterEmail("");
            case "invalid" -> login.enterEmail(login.getLogins().getProperty("email.invaliduser"));
            case "valid" -> login.enterEmail(login.getLogins().getProperty("email.validuser"));
            case "incorrect" -> login.enterEmail(login.getLogins().getProperty("email.incorrectuser")); //email missing the '@' symbol.
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }

        // should this switch statement instead be in the enterPass() function?
        switch (passType) {
            case "empty" -> login.enterPass("");
            case "invalid" -> login.enterPass(login.getLogins().getProperty("pass.invaliduser"));
            case "valid" -> login.enterPass(login.getLogins().getProperty("pass.validuser"));
            default -> throw new Exception("No login information available for " + userType + " please check Login list.");
        }
    }

    @Then("I see the Login Failed message")
    public void iSeeTheLoginFailedMessage() {
        System.out.println("Checking the error message.");
        boolean check = login.confirmErrorMsg();
        Assert.assertTrue(login.confirmErrorMsg(), "The error message was incorrect or not found.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the empty {string} field warning")
    public void thereIsNoErrorDisplayed(String field) {
        System.out.println("There should be a warning on the empty field.");
        String warningMsg = "Please fill out this field.";
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertEquals(login.warningMsgDisplayed(field), warningMsg);
        Assert.assertFalse(login.errorMsgDisplayed(), "An error message was displayed, but was not expected to.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the email error message")
    public void iSeeTheEmailErrorMessage() {
        // full message looks something like "Please include an '@' in the email address. 'carlwu_patracorp.com' is missing an '@'."
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertTrue(login.warningMsgDisplayed("email").contains("Please include an '@' in the email address."), "The warning message was not found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @And("I click the Sign In button")
    public void iClickTheSignInButton() {
        System.out.println("Clicking Sign In button\n");
        login.ClickSignIn();
    }

    @Then("I am redirected to login page")
    public void iAmRedirectedToLoginPage() {
        System.out.println("Checking the error message.");
        Assert.assertTrue(login.confirmSuccessMsg(), "The success message could not be found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I will be taken to the apps page")
    public void iWillBeTakenToTheAppsPage() throws Exception {
        Assert.assertTrue(login.onCorrectPage(), "Apps selection page not displayed");
    }

    @When("I click on the {string} tile")
    public void iClickOnTheTile(String tileName) throws Exception {
        currentApp = tileName;
        System.out.println("Clicking on " + tileName + " tile");
        login.clickOnTile(tileName);
        pageLoaded();
    }

    @Then("I will be taken to the homepage for that app")
    public void iWillBeTakenToTheHomepageForThatApp() {
        pageLoaded();
        // TODO change 'true' in assertion to a function that uses a boolean to verify you have reached the app homepage
        Assert.assertTrue(true, "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);
    }

    @When("I enter the email and password for the {string}")
    public void iEnterTheEmailAndPasswordForThe(String user) {
        currentLogin = user;
        login.enterUserEmail(user);
        login.enterUserPassword(user);
    }


    @And("I reset the password for {string}")
    public void iResetThePasswordFor(String user) {
        if (user.contains("<gmail>")) {
            String email = login.getLogins().getProperty("gmail.email");
            if (user.contains("+")) {
                String modifier = user.substring(user.indexOf('+'));
                String[] eSplit = email.split("@");
                email = eSplit[0] + modifier + "@" + eSplit[1];
            }
            user = email;
        }
        System.out.println("Resetting password for \"" + user + "\".");
        Assert.assertTrue(commonForm.commonFieldEnter("Email address", user), "Could not find email address field!");
        driver.findElement(By.id("resetsubmit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_succ_msg")));

    }
}
