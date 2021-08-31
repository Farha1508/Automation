package Steps;

import Base.BaseUtil;
import Base.TestRailAPI;
import com.gurock.testrail.APIException;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hooks extends BaseUtil {
    private final BaseUtil base;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    // TestRail variables
    ArrayList<String> idList;
    String currentId;
    boolean skipped = false;

    public Hooks(BaseUtil base) {

        this.base = base;
    }


    @Before
    public void InitializeTest(Scenario scenario) throws IOException, APIException {
        testRailStatus = "Test passed with no issues";

        scenarioName = scenario.getName();
        idList = (ArrayList<String>) scenario.getSourceTagNames();

        if (testRun.isTestRailRun()) {
            boolean caseFound = false;
            for(String testId : idList) {
                if(testRun.checkCaseId(testId)) {
                    currentId = testId;
                    caseFound = true;
                    break;
                }
            }
//
            if(!caseFound) {
                skipped = true;
                System.out.println("Case ID " + currentId + " was not in this run, skipping scenario");
                Assume.assumeTrue("Test ID " + currentId + " not in run, skipping", false);
            }
        }


        //System.setProperty("webdriver.chrome.driver", "D:\\Browsers\\chromedriver\\chromedriver_win32\\chromedriver.exe");
        System.out.println("Opening Chrome browser");
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @After
    public void TearDownTest(Scenario scenario) throws IOException, APIException {
        scenario.write("Scenario finished");
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        int statusCode = 1;
        if (scenario.isFailed()) {
            statusCode = 5;
            // TakesScreenshot
            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
            System.out.println(scenario.getName());

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File(filePath + "\\screenshots\\"+ scenarioName +"-"+ dateString + timeString + ".png"));
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
