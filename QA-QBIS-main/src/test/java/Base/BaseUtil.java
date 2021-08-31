package Base;

import Pages.CommonPage;
import Pages.Login;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BaseUtil {
    public static WebDriver driver;
    public static JavascriptExecutor js;

    public static String scenarioName;

    // TestRail variables:
    public static TestRailAPI testRun = new TestRailAPI();
    public static String testRailStatus;

    // Values used by Step Definitions
    public static Map<String, String> valueStore = new HashMap<>();
    public static HashMap<String, String> editedValues = new HashMap<>();
    public static Map<String, Object> objectStore = new HashMap<>();
    public static int gridRecords;


    // filePath is currently set to C:\Users\<USER_NAME>. Feel free to change it
    // Location of attachment on your system
    public static String attachPath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    public static String attachLocation = attachPath("src/test/resources/Attachments/sir_fluffington.jpg");
    // File name of the attachment with the type stripped out (i.e., If it's my-attachment.jpg, just use "my-attachment")
    public static String attachName = "sir_fluffington";
    // Set this to the location of chromedriver.exe on your system
    public static String driverLocation = "C:\\chromedriver\\chromedriver.exe";

    // StepDef variables to allow transfer of information between StepDef files
    public static WebDriverWait wait;

    // Variables to track location and movement
    public static String currentLogin;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    public static Login login;
    public static CommonPage commonPage;
    public static Emails emails;

}
