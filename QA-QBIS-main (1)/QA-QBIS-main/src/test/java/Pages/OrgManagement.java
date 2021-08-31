package Pages;


import Base.BaseUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.*;

public class OrgManagement extends BaseUtil {

    private final WebDriver driver;

    @FindBy(how = How.XPATH, using = "//header/div[1]/button[1]/div[1]/*[1]")
    private WebElement menu;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Owners')]")
    private WebElement owners;

    @FindBy(id = "identifierId")
    private WebElement gmailUserName;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement btnNext;

    @FindBy(name = "password")
    private WebElement gmailPassword;

    @FindBy(xpath = "//*[@class='bog']/span")
    private List<WebElement> mails;

    private String gmailUserIdValue;

    private String gmailPwdValue;

    private final String gmailUrl = "https://mail.google.com/mail/";

    private static final int Max_Attempts = 10;

    public OrgManagement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loadProperties();
        setSystemOwners();
    }

    private void loadProperties() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("logins.properties"));
            gmailUserIdValue = props.getProperty("mailUser");
            gmailPwdValue = props.getProperty("mailPassword");
            String randomEmail = generateRandomEmailFor(gmailUserIdValue);
            System.out.println("Random Email: " + randomEmail);
            valueStore.put("randomEmail", randomEmail);
        } catch (Exception ex) {
            Assert.fail("mailUser and mailPassword properties are not found in logins.properties file.");
        }
    }

    private void setSystemOwners() {
        List<String> owners = new ArrayList<>();
        owners.add("Burns and Wilcox");
        owners.add("Markel ML Specialty");
        owners.add("Markel ML Team");
        owners.add("RPS Bollinger");
        owners.add("Socius Insurance");
        objectStore.put("systemOwners", owners);
    }

    private String generateRandomEmailFor(String email) {
        StringBuilder builder = new StringBuilder();
        int index = email.indexOf('@');
        builder.append(email.substring(0, index));
        builder.append('+');
        String uuid = UUID.randomUUID().toString();
        builder.append(uuid.substring(0, uuid.indexOf('-')));
        builder.append(email.substring(index));
        return builder.toString();
    }

    public void clickHamburgerMenu() {
        commonPage.clickElement(menu);
        System.out.println("Hamburger menu is clicked.");
    }

    public void scroll(int pixels) {
        commonPage.scrollPageVertically(pixels);
        System.out.println("Page is scrolled");
    }



    public void clickPasswordLink() {
        //WebElement link = driver.findElement(By.partialLinkText("#/activate/"));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("#/activate/")));
        commonPage.clickElement(link);
    }

    public void setOrgPassword() {
        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
        WebElement confirmPwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm_password")));
        commonPage.sendKeysToElement(pwd, "password");
        commonPage.sendKeysToElement(confirmPwd, "password");
        WebElement btnSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resetPassword-submit")));
        commonPage.clickElement(btnSubmit);
    }

    public void ensureUserLoginAs(String userType) {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='signIn-Email']")));
        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='signIn-Password']")));
        WebElement btnSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='signIn-SubmitLogin']")));
        String mailValue = valueStore.get("staffEmail");
        if (mailValue == null) {
            mailValue = valueStore.get("randomEmail");
        }
        commonPage.sendKeysToElement(email, mailValue);
        commonPage.sendKeysToElement(pwd, "password");
        commonPage.clickElement(btnSignIn);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        String homeUrl = "";
        switch (userType.toLowerCase()) {
            case "owner":
                homeUrl = "qowners/#/home";
                break;
            case "carrier staff":
                homeUrl = "qcarrier/#/home";
                break;
            case "agency staff":
                homeUrl = "qagency/#/home";
                break;

        }
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        System.out.println("Expected URl: " + homeUrl);
        Assert.assertTrue(currentUrl.endsWith(homeUrl), "New user successfully logged in.");
    }

    public void enterEmailToField(String field) {
        String email = valueStore.get("randomEmail");
        System.out.println("Entering "+email+" to "+field+" field.");
        commonPage.commonFieldEnter(field, email);
    }

    public void ensureValidationFails(String errorMessage) {
        WebElement validationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), \"" + errorMessage + "\")]")));
        System.out.println(validationMsg.getText());
        Assert.assertTrue(validationMsg.isDisplayed());
    }


    public void selectMenu(String sliderMenu) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + sliderMenu + "')]")));
        WebElement menuOption = driver.findElement(By.xpath("//div[contains(text(),'" + sliderMenu + "')]"));
        menuOption.click();
        System.out.println(sliderMenu + " Option is selected.");
    }

    public void alertPopupMessage(String alertMsg){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" +alertMsg+ "')]")));
        WebElement validationMessage = driver.findElement(By.xpath("//span[contains(text(),'" +alertMsg+ "')]"));
        System.out.println(validationMessage.getText());
    }
}

