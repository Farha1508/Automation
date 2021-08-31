package Steps;

import Base.BaseUtil;
import Base.Emails;
import Pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.an.E;
import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Hooks extends BaseUtil {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    // TestRail variables
    ArrayList<String> idList;
    String currentId;
    boolean skipped = false;

    public Hooks() {
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        testRailStatus = "Test passed with no issues";
        scenarioName = scenario.getName();
        idList = (ArrayList<String>) scenario.getSourceTagNames();

        if (testRun.isTestRailRun()) {
            boolean caseFound = false;
            for (String testId : idList) {
                if (testRun.checkCaseId(testId)) {
                    currentId = testId;
                    caseFound = true;
                    break;
                }
            }

            if (!caseFound) {
                skipped = true;
                System.out.println("Case ID " + currentId + " was not in this run, skipping scenario");
                Assume.assumeTrue("Test ID " + currentId + " not in run, skipping", false);
            }
        }

        // Setting ChromeOptions to change the download folder to the one located in project directory
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", dLFolder);
        options.setExperimentalOption("prefs", prefs);
        System.out.println("Opening Chrome browser");
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 10);
        login = new Login(driver);
        emails = new Emails(login.getLogins().getProperty("gmail.email"), login.getLogins().getProperty("gmail.auth"));
        commonForm = new CommonForm(driver, js);
        commonGrid = new CommonGrid(driver, js);
        commonKPI = new CommonKPI(driver, js);
        purchaseOrder = new PurchaseOrder(driver, js);

    }

    @After
    public void TearDownTest(Scenario scenario) {
        scenario.log("Scenario finished");
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        int statusCode = 1;
        if (scenario.isFailed()) {
            statusCode = 5;
            // TakesScreenshot
            String sName;
            if (scenarioName.indexOf(',') > 0) {
                sName = scenarioName.substring(0, scenarioName.indexOf(','));
            } else {
                sName = scenarioName;
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("target/screenshots/" + sName + "-" + dateString + timeString + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // On test failure all items entered into valueStore are added to TestRail message and printed to console
            testRailStatus += "\nVALUES ENTERED DURING TEST:\n";
            valueStore.forEach((key, value) -> {
                testRailStatus += key + " - " + value + "\n";
                System.out.println(key + " - " + value);
            });
        }
        if (!skipped) {
            driver.quit();
            if (testRun.isTestRailRun()) {
                testRun.setStatus(currentId, statusCode, testRailStatus);
            }
        }

    }
}
