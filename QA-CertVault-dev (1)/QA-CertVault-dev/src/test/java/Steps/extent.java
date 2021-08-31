package Steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class extent {

    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void startReport()
    {
        htmlReporter = new ExtentHtmlReporter( System.getProperty("user.dir") +"/test-output/MyOwnReport.html"); //
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Test Pattern", "Automation Testing");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("User Name", "Anusha E");
        extent.setSystemInfo("Java Version", "1.8.0_151");
        extent.setSystemInfo("OS", "Windows 10 Pro" + "64 bit");
        extent.setSystemInfo("Maven", "3.5.2");

        htmlReporter.config().setDocumentTitle("Automation Testing");
        htmlReporter.config().setReportName("Cert Vault Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }
//Initializing part
    @Test(priority = 0)
    public void Method1passTest() throws IOException {
        logger = extent.createTest("Verify URL Navigation");
        Assert.assertTrue(true);
        logger.log(Status.INFO, "Method1passTest = Cert Vault DEV");
        logger.log(Status.PASS, MarkupHelper.createLabel("Navigates to CertVault Login page", ExtentColor.GREEN));
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));
        logger.pass("description", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\Anusha E\\Pictures\\Camera Roll\\Snapshot 1 (3-7-2019 12-33 PM).png").build());
      //  logger.addScreenCaptureFromPath("C:\\Users\\Anusha E\\Pictures\\Camera Roll\\Snapshot 1 (3-7-2019 12-33 PM).png");
    }

    @Test(priority = 1)
    public void Method2failTest() {
        logger.log(Status.INFO, "Method2failTest");
        logger = extent.createTest("Verify Page Title");
        Assert.assertTrue(true);
        logger.log(Status.PASS, "Test Case (failTest) Status is passed");
        logger.log(Status.PASS, MarkupHelper.createLabel("Test Case (failTest) Status is passed", ExtentColor.GREEN));
    }

    @Test
    public void skipTest() {
        logger = extent.createTest("skipTest");
        throw new SkipException("Skipping - This is not ready for testing ");
    }
//Registering part
    @BeforeMethod
    public void register(Method method){
        String testname = method.getName();
        logger = extent.createTest(testname);
    }
//checking status of every tests
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
           // logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Hooks.).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if(result.getStatus() ==ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel("Test Method name is:" + result.getName() + "is passed", ExtentColor.GREEN));
        }
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
