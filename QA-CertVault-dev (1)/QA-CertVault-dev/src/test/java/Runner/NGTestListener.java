package Runner;

import Base.ExtentReportUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import static Base.BaseUtil.Feature;

public class NGTestListener implements ITestListener {
    ExtentReportUtil extentReportUtil = new ExtentReportUtil();
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("On test Start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On test success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("On test Failure");
        try{
            extentReportUtil.ExtentReportScreenshot();
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("On test percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("On Start");
        extentReportUtil.ExtentReport();

        // ToDo: calling Feature - Hardcoding
        Feature = extentReportUtil.extent.createTest(com.aventstack.extentreports.gherkin.model.Feature.class, "Patradocumentsrole");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("On Finish");
        extentReportUtil.FlushReport();
    }
}
