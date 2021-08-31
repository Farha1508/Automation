package Pages;


import Base.BaseUtil;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class Login extends BaseUtil {
    private WebDriver driver;
    private Properties logins = new Properties();
    private String logProp = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "logins.properties";

    @FindBy(how = How.ID, using = "email")
    private WebElement fieldEmail;
    @FindBy(how = How.ID, using = "password")
    private WebElement fieldPassword;
    @FindBy(how = How.ID, using = "submit")
    private WebElement btnSignIn;

    @FindBy(how = How.CLASS_NAME, using = "alert-danger")
    private WebElement loginFailMsg;

    public Login(WebDriver driver) {
        this.driver = driver;
        try {
            this.logins.load(new FileInputStream(logProp));
        } catch (IOException e) {
            Assert.fail("Could not load logins.properties, please ensure file is available in src/test/resources folder");
        }
        PageFactory.initElements(driver, this);
    }

    //TODO Scrub user logins before commit
    public void EnterCredentials(String user) throws Exception {
        Properties login = new Properties();
        login.load(getClass().getClassLoader().getResourceAsStream("logins.properties"));

        String email;
        String pass;
        System.out.println("Entering login for " + user.toUpperCase() + " role\n");
        switch (user.toLowerCase()) {
            case "po initiator":
                email = login.getProperty("poInitEmail");
                pass = login.getProperty("poInitPass");
                break;
            case "po approver":
                email = login.getProperty("poApproveEmail");
                pass = login.getProperty("poApprovePass");
                break;
            case "po purchase officer":
                email = login.getProperty("poPurchOffEmail");
                pass = login.getProperty("poPurchOffPass");
                break;
            case "po procurement manager":
                email = login.getProperty("poProcManEmail");
                pass = login.getProperty("poProcManPass");
                break;
            case "super user":
                email = login.getProperty("superUserEmail");
                pass = login.getProperty("superUserPass");
                break;
            case "expense user":
                email = login.getProperty("expenseUserEmail");
                pass = login.getProperty("expenseUserPass");
                break;
            case "expense approver":
                email = login.getProperty("expenseApproveEmail");
                pass = login.getProperty("expenseApprovePass");
                break;
            case "expense manager":
                email = login.getProperty("expenseManEmail");
                pass = login.getProperty("expenseManPass");
                break;
            default:
                throw new Exception("No login information available for " + user + " please check Login list.");

        }
        fieldEmail.sendKeys(email);
        fieldPassword.sendKeys(pass);

    }

    public void ClickSignIn() {
        btnSignIn.sendKeys(Keys.ENTER);
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


    public void ClickOnTile(String tileName) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Thread.sleep(500);

        switch(tileName.toLowerCase()){
            case "work order tracking":
                wait.until(ExpectedConditions.elementToBeClickable(tileWOT)).click();
                //tileWOT.click();
                break;
            case "pma":
                wait.until(ExpectedConditions.elementToBeClickable(tilePMA)).click();
                break;
            case "ama":
                wait.until(ExpectedConditions.elementToBeClickable(tileAMA)).click();
                break;
            case "implementations":
                wait.until(ExpectedConditions.elementToBeClickable(tileImplementations)).click();
                break;
            case "expenses":
                wait.until(ExpectedConditions.elementToBeClickable(tileExpenses)).click();
                break;
            case "print shop":
                wait.until(ExpectedConditions.elementToBeClickable(tilePrintShop)).click();
                break;
            case "billing":
                wait.until(ExpectedConditions.elementToBeClickable(tileBilling)).click();
                break;
            case "setup":
                wait.until(ExpectedConditions.elementToBeClickable(tileSetup)).click();
                break;
            case "ama-eb":
                wait.until(ExpectedConditions.elementToBeClickable(tileAMAEB)).click();
                break;
            case "purchase order":
                wait.until(ExpectedConditions.elementToBeClickable(tilePurchaseOrder)).click();
                break;
            default:
                throw new Exception("Could not find " + tileName.toUpperCase() + " tile. Please ensure you have the correct login");


        }

        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.close();
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/title[contains(text(),'Patra Corp - Home')]")
    private WebElement pageName;

    public void onCorrectPage() throws Exception {
        try{
            pageName.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new Exception("Login failed. Apps page not reached");
        }
        System.out.println("On apps homepage");
    }

    public boolean confirmErrorMsg() throws Exception {
        String LoginFailedMsg = "Login failed: Invalid user or password. Remaining Attempts:";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text() , '"+LoginFailedMsg+"')]")));
        try {
            return loginFailMsg.getText().contains(LoginFailedMsg);
        } catch (NoSuchElementException e) {
            System.out.println("Did not find the login-failed message.");
            return false;
        }
    }

    public boolean confirmOnLoginPage() throws Exception {
        try {
            return (fieldEmail.isDisplayed() && fieldPassword.isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("Appear to not be on the login page.");
            return false;
        }
    }

    public Properties getLogins() {
        return logins;
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



}
