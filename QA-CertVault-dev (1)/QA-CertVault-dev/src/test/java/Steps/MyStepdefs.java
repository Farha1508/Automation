package Steps;

import Base.BaseUtil;
import Pages.Loginpage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.testgoogle;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.System.out;
import static test.testgoogle.*;


public class MyStepdefs extends BaseUtil {
    // public static String iSelectCertVaultAppAndCreateATestrun;
    private boolean aBoolean;
    private BaseUtil base;

    ExtentReports MyStepdefs;
    ExtentTest logger;

    public MyStepdefs(BaseUtil base) throws IOException {
        this.base = base;
    }

    public static String attachPath(String relativepath) {
        File file = new File(relativepath);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    //  String newTab1 ;
    private static String newTab1 = driver.getCurrentUrl();

    @Given("^I open browser \"([^\"]*)\"$")
    public void iOpenBrowser(String browser) throws Throwable {
        Thread.sleep(3000);
        driver.quit();
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", chromedriverlocation);
                System.out.println("Opening Chrome browser");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", geckodriverlocation);
                System.out.println("Opening Firefox browser");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", edgedriverlocation);
                System.out.println("Opening Edge browser");
                driver = new EdgeDriver();
                break;
            default:
                System.exit(1);
        }
    }

    @Given("^I navigate to login page of cert vault$")
    public void iNavigateToLoginPageOfCertvault() throws Throwable {
        base.driver.get("https://certvault.dev.patracloud.com/");
        base.driver.manage().window().maximize();
        //     scenarioDef.createNode(new GherkinKeyword("Given"), "I navigate to login page of cert vault");
        out.println("Navigates to CertVault Login page");
        Thread.sleep(3000);
    }

