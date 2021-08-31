import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocUpload {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    CommonActions commonActions;
    boolean success;
    BufferedWriter docLog;
    String result;
    String[] results;
    String woNum;
    String woID;
    String docNum;
    private static String baseUrl;
    private static String company;

    @ParameterizedTest
    @CsvFileSource(resources = "/expertai.csv")
    public void fileTest(String expired, String renewal) {
        commonActions = new CommonActions(driver, js);
        String expLoc = new File(expired).getAbsolutePath();
        String renLoc = new File(renewal).getAbsolutePath();
        success = false;
        result = "";
        results = new String[6];
        String woExp = null;
        String woRen = null;
        Pattern woExtract = Pattern.compile("(WO\\s\\d*)(?=\\s)");
        Matcher expWo = woExtract.matcher(expired);
        Matcher renWo = woExtract.matcher(renewal);
        while (expWo.find()) {
            woExp = expWo.group();
        }
        while (renWo.find()) {
            woRen = renWo.group();
        }
        assert woRen != null;
        Assertions.assertEquals(woRen, woExp, "Expired and Renewal work orders did not match");
        docNum = woRen;
        results[2] = "Using " + docNum + " Expiring and Renewal PDFs";
        driver.get(baseUrl);
        commonActions.userLogin("super user");

        commonActions.hold(500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".wot"))).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.close();
                driver.switchTo().window(windowHandle);
            }
        }
        commonActions.openCompany("Policy Checking", company);
        commonActions.commonButton("Add Policy");
        commonActions.pageLoaded();
        commonActions.commonButton("Submit and Open");
        commonActions.pageLoaded();
        woNum = driver.findElement(By.id("pc_WoNumber1")).getText().trim();
        woID = driver.findElement(By.id("pc_WoId")).getAttribute("value");
        commonActions.commonDropDownSelect("Process", "Policy Checking");
        commonActions.commonDropDownSelect("Policy Type", "CL - Umbrella");
        commonActions.commonDropDownSelect("Status", "Available");
        commonActions.commonDropDownSelect("Assign To", "expert.ai");
        commonActions.commonDropDownSelect("Carrier Name", "The Chubb Corporation");
        driver.findElement(By.id("pc_Summary")).sendKeys("Using " + woExp + " Expiring and Renewal PDFs");
        commonActions.commonButton("Submit");
        commonActions.pageLoaded();
        results[0]= woID;
        results[1]= woNum;
        commonActions.commonButton("Upload Documents");
        commonActions.pageLoaded();
        driver.findElement(By.id("expired_policy_file")).sendKeys(expLoc);
        driver.findElement(By.id("renewal_policy_file")).sendKeys(renLoc);
        commonActions.commonButton("Upload");
        long uplStart = System.currentTimeMillis();
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.id("upload_succ_msg")),
                    ExpectedConditions.visibilityOfElementLocated(By.id("upload_fail_msg"))
            ));
        } catch (TimeoutException e) {
            results[5] = "Upload wait > 60 sec";
            Assertions.fail();
        }

        long uplFinish = System.currentTimeMillis() - uplStart;
        results[3] = Long.toString(uplFinish);
        success = driver.findElement(By.id("upload_succ_msg")).isDisplayed();
        result += "|" + uplFinish;
        if (!success) {
            results[5] = "Upload Failed Message Displayed";
            Assertions.fail();
        }
    }

    @BeforeAll
    public static void createWo() {
        Properties config = new Properties();
        String confProp = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "config.properties";
        try {
            config.load(new FileInputStream(confProp));
        } catch (IOException e) {
            System.err.println("Could not load config.properties, please ensure file is available in src/test/resources folder");
            System.exit(1);
        }
        if (config.getProperty("test.environment").equals("dev")) {
            baseUrl = config.getProperty("dev.url");
            company = config.getProperty("dev.company");
        } else {
            baseUrl = config.getProperty("prod.url");
            company = config.getProperty("prod.company");
        }


    }


    @BeforeEach
    public void setup() {
        try {
            docLog = new BufferedWriter(new FileWriter("src/test/resources/uplStatus.log", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 60);
        driver.manage().window().maximize();

    }

    @AfterEach
    public void teardown() {
        if (success) {
            results[4] = "SUCCESS";
        } else {
            results[4] = "FAIL";
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("target/screenshots/" + woID + "-" + woNum + "-" + docNum + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = results[0]+"|"+results[1]+"|"+results[2]+"|"+results[3]+"|"+results[4]+"|"+results[5]+"\n";
        System.out.println("Result: " + result);
        try {
            docLog.write(result);
            docLog.close();

        } catch (IOException e) {
            System.out.println("File writing failed");
            e.printStackTrace();
        }
        driver.quit();
    }


}
