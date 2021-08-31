package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class BaseUtil {
    public static WebDriver driver;

    public ExtentReports extent;

    public static ExtentTest scenarioDef;
    public static ExtentTest Feature;

    public static String reportLocation = "./test-output";

    public static String chromedriverlocation = "src/test/resources/drivers/Chromedriver 90/chromedriver.exe";
    public static String geckodriverlocation = "src/test/resources/drivers/Firefoxdriver/geckodriver.exe";
    public static String edgedriverlocation = "src/test/resources/drivers/Edgedriver 90/msedgedriver.exe";
    public static String filepath = System.getProperty("user.dir") + "\\target\\Screenshots\\CV screenshots";

}