    @And("^I click Home link$")
    public void iClickHomeLink() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.home();
        out.println("Clicks Home link");
        Thread.sleep(5000);
    }

    @And("^I click How It Works link$")
    public void iClickHowItWorksLink() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.howitworks();
        out.println("Clicks How it works link");
        Thread.sleep(5000);
    }

    @And("^I click FAQ link$")
    public void iClickFAQLink() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.FAQ();
        out.println("Clicks FAQ link");
        Thread.sleep(10000);
    }

    @And("^I click Contact Us link$")
    public void iClickContactUsLink() throws Throwable {
        out.println("Clicks Contact Us link");
        Loginpage page = new Loginpage(base.driver);
        page.contactus();
        Thread.sleep(5000);
    }

    @And("^I enter name, company and email to contact them$")
    public void iEnterNameCompanyAndEmailToContactThem() throws Throwable {
        out.println("Enter details");
        Loginpage page = new Loginpage(base.driver);
        page.contactdetails();
        Thread.sleep(3000);
    }

    @And("^I click SignIn/Register button$")
    public void iClickSignInRegisterButton() throws Throwable {
        //  scenarioDef.createNode(new GherkinKeyword("And"), "And I click SignIn/Register button");
        out.println("Clicks SignIn/Register button");
        Loginpage page = new Loginpage(base.driver);
        page.signin();
        Thread.sleep(5000);
    }

    @And("^I enter login credentials$")
    public void iEnterLoginCredentials(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I enter login credentials");
        List<SignIn> users = new ArrayList<SignIn>();      //create arraylst
        users = table.asList(SignIn.class);
        Loginpage page = new Loginpage(base.driver);
        for (SignIn user : users) {
            page.login(user.email, user.password);
            out.println("Entering email address and password");
        }
    }
    //Declaring variables //
    public class SignIn {
        public String email, password;

        public SignIn(String Email, String Password) {
            email = Email;
            password = Password;
        }
    }

    @And("^I click on View Certs btn$")
    public void iClickOnViewCertsBtn() throws Throwable {
        out.println("Clicks View Certs btn in Support grid");
        Loginpage page = new Loginpage(base.driver);
        page.supportviewcert();
        Thread.sleep(5000);
    }

    @And("^I perform click events in Action column$")
    public void iPerformClickEventsInActionColumn() throws Throwable {
        out.println("Clicks actions btn in Support grid");
        Loginpage page = new Loginpage(base.driver);
        try {
            base.driver.findElement(By.xpath("//*[contains(@title, 'Paper Registration')]")).isDisplayed();
            page.supportpaperregisbtn();
            Thread.sleep(8000);
        } catch (Exception e) {
            out.println("Action column : paper registration icon doesn't exist for cert selected");
        }
        try {
            base.driver.findElement(By.xpath("//*[contains(@title, 'Extend Expiration Date')]")).isDisplayed();
            out.println("Action column : Extend Expiration Date icon is available for support unregistered account");
            page.extendexpiration();
            Thread.sleep(3000);
        } catch (Exception e) {
            out.println("Action column : Extend Expiration Date icon is not available in support");
        }
        try {
            base.driver.findElement(By.xpath("//*[contains(@title, 'Email Registration')]")).isDisplayed();
            page.supportemailreg();
            Thread.sleep(8000);
        } catch (Exception e) {
            out.println("Action column : email registration icon doesn't exist for cert selected");
        }
    }

    @And("^I perform support buttons events available for that cert$")
    public void iPerformSupportButtonsEventsAvailableForThatCert() throws Throwable {
        out.println("perform click Button events in support view cert");
        Loginpage page = new Loginpage(base.driver);
        try {
            //   WebElement PRL = base.driver.findElement(By.xpath("//html/body/div[2]/section/div/div[1]/div[3]/button[1]/span/a"));
            WebElement PRL = base.driver.findElement(By.xpath("//*[@id=\"Paper\"]"));
            //   JavascriptExecutor js = (JavascriptExecutor) driver;
            //   js.executeScript("arguments[0].scrollIntoView();",PRL);
            if (PRL.isDisplayed()) {
                out.println("Clicks paper registration button");
                page.supportPRLbtn();
                Thread.sleep(5000);
            }
            //   base.driver.findElement(By.xpath("/html/body/div[2]/section/div/div[1]/div[3]/button[1]")).isDisplayed();
        } catch (Exception e) {
            out.println("paper registration button doesn't exist");
        }
        try {
            base.driver.findElement(By.xpath("//*[@id=\"Emailr\"]")).isDisplayed();
            out.println("clicks email registration button");
            page.supportERLbtn();
            Thread.sleep(5000);
        } catch (Exception e) {
            out.println("email registration button doesn't exist");
        }
        try {
            base.driver.findElement(By.xpath("//*[@id=\"Emailc\"]")).isDisplayed();
            page.supportECbtn();
            Thread.sleep(5000);
        } catch (Exception e) {
            out.println("Email Cert btn doesn't exist");
        }
    }

    @When("^I search with columns in support view cert grid$")
    public void iSearchWithColumnsInSupportViewCertGrid(DataTable table) throws Throwable {
        List<supportcertsearch> users = new ArrayList<>();
        users = table.asList(supportcertsearch.class);
        out.println("Searches record in support view cert");
        Loginpage page = new Loginpage(base.driver);
        for (supportcertsearch search : users) {
            page.support_searchcert(search.certid, search.insured, search.holder, search.issuer, search.expirationdatefrom, search.expirationdateto,
                    search.issuedfrom, search.issuedto, search.uploaddatefrom, search.uploaddateto);
        }
        Thread.sleep(5000);
    }

    public class supportcertsearch {
        public String certid, insured, holder, issuer, expirationdatefrom, expirationdateto, issuedfrom, issuedto, uploaddatefrom, uploaddateto;

        public supportcertsearch(String CertId, String Insured, String Holder, String Issuer, String ExpirationDateFrom, String ExpirationDateTo,
                                 String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo) {
            certid = CertId;
            insured = Insured;
            holder = Holder;
            issuer = Issuer;
            expirationdatefrom = ExpirationDateFrom;
            expirationdateto = ExpirationDateTo;
            issuedfrom = IssuedFrom;
            issuedto = IssuedTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
        }
    }

    @And("^I click on Sign In button$")
    public void iClickOnSignInButton() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on Sign In button");
        out.println("Clicks Sign In button");
        Loginpage page = new Loginpage(base.driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        page.signInButton();
        Thread.sleep(5000);
    }

    @When("^I click on logo in top header$")
    public void iClickOnLogoInTopHeader() throws Throwable {
        if (base.driver.findElement(By.xpath("/html/body/div[2]/nav/div[1]/a/img[1]")).isDisplayed()) {
            base.driver.findElement(By.xpath("/html/body/div[2]/nav/div[1]/a/img[1]")).click();
            out.println("Clicks CertVault logo");
            test.testgoogle.addResultForTestCase("835", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("835", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(5000);
    }

    @And("^i get Files count and upload pdfs in folder$")
    public void iGetFilesCountAndUploadPdfsInFolder() throws Throwable {
        // File directory = new File("D:\\DESKTOP BACKUP FILES\\TEST\\Mail Merge\\Mail Merge");
        File directory = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Mail Merge");
        int FileCount;
        List<String> fileNames = new ArrayList();
        FileCount = directory.list().length;
        out.println("Total Files Count in Folder is =" + FileCount);
        Loginpage page = new Loginpage(base.driver);
        page.upload();

        DirectoryStream<Path> directoryStream;
        directoryStream = Files.newDirectoryStream(Paths.get("src/test/resources/Mail Merge"));

        for (Path path : directoryStream) {
            DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy h-m-s-S");
            Date date = new Date();

            page.upload();
            //fileNames.add(path.toString());
            out.println("Choose a file to Upload");
            base.driver.findElement(By.id("pdf-file")).sendKeys(attachPath(path.toString()));
            out.println(path.toString());
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("upload-btn")));
            page.uploadFile();

            WebElement progressBar = base.driver.findElement(By.id("progress-bar"));
            WebDriverWait wait = new WebDriverWait(base.driver, 100);
            // String patternString = "100% uploaded";
            String patternString2 = "CERTIFICATE UPLOADED SUCCESSFULLY";
            String patternString3 = "We encountered an exception while processing your certificate, it has been sent to our exception handling team and should be available shortly.";
            String patternString1 = "Error while uploading / processing cert.";

            int element = 0;
            while (element == 0) {
                try {
                    out.println("Processing PDF");
                    /* Wait until at least anyone condition returns true */
                    wait.until(
                            ExpectedConditions.or(
                                    ExpectedConditions.textMatches(By.id("AddUpdateUserForm"), Pattern.compile(patternString2)),
                                    ExpectedConditions.textMatches(By.cssSelector("#errorMessage"), Pattern.compile(patternString3))
                            )
                    );
                    if (base.driver.findElement(By.id("AddUpdateUserForm")).isDisplayed()) {
                        out.println(base.driver.findElement(By.id("uploadSuccess")).getAttribute("type"));
                        out.println(progressBar.getCssValue("style"));
                        base.driver.findElement(By.cssSelector("#uploadSuccess > span")).click();
                    } else {
                        page.PopupOK();
                    }
                    element = 1;
                    //   page.failpopup();
                    // page.PopupOK();
                    File srcFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
                    fileNames = Collections.singletonList("success");
                    FileUtils.copyFile(srcFile, new File(filepath + fileNames + "-" + dateFormat.format(date) + ".png"));
                } catch (Exception e) {
                    Thread.sleep(2000);
                    element = 1;
                    File srcFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
                    fileNames = Collections.singletonList("failed");
                    FileUtils.copyFile(srcFile, new File(filepath + " " + fileNames + "-" + dateFormat.format(date) + ".png"));
                    page.failpopup();
                }
                out.println(date);
                out.println(dateFormat.format(date));
            }
        }
    }

    @And("^I click on Upload Cert button$")
    public void iClickOnUploadCertButton() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Upload Cert button");
        Loginpage page = new Loginpage(base.driver);
        page.upload();
        out.println("Clicks Upload Cert");
        Thread.sleep(5000);
    }

    @And("^I browse a pdf and refresh the page to verify clear filepath$")
    public void iBrowseAPdfAndRefreshThePageToVerifyClearFilepath() throws Throwable {
        out.println("browse a pdf and refresh the page");
        String uploadfilepath = attachPath("src/test/resources/Cert for Cisco Meraki.pdf");
        base.driver.findElement(By.id("pdf-file")).sendKeys(uploadfilepath);
        base.driver.navigate().refresh();
        Thread.sleep(4000);
        WebElement choose = base.driver.findElement(By.id("file-name"));
        String verifytext = choose.getText();
        if (verifytext.contentEquals(verifytext)) {
            System.out.println("File Input path value is empty");
            test.testgoogle.addResultForTestCase("612", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("612", TEST_CASE_FAILED_STATUS, "");
        }
    }

   /* @And("^I verify Upload File button is disabled by default$")
    public void iVerifyUploadFileButtonIsDisabledByDefault() throws Throwable {
        WebElement uploadfilebtn = base.driver.findElement(By.id("upload-btn"));
        if (uploadfilebtn.isEnabled()) {
            out.println("Upload File button is enabled by default");
              test.testgoogle.addResultForTestCase("610", TEST_CASE_FAILED_STATUS, "");
        } else {
            out.println("Upload File button is disabled by default");
               test.testgoogle.addResultForTestCase("610", TEST_CASE_PASSED_STATUS, "");
        }
        Thread.sleep(4000);
    }*/

    @And("^I verify text message in certificate uploaded successfully pop-up$")
    public void iVerifyTextMessageInCertificateUploadedSuccessfullyPopUp() throws Throwable {

        String patternString7 = "If you know the email address for the Insured and/or Holder and would like their registration letter to be sent via email please enter it below and click \"Okay,\" otherwise you can just click \"Okay\" to view your certificate.";
        String patternString8 = "If you know the email address for the Insured and would like their registration letter to be sent via email please enter it below and click \"Okay,\" otherwise you can just click \"Okay\" to view your certificate.";
        String patternString9 = "If you know the email address for the Holder and would like their registration letter to be sent via email please enter it below and click \"Okay,\" otherwise you can just click \"Okay\" to view your certificate.";
      WebElement verify_text= driver.findElement(By.xpath("//p[@class='userMessage CVTxt-FrmtCenStyl']"));
        String verify = driver.findElement(By.xpath("//p[@class='userMessage CVTxt-FrmtCenStyl']")).getText();

      if (verify_text.isDisplayed()) {
          if (verify.contentEquals(patternString7)) {
              testgoogle.addResultForTestCase("1611", TEST_CASE_PASSED_STATUS, "");
              out.println("pop-up text is:" + verify);

          } else if (verify.contentEquals(patternString8)) {
              test.testgoogle.addResultForTestCase("1611", TEST_CASE_PASSED_STATUS, "");
              System.out.println("pop-up text is:" + verify);

          } else if (verify.contentEquals(patternString9)) {
              test.testgoogle.addResultForTestCase("1611", TEST_CASE_PASSED_STATUS, "");
              System.out.println("pop-up text is:" + verify);
          }
          out.println("verify text message in certificate uploaded successfully pop-up");
      }
      else {
            out.println("Cert upload popup is not display");
        }
    }

    @And("^I choose renewal file to upload$")
    public void iChooseRenewalFileToUpload() throws Exception {
        out.println("Choose a renewal file to Upload");
        String filePath = attachPath("src/test/resources/OMIC certs/OMIC renewal.pdf");
        base.driver.findElement(By.id("pdf-file")).sendKeys(filePath);
        Loginpage page = new Loginpage(base.driver);
        WebElement uploadbtn = driver.findElement(By.id("upload-btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", uploadbtn);
        page.uploadFile();
        String patternString2 = "Certificate Uploaded Successfully";
        String patternString3 = "We encountered an exception while processing your certificate, it has been sent to our exception handling team and should be available shortly.";
        String batch = "Your batch of certificates has been uploaded successfully and is currently being processed. You can view the status of your batch on the Batches page.";
        WebElement verify_batch = base.driver.findElement(By.xpath("//div[@class='row']//p[@class='CVTxt-FrmtCenStyl']"));

        out.println("Processing PDF");
        WebDriverWait wait = new WebDriverWait(base.driver, 100);
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.textMatches(By.id("em"), Pattern.compile(patternString2)), //AddUpdateUserForm
                        ExpectedConditions.textMatches(By.cssSelector("#errorMessage"), Pattern.compile(patternString3)),
                        ExpectedConditions.textMatches(By.xpath("//div[@id='spid']//div[@id='em']"), Pattern.compile(batch))
                )
        );
        if (base.driver.findElement(By.xpath("//div[@id='spid']//div[@id='em']")).isDisplayed()) {  //AddUpdateUserForm
            Thread.sleep(2000);
            if (batch.contains(verify_batch.getText())) {
                out.println("pop-up text is:" + verify_batch.getText());
                test.testgoogle.addResultForTestCase("606", TEST_CASE_PASSED_STATUS, "");
                test.testgoogle.addResultForTestCase("12453", TEST_CASE_PASSED_STATUS, "");
                test.testgoogle.addResultForTestCase("9822", TEST_CASE_PASSED_STATUS, "");
            }
        } else if (base.driver.findElement(By.cssSelector("#errorMessage")).isDisplayed()) {
            Thread.sleep(2000);
        }
    }

    @And("^I choose file to upload$")
    public void iChooseFileToUpload() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I choose file to upload");
        out.println("Choose a file to Upload");
        String filePath = attachPath("src/test/resources/SMTP USI cert/usi 4.pdf");
        base.driver.findElement(By.id("pdf-file")).sendKeys(filePath);
        Loginpage page = new Loginpage(base.driver);
        WebElement uploadbtn = driver.findElement(By.id("upload-btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", uploadbtn);
        page.uploadFile();
        String patternString2 = "CERTIFICATE UPLOADED SUCCESSFULLY";
        String patternString3 = "We encountered an exception while processing your certificate, it has been sent to our exception handling team and should be available shortly.";

        out.println("Processing PDF");
        WebDriverWait wait = new WebDriverWait(base.driver, 100);
        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.textMatches(By.id("em"), Pattern.compile(patternString2)), //AddUpdateUserForm
                        ExpectedConditions.textMatches(By.cssSelector("#errorMessage"), Pattern.compile(patternString3))
                        //ExpectedConditions.textMatches(By.xpath("//div[@id='spid']//div[@id='em']"), Pattern.compile(batch))
                )
        );
        if (base.driver.findElement(By.id("em")).isDisplayed()) {  //AddUpdateUserForm
            Thread.sleep(2000);
            test.testgoogle.addResultForTestCase("97", TEST_CASE_PASSED_STATUS, "");
            test.testgoogle.addResultForTestCase("12452", TEST_CASE_PASSED_STATUS, "");
            out.println("Cert upload popup displayed");
        }else if (base.driver.findElement(By.cssSelector("#errorMessage")).isDisplayed()) {
            Thread.sleep(2000);
        }
        Thread.sleep(5000);
        File srcFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(filepath + "Browsecert.png"));
    }

    @And("^I choose invalid pdf file to upload$")
    public void iChooseInvalidPdfFileToUpload() throws Throwable {
        out.println("Choose invalid PDF file to Upload");
        String filePath = attachPath("src/test/resources/test.png");
        base.driver.findElement(By.id("pdf-file")).sendKeys(filePath);
        Loginpage page = new Loginpage(base.driver);
        page.warning_OKbtn();
        Thread.sleep(3000);
    }

    @And("^I click on Okay button to view uploaded cert$")
    public void iClickOnOkayButtonToViewUploadedCert() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on Okay button to view uploaded cert");
        out.println("Choose a file to Upload");
        Loginpage page = new Loginpage(base.driver);
        String patternString2 = "Certificate uploaded successfully.";
        WebDriverWait wait = new WebDriverWait(base.driver, 100);
        wait.until(ExpectedConditions.textMatches(By.id("AddUpdateUserForm"), Pattern.compile(patternString2)));
        page.PopupOK();
    }

    @And("^I verify company groups dropdown while uploading cert$")
    public void iVerifyCompanyGroupsDropdownWhileUploadingCert(DataTable table) throws Throwable {
        List<companygroups> grp = new ArrayList<>();
        grp = table.asList(companygroups.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("entering company groups dropdown in upload cert");
        for (companygroups cg : grp) {
            page.companygrp(cg.companygroups);
        }
    }

    public class companygroups {
        public String companygroups;

        public companygroups(String CompanyGroups) {
            companygroups = CompanyGroups;
        }
    }

    @And("^I click on Revision button$")
    public void iClickOnRevisionButton() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on Revision button");
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Revision button");
        page.revision();
        Thread.sleep(3000);
    }

    @And("^I search with columns in certificate list$")
    public void iSearchWithColumnsInCertificateList(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I search with columns in certificate list");
        List<certificatelist> list = new ArrayList<>();
        list = table.asList(certificatelist.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("Search columns in Certificate list");
        for (certificatelist cert : list) {
            page.certlist(cert.uploadedby, cert.insured, cert.holder, cert.uploaddatefrom, cert.uploaddateto);
        }
        String patternString4 = "400 California, LLC";
        WebDriverWait wait = new WebDriverWait(base.driver, 100);
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"revision-tbl\"]/tbody/tr[1]/td[4]"), Pattern.compile(patternString4)));
    }

    public class certificatelist {
        public String uploadedby, insured, holder, issuedate, uploaddatefrom, uploaddateto;

        public certificatelist(String UploadedBy, String Insured, String Holder, String UploadDateFrom, String UploadDateTo) {
            uploadedby = UploadedBy;
            insured = Insured;
            holder = Holder;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
        }
    }

    @And("^I select radio button under Revise column$")
    public void iSelectRadioButtonUnderReviseColumn() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I select radio button under Revise column");
        Loginpage page = new Loginpage(base.driver);
        page.revise();
        Thread.sleep(3000);
    }

    @And("^I fill details$")
    public void iFillDetails(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I fill details");
        out.println("Fill insured and holder email addresses");
        List<upload> certs = new ArrayList<upload>();
        certs = table.asList(upload.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        for (upload cert : certs) {
            page.uploadfields(cert.insuredemail, cert.holderemail);
        }
        File srcFile = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(filepath + "filldetails.png"));
    }

    public class upload {
        public String insuredemail, holderemail;

        public upload(String InsuredEmail, String HolderEmail) {
            this.insuredemail = InsuredEmail;
            this.holderemail = HolderEmail;
        }
    }

    @And("^I click on Admin menu$")
    public void iClickOnAdminMenu() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.admin();
        out.println("Clicks Admin menu");
        Thread.sleep(3000);
    }

    @And("^I click on Companies tab$")
    public void iClickOnCompaniesTab() throws Throwable {
        //  scenarioDef.createNode(new GherkinKeyword("And"), "I click on Companies tab");
        Loginpage page = new Loginpage(base.driver);
        page.companies();
        out.println("Clicks Companies tab");
        Thread.sleep(5000);
    }

    @And("^I click Add Company button$")
    public void iClickAddCompanyButton() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click Add Company button");
        Loginpage page = new Loginpage(base.driver);
        page.addCompany();
        out.println("Clicks Companies tab");
        Thread.sleep(5000);
    }

    @And("^I fill details for add company$")
    public void iFillDetailsForAddCompany(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I fill details for add company");
        Loginpage page = new Loginpage(base.driver);
        out.println("Enters data in add Company");
        List<FillAddCompany> addcomp = new ArrayList<>();
        addcomp = table.asList(FillAddCompany.class);
        for (FillAddCompany add : addcomp) {
            page.filladdcompany(add.companyname, add.companytype, add.patraonecomp, add.address);
        }
        Thread.sleep(10000);
    }

    public class FillAddCompany {
        public String companyname, companytype, patraonecomp, address;

        public FillAddCompany(String CompanyName, String CompanyType, String PatraoneComp, String Address) {
            companyname = CompanyName;
            companytype = CompanyType;
            patraonecomp = PatraoneComp;
            address = Address;
        }
    }

    @And("^I verify close button in Add Company form$")
    public void iVerifyCloseButtonInAddCompanyForm() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("close btn in add Company");
        page.close_addcompany();
        Thread.sleep(3000);
    }

    @When("^I search with columns in add company header grid$")
    public void iSearchWithColumnsInAddCompanyHeaderGrid(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I search with columns in add company header grid");
        Loginpage page = new Loginpage(base.driver);
        out.println("Searches record in add Company grid");
        List<searchcompany> search = new ArrayList<>();
        search = table.asList(searchcompany.class);
        for (searchcompany grid : search) {
            page.searchcompany(grid.companyid, grid.companyname, grid.companytype, grid.registered, grid.address, grid.companyrootid, grid.companyrootname, grid.companyhaslogo);
        }
        Thread.sleep(5000);
    }

    public class searchcompany {
        public String companyid, companyname, companytype, registered, address, companyrootid, companyrootname, companyhaslogo;

        public searchcompany(String CompanyId, String CompanyName, String CompanyType, String Registered, String Address, String CompanyRootId, String CompanyRootName, String CompanyHasLogo) {
            companyid = CompanyId;
            companyname = CompanyName;
            companytype = CompanyType;
            registered = Registered;
            address = Address;
            companyrootid = CompanyRootId;
            companyrootname = CompanyRootName;
            companyhaslogo = CompanyHasLogo;
        }
    }

    @And("^I click edit symbol and edit company values$")
    public void iClickEditSymbolAndEditCompanyValues(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click edit symbol and edit company values");
        Loginpage page = new Loginpage(base.driver);
        out.println("Edits record in add Company");
        List<Editcompany> edit = new ArrayList<>();
        edit = table.asList(Editcompany.class);
        for (Editcompany comp : edit) {
            page.editcompany(comp.companyname, comp.companytype, comp.patraonecomp, comp.address);
        }
        Thread.sleep(5000);
    }

    public class Editcompany {
        public String companyname, companytype, patraonecomp, address;

        public Editcompany(String CompanyName, String CompanyType, String PatraoneComp, String Address) {
            companyname = CompanyName;
            companytype = CompanyType;
            patraonecomp = PatraoneComp;
            address = Address;
        }
    }

    @And("^I verify logo upload for company and its validation$")
    public void iVerifyLogoUploadForCompanyAndItsValidation() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        //  page.editsymbol.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 240)");
        if (page.editcom_logo.isDisplayed()) {
            String filePath = attachPath("src/test/resources/Heffernan renewal cert.pdf");
            base.driver.findElement(By.id("logofile")).sendKeys(filePath);
            Thread.sleep(4000);
            page.war_OKbtn.click();
            Thread.sleep(3000);
            testgoogle.addResultForTestCase("6699", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("6699", TEST_CASE_FAILED_STATUS, "");
        }
        if (page.editcom_logo.isDisplayed()) {
            String filePath = attachPath("src/test/resources/validlogo.png");
            driver.findElement(By.id("logofile")).sendKeys(filePath);
            Thread.sleep(2000);
            page.addcompanySavebtn.click();
            testgoogle.addResultForTestCase("6698", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("6698", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @And("^I verify validation for logo image size$")
    public void iVerifyValidationForLogoImageSize() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.editsymbol.click();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 440)");
        if (page.editcom_logo.isDisplayed()) {
            Thread.sleep(3000);
            String filePath = attachPath("src/test/resources/test.png");
            driver.findElement(By.id("logofile")).sendKeys(filePath);
            Thread.sleep(3000);
            page.war_OKbtn.click();
            Thread.sleep(3000);
            testgoogle.addResultForTestCase("10546", TEST_CASE_PASSED_STATUS, "");
        } else {
            testgoogle.addResultForTestCase("10546", TEST_CASE_FAILED_STATUS, "");
        }
        driver.navigate().refresh();
        // page.addcompanyClosebtn.click();
    }

    @And("^I verify validation for edit company form$")
    public void iVerifyValidationForEditCompanyForm() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("validation in add Company");
        page.validate_Editcompany();
        Thread.sleep(5000);
    }

    @And("^I verify merge companies functionality$")
    public void iVerifyMergeCompaniesFunctionality() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.mergecompanies();
        out.println("clicks Merge companies button");
        //  page.cm_warningpopup();  //cv-1135
        Thread.sleep(3000);
    }

    @And("^I select checkboxes for companies to merge$")
    public void iSelectCheckboxesForCompaniesToMerge() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.selcheckbox();
        Thread.sleep(3000);
    }

    @And("^I verify validation for company merge$")
    public void iVerifyValidationForCompanyMerge() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.validation_CM();
        Thread.sleep(3000);
        // page.closeX(); - removed in UI
    }

    @When("^I click on Add Company Group Icon$")
    public void iClickOnAddCompanyGroupIcon() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addcompanygrpicon();
        Thread.sleep(2000);
    }

    @And("^I verify functionality of add company group$")
    public void iVerifyFunctionalityOfAddCompanyGroup() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addcompgrp();
        Thread.sleep(2000);
    }

    @And("^I verify close button in Add Company Group$")
    public void iVerifyCloseButtonInAddCompanyGroup() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.close_addcompgrp();
        Thread.sleep(2000);
    }

    @And("^I verify validation for Add Company Group$")
    public void iVerifyValidationForAddCompanyGroup() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.validation_ACG();
    }

    @And("^I click on Child Companies list under action column$")
    public void iClickOnChildCompaniesListUnderActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.childcomplist_icon();
        Thread.sleep(3000);
    }

    @And("^I click x button to close child companies list pop-up$")
    public void iClickXButtonToCloseChildCompaniesListPopUp() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.childcomp_Xbtn();
        Thread.sleep(3000);
    }

    @And("^I click on opt-out icon under Action column$")
    public void iClickOnOptOutIconUnderActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.optouticon();
        Thread.sleep(3000);
    }

    @And("^I select broker and submit optout$")
    public void iSelectBrokerAndSubmitOptout() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.sel_optout();
        Thread.sleep(3000);
    }

    @And("^I Verify disabling of Insured Notifications in edit company$")
    public void iVerifyDisablingOfInsuredNotificationsInEditCompany() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.disableinsurednotification();
        Thread.sleep(3000);
    }

    @And("^I click on User Profile tab$")
    public void iClickOnUserProfileTab() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on User Profile tab");
        Loginpage page = new Loginpage(base.driver);
        page.userProfile();
        out.println("Clicks User Profile");
        Thread.sleep(5000);
    }

    @And("^I click on Reset Password button$")
    public void iClickOnResetPasswordButton() throws Throwable {
        out.println("Clicks Reset Password");
        Loginpage page = new Loginpage(base.driver);
        page.resetpwd_btn();
        Thread.sleep(5000);
    }

    @And("^I work on reset password$")
    public void iWorkOnResetPassword(DataTable table) throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I work on reset password");
        List<Resetpwd> reset = new ArrayList<>();
        reset = table.asList(Resetpwd.class);
        Loginpage page = new Loginpage(base.driver);
        for (Resetpwd pwd : reset) {
            page.resetpwd(pwd.oldpassword, pwd.newpassword, pwd.confirmpassword);
        }
        Thread.sleep(5000);
    }

    public class Resetpwd {
        public String oldpassword, newpassword, confirmpassword;

        public Resetpwd(String OldPassword, String NewPassword, String ConfirmPassword, String ResetKey) {
            oldpassword = OldPassword;
            newpassword = NewPassword;
            confirmpassword = ConfirmPassword;
        }
    }

    @Then("^I click on Profile icon and Logout$")
    public void iClickOnProfileIconAndLogout() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("Then"), "I click on Profile icon and Logout");
        out.println("Clicks Profile icon");
        Loginpage page = new Loginpage(base.driver);
        page.profileIcon();
        Thread.sleep(3000);
    }

    @When("^I click on Profile icon and My Profile$")
    public void iClickOnProfileIconAndMyProfile() throws Throwable {
        out.println("Clicks Profile icon");
        Loginpage page = new Loginpage(base.driver);
        page.userProfile();  //myprofile()
        page.myprofile();
    }

    @When("^I click on profile icon and user guide$")
    public void iClickOnProfileIconAndUserGuide() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Profile icon");
        page.userGuide();
    }

    @And("^I click on Certificates tab$")
    public void iClickOnCertificatesTab() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Certificates tab");
        Loginpage page = new Loginpage(base.driver);
        page.certificatesTab();
        out.println("Clicks Certificates tab");
        Thread.sleep(3000);
    }

    @And("^Get the KPI Count$")
    public void getTheKPICount() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "Get the KPI Count");
        Thread.sleep(5000);
        out.println("Gets KPI values in Certificates tab");
        Loginpage page = new Loginpage(base.driver);
        page.KPIValue();
    }

    @And("^I click on Active KPI$")
    public void iClickOnActiveKPI() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Active KPI");
        page.KPIactive();
        Thread.sleep(5000);
    }

    @And("^I click on Expired KPI$")
    public void iClickOnExpiredKPI() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Expired KPI");
        page.KPIexpired();
        Thread.sleep(5000);
    }

    @And("^I sort the columns in Certificates$")
    public void iSortTheColumnsInCertificates() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("sort the columns in Certificates");
        page.sort_certificates();
        Thread.sleep(3000);
    }

    @When("^I search with columns in header grid$")
    public void iSearchWithColumnsInHaederGrid(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("When"), "I search with columns in header grid");
        out.println("Enter search data in search columns");
        List<SearchCertValues> search = new ArrayList<SearchCertValues>();
        search = table.asList(SearchCertValues.class);
        Loginpage page = new Loginpage(base.driver);
        for (SearchCertValues Certfields : search) {
            page.certvalueassign(Certfields.certid, Certfields.insured, Certfields.holder, Certfields.issuer, Certfields.expirationfrom, Certfields.expirationto, Certfields.issuedfrom, Certfields.issuedto,
                    Certfields.uploaddatefrom, Certfields.uploaddateto, Certfields.insuredstatus, Certfields.holderstatus, Certfields.revised, Certfields.renewal, Certfields.renewalbatch, Certfields.companygroup);
        }
    }

    public class SearchCertValues {
        public String certid, insured, holder, issuer, expirationfrom, expirationto, issuedfrom, issuedto, uploaddatefrom, uploaddateto, insuredstatus, holderstatus, revised, renewal, renewalbatch, companygroup;

        public SearchCertValues(String CertId, String Insured, String Holder, String Issuer, String ExpirationFrom, String ExpirationTo, String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo,
        String InsuredStatus, String HolderStatus, String Revised, String Renewal, String RenewalBatch, String CompanyGroup) {
            certid = CertId;
            insured = Insured;
            holder = Holder;
            issuer = Issuer;
            expirationfrom = ExpirationFrom;
            expirationto = ExpirationTo;
            issuedfrom = IssuedFrom;
            issuedto = IssuedTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
            insuredstatus = InsuredStatus;
            holderstatus = HolderStatus;
            revised = Revised;
            renewal = Renewal;
            renewalbatch = RenewalBatch;
            companygroup = CompanyGroup;
        }
    }

    @When("^I search with columns in header grid for Insured login$")
    public void iSearchWithColumnsInHeaderGridForInsuredLogin(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("When"), "I search with columns in header grid for Insured login");
        out.println("Enter search data in search columns for Insured login");
        List<InsuSearchCert> search = new ArrayList<InsuSearchCert>();
        search = table.asList(InsuSearchCert.class);
        Loginpage page = new Loginpage(base.driver);
        for (InsuSearchCert Certfields : search) {
            page.insuredvalueassign(Certfields.certid, Certfields.broker, Certfields.holder, Certfields.expirationfrom, Certfields.expirationto, Certfields.issuedfrom, Certfields.issuedto,
                    Certfields.uploaddatefrom, Certfields.uploaddateto);
            Thread.sleep(10000);
        }
    }

    public class InsuSearchCert {
        public String certid, broker, holder, expirationfrom, expirationto, issuedfrom, issuedto, uploaddatefrom, uploaddateto;

        public InsuSearchCert(String CertId, String Broker, String Holder, String ExpirationFrom, String ExpirationTo, String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo) {
            certid = CertId;
            broker = Broker;
            holder = Holder;
            expirationfrom = ExpirationFrom;
            expirationto = ExpirationTo;
            issuedfrom = IssuedFrom;
            issuedto = IssuedTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
        }
    }

    @When("^I search with columns in header grid for Holder login$")
    public void iSearchWithColumnsInHeaderGridForHolderLogin(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("When"), "I search with columns in header grid for Holder login");
        out.println("Enter search data in search columns for Holder login");
        List<HolderSearchcert> search = new ArrayList<>();
        search = table.asList(HolderSearchcert.class);
        Loginpage page = new Loginpage(base.driver);
        for (HolderSearchcert Certfields : search) {
            page.holdervaluesassign(Certfields.certid, Certfields.insured, Certfields.broker, Certfields.expirationfrom, Certfields.expirationto, Certfields.issuedfrom, Certfields.issuedto,
                    Certfields.uploaddatefrom, Certfields.uploaddateto);
        }
        Thread.sleep(5000);
    }

    public class HolderSearchcert {
        public String certid, insured, broker, expirationfrom, expirationto, issuedfrom, issuedto, uploaddatefrom, uploaddateto;

        public HolderSearchcert(String CertId, String Insured, String Broker, String ExpirationFrom, String ExpirationTo, String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo) {
            certid = CertId;
            insured = Insured;
            broker = Broker;
            expirationfrom = ExpirationFrom;
            expirationto = ExpirationTo;
            issuedfrom = IssuedFrom;
            issuedto = IssuedTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
        }
    }

    @And("^Header title accessibility$")
    public void Header_title_accessibility() throws Throwable {
        out.println("Header title appears");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.header();
    }

    @And("^I click on Clear All Filters button for Certificates$")
    public void iClickOnClearAllFiltersButtonForCertificates() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clear All Filters button for Certificates");
        page.clear_certs();
        Thread.sleep(3000);
    }

    @And("^I click on Clear All Filters button$")
    public void iClickOnClearAllFiltersButton() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Clear All Filters button");
        out.println("Clear All Filters button in Companies");
        Loginpage page = new Loginpage(base.driver);
        page.verify_clear();
        Thread.sleep(3000);
    }

    @And("^I click on Clear All Filters button manage users$")
    public void iClickOnClearAllFiltersButtonManageUsers() throws Throwable {
        out.println("Clear All Filters button");
        Loginpage page = new Loginpage(base.driver);
        page.mnguser_clear();
        Thread.sleep(5000);
    }

    @And("^I click on Exceptions Clear All Filters button$")
    public void iClickOnExceptionsClearAllFiltersButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.exceptionclear();
        Thread.sleep(5000);
    }

    @And("^I click on Recent KPI$")
    public void iClickOnRecentKPI() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Recent KPI");
        Loginpage page = new Loginpage(base.driver);
        page.KPIrecent();
        out.println("Clicks Recent KPI");
        Thread.sleep(4000);
    }

    @And("^I verify fields for single blockchain in view cert page$")
    public void iVerifyFieldsForSingleBlockchainInViewCertPage() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.blockchainfields();
        Thread.sleep(3000);
    }

    @And("^I verify company group value in view cert$")
    public void iVerifyCompanyGroupValueInViewCert() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.companygrp_cert();
    }

    @And("^I edit company group in view cert$")
    public void iEditCompanyGroupInViewCert() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.editcompanygrp_cert();
        Thread.sleep(3000);
    }

    @And("^I click on View Cert button$")
    public void iClickOnViewCertButton() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on View Cert button");
        out.println("Clicks view Cert button");
        Loginpage page = new Loginpage(base.driver);
        page.viewCert();
    }

    @And("^I handle paper registration button functionality$")
    public void iHandlePaperRegistrationButtonFunctionality() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.paperreg();
    }

    @And("^I handle email registration button functionality$")
    public void iHandleEmailRegistrationButtonFunctionality(DataTable table) throws Exception {
        List<emailregistration> email = new ArrayList<>();
        email = table.asList(emailregistration.class);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        for (emailregistration enter : email) {
            page.emailreg(enter.enteremail);
        }
        Thread.sleep(5000);
    }

    public class emailregistration {
        public String enteremail;

        public emailregistration(String EnterEmail) {
            enteremail = EnterEmail;
        }
    }

    @And("^I scroll down to the page$")
    public void iScrollDownToThePage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");
    }

    @And("^I verify validation for Paper Registration form in view cert$")
    public void iVerifyValidationForPaperRegistrationFormInViewCert() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(4000);
        page.validate_paperreg();

    }

    @And("^I verify validation for Email registration form in view cert$")
    public void iVerifyValidationForEmailRegistrationFormInViewCert() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.validate_emailreg();
        Thread.sleep(4000);
    }

    @And("^I verify the remove Email functionality$")
    public void I_verify_the_remove_Email_functionality() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.remove_email();
        Thread.sleep(4000);
    }

    @And("^I verify validation for Email Certificate form in view cert$")
    public void iVerifyValidationForEmailCertificateFormInViewCert() throws Throwable {
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.validate_emailcert();
        Thread.sleep(4000);
    }

    @And("^I update email in Email Registration Letter$")
    public void iUpdateEmailInEmailRegistrationLetter() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.updateemail();
        Thread.sleep(8000);
    }

    @And("^I perform buttons actions available for that cert$")
    public void iPerformButtonsActionsAvailableForThatCert() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I perform buttons actions available for that cert");
        out.println("Perform actions for Paper, Email Reg and Email cert buttons");
        Loginpage page = new Loginpage(base.driver);
        page.viewCert();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");
    }

    @And("^I click on Email Cert$")
    public void iClickOnEmailCert() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click on Email Cert");
        System.out.println("Clicks Email Cert button");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(6000);
        page.emailcert();
        Thread.sleep(6000);
    }

    @And("^I click on Manage Users tab$")
    public void iClickOnManagerUsersTab() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click on Manage Users tab");
        Loginpage page = new Loginpage(base.driver);
        page.manageUsers();
        out.println("Clicks Manage Users");
        Thread.sleep(5000);
    }

    @And("^I click Add User button$")
    public void iClickAddUserButton(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click Add User button");
        WebElement adduser = driver.findElement(By.xpath("//html/body/div[2]/section[2]/div/div[2]/div/button/a"));  //Add user btn
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", adduser);
        adduser.click();
        List<adduser> user = new ArrayList<adduser>();
        user = table.asList(adduser.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Add User button and fill details");
        for (adduser add : user) {
            page.addUser(add.name, add.role, add.companytype, add.companyname, add.emailaddress, add.pwd);
        }
        Thread.sleep(5000);
    }

    public class adduser {
        public String name, role, companytype, companyname, emailaddress, pwd;

        public adduser(String Name, String Role, String CompanyType, String CompanyName, String EmailAddress, String Pwd) {
            name = Name;
            role = Role;
            companytype = CompanyType;
            companyname = CompanyName;
            emailaddress = EmailAddress;
            pwd = Pwd;
        }
    }

    @And("^I verify validation for Add user pop-up$")
    public void iVerifyValidationForAddUserPopUp() throws Throwable {
        String exp_valid = "Add User";
        if (base.driver.getPageSource().contains(exp_valid)) {
            test.testgoogle.addResultForTestCase("1584", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("1584", TEST_CASE_FAILED_STATUS, "");
        }
        Thread.sleep(5000);
    }

    @When("^I search with columns in manage users header grid$")
    public void iSearchWithColumnsInManageUsersHeaderGrid(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("When"), "I search with columns in manage users header grid");
        Loginpage page = new Loginpage(base.driver);
        out.println("Search record in grid");
        List<Search_manageuser> search = new ArrayList<>();
        search = table.asList(Search_manageuser.class);
        for (Search_manageuser grid : search) {
            page.searchmanageuser(grid.name, grid.account, grid.companyname, grid.companyid, grid.role, grid.lastloginfrom, grid.lastloginto);
        }
        Thread.sleep(12000);
    }

    public class Search_manageuser {
        public String name, account, companyname, companyid, role, lastloginfrom, lastloginto;

        public Search_manageuser(String Name, String Account, String CompanyName, String CompanyId, String Role, String LastLoginFrom, String LastLoginTo) {
            name = Name;
            account = Account;
            companyname = CompanyName;
            companyid = CompanyId;
            role = Role;
            lastloginfrom = LastLoginFrom;
            lastloginto = LastLoginTo;
        }
    }

    @And("^I click edit symbol and edit user details$")
    public void iClickEditSymbolAndEditUserDetails(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click edit symbol and edit user details");
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Edit symbol and fill edit user form");
        List<adduser> user = new ArrayList<adduser>();
        user = table.asList(adduser.class);
        for (adduser add : user) {
            page.editUser(add.name, add.role, add.emailaddress, add.pwd);
        }
        WebElement element = base.driver.findElement(By.xpath("//div[@class='row']//button[@type='submit'][contains(text(),'Save')]"));  //Save btn
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        Thread.sleep(5000);
    }

    @And("^I click on Save button in edit user pop-up$")
    public void iClickOnSaveButtonInEditUserPopUp() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.edituserclick();
        Thread.sleep(3000);
    }

    @And("^I verify validation for Edit user pop-up$")
    public void iVerifyValidationForEditUserPopUp() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        String exp_editvalid = "Edit User";
        if (base.driver.getPageSource().contains(exp_editvalid)) {
             test.testgoogle.addResultForTestCase("868", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("868", TEST_CASE_FAILED_STATUS, "");
        }
        page.edit_close();
        Thread.sleep(3000);
    }

    @And("^I click on Save button in pop-up$")
    public void iClickOnSaveButtonInPopUp() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click on Save button in pop-up");
        WebElement element = base.driver.findElement(By.xpath("//*[@id='AddUpdateUserForm']/div[2]/div[3]/div/button"));  //save button
        Actions action = new Actions(base.driver);
        if (element.isDisplayed()) {
            action.moveToElement(element).click().perform();
            out.println("Clicks Save button");
            Thread.sleep(5000);
            test.testgoogle.addResultForTestCase("104", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("104", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @And("^I click on close button in add user form$")
    public void iClickOnCloseButtonInAddUserForm() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Close button");
        page.addUser_close();
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(3000);
    }

    @And("^I mark the user as enable or disable$")
    public void iMarkTheUserAsEnableORDisable() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I mark the user as enable or disable");
        Loginpage page = new Loginpage(base.driver);
     /* page.edituserclick(); - removed in ui2
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element2 = base.driver.findElement(By.xpath("//*[@id=\"AddUpdateUserForm\"]/div/div[4]/button[2]"));
        jse.executeScript("arguments[0].scrollIntoView();", element2);*/
        page.disable();
        out.println("Clicks Disable/Enable button");
        Thread.sleep(6000);
    }

    @And("^I click x button to close$")
    public void iClickXButtonToClose() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click x button to close");
        Loginpage page = new Loginpage(base.driver);
        WebElement paswordResetErrMsg = base.driver.findElement(By.id("paswordResetErrMsg"));
        try {
            if (paswordResetErrMsg.isDisplayed()) {
                out.println(paswordResetErrMsg.getText());
                page.Xbtn();
            } else {
                out.println("Password Reset successfully done");
            }
        } catch (Exception e) {
            if (driver.findElement(By.id("addUserLabel")).isDisplayed()) {
                page.Xbtn();
                out.println("Clicks X button in Edit User");
            } else {
                out.println("Not found edit user again");
            }
        }
        Thread.sleep(5000);
    }

    @When("^I click on Reset Password button in users page$")
    public void iClickOnResetPasswordButtonInUsersPage(DataTable table) throws Throwable {
        List<userresetpwd> pwd = new ArrayList<>();
        pwd = table.asList(userresetpwd.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("clicks reset pwd in edit user");
        for (userresetpwd reset : pwd) {
            page.edituser_resetpwd(reset.newpwd, reset.confirmpwd);
        }
        Thread.sleep(3000);
    }

    public class userresetpwd {
        public String newpwd;
        public String confirmpwd;

        public userresetpwd(String NewPwd, String ConfirmPwd) {
            newpwd = NewPwd;
            confirmpwd = ConfirmPwd;
        }
    }

    @And("^I enter password confirmation$")
    public void iEnterPasswordConfirmation(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I enter password confirmation");
        List<validatepwdconfirm> pwd = new ArrayList<>();
        pwd = table.asList(validatepwdconfirm.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("Enter password confirmation");
        for (validatepwdconfirm confirm : pwd) {
            page.pwdconfirmation(confirm.confirmuserpwd);
        }
        Thread.sleep(5000);

        /*if(driver.findElement(By.id("paswordResetErrMsg")).isDisplayed()) {
            page.addUser_close();
            Thread.sleep(3000);
        }*/
    }

    public class validatepwdconfirm {
        public String confirmuserpwd;

        public validatepwdconfirm(String PWDConfirmation) {
            confirmuserpwd = PWDConfirmation;
        }
    }

    @And("^I verify validation for password confirmation$")
    public void iVerifyValidationForPasswordConfirmation(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<validatepwdconfirm> pwd = new ArrayList<>();
        pwd = table.asList(validatepwdconfirm.class);
        out.println("Enter invalid password confirmation");
        Thread.sleep(3000);
        for (validatepwdconfirm confirm : pwd) {
            page.invalidpwdconfirmation(confirm.confirmuserpwd);
        }
    }

    @And("^I verify validation for reset failed$")
    public void iVerifyValidationForResetFailed() throws Throwable {
        out.println("verify validation for reset failed : Edit user");
        String exp_msg = "Reset failed: Password Previously Used.";
        if (base.driver.getPageSource().contains(exp_msg)) {
            test.testgoogle.addResultForTestCase("2003", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("2003", TEST_CASE_FAILED_STATUS, "");
        }
        Loginpage page = new Loginpage(base.driver);
        page.addUser_close();
        Thread.sleep(3000);
    }

    @And("^I click on Support tab$")
    public void iClickOnSupportTab() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click on Support tab");
        out.println("Clicks Support tab");
        Loginpage page = new Loginpage(base.driver);
        page.support();
        Thread.sleep(5000);
    }

    @When("^I search with columns in users list$")
    public void iSearchWithColumnsInUsersList(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("When"), "I search with columns in users list");
        out.println("Searches with support user list grid");
        List<Searchsupportvalues> users = new ArrayList<Searchsupportvalues>();
        users = table.asList(Searchsupportvalues.class);
        Loginpage page = new Loginpage(base.driver);
        for (Searchsupportvalues support : users) {
            page.supportsearchgrid(support.name, support.account, support.accesscode, support.type, support.company, support.companyid, support.addedby, support.addedondatefrom,
                    support.addedondateto, support.expirationdatefrom, support.expirationdateto, support.registered);
        }
        //  Thread.sleep(7000);
    }

    public class Searchsupportvalues {
        public String name, account, accesscode, type, company, companyid, addedby, addedondatefrom, addedondateto, expirationdatefrom, expirationdateto, registered;

        public Searchsupportvalues(String Name, String Account, String AccessCode, String Type, String Company, String CompanyId, String AddedBy, String AddedOnDateFrom,
                                   String AddedOnDateTo, String ExpirationDateFrom, String ExpirationDateTo, String Registered) {
            name = Name;
            account = Account;
            accesscode = AccessCode;
            type = Type;
            company = Company;
            companyid = CompanyId;
            addedby = AddedBy;
            addedondatefrom = AddedOnDateFrom;
            addedondateto = AddedOnDateTo;
            expirationdatefrom = ExpirationDateFrom;
            expirationdateto = ExpirationDateTo;
            registered = Registered;
        }
    }

    @And("^I click on Exception Handling tab$")
    public void iClickOnExceptionHandlingTab() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click on Exception Handling tab");
        Loginpage page = new Loginpage(base.driver);
        page.excceptionHandling();
        out.println("Clicks Exception Handling tab");
        Thread.sleep(5000);
    }

    @And("^I sort the columns in Exception handling$")
    public void iSortTheColumnsInExceptionHandling() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Sorting Exception Handling columns");
        page.sort_Exception();
        Thread.sleep(5000);
    }

    @And("^I click Paper Registration Letters button to generate letters$")
    public void iClickPaperRegistrationLettersButtonToGenerateLetters() throws Throwable {
        // scenarioDef.createNode(new GherkinKeyword("And"), "I click Paper Registration Letters button to generate letters");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.paperRegltrs();
     /*   Thread.sleep(8000);
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);*/
    }

    @And("^I verify cancel button in generate paper letters form$")
    public void iVerifyCancelButtonInGeneratePaperLettersForm() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.paperregltrs_cancel();
        Thread.sleep(3000);
    }

    @And("^I select Broker Company in Generate Paper letters$")
    public void iSelectBrokerCompanyInGeneratePaperLetters() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.brokerCompany();
        Thread.sleep(3000);
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);
    }

    @And("^I search with columns in exception grid$")
    public void iSearchWithColumnsInExceptionGrid(DataTable table) throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I search with columns in exception grid");
        out.println("Search with column grids");
        List<Searchexceptionvalues> exception = new ArrayList<Searchexceptionvalues>();
        exception = table.asList(Searchexceptionvalues.class);
        Loginpage page = new Loginpage(base.driver);
        for (Searchexceptionvalues search : exception) {
            page.exceptionsearchgrid(search.certid, search.errortype, search.insured, search.holder, search.issuer, search.issuefrom, search.issueto,
                    search.uploaddatefrom, search.uploaddateto);
        }
        Thread.sleep(8000);
    }

    //Declare variables
    public class Searchexceptionvalues {
        public String certid, errortype, insured, holder, issuer, issuefrom, issueto, uploaddatefrom, uploaddateto;

        public Searchexceptionvalues(String CertID, String ErrorType, String Insured, String Holder, String Issuer, String IssueFrom, String IssueTo, String UploadDateFrom, String UploadDateTo) {
            certid = CertID;
            errortype = ErrorType;
            insured = Insured;
            holder = Holder;
            issuer = Issuer;
            issuefrom = IssueFrom;
            issueto = IssueTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
        }
    }

    @And("^I click on Delete button$")
    public void iClickOnDeleteButton() throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Delete button");
        Loginpage page = new Loginpage(base.driver);
        WebElement delete = driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div[3]/div/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", delete);
        page.delete();
        out.println("Clicks Delete btn in Exception view cert");
        Thread.sleep(3000);
    }

    @And("^I click on Add Supporting Documents button$")
    public void iClickOnAddSupportingDocumentsButton() throws Throwable {
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.addSuppDoc();
        out.println("Clicks Add Supporting Documents button");
        Thread.sleep(3000);
    }

    @And("^I verify validation for blank supporting docs$")
    public void iVerifyValidationForBlankSupportingDocs() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.validate_addsuppdoc();
        Thread.sleep(3000);
    }

    @And("^I choose and upload file for Add Supporting docs$")
    public void iChooseAndUploadFileForAddSupportingDocs() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        WebElement addsuppdoc = base.driver.findElement(By.id("thefile"));
        addsuppdoc.sendKeys(attachPath("src/test/resources/test.png"));
        out.println("Choose and upload support doc");
        page.uploadSupportdoc();
        Thread.sleep(5000);
    }

    @And("^I click on View Supporting Documents button$")
    public void iClickOnViewSupportingDocumentsButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks View Supporting Documents button");
        page.viewSuppDoc();
        Thread.sleep(5000);
    }

    @And("^I delete view support document$")
    public void iDeleteViewSupportDocument() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.viewSuppDelete();
        Thread.sleep(6000);
    }

    @And("^I click on Delete button in Certificates tab$")
    public void iClickOnDeleteButtonInCertificatesTab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.viewCert();
        out.println("Clicks view Cert button");
        WebElement delete = driver.findElement(By.id("Dlt"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", delete);
        //  Thread.sleep(3000);
        page.DeleteCert();
        out.println("Clicks Delete button in Certs tab");
        Thread.sleep(3000);
    }

    @And("^I verify Delete button is not visible for insured/holder/broker/cert uploader users$")
    public void iVerifyDeleteButtonIsNotVisibleForInsuredHolderBrokercertuploaderUsers() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        page.DeleteCert();
    }

    @And("^I click on View Cert button in Exception tab$")
    public void iClickOnViewCertButtonInExceptionTab() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "I click on View Cert button in Exception tab");
        Loginpage page = new Loginpage(base.driver);
        page.excpviewcert();
        out.println("Clicks View Cert btn in Exception tab");
        Thread.sleep(8000);
    }

    @And("^I click on Add Policy Information button$")
    public void iClickOnAddPolicyInformationButton() throws Throwable {
        out.println("Clicks Add Policy Information button");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement addpolicyinfo = base.driver.findElement(By.xpath("//html/body/div[2]/section[2]/div/div[1]/div/button"));
        //  js.executeScript("arguments[0].scrollIntoView();",addpolicyinfo);
        js.executeScript("window.scrollBy(0, 250)");
        Loginpage page = new Loginpage(base.driver);
        page.addPolicyInfo();
       /* WebElement excpCancel = base.driver.findElement(By.xpath("//*[@id=\"Cancel\"]"));
        js.executeScript("window.scrollBy(0, -500)");
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        excpCancel.click();*/
    }

    @And("^I click on Edit and fill details$")
    public void iClickOnEditAndFillDetails(DataTable table) throws Throwable {
        //   scenarioDef.createNode(new GherkinKeyword("And"), "I click on Edit and fill details");
        out.println("Edit & enter details");
        List<editexcpvalues> values = new ArrayList<editexcpvalues>();
        values = table.asList(editexcpvalues.class);
        Loginpage page = new Loginpage(base.driver);
        for (editexcpvalues edit : values) {
            page.editexception(edit.policy1number, edit.policycarrier, edit.policy1effdate, edit.policyexpdate);
        }
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1500)");
        Thread.sleep(3000);
    }

    public class editexcpvalues {
        public String policy1number, policycarrier, policy1effdate, policyexpdate;

        public editexcpvalues(String Policy1Number, String PolicyCarrier, String Policy1EffDate, String PolicyExpDate) {
            policy1number = Policy1Number;
            policycarrier = PolicyCarrier;
            policy1effdate = Policy1EffDate;
            policyexpdate = PolicyExpDate;
        }
    }

    @And("^click on Save btn$")
    public void clickOnSaveBtn() throws Throwable {
        //    scenarioDef.createNode(new GherkinKeyword("And"), "click on Save btn");
        out.println("Clicks Save");
        Loginpage page = new Loginpage(base.driver);
        page.save();
        Thread.sleep(5000);
    }

    @And("^I verify validation message for exception cert fields$")
    public void iVerifyValidationMessageForExceptionCertFields() throws Throwable {
        out.println("verify validation message for exception cert fields");
        String exp_validmsg = "* Please fill all the required fields before saving.";
        if (driver.getPageSource().contains(exp_validmsg)) {
            out.println("Validation text is :" + exp_validmsg);
            test.testgoogle.addResultForTestCase("846", TEST_CASE_PASSED_STATUS, "");
        } else {
            test.testgoogle.addResultForTestCase("846", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @And("^I click on Cancel button in exception$")
    public void iClickOnCancelButtonInException() throws Throwable {
        out.println("Clicks Cancel button in exception");
        Loginpage page = new Loginpage(base.driver);
        page.cancel();
        Thread.sleep(5000);
    }

    @When("^I click on Register button$")
    public void iClickOnRegisterButton() throws Throwable {
        out.println("Clicks Register button");
        Loginpage page = new Loginpage(base.driver);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        page.register();
        Thread.sleep(3000);
    }

    @And("^I click on View my certs! button$")
    public void iClickOnViewMyCertsButton() throws Throwable {
        out.println("Clicks view my certs! button");
        Loginpage page = new Loginpage(base.driver);
        page.viewmyCerts();
        Thread.sleep(3000);
    }

    @And("^I enter access code$")
    public void iEnterAccessCode(DataTable table) throws Throwable {
        List<Access> code = new ArrayList<>();
        code = table.asList(Access.class);
        Loginpage page = new Loginpage(base.driver);
        for (Access codes : code) {
            page.accessCode(codes.accesscode);
        }
        out.println("enters access code");
        Thread.sleep(3000);
    }

    public class Access {
        public String accesscode;

        public Access(String AccessCode) {
            accesscode = AccessCode;
        }
    }

    @And("^I fill details in registerInfo page$")
    public void iFillDetailsInRegisterInfoPage(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("enters register info details");
        List<RegisterInfo> info = new ArrayList<>();
        info = table.asList(RegisterInfo.class);
        for (RegisterInfo reg : info) {
            page.registerInfo(reg.name, reg.password, reg.confirmpassword, reg.address, reg.email);  //
        }
        Thread.sleep(5000);
    }

    public class RegisterInfo {
        public String name, password, confirmpassword, address, email;

        public RegisterInfo(String Name, String Password, String ConfirmPassword, String Address, String Email) {
            name = Name;
            password = Password;
            confirmpassword = ConfirmPassword;
            address = Address;
            email = Email;
        }
    }

    @When("^I click on Forgot Password link$")
    public void iClickOnForgotPasswordLink() throws Throwable {
        out.println("Clicks Forgot Password link");
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        page.forgotPwd();
        Thread.sleep(3000);
    }

    @And("^I enter email in Forgot password pop-up$")
    public void iEnterEmailInForgotPasswordPopUp(DataTable table) throws Throwable {
        List<forgotpwd> email = new ArrayList<>();
        email = table.asList(forgotpwd.class);
        Loginpage page = new Loginpage(base.driver);
        for (forgotpwd enter : email) {
            page.forgotPwdemail(enter.forgotpwdemail);
        }
        out.println("enters email address in Forgot password");
        Thread.sleep(3000);
    }

    public class forgotpwd {
        public String forgotpwdemail;

        public forgotpwd(String ForgotPwdEmail) {
            forgotpwdemail = ForgotPwdEmail;
        }
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.forgotSubmit();
        out.println("clicks Reset My Password button");
        Thread.sleep(3000);
    }

   /* @And("^I click on cancel button$")
    public void iClickOnCancelButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.forgotCancel();
        Thread.sleep(3000);
    }*/

    @And("^I verify for Revised watermark$")
    public void iVerifyForRevisedWatermark() throws Throwable {
        out.println("Verify Revised watermark");
        base.driver.navigate().to(newTab1);
        Thread.sleep(5000);
        Loginpage page = new Loginpage(base.driver);
        page.currentVersion();
        out.println("Current Version cert is:" + base.driver.getCurrentUrl());
        Thread.sleep(5000);
    }

    @And("^I click on View button in certificate list$")
    public void iClickOnViewButtonInCertificateList() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        String oldTab = driver.getWindowHandle();
        page.view_Certlist();
        out.println("Clicks View button in certificate list");
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        newTab1 = base.driver.getCurrentUrl();
        out.println("Revised cert is:" + newTab1);
        Thread.sleep(5000);
        base.driver.switchTo().window(oldTab);   //Switching frames
        Thread.sleep(5000);
    }

    @When("^I click on Enable/Disable Notification$")
    public void iClickOnEnableDisableNotification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks on Enable/Disable Notification button");
        page.enabledisablenotification();
        Thread.sleep(5000);
    }

    @And("^I edit New Certificate Upload Notification status$")
    public void iEditNewCertificateUploadNotificationStatus() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Edits on Enable/Disable Notification button");
        page.notification_btn();
        Thread.sleep(3000);
    }

    @And("^I verify that elements in page are disabled by default$")
    public void iVerifyThatElementsInPageAreDisabledByDefault() throws Throwable {
        WebElement excpview = driver.findElement(By.id("CertsForm"));
        List<WebElement> tablerows = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(excpview, By.tagName("td")));
        //  System.out.println(tablerows);
        List<WebElement> uniqueVal = driver.findElements(By.className("exceptionInput"));
        //System.out.println(uniqueVal.get(0).getAttribute("disabled")); //It will return true if input box disabled else return false

        if (uniqueVal.get(0).isEnabled()) { //condition to check whether input box is enabled or disabled
            out.println("Exception view page fields are Enabled");
            test.testgoogle.addResultForTestCase("853", TEST_CASE_FAILED_STATUS, "");
        } else {
            out.println("Exception view page fields are Disabled");
            test.testgoogle.addResultForTestCase("853", TEST_CASE_PASSED_STATUS, "");
        }
        int z = 0;
        for (int i = 0; i < uniqueVal.size(); i++) {
            //System.out.println(uniqueVal.get(i).getAttribute("disabled"));
            if (uniqueVal.get(0).isEnabled()) {
                z = 1;
            } else {
                z = 0;
            }
        }

        if (z == 0) {
            //Write for Disable code as required
        } else {
            //Write for Enable code as required
        }
    }

    @When("^I click on Batches link$")
    public void iClickOnBatchesLink() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Batches on menu header");
        page.batchesmenu();
    }

    @When("^I search with columns in batches header grid$")
    public void iSearchWithColumnsInBatchesHeaderGrid(DataTable table) throws Throwable {
        out.println("Batch header grid search");
        List<SearchBatchesvalues> batch = new ArrayList<>();
        batch = table.asList(SearchBatchesvalues.class);
        Loginpage page = new Loginpage(base.driver);
        for (SearchBatchesvalues batches : batch) {
            page.batchessearchgrid(batches.batchid, batches.batchname, batches.batch_adddate_from, batches.batch_adddate_to, batches.primaryinsured,
                    batches.primaryholder, batches.batch_status, batches.batch_company_group);
        }
    }

    public class SearchBatchesvalues {
        public String batchid, batchname, batch_adddate_from, batch_adddate_to, primaryinsured, primaryholder, batch_status, batch_company_group;

        public SearchBatchesvalues(String BatchID, String BatchName, String Batch_Adddate_From, String Batch_Adddate_to, String PrimaryInsured, String PrimaryHolder, String Batch_Status, String Batch_Company_Group) {
            batchid = BatchID;
            batchname = BatchName;
            batch_adddate_from = Batch_Adddate_From;
            batch_adddate_to = Batch_Adddate_to;
            primaryinsured = PrimaryInsured;
            primaryholder = PrimaryHolder;
            batch_status = Batch_Status;
            batch_company_group = Batch_Company_Group;
        }
    }

    @And("^I click on Clear All Filters button for Batches$")
    public void iClickOnClearAllFiltersButtonForBatches() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.batches_clear();
        Thread.sleep(3000);
    }

    @And("^I click on Batch Id cert$")
    public void iClickOnBatchIdCert() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Batch id view cert");
        page.batchcertid();
        Thread.sleep(3000);
    }

    @When("^I search with columns in batchDetail header grid$")
    public void iSearchWithColumnsInBatchDetailHeaderGrid(DataTable table) throws Throwable {
        out.println("BatchDetail header grid search");
        List<BatchDetail> batchesdetail = new ArrayList<>();
        batchesdetail = table.asList(BatchDetail.class);
        Loginpage page = new Loginpage(base.driver);
        for (BatchDetail batchlist : batchesdetail) {
            page.batchdetailsearchgrid(batchlist.bd_companyname, batchlist.bd_companytype, batchlist.bd_noofcerts, batchlist.bd_status);
        }
        Thread.sleep(5000);
    }

    public class BatchDetail {
        public String bd_companyname, bd_companytype, bd_noofcerts, bd_status;

        public BatchDetail(String BD_CompanyName, String BD_CompanyType, String BD_NoofCerts, String BD_Status) {
            bd_companyname = BD_CompanyName;
            bd_companytype = BD_CompanyType;
            bd_noofcerts = BD_NoofCerts;
            bd_status = BD_Status;
        }
    }

    @And("^I perform click events in Batch Detail page Action column$")
    public void iPerformClickEventsInBatchDetailPageActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.batchDetail_Action();
        Thread.sleep(6000);
    }

    @And("^I click on View icon in Batch Detail page Action column$")
    public void iClickOnViewIconInBatchDetailPageActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.batchDetail_Action_View();
        Thread.sleep(3000);
    }

    @And("^I click on cert id to view certificate$")
    public void iclickoncertidtoviewcertificate(DataTable table) throws Throwable {
        List<batchcertificates> batchcertsgridsearch = new ArrayList<>();
        batchcertsgridsearch = table.asList(batchcertificates.class);
        Loginpage page = new Loginpage(base.driver);
        for (batchcertificates search : batchcertsgridsearch) {
            page.batchcert_certid(search.batchcert_certid, search.insured, search.holder, search.issuer, search.expirationdatefrom, search.expirationdateto, search.issuedfrom,
                    search.issuedto, search.uploaddatefrom, search.uploaddateto, search.insuredstatus, search.holderstatus);
        }
        Thread.sleep(3000);
    }

    public class batchcertificates {
        public String batchcert_certid, insured, holder, issuer, expirationdatefrom, expirationdateto, issuedfrom,
                issuedto, uploaddatefrom, uploaddateto, insuredstatus, holderstatus;

        public batchcertificates(String CertId, String Insured, String Holder, String Issuer, String ExpirationDateFrom, String ExpirationDateTo,
                                 String IssuedFrom, String IssuedTo, String UploadDateFrom, String UploadDateTo, String InsuredStatus, String HolderStatus) {
            batchcert_certid = CertId;
            insured = Insured;
            holder = Holder;
            issuer = Issuer;
            expirationdatefrom = ExpirationDateFrom;
            expirationdateto = ExpirationDateTo;
            issuedfrom = IssuedFrom;
            issuedto = IssuedTo;
            uploaddatefrom = UploadDateFrom;
            uploaddateto = UploadDateTo;
            insuredstatus = InsuredStatus;
            holderstatus = HolderStatus;
        }
    }

    @When("^I click on Clients link$")
    public void iClickOnClientsLink() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clientsmenu();
        out.println("Clicks Clients in menu header");
        Thread.sleep(3000);
    }

    @When("^I search with columns in clients header grid$")
    public void iSearchWithColumnsInClientsHeaderGrid(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<Clientsgrid> clientgridsearch = new ArrayList<>();
        clientgridsearch = table.asList(Clientsgrid.class);
        for (Clientsgrid search : clientgridsearch) {
            page.clientssearchgrid(search.cl_companyname, search.cl_companytype, search.cl_adddatefrom, search.cl_adddateto, search.cl_favorite, search.cl_compregistered, search.cl_company_group);
        }
        Thread.sleep(5000);
        out.println("Performs Clients header grid search");
    }

    public class Clientsgrid {
        public String cl_companyname, cl_companytype, cl_adddatefrom, cl_adddateto, cl_favorite, cl_compregistered, cl_company_group;

        public Clientsgrid(String CompanyName, String CompanyType, String cl_AdddateFrom, String cl_AdddateTo, String Favorite, String CompRegistered, String cl_CompanyGroup) {
            cl_companyname = CompanyName;
            cl_companytype = CompanyType;
            cl_adddatefrom = cl_AdddateFrom;
            cl_adddateto = cl_AdddateTo;
            cl_favorite = Favorite;
            cl_compregistered = CompRegistered;
            cl_company_group = cl_CompanyGroup;
        }
    }

    @And("^I click favorite icon$")
    public void iClickFavoriteIcon() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.favorite();
        out.println("Clicks favorite icon in red");
        Thread.sleep(2000);
    }

    @And("^I click on clients company name view$")
    public void iClickOnClientsCompanyNameView() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clientsview();
        out.println("Clicks cert under company name column");
        Thread.sleep(4000);
    }

    @And("^I click on cert Id client list view$")
    public void iClickOnCertIdClientListView() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clientListViewCerts();
        Thread.sleep(3000);
    }

    @When("^I click on backward button and verify Clear all functionality$")
    public void iClickOnBackwardButtonAndVerifyClearAllFunctionality() throws Throwable {
        //   base.driver.navigate().back();
        Thread.sleep(3000);
        Loginpage page = new Loginpage(base.driver);
        page.client_clear();
        //  page.clear();
        System.out.println("Clicks clear All button in Clients tab");
        Thread.sleep(5000);
    }

    @And("^I click on Paper Registration menu$")
    public void iClickOnPaperRegistrationMenu() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.paperRegistrationpage();
        out.println("Clicks on paper Registration menu");
        Thread.sleep(3000);
    }

    @When("^I click on Flag for paper Registration to verify validation message$")
    public void iClickOnFlagForPaperRegistrationToVerifyValidationMessage() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.flag();
        out.println("Clicks on Flag for paper Registration");
    }

    @When("^I search with columns in paper Registration$")
    public void iSearchWithColumnsInPaperRegistration(DataTable table) throws Throwable {
        List<searchpaperreggrid> paperregistration = new ArrayList<>();
        paperregistration = table.asList(searchpaperreggrid.class);
        Loginpage page = new Loginpage(base.driver);
        for (searchpaperreggrid search : paperregistration) {
            page.papregsearchgrid(search.account, search.company, search.companytype, search.brokercompany, search.paperadddatefrom, search.paperadddateto);
        }
        out.println("Searches with company in grid paper Registration");
        Thread.sleep(3000);
    }

    public class searchpaperreggrid {
        public String account, company, companytype, brokercompany, paperadddatefrom, paperadddateto;

        public searchpaperreggrid(String Account, String Company, String CompanyType, String BrokerCompany, String PaperAddDateFrom, String PaperAddDateTo) {
            account = Account;
            company = Company;
            companytype = CompanyType;
            brokercompany = BrokerCompany;
            paperadddatefrom = PaperAddDateFrom;
            paperadddateto = PaperAddDateTo;
        }
    }

    @And("^I get the status of Letters Sent column$")
    public void iGetTheStatusOfLettersSentColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.letter_sent();
    }

    @And("^I get the status of Total Letters Sent column$")
    public void I_get_the_status_of_Total_Letters_Sent_column() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.Total_letter_sent();
    }

    @When("^I select checkbox under select user/row column and flag$")
    public void iSelectCheckboxUnderSelectUserRowColumnAndflag() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("selects Check boxes for flag");
        page.selectrow();
        Thread.sleep(4000);
        page.flagforPR();
        Thread.sleep(4000);
    }

    @And("^I click on Terms and Conditions hyperlink$")
    public void iClickOnTermsAndConditionsHyperlink() throws Throwable {
        out.println("Clicks terms and conditions hyperlink");
        Loginpage page = new Loginpage(base.driver);
        page.termsandcond_hyperlink();
        Thread.sleep(3000);
    }

    @And("^I accept the checkbox terms and conditions$")
    public void iAcceptTheCheckboxTermsAndConditions() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.termsandcond();
        Thread.sleep(3000);
        out.println("select Checkbox for terms and conditions");
    }

    @Given("^I navigate to login page of print shop app$")
    public void iNavigateToLoginPageOfPrintShopApp() throws Throwable {
        base.driver.get("http://login.dev.patracloud.net/auth");
        base.driver.manage().window().maximize();
        System.out.println("Navigates to PS Login page\n");
    }

    @When("^I select Printshop application box$")
    public void iSelectPrintshopApplicationBox() {
        System.out.println("Before switching tab title is:" + base.driver.getTitle());
        base.driver.get("http://printshop.dev.patracloud.net");
        String ParentWindow = base.driver.getWindowHandle();
        System.out.println("After switching tab title is:" + base.driver.getTitle());
        Set<String> allwindows = base.driver.getWindowHandles();
        for (String window : allwindows) {
            if (!allwindows.equals(ParentWindow)) {
                base.driver.switchTo().window(window);
            }
        }
    }

    @And("^I click on Assigned To Printshop KPI$")
    public void iClickOnAssignedToPrintshopKPI() throws InterruptedException {
        out.println("Clicks Assigned to printshop kPI");
        Loginpage page = new Loginpage(base.driver);
        page.assignedtoPS_KPI();
    }

    @When("^I search with columns in Processing tab$")
    public void iSearchWithColumnsInProcessingTab(DataTable table) throws InterruptedException {
        Loginpage page = new Loginpage(base.driver);
        out.println("searches for service and mailing type");
        List<PSgrid> psgridsearch = new ArrayList<>();
        psgridsearch = table.asList(PSgrid.class);
        for (PSgrid search : psgridsearch) {
            page.ps_gridsearch(search.service, search.mailingtype);
        }
        Thread.sleep(5000);
    }

    public class PSgrid {
        public String service, mailingtype;

        public PSgrid(String Service, String MailingType) {
            service = Service;
            mailingtype = MailingType;
        }
    }

    @And("^I click on record id$")
    public void iClickOnRecordId() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.record_ID();
        System.out.println("Clicks PS record to view");
        Thread.sleep(4000);
    }

    @And("^I click on Attachments tab$")
    public void iClickOnAttachmentsTab() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.attachments_PS();
        System.out.println("Clicks on PS Attachments tab");
        Thread.sleep(3000);
    }

    @And("^I click on link under Attachments tab$")
    public void iClickOnLinkUnderAttachmentsTab() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.view_attach();
        System.out.println("Clicks on Attachment link to view");
        Thread.sleep(3000);
        String currentWindow = base.driver.getWindowHandle();  //Switching frames
        base.driver.switchTo().window(currentWindow);

    }

    @And("^I click on Upload and Print tab$")
    public void iClickOnUploadAndPrintTab() {
        Loginpage page = new Loginpage(base.driver);
        page.uploadprint();
        System.out.println("Clicks Upload & Print tab");
    }

    @And("^i verify validation for checking flags$")
    public void iVerifyValidationForCheckingFlags() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.valid_checkflag();
        Thread.sleep(3000);
    }

    @And("^I check Insured mail or Holder mail checkbox$")
    public void iCheckInsuredMailOrHolderMailCheckbox() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.checkflag();
        Thread.sleep(3000);
    }

    @And("^I click on Upload and send mail button$")
    public void iClickOnUploadAndSendMailButton() {
        System.out.println("Clicks Upload & send mail btn");
        Loginpage page = new Loginpage(base.driver);
        page.upload_sendmail();
    }

    @And("^Paper registration Email registration and Fax registration buttons do not be accessible for users and admin$")
    public void paper_registration_email_registration_and_fax_registration_buttons_do_not_be_accessible_for_users_and_admin() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Thread.sleep(3000);
        page.validate_paperreg();
        Thread.sleep(3000);
        page.validate_emailreg();
        Thread.sleep(3000);
        page.validate_faxreg();
    }

    @And("^Add Supporting document button not accessible for users and admin$")
    public void add_Supporting_document_button_not_accessible_for_users_and_admin() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.addSuppDoc();
    }

    @And("^I login into gmail account for email notification$")
    public void I_login_into_gmail_account_for_email_notification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.gmail_account();
        Thread.sleep(5000);
    }

    @And("^Delete Supporting document button not accessible for users and admin$")
    public void delete_Supporting_document_button_not_accessible_for_users_and_admin() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.viewSuppDelete();
        Thread.sleep(6000);
    }

    @When("^I click on Profile icon and Claim access code$")
    public void iClickOnProfileIconAndClaimAccessCode() throws Throwable {
        out.println("Clicks Profile icon");
        Loginpage page = new Loginpage(base.driver);
        page.userAccessCode();  //access code()
    }

    @And("^I enter access code to claim$")
    public void iEnterAccessCodeToClaim(DataTable table) throws Throwable {
        List<Accessclaim> code = new ArrayList<>();
        code = table.asList(Accessclaim.class);
        Loginpage page = new Loginpage(base.driver);
        for (Accessclaim codes : code) {
            page.accessCodeClaim(codes.accesscodeclaim);
        }
        out.println("enters access code");
        Thread.sleep(3000);
    }

    public class Accessclaim {
        public String accesscodeclaim;

        public Accessclaim(String AccessCodeClaim) {
            accesscodeclaim = AccessCodeClaim;
        }
    }

    @Then("^I click on Submit button to claim$")
    public void iClickOnSubmitButtonToClaim() throws Throwable {
        out.println("Clicks Profile icon");
        Thread.sleep(3000);
        Loginpage page = new Loginpage(base.driver);
        page.Submit_access_code();  //access code()
    }

    @And("^I click on Proposed Company Merge Tab$")
    public void I_click_on_Proposed_Company_Merge_Tab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.proposed_company_merge();
        out.println("Clicks Proposed Company Merge Tab");
        Thread.sleep(3000);
    }

    @And("^I click on Proposed Company Merge Clear All Filters button$")
    public void I_click_on_Proposed_Company_Merge_Clear_All_Filters_button() throws Throwable {
        out.println("Clear All Filters button in Proposed Company Merge");
        Loginpage page = new Loginpage(base.driver);
        page.proposedmergeclear();
        Thread.sleep(3000);
    }

    @When("^I search with columns in header grid for Proposed merge header grid$")
    public void I_search_with_columns_in_header_grid_for_Proposed_merge_header_grid(DataTable table) throws Throwable {
        out.println("Enter search data in search columns for Proposed merge");
        List<ProposedSearch> search = new ArrayList<ProposedSearch>();
        search = table.asList(ProposedSearch.class);
        Loginpage page = new Loginpage(base.driver);
        for (ProposedSearch Proposedfields : search) {
            page.Proposedvalueassign(Proposedfields.userrequestingmerge, Proposedfields.userscompany, Proposedfields.userscompanytype, Proposedfields.userscompanytypeaddress, Proposedfields.userscompanyid, Proposedfields.companytobemerged, Proposedfields.companytobemergedtype, Proposedfields.companytobemergedtypecompanyid, Proposedfields.companytobemergedtypeaddress, Proposedfields.adddatefrom, Proposedfields.adddateto,
                    Proposedfields.status, Proposedfields.completedby, Proposedfields.requestedby);
            Thread.sleep(10000);
        }
    }

    public class ProposedSearch {
        public String userrequestingmerge, userscompany, userscompanytype, userscompanyid, userscompanytypeaddress, companytobemerged, companytobemergedtype, companytobemergedtypecompanyid, companytobemergedtypeaddress, adddatefrom, adddateto, status, completedby, requestedby;

        public ProposedSearch(String UserRequestingMerge, String UsersCompany, String UsersCompanyType, String UsersCompanyId, String UsersCompanyTypeAddress, String CompanyToBeMerged, String CompanyToBeMergedType,
                              String CompanyToBeMergedTypeCompanyId, String CompanyToBeMergedTypeAddress, String AddDateFrom, String AddDateTo, String Status, String CompletedBy, String RequestedBy) {
            userrequestingmerge = UserRequestingMerge;
            userscompany = UsersCompany;
            userscompanytype = UsersCompanyType;
            userscompanyid = UsersCompanyId;
            userscompanytypeaddress = UsersCompanyTypeAddress;
            companytobemerged = CompanyToBeMerged;
            companytobemergedtype = CompanyToBeMergedType;
            companytobemergedtypecompanyid = CompanyToBeMergedTypeCompanyId;
            companytobemergedtypeaddress = CompanyToBeMergedTypeAddress;
            adddatefrom = AddDateFrom;
            adddateto = AddDateTo;
            status = Status;
            completedby = CompletedBy;
            requestedby = RequestedBy;
        }
    }

    @And("^I get the status of proposed company merge$")
    public void I_get_the_status_of_proposed_company_merge() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.proposestatus();
        System.out.println("Getting status of proposed merged");
    }

    @When("^I click on deny button from action column$")
    public void I_click_on_deny_button_from_action_column() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.proposedeny();
    }

    @When("^I click on approve button from action column$")
    public void I_click_on_approve_button_from_action_column() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.proposeapprove();
    }

    @When("^I click on Archived Merges$")
    public void iClickOnArchivedMerges() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.archivedmerges();
        Thread.sleep(3000);
    }

    @And("^I verify archived merges should show only merged or declined records$")
    public void iVerifyArchivedMergesShouldShowOnlyMergedOrDeclinedRecords() throws Exception {
        if(driver.getPageSource().contains("Merged") || driver.getPageSource().contains("Declined"))  {
            System.out.println("Archived merges showed only merged/declined records");
            addResultForTestCase("12859", TEST_CASE_PASSED_STATUS, "");
        }
        if(driver.getPageSource().contains("Pending")) {
            System.out.println("Archived merges showed pending records");
            addResultForTestCase("12859", TEST_CASE_FAILED_STATUS, "");
        } else {
            System.out.println("Archived merges don't have any pending records-which is valid");
        }
    }

    @And("^I verify Active proposed Merges should show only pending merges$")
    public void iVerifyActiveProposedMergesShouldShowOnlyPendingMerges() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.activeproposedmerges();
        Thread.sleep(2000);
        if(driver.getPageSource().contains("Pending")) {
            System.out.println("Active proposed Merges displayed only pending merges");
            addResultForTestCase("12859", TEST_CASE_PASSED_STATUS, "");
        }
        if(driver.getPageSource().contains("Merged") || driver.getPageSource().contains("Declined")) {
            System.out.println("Active proposed Merges showed Merged, Declined instead of pending merges");
            addResultForTestCase("12859", TEST_CASE_FAILED_STATUS, "");
        }
    }

    @When("^I click on profile icon and switch company$")
    public void I_click_on_profile_icon_and_switch_company(DataTable table) throws Throwable {
        List<switchcompany> comp = new ArrayList<>();
        comp = table.asList(switchcompany.class);
        Loginpage page = new Loginpage(base.driver);
        for (switchcompany assign : comp) {
            page.switchCompany(assign.switch_company);
        }
        Thread.sleep(5000);
    }
    public class switchcompany {
        public String switch_company;

        public switchcompany(String SwitchCompany) {
            switch_company = SwitchCompany;
        }
    }

    @And("^I verify the email cert notification$")
    public void iVerifyTheEmailCertNotification() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.email_cert_notification();
        Thread.sleep(5000);
    }
    @And("^I verify the email blocked notification$")
    public void iVerifyTheEmailBlockedNotification() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.email_cert_notification();
        Thread.sleep(5000);
    }

    @And("^I verify the Delivery failure email notification$")
    public void I_verify_the_Delivery_failure_email_notification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.delivery_failure_notification();
        Thread.sleep(5000);
    }

    @And("^I verify the Email Registration letter for Insured and Holder user$")
    public void I_verify_the_Email_Registration_letter_for_Insured_and_Holder_user() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.email_reg_notification();
        Thread.sleep(5000);
    }

    @And("^I verify the single cert upload notification$")
    public void I_verify_the_single_cert_upload_notification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.single_cert_notification();
        Thread.sleep(5000);
    }

    @And("^I verify the renewal cert notification$")
    public void I_verify_the_renewal_cert_notification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.renewal_cert_notification();
        Thread.sleep(5000);
    }

    @And("^I verify the Forgot Password notification$")
    public void I_verify_the_Forgot_Password_notification() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.forgot_password_notification();
        Thread.sleep(5000);
    }

    @And("^I click on Blocked Domains tab$")
    public void iClickOnBlockedDomainsTab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.blockeddomain();
        System.out.println("clicks blocked domain link");
        Thread.sleep(5000);
    }

    @When("^I search with columns in blocked domain header grid$")
    public void iSearchWithColumnsInBlockedDomainHeaderGrid(DataTable table) throws Exception {
        List<search_BD> bdgrid = new ArrayList<>();
        bdgrid = table.asList(search_BD.class);
        System.out.println("performs grid search ");
        Loginpage page = new Loginpage(base.driver);
        for (search_BD grid : bdgrid) {
            page.search_BD(grid.id, grid.domainname, grid.company, grid.status, grid.adddatefrom, grid.adddateto);
        }
        Thread.sleep(3000);
    }

    public class search_BD {
        public String id, domainname, company, status, adddatefrom, adddateto;

        public search_BD(String ID, String DomainName, String Company, String Status, String AddDateFrom, String AddDateTo) {
            id = ID;
            domainname = DomainName;
            company = Company;
            status = Status;
            adddatefrom = AddDateFrom;
            adddateto = AddDateTo;
        }
    }

    @And("^I click on Clear All Filters button for Blocked Domains$")
    public void iClickOnClearAllFiltersButtonForBlockedDomains() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.blockeddomainclear();
        System.out.println("perform clear all - blocked domain");
        Thread.sleep(3000);
    }

    @And("^I click on blocked Domain Delete icon$")
    public void iClickOnBlockedDomainDeleteIcon() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.blockeddomain_delete();
        System.out.println("clicks delete icon under Action column");
    }

    @And("^I click on Add Blocked Domain button$")
    public void iClickOnAddBlockedDomainButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addblockeddomain();
        System.out.println("clicks Add Blocked Domain button");
        Thread.sleep(3000);
    }

    @And("^I click on Add Blocked Domain Save button$")
    public void iClickOnAddBlockedDomainSaveButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addbd_savebtn();
        System.out.println("clicks save btn add blocked domain");
        Thread.sleep(3000);
    }

    @And("^I enter details in Add Blocked Domain pop-up$")
    public void iEnterDetailsInAddBlockedDomainPopUp(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<addblckdomain> block = new ArrayList<>();
        block = table.asList(addblckdomain.class);
        for (addblckdomain add : block) {
            page.filladdblockdomain(add.blockedtype, add.domainemail, add.status, add.company);
        }
        System.out.println("enters details in add blocked domain");
        Thread.sleep(3000);
    }

    public class addblckdomain {
        public String blockedtype, domainemail, status, company;

        public addblckdomain(String BlockedType, String DomainEmail, String Status, String Company) {
            blockedtype = BlockedType;
            domainemail = DomainEmail;
            status = Status;
            company = Company;
        }
    }

    @And("^I enter fax details in Add Blocked Domain pop-up$")
    public void iEnterFaxDetailsInAddBlockedDomainPopUp(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<addfaxrecord> fax = new ArrayList<>();
        fax = table.asList(addfaxrecord.class);
        for (addfaxrecord add : fax) {
            page.fillfaxrecord(add.blockedtype, add.fax);
        }
        System.out.println("enters details for Fax in add blocked domain");
        Thread.sleep(3000);
    }

    public class addfaxrecord {
        public String blockedtype, fax;

        public addfaxrecord(String BlockedType, String Fax) {
            blockedtype = BlockedType;
            fax = Fax;
        }
    }


    @And("^I click on x icon for Add blocked Domain form$")
    public void iClickOnXIconForAddBlockedDomainForm() {
        Loginpage page = new Loginpage(base.driver);
        page.xicon_bd();
        System.out.println("Clicks X icon");
    }

    @And("^I click on edit blocked domain$")
    public void iClickOnEditBlockedDomain() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.edit_bd();
        Thread.sleep(3000);
    }

    @And("^I click on Paper Letter History button$")
    public void iClickOnPaperLetterHistoryButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.letterhistory_PR();
        Thread.sleep(3000);
    }

    @When("^I search with columns in Paper Letter History grid$")
    public void iSearchWithColumnsInPaperLetterHistoryGrid(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<searchletterhis> search = new ArrayList<>();
        search = table.asList(searchletterhis.class);
        for (searchletterhis grid : search) {
            page.searchletterhisgrid(grid.rootcomp, grid.companygrp, grid.letters, grid.dategenfrom, grid.dategento, grid.lettertype, grid.comptype, grid.genby);
        }
        Thread.sleep(3000);
    }

    public class searchletterhis {
        public String rootcomp, companygrp, letters, dategenfrom, dategento, lettertype, comptype, genby;

        public searchletterhis(String RootComp, String CompanyGrp, String Letters, String DategenFrom, String DategenTo, String LetterType, String CompType, String GenBy) {
            rootcomp = RootComp;
            companygrp = CompanyGrp;
            letters = Letters;
            dategenfrom = DategenFrom;
            dategento = DategenTo;
            lettertype = LetterType;
            comptype = CompType;
            genby = GenBy;
        }
    }

    @And("^I click on Clear All Filters button for paper letter history$")
    public void iClickOnClearAllFiltersButtonForPaperLetterHistory() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clearbtn_lettershis();
        Thread.sleep(3000);
    }

    @Then("^I verify validation for Assign companies dropdown$")
    public void I_verify_validation_for_Assign_companies_dropdown() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.assigncompval();
        Thread.sleep(3000);
    }

    @And("^I click on Assign companies dropdown$")
    public void I_click_on_Assign_companies_dropdown(DataTable table) throws Throwable {
        WebElement assigncomp = driver.findElement(By.xpath("//button[@id='dropdownMenu1']"));  //assign companies
        assigncomp.click();
        List<assigncompanies> comp = new ArrayList<>();
        comp = table.asList(assigncompanies.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("select the companies");
        for (assigncompanies assign : comp) {
            page.assignCompanies(assign.assigncompany);
        }
        Thread.sleep(5000);
    }

    public class assigncompanies {
        public String assigncompany;

        public assigncompanies(String AssignCompany) {
            assigncompany = AssignCompany;
        }
    }

    @And("^I click on edit user button$")
    public void I_click_on_edit_user_button(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        out.println("Clicks Edit symbol and fill edit user form");
        List<adduser> user = new ArrayList<adduser>();
        user = table.asList(adduser.class);
        for (adduser add : user) {
            page.editUser(add.name, add.role, add.emailaddress, add.pwd);
        }
    }

    @And("^I click on switch company for cert uploader user$")
    public void I_click_on_switch_company_for_cert_uploader_user(DataTable table) throws Throwable {
        WebElement Switch_comp = driver.findElement(By.xpath("/html/body/div[2]/nav/ul/li[5]/a/select"));  //switch company
        Switch_comp.click();
        List<switchcompanies> comp = new ArrayList<>();
        comp = table.asList(switchcompanies.class);
        Loginpage page = new Loginpage(base.driver);
        for (switchcompanies assign : comp) {
            page.switchCompanies(assign.switch_company);
        }
        Thread.sleep(5000);
    }

    public class switchcompanies {
        public String switch_company;

        public switchcompanies(String SwitchCompany) {
            switch_company = SwitchCompany;
        }
    }

    @And("^I verify validation for Fax Registration form in view cert$")
    public void I_verify_validation_for_Fax_Registration_form_in_view_cert() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.validate_faxreg();
        Thread.sleep(4000);
    }

    @And("^I verify the remove Fax functionality$")
    public void I_verify_the_remove_Fax_functionality() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.remove_fax();
        Thread.sleep(4000);
    }

    @And("^I handle blocked fax registration functionality$")
    public void I_handle_blocked_fax_registration_functionality(DataTable table) throws Exception {
        List<blockfax> blockedfax = new ArrayList<>();
        blockedfax = table.asList(blockfax.class);
        Loginpage page = new Loginpage(base.driver);
        for (blockfax enter : blockedfax) {
            page.blockfaxreg(enter.enter_blocked_fax);
        }
        Thread.sleep(5000);
    }

    public class blockfax {
        public String enter_blocked_fax;

        public blockfax(String Enter_Blocked_Fax) {
            enter_blocked_fax = Enter_Blocked_Fax;
        }
    }

    @And("^I handle blocked email registration functionality$")
    public void I_handle_blocked_email_registration_functionality(DataTable table) throws Exception {
        List<blockemail> blockedemail = new ArrayList<>();
        blockedemail = table.asList(blockemail.class);
        Loginpage page = new Loginpage(base.driver);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        for (blockemail enter : blockedemail) {
            page.emailreg(enter.enter_blocked_email);
        }
        Thread.sleep(5000);
    }

    public class blockemail {
        public String enter_blocked_email;

        public blockemail(String Enter_Blocked_Email) {
            enter_blocked_email = Enter_Blocked_Email;
        }
    }

    @And("^I handle fax registration button functionality$")
    public void I_handle_fax_registration_button_functionality(DataTable table) throws Exception {
        List<faxregistration> fax = new ArrayList<>();
        fax = table.asList(faxregistration.class);
        Loginpage page = new Loginpage(base.driver);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        for (faxregistration enter : fax) {
            page.faxreg(enter.enterfax);
        }
        Thread.sleep(5000);
    }

    public class faxregistration {
        public String enterfax;

        public faxregistration(String EnterFax) {
            enterfax = EnterFax;
        }
    }

    @When("^I click on Fax blocklist button$")
    public void iClickOnFaxBlocklistButton() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.faxblocklist();
        Thread.sleep(3000);
    }

    @When("^I search with columns in fax blocklist header grid$")
    public void iSearchWithColumnsInFaxBlocklistHeaderGrid(DataTable table) throws Throwable {
        List<search_Faxblocklist> searchfax = new ArrayList<>();
        searchfax = table.asList(search_Faxblocklist.class);
        Loginpage page = new Loginpage(base.driver);
        for (search_Faxblocklist search : searchfax) {
            page.faxgridsearch(search.id, search.faxnumber, search.adddatefrom, search.adddateto);
        }
        System.out.println("performs grid search in fax blocklist");
    }

    public class search_Faxblocklist {
        public String id, faxnumber, adddatefrom, adddateto;

        public search_Faxblocklist(String ID, String FaxNumber, String AddDateFrom, String AddDateTo) {
            String id = ID;
            String faxnumber = FaxNumber;
            String adddatefrom = AddDateFrom;
            String stadddatetoatus = AddDateTo;
        }
    }

    @And("^I click on edit icon for fax blocklist$")
    public void iClickOnEditIconForFaxBlocklist(DataTable table) throws Throwable {
        List<editfaxnum> edit = new ArrayList<>();
        edit = table.asList(editfaxnum.class);
        Loginpage page = new Loginpage(base.driver);
        for (editfaxnum editnum : edit) {
            page.editfax(editnum.faxnumber);
        }
        System.out.println("clicks on edit fax");
        Thread.sleep(3000);
    }

    public class editfaxnum {
        public String faxnumber;

        public editfaxnum(String FaxNumber) {
            faxnumber = FaxNumber;
        }
    }

    @And("^I click on Delete icon for Fax Blocklist$")
    public void iClickOnDeleteIconForFaxBlocklist() throws Throwable {
        System.out.println("clicks on delete icon fax");
        Loginpage page = new Loginpage(base.driver);
        page.deletefax();
        Thread.sleep(3000);
    }

    @And("^I verify validation for fax$")
    public void iVerifyValidationForFax() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.verifyfax();
        System.out.println("verify fax validation");
        Thread.sleep(3000);
    }

    @And("^I enter the Notification Failure email address$")
    public void I_enter_the_Notification_Failure_email_address() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.emailfailure();
        Thread.sleep(3000);
    }

    @And("^I verify Email certificate button is not visible for cert uploader user$")
    public void I_verify_Email_certificate_button_is_not_visible_forcert_uploader_user() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.validate_emailcert();
        Thread.sleep(4000);
    }

    @And("^I verify email notification failure address for companies$")
    public void I_verify_email_notification_failure_address_for_companies(DataTable table) throws Exception {
        List<emailregistration> email = new ArrayList<>();
        email = table.asList(emailregistration.class);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        for (emailregistration enter : email) {
            page.incorrectemail(enter.enteremail);
        }
        Thread.sleep(5000);
    }

    public class failure_email {
        public String enteremail;

        public failure_email(String EnterEmail) {
            enteremail = EnterEmail;
        }
    }

    @And("^I select OCR process type$")
    public void I_select_OCR_process_type(DataTable table) throws Throwable {
        WebElement Ocrtype = driver.findElement(By.xpath("//select[@id='OCRProcess']"));  //OCR select box
        WebElement editsymbol = driver.findElement(By.xpath("//*[@id=\"company-list\"]/tbody/tr/td[10]/a[1]/i"));
        editsymbol.click();
        Thread.sleep(2000);
        Ocrtype.click();
        List<ocrtypes> ocr = new ArrayList<>();
        ocr = table.asList(ocrtypes.class);
        Loginpage page = new Loginpage(base.driver);
        out.println("Select the OCR type");
        for (ocrtypes select : ocr) {
            page.ocrType(select.ocr_type);
        }
        Thread.sleep(5000);
    }

    public class ocrtypes {
        public String ocr_type;

        public ocrtypes(String OcrType) {
            ocr_type = OcrType;
        }
    }

    @And("^I click on Registration Templates tab$")
    public void iClickOnRegistrationTemplatesTab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.registrationtemplate();
        Thread.sleep(3000);
    }

    @And("^I verify the Claim Access Code Request$")
    public void I_verify_the_Claim_Access_Code_Request() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(5000);
        page.email_claim_access_code();
        Thread.sleep(5000);
    }

    @When("^I search with columns in registration template header grid$")
    public void iSearchWithColumnsInRegistrationTemplateHeaderGrid(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<search_registrationtemplate> template = new ArrayList<>();
        template = table.asList(search_registrationtemplate.class);
        for (search_registrationtemplate search : template) {
            page.searchregistemplate(search.companyid, search.companyname, search.recipienttype, search.enableddisabled);
            Thread.sleep(3000);
        }
    }

    public class search_registrationtemplate {
        public String companyid, companyname, recipienttype, enableddisabled;

        public search_registrationtemplate(String CompanyId, String CompanyName, String RecipientType, String EnabledDisabled) {
            companyid = CompanyId;
            companyname = CompanyName;
            recipienttype = RecipientType;
            enableddisabled = EnabledDisabled;
        }
    }

    @And("^I click on Clear All Filters button for Registration Templates$")
    public void iClickOnClearAllFiltersButtonForRegistrationTemplates() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.regtemp_clear();
        System.out.println("Clicks clear all - Registration template");
        Thread.sleep(3000);
    }

    @When("^I click on Add Template button$")
    public void iClickOnAddTemplateButton(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<addtemplatepopup> popup = new ArrayList<>();
        popup = table.asList(addtemplatepopup.class);
        for (addtemplatepopup enter : popup) {
            page.addregtemp(enter.companyname, enter.recipienttype);
            System.out.println("Clicks & enter details in  Add Template pop-up");
        }
        Thread.sleep(3000);
    }

    public class addtemplatepopup {
        public String companyname, recipienttype;

        public addtemplatepopup(String CompanyName, String RecipientType) {
            companyname = CompanyName;
            recipienttype = RecipientType;
        }
    }

    @And("^I click on save button in add template popup$")
    public void iClickOnSaveButtonInAddTemplatePopup() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addtemplate_save();
        Thread.sleep(3000);
    }

    @When("^I click on edit icon under Action column$")
    public void iClickOnEditIconUnderActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.edit_regtemp();
        Thread.sleep(3000);
    }

    @And("^I click on Save button in template editor$")
    public void iClickOnSaveButtonInTemplateEditor() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.save_tempeditor();
        Thread.sleep(4000);
    }

    @And("^I click on Enable/Disable button in template editor$")
    public void iClickOnEnableDisableButtonInTemplateEditor() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.enabledisable_tempeditor();
        Thread.sleep(4000);
    }

    @And("^I click on Cancel button in template editor$")
    public void iClickOnCancelButtonInTemplateEditor() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.cancel_tempeditor();
        Thread.sleep(3000);
    }

    @And("^I click on Preview button in template editor$")
    public void iClickOnPreviewButtonInTemplateEditor() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.preview_tempeditor();
        Thread.sleep(3000);
    }

    @And("^I verify logo position validations$")
    public void iVerifyLogoPositionValidations(DataTable table) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");  //scroll till page end
        Loginpage page = new Loginpage(base.driver);
        List<templatelogo> logos = new ArrayList<>();
        logos = table.asList(templatelogo.class);
        for (templatelogo editor : logos) {
            page.setlogo(editor.brokerlogo, editor.cvlogo);
        }
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(3000);
    }

    public class templatelogo {
        public String brokerlogo, cvlogo;

        public templatelogo(String BrokerLogo, String CvLogo) {
            brokerlogo = BrokerLogo;
            cvlogo = CvLogo;
        }
    }

    @And("^I verify validation for already existing records$")
    public void I_verify_validation_for_already_existing_records() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.addbd_faxexistrecord();
    }

    @And("^Verify the Company Group for current broker$")
    public void Verify_the_Company_Group_for_current_broker() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.company_group();
    }

    @And("^I click on Import Utility tab$")
    public void iClickOnImportUtilityTab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.importutility_tab();
        out.println("Clicks import utility link under Admin");
        Thread.sleep(3000);
    }

    @When("^I click on Import Holders button$")
    public void iClickOnImportHoldersButton() {
        Loginpage page = new Loginpage(base.driver);
        page.importholder();
        out.println("Clicks Import Holders button");
    }

    @When("^I click on Import Users button$")
    public void iClickOnImportUsersButton() {
        Loginpage page = new Loginpage(base.driver);
        page.importuser();
        out.println("Clicks Import Users button");
    }

    @And("^I choose csv file to upload import users$")
    public void iChooseCsvFileToUploadImportUsers() throws Throwable {
        out.println("Chooses a CSV file to Upload User import");
        String csvfilepath = attachPath("src/test/resources/import-users-csv.csv");
        driver.findElement(By.id("csv-file")).sendKeys(csvfilepath);
        WebElement uploadbtn = driver.findElement(By.id("upload-btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", uploadbtn);
        Loginpage page = new Loginpage(base.driver);
        page.uploadFile();
        Thread.sleep(3000);
        String csvsuccesspop = "CSV FILE UPLOADED SUCCESSFULLY";
        out.println("Processing CSV file");
        Thread.sleep(3000);
        if (driver.getPageSource().contains(csvsuccesspop)) {
            System.out.println("csv pop-up text is:" + driver.findElement(By.xpath("//*[@id=\"em\"]/div[3]/div/div/div/p")).getText());
            page.popbtnOK.click();
            testgoogle.addResultForTestCase("14178", TEST_CASE_PASSED_STATUS, "");  //import users
        }
    }

    @And("^I choose csv file to upload$")
    public void iChooseCsvFileToUpload() throws Throwable {
        out.println("Chooses a CSV file to Upload");
        String csvfilePath = attachPath("src/test/resources/File1.csv");
        driver.findElement(By.id("csv-file")).sendKeys(csvfilePath);
        WebElement uploadbtn = driver.findElement(By.id("upload-btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", uploadbtn);
        Loginpage page = new Loginpage(base.driver);
        page.uploadFile();
        Thread.sleep(3000);
        String csvsuccesspop = "CSV FILE UPLOADED SUCCESSFULLY";
        out.println("Processing CSV file");
        Thread.sleep(3000);
        if (driver.getPageSource().contains(csvsuccesspop)) {
            System.out.println("csv pop-up text is:" + driver.findElement(By.xpath("//*[@id=\"em\"]/div[3]/div/div/div/p")).getText());
            page.csvsuccess();
        }
    }

    @And("^I upload invalid csv file to verify validation$")
    public void iUploadInvalidCsvFileToVerifyValidation() throws Throwable {
        String invalidcsvfile = attachPath("src/test/resources/Cert for Poppy Bank.pdf");
        driver.findElement(By.id("csv-file")).sendKeys(invalidcsvfile);
        Loginpage page = new Loginpage(base.driver);
        if (driver.findElement(By.xpath("/html/body/div[4]/div/div[2]")).isDisplayed()) {
            System.out.println("Clicks Ok button in csvvalidation warning pop-up");
            page.csvvalidationpop();
            Thread.sleep(3000);
        }
    }

    @When("^I click on Import History link$")
    public void iClickOnImportHistoryLink() throws Throwable {
        System.out.println("Clicks Import history link");
        Loginpage page = new Loginpage(base.driver);
        page.importhistory();
        Thread.sleep(3000);
    }

    @When("^I search with columns in import history page$")
    public void iSearchWithColumnsInImportHistoryPage(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<search_imphist> history = new ArrayList<>();
        history = table.asList(search_imphist.class);
        for (search_imphist grid : history) {
            page.gridsearch_imphistory(grid.importid, grid.filename, grid.fromdate, grid.todate, grid.brokercompany, grid.uploadedby, grid.status);
        }
        System.out.println("perform searche in import history");
    }

    public class search_imphist {
        public String importid, filename, fromdate, todate, brokercompany, uploadedby, status;

        public search_imphist(String ImportID, String FileName, String FromDate, String ToDate, String BrokerCompany, String UploadedBy, String Status) {
            importid = ImportID;
            filename = FileName;
            fromdate = FromDate;
            todate = ToDate;
            brokercompany = BrokerCompany;
            uploadedby = UploadedBy;
            status = Status;
        }
    }

    @And("^I click on Clear All Filters button for import History$")
    public void iClickOnClearAllFiltersButtonForImportHistory() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.clear_importhistory();
        System.out.println("Clicks clear button - import history");
        Thread.sleep(3000);
    }

    @And("^I verify already existing email in Email registration form$")
    public void I_verify_already_existing_email_in_Email_registration_form(DataTable table) throws Exception {
        List<emailexist> email = new ArrayList<>();
        email = table.asList(emailexist.class);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        for (emailexist enter : email) {
            page.emailreg(enter.enteremail);
        }
        Thread.sleep(5000);
    }

    public class emailexist {
        public String enteremail;

        public emailexist(String EnterEmail) {
            enteremail = EnterEmail;
        }
    }

    @And("^I verify the functionality of add extra holder or insured in Email registration form$")
    public void I_verify_the_functionality_of_add_extra_holder_or_insured_in_Email_registration_form(DataTable table) throws Exception {
        List<addemails> email = new ArrayList<>();
        email = table.asList(addemails.class);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        for (addemails enter : email) {
            page.emailreg(enter.enteremail);
        }
        Thread.sleep(5000);
    }

    public class addemails {
        public String enteremail;

        public addemails(String EnterEmail) {
            enteremail = EnterEmail;
        }
    }

    @And("^I verify validation for blank text field in Email registration form$")
    public void IverifyvalidationforblanktextfieldinEmailregistrationform() throws Throwable {
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");
        Loginpage page = new Loginpage(base.driver);
        page.validate_emailblank();
        Thread.sleep(4000);
    }

    @When("^I click on Patra One Company list icon under Action column$")
    public void iClickOnPatraOneCompanyListIconUnderActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.patraonecompanylisticon();
        Thread.sleep(3000);
    }

    @And("^I enter details in Patra One Company list pop-up$")
    public void iEnterDetailsInPatraOneCompanyListPopUp(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<patraonepopup> one = new ArrayList<>();
        one = table.asList(patraonepopup.class);
        page.patraonepopup(one.get(0).companyname, one.get(0).metername, one.get(0).service, one.get(0).division, one.get(0).branch, one.get(0).department, one.get(0).companyforfilename);
        out.println("entering details in patraone company list pop-up");
    }

    public class patraonepopup {
        public String companyname, metername, service, division, branch, department, companyforfilename;

        public patraonepopup(String CompanyName, String MeterName, String Service, String Division, String Branch, String Department, String CompanyForFileName) {
            companyname = CompanyName;
            metername = MeterName;
            service = Service;
            division = Division;
            branch = Branch;
            department = Department;
            companyforfilename = CompanyForFileName;
        }
    }
    @And("^I verify extract details for ins/hol/broker/issued/exp date in view cert from OMIC$")
    public void iVerifyExtractDetailsForInsHolBrokerIssuedExpDateInViewCertFromOMIC() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.validate_omic();
    }
    @And("^I click on OKAY button$")
    public void I_click_on_OKAY_button() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.PopupOK();
    }

    @When("^I enter Email in post cert upload popup$")
    public void I_enter_Email_in_post_cert_upload_popup(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<enter_email> text = new ArrayList<>();
        text = table.asList(enter_email.class);
        for (enter_email enter : text) {
            Thread.sleep(3000);
            page.Enter_Email(enter.email);
        }
    }

    public class enter_email {
        public String email;

        public enter_email(String Email) {
            email = Email;
        }
    }

    @When("^I enter Fax in post cert upload popup$")
    public void I_enter_Fax_in_post_cert_upload_popup(DataTable table) throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        List<enter_fax> text = new ArrayList<>();
        text = table.asList(enter_fax.class);
        for (enter_fax enter : text) {
            Thread.sleep(3000);
            page.Enter_Fax(enter.fax);

        }
    }

    public class enter_fax {
        public String fax;

        public enter_fax(String Fax) {
            fax = Fax;
        }
    }

    @And("^I click on SMTP Configuration tab$")
    public void iClickOnSMTPConfigurationTab() throws InterruptedException {
        Loginpage page = new Loginpage(base.driver);
        page.smtp_config();
        Thread.sleep(3000);
    }

    @When("^I search with columns in SMTP Configuration header grid$")
    public void iSearchWithColumnsInSMTPConfigurationHeaderGrid(DataTable table) throws Exception {
        Loginpage page = new Loginpage(base.driver);
        List<search_smtpconfig> config = new ArrayList<>();
        config = table.asList(search_smtpconfig.class);
        for (search_smtpconfig search : config) {
            page.searchsmtpconfig(search.companyid, search.companyname, search.enableddisabled);
            Thread.sleep(3000);
        }
    }

    public class search_smtpconfig {
        public String companyid, companyname, enableddisabled;

        public search_smtpconfig(String CompanyId, String CompanyName, String EnabledDisabled) {
            companyid = CompanyId;
            companyname = CompanyName;
            enableddisabled = EnabledDisabled;
        }
    }

    @And("^I click on Clear All Filters button for SMTP Configuration$")
    public void iClickOnClearAllFiltersButtonForSMTPConfiguration() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.smtpconfig_clear();
        System.out.println("Clicks clear all - SMTP Configuration");
        Thread.sleep(3000);
    }

    @And("^I click on Add SMTP Configuration button$")
    public void iClickOnAddSMTPConfigurationButton() throws InterruptedException {
        Loginpage page = new Loginpage(base.driver);
        page.add_smtp_config();
        System.out.println("clicks Add SMTP Configuration button");
        Thread.sleep(3000);
    }

    @And("^I enter details in Add SMTP Configuration pop-up$")
    public void iEnterDetailsInAddSMTPConfigurationPopUp(DataTable table) throws InterruptedException {
        Loginpage page = new Loginpage(base.driver);
        List<addsmptconfig> config = new ArrayList<>();
        config = table.asList(addsmptconfig.class);
        for (addsmptconfig add : config) {
            page.filladdsmtpconfig(add.select_company, add.smtp_host, add.smtp_username, add.password, add.outbound_port, add.encryption_method);
        }
        System.out.println("enters details in add SMTP Configuration");
        Thread.sleep(3000);
    }

    public class addsmptconfig {
        public String select_company, smtp_host, smtp_username, password, outbound_port, encryption_method;

        public addsmptconfig(String Select_Company, String Smtp_Host, String Smtp_Username, String Password, String Outbound_Port, String Encryption_Method) {
            select_company = Select_Company;
            smtp_host = Smtp_Host;
            smtp_username = Smtp_Username;
            password = Password;
            outbound_port = Outbound_Port;
            encryption_method = Encryption_Method;
        }
    }

    @And("^I verify disable/enable SMTP Configuration$")
    public void iVerifyDisableEnableSMTPConfiguration() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        page.disablesmtpconfig();
        Thread.sleep(3000);
    }

    @And("^I click on save button in SMTP Configuration popup$")
    public void iClickOnSaveButtonInSMTPConfigurationPopup() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        System.out.println("clicks save btn in SMTP Configuration popup");
        page.addsc_savebtn();
        Thread.sleep(3000);
    }

    @And("^I click on edit SMTP Configuration$")
    public void iClickOnEditSMTPConfiguration() throws InterruptedException {
        Loginpage page = new Loginpage(base.driver);
        System.out.println("clicks on edit icon");
        Thread.sleep(3000);
        page.edit_sc();
    }

    @And("^I click on x icon for SMTP Configuration form$")
    public void iClickOnXIconForSMTPConfigurationForm() throws Exception {
        Loginpage page = new Loginpage(base.driver);
        System.out.println("Clicks X icon");
        page.xicon_sc();
    }
    @And("^I click on Agency API Configuration tab$")
    public void iClickOnAgencyAPIConfigurationTab() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        page.agencyapiconf();
    }

    @When("^I perform grid search for agency api$")
    public void iPerformGridSearchForAgencyApi(DataTable table) throws Exception {
        List<apisearch> api = new ArrayList<>();
        api = table.asList(apisearch.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        System.out.println("grid search for agency API config");
        page.APIgridsearch(api.get(0).compid, api.get(0).compname, api.get(0).enabledisable, api.get(0).apiname);
    }
    public class apisearch {
        public String compid, compname, enabledisable, apiname;
        public apisearch(String CompID, String CompName, String EnableDisable, String ApiName) {
            compid = CompID;
            compname = CompName;
            enabledisable = EnableDisable;
            apiname = ApiName;
        }
    }

    @When("^I click on Add API Configuration button$")
    public void iClickOnAddAPIConfigurationButton(DataTable table) throws Throwable {
        List<addapi> api = new ArrayList<>();
        api = table.asList(addapi.class);
        Loginpage page = new Loginpage(base.driver);
        Thread.sleep(3000);
        System.out.println("entering add API config");
        page.addAPIConfig(api.get(0).companyname, api.get(0).apiname, api.get(0).endpointurl, api.get(0).key, api.get(0).value);
    }
    public class addapi {
        public String companyname,apiname,endpointurl,key,value;
        public addapi(String CompanyName, String APIName, String EndPointurl, String Key, String Value){
            companyname = CompanyName;
            apiname = APIName;
            endpointurl = EndPointurl;
            key = Key;
            value = Value;
        }
    }

    @And("^I click on edit api icon under Action column$")
    public void iClickOnEditApiIconUnderActionColumn() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        System.out.println("Clicks edit icon");
        page.editapi();
    }

    @And("^I verify validation for add/edit api configuration$")
    public void iVerifyValidationForAddEditApiConfiguration() throws Throwable {
        Loginpage page = new Loginpage(base.driver);
        page.api_validation();
    }

}