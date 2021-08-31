package Pages;

import Base.BaseUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class Login extends BaseUtil {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "email")
    private WebElement fieldEmail;
    @FindBy(how = How.ID, using = "password")
    private WebElement fieldPassword;
    @FindBy(how = How.ID, using = "submit")
    private WebElement btnSignIn;
    @FindBy(how = How.CLASS_NAME, using = "alert-danger")
//there may be multiple elements on the page with this class. Make more robust if possible.
    private WebElement loginFailMsg;
    @FindBy(how = How.CLASS_NAME, using = "alert-success")
//there may be multiple elements on the page with this class. Make more robust if possible.
    private WebElement loginSuccessMsg;

    private final Properties logins;
    private String logProp = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "logins.properties";
    private final Properties config;
    private String confProp = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "config.properties";

    public Login(WebDriver driver) {
        this.driver = driver;
        this.logins = new Properties();
        this.config = new Properties();
        try {
            this.logins.load(new FileInputStream(logProp));
            this.config.load(new FileInputStream(confProp));
        } catch (IOException e) {
            System.err.println("Could not load properties files. Ending test\n" + e);
            System.exit(1);
        }

        PageFactory.initElements(driver, this);
    }

    public Properties getLogins() {
        return logins;
    }

    public Properties getConfig() {
        return config;
    }

    /**
     * Checks what is in the environment variable in config.properties and navigates to
     * the URL for that environment.
     */
    public void navigateToLogin() {
        String env = config.getProperty("environment");
        System.out.println("Navigating to " + env.toUpperCase() + " login page");
        driver.get(config.getProperty(env + ".url"));

    }

    /**
     * Overloaded version of the {@link #navigateToLogin() navigateToLogin} method.
     * Use this when the environment variable in the config.properties
     * file cannot be used.
     * @param environment The environment to use. Must contain "dev", "prod", or "cloud". Case does not matter
     */
    public void navigateToLogin(String environment) {
        String env = "";
        if (environment.toLowerCase().contains("dev")) {
            env = "dev";
        } else if (environment.toLowerCase().contains("prod")) {
            env = "prod";
        } else if (environment.toLowerCase().contains("cloud")) {
            env = "cloud";

        } else {
            Assert.fail("Could not find the URL for " + environment + " in config.properties");
        }
        System.out.println("Navigating to " + environment + " login page");
        driver.get(config.getProperty(env + ".url"));
    }

    public void enterEmail(String email) {
        // because using the same name will cause the user to run out of sign-in attempts, add a date to the email.
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        email = email.replaceAll("<current date>", dateString + timeString);
        fieldEmail.sendKeys(email);
    }

    public void enterPass(String pass) {
        fieldPassword.sendKeys(pass);
    }

    public boolean errorMsgDisplayed() {
        try {
            return loginFailMsg.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Error message was not displayed.");
            return false;
        }
    }

    public String warningMsgDisplayed(String field) {
        //Note: these warnings are browser-specific. This code was written for Chrome.
        try {
            if (field.equals("password")) {
                return fieldPassword.getAttribute("validationMessage");
            }
            return fieldEmail.getAttribute("validationMessage");
        } catch (NoSuchElementException e) {
            System.out.println("Warning message was not displayed.");
            return null;
        }
    }

    public boolean confirmErrorMsg() {
        String LoginFailedMsg = "Login failed: Invalid user or password. Remaining Attempts:";
        wait.until(ExpectedConditions.visibilityOf(loginFailMsg));
        try {
            return loginFailMsg.getText().contains(LoginFailedMsg);
        } catch (NoSuchElementException e) {
            System.out.println("Did not find the login-failed message.");
            return false;
        }
    }

    public boolean confirmSuccessMsg() {
        String LoginSuccessMsg = "You are now logged out";
        wait.until(ExpectedConditions.visibilityOf(loginSuccessMsg));
        try {
            return loginSuccessMsg.getText().contains(LoginSuccessMsg);
        } catch (NoSuchElementException e) {
            System.out.println("Did not find the logout success message.");
            return false;
        }
    }

    public boolean confirmOnLoginPage() {
        try {
            return (fieldEmail.isDisplayed() && fieldPassword.isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Appear to not be on the login page.");
            return false;
        }
    }

    //TODO Scrub user logins before commit
    public void EnterCredentials(String user) throws Exception {
        logins.load(getClass().getClassLoader().getResourceAsStream("logins.properties"));

        String email;
        String pass;
        System.out.println("Entering login for " + user.toUpperCase() + " role\n");
        switch (user.toLowerCase()) {
            case "po initiator":
                email = logins.getProperty("email.poinitiator");
                pass = logins.getProperty("pass.poinitiator");
                break;
            case "po approver":
                email = logins.getProperty("email.poapprover");
                pass = logins.getProperty("pass.poapprover");
                break;
            case "po purchase officer":
                email = logins.getProperty("email.popurchaseofficer");
                pass = logins.getProperty("pass.popurchaseofficer");
                break;
            case "po procurement manager":
                email = logins.getProperty("email.poprocurementmanager");
                pass = logins.getProperty("pass.poprocurementmanager");
                break;
            case "super user":
                email = logins.getProperty("email.superuser");
                pass = logins.getProperty("pass.superuser");
                break;
            case "expense user":
                email = logins.getProperty("email.expenseuser");
                pass = logins.getProperty("pass.expenseuser");
                break;
            case "expense approver":
                email = logins.getProperty("email.expenseapprover");
                pass = logins.getProperty("pass.expenseapprover");
                break;
            case "expense manager":
                email = logins.getProperty("email.expensemanager");
                pass = logins.getProperty("pass.expensemanager");
                break;
            default:
                throw new Exception("No login information available for " + user + " please check Login list.");

        }
        fieldEmail.sendKeys(email);
        fieldPassword.sendKeys(pass);

    }

    /**
     * Compares userName input to the entries in <code>logins.properties</code> file that contain the "email." prefix. If a matching entry is found, the method will populate the email field of the PatraOne login page with the appropriate information.
     * @param userName The key of the email value from logins.properties without "email." prefix.
     *                 Spaces and capitalization do not matter.
     *                 (e.g., "Super User", "superuser", "SuperUser")
     */
    public void enterUserEmail(String userName) {
        String user = userName.toLowerCase().replaceAll("\\s+", "");
        fieldEmail.sendKeys(logins.getProperty("email." + user));
    }

    /**
     * Compares userPass input to the entries in <code>logins.properties</code> file that contain the "pass." prefix. If a matching entry is found, the method will populate the password field of the PatraOne login page with the appropriate information.
     * @param userPass The key of the password value from logins.properties without "pass." prefix.
     *                 Spaces and capitalization do not matter.
     *                 (e.g., "Super User, "superuser", "SuperUser")
     */
    public void enterUserPassword(String userPass) {
        String user = userPass.toLowerCase().replaceAll("\\s+", "");
        fieldPassword.sendKeys(logins.getProperty("pass." + user));
    }

    /**
     * Clicks the sign in button on the login page
     */
    public void ClickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSignIn));
        btnSignIn.click();
    }

    public void ClickUserIcon() {
        //sometimes the drop down does not appear, so click it until it does.
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        long startTime = System.currentTimeMillis();
        while (!logOutBtn.isDisplayed() && ((System.currentTimeMillis() - startTime) < 5000)) {
            userIcon.click();
            waitForMiliseconds(500);
        }
    }

    public void ClickLogOut() {
        wait.until(ExpectedConditions.elementToBeClickable(logOutBtn));
        logOutBtn.click();
    }

    @FindBy(how = How.CSS, using = ".glyphicon-user")
    private WebElement userIcon;
    @FindBy(how = How.XPATH, using = "//a/h4[contains(text(),'Log Out')]")
    private WebElement logOutBtn;
    // Tiles for each app
    @FindBy(how = How.CSS, using = ".wot")
    private WebElement tileWOT;
    @FindBy(how = How.CSS, using = ".pma")
    private WebElement tilePMA;
    @FindBy(how = How.CSS, using = ".ama")
    private WebElement tileAMA;
    @FindBy(how = How.CSS, using = ".imp")
    private WebElement tileImplementations;
    @FindBy(how = How.CSS, using = ".exp")
    private WebElement tileExpenses;
    @FindBy(how = How.CSS, using = ".prnt")
    private WebElement tilePrintShop;
    @FindBy(how = How.CSS, using = ".bil")
    private WebElement tileBilling;
    @FindBy(how = How.CSS, using = ".setup")
    private WebElement tileSetup;
    @FindBy(how = How.CSS, using = ".amaeb")
    private WebElement tileAMAEB;
    @FindBy(how = How.CSS, using = ".po")
    private WebElement tilePurchaseOrder;
    @FindBy(how = How.CSS, using =".hd")
    private WebElement tileHelpDesk;

    /**
     * Find and clicks on an app tile located on the apps page, then switches to the new tab.
     * @param tileName Name of the tile to be clicked
     */
    public void clickOnTile(String tileName) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        int windows = driver.getWindowHandles().size();

        do {
            switch (tileName.toLowerCase()) {
                case "work order tracking" -> wait.until(ExpectedConditions.elementToBeClickable(tileWOT)).click();
                case "pma" -> wait.until(ExpectedConditions.elementToBeClickable(tilePMA)).click();
                case "ama" -> wait.until(ExpectedConditions.elementToBeClickable(tileAMA)).click();
                case "implementations" -> wait.until(ExpectedConditions.elementToBeClickable(tileImplementations)).click();
                case "expenses" -> wait.until(ExpectedConditions.elementToBeClickable(tileExpenses)).click();
                case "print shop" -> wait.until(ExpectedConditions.elementToBeClickable(tilePrintShop)).click();
                case "billing" -> wait.until(ExpectedConditions.elementToBeClickable(tileBilling)).click();
                case "setup" -> wait.until(ExpectedConditions.elementToBeClickable(tileSetup)).click();
                case "ama-eb" -> wait.until(ExpectedConditions.elementToBeClickable(tileAMAEB)).click();
                case "purchase order" -> wait.until(ExpectedConditions.elementToBeClickable(tilePurchaseOrder)).click();
                case "help desk" -> wait.until(ExpectedConditions.elementToBeClickable(tileHelpDesk)).click();
                default -> Assert.fail("Could not find " + tileName.toUpperCase() + " tile. Please ensure you have the correct login");
            }
        } while (driver.getWindowHandles().size() == windows);


        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.close();
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @FindBy(xpath = "//center[normalize-space()=\"Patra Powered\"]")
    private WebElement pageName;

    public boolean onCorrectPage() {

        try {
            return pageName.isDisplayed();
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    public void waitForMiliseconds(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
