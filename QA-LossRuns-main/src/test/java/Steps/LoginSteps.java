package Steps;

import Base.BaseUtil;
import Pages.Login;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class LoginSteps extends BaseUtil {

    public LoginSteps() {
        login = new Login(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @Given("Navigate to login page of Loss Runs app in {string}")
    public void navigateToLoginPageOfLossRunsAppIn(String site)  throws IOException {
        System.out.println("Navigates to Loss Runs Login page");
        login.BaseUrl(site);
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
           case "empty":
               login.enterEmail("");
               break;
           case "invalid" :
               login.enterEmail(login.getLogins().getProperty("invalidUser"));
               break;
           case "valid" :
               login.enterEmail(login.getLogins().getProperty("validUser"));
               break;
           case "incorrect" :
               login.enterEmail(login.getLogins().getProperty("incorrectUser")); //email missing the '@' symbol.
               break;
           default :
               throw new Exception("No login information available for " + userType + " please check Login list.");

        }
        // should this switch statement instead be in the enterPass() function?
       switch (passType) {
            case "empty" :
                login.enterPass("");
                break;
            case "invalid" :
                login.enterPass(login.getLogins().getProperty("invalidPassword"));
                break;
            case "valid" :
                login.enterPass(login.getLogins().getProperty("validPassword"));
                break;
            default :
                throw new Exception("No login information available for " + userType + " please check Login list.");
        }
    }

    @Then("I see the Login Failed message")
    public void iSeeTheLoginFailedMessage() throws Exception {
        System.out.println("Checking the error message.");
        boolean check = login.confirmErrorMsg();
        Assert.assertTrue(login.confirmErrorMsg(), "The error message was incorrect or not found.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the empty {string} field warning")
    public void thereIsNoErrorDisplayed(String field) throws Exception {
        System.out.println("There should be a warning on the empty field.");
        String warningMsg = "Please fill in this field.";
        //Note: these warnings are browser-specific. This code was written for Chrome.
        Assert.assertEquals(login.warningMsgDisplayed(field), warningMsg);
        Assert.assertFalse(login.errorMsgDisplayed(), "An error message was displayed, but was not expected to.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I see the email error message")
    public void iSeeTheEmailErrorMessage() throws Exception {
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
    public void iAmRedirectedToLoginPage() throws Exception {
        System.out.println("Checking the error message.");
        Assert.assertTrue(login.confirmSuccessMsg(), "The success message could not be found, or had incorrect text.");
        Assert.assertTrue(login.confirmOnLoginPage(), "Appear to not be on the Login page.");
    }

    @Then("I will be taken to the apps page")
    public void iWillBeTakenToTheAppsPage() throws Exception {
        login.onCorrectPage();
    }

    @Then("I click on the {string} tile")
    public void iClickOnTheTile(String tileName) throws Exception {
        currentApp = tileName;
        System.out.println("Clicking on " + tileName + " tile");
        login.ClickOnTile(tileName);
        pageMap(currentApp);
        BaseUtil.pageLoaded();
    }

    @Then("I will be taken to the homepage for that app")
    public void iWillBeTakenToTheHomepageForThatApp() {
        BaseUtil.pageLoaded();
        Assert.assertTrue(commonPage.onCorrectPage(), "Homepage for " + currentApp + " not displayed");
        System.out.println("On homepage for " + currentApp);
    }

    @And("Return to the apps page")
    public void returnToTheAppsPage() {
        driver.findElement(By.id("index")).click();
    }

    @When("I Click on user icon")
    public void iClickOnUserIcon() {
        System.out.println("Clicking User Icon");
        login.ClickUserIcon();
    }

    @And("I click Logout button")
    public void iClickLogoutButton() {
        System.out.println("Clicking Logout button");
        login.ClickLogOut();
    }

    @Then("I log out")
    public void iLogOut() {
        System.out.println("Logging out " + currentLogin.toUpperCase());
        System.out.println(currentApp);

        if (currentApp.equalsIgnoreCase("setup")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown"))).click();
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-toggle"))).click(); /*Changed the locator as per logo used in Loss Runs App*/
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log Out')]"))).click();
        BaseUtil.pageLoaded();
    }

    @When("I Click on user icon in WOT")
    public void iClickOnUserIconinWOT() {
        System.out.println("Clicking User Icon");
        login.WOTClickUserIcon();
    }
}
