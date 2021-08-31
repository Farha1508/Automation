import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarrierCheck {
    private static HashMap<String, String> items = new HashMap<>();
    String workOrder = "https://okati.patracorp.net/woDetailPC/1905047?companyData=eyJjb21wYW55SUQiOiI3NDgiLCJ0cmFuc2FjdGlvblR5cGVJZCI6IjIifQ==";
    private static String baseUrl;
    private static String company;
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static WebDriverWait wait;
    private static CommonActions commonActions;
    boolean success;
    static FileWriter docLog;
    String result;
    private static String woNum;
    private static String woID;
    String docNum;

    @ParameterizedTest
    @ValueSource(strings = {"Chubb Custom Insurance Company", "Chubb Indemnity Insurance Company", "Chubb Insurance Company Of New Jersey",
            "Chubb National Insurance Company", "Church Mutual Insurance Company", "Federal Insurance Company", "The Chubb Corporation",
            "Travelers Casualty And Surety Company", "Travelers Casualty and Surety Co America", "Travelers Casualty Company of Connecticut",
            "Travelers Casualty Insurance Company of America", "Travelers Commercial Casualty Insurance Company",
            "Travelers Excess and Surplus Lines Company", "Travelers Home & Marine Insurance Company", "Travelers Indemnity Company of CT",
            "Travelers Indemnity Company of America", "Travelers Insurance Company Limited", "Travelers Lloyds Insurance Company", "Travelers Personal Insurance Company",
            "Travelers Personal Security Insurance Company", "Travelers Property Casualty Company of America", "Travelers Property Casualty Insurance Company"})
    public void carrierTest(String carrier) {
        success = false;
        result = "";
        commonActions.commonDropDownSelect("Carrier Name", carrier);
        commonActions.commonButton("Submit");
        commonActions.pageLoaded();
        Assertions.assertTrue(driver.findElement(By.id("upload_doc")).isDisplayed(),
                "Upload Document button didn't display with '" + carrier + "' carrier");

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
        String env = config.getProperty("test.environment");
        baseUrl = config.getProperty(env + ".url");
        company = config.getProperty(env + ".company");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor) driver;
        commonActions = new CommonActions(driver, js);
        driver.manage().window().maximize();

        // Creation of work order
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
        commonActions.pageLoaded();
        commonActions.commonButton("Add Policy");
        commonActions.pageLoaded();
        commonActions.commonButton("Submit and Open");
        commonActions.pageLoaded();
        woNum = driver.findElement(By.id("pc_WoNumber1")).getText().trim();
        woID = driver.findElement(By.id("pc_WoId")).getAttribute("value");
        System.out.println("Created Work Order #" + woNum + " with ID: " + woID);
        commonActions.commonDropDownSelect("Process", "Policy Checking");
        commonActions.commonDropDownSelect("Policy Type", "CL - Umbrella");
        commonActions.commonDropDownSelect("Status", "Available");
        commonActions.commonDropDownSelect("Assign To", "expert.ai");


    }

    @BeforeEach
    public void setup() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        js = (JavascriptExecutor) driver;
//        wait = new WebDriverWait(driver, 5);
    }

    @AfterEach
    public void teardown() {
        driver.findElement(By.linkText("Detail")).click();
        commonActions.pageLoaded();
        commonActions.commonDropDownSelect("Carrier Name", "AARIS");
        commonActions.commonButton("Submit");
        commonActions.pageLoaded();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("upload_doc")));
        driver.findElement(By.linkText("Detail")).click();
        commonActions.pageLoaded();
    }

    @AfterAll
    public static void end() {
        driver.quit();
    }


}
