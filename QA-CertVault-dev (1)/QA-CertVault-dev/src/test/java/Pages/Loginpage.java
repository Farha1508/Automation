package Pages;

import com.gurock.testrail.APIException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.testgoogle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.System.out;
import static test.testgoogle.*;

public class Loginpage {
    WebDriver driver;

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.LINK_TEXT, using = "Home")  //Home link
    public WebElement homebtn;
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "How It Works")  //How It Works link
    public WebElement howitworksbtn;
    @FindBy(how = How.LINK_TEXT, using = "FAQ")  //FAQ link
    public WebElement faqbtn;
    @FindBy(how = How.LINK_TEXT, using = "Contact Us")  //Contact Us
    public WebElement contactusbtn;
    @FindBy(how = How.NAME, using = "name")  //Name text field
    public WebElement txtname;
    @FindBy(how = How.NAME, using = "company")  //Company text field
    public WebElement txtcompany;
    @FindBy(how = How.NAME, using = "email")  //Email text field
    public WebElement txtemailadd;
    @FindBy(how = How.XPATH, using = "//*[@id=\"contactForm\"]/div[2]/div[3]/button")   //Send Message btn
    public WebElement sendmsgbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Home\"]/nav/ul/li[5]/a")
    //Sign In/Register link - authLink //*[@id="section1"]/nav/ul/li[5]/a
    public WebElement signinregbtn;

    public void home() {
        homebtn.click();
    }

    public void howitworks() {
        howitworksbtn.click();
    }

    public void FAQ() {
        faqbtn.click();
    }

    public void contactus() {
        contactusbtn.click();
    }

    public void contactdetails() {
        txtname.sendKeys("Anusha");
        txtcompany.sendKeys("PATRA");
        txtemailadd.sendKeys("e.anusha@patracorp.com");
        sendmsgbtn.click();
    }

    public void signin() {
        signinregbtn.click();
    }

    /*
     Initializing elements - Login
     */
    @FindBy(how = How.ID, using = "email")
    public WebElement txtemail;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtpassword;
    @FindBy(how = How.ID, using = "submit")
    public WebElement btnSignIn;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div[2]/div/center")
    public WebElement loginfailed_msg;

    public void login(String Email, String Password) {
        txtemail.sendKeys(Email);
        txtpassword.sendKeys(Password);
    }

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger dismissible']")
    public WebElement disabledmsg;

    public void signInButton() throws Exception {
        //  btnSignIn.click();
        String actualURL = "https://certvault.dev.patracloud.com/home";
        if (driver.getCurrentUrl().equals(actualURL)) {
          //  addResultForTestCase("614", TEST_CASE_PASSED_STATUS, "");
        } else {
          //  addResultForTestCase("614", TEST_CASE_FAILED_STATUS, "");
        }

       /* String invaliduser = "Login failed: Invalid user or password. Remaining Attempts:";
        if(driver.getPageSource().contains(invaliduser)) {
            test.testgoogle.addResultForTestCase("615", TEST_CASE_PASSED_STATUS, "");
        }

        String invalidpwd = "Login failed: Invalid user or password. Remaining Attempts:";
        if(driver.getPageSource().contains(invalidpwd)) {
            test.testgoogle.addResultForTestCase("616", TEST_CASE_PASSED_STATUS, "");
        }

        if(txtemail.getText().isEmpty()) {
            test.testgoogle.addResultForTestCase("617", TEST_CASE_PASSED_STATUS, "");
        } driver.navigate().refresh();

        if(txtpassword.getText().isEmpty()) {
            test.testgoogle.addResultForTestCase("618", TEST_CASE_PASSED_STATUS, "");
        } driver.navigate().refresh();

        String lock_account = "https://certvault.dev.patracloud.com";
        if(driver.getCurrentUrl().equals(lock_account)) {
            test.testgoogle.addResultForTestCase("1635", TEST_CASE_PASSED_STATUS, "");
        }

        String val_disabled = "Unfortunately your account has been disabled,";
        String msg = disabledmsg.getText();
        System.out.println(msg.contains(val_disabled));
        if(msg.contains(val_disabled)) {
            test.testgoogle.addResultForTestCase("1732", TEST_CASE_PASSED_STATUS, "");
        }

        String attempts = "Login failed: Too many failed attempts.";
        String msg2 = disabledmsg.getText();
        System.out.println(msg2.contains(attempts));
        if(msg2.contains(attempts)) {
            test.testgoogle.addResultForTestCase("1634", TEST_CASE_PASSED_STATUS, "");
        }*/
    }

    /*
     Initializing elements - header's title
  */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//ul[@class='navbar-nav']")   // header title
    public WebElement user_role_header;
    @FindBy(how = How.XPATH, using = "//label[@class='profile-dropdown']//ul")   //profile title
    public WebElement profile_header;
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown-menu dropdown-menuCV1 show']")   //admin title
    public WebElement admin_header;

    public void header() throws Exception {
        String[] super_admin = {"USERS", "CERTIFICATES", "UPLOAD CERT", "CLIENTS", "BATCHES", "UPLOAD & PRINT", "ADMIN"};
        String[] broker_admin = {"USERS", "CERTIFICATES", "UPLOAD CERT", "CLIENTS", "MANAGE GROUPING", "BATCHES", "UPLOAD & PRINT"};
        String[] broker_user_cert_uploader = {"CERTIFICATES", "UPLOAD CERT", "CLIENTS", "BATCHES", "UPLOAD & PRINT"};
        String[] insured_holder_Admin = {"USERS", "CERTIFICATES"};
        String[] insured_holder_user = {"CERTIFICATES"};
        String[] Exception_user = {"EXCEPTION", "PROPOSED COMPANY MERGE"};
        String[] Document_user = {"PAPER REGISTRATION"};
        String[] Support_user = {"SUPPORT", "PROPOSED COMPANY MERGE"};

        String userrole = user_role_header.getText();
        if (userrole.contains("ADMIN")) {
            for (int i = 0; i < super_admin.length; i++) {
                if (userrole.contains(super_admin[i])) {
                    out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1683", TEST_CASE_PASSED_STATUS, "");// Super Admin

        } else if (userrole.contains("MANAGE GROUPING")) {
            for (int i = 0; i < broker_admin.length; i++) {
                if (userrole.contains(broker_admin[i])) {
                    out.println("Header access broker :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1691", TEST_CASE_PASSED_STATUS, "");// Broker Admin

        } else if (userrole.contains("UPLOAD CERT")) {
            for (int i = 0; i < broker_user_cert_uploader.length; i++) {
                if (userrole.contains(broker_user_cert_uploader[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("10104", TEST_CASE_PASSED_STATUS, ""); // Broker user
            addResultForTestCase("11753", TEST_CASE_PASSED_STATUS, "");  // Cert uploader user


        } else if (userrole.contains("USERS")) {
            for (int i = 0; i < insured_holder_Admin.length; i++) {
                if (userrole.contains(insured_holder_Admin[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1692", TEST_CASE_PASSED_STATUS, "");    // Holder Admin
            addResultForTestCase("1694", TEST_CASE_PASSED_STATUS, "");  //  Insured Admin

        } else if (userrole.contains("CERTIFICATES")) {
            for (int i = 0; i < insured_holder_user.length; i++) {
                if (userrole.contains(insured_holder_user[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("10091", TEST_CASE_PASSED_STATUS, "");  // Insured user
            addResultForTestCase("10103", TEST_CASE_PASSED_STATUS, "");    // Holder user

        } else if (userrole.contains("EXCEPTION")) {
            for (int i = 0; i < Exception_user.length; i++) {
                if (userrole.contains(Exception_user[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1702", TEST_CASE_PASSED_STATUS, "");  // Exception user

        } else if (userrole.contains("PAPER REGISTRATION")) {
            for (int i = 0; i < Document_user.length; i++) {
                if (userrole.contains(Document_user[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1698", TEST_CASE_PASSED_STATUS, "");  // Document user

        } else if (userrole.contains("SUPPORT")) {
            for (int i = 0; i < Support_user.length; i++) {
                if (userrole.contains(Support_user[i])) {
                    System.out.println("Header access :" + "\n" + userrole);
                    break;
                }
            }
            addResultForTestCase("1700", TEST_CASE_PASSED_STATUS, "");  // support user
        }
        List<WebElement> ad = driver.findElements(By.xpath("//*[@id=\"navbarDropdownMenuLink\"]"));
        if (ad.size() == 0) {
            System.out.println("Admin button not visible or accessible");
        } else if (admin_menu.isDisplayed()) {
            admin_menu.click();
            String admin = admin_header.getText();
            System.out.println("Admin access :" + "\n" + admin);
            admin_menu.click();
        }
        linkUserprofile.click();
        Thread.sleep(3000);
        String profilerole = profile_header.getText();
        System.out.println("Profile access :" + "\n" + profilerole);
        linkUserprofile.click();
    }

    /*
       Initializing elements - KPI's
    */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[1]/ul/li[2]/a")   //Recent KPI
    public WebElement recentKPIValue;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[1]/ul/li[3]/a")   //Active KPI
    public WebElement activeKPIValue;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[1]/ul/li[4]/a")   //Expired KPI
    public WebElement expiredKPIValue;

    public void KPIValue() {
        String RecentCount = recentKPIValue.getText();
        System.out.println("Recent Certs Value is :" + RecentCount);
        String ActiveCount = activeKPIValue.getText();
        System.out.println("Active Certs Value is :" + ActiveCount);
        String ExpiredCount = expiredKPIValue.getText();
        System.out.println("Expired Certs Value is :" + ExpiredCount);
    }

    public void KPIrecent() throws Exception {
        if (recentKPIValue.isEnabled()) {
            recentKPIValue.click();
            addResultForTestCase("99", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("99", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void KPIactive() throws Exception {
        if (activeKPIValue.isEnabled()) {
            activeKPIValue.click();
            addResultForTestCase("705", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("705", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void KPIexpired() throws Exception {
        if (expiredKPIValue.isEnabled()) {
            expiredKPIValue.click();
            addResultForTestCase("706", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("706", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
       Initializing elements - Certificates
    */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//a[contains(text(),'Certificates')]")
    public WebElement certificatestab;
    @FindBy(how = How.ID, using = "CertID")   //Cert id at Header grid
    public WebElement Certid;
    @FindBy(how = How.ID, using = "Broker")   //Broker at Header grid
    public WebElement Certbroker;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Insured\"]")  //Insured at Header grid
    public WebElement Certinsured;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Holder\"]")   //Holder at Header grid
    public WebElement Certholder;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Issuer\"]")   //Issuer
    public WebElement Certissuer;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Expirationfrom\"]")   //Expiration from
    public WebElement Certexpirationfrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Expirationto\"]")    //Expiration To
    public WebElement Certexpirationto;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Issuedfrom\"]")      //Issued From
    public WebElement Certissuedfrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Issuedto\"]")       //Issued To
    public WebElement Certissuedto;
    @FindBy(how = How.XPATH, using = "//*[@id=\"UploadDatefrom\"]")   //Upload Date from
    public WebElement Certuploaddatefrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"UploadDateto\"]")    //Upload Date To
    public WebElement Certuploaddateto;
    @FindBy(how = How.XPATH, using = "//*[@id='InsuredStatus']")    //Insured Status
    public WebElement Certinsuredstatus;
    @FindBy(how = How.XPATH, using = "//*[@id='HolderStatus']")    //Holder Status
    public WebElement Certholderstatus;
    @FindBy(how = How.XPATH, using = "//*[@id='Revised']")    //Revised
    public WebElement Certrevised;
    @FindBy(how = How.XPATH, using = "//*[@id='Renewal']")    //Renewal
    public WebElement Certrenewal;
    @FindBy(how = How.XPATH, using = "//*[@id='RenewalBatch']")    //Renewal batch
    public WebElement Certrenewalbatch;
    @FindBy(how = How.XPATH, using = "//*[@id='Company Group']")    //Company Group
    public WebElement Certcompanygroup;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/tbody/tr[1]/td[1]/a")
    //View Cert - //*[@id="certs-list-support"]/tbody/tr[1]/td[1]/a
    public WebElement viewCert;
    @FindBy(how = How.XPATH, using = "//table[@id='certs-list']/tbody/tr/td[13]")
    public WebElement companygroup;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div[2]/div[2]/h6/a")
    //Clear All Filters - Certificates
    public WebElement clearbtn_certs;
    @FindBy(how = How.XPATH, using = "//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']")
    public WebElement datepicker;

    public void certvalueassign(String certid, String insured, String holder, String issuer, String expirationfrom, String expirationto, String issuedfrom, String issuedto, String uploaddatefrom, String uploaddateto,
                                String insuredstatus, String holderstatus, String revised, String renewal, String renewalbatch, String companygroup) throws Exception {
        Certid.sendKeys(certid);
        Certinsured.sendKeys(insured);
        Certholder.sendKeys(holder);
        Certissuer.sendKeys(issuer);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationfrom').removeAttribute('readonly', 0);");
        Certexpirationfrom.sendKeys(expirationfrom);
        Actions actions = new Actions(driver);
        actions.moveToElement(Certexpirationfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationto').removeAttribute('readonly', 0);");
        Certexpirationto.sendKeys(expirationto);
        actions.moveToElement(Certexpirationto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedfrom').removeAttribute('readonly', 0);");
        Certissuedfrom.sendKeys(issuedfrom);
        actions.moveToElement(Certissuedfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedto').removeAttribute('readonly', 0);");
        Certissuedto.sendKeys(issuedto);
        actions.moveToElement(Certissuedto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDatefrom').removeAttribute('readonly', 0);");
        Certuploaddatefrom.sendKeys(uploaddatefrom);
        actions.moveToElement(Certuploaddatefrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDateto').removeAttribute('readonly', 0);");
        Certuploaddateto.sendKeys(uploaddateto);
        actions.moveToElement(Certuploaddateto).click().perform();
        Certinsuredstatus.sendKeys(insuredstatus);
        Certholderstatus.sendKeys(holderstatus);
        if (Certrevised.isDisplayed()) {
            Certrevised.sendKeys(revised);
        } else {
            out.println("Revised column doesn't exist in clientListViewCerts grid search");
        }
        Certrenewal.sendKeys(renewal);
        Certrenewalbatch.sendKeys(renewalbatch);
        Certcompanygroup.sendKeys(companygroup);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"certs-list\"]/tbody/tr[1]/td[3]"), Pattern.compile(holder))); //Montara Custom Homes

        String actualholder = driver.findElement(By.xpath("//*[@id=\"certs-list\"]/tbody/tr/td[3]")).getText();
        if (actualholder.contentEquals(holder)) {
            addResultForTestCase("100", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("100", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void insuredvalueassign(String CertId, String Broker, String Holder, String ExpirationFrom, String ExpirationTo, String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo) {
        Certid.sendKeys(CertId);
        Certbroker.sendKeys(Broker);
        Certholder.sendKeys(Holder);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationfrom').removeAttribute('readonly', 0);");
        Certexpirationfrom.sendKeys(ExpirationFrom);
        Actions actions = new Actions(driver);
        actions.moveToElement(Certexpirationfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationto').removeAttribute('readonly', 0);");
        Certexpirationto.sendKeys(ExpirationTo);
        actions.moveToElement(Certexpirationto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedfrom').removeAttribute('readonly', 0);");
        Certissuedfrom.sendKeys(IssuedFrom);
        actions.moveToElement(Certissuedfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedto').removeAttribute('readonly', 0);");
        Certissuedto.sendKeys(IssuedTo);
        actions.moveToElement(Certissuedto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDatefrom').removeAttribute('readonly', 0);");
        Certuploaddatefrom.sendKeys(UploadDateFrom);
        actions.moveToElement(Certuploaddatefrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDateto').removeAttribute('readonly', 0);");
        Certuploaddateto.sendKeys(UploadDateTo);
        actions.moveToElement(Certuploaddateto).click().perform();
    }

    public void holdervaluesassign(String CertId, String Insured, String Broker, String ExpirationFrom, String ExpirationTo, String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo) throws Exception {
        Certid.sendKeys(CertId);
        Certinsured.sendKeys(Insured);
        Certbroker.sendKeys(Broker);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationfrom').removeAttribute('readonly', 0);");
        Certexpirationfrom.sendKeys(ExpirationFrom);
        Actions actions = new Actions(driver);
        actions.moveToElement(Certexpirationfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Expirationto').removeAttribute('readonly', 0);");
        Certexpirationto.sendKeys(ExpirationTo);
        actions.moveToElement(Certexpirationto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedfrom').removeAttribute('readonly', 0);");
        Certissuedfrom.sendKeys(IssuedFrom);
        actions.moveToElement(Certissuedfrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('Issuedto').removeAttribute('readonly', 0);");
        Certissuedto.sendKeys(IssuedTo);
        actions.moveToElement(Certissuedto).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDatefrom').removeAttribute('readonly', 0);");
        Certuploaddatefrom.sendKeys(UploadDateFrom);
        actions.moveToElement(Certuploaddatefrom).click().perform();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('UploadDateto').removeAttribute('readonly', 0);");
        Certuploaddateto.sendKeys(UploadDateTo);
        actions.moveToElement(Certuploaddateto).click().perform();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"certs-list\"]/tbody/tr[1]/td[3]"), Pattern.compile(Broker)));

        String actualholder = driver.findElement(By.xpath("//*[@id=\"certs-list\"]/tbody/tr/td[3]")).getText();
        if (actualholder.contentEquals(Broker)) {
            addResultForTestCase("100", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("100", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void certificatesTab() {
        //  WebDriverWait wait = new WebDriverWait(driver, 100);
        // wait.until(ExpectedConditions.visibilityOf(certificatestab));
        certificatestab.click();
    }

    public void clear_certs() throws Exception {
        clearbtn_certs.click();
        if (Certid.getText().isEmpty()) {
            addResultForTestCase("101", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("101", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void clear() throws Exception {
        clearbtn.click();
        if (Certinsured.getText().isEmpty()) {
            System.out.println("search data cleared in grid fields");
            addResultForTestCase("4873", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("4873", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void verify_clear() throws Exception {
        clearbtn.click();
        if (gridcompanyname.getText().isEmpty()) {
            System.out.println("search data cleared in grid fields");
           // addResultForTestCase("1614", TEST_CASE_PASSED_STATUS, "");
        } else {
          //  addResultForTestCase("1614", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void exceptionclear() throws Exception {
        if (clearbtn_excp.isDisplayed()) {
            clearbtn_excp.click();
            addResultForTestCase("1587", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1587", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void mnguser_clear() throws Exception {
        //  if(mng_gridcompanyname.equals("") && mng_gridrole.equals(""))
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -400)");
        if (clearbtn_users.isDisplayed()) {
            clearbtn_users.click();
            addResultForTestCase("1585", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1585", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "Paper")  //Paper Registration Letter button
    public WebElement PaperRegletterbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewPaperRegistration\"]/div[3]/div/div[2]/label")   // Insured
    public WebElement paperinsuredchk;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewPaperRegistration\"]/div[3]/div/div[1]/label")   // Holder
    public WebElement paperholderchk;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewPaperRegistration\"]/a")   //Alert Close x btn
    public WebElement AlertClosebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"updateCertsPaperRegistration\"]")   //Alert Save
    public WebElement AlertSavebtn;
    @FindBy(how = How.ID, using = "Emailr") //Email Registration Letter button
    public WebElement Emailregletterbtn;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Holder Email address']") //Email holder text field
    public WebElement Emailholdertext;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Insured Email address']") //Email insured text field
    public WebElement Emailinsuredtext;
    @FindBy(how = How.XPATH, using = "//input[@class=\"effect-1 UserRegisterEmail\"]")
    //Holder email address in pop-up
    public WebElement enteremail;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewEmailCert\"]/a")  //Email cert Close btn
    public WebElement emailcertppupclosebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitRegisterEmail\"]")  // Email Reg pop-up Save btn
    public WebElement Emailpopupsavebtn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Remove Holder Email')]")
    public WebElement holder_remove_email_btn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Remove Insured Email')]")
    public WebElement insured_remove_email_btn;
    @FindBy(how = How.ID, using = "Emailc")  //Email cert button
    public WebElement Emailcertbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"UserEmail\"]")    //email address in pop-up
    public WebElement entercertemail;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitMail\"]")  //Save btn - popupsavebtn
    public WebElement emailcertpopupsavebtn;
    @FindBy(how = How.XPATH, using = "//div[@id='emailr-main']//a[contains(text(),'x')]")  //Email reg Close btn
    public WebElement emailregppupclosebtn;
    @FindBy(how = How.ID, using = "companyGroupValue")
    public WebElement companygrpvalue;
    @FindBy(how = How.ID, using = "editCompanyGroup")  //Edit Company group
    public WebElement edit_drp;
    @FindBy(how = How.ID, using = "saveCompanyGroup")  //Save Company group
    public WebElement save_drp;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/h3")
    public WebElement successvalid_comgrp;
    @FindBy(how = How.XPATH, using = "//button[@class='progress-button-CV-RemoveEmail']")
    public WebElement remove_email_btn;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Insured/Holder Email is in Domain Blocklist, the Certificate(s) have been emailed.')]")
    public WebElement email_block;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Email Registration Sent Successfully.')]")
    public WebElement emailsuccess;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Email sent successfully.')]")
    public WebElement emailcertsuccess;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Email Id Removed For Selected Unregistered User.')]")
    public WebElement remove_email;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Please enter any one of the email address to send Registration letter')]")
    public WebElement blank_email;
    @FindBy(how = How.XPATH, using = "//span[@id='BlockchainHash']")
    public WebElement BlockchainHash;
    @FindBy(how = How.XPATH, using = "//span[@id='PDFID']")
    public WebElement pdfid;
    @FindBy(how = How.XPATH, using = "//div[@class='row']/div[2]/table[1]/tbody[1]/tr[6]")
    public WebElement BlockchainWriteTime;
    @FindBy(how = How.XPATH, using = "//i[@id='faCopyPDFID']")
    public WebElement copypdf;
    @FindBy(how = How.XPATH, using = "//i[@id='faCopyBlockchainHash']")
    public WebElement copyblockchainhash;
    // email status
    @FindBy(how = How.ID, using = "unregEmailStatus")   //email status popup
    public WebElement email_status_popup;
    @FindBy(how = How.ID, using = "closeEmailStatus")  //okay button
    public WebElement ok_btn;
    @FindBy(how = How.XPATH, using = "//div[@id='unregEmailStatus-main']//div[@id='unregEmailsUpdateStatus']/div[1]")
    //grab emails
    public WebElement emails;
    @FindBy(how = How.XPATH, using = "//div[@id='unregEmailStatus-main']//div[@id='unregEmailsUpdateStatus']/div[2]")
    //grab status
    public WebElement email_status;
    //Add button in email registration form
    @FindBy(how = How.ID, using = "addHolderEmailAddress")  //Add holder button
    public WebElement add_holder;
    @FindBy(how = How.ID, using = "addInsuredEmailAddress")  //Add insured button
    public WebElement add_insured;
    @FindBy(how = How.XPATH, using = "//input[@class='effect-1 form-control extraEmail extraInsuredEmail']")
    //extra insured text field
    public WebElement add_extra_insured;
    @FindBy(how = How.XPATH, using = "//input[@class='effect-1 form-control extraEmail extraHolderEmail']")
    //extra holder text field
    public WebElement add_extra_holder;
    @FindBy(how = How.XPATH, using = "//button[@class='fax progress-button-CV-CrtUpld']")  //add button
    public WebElement add_button;
    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-remove']")  //cross button
    public WebElement cross_button;

    public void blockchainfields() throws Exception {
        // viewCert.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.urlContains("https://certvault.dev.patracloud.com/certs/view/"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
        List<WebElement> copyhash = driver.findElements(By.xpath("//i[@id='faCopyBlockchainHash']"));
        // List<WebElement> BlockchainWriteTime = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/section[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[6]"));
        if (pdfid.isDisplayed()) {
            if (copypdf.isDisplayed()) {
                copypdf.click();
            }
            out.println("PDF Id:" + pdfid.getText());
            addResultForTestCase("7451", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("7451", TEST_CASE_FAILED_STATUS, "");
        }
        if (BlockchainHash.isDisplayed()) {
            if (copyhash.size() == 0) {
                out.println("For Blockchain hash the copy button not visible ");
            } else if (copyblockchainhash.isDisplayed()) {
                copyblockchainhash.click();
            }
            out.println("Blockchain Hash:" + BlockchainHash.getText());
            addResultForTestCase("7419", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("7419", TEST_CASE_FAILED_STATUS, "");
        }
        if (BlockchainWriteTime.isDisplayed()) {
            out.println(BlockchainWriteTime.getText());
            addResultForTestCase("7419", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("7419", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void companygrp_cert() throws Exception {
        companygrpvalue.getText();
        if (companygrpvalue.getText().contentEquals("Heffernan Insurance Brokers")) {
            addResultForTestCase("5140", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5140", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void editcompanygrp_cert() throws Exception {
        edit_drp.click();
        Thread.sleep(2000);
        if (save_drp.isDisplayed()) {
            save_drp.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(successvalid_comgrp));
            addResultForTestCase("5141", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5141", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void viewCert() throws Exception {
        viewCert.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.urlContains("https://certvault.dev.patracloud.com/certs/view/"));

        String expectedurl = "https://certvault.dev.patracloud.com/certs/view/";
        if (driver.getCurrentUrl().contains(expectedurl)) {
            addResultForTestCase("1763", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1763", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void paperreg() throws Exception {
        if (PaperRegletterbtn.isDisplayed()) {
            PaperRegletterbtn.click();
            Thread.sleep(2000);
            try {
                paperinsuredchk.click();
            } catch (Exception e) {
                paperholderchk.click();
            }
            if (AlertSavebtn.isEnabled()) {
                AlertSavebtn.click();
                // AlertClosebtn.click();
                addResultForTestCase("102", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("102", TEST_CASE_PASSED_STATUS, "");
            }
        } else {
            System.out.println("Paper Registration button doesn't exist for this cert");
        }
    }

    public void validate_paperreg() throws Exception {
        List<WebElement> el = (List<WebElement>) driver.findElements(By.id("Paper"));
        if (el.size() == 0) {
            System.out.println("Paper registration button is not visible");
            test.testgoogle.addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, "");
        } else if (PaperRegletterbtn.isDisplayed() && PaperRegletterbtn.isEnabled()) {
            PaperRegletterbtn.click();
            Thread.sleep(2000);
            if (AlertSavebtn.isEnabled()) {
                AlertSavebtn.click();
                AlertClosebtn.click();
                addResultForTestCase("1758", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("1758", TEST_CASE_PASSED_STATUS, "");
            }
        }
    }

    public void emailreg(String EnterEmail) throws Exception {
        if (Emailregletterbtn.isDisplayed() && Emailregletterbtn.isEnabled()) {
            Emailregletterbtn.click();
            Thread.sleep(2000);
            String emailtextholder = Emailholdertext.getAttribute("value");
            String emailtextinsured = Emailinsuredtext.getAttribute("value");
            if (emailtextholder.isEmpty()) {
                Emailholdertext.sendKeys(EnterEmail);
                Thread.sleep(5000);
                add_holder.click();
                Thread.sleep(5000);
                cross_button.click();
                out.println("Clicked on add button than click on cross button to remove text field");
            } else if (emailtextinsured.isEmpty()) {
                Emailinsuredtext.sendKeys(EnterEmail);
                Thread.sleep(5000);
                add_button.click();
                Thread.sleep(5000);
                cross_button.click();
                out.println("Clicked on add button than click on cross button to remove text field");
            } else if (!emailtextholder.isEmpty() && !emailtextinsured.isEmpty()) {
                Thread.sleep(2000);
                List<WebElement> add_ins = (List<WebElement>) driver.findElements(By.id("addInsuredEmailAddress"));
                if (add_ins.size() == 0) {
                    System.out.println("add insured button is not visible");
                }
                if (add_insured.isDisplayed()) {
                    add_insured.click();
                    add_extra_insured.sendKeys(EnterEmail);
                    Thread.sleep(5000);
                    add_button.click();
                    Thread.sleep(5000);
                    cross_button.click();
                    out.println("Clicked on add button than click on cross button to remove text field");
                } else {
                    List<WebElement> add_hol = (List<WebElement>) driver.findElements(By.id("addHolderEmailAddress"));
                    if (add_hol.size() == 0) {
                        System.out.println("add holder button is not visible");
                    } else if (add_holder.isDisplayed()) {
                        add_holder.click();
                        add_extra_holder.sendKeys(EnterEmail);
                        Thread.sleep(5000);
                        add_button.click();
                        Thread.sleep(5000);
                        cross_button.click();
                        out.println("Clicked on add button than click on cross button to remove text field");
                    }
                }
            }
            if (Emailpopupsavebtn.isDisplayed()) {
                Emailpopupsavebtn.click();
                Thread.sleep(8000);
                if (email_status_popup.isDisplayed()) {
                    List<WebElement> rows = emails.findElements(By.tagName("div"));
                    List<WebElement> rows1 = email_status.findElements(By.tagName("div"));
                    java.util.Iterator<WebElement> i = rows.iterator();
                    java.util.Iterator<WebElement> j = rows1.iterator();
                    while (i.hasNext()) {
                        while (j.hasNext()) {
                            WebElement row = i.next();
                            WebElement row1 = j.next();
                            out.println(row.getText() + " - " + row1.getText());
                        }
                    }
                    if (email_status_popup.getText().contains("New Email Registration")) {
                        addResultForTestCase("871", TEST_CASE_PASSED_STATUS, "");
                        addResultForTestCase("12669", TEST_CASE_PASSED_STATUS, "");
                    }
                    if (email_status_popup.getText().contains("Blocked Email")) {
                        addResultForTestCase("12000", TEST_CASE_PASSED_STATUS, "");
                        addResultForTestCase("11564", TEST_CASE_PASSED_STATUS, "");
                    }
                    if (email_status_popup.getText().contains("Email Already Exist") || email_status_popup.getText().contains("Proposed Company Merge")) {
                        addResultForTestCase("12671", TEST_CASE_PASSED_STATUS, "");
                    }
                    ok_btn.click();
                    Thread.sleep(5000);
                }
            }
        }
    }

    public void validate_emailreg() throws Exception {
        List<WebElement> er = driver.findElements(By.id("Emailr"));
        if (er.size() == 0) {
            System.out.println("Email registration button is not visible");
            addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, "");
        } else if (Emailregletterbtn.isDisplayed() && Emailregletterbtn.isEnabled()) {
            Emailregletterbtn.click();
            enteremail.clear();
            enteremail.sendKeys("invalidemail.com"); //verifying invalid email format C876
            Emailpopupsavebtn.click();
            String emailinvalid = "Please Enter Valid Email";
            String val_extra_holder = "Allow only 3 Additional Holder email addresses";
            String val_extra_insured = "Allow only 3 Additional Insured email addresses";
            if (driver.getPageSource().contains(emailinvalid)) {
                out.println(emailinvalid);
                Thread.sleep(3000);
                addResultForTestCase("10671", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("10671", TEST_CASE_FAILED_STATUS, "");
            }
            if (add_holder.isDisplayed()) {
                for (int i = 0; i < 4; i++) {
                    add_holder.click();
                    Thread.sleep(3000);
                }
                if (driver.getPageSource().contains(val_extra_holder)) {
                    out.println(val_extra_holder);
                    Thread.sleep(3000);
                    addResultForTestCase("12672", TEST_CASE_PASSED_STATUS, "");
                } else {
                    String primary_hol_val = "Please enter primary holder email address to add extra holder email address";
                    if (driver.getPageSource().contains(primary_hol_val)) {
                        out.println(primary_hol_val);
                        Thread.sleep(5000);
                    }
                }
            } else if (add_insured.isDisplayed()) {
                for (int i = 0; i < 4; i++) {
                    add_insured.click();
                    Thread.sleep(3000);
                }
                if (driver.getPageSource().contains(val_extra_insured)) {
                    out.println(val_extra_insured);
                    Thread.sleep(3000);
                    addResultForTestCase("12672", TEST_CASE_PASSED_STATUS, "");
                } else {
                    String primary_ins_val = "Please enter primary insured email address to add extra Insured email address";
                    if (driver.getPageSource().contains(primary_ins_val)) {
                        out.println(primary_ins_val);
                        Thread.sleep(5000);
                    }
                }
            }
            emailregppupclosebtn.click();
        }
    }

    public void remove_email() throws Exception {
        Emailregletterbtn.click();
        Thread.sleep(3000);
        List<WebElement> remove_holder_email = driver.findElements(By.xpath("//button[contains(text(),'Remove Holder Email')]"));
        List<WebElement> remove_insured_email = driver.findElements(By.xpath("//button[contains(text(),'Remove Insured Email')]"));
        if (remove_holder_email.size() == 0) {
            Thread.sleep(2000);
            out.println("Remove holder email button is not visible");
        } else if (holder_remove_email_btn.isDisplayed() || insured_remove_email_btn.isDisplayed()) {
            holder_remove_email_btn.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(remove_email));
            out.println(remove_email.getText());
            Thread.sleep(2000);
            addResultForTestCase("11994", TEST_CASE_PASSED_STATUS, "");
        } else {
            if (remove_insured_email.size() == 0) {
                Thread.sleep(2000);
                out.println("Remove insured email button is not visible");
            } else if (insured_remove_email_btn.isDisplayed()) {
                insured_remove_email_btn.click();
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOf(remove_email));
                out.println(remove_email.getText());
                Thread.sleep(2000);
                addResultForTestCase("11994", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("11994", TEST_CASE_FAILED_STATUS, "");
            }
        }
        if (remove_holder_email.size() == 0 && remove_insured_email.size() == 0) {
            if (emailregppupclosebtn.isDisplayed()) {
                emailregppupclosebtn.click();
                out.println("Email Registration popup get closed");
                addResultForTestCase("875", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("875", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    public void updateemail() throws InterruptedException {
        viewCert.click();
        Thread.sleep(5000);
        if (Emailregletterbtn.isDisplayed() && Emailregletterbtn.isEnabled()) {
            Emailregletterbtn.click();
            Thread.sleep(2000);
            enteremail.clear();
            enteremail.sendKeys("updateemail@test.com");
            Thread.sleep(2000);
            Emailpopupsavebtn.click();
        }
    }

    public void emailcert() throws Exception {
        Emailcertbtn.click();
        Thread.sleep(2000);
        entercertemail.clear();
        entercertemail.sendKeys("sonali.gupta@jellyfishtechnologies.com");
        Thread.sleep(2000);
        if (emailcertpopupsavebtn.isEnabled()) {
            emailcertpopupsavebtn.click();   //C872
            Thread.sleep(2000);
            addResultForTestCase("872", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("872", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void validate_emailcert() throws Exception {
        List<WebElement> email_cert = driver.findElements(By.id("Emailc"));
        if (email_cert.size() == 0) {
            System.out.println("For Cert Uploader user email Certificate button not visible or accessible.");
            addResultForTestCase("12140", TEST_CASE_PASSED_STATUS, "");
        } else if (Emailcertbtn.isDisplayed()) {
            Emailcertbtn.click();
            Thread.sleep(3000);
            entercertemail.clear();
            Thread.sleep(2000);
            emailcertpopupsavebtn.click();  //verifying blank field validation C874
            if (driver.getPageSource().contains("Please Enter Valid Email")) {
                addResultForTestCase("874", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("874", TEST_CASE_PASSED_STATUS, "");
            }
            if (entercertemail.isDisplayed()) {
                entercertemail.sendKeys("invalidemail.com");  //verify invalid email pattern validation C876
                Thread.sleep(5000);
                emailcertpopupsavebtn.click();
                test.testgoogle.addResultForTestCase("10671", TEST_CASE_PASSED_STATUS, "");
            } else {
                test.testgoogle.addResultForTestCase("10671", TEST_CASE_PASSED_STATUS, "");
            }
            Thread.sleep(2000);
            if (emailcertppupclosebtn.isEnabled()) {
                emailcertppupclosebtn.click();   //C875
                Thread.sleep(4000);
                addResultForTestCase("875", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("875", TEST_CASE_PASSED_STATUS, "");
            }
        }
    }

    @FindBy(how = How.ID, using = "Add")  //Add Supporting document btn
    public WebElement addsuppdocbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fileUploadFormSdoc\"]/div[2]/div/button/span[1]/span[1]")
    //Upload File btn
    public WebElement addsupp_uploadfilebtn;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div[2]/div/button")  //Add Support Pop-up OK btn
    public WebElement suppdoc_Okbtn;
    @FindBy(how = How.ID, using = "View")  //View Supporting document btn
    public WebElement viewsuppdocbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"attachmentsTable\"]/tbody/tr/td[2]/a")
    public WebElement viewsupp_link;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div[4]/div[2]/button") //View Supp Doc- Delete Alert Ok
    public WebElement viewsuppdelete_alertOK;
    @FindBy(how = How.XPATH, using = "//*[@id=\"view-main\"]/div/a")
    public WebElement viewsupp_closebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"attachmentsTable\"]/tbody/tr/td[3]/center/i")
    public WebElement viewsupp_deleteicon;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div[2]/div/button")
    public WebElement deletesupp_Okbtn;
    @FindBy(how = How.XPATH, using = "//div[@class=\"notify-text\"]/p[text()=\"Please select a file before submit\"]")
    public WebElement valid_addsuppdoc;

    public void addSuppDoc() throws Exception {
        List<WebElement> ad = driver.findElements(By.id("Add"));
        if (ad.size() == 0) {
            System.out.println("Add Supporting Documents not visible or accessible.");
            addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, ""); // For insured/holder user
            addResultForTestCase("1637", TEST_CASE_PASSED_STATUS, ""); // For insured/holder Admin
        } else if (addsuppdocbtn.isDisplayed()) {
            addsuppdocbtn.click();
        }
    }

    public void validate_addsuppdoc() throws Exception {
        if (addsupp_uploadfilebtn.isDisplayed()) {
            addsupp_uploadfilebtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(valid_addsuppdoc));
            addResultForTestCase("10927", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("10927", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void uploadSupportdoc() throws Exception {
        addsupp_uploadfilebtn.click();
        Thread.sleep(8000);
        if (suppdoc_Okbtn.isEnabled()) {
            suppdoc_Okbtn.click();
            addResultForTestCase("1759", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1759", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void viewSuppDoc() throws Exception {
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");
        if (viewsuppdocbtn.isDisplayed()) {
            viewsuppdocbtn.click();
            Thread.sleep(3000);
            addResultForTestCase("1760", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1760", TEST_CASE_FAILED_STATUS, "");
        }
        String nodata = "No data available in table";
        if (driver.getPageSource().contains(nodata)) {
            System.out.println("No file exists in view supp documemt pop-up");
            viewsupp_closebtn.click();
        } else if (viewsupp_link.isDisplayed()) {
            viewsupp_link.click();
            String currentWindow = driver.getWindowHandle();  //Switching frames
            driver.switchTo().window(currentWindow);
        }
    }

    public void viewSuppDelete() throws Exception {
        List<WebElement> supdel = driver.findElements(By.xpath("//*[@id=\"attachmentsTable\"]/tbody/tr/td[3]/center/i"));
        if (supdel.size() == 0) {
            System.out.println("Delete Supporting Documents buttons not visible or accessible.");
            addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, ""); // For insured/holder user
            addResultForTestCase("1637", TEST_CASE_PASSED_STATUS, ""); // For insured/holder Admin
            addResultForTestCase("10933", TEST_CASE_PASSED_STATUS, ""); // For broker user
            addResultForTestCase("12140", TEST_CASE_PASSED_STATUS, ""); //For cert uploader
        } else if (viewsupp_deleteicon.isDisplayed()) {
            viewsupp_deleteicon.click();
            System.out.println("Clicks delete icon under Action column");
            Thread.sleep(4000);
            viewsuppdelete_alertOK.click();
            Thread.sleep(2000);
            if (deletesupp_Okbtn.isDisplayed()) {
                deletesupp_Okbtn.click();
                addResultForTestCase("10932", TEST_CASE_PASSED_STATUS, "");
            }
            Thread.sleep(2000);
            if (viewsupp_closebtn.isDisplayed()) {
                viewsupp_closebtn.click();
                addResultForTestCase("1760", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("1760", TEST_CASE_FAILED_STATUS, "");
            }
        } else {
            viewsupp_closebtn.click();
        }
    }

    public void DeleteCert() throws Throwable, IOException, APIException {
        List<WebElement> del = (List<WebElement>) driver.findElements(By.id("Dlt"));
        if (del.size() == 0) {
            System.out.println("Delete btn is not visible for insured/holder/broker/cert uploader users");
            addResultForTestCase("1637", TEST_CASE_PASSED_STATUS, "");
            addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, "");
            addResultForTestCase("12140", TEST_CASE_PASSED_STATUS, "");
        } else if (delete_certbtn.isDisplayed()) {
            delete_certbtn.click();
            Thread.sleep(4000);
            if (deletealert_okbtn.isDisplayed() && deletealert_okbtn.isEnabled()) {
                deletealert_okbtn.click();
                deletealert_cancelbtn.click();
                addResultForTestCase("1581", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("1581", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    /*
      Initializing elements - Certificates
    */
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[2]")
    //Insured Status: activate to sort column descending
    public WebElement insuredstatus_asc;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[3]")
    //Holder Status: activate to sort column ascending
    public WebElement holderstatus_asc;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[10]")
    //Revised: activate to sort column descending
    public WebElement revised_asc;

    public void sort_certificates() throws Exception {
        insuredstatus_asc.click();
        Thread.sleep(2000);
        insuredstatus_asc.click();
        if (insured_asc.isEnabled()) {
            addResultForTestCase("1615", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1615", TEST_CASE_PASSED_STATUS, "");
        }
        Thread.sleep(2000);
        holderstatus_asc.click();
        Thread.sleep(2000);
        holderstatus_asc.click();
        Thread.sleep(2000);
        revised_asc.click();
    }

    /*
      Initializing elements - Manage Users
    */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//a[contains(text(),'Users')]")    //Manage Users tab
    public WebElement linkManageUsers;

    public void manageUsers() throws InterruptedException {
        linkManageUsers.click();
        Thread.sleep(2000);
    }
    /*
    Initializing elements - Paper Registration
     */

    //  @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list_wrapper\"]/div/div/a/i")   // Paper Registration Letters btn
    @FindBy(how = How.XPATH, using = "//*[@id=\"Paper\"]")   //Generate Paper Letters
    public WebElement GenPaperregletterbtn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'OK')]")
    // Alert OK Paper Registration Letters btn - //*[@id="paper-main"]/div[1]/div[4]/div[2]/button
    public WebElement alertok;
    @FindBy(how = How.XPATH, using = "//button[@id='Preg']")
    public WebElement alertsave;
    @FindBy(how = How.XPATH, using = "//button[@class='pview popup-close']")
    // Alert Cancel Paper Registration Letters btn - //*[@id="paper-main"]/div[1]/div[4]/div[1]/button
    public WebElement alertCancel;
    @FindBy(how = How.ID, using = "brokerCompanyHelp")   //GENERATE PAPER LETTERS error validation
    public WebElement Genpaper_errvalidation;
    @FindBy(how = How.ID, using = "brokerCompanyId")  //Broker Company
    public WebElement brokercompnydrp;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list\"]/tbody/tr[1]/td[1]/div/label/span/span")
    //Select user/row
    public WebElement checkrow;
    @FindBy(how = How.ID, using = "Paper_registration")   //Flag for paper Registration
    public WebElement flag_PR;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Preg\"]")  //Flag for paper Registration - Save btn
    public WebElement flagPR_savebtn;
    @FindBy(how = How.XPATH, using = "//*[@id='Account']")  //Account
    public WebElement grid_account;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[2]/div/div/table/tfoot/tr/td[3]/input")
    public WebElement papreggrid_company;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'dropdown-item nav-link')][contains(text(),'Paper Registration')]")
    //paper Registration menu
    public WebElement linkpaperregistration;
    @FindBy(how = How.XPATH, using = "//*[@id='Type']")  //Company Type
    public WebElement grid_companytype;
    @FindBy(how = How.XPATH, using = "//*[@id='BrokerCompany']")  //broker company
    public WebElement grid_brokercompany;
    @FindBy(how = How.XPATH, using = "//input[@id='AddDatefrom']")  //Add date from
    public WebElement grid_adddatefrom;
    @FindBy(how = How.XPATH, using = "//input[@id='AddDateto']")  //Add date to
    public WebElement grid_adddateto;
//    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[7]/div/a[1]")  //paper Registration menu
//    public WebElement linkpaperregistration;

    public void paperRegistrationpage() {
        linkpaperregistration.click();
    }

    public void papregsearchgrid(String Account, String Company, String CompanyType, String BrokerCompany, String PaperAddDateFrom, String PaperAddDateTo) throws Exception {
        grid_account.sendKeys(Account);
        papreggrid_company.sendKeys(Company);
        grid_companytype.sendKeys(CompanyType);
        grid_brokercompany.sendKeys(BrokerCompany);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", grid_adddatefrom, PaperAddDateFrom);// Enables the from date box
        Thread.sleep(3000);
        grid_adddatefrom.sendKeys(PaperAddDateFrom);
        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", grid_adddateto, PaperAddDateTo);// Enables the to date box
        Thread.sleep(3000);
        grid_adddateto.sendKeys(PaperAddDateTo);
        Thread.sleep(3000);
        actions.click().perform();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"manage-users-list\"]/tbody/tr/td[3]"), Pattern.compile(Company)));

        String expectedbatchid = driver.findElement(By.xpath("//*[@id=\"manage-users-list\"]/tbody/tr/td[3]")).getText();
        if (expectedbatchid.contains(Company)) {
           // addResultForTestCase("11796", TEST_CASE_PASSED_STATUS, "");
        } else {
          //  addResultForTestCase("11796", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void paperRegltrs() throws Exception {
        GenPaperregletterbtn.click();
        System.out.println("Clicks Generate Paper Registration Letters");
        Thread.sleep(5000);

        if (alertok.isDisplayed()) {
            alertok.click();
            System.out.println(Genpaper_errvalidation.getText());
            addResultForTestCase("5121", TEST_CASE_PASSED_STATUS, ""); //103
        } else {
            addResultForTestCase("5121", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void paperregltrs_cancel() throws Exception {
        if (alertCancel.isDisplayed()) {
            alertCancel.click();
            System.out.println("Clicks Cancel button in GENERATE PAPER LETTERS");
            addResultForTestCase("864", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("864", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void flag() throws Exception {
        flag_PR.click();
        String flag_errvalidation = "Please select any one of the account to send paper registration.";
        if (driver.getPageSource().contains(flag_errvalidation)) {
            System.out.println("error text is:" + flag_errvalidation);
            addResultForTestCase("5123", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5123", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void selectrow() {
        checkrow.click();
    }

    public void flagforPR() throws Throwable {
        flag_PR.click();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", flagPR_savebtn);
        flagPR_savebtn.click();

        String flag_successvalid = "Success\n" +
                "Paper Registration Approved.";
        if (driver.getPageSource().contains(flag_successvalid)) {
            System.out.println("success text is:" + flag_successvalid);
            addResultForTestCase("5124", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5124", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void brokerCompany() throws Exception {
        GenPaperregletterbtn.click();
        brokercompnydrp.click();
        Select broker = new Select(brokercompnydrp);
        broker.selectByVisibleText("  Heffernan Insurance Brokers");
        if (alertok.isDisplayed()) {
            alertok.click();
            driver.getCurrentUrl().contains("https://certvault.dev.patracloud.com/paper-registrations/genLetters/6");
            addResultForTestCase("103", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("103", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list\"]/tbody/tr/td[8]")   // Total letter sent
    public WebElement Total_letter;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list\"]/tbody/tr/td[7]")   // Total letter sent
    public WebElement letter_Sent;

    public void Total_letter_sent() throws Exception {
        String totallettersent = Total_letter.getText();
        out.println("Total Letters Sent status is:" + totallettersent);
        Thread.sleep(3000);
        addResultForTestCase("5122", TEST_CASE_PASSED_STATUS, "");
    }

    public void letter_sent() throws Exception {
        String lettersent = letter_Sent.getText();
        out.println("Total Letters Sent status is:" + lettersent);
        Thread.sleep(3000);
        addResultForTestCase("5122", TEST_CASE_PASSED_STATUS, "");
    }

    // @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list_wrapper\"]/div[1]/div[2]/button[1]")   //Add User - //*[@id="manage-users-list_wrapper"]/div[1]/div[1]/button
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[2]/div/div[2]/div/button/a")
    public WebElement Adduserbtn;
    @FindBy(how = How.ID, using = "AccountName")  //add user popup Name
    public WebElement addname;
    @FindBy(how = How.XPATH, using = "//*[@id=\"AddUpdateUserForm\"]/div[2]/div/div/div/div[5]/input")
    //add user popup Email Address - //*[@id="AddUpdateUserForm"]/div/div[2]/div[5]/input
    public WebElement addemailaddress; //*[@id="Account"]
    //  @FindBy(how = How.XPATH, using = "//*[@id=\"EncryptedPassword\"]")   //add user pop up Password
    @FindBy(how = How.XPATH, using = "//*[@id=\"AddUpdateUserForm\"]/div[2]/div/div/div[1]/div[6]/input")
    public WebElement adduserpwd;
    @FindBy(how = How.ID, using = "RoleID")   //add user pop up role
    public WebElement adduserrole;
    @FindBy(how = How.ID, using = "CompanyTypeID")   //add user pop up Company Type
    public WebElement addusercompanytype;
    @FindBy(how = How.ID, using = "CompanyID")   //add user pop up Company Name
    public WebElement addusercompanyname;
    @FindBy(how = How.XPATH, using = "//a[@class='close-btn popup-close'][contains(text(),'x')]")
    //Add User popup Close btn //*[@id="AddUpdateUserForm"]/div/div[4]/button[2]
    public WebElement adduserpopupclosebtn;
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary user-add-edit']")
    public WebElement addusersavebtn;
    //div[contains(@class, 'Caption' and .//text()='Model saved']
    @FindBy(how = How.XPATH, using = "//*[@id=\"AddUpdateUserForm\"]/div/div[1]/button/span")  //X btn
    public WebElement xmark;
    @FindBy(how = How.ID, using = "Name")   //Grid Name
    public WebElement mng_gridname;
    @FindBy(how = How.ID, using = "Account")   //Grid Account
    public WebElement mng_gridaccount;
    @FindBy(how = How.ID, using = "Company Name")   //Grid Company Name
    public WebElement mng_gridcompanyname;
    @FindBy(how = How.ID, using = "Company ID")   //Grid Company ID
    public WebElement mng_gridcompanyid;
    @FindBy(how = How.ID, using = "Role")   //Grid Role
    public WebElement mng_gridrole;
    @FindBy(how = How.ID, using = "LastLoginfrom")   //Grid Last login from
    public WebElement mng_gridlastloginfrom;
    @FindBy(how = How.ID, using = "LastLoginto")   //Grid Last login to
    public WebElement mng_gridlastloginto;
    @FindBy(how = How.XPATH, using = "//table[@id='manage-users-list']/tbody/tr/td[7]/a[2]/span[1]")
    //Manage user Edit Symbol - //*[@id="manage-users-list"]/tbody/tr/td[6]/div/button/i
    public WebElement mng_editsymbol;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/div[2]/div[1]/form[1]/div[2]/div[3]/div[1]/button[1]")
    //Edit User Save btn
    public WebElement editusersavebtn;
    @FindBy(how = How.ID, using = "EncryptedUserPassword")   //Password Confirmation pop-up
    public WebElement confirmuserpwd;
    @FindBy(how = How.XPATH, using = "//*[@id=\"adminUserPassword\"]/div[3]/div/button[text()='Continue']")
    //Continue btn - Password Confirmation pop-up
    public WebElement continuebtn;
    //  @FindBy(how = How.ID, using = "disable-user-btn")   //Disable btn
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[2]/div/div[1]/table/tbody/tr/td[6]/a[3]/span")
    public WebElement mng_disablebtn;
    @FindBy(how = How.ID, using = "updateUserStatusMessage") // Disable - text
    public WebElement enabledisable_text;
    //  @FindBy(how = How.ID, using = "enable-user-btn")   //Enable btn
    @FindBy(how = How.XPATH, using = "//table[@id='manage-users-list']/tbody/tr/td[7]/a[3]/span[1]")
    public WebElement user_enabledisablebtn;
    @FindBy(how = How.XPATH, using = "//div[@id='dlte-main']//button[@class='progress-button']")
    public WebElement enable_disable_alertokbtn;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/h6/a")
    public WebElement clearbtn_users;

    public void addUser(String name, String role, String companytype, String companyname, String emailaddress, String pwd) throws Exception {
        addname.sendKeys(name);
        Thread.sleep(3000);
        adduserrole.sendKeys(role);
        if (addusercompanytype.isEnabled() && addusercompanyname.isEnabled()) {
            System.out.println("Company Type & Name fields are Enabled");
        } else {
            System.out.println("Company Type & Name fields are Disabled");
            String comptyp = addusercompanytype.getAttribute("value");
            System.out.println("Company type dropdown value is:" + comptyp);
            if (Objects.equals(comptyp, "5") && role.equals("Patra Documents")) {
                addResultForTestCase("10028", TEST_CASE_PASSED_STATUS, "");
            } else if (Objects.equals(comptyp, "5") && role.equals("Support")) {
                addResultForTestCase("10033", TEST_CASE_PASSED_STATUS, "");
            } else if (Objects.equals(comptyp, "5") && role.equals("Exception Handling")) {
                addResultForTestCase("10034", TEST_CASE_PASSED_STATUS, "");
            } else if (Objects.equals(comptyp, "5") && role.equals("Cert Uploader")) {
                addResultForTestCase("11751", TEST_CASE_PASSED_STATUS, "");
            }
        }
        if (addusercompanytype.isEnabled()) {
            System.out.println("Company Type field is Enabled");
        } else {
            System.out.println("Company Type field is Disabled");
            String comptyp = addusercompanytype.getAttribute("value");
            System.out.println("Company type dropdown value is:" + comptyp + " i.e., Broker");
            if (comptyp.equals("2") && role.equals("Super Admin")) {
                addResultForTestCase("10026", TEST_CASE_PASSED_STATUS, "");
            }
        }
        // addusercompanyname.sendKeys(companyname);
        Thread.sleep(2000);
        addemailaddress.sendKeys(emailaddress);
        Thread.sleep(2000);
        adduserpwd.sendKeys(pwd);
    }

    public void addUser_close() throws Exception {
        if (adduserpopupclosebtn.isDisplayed()) {
            adduserpopupclosebtn.click();
            addResultForTestCase("1582", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1582", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void Savebtn() {
        addusersavebtn.click();
    }

    public void Xbtn() {
        xmark.click();
    }

    public void searchmanageuser(String name, String account, String companyname, String companyid, String role, String lastloginfrom, String lastloginto) throws Exception {
        mng_gridname.sendKeys(name);
        mng_gridaccount.clear();
        mng_gridaccount.sendKeys(account);
        mng_gridcompanyname.sendKeys(companyname);
        mng_gridcompanyid.sendKeys(companyid);
        mng_gridrole.sendKeys(role);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", mng_gridlastloginfrom, lastloginfrom);// Enables the from date box
        Thread.sleep(5000);
        mng_gridlastloginfrom.sendKeys(lastloginfrom);
        Actions actions = new Actions(driver);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", mng_gridlastloginto, lastloginto);// Enables the from date box
        Thread.sleep(5000);
        mng_gridlastloginto.sendKeys(lastloginto);
        actions.click().perform();
        String expaccount = driver.findElement(By.xpath("//*[@id=\"manage-users-list\"]/tbody/tr[1]/td[2]")).getText();
        if (expaccount.contentEquals(account)) {
          //  addResultForTestCase("1626", TEST_CASE_PASSED_STATUS, "");//1626,105
        } else {
          //  addResultForTestCase("1626", TEST_CASE_FAILED_STATUS, "");//1626,105
        }
    }

    public void editUser(String name, String role, String emailaddress, String pwd) throws Throwable {
        mng_editsymbol.click();
        Thread.sleep(3000);
        //  addname.clear();
        addname.sendKeys(name);
        adduserrole.sendKeys(role);
        Thread.sleep(3000);
        //   adduserpwd.clear();
        //   adduserpwd.sendKeys(pwd);
        //   addemailaddress.clear();
        //   addemailaddress.sendKeys(emailaddress);
        Thread.sleep(3000);
    }

    public void edit_close() throws Exception {
        if (adduserpopupclosebtn.isDisplayed()) {
            adduserpopupclosebtn.click();
            addResultForTestCase("867", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("867", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void pwdconfirmation(String PWDConfirmation) throws Exception {
        confirmuserpwd.sendKeys(PWDConfirmation);
        continuebtn.click();
        Thread.sleep(4000);
        if (driver.getCurrentUrl().contains("https://certvault.dev.patracloud.com/users/manageUsers")) {
            addResultForTestCase("869", TEST_CASE_PASSED_STATUS, "");
            System.out.println("user add/update done");
        } else {
            addResultForTestCase("869", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void invalidpwdconfirmation(String PWDConfirmation) throws Exception {
        //  confirmuserpwd.click();
        confirmuserpwd.sendKeys(PWDConfirmation);
        continuebtn.click();
        Thread.sleep(4000);
        if (pwdconfirm_errortext.isDisplayed()) {
            System.out.println(pwdconfirm_errortext.getText());
            Thread.sleep(4000);
            pwdconfirm_Xbtn.click();
            Thread.sleep(4000);
            addResultForTestCase("870", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("870", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//table[@id='manage-users-list']/tbody/tr/td[7]/a[1]/span[1]")
    //user page - Reset Pwd btn
    public WebElement edituser_resetbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"NewEncryptedPassword\"]")  //new pwd
    public WebElement edituser_newpwd;
    @FindBy(how = How.XPATH, using = "//*[@id=\"NewEncryptedPassword1\"]")  //confirm pwd
    public WebElement edituser_confirmpwd;
    @FindBy(how = How.XPATH, using = "//*[@id=\"resetpass-btn\"]")  //save btn
    public WebElement edituser_resetSavebtn;

    public void edituser_resetpwd(String NewPwd, String ConfirmPwd) throws InterruptedException {
        edituser_resetbtn.click();
        Thread.sleep(2000);
        edituser_newpwd.sendKeys(NewPwd);
        edituser_confirmpwd.sendKeys(ConfirmPwd);
        Thread.sleep(2000);
        edituser_resetSavebtn.click();
    }

    @FindBy(how = How.ID, using = "passwordHelpError")  //Pwd Confirmation validation msg
    public WebElement pwdconfirm_errortext;
    @FindBy(how = How.XPATH, using = "//*[@id=\"adminUserPassword\"]/a")
    public WebElement pwdconfirm_Xbtn;

    public void edituserclick() throws Throwable {
        if (mng_editsymbol.isDisplayed()) {
            mng_editsymbol.click();
            Thread.sleep(3000);
            editusersavebtn.click();
            addResultForTestCase("127", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("127", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void disable() throws Exception {
        if (user_enabledisablebtn.isDisplayed()) {
            user_enabledisablebtn.click();
            Thread.sleep(3000);
            if (enabledisable_text.getText().contains("Are you sure you want to disable the user?") == true) {
                if (enable_disable_alertokbtn.isDisplayed()) {
                    enable_disable_alertokbtn.click();
                    addResultForTestCase("865", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("865", TEST_CASE_FAILED_STATUS, "");
                }
            } else if (enabledisable_text.getText().contains("Are you sure you want to enable the user?")) {
                if (enable_disable_alertokbtn.isDisplayed()) {
                    enable_disable_alertokbtn.click();
                    addResultForTestCase("866", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("866", TEST_CASE_FAILED_STATUS, "");
                }
            }
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"EnableDisableNotification\"]")
    public WebElement EnableDisableNotificationbtn;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section/div/div/div/div/div[4]/div[2]/label/span")
    //Disable button - //*[@id="AddUpdateUserModal"]/div/div/div[2]/div/table/tbody/tr/td[2]/button[2]
    public WebElement notify_disablebtn;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section/div/div/div/div/div[4]/div[2]/label/span")
    //Enable button - //*[@id="AddUpdateUserModal"]/div/div/div[2]/div/table/tbody/tr/td[2]/button[1]
    public WebElement notify_enablebtn;

    public void enabledisablenotification() {
        EnableDisableNotificationbtn.click();
    }

    public void notification_btn() throws Exception {
        if (notify_disablebtn.isDisplayed() || notify_enablebtn.isDisplayed()) {
            try {
                notify_disablebtn.click();
                addResultForTestCase("2116", TEST_CASE_PASSED_STATUS, "");
            } catch (Exception e) {
                notify_enablebtn.click();
                addResultForTestCase("2116", TEST_CASE_PASSED_STATUS, "");
            }
        } else {
            addResultForTestCase("2116", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
       Initializing elements - Support tab
    */
    @FindBy(how = How.ID, using = "navbarDropdownMenuLink")  //Admin menu
    public WebElement linkAdmin;
    @FindBy(how = How.XPATH, using = "//a[contains(@class,'dropdown-item nav-link')][contains(text(),'Support')]")
    //Support tab
    public WebElement linkSupport;
    @FindBy(how = How.ID, using = "Name")   //Support Name
    public WebElement supportname;
    @FindBy(how = How.ID, using = "Account")  //Account
    public WebElement supportAccount;
    @FindBy(how = How.ID, using = "AccessCode")  //Access code
    public WebElement supportAccesscode;
    @FindBy(how = How.ID, using = "Type")  //Type
    public WebElement supportType;
    @FindBy(how = How.ID, using = "Company")  //Company
    public WebElement supportCompany;
    @FindBy(how = How.ID, using = "CompanyID")  //Company id
    public WebElement supportCompanyId;
    @FindBy(how = How.ID, using = "Added By")  //Added by
    public WebElement supportAddedBy;
    @FindBy(how = How.ID, using = "AddedOnfrom")
    public WebElement supportAdddateFrom;
    @FindBy(how = How.XPATH, using = "//input[@id='AddedOnto']")
    public WebElement supportAdddateTo;
    @FindBy(how = How.ID, using = "ExpOnfrom")
    public WebElement supportExpdateFrom;
    @FindBy(how = How.ID, using = "ExpOnto")
    public WebElement supportExpdateTo;
    @FindBy(how = How.ID, using = "Registered")
    public WebElement supportRegistered;
    @FindBy(how = How.XPATH, using = "//*[contains(@title, 'Paper Registration')]")
    // @FindBy(how = How.XPATH, using =  //Paperreg icon - //*[@id="manage-users-list"]/tbody/tr/td[1]/div/a[2]
    public WebElement suppaperregicon;
    @FindBy(how = How.XPATH, using = "//*[contains(@title, 'Extend Expiration Date')]")
    public WebElement expirationextendicon;
    @FindBy(how = How.XPATH, using = "//div[1]/div[1]/table[1]/tbody[1]/tr/td[9]") // verify expiration date
    public WebElement verify_expiration_date;
    @FindBy(how = How.XPATH, using = "//*[contains(@title, 'Email Registration')]")
    //Email reg icon //*[@id="manage-users-list"]/tbody/tr[1]/td[1]/div/a[3]
    public WebElement supemailregicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-users-list\"]/tbody/tr/td[11]/a/i")
    //View Certs btn - //*[@id="manage-users-list"]/tbody/tr[1]/td[1]/div/a
    public WebElement supviewcertsbtn;
    //   @FindBy(how = How.XPATH, using = "//html/body/div[2]/section/div/div[1]/div[3]/button[1]/span/a")  //Paper Registration btn - //*[@id="certView"]/tbody/tr[1]/td/a[3]
    @FindBy(how = How.XPATH, using = "//*[@id=\"Paper\"]")  //html/body/div/section/div/div[2]/div[3]/button[1]/a
    public WebElement suppaperregisbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Emailr\"]")
    //Email Registration btn - //*[@id="certView"]/tbody/tr[1]/td/a[2]
    public WebElement supemailregisbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Emailc\"]")  //Email Cert -//*[@id="certView"]/tbody/tr[1]/td/a[1]
    public WebElement supemailcertbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list-support\"]/tbody/tr/td[1]/a")  //support view cert
    public WebElement supviewcertid;

    public void support() throws Throwable {
        linkSupport.click();
    }

    public void supportsearchgrid(String name, String account, String accesscode, String type, String company, String companyid, String addedby, String addedondatefrom, String addedondateto, String expirationdatefrom, String expirationdateto, String registered) throws Exception {
        supportname.sendKeys(name);
        supportAccount.sendKeys(account);
        supportAccesscode.sendKeys(accesscode);
        supportType.sendKeys(type);
        supportCompany.sendKeys(company);
        supportCompanyId.sendKeys(companyid);
        supportAddedBy.sendKeys(addedby);
        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", supportAdddateFrom, addedondatefrom);
        Thread.sleep(3000);
        supportAdddateFrom.sendKeys(addedondatefrom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", supportAdddateTo, addedondateto);
        Thread.sleep(3000);
        supportAdddateTo.sendKeys(addedondateto);
        if (expirationdatefrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", supportExpdateFrom, expirationdatefrom);
            Thread.sleep(3000);
            supportExpdateFrom.sendKeys(expirationdatefrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (expirationdateto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", supportExpdateTo, expirationdateto);
            Thread.sleep(3000);
            supportExpdateTo.sendKeys(expirationdateto);
            actions.moveToElement(datepicker).clickAndHold(supportExpdateTo).perform();
            Thread.sleep(3000);
        }
        actions.clickAndHold(supportRegistered).sendKeys(registered).perform();
        String expectedaccount = driver.findElement(By.xpath("//*[@id=\"manage-users-list\"]/tbody/tr/td[3]")).getText();
        if (expectedaccount.contentEquals(account)) {
          //  addResultForTestCase("1627", TEST_CASE_PASSED_STATUS, "");
        } else {
          //  addResultForTestCase("1627", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/tbody/tr/td")
    public WebElement supp_nodata;

    public void supportviewcert() throws Exception {
        if (supviewcertsbtn.isDisplayed()) {
            Thread.sleep(6000);
            supviewcertsbtn.click();
            addResultForTestCase("605", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("605", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void supportemailreg() throws Exception {
        //supemailregisbtn.click();
        supemailregicon.click();
        System.out.println("Action column : clicks email registration icon");
        Thread.sleep(3000);
        entercertemail.clear();
        entercertemail.sendKeys("e.anusha@patracorp.com");
        Thread.sleep(3000);
        if (popupsavebtn.isDisplayed()) {
            popupsavebtn.click();
            addResultForTestCase("1629", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1629", TEST_CASE_FAILED_STATUS, "");
        }
//        String emailregpopup = "EMAIL REGISTRATION";
//        if (driver.getPageSource().contains(emailregpopup)) {
//            Emailpopupclosebtn.click();
//        }
    }

    public void supportpaperregisbtn() throws Exception {
        //suppaperregisbtn.click();
        suppaperregicon.click();
        System.out.println("Action column : clicks paper registration icon");
        Thread.sleep(3000);
        if (alertsave.isDisplayed()) {
            alertsave.click();
            addResultForTestCase("1628", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1628", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void extendexpiration() throws Exception {
        if (verify_expiration_date.isDisplayed()) {
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String oldexpirationdate = verify_expiration_date.getText();
            String currentdate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
            String newexpirationdate = verify_expiration_date.getText();
            out.println("Verify expiration date before clicking on Extent Expiration date: " + oldexpirationdate);
            if (expirationextendicon.isDisplayed()) {
                System.out.println("Action column : clicks Extend Expiration Date icon");
                Thread.sleep(3000);
                out.println("current date is :" + currentdate);
                Calendar c = Calendar.getInstance();  // convert date to calendar
                c.add(Calendar.DATE, 90);
                Date currentdateplus90 = c.getTime();
                out.println("90 days from current date is:" + dateFormat.format(currentdateplus90));
                Thread.sleep(4000);
                expirationextendicon.click();
                out.println("Verify expiration date after clicking on Extent Expiration date: " + newexpirationdate);
                if (dateFormat.format(currentdateplus90).contentEquals(newexpirationdate)) {
                    addResultForTestCase("6702", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("6702", TEST_CASE_FAILED_STATUS, "");
                }
            }
        }
    }

    public void support_searchcert(String certid, String insured, String holder, String issuer, String expirationfrom, String expirationto, String issuedfrom, String issuedto, String uploaddatefrom, String uploaddateto) throws Exception {
        Certid.sendKeys(certid);
        Certinsured.sendKeys(insured);
        Certholder.sendKeys(holder);
        Certissuer.sendKeys(issuer);
        Actions actions = new Actions(driver);
        if (expirationfrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certexpirationfrom, expirationfrom);// Enables the from date box
            Thread.sleep(3000);
            Certexpirationfrom.sendKeys(expirationfrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (expirationto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certexpirationto, expirationto);// Enables the from date box
            Thread.sleep(3000);
            Certexpirationto.sendKeys(expirationto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (issuedfrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedfrom, issuedfrom);// Enables the from date box
            Thread.sleep(3000);
            Certissuedfrom.sendKeys(issuedfrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (issuedto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedto, issuedto);// Enables the from date box
            Thread.sleep(3000);
            Certissuedto.sendKeys(issuedto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (uploaddatefrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddatefrom, uploaddatefrom);// Enables the from date box
            Thread.sleep(3000);
            Certuploaddatefrom.sendKeys(uploaddatefrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (uploaddateto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddateto, uploaddateto);// Enables the from date box
            Thread.sleep(3000);
            Certuploaddateto.sendKeys(uploaddateto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"certs-list-support\"]/tbody/tr[1]/td[3]"), Pattern.compile(holder)));

        String actualholder = driver.findElement(By.xpath("//*[@id=\"certs-list-support\"]/tbody/tr/td[3]")).getText();
        if (actualholder.contentEquals(holder)) {
            addResultForTestCase("588", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("588", TEST_CASE_FAILED_STATUS, "");
        }
        supviewcertid.click();
        Thread.sleep(4000);
    }

    // ------ Support view cert buttons ------
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewPaperRegistration\"]/div[3]/div/div[2]/label")
    //Insured - //*[@id="updateSupportPaperRegistraiton"]/div/div[2]/div/div/div/label[1]/input
    public WebElement PRLinsured;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewPaperRegistration\"]/div[3]/div/div[1]/label")
    //Holder - //*[@id="updateSupportPaperRegistraiton"]/div/div[2]/div/div/div/label[2]/input
    public WebElement PRLholder;
    @FindBy(how = How.XPATH, using = "//*[@id=\"updateCertsPaperRegistration\"]")  //Save PRL
    public WebElement supportsavebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"UserRegisterEmail\"]")  //ERL email
    public WebElement supinsuredemail;
    //  @FindBy(how = How.XPATH, using = "//*[@id=\"submitRegisterEmail(event)\"]/div/div[3]/button[2]")  //ERL save btn
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitRegisterEmail\"]")
    public WebElement supportERLsavebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitRegisterEmail(event)\"]/div/div[3]/button[1]")  //ERL close btn
    public WebElement supportERLclosebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewEmailRegistration\"]/a") //ERL x btn
    public WebElement supportERL_Xbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"UserEmail\"]") //email cert- email address
    public WebElement supportEC_email;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitMail\"]") //EC save btn
    public WebElement supportEC_savebtn;

    public void supportPRLbtn() throws InterruptedException {
        suppaperregisbtn.click();
        Thread.sleep(4000);
        try {
            PRLinsured.click();
        } catch (Exception e) {
            PRLholder.click();
        }
        Thread.sleep(2000);
        supportsavebtn.click();
    }

    public void supportERLbtn() throws InterruptedException {
        supemailregisbtn.click();
        Thread.sleep(4000);
        supinsuredemail.clear();
        Thread.sleep(4000);
        supinsuredemail.sendKeys("ERL@patracorp.com");
        Thread.sleep(4000);
        supportERLsavebtn.click();
        //  supportERLclosebtn.click();
        supportERL_Xbtn.click();
    }

    public void supportECbtn() throws InterruptedException {
        supemailcertbtn.click();
        Thread.sleep(5000);
        System.out.println("clicks email cert button");
        Thread.sleep(5000);
        supportEC_email.sendKeys("e.anusha@patracorp.com");
        Thread.sleep(5000);
        supportEC_savebtn.click();
    }

    /*
        Initializing elements - Exception Handling tab
     */
    @FindBy(how = How.XPATH, using = "//a[contains(@class,'dropdown-item nav-link')][contains(text(),'Exception')]")
    //Exception Handling tab
    public WebElement linkExceptionHandling;
    @FindBy(how = How.ID, using = "Cert ID")  //search certid
    public WebElement excpcertid;
    @FindBy(how = How.ID, using = "Error Type")  //search error type
    public WebElement excperrortype;
    @FindBy(how = How.ID, using = "Insured")  //search insured
    public WebElement excpinsured;
    @FindBy(how = How.ID, using = "Holder")  //search Holder
    public WebElement excpholder;
    @FindBy(how = How.ID, using = "Issuer")  //search Issuer
    public WebElement excpissuer;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/tbody/tr/td[1]/a")  //View Certs btn in Exception
    public WebElement excpviewcertbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Edit\"]")  //Edit
    public WebElement excpEdit;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/h6/a[2]") //Cancel - //*[@id="Cancel"]
    public WebElement excpCancel;
    @FindBy(how = How.ID, using = "Policy1Number")   //Policy 1 Number
    public WebElement editpolicy1;
    @FindBy(how = How.XPATH, using = "//*[@id=\"items\"]/tr[7]/td[2]/div[1]/button[1]")  //Add insurer
    public WebElement addinsurerbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"carrierCompanies\"]/div/input")
    public WebElement carrier1company;
    @FindBy(how = How.XPATH, using = "//*[@id=\"items\"]/tr[7]/td[2]/div[1]/button[2]")
    public WebElement savecarrier;
    @FindBy(how = How.ID, using = "Carrier1")   //Policy 1 Carrier
    public WebElement editpolicycarrier;
    @FindBy(how = How.ID, using = "EffectiveDate1")     // Policy 1 Effective Date
    public WebElement editpolicy1effdate;
    @FindBy(how = How.ID, using = "ExpDate1")     // Policy 1 Exp Date
    public WebElement editpolicy1expdate;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Edit\"]")  //Save
    public WebElement excpSave;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[2]/div/div[3]/div/button")
    //Delete btn - /html/body/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div[2]/a
    public WebElement deletebtn;

    @FindBy(how = How.ID, using = "Dlt")  //Delete btn in certs view
    public WebElement delete_certbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewDeleteCert\"]/div[3]/div[2]/button")   //Delete alert Ok btn
    public WebElement deletealert_okbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certViewDeleteCert\"]/div[3]/div[1]/button")  //Delete alert Cancel btn
    public WebElement deletealert_cancelbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Dlt\"]") //Delete alert ok in exception
    public WebElement deletealert_okbtn_exception;

    @FindBy(how = How.XPATH, using = "//html/body/div[2]/section[2]/div/div[1]/div/button")
    //Add Policy Info button //*[@class='col-sm-10 KPI-info']//div[contains(@class, 'PolicyMaginstyl')]
    public WebElement addpolicyinfobtn;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/h6/a")  //Clear All Exception
    public WebElement clearbtn_excp;

    public void excceptionHandling() {
        linkExceptionHandling.click();
    }

    public void exceptionsearchgrid(String certid, String errortype, String insured, String holder, String issuer, String issuefrom, String issueto, String uploaddatefrom, String uploaddateto) throws Exception {
        excpcertid.sendKeys(certid);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedfrom, issuefrom);// Enables the from date box
        Thread.sleep(3000);
        Certissuedfrom.sendKeys(issuefrom);
        Actions actions = new Actions(driver);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedto, issueto);// Enables the from date box
        Thread.sleep(3000);
        Certissuedto.sendKeys(issueto);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddatefrom, uploaddatefrom);// Enables the from date box
        Thread.sleep(3000);
        Certuploaddatefrom.sendKeys(uploaddatefrom);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddateto, uploaddateto);// Enables the from date box
        Thread.sleep(3000);
        Certuploaddateto.sendKeys(uploaddateto);
        actions.click().perform();
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"certs-list\"]/tbody/tr/td[1]"), Pattern.compile(certid)));

        String expcert = driver.findElement(By.xpath("//*[@id=\"certs-list\"]/tbody/tr/td[2]")).getText();
        if (expcert.matches(certid) || expcert.matches(errortype)) {
            addResultForTestCase("587", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("587", TEST_CASE_FAILED_STATUS, "");
        }
        //  driver.findElement(By.xpath("//*[@id=\"certs-list\"]/tbody/tr/td[1]")).click();
      /*  excperrortype.sendKeys(errortype);
        excpinsured.sendKeys(insured);
        excpholder.sendKeys(holder);
        excpissuer.sendKeys(issuer);*/
    }

    public void excpviewcert() throws Exception {
        if (excpviewcertbtn.isDisplayed()) {
            excpviewcertbtn.click();
            addResultForTestCase("585", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("585", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void editexception(String policy1, String policycarrier, String policy1effdate, String policyexpdate) throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        excpEdit.click();
        editpolicy1.sendKeys(policy1);
        Thread.sleep(3000);
        if (addinsurerbtn.isDisplayed()) {
            addinsurerbtn.click();
            carrier1company.sendKeys("test Insurer A");
            savecarrier.click();
            addResultForTestCase("7418", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("7418", TEST_CASE_FAILED_STATUS, "");
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 150)");
        editpolicycarrier.click();
        editpolicycarrier.sendKeys(policycarrier);
        editpolicy1effdate.sendKeys(policy1effdate);
        editpolicy1expdate.click();
        editpolicy1expdate.sendKeys(policyexpdate);
        editpolicy1.click();
        editpolicy1expdate.click();
    }

    public void save() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        if (excpEdit.isDisplayed()) {
            excpEdit.click();
            addResultForTestCase("1618", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("1618", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void delete() throws Exception {
        deletebtn.click();
        Thread.sleep(3000);
        if (deletealert_okbtn_exception.isDisplayed() && deletealert_okbtn_exception.isEnabled()) {
            deletealert_okbtn_exception.click();
            addResultForTestCase("1617", TEST_CASE_PASSED_STATUS, ""); //1627 cert delete
        } else {
            addResultForTestCase("1617", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void addPolicyInfo() throws Throwable {
        if (addpolicyinfobtn.isDisplayed()) {
            addpolicyinfobtn.click();
            Thread.sleep(5000);
            addResultForTestCase("845", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("845", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void cancel() throws Exception {
        if (excpCancel.isDisplayed()) {
            excpCancel.click();
            addResultForTestCase("848", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("848", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
       Initializing elements - Sorting Exception Handling columns
   */
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[2]")
    //Cert ID: activate to sort column ascending
    public WebElement certid_asc;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[3]")
    //Error Type: activate to sort column descending
    public WebElement errortype_asc;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[4]")
    //Insured: activate to sort column descending
    public WebElement insured_asc;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/thead/tr/th[5]")
    //Holder: activate to sort column descending
    public WebElement holder_asc;

    public void sort_Exception() throws Exception {
        certid_asc.click();
        Thread.sleep(3000);
        certid_asc.click();
        if (certid_asc.isEnabled()) {
            test.testgoogle.addResultForTestCase("1616", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("1616", TEST_CASE_PASSED_STATUS, "");
        }
        Thread.sleep(3000);
        errortype_asc.click();
        Thread.sleep(3000);
        errortype_asc.click();
        Thread.sleep(3000);
        insured_asc.click();
        Thread.sleep(3000);
        insured_asc.click();
        Thread.sleep(3000);
        holder_asc.click();
        Thread.sleep(3000);
        holder_asc.click();
    }

    /*
       Initializing elements - Upload Cert
   */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//a[contains(text(),'Upload Cert')]")  //Upload Cert tab
    public WebElement linkUploadcert;
    @FindBy(how = How.ID, using = "insuredEmail")  //Insured Email textbox
    public WebElement txtInsuredemail;
    @FindBy(how = How.ID, using = "holderEmail")  //Holder Email textbox
    public WebElement txtholderemail;
    @FindBy(how = How.ID, using = "pdf-file")  //Choose File
    public WebElement choosefile;
    @FindBy(how = How.ID, using = "upload-btn")  //Upload File button
    public WebElement btnUploadFile;
    //    @FindBy(how = How.CSS, using = "#AddUpdateUserForm > div > div.modal-body.text-center > button > span")  //pop-up Ok button  body > div.swal-overlay.swal-overlay--show-modal > div > div.swal-footer > div > button
    @FindBy(how = How.CSS, using = "#uploadSuccess")  //Certificate uploaded successfully.
    public WebElement popbtnOK;
    @FindBy(how = How.ID, using = "RenewalPDF")  //Renewal PDF checkbox
    public WebElement chckRenewalPDF;
    @FindBy(how = How.LINK_TEXT, using = "Current Version")   //Current Version
    public WebElement currentversionbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"revision-tbl\"]/tbody/tr[1]/td[7]/div/a")
    //View button in Certificate list
    public WebElement view_certlistbtn;

    public void currentVersion() throws Exception {
        if (currentversionbtn.isDisplayed()) {
            currentversionbtn.click();
            addResultForTestCase("608", TEST_CASE_PASSED_STATUS, ""); //1744
        } else {
            addResultForTestCase("608", TEST_CASE_FAILED_STATUS, ""); //1744
        }
    }

    public void view_Certlist() {
        view_certlistbtn.click();
    }

    public void upload() {
        linkUploadcert.click();
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div/div[1]")  //Error while uploading / processing cert
    public WebElement errormsg;
    //  @FindBy(how = How.CSS, using = "body > div.swal-overlay.swal-overlay--show-modal > div > div.swal-footer > div > button")  //error pop-up Ok btn
    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div/div[4]/div/button")
    public WebElement errorpopupok;
    //   @FindBy(how = How.CSS, using = "body > div.swal-overlay.swal-overlay--show-modal > div > div.swal-footer > div > button")  //oops ok btn
    @FindBy(how = How.XPATH, using = "//*[@id=\"em\"]/div[3]/div/button")
    public WebElement oopsOK;
    @FindBy(how = How.XPATH, using = "/html/body/div[8]/div/div[4]/div/button")
    ///html/body/div[3]/div/div[4]/div/button
    public WebElement invalid_popupOkbtn;

    public void uploadFile() {
        btnUploadFile.click();
    }

    public void warning_OKbtn() throws Exception {
        if (invalid_popupOkbtn.isDisplayed()) {
            invalid_popupOkbtn.click();
            addResultForTestCase("607", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("607", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void failpopup() {
        try {
            if (popbtnOK.isDisplayed()) {
                popbtnOK.click();
                addResultForTestCase("97", TEST_CASE_PASSED_STATUS, "");
            } else {
                oopsOK.click();
                Thread.sleep(5000);
                addResultForTestCase("613", TEST_CASE_PASSED_STATUS, "");
                popbtnOK.click();
            }
            //  errorpopupok.click();
        } catch (Exception e) {
            System.out.println("In Catch");
        }
    }

    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div/div[4]/div/button")
    public WebElement error_okbtn;
    @FindBy(how = How.ID, using = "errorMessage")
    public WebElement verify_exp_error;

    public void Error() {
        error_okbtn.click();
    }

    public void PopupOK() throws Exception {
//        try {
        if (oopsOK.isDisplayed()) {
            Thread.sleep(3000);
            System.out.println(verify_exp_error.getText());
            oopsOK.click();
            addResultForTestCase("613", TEST_CASE_PASSED_STATUS, "");
            addResultForTestCase("12673", TEST_CASE_PASSED_STATUS, "");
            Thread.sleep(3000);
            popbtnOK.click();
        } else if (popbtnOK.isDisplayed()) {
            popbtnOK.click();
            Thread.sleep(3000);
            System.out.println("Cert uploaded successfully - clicks success pop-up OK btn");
        }
        //  errorpopupok.click();
//        } catch (Exception e) {
//            popbtnOK.click();
//            System.out.println("In catch ");
//        }
    }

    @FindBy(how = How.ID, using = "groupSelect")  //Company Group upload cert
    public WebElement drpcompanygrp;

    public void companygrp(String companygrp) throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
        drpcompanygrp.click();
        drpcompanygrp.sendKeys(companygrp);
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"toggleUpCr\"]")  //Revision btn
    public WebElement revisionbtn;
    @FindBy(how = How.ID, using = "Uploaded By")
    public WebElement CLuploadedby;
    @FindBy(how = How.ID, using = "Insured")
    public WebElement CLInsured;
    @FindBy(how = How.ID, using = "Holder")
    public WebElement CLHolder;
    @FindBy(how = How.XPATH, using = "//*[@id=\"revision-tbl\"]/tbody/tr[1]/td[1]/div/label/span")
    public WebElement reviseradiobtn;
    @FindBy(how = How.ID, using = "UploadDatefrom")
    public WebElement CLuploaddatefrom;
    @FindBy(how = How.ID, using = "UploadDateto")
    public WebElement CLuploaddateto;

    public void revision() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100)");
        revisionbtn.click();
    }

    public void certlist(String uploadedby, String insured, String holder, String uploaddatefrom, String uploaddateto) {
        CLuploadedby.sendKeys(uploadedby);
        CLInsured.sendKeys(insured);
        CLHolder.sendKeys(holder);
        CLuploaddatefrom.sendKeys(uploaddatefrom);
        CLuploaddateto.sendKeys(uploaddateto);
        CLHolder.click();
    }

    public void uploadfields(String insuredemail, String holderemail) {
        txtInsuredemail.sendKeys(insuredemail);
        txtholderemail.sendKeys(holderemail);
        popupsavebtn.click();
    }

    public void revise() {
        reviseradiobtn.click();
    }

    /*
        Initializing elements - Companies tab
     */
    @FindBy(how = How.XPATH, using = "//a[contains(@class,'dropdown-item nav-link')][contains(text(),' Companies')]")
    //Companies tab  /html/body/div[2]/nav/ul/li[7]/div/a[3]
    public WebElement companiestab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"add-company\"]")
    //add company //*[@id="company-list_wrapper"]/div[2]/h6/button
    public WebElement addcompany;
    @FindBy(how = How.ID, using = "EditCompanyName")   //Company Name
    public WebElement txtcompanyname;
    @FindBy(how = How.ID, using = "EditCompanyTypeID")   //Company Type
    public WebElement drpcompanytype;
    @FindBy(how = How.ID, using = "patraOneCompanyID") //Patra One company
    public WebElement drppatraone;
    @FindBy(how = How.ID, using = "EditAddress")   //Address
    public WebElement txtaddress;
    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr/td[10]/a[1]/i")  //edit symbol
    public WebElement editsymbol;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submit-btn-company\"]")
    //add company Save btn //*[@id="AddUpdateCompanyForm"]/div[2]/div/button
    public WebElement addcompanySavebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"emc\"]/a")  //add company Close btn - //*[@id="AddUpdateCompanyForm"]/a
    public WebElement addcompanyClosebtn;
    @FindBy(how = How.ID, using = "cmp-id")   //Company ID
    public WebElement gridcompanyid;
    @FindBy(how = How.ID, using = "cmp-name")   //Company Name
    public WebElement gridcompanyname;
    @FindBy(how = How.ID, using = "cmp-type")   //Company Type
    public WebElement gridcompanytype;
    @FindBy(how = How.ID, using = "cmp-reg")   //Company registry
    public WebElement gridregistered;
    @FindBy(how = How.ID, using = "cmp-address")   //Company address
    public WebElement gridaddress;
    @FindBy(how = How.ID, using = "rootcmp-id")   //Root Company ID
    public WebElement gridrootid;
    @FindBy(how = How.ID, using = "rootcmp-name")   //Root Company name
    public WebElement gridrootname;
    @FindBy(how = How.ID, using = "rcomp-has-logo")   //has logo
    public WebElement gridhaslogo;
    @FindBy(how = How.XPATH, using = " //*[@id=\"company-list_wrapper\"]/div[3]/h6/a")   //Clear All Filters - Companies
    public WebElement clearbtn;
    @FindBy(how = How.XPATH, using = "//a[@id ='navbarDropdownMenuLink']") //*[@id="navbarDropdownMenuLink"]
    public WebElement admin_menu;
    @FindBy(how = How.XPATH, using = "//*[@id=\"logoInputDiv\"]/label")  //Edit company - logo bar
    public WebElement editcom_logo;
    //  @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div[4]/div/button")
    @FindBy(how = How.CSS, using = "body > div.swal-overlay.swal-overlay--show-modal > div > div.swal-footer > div > button")
    //logo warning
    public WebElement war_OKbtn;

    public void admin() {
        admin_menu.click();
    }

    public void companies() {
        companiestab.click();
    }

    public void addCompany() throws Exception {
        if (driver.findElement(By.id("add-company")).isEnabled()) {   //AddUpdateCompanyForm
            addcompany.click();
            addResultForTestCase("583", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("583", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void filladdcompany(String companyname, String companytype, String patraonecomp, String address) throws Exception {
        txtcompanyname.sendKeys(companyname);
        addcompanySavebtn.click();
        Thread.sleep(2000);
        if (drpcompanytype.isDisplayed()) {
            addResultForTestCase("856", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("856", TEST_CASE_FAILED_STATUS, "");
        }
        drpcompanytype.sendKeys(companytype);
        addcompanySavebtn.click();
        if(drppatraone.isDisplayed()) {
            drppatraone.sendKeys(patraonecomp);
        }
        Thread.sleep(2000);
        txtaddress.sendKeys(address);
        if (addcompanySavebtn.isEnabled()) {
            addcompanySavebtn.click();
            addResultForTestCase("855", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("855", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void close_addcompany() throws Exception {
      //  addcompany.click();
        Thread.sleep(2000);
        addcompanySavebtn.click();
        Thread.sleep(2000);
        if (addcompanyClosebtn.isDisplayed()) {
            addcompanyClosebtn.click();
            //  addResultForTestCase("854", TEST_CASE_PASSED_STATUS, "");
        } else {
            //  addResultForTestCase("854", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void searchcompany(String companyid, String companyname, String companytype, String registered, String address, String companyrootid, String companyrootname, String companyhaslogo) throws Exception {
        gridcompanyid.sendKeys(companyid);
        gridcompanyname.clear();
        gridcompanyname.sendKeys(companyname);
        gridcompanytype.sendKeys(companytype);
        gridregistered.sendKeys(registered);
        gridaddress.sendKeys(address);
        gridrootid.sendKeys(companyrootid);
        gridrootname.sendKeys(companyrootname);
        gridhaslogo.sendKeys(companyhaslogo);
        Thread.sleep(6000);
        String actualtext = driver.findElement(By.xpath("//*[@id=\"company-list\"]/tbody/tr[1]/td[3]")).getText();
        if (actualtext.contentEquals(companyname)) {
          //  addResultForTestCase("584", TEST_CASE_PASSED_STATUS, "");  //header grid search
        } else {
          //  addResultForTestCase("584", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void editcompany(String companyname, String companytype, String patraonecomp, String address) throws Exception {
        if (editsymbol.isDisplayed()) {
            editsymbol.click();
            addResultForTestCase("861", TEST_CASE_PASSED_STATUS, "");  //edit company
        } else {
            addResultForTestCase("861", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(4000);
        txtcompanyname.sendKeys(companyname);
        drpcompanytype.sendKeys(companytype);
        if (drppatraone.isDisplayed()) {
            drppatraone.click();
            drppatraone.sendKeys(patraonecomp);
            addResultForTestCase("12354", TEST_CASE_PASSED_STATUS, "");  //9817
            System.out.println("Entered patra one company value for Broker");
        } else {
            addResultForTestCase("12354", TEST_CASE_FAILED_STATUS, "");
        }
        txtaddress.sendKeys(address);
        Thread.sleep(2000);
        if (addcompanySavebtn.isEnabled()) {
            addcompanySavebtn.click();
            addResultForTestCase("859", TEST_CASE_PASSED_STATUS, "");  //edit save
        } else {
            addResultForTestCase("859", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void validate_Editcompany() throws Exception {
        editsymbol.click();
        Thread.sleep(2000);
        txtcompanyname.clear();
        addcompanySavebtn.click();
        if (addcompanySavebtn.isEnabled()) {
            addResultForTestCase("863", TEST_CASE_PASSED_STATUS, ""); //edit validations
        } else {
            addResultForTestCase("863", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(2000);
        if (addcompanyClosebtn.isEnabled()) {
            addcompanyClosebtn.click();
            //  addResultForTestCase("862", TEST_CASE_PASSED_STATUS, "");   //edit close
        } else {
            //  addResultForTestCase("862", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "merge-company")  //merge companies
    public WebElement mergecompanybtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"updateMergeCompanyForm\"]/div[2]/div/button")
    public WebElement savebtn;   //company merge list save btn
    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tfoot/tr/td[1]/div/label/span/span")
    public WebElement checkboxall;
    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr/td[1]/div/label/span/span")
    public WebElement checkbox;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[3]/div/a")   //X icon in dropdown
    public WebElement Xbtn_CM;
    @FindBy(how = How.ID, using = "mergeCompanyList")   //dropdown
    public WebElement sel_primcomp;

    public void selcheckbox() throws InterruptedException {
        //  checkboxall.click();
        checkbox.click();
        Thread.sleep(3000);
    }

    //  @FindBy(how = How.XPATH, using = "*//div[@class=\"notify-text\"]/p[text()=\"Companies Merged Successfully.\"]")
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/p")
    public WebElement successvalid_merge;
    @FindBy(how = How.XPATH, using = "//*[@id=\"merge-warning-messages-branchPopup\"]/div[4]/div[2]/button")
    //company merge warnong popup Ok //*[@id="ap"]/div[4]/div/button
    public WebElement cmwarn_okbtn;    //*[@id="merge-warning-messages-branchPopup"]/div[4]/div[2]/button
    @FindBy(how = How.CSS, using = "#ap > div:nth-child(4) > div.col-md-6.toogle-CV-Nthme > button")
    public WebElement cm_warnpop_cancelbtn;

    public void mergecompanies() throws Exception {
        mergecompanybtn.click();
        sel_primcomp.click();
        sel_primcomp.sendKeys("Peking Handicraft, Inc.");
        if (savebtn.isDisplayed()) {
            savebtn.click();
            Thread.sleep(3000);
            //  cm_warnpop_cancelbtn.click();
            cmwarn_okbtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(successvalid_merge));
            System.out.println(successvalid_merge.getText());
            addResultForTestCase("5629", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5629", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(5000);
    }

    public void cm_warningpopup() {
        cm_warnpop_cancelbtn.click();
    }

    @FindBy(how = How.XPATH, using = "//div[@class=\"notify-text\"]/p[text()=\"Please select at least 2 companies to merge.\"]")
    public WebElement errvalid_merge;

    public void validation_CM() throws Exception {
        if (mergecompanybtn.isDisplayed()) {
            mergecompanybtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.visibilityOf(errvalid_merge));
            addResultForTestCase("5630", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5630", TEST_CASE_FAILED_STATUS, "");
        }
    }

   /* public void closeX() throws Exception {
        mergecompanybtn.click();
        Thread.sleep(2000);
        if (Xbtn_CM.isDisplayed()) {
            Xbtn_CM.click();
              addResultForTestCase("5637", TEST_CASE_PASSED_STATUS, "");
        } else {
              addResultForTestCase("5637", TEST_CASE_FAILED_STATUS, "");
        }
    }*/

    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr[1]/td[10]/a[2]/i")  //add company grp icon
    public WebElement addcompgrp_icon;
    @FindBy(how = How.ID, using = "CompanyGroup")  //add value in company grp
    public WebElement enterCGvalue;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[4]/div/a")  //close X btn
    public WebElement Xbtn_addcompgrp;
    @FindBy(how = How.XPATH, using = "//*[@id=\"submitCgroup\"]")
    public WebElement add_btn;

    public void addcompanygrpicon() {
        addcompgrp_icon.click();
    }

    public void addcompgrp() throws Exception {
        enterCGvalue.sendKeys("branch");
        if (add_btn.isDisplayed()) {
            add_btn.click();
            addResultForTestCase("5210", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5210", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void close_addcompgrp() throws Exception {
        if (Xbtn_addcompgrp.isDisplayed()) {
            Xbtn_addcompgrp.click();
            addResultForTestCase("5219", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5219", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void validation_ACG() throws Exception {
        add_btn.click();
        if (driver.getPageSource().contains("Add Company Group")) {
            addResultForTestCase("5218", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5218", TEST_CASE_PASSED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr[1]/td[10]/a[3]/i")  //Child companies list icon
    public WebElement childcomp_icon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"childCompaniesListModal-close\"]") //Child companies list X mark
    public WebElement childcomp_Xmark;

    public void childcomplist_icon() {
        childcomp_icon.click();
    }

    public void childcomp_Xbtn() throws Exception {
        if (childcomp_Xmark.isDisplayed()) {
            childcomp_Xmark.click();
            addResultForTestCase("10797", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("10797", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr[1]/td[10]/a[4]/i")  //opt out icon
    public WebElement optout_icon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"brokerlistform\"]/div[2]/button")  //submit btn
    public WebElement optout_submitbtn;
    @FindBy(how = How.XPATH, using = "//div[@class=\"notify-text\"]/p[text()=\"Updated Opt Out options for selected broker companies.\"]")
    public WebElement successvalid_optout;
    @FindBy(how = How.XPATH, using = "//*[@id=\"insuredNotificationDiv\"]/div/label/span/span")
    //Disable insured notification
    public WebElement check_disableinsurednotifcation;
    @FindBy(how = How.XPATH, using = "//input[@id='BrokerCompanyEmailID']")
    public WebElement notificationfailuretxt;

    public void optouticon() {
        optout_icon.click();
    }

    public void sel_optout() throws Exception {
        Select broker = new Select(driver.findElement(By.id("brokerlistSelect")));
        broker.selectByVisibleText("USI Insurance Services");
        Thread.sleep(3000);
        if (optout_submitbtn.isEnabled()) {
            optout_submitbtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(successvalid_optout));
            addResultForTestCase("10548", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("10548", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void disableinsurednotification() throws Exception {
        editsymbol.click();
        Thread.sleep(2000);
        if (check_disableinsurednotifcation.isDisplayed()) {
            check_disableinsurednotifcation.click();
            addcompanySavebtn.click();
            addResultForTestCase("10547", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("10547", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void emailfailure() throws Exception {
        editsymbol.click();
        Thread.sleep(2000);
        if (notificationfailuretxt.isDisplayed()) {
            notificationfailuretxt.clear();
            notificationfailuretxt.sendKeys("sonali.gupta@jellyfishtechnologies.com");
            addcompanySavebtn.click();
            addResultForTestCase("11424", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11424", TEST_CASE_FAILED_STATUS, "");
        }
    }

    //Patra One Company list pop-up
    @FindBy(how = How.XPATH, using = "//*[@id=\"company-list\"]/tbody/tr[1]/td[10]/a[4]/i")
    public WebElement patraonepopup;
    @FindBy(how = How.ID, using = "patraOneCompanyList")  //company name
    public WebElement patronecompanyname;
    @FindBy(how = How.ID, using = "printShopMeterList")  //metre name
    public WebElement patronemetername;
    @FindBy(how = How.ID, using = "printShopServiceList")  //Service
    public WebElement patroneservice;
    @FindBy(how = How.ID, using = "printShopDivisionList")  //Division
    public WebElement patronedivision;
    @FindBy(how = How.ID, using = "printShopBranchList")  //Branch
    public WebElement patronebranch;
    @FindBy(how = How.ID, using = "printShopDepartmentList")  //Department
    public WebElement patronedept;
    @FindBy(how = How.ID, using = "FileNameCompany")
    public WebElement companyforfilenamedrp;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[12]/div/form/div[8]/div/button")
    public WebElement patraonecomp_savebtn;

    public void patraonecompanylisticon() {
        patraonepopup.click();
    }

    public void patraonepopup(String CompanyName, String MeterName, String Service, String Division, String Branch, String Department, String CompanyForFileName) throws Exception {
        Select drpcn = new Select(patronecompanyname);
        drpcn.selectByVisibleText(CompanyName);
        Select meter = new Select(patronemetername);
        meter.selectByVisibleText(MeterName);
        Select service = new Select(patroneservice);
        service.selectByVisibleText(Service);
        patronedivision.sendKeys(Division);
        patronebranch.sendKeys(Branch);
        patronedept.sendKeys(Department);
        companyforfilenamedrp.sendKeys(CompanyForFileName);
        Thread.sleep(2000);
        patraonecomp_savebtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(successvalid_merge));
        out.println(successvalid_merge.getText());
        testgoogle.addResultForTestCase("12588", TEST_CASE_PASSED_STATUS, "");
    }


    /*
      Initializing elements - User Profile section -My Profile icon & logout
    */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/div[3]/label/span")  //hi user
    public WebElement linkUserprofile;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/div[3]/label/ul/li[1]/a")  //my profile
    public WebElement linkMyprofile;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/div[3]/label/ul/li[2]/a")  //user guide
    public WebElement linkuserguide;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Logout')]") //Logout
    public WebElement btnlogout;
    //  @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div/div[2]/div/div[2]/div/div/button")  //Reset Password - /html/body/div[2]/div/div/div[2]/div/div[1]/div/div/div/button
    @FindBy(how = How.ID, using = "Reset")
    public WebElement resetpwd;
    @FindBy(how = How.ID, using = "EncryptedPassword")  //Old Password
    public WebElement oldpwd;
    @FindBy(how = How.ID, using = "NewEncryptedPassword")  //New Password
    public WebElement newpwd;
    @FindBy(how = How.ID, using = "NewEncryptedPassword1")  //Confirm Password
    public WebElement confirmpwd;
    @FindBy(how = How.XPATH, using = "//*[@id=\"resetPasswordForm\"]/a")
    //Close btn Reset Pwd - //*[@id='resetPasswordForm']/div[2]/button[1]
    public WebElement ResetClose;
    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    //Save btn Reset Pwd - popupsavebtn //*[@id='submit-btn']
    public WebElement popupsavebtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"EncryptedPasswordHelp\"]")  //error validation
    public WebElement valditionpwd;
    @FindBy(how = How.XPATH, using = "//span[@id='NewUserEncryptedPasswordHelp']")  //error validation
    public WebElement valpwd;
    @FindBy(how = How.XPATH, using = "//span[@id='NewEncryptedPassword1Help']")
    public WebElement incorrectpwd;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Password successfully changed.')]")
    public WebElement password_change_success;

    public void userProfile() throws Exception {
        if (linkUserprofile.isDisplayed()) {
            try {
                linkUserprofile.click();
                addResultForTestCase("836", TEST_CASE_PASSED_STATUS, "");
            } catch (Exception e) {
                Userprofiletab.click();
            }
        } else {
            addResultForTestCase("836", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void myprofile() throws Throwable {
       /* if (profileicon.isDisplayed()) {
            profileicon.click();
            Thread.sleep(3000);
        }*/
        if (linkMyprofile.isDisplayed()) {
            linkMyprofile.click();
        }
    }

    public void userGuide() throws Throwable {
        linkUserprofile.click();
        Thread.sleep(3000);
        if (linkuserguide.isDisplayed()) {
            linkuserguide.click();
            addResultForTestCase("10883", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("10883", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void resetpwd_btn() {
        resetpwd.click();
    }

    public void resetpwd(String oldpassword, String newpassword, String confirmpassword) throws Exception {
        oldpwd.sendKeys(oldpassword);
        Thread.sleep(2000);
        newpwd.sendKeys(newpassword);
        Thread.sleep(2000);
        confirmpwd.sendKeys(confirmpassword);
        Thread.sleep(2000);
        popupsavebtn.click();
        Thread.sleep(4000);
        String pwdreq = "Password requires at least 1 letter, 1 number, 1 special character(^$*.[]{}()?\"!@#%&/,><':;|_~`) and minimum of 8 characters";
        String validation_msg = valditionpwd.getText();
        String validation_txt = valpwd.getText();
        String match_not_found = incorrectpwd.getText();
        try {
            if (oldpassword.isEmpty()) {
                if (driver.getPageSource().contains(validation_msg)) {
                    System.out.println("validation text appeared is:" + validation_msg);
                    addResultForTestCase("842", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("842", TEST_CASE_FAILED_STATUS, "");
                }
            } else if (newpassword != confirmpassword) {
                if (validation_txt.isEmpty()) {
                    if (driver.getPageSource().contains(match_not_found)) {
                        System.out.println("validation text appeared is:" + match_not_found);
                        addResultForTestCase("841", TEST_CASE_PASSED_STATUS, "");
                    } else {
                        addResultForTestCase("841", TEST_CASE_FAILED_STATUS, "");
                    }
                } else if (match_not_found.isEmpty()) {
                    if (validation_txt.contains(pwdreq)) {
                        System.out.println("validation text appeared is:" + validation_txt);
                        addResultForTestCase("9912", TEST_CASE_PASSED_STATUS, "");
                    } else {
                        addResultForTestCase("9912", TEST_CASE_FAILED_STATUS, "");
                    }
                }
            } else if (newpassword == confirmpassword) {
                if (validation_txt.isEmpty() && validation_msg.isEmpty()) {
                    if (password_change_success.isDisplayed()) {
                        WebDriverWait wait = new WebDriverWait(driver, 30);
                        wait.until(ExpectedConditions.visibilityOf(password_change_success));
                        out.println(password_change_success.getText());
                        addResultForTestCase("837", TEST_CASE_PASSED_STATUS, "");
                        return;
                    } else {
                        addResultForTestCase("837", TEST_CASE_FAILED_STATUS, "");
                    }
                } else if (validation_txt.isEmpty()) {
                    if (driver.getPageSource().contains(validation_msg)) {
                        System.out.println("validation text appeared is:" + validation_msg);
                        addResultForTestCase("838", TEST_CASE_PASSED_STATUS, "");
                    } else {
                        addResultForTestCase("838", TEST_CASE_FAILED_STATUS, "");
                    }
                } else if (validation_msg.isEmpty()) {
                    if (validation_txt.contains(pwdreq)) {
                        System.out.println("validation text appeared is:" + validation_txt);
                        addResultForTestCase("9912", TEST_CASE_PASSED_STATUS, "");
                    } else if (driver.getPageSource().contains(validation_txt)) {
                        System.out.println("validation text appeared is:" + validation_txt);
                        addResultForTestCase("12228", TEST_CASE_PASSED_STATUS, "");
                    } else {
                        addResultForTestCase("12228", TEST_CASE_FAILED_STATUS, "");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No validation messages are displayed for reset password");
        }
        Thread.sleep(3000);
        if (ResetClose.isDisplayed()) {
            ResetClose.click();
            addResultForTestCase("840", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("840", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void profileIcon() throws Exception {
        if (linkUserprofile.isDisplayed()) {
            linkUserprofile.click();
            Thread.sleep(3000);
        }
        if (btnlogout.isDisplayed()) {
            btnlogout.click();
            String expectedurl = "https://certvault.dev.patracloud.com/logout";
            if (driver.getCurrentUrl().contentEquals(expectedurl)) {
                addResultForTestCase("582", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("582", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    /*
       Initialize elements for Patra Documents Role
    */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/div[3]/label/ul/li[1]/a")  //My profile
    public WebElement Userprofiletab;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div/div[2]/div[1]/div[1]/a/i")
    public WebElement generatepaperreg;

    /*
        Initialize elements for Register & Forgot password? link
    */
    @FindBy(how = How.XPATH, using = "/html/body/section[1]/div/div/div[2]/div/p/a[1]")   //Register -register_button
    public WebElement registerbtn;
    @FindBy(how = How.ID, using = "email") //Access code - //*[@id="form-accesscode-signin"]/div[2]/input[1]
    public WebElement accesscode;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-accesscode-signin\"]/span/button") //View my certs! btn
    public WebElement viewmycerts_btn;
    @FindBy(how = How.XPATH, using = "/html/body/section[1]/div/div/div[2]/div/p/a[2]")  //Forgot password
    public WebElement forgotpwdlink;
    @FindBy(how = How.NAME, using = "email")  //forgotPasswordEmail
    public WebElement forgotpwdemail;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-forgotpassword\"]/span/button")  //Reset My Password
    public WebElement forgot_submitbtn;
    @FindBy(how = How.ID, using = "impl_workOrder_cancel")
    public WebElement forgot_cancelbtn;
    @FindBy(how = How.XPATH, using = "/html/body/section[1]/div/div/div[2]/div")
    public WebElement emailerr_validation;

    public void register() {
        registerbtn.click();
    }

    public void accessCode(String AccessCode) {
        accesscode.sendKeys(AccessCode);
        //  base.driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]")).getAttribute("text");
    }

    public void viewmyCerts() throws Exception {
        viewmycerts_btn.click();
        String access_validation = "An access code is required to register with CertVault, if you do not have one and are interested in using CertVault please contact your Broker or send an email to support@certvault.org";
        if (driver.getPageSource().contains(access_validation)) {
            //  addResultForTestCase("1613", TEST_CASE_PASSED_STATUS, "");
            System.out.println(access_validation);
        }

        String access_invalid = "The Access Code you entered could not be found, please be sure that it was entered correctly, if you continue to experience issues with registration please contact our help desk at helpdesk@certvault.org";
        if (driver.getPageSource().contains(access_invalid)) {
            addResultForTestCase("1619", TEST_CASE_PASSED_STATUS, "");
            System.out.println(access_invalid);
        }

        String access_registered = "The Access Code you entered has already been registered, please login using the email address you used to register on our login page https://certvault.org/auth, if you continue to experience issues logging in please contact our help desk at helpdesk@certvault.org";
        if (driver.getPageSource().contains(access_registered)) {
            addResultForTestCase("1620", TEST_CASE_PASSED_STATUS, "");
            System.out.println(access_registered);
        }

        String access_expired = "The Access Code you entered is invalid, please contact our help desk at helpdesk@certvault.org for assistance.";
        if (driver.getPageSource().contains(access_expired)) {
            addResultForTestCase("10181", TEST_CASE_PASSED_STATUS, "");
            System.out.println(access_expired);
        }

        String registerurl = "https://certvault.dev.patracloud.com/register";
        if (driver.getCurrentUrl().contains(registerurl)) {
            addResultForTestCase("1610", TEST_CASE_PASSED_STATUS, "");
            System.out.println("Registering new user");
        }

        String form = "Please confirm and enter your information below to complete your account registration";
        if (driver.getPageSource().contains(form)) {
            addResultForTestCase("2002", TEST_CASE_PASSED_STATUS, "");
            System.out.println(form);
        }
    }

    public void forgotPwd() {
        forgotpwdlink.click();
    }

    public void forgotPwdemail(String ForgotPwdEmail) {
        forgotpwdemail.clear();
        forgotpwdemail.sendKeys(ForgotPwdEmail);
    }

    public void forgotSubmit() throws Exception {
        forgot_submitbtn.click();
        String resetsuccess = "Password Reset Email Sent Successfully. Please Follow Instruction for Password Reset.";
        String resetvalid = "An email is required to get temporary password, if you do not have one or forgot your email please contact your Broker or send an email to support@certvault.org";
        String invalidaccount = "Account does not exist";

        if (driver.getPageSource().contains(resetsuccess)) {
            System.out.println(resetsuccess);
            addResultForTestCase("1623", TEST_CASE_PASSED_STATUS, "");
        } else if (driver.getPageSource().contains(resetvalid)) {
            System.out.println(resetvalid);
            addResultForTestCase("1624", TEST_CASE_PASSED_STATUS, "");
        } else if (driver.getPageSource().contains(invalidaccount)) {
            System.out.println("Account does not exist");
            addResultForTestCase("1625", TEST_CASE_PASSED_STATUS, "");
        }
    }

   /* public void forgotCancel() throws IOException, APIException {
        forgot_cancelbtn.click();
        if(driver.getPageSource().contains("//*[@id=\"forgotPassword\"]/div/div/div/div[1]/div/h3")) {
           test.testgoogle.addResultForTestCase("620", TEST_CASE_FAILED_STATUS, "");
        } else {
           test.testgoogle.addResultForTestCase("620", TEST_CASE_PASSED_STATUS, "");
        }
    }*/

    /*
     ------------ Initializing elements for Register Info Page ----------
     */
    @FindBy(how = How.XPATH, using = "//*[@id=\"name\"]") // Register Info -name
    public WebElement RegInfo_name;
    @FindBy(how = How.XPATH, using = "//*[@id=\"newPassword\"]") // Register Info -Password
    public WebElement RegInfo_paswword;
    @FindBy(how = How.XPATH, using = "//*[@id=\"confirmPassword\"]") // Register Info -Confirm Password
    public WebElement RegInfo_confirmpaswword;
    @FindBy(how = How.XPATH, using = "//*[@id=\"address\"]")  //Register Info -address
    public WebElement RegInfo_address;
    @FindBy(how = How.XPATH, using = "//*[@id=\"email\"]")  //Register Info -email
    public WebElement RegInfo_email;
    @FindBy(how = How.XPATH, using = "//*[@id=\"ViewMyCertBTN\"]") //Register Info View my certs! btn
    public WebElement RegInfo_viewmycerts_btn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"error_msg\"]")  //Register Info validation
    public WebElement RegInfo_validation;

    public void registerInfo(String Name, String Password, String ConfirmPassword, String Address, String Email) throws Exception {
        RegInfo_name.sendKeys(Name);
        Thread.sleep(2000);
        RegInfo_paswword.sendKeys(Password);
        Thread.sleep(2000);
        RegInfo_confirmpaswword.sendKeys(ConfirmPassword);
        Thread.sleep(2000);
        if (!RegInfo_address.getText().equals("")) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 220)");
            RegInfo_viewmycerts_btn.click();
        } else {
            RegInfo_address.sendKeys(Address);
        }
        Thread.sleep(2000);
        if (RegInfo_email.getText().equals("")) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 240)");
            RegInfo_email.click();
            RegInfo_email.sendKeys(Email);
        } else {
            RegInfo_viewmycerts_btn.click();
        }
        Thread.sleep(2000);
        // RegInfo_viewmycerts_btn.click();
        // RegInfo_viewmycerts_btn.click();
        if (RegInfo_validation.isDisplayed() && RegInfo_validation.getText().contains("Please enter the fields:")) {
            System.out.println(RegInfo_validation.getText());
            addResultForTestCase("2002", TEST_CASE_PASSED_STATUS, "");
        }

        String TC_valid = "Please accept the Terms and Conditions before proceeding, to view the terms and conditions click here.";
        if (RegInfo_validation.getText().contains(TC_valid)) {
            //if (TC_valid.contains(RegInfo_validation.getText())) {
            System.out.println(TC_valid);
            addResultForTestCase("5184", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void registerInfo_valid() throws Exception {
        RegInfo_viewmycerts_btn.click();
        if (RegInfo_validation.isDisplayed() && RegInfo_validation.getText().contains("Please enter the fields:")) {
            System.out.println(RegInfo_validation.getText());
            addResultForTestCase("C2002", TEST_CASE_PASSED_STATUS, "");
        }
        Thread.sleep(2000);
    }

    /*
      ----TERMS AND CONDITIONS----
    */
    @FindBy(how = How.ID, using = "TermsConditionsCheck") // i accept terms and conditions
    public WebElement termandcondchckbox;
    @FindBy(how = How.XPATH, using = "//*[@id=\"form-registerInfo\"]/div[7]/p/label/span/u")
    //terms and conditions hyperlink
    public WebElement hyperlinktermsandcond;
    @FindBy(how = How.XPATH, using = "//*[@id=\"TandC\"]/div[2]/button")  //terms and conditions close btn pop-up
    public WebElement TC_closebtn;

    public void termsandcond_hyperlink() throws InterruptedException {
        hyperlinktermsandcond.click();
        Thread.sleep(2000);
        TC_closebtn.click();
    }

    public void termsandcond() throws Exception {
        termandcondchckbox.click();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 140)");
        RegInfo_viewmycerts_btn.click();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals("https://certvault.dev.patracloud.com/home")) {
            addResultForTestCase("5183", TEST_CASE_PASSED_STATUS, "");
            System.out.println("Registration process is done and user navigated to home page");
        }
    }

    /*
       -------Initializing elements - Batches-------
    */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//a[contains(text(),'Batches')]")  //batches link
    public WebElement Batches_menu;

    public void batchesmenu() {
        Batches_menu.click();
    }

    @FindBy(how = How.ID, using = "bat-id")
    public WebElement batchid;
    @FindBy(how = How.ID, using = "bat-name")
    public WebElement batchname;
    @FindBy(how = How.ID, using = "AddedOnfrom")
    public WebElement batch_Adddate_From;
    @FindBy(how = How.ID, using = "AddedOnto")
    public WebElement batch_Adddate_To;
    @FindBy(how = How.ID, using = "bat-insured")
    public WebElement batch_primaryinsured;
    @FindBy(how = How.ID, using = "bat-holder")
    public WebElement batch_primaryholder;
    @FindBy(how = How.XPATH, using = "//body[@class='page-loaded']/div[@class='wrapper']/section[@class='points-s']/div[@class='container-fluid']/div[@id='batch-list_wrapper']/table[@id='batch-list']/tfoot/tr/td[6]/input[1]")
    //Batch status
    public WebElement batch_Status;
    @FindBy(how = How.XPATH, using = "//body[@class='page-loaded']/div[@class='wrapper']/section[@class='points-s']/div[@class='container-fluid']/div[@id='batch-list_wrapper']/table[@id='batch-list']/tfoot/tr/td[7]/input[1]")
    //Batch company group
    public WebElement batch_Company_Group;
    @FindBy(how = How.XPATH, using = "//*[@id=\"batch-list\"]/tbody/tr[1]/td[1]/a")  //BatchId cert
    public WebElement batchcert;
    @FindBy(how = How.XPATH, using = "//*[@id=\"batch-list_wrapper\"]/div[2]/h6/a")
    public WebElement batches_clearallbtn;

    public void batchessearchgrid(String BatchID, String BatchName, String Batch_Adddate_From, String Batch_Adddate_To, String PrimaryInsured, String PrimaryHolder, String Batch_Status, String Batch_Company_Group) throws Exception {
        batchid.sendKeys(BatchID);
        batchname.sendKeys(BatchName);
        Actions actions = new Actions(driver);
        if (Batch_Adddate_From.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", batch_Adddate_From, Batch_Adddate_From);
            Thread.sleep(3000);
            batch_Adddate_From.sendKeys(Batch_Adddate_From);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (Batch_Adddate_To.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", batch_Adddate_To, Batch_Adddate_To);
            Thread.sleep(3000);
            batch_Adddate_To.sendKeys(Batch_Adddate_To);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        batch_primaryinsured.sendKeys(PrimaryInsured);
        batch_primaryholder.sendKeys(PrimaryHolder);
        batch_Status.sendKeys(Batch_Status);
        batch_Company_Group.sendKeys(Batch_Company_Group);
        batchid.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"batch-list\"]/tbody/tr/td[1]/a"), Pattern.compile(BatchID)));

        String expectedbatchid = driver.findElement(By.xpath("//*[@id=\"batch-list\"]/tbody/tr/td[1]/a")).getText();
        if (expectedbatchid.contains(BatchID)) {
            addResultForTestCase("5064", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5064", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void batchcertid() {
        batchcert.click();
    }

    public void batches_clear() throws Exception {
        batches_clearallbtn.click();
        if (batchid.getText().isEmpty()) {
            addResultForTestCase("5066", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5066", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
      ----------Initializing elements in Batch Detail view cert--------
    */
    @FindBy(how = How.ID, using = "companyName")
    public WebElement batchlist_companyname;
    @FindBy(how = How.ID, using = "companyType")
    public WebElement batchlist_companyType;
    @FindBy(how = How.ID, using = "noofcerts")
    public WebElement batchlist_noofcerts;

    public void batchdetailsearchgrid(String BD_CompanyName, String BD_CompanyType, String BD_NoofCerts, String BD_Status) {
        batchlist_companyname.sendKeys(BD_CompanyName);
        batchlist_companyType.sendKeys(BD_CompanyType);
        batchlist_noofcerts.sendKeys(BD_NoofCerts);
        Select drpstatus = new Select(driver.findElement(By.id("status")));
        drpstatus.selectByVisibleText(BD_Status);
    }

    //   @FindBy(how = How.XPATH, using = "//*[@id=\"batch-list\"]/tbody/tr[1]/td[5]/a[1]")  //PRL
    @FindBy(how = How.XPATH, using = "//*[contains(@title, 'Paper Registration')]")
    public WebElement BD_PRLicon;
    @FindBy(how = How.ID, using = "Preg")  //Save
    public WebElement BD_PRLsavebtn;
    @FindBy(how = How.LINK_TEXT, using = "Email Registration")
    //ER  base.driver.findElement(By.xpath("//*[contains(@title, 'Email Registration')]")).isDisplayed();
    public WebElement BD_ERicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"batch-list\"]/tbody/tr[1]/td[5]/a[1]/i") //View icon
    //  @FindBy(how = How.XPATH, using = "//*[contains(@title, 'View Certs')]")
    public WebElement BD_Viewicon; //*[@id="batch-list"]/tbody/tr[1]/td[5]/a[1]/i
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/tbody/tr/td[1]/a") //Cert id in Batch Certificates
    public WebElement BC_certid;

    public void batchDetail_Action() throws InterruptedException {
        try {
            System.out.println(BD_PRLicon.getAttribute("title"));
            if (driver.findElement(By.xpath("//*[contains(@title, 'Paper Registration')]")).isDisplayed()) {
                BD_PRLicon.click();
                //  System.out.println("Action column: Clicks PRL icon in Batch Detail page");
                Thread.sleep(2000);
                BD_PRLsavebtn.click();
                Thread.sleep(4000);
            }
        } catch (Exception e) {
            System.out.println("Action column: PRL icon is not available Batch Detail page");
        }

        try {
            System.out.println(BD_ERicon.isDisplayed());
            if (BD_ERicon.isDisplayed()) {
                BD_ERicon.click();
                //  driver.findElement(By.xpath("//*[contains(@title, 'Email Registration')]")).click();
                System.out.println("Action column: Clicks ER icon in Batch Detail page");
                entercertemail.clear();
                popupsavebtn.click();
                entercertemail.sendKeys("test@gmail.com");
                popupsavebtn.click();
                Thread.sleep(4000);
            }
        } catch (Exception e) {
            System.out.println("Action column: ER icon is not available Batch Detail page");
        }
    }

    public void batchDetail_Action_View() throws InterruptedException {
        System.out.println("Clicks View icon in Batch Detail page Action column");
        BD_Viewicon.click();
        Thread.sleep(3000);
    }

    public void batchcert_certid(String CertId, String insured, String holder, String issuer, String expirationfrom, String expirationto, String issuedfrom, String issuedto, String uploaddatefrom, String uploaddateto,
                                 String insuredstatus, String holderstatus) throws Exception {
        Certid.sendKeys(CertId);
        Certinsured.sendKeys(insured);
        Certholder.sendKeys(holder);
        Certissuer.sendKeys(issuer);
        Actions actions = new Actions(driver);
        if (expirationfrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certexpirationfrom, expirationfrom);
            Thread.sleep(3000);
            Certexpirationfrom.sendKeys(expirationfrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (expirationto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certexpirationto, expirationto);
            Thread.sleep(3000);
            Certexpirationto.sendKeys(expirationto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (issuedfrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedfrom, issuedfrom);
            Thread.sleep(3000);
            Certissuedfrom.sendKeys(issuedfrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (issuedto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certissuedto, issuedto);
            Thread.sleep(3000);
            Certissuedto.sendKeys(issuedto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (uploaddatefrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddatefrom, uploaddatefrom);
            Thread.sleep(3000);
            Certuploaddatefrom.sendKeys(uploaddatefrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (uploaddateto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", Certuploaddateto, uploaddateto);
            Thread.sleep(3000);
            Certuploaddateto.sendKeys(uploaddateto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        Certinsuredstatus.sendKeys(insuredstatus);
        Certholderstatus.sendKeys(holderstatus);
        BC_certid.click();
        System.out.println("Clicks on cert id - Batch Certificates");
        String expectedbatchurl = "https://certvault.dev.patracloud.com/batchList/viewCert/";
        if (driver.getCurrentUrl().contains(expectedbatchurl)) {
            addResultForTestCase("5067", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5067", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
      ------Initializing elements - Clients--------
   */
    @FindBy(how = How.XPATH, using = "//nav[@class='navbar']//a[contains(text(),'Clients')]")
    public WebElement Clients_menu;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-clients-list\"]/tbody/tr[1]/td[8]/a/i")
    public WebElement favoriteicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-clients-list\"]/tbody/tr[1]/td[1]/a")  //companyname certs view
    public WebElement clientviewcert;

    public void clientsmenu() {
        Clients_menu.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"Company Name\"]")
    public WebElement clientgrid_companyname;
    @FindBy(how = How.XPATH, using = "//input[@id='AddedDatefrom']")
    public WebElement clientgrid_adddatefrom;
    @FindBy(how = How.XPATH, using = "//input[@id='AddedDateto']")
    public WebElement clientgrid_adddateto;
    @FindBy(how = How.XPATH, using = "//input[@id='Company Group']")
    public WebElement clientgrid_companygroup;
    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-clients-list\"]/tbody/tr/td[1]/a")
    public WebElement cl_companynameresult;
    @FindBy(how = How.XPATH, using = "//*[@id=\"certs-list\"]/tbody/tr/td[1]/a")  //client_certid
    public WebElement client_certid;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[3]/h6/a") //Client - clear all
    public WebElement client_clearbtn;

    public void clientssearchgrid(String CompanyName, String CompanyType, String cl_AdddateFrom, String cl_AdddateTo, String Favorite, String CompRegistered, String cl_CompanyGroup) throws Exception {
        clientgrid_companyname.sendKeys(CompanyName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", clientgrid_adddatefrom, cl_AdddateFrom);// Enables the from date box
        Thread.sleep(2000);
        clientgrid_adddatefrom.sendKeys(cl_AdddateFrom);
        Actions actions = new Actions(driver);
        actions.click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", clientgrid_adddateto, cl_AdddateTo);// Enables the to date box
        clientgrid_adddateto.sendKeys(cl_AdddateTo);
        actions.click().perform();
        Thread.sleep(2000);
        clientgrid_companygroup.sendKeys(cl_CompanyGroup);
        Select drpcompanytype = new Select(driver.findElement(By.id("Company Type")));
        drpcompanytype.selectByVisibleText(CompanyType);
        Thread.sleep(5000);
        String cl_companynameresult = driver.findElement(By.xpath("//*[@id=\"manage-clients-list\"]/tbody/tr/td[1]/a")).getText();

        if (cl_companynameresult.contains(CompanyName)) {
            addResultForTestCase("4870", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("4870", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void favorite() throws Exception {
        favoriteicon.click();
        if (favoriteicon.isDisplayed()) {
            addResultForTestCase("5127", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5127", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void clientsview() throws Exception {
        clientviewcert.click();
        Thread.sleep(3000);
        String expclientviewURL = "https://certvault.dev.patracloud.com/clients/clientListViewCerts/";
        Thread.sleep(3000);
        if (driver.getCurrentUrl().contains(expclientviewURL)) {
            addResultForTestCase("4871", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("4871", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void clientListViewCerts() {
        client_certid.click();
        System.out.println("Clicks cert link under cert id column");
    }

    public void client_clear() throws Exception {
        client_clearbtn.click();
        if (clientgrid_companyname.getText().isEmpty()) {
            System.out.println("search data cleared in grid fields");
            addResultForTestCase("4873", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("4873", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
    Initializing elements - Printshop app
     */
    @FindBy(how = How.XPATH, using = "//*[@id=\"projectedImplKpi\"]/div[2]/div[2]")
    public WebElement assignedPS_kpi;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-TransactionTypepsGrid\"]/span/div/button/span")
    public WebElement drpservice;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-TransactionTypepsGrid\"]/span/div/ul/li[1]/div/input")
    public WebElement drpservicesearch;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-WorkOrderTypepsGrid\"]/span/div/button/span")
    public WebElement drpmailingtype;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-WorkOrderTypepsGrid\"]/span/div/ul/li[1]/div/input")
    public WebElement drpmailingtypesearch;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-NotespsGrid\"]")
    public WebElement notes_column;
    @FindBy(how = How.XPATH, using = "//*[@id=\"th-NPWorkOrderPrintShopIDpsGrid\"]/div")
    public WebElement sortdesc_recordid;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtprocessing\"]/tbody/tr[1]/td[1]/a")
    public WebElement recordid;
    @FindBy(how = How.XPATH, using = "//*[@id=\"btnattachments\"]/a")  //Attachments tab
    public WebElement attachments_tab;
    @FindBy(how = How.XPATH, using = "//*[@id=\"dtattachments\"]/tbody/tr/td[2]/a")  //attachment link
    public WebElement attachmentlink;

    public void assignedtoPS_KPI() throws InterruptedException {
        assignedPS_kpi.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 130)");
        Thread.sleep(3000);
    }

    public void ps_gridsearch(String Service, String MailingType) throws InterruptedException {
        drpservice.click();
        Thread.sleep(3000);
        drpservicesearch.sendKeys(Service);
        Thread.sleep(3000);
        WebElement checkbox_service = driver.findElement(By.xpath("//*[@id=\"th-TransactionTypepsGrid\"]/span/div/ul/li[28]/a/label/input"));
        checkbox_service.click();
        System.out.println("clicks Certificate Issuance checkbox");
        ((JavascriptExecutor) driver).executeScript("window. scrollBy(200,0)");
        drpmailingtype.click();
        Thread.sleep(3000);
        drpmailingtypesearch.sendKeys(MailingType);
        WebElement chckbox_mailingtype = driver.findElement(By.xpath("//*[@id=\"th-WorkOrderTypepsGrid\"]/span/div/ul/li[9]/a/label/input"));
        chckbox_mailingtype.click();
        System.out.println("clicks CertVault checkbox");
        notes_column.click();
        ((JavascriptExecutor) driver).executeScript("window. scrollBy(-200,0)");
    }

    public void record_ID() throws Exception {
        sortdesc_recordid.click();
        Thread.sleep(3000);
        sortdesc_recordid.click();
        Thread.sleep(4000);
        recordid.click();
        Thread.sleep(4000);
        if (driver.getCurrentUrl().contains("https://printshopdev.patracorp.net/printshop/")) {
            addResultForTestCase("9904", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9904", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void attachments_PS() throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", attachments_tab);
        attachments_tab.click();
    }

    public void view_attach() throws Exception {
        if (attachmentlink.isDisplayed()) {
            attachmentlink.click();
            addResultForTestCase("9908", TEST_CASE_PASSED_STATUS, "");
            addResultForTestCase("10566", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9908", TEST_CASE_FAILED_STATUS, "");
            addResultForTestCase("10566", TEST_CASE_PASSED_STATUS, "");
        }
    }

    /*
    Initializing elements for Upload & print module
     */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[6]/a")
    public WebElement link_uploadandprint;

    public void uploadprint() {
        link_uploadandprint.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("https://certvault.dev.patracloud.com/printReplacement/upload"));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
    }

    public void upload_sendmail() {
        btnUploadFile.click();
    }

    public void valid_checkflag() throws Exception {
        if (invalid_popupOkbtn.isDisplayed()) {
            Thread.sleep(2000);
            invalid_popupOkbtn.click();
            addResultForTestCase("9895", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9895", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"fileUploadForm\"]/div/div/div[2]/div/div[3]/label/span/span")
    //holder mail
    public WebElement chckholdermail;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fileUploadForm\"]/div/div/div[2]/div/div[4]/label/span/span")
    //holder mail
    public WebElement chckinsuredmail;

    public void checkflag() throws InterruptedException {
        chckholdermail.click();
        Thread.sleep(2000);
        chckinsuredmail.click();
    }

    // gmail xpath
    public @FindBy(how = How.XPATH, using = "//input[@id='identifierId']") // gmail email id -
            WebElement textfield_email_id; //*[@id="Email"]
    public @FindBy(how = How.XPATH, using = "//div[@class='VfPpkd-RLmnJb']") //next button
            WebElement button_next;
    public @FindBy(how = How.XPATH, using = "//input[@name='password']") //gmail password field
            WebElement textfield_password;
    public @FindBy(how = How.XPATH, using = "//div[@id='passwordNext']//button[@type='button']") // gmail sign-in button
            WebElement button_sign_in;
    public @FindBy(how = How.XPATH, using = "//body[@class='aAU']/div/div[@class='nH']/div[@class='nH']/div[@class='nH bkL']/div[@class='no']/div[@class='nH bkK nn']/div[@class='nH']/div[@class='nH']/div[@class='nH ar4 z']/div/div[@id=':4']/div[@class='G-atb D E']/div[@class='iH bzn']/div[@class='G-tF']/div[1]")// back to inbox button
            WebElement back_to_inbox;
    public @FindBy(how = How.XPATH, using = "//img[@class='ajT']")
    WebElement content_spread;
    public @FindBy(how = How.XPATH, using = "//span[@class='gD']//span[contains(text(),'smtpuser@patracorp.net')]") // from email
    WebElement smtpuser_email;
    public @FindBy(how = How.XPATH, using = "//span[@class='gD']//span[contains(text(),'No-Reply.CertVault@usi.com')]") // from email
    WebElement smtpconfig_email;
    public @FindBy(how = How.XPATH, using = "//p[contains(text(),'To Whom It May Concern,')]") // blocked email
    WebElement blocked_text_email;
    public @FindBy(how = How.XPATH, using = "//p[contains(text(),'Please find the attached certificate of insurance')]") // cert email
    WebElement cert_text_email;



    public void gmail_account() throws Exception {
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(10000);
        driver.get("https://mail.google.com/");
        textfield_email_id.sendKeys("xxxx@info.com");  //xxxx@info.com
        Thread.sleep(5000);
        button_next.click();
        Thread.sleep(5000);
        textfield_password.sendKeys("xxxx");  //xxxx
        Thread.sleep(5000);
        button_sign_in.click();
        Thread.sleep(5000);
    }

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Claim Access Code')]")
    public WebElement user_access_code;
    @FindBy(how = How.ID, using = "AccessCode") //Access code - //*[@id="form-accesscode-signin"]/div[2]/input[1]
    public WebElement accesscodeclaim;
    @FindBy(how = How.XPATH, using = "//*[@class='progress-button-save']")
    //Access code - //*[@id="form-accesscode-signin"]/div[2]/input[1]
    public WebElement submit_access_code_btn;
    @FindBy(how = How.XPATH, using = "//button[@class='swal-button swal-button--confirm']")
    public WebElement claim_err_popup_ok;

    public void userAccessCode() throws Exception {
        if (linkUserprofile.isDisplayed()) {
            linkUserprofile.click();
            Thread.sleep(3000);
            if (user_access_code.isDisplayed()) {
                Thread.sleep(3000);
                user_access_code.click();
            }
            addResultForTestCase("9777", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9777", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void accessCodeClaim(String AccessCodeClaim) {
        accesscodeclaim.sendKeys(AccessCodeClaim);
    }

    public void Submit_access_code() throws Exception {
        if (submit_access_code_btn.isDisplayed()) {
            submit_access_code_btn.click();
            Thread.sleep(5000);
            // Valid access code to claim/ invalid / claimed / pending access code / merges for same companyid validation
            String success_access_code = "The Access Code has been claimed and has been submitted for review, you will receive a notification once it has been processed.";
            String invalid_access_code = "Invalid Access Code: The access code you entered could not be found, please confirm the access code was entered correct and try again. If this issue persists please contact our help desk for further support (helpdesk@certvault.org).";
            String Pending_claimed_access_code = "Access Code Already in a proposed merge: Unfortunately the access code you entered already has a pending claim, please contact our help desk (helpdesk@certvault.org) for further assistance.";
            String access_code_merged = "Access Code Already Claimed/Merged: Unfortunately the access code you entered has already been claimed, please contact our help desk (helpdesk@certvault.org) for further assistance.";
            String samecompanyid_mergevalidation = "The Access Code you are attempting to claim is for your current company, as it is already associated with your account it does not need to be claimed. If you have any questions please contact our helpdesk at helpdesk@certvault.org.";
            if (driver.getPageSource().contains(success_access_code)) {
                String success = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]")).getText();
                if (success.contentEquals(success_access_code)) {
                    System.out.println("validation text appeared is:" + success);
                    addResultForTestCase("9777", TEST_CASE_PASSED_STATUS, "");
                }
                claim_err_popup_ok.click();
                Thread.sleep(5000);
            } else if (driver.getPageSource().contains(invalid_access_code)) {
                String invalid = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]")).getText();
                if (invalid.contentEquals(invalid_access_code)) {
                    System.out.println("validation text appeared is:" + invalid);
                    addResultForTestCase("11681", TEST_CASE_PASSED_STATUS, "");
                }
                claim_err_popup_ok.click();
                Thread.sleep(5000);
            } else if (driver.getPageSource().contains(Pending_claimed_access_code)) {
                String pending = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]")).getText();
                if (pending.contentEquals(Pending_claimed_access_code)) {
                    System.out.println("validation text appeared is:" + pending);
                    addResultForTestCase("11680", TEST_CASE_PASSED_STATUS, "");
                }
                claim_err_popup_ok.click();
                Thread.sleep(5000);
            } else if (driver.getPageSource().contains(access_code_merged)) {
                String usedaccesscode = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]")).getText();
                if (usedaccesscode.contentEquals(access_code_merged)) {
                    addResultForTestCase("9781", TEST_CASE_PASSED_STATUS, "");
                    System.out.println("validation text appeared is:" + usedaccesscode);
                }
                claim_err_popup_ok.click();
                Thread.sleep(5000);
            } else if (driver.getPageSource().contains(samecompanyid_mergevalidation)) {
                String samecompid_accesscode = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]")).getText();
                if(samecompid_accesscode.contentEquals(samecompanyid_mergevalidation)) {
                    addResultForTestCase("2437", TEST_CASE_PASSED_STATUS, "");
                    System.out.println("validation text appeared is:" + samecompid_accesscode);
                }
                claim_err_popup_ok.click();
                Thread.sleep(5000);
            }
        }
    }

    // proposed company merge
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Proposed Company Merge')]")  // Proposed Company merge
    public WebElement proposed_company_merge_tab;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/section[1]/div[1]/div[1]/div[2]/h6[1]/a[1]")
// Clear button Proposed Company merge
    public WebElement clearbtn_pcm;
    @FindBy(how = How.ID, using = "user-request")  // user requesting merge
    public WebElement proposeuserrequestingmerge;
    @FindBy(how = How.XPATH, using = "//body[@class='page-loaded']/div[@class='wrapper']/section[@class='points-s']/div[@class='container-fluid']/div[@id='proposedCompanyMerge_wrapper']/table[@id='proposedCompanyMerge']/tfoot/tr/td[2]/input[1]")
// users company
    public WebElement proposeuserscompany;
    @FindBy(how = How.XPATH, using = "//body[@class='page-loaded']/div[@class='wrapper']/section[@class='points-s']/div[@class='container-fluid']/div[@id='proposedCompanyMerge_wrapper']/table[@id='proposedCompanyMerge']/tfoot/tr/td[3]/input[1]")
// users company type
    public WebElement proposeuserscompanytype;
    @FindBy(how = How.ID, using = "users-company-type-id")// users company id
    public WebElement proposeuserscompanyid;
    @FindBy(how = How.ID, using = "users-company-type-address")// users company type address
    public WebElement proposeuserscompanytypeaddress;
    @FindBy(how = How.ID, using = "company-tobe-mereged")  // company to be merged
    public WebElement proposecompanytobemerged;
    @FindBy(how = How.ID, using = "company-tobe-merged-type")// company to be merged type
    public WebElement proposecompanytobemergedtype;
    @FindBy(how = How.ID, using = "company-tobe-merged-type-id")// company to be merged type company id
    public WebElement proposecompanytobemergedtypecompanyid;
    @FindBy(how = How.ID, using = "company-tobe-merged-type-address")// company to be merged type address
    public WebElement proposecompanytobemergedtypeaddress;
    @FindBy(how = How.XPATH, using = "//input[@id='AddedOnfrom']")  // add date from
    public WebElement proposeadddatefrom;
    @FindBy(how = How.XPATH, using = "//input[@id='AddedOnto']")  // add date to
    public WebElement proposeadddateto;
    @FindBy(how = How.ID, using = "bat-name")// status
    public WebElement proposestatus;
    @FindBy(how = How.ID, using = "bat-holder")  // completed by
    public WebElement proposecompletedby;
    @FindBy(how = How.ID, using = "bat-requestedby")  // requested by
    public WebElement proposerequestedby;
    @FindBy(how = How.XPATH, using = "//table[@id='proposedCompanyMerge']/tbody/tr/td[11]")  // status
    public WebElement propose_status;
    @FindBy(how = How.XPATH, using = "//table[@id='proposedCompanyMerge']/tbody/tr/td[14]/a[2]/i[1]")  // deny
    public WebElement propose_deny;
    @FindBy(how = How.XPATH, using = "//table[@id='proposedCompanyMerge']/tbody/tr/td[14]/a[1]/i[1]")  // approve
    public WebElement propose_approve;

    public void proposed_company_merge() throws Exception {
        proposed_company_merge_tab.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.urlContains("https://certvault.dev.patracloud.com/propsedCompanyMerge"));
        addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
    }

    public void proposedmergeclear() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        if (clearbtn_pcm.isDisplayed()) {
            clearbtn_pcm.click();
            addResultForTestCase("11612", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11612", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void Proposedvalueassign(String userrequestingmerge, String userscompany, String userscompanytype, String userscompanyid, String userscompanytypeaddress, String companytobemerged, String companytobemergedtype, String companytobemergedtypecompanyid, String companytobemergedtypeaddress, String adddatefrom, String adddateto, String status, String completedby, String requestedby) throws Exception {
        proposeuserrequestingmerge.sendKeys(userrequestingmerge);
        Thread.sleep(5000);
        proposeuserscompany.sendKeys(userscompany);
        proposeuserscompanytype.sendKeys(userscompanytype);
        proposeuserscompanyid.sendKeys(userscompanyid);
        proposeuserscompanytypeaddress.sendKeys(userscompanytypeaddress);
        proposecompanytobemerged.sendKeys(companytobemerged);
        proposecompanytobemergedtype.sendKeys(companytobemergedtype);
        proposecompanytobemergedtypecompanyid.sendKeys(companytobemergedtypecompanyid);
        proposecompanytobemergedtypeaddress.sendKeys(companytobemergedtypeaddress);
        Actions actions = new Actions(driver);
        if (adddatefrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", proposeadddatefrom, adddatefrom);
            Thread.sleep(3000);
            proposeadddatefrom.sendKeys(adddatefrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (adddateto.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", proposeadddateto, adddateto);
            Thread.sleep(3000);
            proposeadddateto.sendKeys(adddateto);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        proposestatus.sendKeys(status);
        // proposecompletedby.sendKeys(completedby);
        //proposerequestedby.sendKeys(requestedby);
        String actualcompanytobemerged = driver.findElement(By.xpath("//table[@id='proposedCompanyMerge']/tbody/tr[1]/td[6]")).getText();
        if (actualcompanytobemerged.contentEquals(companytobemerged)) {
            addResultForTestCase("11615", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void proposestatus() throws Exception {
        Thread.sleep(2000);
        proposestatus.clear();
        String propose_status_set = propose_status.getText();
        out.println("Proposed company merge status is:" + propose_status_set);
        addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
    }

    public void proposedeny() throws Exception {
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(500, 200)");
        Thread.sleep(3000);
        propose_deny.click();
        System.out.println("clicks deny - proposed merged");
        Thread.sleep(3000);
        String success_deny = "Request Completed Successfully";
        if (driver.getPageSource().contains(success_deny)) {
            System.out.println(propose_status.getText());
            addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void proposeapprove() throws Exception {
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(500, 200)");
        Thread.sleep(3000);
        propose_approve.click();
        System.out.println("clicks approve - proposed merged");
        Thread.sleep(3000);
        String success_approve = "Request Completed Successfully";
        if (driver.getPageSource().contains(success_approve)) {
            System.out.println(propose_status.getText());
            addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("9780", TEST_CASE_PASSED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//a[text() = 'Archived Merges ']")
    public WebElement link_archivedmerges;
    @FindBy(how = How.XPATH, using = "//a[text() = 'Active Proposed Merges ']")
    public WebElement link_activeproposedmerges;

    public void archivedmerges() {
        link_archivedmerges.click();
    }
    public void activeproposedmerges() {
        link_activeproposedmerges.click();
    }


    @FindBy(how = How.XPATH, using = "//select[@id='brokerselectlist']")  //switch company
    public WebElement switchcompany;

    public void switchCompany(String Switch_company) throws Exception {
        linkUserprofile.click();
        Thread.sleep(3000);
        switchcompany.click();
        Boolean flag = true;
        List<WebElement> switchcompoptions = new ArrayList<>();
        switchcompoptions = switchcompany.findElements(By.xpath("/html[1]/body[1]/div[2]/nav[1]/div[3]/label[1]/ul[1]/li[4]/div[1]/select[1]"));
        for (WebElement switchcompoption : switchcompoptions) {
            if (switchcompoption.getText().contains(Switch_company)) {
                System.out.println("switch company list:" + switchcompoption.getText());
                Thread.sleep(3000);
                Select sel = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[2]/nav[1]/div[3]/label[1]/ul[1]/li[4]/div[1]/select[1]")));
                sel.selectByVisibleText(Switch_company);
                Thread.sleep(5000);
                addResultForTestCase("11343", TEST_CASE_PASSED_STATUS, "");
                linkUserprofile.click();
                break;
            } else {
                if (flag)
                    flag = true;
                System.out.println("Existing Switch company list is:" + switchcompoption.getText() + "So, Selected Company doesn't exist in Switch company dropdown");
                addResultForTestCase("11343", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    public void email_cert_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(5000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bog']//span[@class='bqe'][starts-with(text(),'Certificate of Insurance')]"));
        // Mailer name for which i want to check do i have an email in my inbox
        String cert_email_sub = "Certificate of Insurance";
// real logic starts here
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                // now verify if you have got mail form a specific mailer (Note Un-read mails)
                // for read mails xpath loactor will change but logic will remain same
                if (unreademail.get(i).getText().startsWith(cert_email_sub)) {
                    System.out.println("Yes we have got mail from " + cert_email_sub);
                    // also you can perform more actions here
                    // like if you want to open email form the mailer
                    unreademail.get(i).click();
                    Thread.sleep(2000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (driver.findElement(By.xpath("//span[@class='gD']")).isDisplayed()) {
                        List<WebElement> smtp_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'smtpuser@patracorp.net')]"));
                        List<WebElement> smtpconfig_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'No-Reply.CertVault@usi.com')]"));
                        List<WebElement> cert_email_nofound = driver.findElements(By.xpath("//p[contains(text(),'Please find the attached certificate of insurance')]"));
                        List<WebElement> blocked_email_nofound = driver.findElements(By.xpath("//p[contains(text(),'To Whom It May Concern,')]"));
                        String smtp = "smtpuser@patracorp.net";
                        String smtp_config_email = "No-Reply.CertVault@usi.com";
                        if (smtp_nofound.size() == 0) {
                        }
                        else if (smtp.contains(smtpuser_email.getText())) {
                            out.println(smtpuser_email.getText());
                            if (cert_email_nofound.size() == 0) {
                            }
                            else if (cert_text_email.isDisplayed()) {
                                addResultForTestCase("10943", TEST_CASE_PASSED_STATUS, "");
                            }
                            if (blocked_email_nofound.size() == 0) {
                            }
                            else if (blocked_text_email.isDisplayed()) {
                                addResultForTestCase("11469", TEST_CASE_PASSED_STATUS, "");
                                addResultForTestCase("11795", TEST_CASE_PASSED_STATUS, "");
                            }
                        }
                        if (smtpconfig_nofound.size() == 0) {
                        }
                        else if (smtp_config_email.contains(smtpconfig_email.getText())) {
                            out.println(smtpconfig_email.getText());
                            if (cert_email_nofound.size() == 0) {
                            }
                            else if (cert_text_email.isDisplayed()) {
                                addResultForTestCase("10943", TEST_CASE_PASSED_STATUS, "");
                            }
                            if (blocked_email_nofound.size() == 0) {
                            }
                            else if (blocked_text_email.isDisplayed()) {
                                addResultForTestCase("11469", TEST_CASE_PASSED_STATUS, "");
                                addResultForTestCase("11795", TEST_CASE_PASSED_STATUS, "");
                            }
                            addResultForTestCase("12849", TEST_CASE_PASSED_STATUS, "");
                        }
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + cert_email_sub);
                    Thread.sleep(5000);
                }
            }
        }
        driver.switchTo().window(tabs.get(0));
    }

    public void delivery_failure_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bqe']"));
        String cert_email_failure = "CertVault Email Delivery Failure";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().equals(cert_email_failure)) {
                    System.out.println("Yes we have got mail from " + cert_email_failure);
                    unreademail.get(i).click();
                    Thread.sleep(5000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + cert_email_failure);
                    Thread.sleep(5000);
                }
            }
        }
        addResultForTestCase("1745", TEST_CASE_PASSED_STATUS, "");
        addResultForTestCase("11678", TEST_CASE_PASSED_STATUS, "");
        driver.switchTo().window(tabs.get(0));
    }

    public void email_reg_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(5000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bog']//span[@class='bqe'][starts-with(text(),'Please Register to View your Certificate of Insurance')]"));
        String email_reg_sub = "Please Register to View your Certificate of Insurance";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().startsWith(email_reg_sub)) {
                    System.out.println("Yes we have got mail from " + email_reg_sub);
                    unreademail.get(i).click();
                    Thread.sleep(2000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (driver.findElement(By.xpath("//span[@class='gD']")).isDisplayed()) {
                        List<WebElement> smtp_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'smtpuser@patracorp.net')]"));
                        List<WebElement> smtpconfig_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'No-Reply.CertVault@usi.com')]"));
                        String smtp = "smtpuser@patracorp.net";
                        String smtp_config_email = "No-Reply.CertVault@usi.com";
                        if (smtp_nofound.size() == 0) {
                        } else if (smtp.contains(smtpuser_email.getText())) {
                            out.println(smtpuser_email.getText());
                            addResultForTestCase("11674", TEST_CASE_PASSED_STATUS, "");
                            addResultForTestCase("10871", TEST_CASE_PASSED_STATUS, "");
                        }
                        if (smtpconfig_nofound.size() == 0) {
                        } else if (smtp_config_email.contains(smtpconfig_email.getText())) {
                            out.println(smtpconfig_email.getText());
                            addResultForTestCase("11674", TEST_CASE_PASSED_STATUS, "");
                            addResultForTestCase("10871", TEST_CASE_PASSED_STATUS, "");
                            addResultForTestCase("12849", TEST_CASE_PASSED_STATUS, "");
                        }
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + email_reg_sub);
                    Thread.sleep(5000);
                }
            }
        }
        driver.switchTo().window(tabs.get(0));
    }

    public void single_cert_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bqe']"));
        String single_cert_success = "A New Certificate has been uploaded to CertVault";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().equals(single_cert_success)) {
                    System.out.println("Yes we have got mail from " + single_cert_success);
                    unreademail.get(i).click();
                    Thread.sleep(5000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (driver.findElement(By.xpath("//span[@class='gD']")).isDisplayed()) {
                        List<WebElement> smtp_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'smtpuser@patracorp.net')]"));
                        List<WebElement> smtpconfig_nofound = driver.findElements(By.xpath("//span[@class='gD']//span[contains(text(),'No-Reply.CertVault@usi.com')]"));
                        String smtp = "smtpuser@patracorp.net";
                        String smtp_config_email = "No-Reply.CertVault@usi.com";
                        if (smtp_nofound.size() == 0) {
                        } else if (smtp.contains(smtpuser_email.getText())) {
                            out.println(smtpuser_email.getText());
                            addResultForTestCase("1636", TEST_CASE_PASSED_STATUS, "");
                        }
                        if (smtpconfig_nofound.size() == 0) {
                        } else if (smtp_config_email.contains(smtpconfig_email.getText())) {
                            out.println(smtpconfig_email.getText());
                            addResultForTestCase("1636", TEST_CASE_PASSED_STATUS, "");
                            addResultForTestCase("12849", TEST_CASE_PASSED_STATUS, "");
                        }
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + single_cert_success);
                    Thread.sleep(5000);
                }
            }
        }
        driver.switchTo().window(tabs.get(0));
    }

    public void renewal_cert_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bqe']"));
        String renewal_cert_success = "CertVault Notification";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().equals(renewal_cert_success)) {
                    System.out.println("Yes we have got mail from " + renewal_cert_success);
                    unreademail.get(i).click();
                    Thread.sleep(5000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + renewal_cert_success);
                    Thread.sleep(5000);
                }
            }
        }
        addResultForTestCase("7421", TEST_CASE_PASSED_STATUS, "");
        driver.switchTo().window(tabs.get(0));
    }

    public void forgot_password_notification() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to gmail tab
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bqe']"));
        String forgot_password_success = "Forgot Password";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().equals(forgot_password_success)) {
                    System.out.println("Yes we have got mail from " + forgot_password_success);
                    unreademail.get(i).click();
                    Thread.sleep(5000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + forgot_password_success);
                    Thread.sleep(5000);
                }
            }
        }
        addResultForTestCase("1623", TEST_CASE_PASSED_STATUS, "");
        driver.switchTo().window(tabs.get(0));
    }

    /*
      Initializing elements for Blocked Domains
     */

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Block List')]")
    public WebElement blockeddomain_page;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[4]/h6/a")     //clear all
    public WebElement blockeddomain_clearall;
    @FindBy(how = How.ID, using = "ID")
    public WebElement bdgrid_id;
    @FindBy(how = How.ID, using = "Domain Name")
    public WebElement bdgrid_domainname;
    @FindBy(how = How.ID, using = "Company")
    public WebElement bdgrid_company;
    @FindBy(how = How.ID, using = "Status")
    public WebElement bdgrid_status;
    @FindBy(how = How.XPATH, using = "//*[@id=\"blocked-domain-list\"]/tbody/tr/td[6]/a[2]/i")
    public WebElement bd_deleteicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Dlt\"]")  //Delete btn in pop-up
    public WebElement bd_deletepopbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"em\"]/div[3]/div[1]/button")  //delete cancel btn in pop-up
    public WebElement bd_cancelpopbtn;
    @FindBy(how = How.ID, using = "add-blocked-domain")  //Add blocked Domain
    public WebElement addblockeddomain_btn;
    @FindBy(how = How.ID, using = "submit-btn-company") //Add blocked Domain save btn
    public WebElement addblock_savebtn;
    @FindBy(how = How.ID, using = "blockedType")  //select Blocked Type
    public WebElement selblockedtype;
    @FindBy(how = How.ID, using = "editDomainName")  //Domain /Email
    public WebElement domainemail;
    @FindBy(how = How.ID, using = "domainStatusID")  //Status
    public WebElement addbd_status;
    @FindBy(how = How.ID, using = "EditCompanyID")  //Company
    public WebElement addbd_company;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[2]/p")  //Edit block domain success msg, Delete success msg
    public WebElement editbd_successmsg;
    @FindBy(how = How.XPATH, using = "//*[@id=\"em\"]/a")  //X icon -Add blocked domain
    public WebElement xicon_bd;
    @FindBy(how = How.XPATH, using = "//td[contains(@class,'active day')]")
    public WebElement active_day;

    public void blockeddomain() {
        blockeddomain_page.click();
    }

    public void blockeddomainclear() throws Exception {
        blockeddomain_clearall.click();
        if (bdgrid_domainname.getText().isEmpty()) {
            addResultForTestCase("12015", TEST_CASE_PASSED_STATUS, "");  //11372
        } else {
            addResultForTestCase("12015", TEST_CASE_FAILED_STATUS, "");  //11372
        }
    }

    public void addblockeddomain() {
        addblockeddomain_btn.click();
    }

    public void filladdblockdomain(String BlockedType, String DomainEmail, String Status, String Company) {
        Select drp = new Select(selblockedtype);
        drp.selectByVisibleText(BlockedType);
        domainemail.clear();
        domainemail.sendKeys(DomainEmail);
        addbd_status.click();
        addbd_status.sendKeys(Status);
        addbd_company.click();
        addbd_company.sendKeys(Company);
        addbd_company.click();
    }

    public void fillfaxrecord(String BlockedType, String Fax) {
        Select drp = new Select(selblockedtype);
        drp.selectByVisibleText(BlockedType);
        domainemail.sendKeys(Fax);
    }

    public void addbd_savebtn() throws Exception {
        if (addblock_savebtn.isDisplayed()) {
            addblock_savebtn.click();
            try {
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.visibilityOf(editbd_successmsg));
                System.out.println(editbd_successmsg.getText());
                addResultForTestCase("11373", TEST_CASE_PASSED_STATUS, "");

                String valdupl_blockedrecord = "Record cannot be both allowed and blocked globally.";
                String validation_duplicateblockedrecord = "Record Already Exists.";
                if (editbd_successmsg.getText().contentEquals(validation_duplicateblockedrecord)) {
                    addResultForTestCase("12466", TEST_CASE_PASSED_STATUS, "");
                }
            } catch (Exception e) {
                if (driver.findElement(By.id("companyModal")).isDisplayed()) {
                    addResultForTestCase("11563", TEST_CASE_PASSED_STATUS, "");
                    System.out.println("validation msg displays");
                }
            }
        }
    }

    public void search_BD(String ID, String DomainName, String Company, String Status, String AddDateFrom, String AddDateTo) throws Exception {
        bdgrid_id.sendKeys(ID);
        bdgrid_domainname.sendKeys(DomainName);
        bdgrid_company.sendKeys(Company);
        Select drp = new Select(bdgrid_status);
        drp.selectByVisibleText(Status);
        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        if (AddDateFrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", adddatefrom, AddDateFrom);
            Thread.sleep(3000);
            adddatefrom.sendKeys(AddDateFrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (AddDateTo.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", adddateto, AddDateTo);
            Thread.sleep(3000);
            adddateto.sendKeys(AddDateTo);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"blocked-domain-list\"]/tbody/tr/td[1]"), Pattern.compile(ID)));
        String expid = driver.findElement(By.xpath("//*[@id=\"blocked-domain-list\"]/tbody/tr/td[1]")).getText();
        if (expid.contentEquals(ID)){
            addResultForTestCase("11372", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11372", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void blockeddomain_delete() throws Exception {
        bd_deleteicon.click();
        Thread.sleep(3000);
        bd_cancelpopbtn.click();
        bd_deleteicon.click();
        Thread.sleep(3000);
        if (bd_deletepopbtn.isDisplayed()) {
            bd_deletepopbtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(editbd_successmsg));
            System.out.println(editbd_successmsg.getText());
            addResultForTestCase("11562", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11562", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void xicon_bd() {
        xicon_bd.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"blocked-domain-list\"]/tbody/tr[1]/td[6]/a[1]/i")
    public WebElement edition_bd;

    public void edit_bd() {
        edition_bd.click();
    }

    /*
   Initializing elements for Fax Blocklist
  */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/div/button/a")
    public WebElement faxblocklist_link;
    @FindBy(how = How.XPATH, using = "//*[@id=\"AddedDatefrom\"]")
    public WebElement adddatefrom;
    @FindBy(how = How.XPATH, using = "//*[@id=\"AddedDateto\"]")
    public WebElement adddateto;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fax-blocked-list\"]/tbody/tr[1]/td[4]/a[1]/i")  //fax edit icon
    public WebElement fax_editicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fax-blocked-list\"]/tbody/tr[1]/td[4]/a[2]/i")  //fax delete icon
    public WebElement fax_deleteicon;

    public void faxblocklist() {
        faxblocklist_link.click();
    }

    public void faxgridsearch(String ID, String FaxNumber, String AddDateFrom, String AddDateTo) throws Exception {
        bdgrid_id.sendKeys(ID);
        bdgrid_domainname.sendKeys(FaxNumber);
        Actions actions = new Actions(driver);
        if (AddDateFrom.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", adddatefrom, AddDateFrom);
            Thread.sleep(3000);
            adddatefrom.sendKeys(AddDateFrom);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (AddDateTo.isEmpty()) {
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1]", adddateto, AddDateTo);
            Thread.sleep(3000);
            adddateto.sendKeys(AddDateTo);
            actions.clickAndHold(datepicker).click(active_day).perform();
            Thread.sleep(3000);
        }
        if (driver.findElement(By.xpath("//*[@id=\"fax-blocked-list\"]/tbody/tr[1]/td[1]")).getText().contains(ID)) {
            testgoogle.addResultForTestCase("12014", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12014", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void editfax(String FaxNumber) throws Exception {
        fax_editicon.click();
        domainemail.clear();
        domainemail.sendKeys(FaxNumber);
        addResultForTestCase("11956", TEST_CASE_PASSED_STATUS, "");
    }

    public void deletefax() throws Exception {
        fax_deleteicon.click();
        Thread.sleep(3000);
        bd_cancelpopbtn.click();
        fax_deleteicon.click();
        Thread.sleep(3000);
        if (bd_deletepopbtn.isDisplayed()) {
            bd_deletepopbtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(editbd_successmsg));
            System.out.println(editbd_successmsg.getText());
            addResultForTestCase("11957", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11957", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void verifyfax() throws Exception {
        addblock_savebtn.click();
        if (driver.findElement(By.id("companyModal")).isDisplayed()) {
            addResultForTestCase("11955", TEST_CASE_PASSED_STATUS, "");  //fax validation
            System.out.println("validation msg displays");
        }
    }

    public void addbd_faxexistrecord() throws Exception {
        addblockeddomain_btn.click();
        Select drp = new Select(selblockedtype);
        drp.selectByVisibleText("Fax");
        domainemail.clear();
        domainemail.sendKeys("7 (875) 787-5454");
        addblock_savebtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(editbd_successmsg));
        System.out.println(editbd_successmsg.getText());
        String validation_duplicatefax = "Record already exists.";
        if (editbd_successmsg.getText().contentEquals(validation_duplicatefax)) {
            addResultForTestCase("12459", TEST_CASE_PASSED_STATUS, "");
        }
    }


    /*
    Initializing elements - Paper letter History
    */
    @FindBy(how = How.XPATH, using = "//*[@id=\"Paper\" and contains(text(), 'Paper Letter History')]")
    public WebElement paperletterhistory;
    @FindBy(how = How.ID, using = "RootCompany")
    public WebElement rootcomptxt;
    @FindBy(how = How.ID, using = "CompanyGroup")
    public WebElement compgrptxt;
    @FindBy(how = How.ID, using = "NoofLetters")
    public WebElement letterstxt;
    @FindBy(how = How.ID, using = "DateGeneratedfrom")
    public WebElement fromdate;
    @FindBy(how = How.ID, using = "DateGeneratedto")
    public WebElement fromto;
    @FindBy(how = How.ID, using = "Type")
    public WebElement lettertypedrp;
    @FindBy(how = How.ID, using = "CompanyType")
    public WebElement companytypedrp;
    @FindBy(how = How.ID, using = "SendAccount")
    public WebElement generatedBy;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/h6/a")
    public WebElement clearbtn_letters;

    public void letterhistory_PR() throws Exception {
        paperletterhistory.click();
        String actualurl = "https://certvault.dev.patracloud.com/paper-registrations/paper-letter-history";
        if (driver.getCurrentUrl().matches(actualurl)) {
            addResultForTestCase("11741", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11741", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void searchletterhisgrid(String RootComp, String CompanyGrp, String Letters, String DategenFrom, String DategenTo, String LetterType, String CompType, String GenBy) {
        rootcomptxt.sendKeys(RootComp);
        compgrptxt.sendKeys(CompanyGrp);
        letterstxt.sendKeys(Letters);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('DateGeneratedfrom').removeAttribute('readonly', 0);");
        fromdate.click();
        fromdate.sendKeys(DategenFrom);
        fromto.click();
        fromto.sendKeys(Keys.ENTER);
        lettertypedrp.sendKeys(LetterType);
        companytypedrp.sendKeys(CompType);
        generatedBy.sendKeys(GenBy);
    }

    public void clearbtn_lettershis() throws Exception {
        clearbtn_letters.click();
        if (rootcomptxt.getText().isEmpty()) {
            addResultForTestCase("11742", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11742", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(3000);
    }

    // Cert uploader
    @FindBy(how = How.XPATH, using = "//button[@id='dropdownMenu1']")
    public WebElement assignbtn;
    @FindBy(how = How.XPATH, using = "//ul[@id='certUploaderCompanyList']")
    public WebElement assigndropdown;
    @FindBy(how = How.XPATH, using = "//select[@class='form-control']")
    public WebElement switchdropdown;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[5]/a/select")
    public WebDriverWait drpswitchcomp;

    public void assigncompval() throws Exception {
        String assign_company_validation = "Please select at least one company below";
        if (driver.getPageSource().contains(assign_company_validation)) {
            String validation_assign_comp = driver.findElement(By.xpath("//span[@id='assignCompanyValidateMsg']")).getText();
            if (validation_assign_comp.contentEquals(assign_company_validation)) {
                System.out.println("validation text appeared is:" + validation_assign_comp);
                addResultForTestCase("11752", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("11752", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    public void assignCompanies(String assigncompany) throws Exception {
        if (assigndropdown.isDisplayed()) {
            List<WebElement> assigncompoptions = assigndropdown.findElements(By.tagName("label"));
            for (WebElement assigncompoption : assigncompoptions) {
                if (assigncompoption.getText().equals(assigncompany)) {
                    WebDriverWait wait = new WebDriverWait(driver, 100);
                    wait.until(ExpectedConditions.visibilityOf(assigncompoption));
                    Actions action = new Actions(driver);
                    action.clickAndHold(assigndropdown);
                    action.sendKeys(Keys.PAGE_DOWN).build().perform();
                    Thread.sleep(8000);
                    assigncompoption.click();
                    break;
                }
            }
        }
        addResultForTestCase("11761", TEST_CASE_PASSED_STATUS, "");
    }

    public void switchCompanies(String Switch_company) throws Exception {
        Boolean flag = true;
        List<WebElement> switchcompoptions = new ArrayList<>();
        switchcompoptions = switchdropdown.findElements(By.xpath("/html/body/div[2]/nav/ul/li[5]/a/select"));
        for (WebElement switchcompoption : switchcompoptions) {
            System.out.println("switch company list:" + switchcompoption.getText());
            if (switchcompoption.getText().contains(Switch_company)) {
                switchdropdown.click();
                Thread.sleep(3000);
                Select sel = new Select(driver.findElement(By.xpath("/html/body/div[2]/nav/ul/li[5]/a/select")));
                sel.selectByVisibleText(Switch_company);
                // switchcompoption.click();
                Thread.sleep(3000);
                addResultForTestCase("11754", TEST_CASE_PASSED_STATUS, "");
                break;
            } else {
                if (flag) break;
                flag = true;
                System.out.println("Existing Switch company list is:" + switchcompoption.getText() + "So, Selected Company doesn't exist in Switch company dropdown - cert uploader user");
                addResultForTestCase("11754", TEST_CASE_FAILED_STATUS, "");
            }
        }
    }

    //* Fax registration
    @FindBy(how = How.ID, using = "Faxr") //Fax Registration Letter button
    public WebElement Faxregbtn;
    @FindBy(how = How.XPATH, using = "//*[@class=\"effect-1 form-control\"]")    //enter fax in popup
    public WebElement enterfax;
    @FindBy(how = How.XPATH, using = "//input[@id='holderFax']")    //enter holder fax in popup
    public WebElement holderfax;
    @FindBy(how = How.XPATH, using = "//input[@id='insuredFax']")    //enter insured fax in popup
    public WebElement insuredfax;
    @FindBy(how = How.XPATH, using = "//span[@id='submitFaxRegistration']")  // Fax Reg pop-up Save btn
    public WebElement Faxpopupsavebtn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Remove Holder Fax')]")
    public WebElement holder_remove_fax_btn;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Remove Insured Fax')]")
    public WebElement insured_remove_fax_btn;
    @FindBy(how = How.XPATH, using = "//form[@id='AddUpdateFaxForm']//a[contains(text(),'x')]")
    public WebElement close_fax_btn;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Fax Registration Sent Successfully.')]")
    public WebElement faxsuccess;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Insured/Holder Fax is in Domain Blocklist, the Certificate(s) have been faxed.')]")
    public WebElement fax_block;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Fax Removed For Selected Unregistered User.')]")
    public WebElement remove_fax;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Please enter any one of the fax number to send Registration letter')]")
    public WebElement blank_fax;

    public void validate_faxreg() throws Exception {
        List<WebElement> fax = driver.findElements(By.id("Faxr"));
        if (fax.size() == 0) {
            out.println("Fax registration button is not visible");
            addResultForTestCase("1638", TEST_CASE_PASSED_STATUS, "");
        } else if (Faxregbtn.isDisplayed() && Faxregbtn.isEnabled()) {
            Faxregbtn.click();
            String textholder = holderfax.getAttribute("value");
            String textinsured = insuredfax.getAttribute("value");
            if (textholder.isEmpty() && textinsured.isEmpty()) {
                Faxpopupsavebtn.click();
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOf(blank_fax));
                out.println(blank_fax.getText());
                addResultForTestCase("11982", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("11982", TEST_CASE_FAILED_STATUS, "");
            }
            if (!textholder.isEmpty() || !textinsured.isEmpty()) {
                Thread.sleep(2000);
                enterfax.clear();
                enterfax.sendKeys("1212121"); //verifying invalid fax format
                Faxpopupsavebtn.click();
                Thread.sleep(2000);
                String faxinvalid = "Enter valid Fax Number.";
                if (driver.getPageSource().contains(faxinvalid)) {
                    out.println(faxinvalid);
                    Thread.sleep(3000);
                    addResultForTestCase("11981", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("11981", TEST_CASE_FAILED_STATUS, "");
                }
                if (close_fax_btn.isDisplayed()) {
                    close_fax_btn.click();
                    out.println("Fax Registration popup get closed");
                    addResultForTestCase("875", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("875", TEST_CASE_FAILED_STATUS, "");
                }
            }
        }
    }

    public void remove_fax() throws Exception {
        Faxregbtn.click();
        Thread.sleep(5000);
        List<WebElement> remove_holder_fax = driver.findElements(By.xpath("//button[contains(text(),'Remove Holder Fax')]"));
        List<WebElement> remove_insured_fax = driver.findElements(By.xpath("//button[contains(text(),'Remove Insured Fax')]"));
        if (remove_holder_fax.size() == 0) {
            out.println("Remove holder fax button is not visible");
        } else if (holder_remove_fax_btn.isDisplayed() || insured_remove_fax_btn.isDisplayed()) {
            holder_remove_fax_btn.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(remove_fax));
            out.println(remove_fax.getText());
            addResultForTestCase("11985", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11985", TEST_CASE_FAILED_STATUS, "");
        }
        if (remove_insured_fax.size() == 0) {
            out.println("Remove insured fax button is not visible");
        } else if (insured_remove_fax_btn.isDisplayed()) {
            insured_remove_fax_btn.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(remove_fax));
            out.println(remove_fax.getText());
            addResultForTestCase("11985", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11985", TEST_CASE_FAILED_STATUS, "");
        }
        if (remove_holder_fax.size() == 0 && remove_insured_fax.size() == 0) {
            close_fax_btn.click();
            out.println("Fax Registration popup get closed");
            Thread.sleep(2000);
        }
    }

    public void blockfaxreg(String Enter_Blocked_Fax) throws Exception {
        if (Faxregbtn.isDisplayed() && Faxregbtn.isEnabled()) {
            Faxregbtn.click();
            enterfax.clear();
            enterfax.sendKeys(Enter_Blocked_Fax);
            Thread.sleep(3000);
            Faxpopupsavebtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(fax_block));
            out.println(fax_block.getText());
            addResultForTestCase("11983", TEST_CASE_PASSED_STATUS, "");
            Thread.sleep(5000);
        } else {
            addResultForTestCase("11983", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void faxreg(String EnterFax) throws Exception {
        if (Faxregbtn.isDisplayed() && Faxregbtn.isEnabled()) {
            Faxregbtn.click();
            enterfax.clear();
            enterfax.sendKeys(EnterFax);
            Thread.sleep(3000);
            Faxpopupsavebtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(faxsuccess));
            out.println(faxsuccess.getText());
            Thread.sleep(5000);
            addResultForTestCase("11980", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11980", TEST_CASE_FAILED_STATUS, "");
        }
    }

    public void incorrectemail(String EnterEmail) throws Exception {
        if (Emailregletterbtn.isDisplayed() && Emailregletterbtn.isEnabled()) {
            Emailregletterbtn.click();
            Thread.sleep(2000);
            if (enteremail.isDisplayed()) {
                enteremail.clear();
                enteremail.sendKeys(EnterEmail); //verifying failure email C11673
                out.println("Entered failure email");
                Thread.sleep(5000);
                Emailpopupsavebtn.click();
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.visibilityOf(emailsuccess));
                out.println(emailsuccess.getText());
                Thread.sleep(5000);
                addResultForTestCase("11673", TEST_CASE_PASSED_STATUS, "");
            } else {
                addResultForTestCase("11673", TEST_CASE_FAILED_STATUS, "");
            }
        }
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        if (Emailcertbtn.isDisplayed()) {
            Emailcertbtn.click();
            Thread.sleep(4000);
            entercertemail.clear();
            entercertemail.sendKeys("abc@patracorp.net");
            Thread.sleep(4000);
            emailcertpopupsavebtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(emailcertsuccess));
            out.println(emailcertsuccess.getText());
            Thread.sleep(2000);
            addResultForTestCase("11673", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("11673", TEST_CASE_FAILED_STATUS, "");
        }
    }

    // OCR Process dropdown
    @FindBy(how = How.XPATH, using = "//label[@class='custom-select OCRProcessLabel']")
    public WebElement ocrdropdown;

    public void ocrType(String Ocr_type) throws Exception {
        if (ocrdropdown.isDisplayed()) {
            List<WebElement> ocroptions = ocrdropdown.findElements(By.tagName("option"));
            for (WebElement ocroption : ocroptions) {
                if (ocroption.getText().equals(Ocr_type)) {
                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(ExpectedConditions.visibilityOf(ocrdropdown));
                    ocroption.click();
                    Thread.sleep(5000);
                    Actions actions = new Actions(driver);
                    actions.click().perform();
                    addResultForTestCase("12143", TEST_CASE_PASSED_STATUS, "");
                    addcompanySavebtn.click();
                    break;
                }
            }
        } else {
            addResultForTestCase("12143", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
      Initializing elements - Registration Template
       */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[7]/div/a[9]")
    public WebElement registrationtemplate_link;

    public void registrationtemplate() {
        registrationtemplate_link.click();
        String expectedurl = "https://certvault.dev.patracloud.com/registrationTemplate";
        if (driver.getCurrentUrl().equals(expectedurl)) {
            out.println("Clicked registration templates menu");
        }
    }

    @FindBy(how = How.ID, using = "company-id")   //Company id
    public WebElement regtempgrid_companyid;
    @FindBy(how = How.ID, using = "company-name")   //Company name
    public WebElement regtempgrid_companyname;
    @FindBy(how = How.ID, using = "reciepentType")   //reciepent Type
    public WebElement regtempgrid_reciepenttype;
    @FindBy(how = How.ID, using = "templateEnabled")   //template Enabled
    public WebElement regtempgrid_templateEnabled;

    public void searchregistemplate(String CompanyId, String CompanyName, String RecipientType, String EnabledDisabled) throws Exception {
        regtempgrid_companyid.sendKeys(CompanyId);
        regtempgrid_companyname.sendKeys(CompanyName);
        Select drp1 = new Select(regtempgrid_reciepenttype);
        drp1.selectByVisibleText(RecipientType);
        Select drp2 = new Select(regtempgrid_templateEnabled);
        drp2.selectByVisibleText(EnabledDisabled);

        if (driver.findElement(By.xpath("//*[@id=\"manage-registrationtemplates-list\"]/tbody/tr[1]/td[2]")).getText().contains(CompanyName)) {
            System.out.println("performs grid search");
            testgoogle.addResultForTestCase("12603", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12603", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "clear-grid")   //clear all - registration template
    public WebElement clearbtn_regtempl;

    public void regtemp_clear() throws Exception {
        clearbtn_regtempl.click();
        if (regtempgrid_companyname.getText().isEmpty()) {
            testgoogle.addResultForTestCase("12604", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12604", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "add-Template")
    public WebElement addtemplate_btn;
    @FindBy(how = How.ID, using = "templateCompanyName")
    public WebElement add_companynamedrp;
    @FindBy(how = How.ID, using = "templateRecepientType")
    public WebElement add_receiptypedrp;
    @FindBy(how = How.ID, using = "submit-btn-registrationtemplate")
    public WebElement addtemp_savebtn;

    public void addregtemp(String CompanyName, String RecipientType) {
        addtemplate_btn.click();
        System.out.println("Clicks Add Template button");
        Select compname = new Select(add_companynamedrp);
        compname.selectByVisibleText(CompanyName);
        Select receiptype = new Select(add_receiptypedrp);
        receiptype.selectByVisibleText(RecipientType);
    }

    public void addtemplate_save() throws Exception {
        addtemp_savebtn.click();
        String success = "Registration template created successfully";
        String validation = "Template already exists with this company and recipient";
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(successvalid_merge));
        System.out.println(successvalid_merge.getText());
        if (driver.getPageSource().contains(success)) {
            testgoogle.addResultForTestCase("12605", TEST_CASE_PASSED_STATUS, "");
        }
        if (driver.getPageSource().contains(validation)) {
            testgoogle.addResultForTestCase("12606", TEST_CASE_PASSED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"manage-registrationtemplates-list\"]/tbody/tr[1]/td[5]/a")
    public WebElement editicon_regtemp;

    public void edit_regtemp() throws Exception {
        editicon_regtemp.click();
        System.out.println("Clicks edit icon - registration template");
        if (driver.getCurrentUrl().contains("https://certvault.dev.patracloud.com/registrationTemplate/edit/")) {
            testgoogle.addResultForTestCase("12607", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12607", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(3000);
    }

    @FindBy(how = How.ID, using = "savebtn")  //Save btn - template editor
    public WebElement tempeditor_savebtn;
    @FindBy(how = How.XPATH, using = " /html/body/div[5]/div[2]/p")  //success msg
    public WebElement tempeditor_successvalid;

    public void save_tempeditor() throws Exception {
        tempeditor_savebtn.click();
        String save_successmsg = "Registration Template details are saved.";
        String save_warningmsg = " Please fill all the fields.";
        String savelogo_warningmsg = " Logos positions cannot be same.";
        String logo_condition = " CertVault logo position cannot be Top-Right if the broker logo position is Middle.";

        if (driver.getPageSource().contains(save_successmsg)) {
            System.out.println("clicks save button -" + save_successmsg);
            testgoogle.addResultForTestCase("12608", TEST_CASE_PASSED_STATUS, "");
        }
        if (driver.getPageSource().contains(save_warningmsg)) {
            System.out.println("clicks save button -" + save_warningmsg);
            testgoogle.addResultForTestCase("12609", TEST_CASE_PASSED_STATUS, "");
        }
        if (driver.getPageSource().contains(savelogo_warningmsg)) {
            System.out.println("clicks save button -" + savelogo_warningmsg);
            testgoogle.addResultForTestCase("12661", TEST_CASE_PASSED_STATUS, "");
        }
        if (driver.getPageSource().contains(logo_condition)) {
            System.out.println("clicks save button -" + logo_condition);
            testgoogle.addResultForTestCase("12661", TEST_CASE_PASSED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "disableBtn")  //Disable btn - template editor
    public WebElement tempeditor_disablebtn;
    @FindBy(how = How.ID, using = "enableBtn")  //Enable btn - template editor
    public WebElement tempeditor_enablebtn;

    public void enabledisable_tempeditor() throws Exception {
        if (tempeditor_disablebtn.isDisplayed()) {
            System.out.println("clicks disable button");
            tempeditor_disablebtn.click();
        } else {
            System.out.println("clicks enable button");
            tempeditor_enablebtn.click();
        }

        if (driver.getPageSource().contains("Registration template")) {
            System.out.println(tempeditor_successvalid.getText());
            testgoogle.addResultForTestCase("12663", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12663", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"editorFrom\"]/div[1]/div[2]/a/button")  //cancel btn - template editor
    public WebElement tempeditor_cancelbtn;

    public void cancel_tempeditor() throws Exception {
        tempeditor_cancelbtn.click();
        String expectedurl = "https://certvault.dev.patracloud.com/registrationTemplate";
        if (driver.getCurrentUrl().contains(expectedurl)) {
            testgoogle.addResultForTestCase("12662", TEST_CASE_PASSED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "previewBtn")   //preview btn - template editor
    public WebElement tempeditor_previewbtn;
    @FindBy(how = How.ID, using = "prtpr")
    public WebElement tempeditor_previewpopup;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div/a")  //X btn preview pop-up - template editor
    public WebElement previewpop_xbtn;   //a[@class='close-btn popup-close'][contains(text(),'x')]

    public void preview_tempeditor() throws Exception {
        tempeditor_previewbtn.click();
        out.println("Clicks preview button");
        if (tempeditor_previewpopup.isDisplayed() && driver.getPageSource().contains("Registration Template Preview")) {
            testgoogle.addResultForTestCase("12664", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12664", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(3000);
        //  previewpop_xbtn.click();
        out.println("Clicks X in preview pop up");
        driver.navigate().back();
    }

    @FindBy(how = How.ID, using = "brokerLogoPosition")   //Broker logo position drp
    public WebElement brokerlogodrp;
    @FindBy(how = How.ID, using = "certvaultLogoPosition")  //CV logo position
    public WebElement cvlogodrp;

    public void setlogo(String BrokerLogo, String CvLogo) {
        Select brokerlogo = new Select(brokerlogodrp);
        brokerlogo.selectByVisibleText(BrokerLogo);
        Select cvlogo = new Select(cvlogodrp);
        cvlogo.selectByVisibleText(CvLogo);

    }

    public void email_claim_access_code() throws Exception {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000);
        List<WebElement> unreademail = driver.findElements(By.xpath("//span[@class='bog']//span[@class='bqe'][starts-with(text(),'CertVault - Claim Access Code Request')]"));
        String cert_email_sub = "CertVault - Claim Access Code Request";
        for (int i = 0; i < unreademail.size(); i++) {
            if (unreademail.get(i).isDisplayed() == true) {
                if (unreademail.get(i).getText().startsWith(cert_email_sub)) {
                    System.out.println("Yes we have got mail from " + cert_email_sub);
                    unreademail.get(i).click();
                    Thread.sleep(2000);
                    List<WebElement> el = driver.findElements(By.xpath("//img[@class='ajT']"));
                    if (el.size() == 0) {
                        System.out.println("Trimmed content icon not found");
                        Thread.sleep(5000);
                    } else if (content_spread.isDisplayed()) {
                        content_spread.click();
                        Thread.sleep(5000);
                    }
                    if (back_to_inbox.isDisplayed()) {
                        back_to_inbox.click();
                        Thread.sleep(5000);
                        break;
                    }
                } else {
                    System.out.println("No mail from " + cert_email_sub);
                    Thread.sleep(5000);
                }
            }
        }
        addResultForTestCase("12355", TEST_CASE_PASSED_STATUS, "");
        addResultForTestCase("12436", TEST_CASE_PASSED_STATUS, "");
        driver.switchTo().window(tabs.get(0));
    }

    public void company_group() throws Exception {
        if (companygroup.isDisplayed()) {
            out.println("Company group is:" + companygroup.getText());
            addResultForTestCase("5220", TEST_CASE_PASSED_STATUS, "");
        } else {
            addResultForTestCase("5220", TEST_CASE_FAILED_STATUS, "");
        }
    }

    /*
   Initializing elements - Import Utility page
    */
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/nav/ul/li[7]/div/a[8]")  //Import Utility tab
    public WebElement Importutility_link;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div[4]/div/button")
    public WebElement warningcsv_Okbtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"Import_History\"]")
    public WebElement importhistory_link;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/section[1]/div/div/div[2]/h6/a")
    public WebElement importhis_clearbtn;
    @FindBy(how = How.ID, using = "ImportID")
    public WebElement grid_importid;
    @FindBy(how = How.ID, using = "FileName")
    public WebElement grid_filename;
    @FindBy(how = How.ID, using = "DateGeneratedfrom")
    public WebElement grid_fromdate;
    @FindBy(how = How.ID, using = "DateGeneratedto")
    public WebElement grid_todate;
    @FindBy(how = How.ID, using = "BrokerCompany")
    public WebElement grid_brokercomp;
    @FindBy(how = How.ID, using = "UploadedBy")
    public WebElement grid_uploadedby;
    @FindBy(how = How.ID, using = "Status")
    public WebElement grid_status;
    @FindBy(how = How.ID, using = "upload-btn-holder")  //Import Holder
    public WebElement importholder_btn;
    @FindBy(how = How.ID, using = "upload-btn-user")  //Import User
    public WebElement importuser_btn;

    public void importutility_tab() {
        Importutility_link.click();
    }

    public void importholder(){
        importholder_btn.click();
        String expurl = "https://certvault.dev.patracloud.com/importUtility/importholders";
        if(driver.getCurrentUrl().matches(expurl)) {
            out.println("Import holder URL matched");
        }
    }
    public void importuser(){
        importuser_btn.click();
        String expurl = "https://certvault.dev.patracloud.com/importUtility/importUsers";
        if(driver.getCurrentUrl().matches(expurl)) {
            out.println("Import user URL matched");
        }
    }

    public void csvsuccess() throws Exception {
        popbtnOK.click();
        testgoogle.addResultForTestCase("12447", TEST_CASE_PASSED_STATUS, ""); //import holder
    }

    public void csvvalidationpop() throws Exception {
        warningcsv_Okbtn.click();
        testgoogle.addResultForTestCase("12448", TEST_CASE_PASSED_STATUS, "");
    }

    public void importhistory() throws Exception {
        importhistory_link.click();
        String expurl = "https://certvault.dev.patracloud.com/importUtility/importHistory";
        if (driver.getCurrentUrl().contentEquals(expurl)) {
            testgoogle.addResultForTestCase("12450", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void gridsearch_imphistory(String ImportID, String FileName, String FromDate, String ToDate, String BrokerCompany, String UploadedBy, String Status) {
        grid_importid.sendKeys(ImportID);
        grid_filename.sendKeys(FileName);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('DateGeneratedfrom').removeAttribute('readonly', 0);");
        grid_fromdate.sendKeys(FromDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", grid_fromdate);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('DateGeneratedto').removeAttribute('readonly', 0);");
        grid_todate.sendKeys(ToDate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", grid_todate);
        grid_brokercomp.sendKeys(BrokerCompany);

           /* try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                Thread.sleep(3000);
                alert.accept();
                String alerttext = alert.getText();
                System.out.println("Alert data: " + alerttext);
            } catch (NoAlertPresentException | InterruptedException e) {
                e.printStackTrace();
            }*/
        grid_uploadedby.sendKeys(UploadedBy);
        grid_status.sendKeys(Status);
    }

    public void clear_importhistory() throws Exception {
        importhis_clearbtn.click();
        if (grid_importid.getText().isEmpty()) {
            testgoogle.addResultForTestCase("12451", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void validate_emailblank() throws Exception {
        if (Emailregletterbtn.isDisplayed() && Emailregletterbtn.isEnabled()) {
            Emailregletterbtn.click();
            String emailtextholder = Emailholdertext.getAttribute("value");
            String emailtextinsured = Emailinsuredtext.getAttribute("value");
            if (emailtextholder.isEmpty() && emailtextinsured.isEmpty()) {
                if (add_holder.isDisplayed()) {
                    add_holder.click();
                    String primary_hol_val = "Please enter primary holder email address to add extra holder email address";
                    if (driver.getPageSource().contains(primary_hol_val)) {
                        out.println(primary_hol_val);
                        Thread.sleep(5000);
                    }
                }
                if (add_insured.isDisplayed()) {
                    add_insured.click();
                    String primary_ins_val = "Please enter primary insured email address to add extra Insured email address";
                    if (driver.getPageSource().contains(primary_ins_val)) {
                        out.println(primary_ins_val);
                        Thread.sleep(5000);
                    }
                }
                if (Emailpopupsavebtn.isDisplayed()) {
                    Emailpopupsavebtn.click();
                    WebDriverWait wait = new WebDriverWait(driver, 30);
                    wait.until(ExpectedConditions.visibilityOf(blank_email));
                    out.println(blank_email.getText());
                    addResultForTestCase("873", TEST_CASE_PASSED_STATUS, "");
                } else {
                    addResultForTestCase("873", TEST_CASE_FAILED_STATUS, "");
                }
            } else {
                emailregppupclosebtn.click();
            }
        }
    }

    // view certficate OMIC details
    @FindBy(how = How.XPATH, using = "//div[@class='textLayer']")   //PDF cert details
    public WebElement cert_page;
    @FindBy(how = How.XPATH, using = "//div[@class='view-div']/div[@class='row']/div[1]/table[1]/tbody[1]/tr[2]")
    //Holder company detail
    public WebElement view_holder;
    @FindBy(how = How.XPATH, using = "//div[@class='view-div']/div[@class='row']/div[2]/table[1]/tbody[1]/tr[1]")
    //Insured company detail
    public WebElement view_insured;
    @FindBy(how = How.XPATH, using = "//div[@class='view-div']/div[@class='row']/div[1]/table[1]/tbody[1]/tr[1]")
    //Broker company detail
    public WebElement view_broker;
    @FindBy(how = How.XPATH, using = "//div[@class='view-div']/div[@class='row']/div[1]/table[1]/tbody[1]/tr[3]")
    //PDF issue date
    public WebElement view_issue_date;
    @FindBy(how = How.XPATH, using = "//div[@class='view-div']/div[@class='row']/div[2]/table[1]/tbody[1]/tr[3]")
    //PDF expiration date
    public WebElement view_expiration_date;

    public void validate_omic() throws Exception {
        String expectedurl = "https://certvault.dev.patracloud.com/certs/view/";
        if (driver.getCurrentUrl().contains(expectedurl)) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.switchTo().frame("cert_iframe");
            Thread.sleep(5000);
            if (cert_page.isDisplayed()) {
                String PDF_text = cert_page.getText();
                driver.switchTo().defaultContent();
                Thread.sleep(5000);
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
                if (view_broker.isDisplayed()) {
                    PDF_text.compareTo(view_broker.getText());
                    Thread.sleep(5000);
                    out.println(view_broker.getText());
                    addResultForTestCase("12473", TEST_CASE_PASSED_STATUS, "");
                }
                if (view_insured.isDisplayed()) {
                    PDF_text.compareTo(view_insured.getText());
                    Thread.sleep(5000);
                    out.println(view_insured.getText());
                    addResultForTestCase("12472", TEST_CASE_PASSED_STATUS, "");
                }
                if (view_holder.isDisplayed()) {
                    PDF_text.compareTo(view_holder.getText());
                    Thread.sleep(5000);
                    out.println(view_holder.getText());
                    addResultForTestCase("12470", TEST_CASE_PASSED_STATUS, "");
                }
                if (view_issue_date.isDisplayed()) {
                    PDF_text.compareTo(view_issue_date.getText());
                    Thread.sleep(5000);
                    out.println(view_issue_date.getText());
                    if (view_expiration_date.isDisplayed()) {
                        PDF_text.compareTo(view_expiration_date.getText());
                        Thread.sleep(5000);
                        out.println(view_expiration_date.getText());
                        addResultForTestCase("12474", TEST_CASE_PASSED_STATUS, "");
                    }
                }
            }
        } else {
            out.println("When exception found page redirects to certificate page / When renewal is uploaded page remains on upload cert");
        }
    }

    // post cert upload popup
    @FindBy(how = How.ID, using = "holderEmail")   //holder email
    public WebElement holder_email_text;
    @FindBy(how = How.ID, using = "insuredEmail")   //insured email
    public WebElement insured_email_text;
    @FindBy(how = How.ID, using = "addHolderEmailAddress")   //add holder email
    public WebElement add_holder_email_btn;
    @FindBy(how = How.ID, using = "addInsuredEmailAddress")   //add insured email
    public WebElement add_insured_email_btn;
    @FindBy(how = How.ID, using = "holderEmailAddress")   //extra add holder email
    public WebElement extra_add_holder_email_text;
    @FindBy(how = How.ID, using = "insuredEmailAddress")   //extra add insured email
    public WebElement extra_add_insured_email_text;
    @FindBy(how = How.ID, using = "insureFaxCheckbox")   //insured fax btn
    public WebElement Insured_faxbtn;
    @FindBy(how = How.XPATH, using = "//button[@onclick='holderFaxSelect(event)']")   //holder fax btn
    public WebElement Holder_faxbtn;

    public void Enter_Email(String Email) throws Exception {
        if (driver.findElement(By.id("em")).isDisplayed()) {
            if (holder_email_text.isDisplayed()) {
                String emailtextholderemt = holder_email_text.getAttribute("value");
                if (emailtextholderemt.isEmpty()) {
                    holder_email_text.sendKeys(Email);
                    test.testgoogle.addResultForTestCase("11760", TEST_CASE_PASSED_STATUS, "");
                } else if (!holder_email_text.toString().isEmpty()) {
                    if (add_holder_email_btn.isDisplayed()) {
                        add_holder_email_btn.click();
                        extra_add_holder_email_text.sendKeys(Email);
                        test.testgoogle.addResultForTestCase("11760", TEST_CASE_PASSED_STATUS, "");
                    }
                }
            }
            if (insured_email_text.isDisplayed()) {
                String emailtextinsuredemt = insured_email_text.getAttribute("value");
                if (emailtextinsuredemt.isEmpty()) {
                    insured_email_text.sendKeys(Email);
                    test.testgoogle.addResultForTestCase("11760", TEST_CASE_PASSED_STATUS, "");
                } else if (!insured_email_text.toString().isEmpty()) {
                    if (add_insured_email_btn.isDisplayed()) {
                        add_insured_email_btn.click();
                        extra_add_insured_email_text.sendKeys(Email);
                        test.testgoogle.addResultForTestCase("11760", TEST_CASE_PASSED_STATUS, "");
                    }
                }
            }
        }else {
            out.println("Cert upload popup is not display");
        }
    }

    public void Enter_Fax(String Fax) throws Exception {
        if (driver.findElement(By.id("em")).isDisplayed()) {
            if (Insured_faxbtn.isDisplayed() || Holder_faxbtn.isDisplayed()) {
                if (Insured_faxbtn.isDisplayed()) {
                    Insured_faxbtn.click();
                    driver.findElement(By.xpath("//input[@placeholder='Insured Fax Number']")).sendKeys(Fax);
                    test.testgoogle.addResultForTestCase("11984", TEST_CASE_PASSED_STATUS, "");
                } else if (Holder_faxbtn.isDisplayed()) {
                    Holder_faxbtn.click();
                    driver.findElement(By.xpath("//input[@placeholder='Holder Fax Number']")).sendKeys(Fax);
                    test.testgoogle.addResultForTestCase("11984", TEST_CASE_PASSED_STATUS, "");
                } else if (!Insured_faxbtn.isDisplayed() && !Holder_faxbtn.isDisplayed()) {
                    out.println("Fax Insured/Holder switch button not display");
                    test.testgoogle.addResultForTestCase("11984", TEST_CASE_FAILED_STATUS, "");
                }
            }
        }else {
            out.println("Cert upload popup is not display");
        }
    }
          /*
          Initializing elements - SMTP Configuration
           */
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'SMTP Configuration')]")
    public WebElement SMTP_Configuration_link;

    public void smtp_config() {
        SMTP_Configuration_link.click();
        String expectedurl = "https://certvault.dev.patracloud.com/smtpConfig";
        if (driver.getCurrentUrl().equals(expectedurl)) {
            out.println("Clicked SMTP Configuration menu");
        }
    }

    @FindBy(how = How.ID, using = "company-id")   //Company id
    public WebElement smtpconfig_grid_companyid;
    @FindBy(how = How.ID, using = "company-name")   //Company name
    public WebElement smtpconfig_grid_companyname;
    @FindBy(how = How.ID, using = "templateEnabled")   //template Enabled
    public WebElement smtpconfig_grid_Enabled;

    public void searchsmtpconfig(String CompanyId, String CompanyName, String EnabledDisabled) throws Exception {
        smtpconfig_grid_companyid.sendKeys(CompanyId);
        smtpconfig_grid_companyname.sendKeys(CompanyName);
        Select drp2 = new Select(smtpconfig_grid_Enabled);
        drp2.selectByVisibleText(EnabledDisabled);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"manage-smtp-list\"]/tbody/tr/td[2]"), Pattern.compile(CompanyName)));
        String comp_name = driver.findElement(By.xpath("//*[@id=\"manage-smtp-list\"]/tbody/tr/td[2]")).getText();
        if (comp_name.contentEquals(CompanyName)) {
            System.out.println("performs grid search");
            testgoogle.addResultForTestCase("12723", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12723", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "clear-grid")   //clear all - smtp configuration
    public WebElement clearbtn_smtpconfig;

    public void smtpconfig_clear() throws Exception {
        if (clearbtn_smtpconfig.isDisplayed()) {
            clearbtn_smtpconfig.click();
            testgoogle.addResultForTestCase("12724", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("12724", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @FindBy(how = How.ID, using = "add-Template")   //add- smtp configuration
    public WebElement add_smtpconfig_btn;

    public void add_smtp_config() {
        add_smtpconfig_btn.click();
    }

    @FindBy(how = How.ID, using = "CompanyName")   //select company
    public WebElement select_comp;
    @FindBy(how = How.ID, using = "SMTP")   // smtp host
    public WebElement smtp_host;
    @FindBy(how = How.ID, using = "UserName")   // smtp username
    public WebElement smtp_username;
    @FindBy(how = How.ID, using = "EncryptedPassword")   // smtp password
    public WebElement smtp_password;
    @FindBy(how = How.ID, using = "OutboundPort")   // outbound port
    public WebElement outbound_port;
    @FindBy(how = How.ID, using = "EncryptionMethod")   // Encryption Method
    public WebElement encryption_mtd;
    @FindBy(how = How.XPATH, using = "//label[@class='label16px']")   //  disable/enable smtp
    public WebElement disable_smptconfig;
    @FindBy(how = How.ID, using = "submit-btn-smtpDetails")   //  save button
    public WebElement smtpconfig_savebtn;
    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[4]/div[2]/p[1]")   //  success msg
    public WebElement smtpconfig_successmsg;
    @FindBy(how = How.XPATH, using = "//p[contains(text(),'Configuration already exists with this company')]")   //  error msg
    public WebElement smtpconfig_errorsmsg;
    @FindBy(how = How.XPATH, using = "//div[@class='col-md-12 CV-popupTitl-Styl row']")   //  popup title
    public WebElement smtpconfig_popuptile;
    @FindBy(how = How.XPATH, using = "//table[@id='manage-smtp-list']/tbody/tr/td[4]/a")   //   edit icon
    public WebElement editicon_sc;
    @FindBy(how = How.ID, using = "broker-list-modal-close")   //   close button
    public WebElement xicon_sc;

    public void filladdsmtpconfig(String Select_Company, String Smtp_Host, String Smtp_Username, String Password, String Outbound_Port, String Encryption_Method) {
        Select comp_drp = new Select(select_comp);
        comp_drp.selectByVisibleText(Select_Company);
        smtp_host.clear();
        smtp_host.sendKeys(Smtp_Host);
        smtp_username.sendKeys(Smtp_Username);
        smtp_password.sendKeys(Password);
        outbound_port.sendKeys(Outbound_Port);
        Select mtd_drp = new Select(encryption_mtd);
        mtd_drp.selectByVisibleText(Encryption_Method);
    }

    public void disablesmtpconfig() throws InterruptedException {
        if (disable_smptconfig.isDisplayed()) {
            disable_smptconfig.click();
        }
    }

    public void addsc_savebtn() throws Exception {
        if (smtpconfig_savebtn.isDisplayed()) {
            smtpconfig_savebtn.click();
            try {
                String validation_duplicatesc = "Configuration already exists with this company.";
                String success_msg = "Configuration Updated successfully.";
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.visibilityOf(smtpconfig_successmsg));
                if (validation_duplicatesc.contains(smtpconfig_successmsg.getText())) {
                    System.out.println(smtpconfig_successmsg.getText());
                    addResultForTestCase("12858", TEST_CASE_PASSED_STATUS, "");
                } else if (success_msg.contains(smtpconfig_successmsg.getText())) {
                    System.out.println(smtpconfig_successmsg.getText());
                    addResultForTestCase("12725", TEST_CASE_PASSED_STATUS, "");
                    addResultForTestCase("12852", TEST_CASE_PASSED_STATUS, "");
                }
                Thread.sleep(3000);
            } catch (Exception e) {
                if (smtpconfig_popuptile.isDisplayed()) {
                    System.out.println("validation msg displays");
                    addResultForTestCase("12726", TEST_CASE_PASSED_STATUS, "");
                }
            }
        }
    }

    public void edit_sc() throws InterruptedException {
        editicon_sc.click();
        Thread.sleep(5000);
        smtp_username.clear();
    }

    public void xicon_sc() throws Exception {
        if (xicon_sc.isDisplayed()){
            String update_sc = "Update SMTP Configuration";
            String add_sc = "Add SMTP Configuration";
            if (add_sc.contains(smtpconfig_popuptile.getText())){
                xicon_sc.click();
                addResultForTestCase("12853", TEST_CASE_PASSED_STATUS, "");
            }else if (update_sc.contains(smtpconfig_popuptile.getText())) {
                xicon_sc.click();
                addResultForTestCase("12854", TEST_CASE_PASSED_STATUS, "");
            }
        }
    }

    /*
    Initializing elements - Agency API Config
    */
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'dropdown-item nav-link')][contains(text(),'Agency API Configuration')]")
    public WebElement agencyapi_link;
    @FindBy(how = How.ID, using = "add-Template")
    public WebElement addapiconf_btn;
    @FindBy(how = How.ID, using = "companyName")
    public WebElement companyname_drp;
    @FindBy(how = How.ID, using = "APIName")
    public WebElement apiname;
    @FindBy(how = How.ID, using = "EndPoint")
    public WebElement endpoint;
    @FindBy(how = How.ID, using = "addNextParameter")  //Add new Parameter
    public WebElement addnextparam_btn;
    @FindBy(how = How.ID, using = "APIParamKey")  //Key
    public WebElement apikey;
    @FindBy(how = How.ID, using = "APIParamVal")  //Value
    public WebElement apiValue;
    @FindBy(how = How.XPATH, using = "//*[@id=\"addAPIParameterDiv\"]/div/div[3]/a/i")
    public WebElement api_deleteicon;
    @FindBy(how = How.XPATH, using = "//*[@id=\"apiDisabledDiv\"]/div/label/span/span")  //Disabled
    public WebElement disable_chckbox;
    @FindBy(how = How.ID, using = "submit-btn-APIDetails")
    public WebElement save_btn;
    @FindBy(how = How.XPATH, using = "//a[@title='Edit API Configuration']")
    public WebElement editicon_api;
    @FindBy(how = How.XPATH, using = "//*[@id=\"broker-list-modal-close\"]")  //X icon
    public WebElement api_x_icon;

    public void agencyapiconf() {
        agencyapi_link.click();
        String expurl = "https://certvault.dev.patracloud.com/agencyAPIconfiguration";
        if(driver.getCurrentUrl().matches(expurl)){
            System.out.println("Navigated to agency api config page");
        }
    }

    public void addAPIConfig(String CompanyName, String APIName, String EndPointurl, String Key, String Value) throws Exception {
        addapiconf_btn.click();
        Thread.sleep(2000);
        Select drp = new Select(companyname_drp);
        drp.selectByVisibleText(CompanyName);
        apiname.sendKeys(APIName);
        endpoint.sendKeys(EndPointurl);
        addnextparam_btn.click();
        apikey.sendKeys(Key);
        apiValue.sendKeys(Value);
        api_deleteicon.click();
        disable_chckbox.click();
        Thread.sleep(3000);
        save_btn.click();
        String apisucess = "Configuration Updated successfully.";
        String apierror = "Configuration already exists with this company";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(successvalid_merge));
        if(successvalid_merge.getText().contains(apisucess)) {
            testgoogle.addResultForTestCase("14315", TEST_CASE_PASSED_STATUS, "");
        } else if(successvalid_merge.getText().contains(apierror)) {
            testgoogle.addResultForTestCase("14317", TEST_CASE_PASSED_STATUS, "");
            System.out.println(apierror);
        }
    }

    public void editapi() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(editicon_api));
        editicon_api.click();
        Thread.sleep(3000);
        disable_chckbox.click();
        save_btn.click();
        Thread.sleep(3000);
        String editapisucess = "Configuration Updated successfully.";
        wait.until(ExpectedConditions.visibilityOf(successvalid_merge));
        if(successvalid_merge.getText().contains(editapisucess)) {
            testgoogle.addResultForTestCase("14316", TEST_CASE_PASSED_STATUS, "");
        }
    }

    public void api_validation() throws Exception {
        addapiconf_btn.click();
        save_btn.click();
        api_x_icon.click();
        Thread.sleep(2000);
        editicon_api.click();
        Thread.sleep(2000);
        apiname.clear();
        addnextparam_btn.click();
        save_btn.click();
        if(driver.getPageSource().contains("Update API Configuration")) {
            testgoogle.addResultForTestCase("14317", TEST_CASE_PASSED_STATUS, "");
        }
        api_x_icon.click();
    }

    /* Initializing elements - agency api header grid search */
    @FindBy(how = How.ID, using = "company-id")  //company ID
    public WebElement srch_companyid;
    @FindBy(how = How.ID, using = "company-name")  //company Name
    public WebElement srch_companyname;
    @FindBy(how = How.ID, using = "templateEnabled")  //Enabled/Disabed
    public WebElement srch_enableddisabled_drp;
 //   @FindBy(how = How.XPATH, using = "//*[@id=\"company-id\"]")  //API Name
 //   public WebElement srch_APIname;

    public void APIgridsearch(String CompID, String CompName, String EnableDisable, String ApiName) throws Exception {
        srch_companyid.sendKeys(CompID);
        srch_companyname.sendKeys(CompName);
        srch_enableddisabled_drp.sendKeys(EnableDisable);
      //  srch_APIname.sendKeys(ApiName);
        String expcompid = driver.findElement(By.xpath("//*[@id=\"agency-API-list\"]/tbody/tr[1]/td[1]")).getText();
        if(expcompid.contentEquals(CompID)){
            testgoogle.addResultForTestCase("14359", TEST_CASE_PASSED_STATUS, "");
        }
    }
}


