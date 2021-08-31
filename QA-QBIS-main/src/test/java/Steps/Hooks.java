package Steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks extends BaseUtil {
    private BaseUtil base;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    private final Properties config = new Properties();

    // TestRail variables
    ArrayList<String> idList;
    String currentId;
    boolean skipped = false;

    public Hooks(BaseUtil base) {

        this.base = base;
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

        System.out.println("Opening Chrome browser");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        // Setting implicit wait for all the scenarios to be 10 seconds.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        }
        if (!skipped) {
            driver.quit();
            if (testRun.isTestRailRun()) {
                testRun.setStatus(currentId, statusCode, testRailStatus);
            }
        }

    }
}
