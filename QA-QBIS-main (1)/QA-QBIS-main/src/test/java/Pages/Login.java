package Pages;


import Base.BaseUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Login {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "signIn-Email")
    private WebElement fieldEmail;
    @FindBy(how = How.ID, using = "signIn-Password")
    private WebElement fieldPassword;
    @FindBy(how = How.ID, using = "signIn-SubmitLogin")
    private WebElement btnSignIn;

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

    public void EnterCredentials(String user) throws Exception {

        String email;
        String pass;
        System.out.println("Entering login for " + user.toUpperCase() + " role\n");
        switch (user.toLowerCase().replaceAll("\\s+", "")) {
            case "carrieradmin":
                email = logins.getProperty("carrierAdminEmail");
                pass = logins.getProperty("carrierAdminPass");
                break;
            case "carrier":
                email = logins.getProperty("carrierEmail");
                pass = logins.getProperty("carrierPass");
                break;
            case "owner":
                email = logins.getProperty("ownerEmail");
                pass = logins.getProperty("ownerPass");
                break;
            case "agency":
                email = logins.getProperty("agencyEmail");
                pass = logins.getProperty("agencyPass");
                break;
            default:
                throw new Exception("No login information available for " + user + " please check Login list.");

        }
        fieldEmail.sendKeys(email);
        fieldPassword.sendKeys(pass);

    }

    public void ClickSignIn() {
        btnSignIn.click();
    }

    @FindBy(how = How.CSS, using = ".glyphicon-user")
    private WebElement userIcon;
    @FindBy(how = How.XPATH, using = "//a/h4[contains(text(),'Log Out')]")
    private WebElement logOutBtn;

    @FindBy(how = How.XPATH, using = "/html/body/title[contains(text(),'Patra Corp - Home')]")
    private WebElement pageName;

    public void onCorrectPage() throws Exception {
        try {
            pageName.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new Exception("Login failed. Apps page not reached");
        }
        System.out.println("On apps homepage");
    }


}
