
package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ExtentReportUtil extends BaseUtil {

    String fileName = reportLocation + "extentreport.html";

    public void ExtentReport() {
        //Create Extent report object
        extent = new ExtentReports();

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Testing");
        htmlReporter.config().setReportName("Cert Vault Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        extent.setSystemInfo("Test Pattern", "Automation Testing");
        extent.setSystemInfo("Environment", "Dev");
        extent.setSystemInfo("User Name", "Anusha E");
        extent.setSystemInfo("Java Version", "1.8.0_151");
        extent.setSystemInfo("OS", "Windows 10 Pro" + "64 bit");
        extent.setSystemInfo("Maven", "3.5.2");

        extent.attachReporter(htmlReporter);
        return;
    }

    public void ExtentReportScreenshot() throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(srcFile.toPath(), new File(reportLocation + "screenshot.png"+ System.currentTimeMillis()).toPath());
        scenarioDef.fail("error snapshot details").addScreenCaptureFromPath(reportLocation + "screenshot.png");
    }

    public void FlushReport() {
        extent.flush();
    }
}

