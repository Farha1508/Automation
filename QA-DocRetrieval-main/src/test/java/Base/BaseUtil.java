package Base;

import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public static ArrayList<KpiClass> kpiCompare;
    public static int gridRecords;


    // filePath is currently set to C:\Users\<USER_NAME>. Feel free to change it
    // filePath is used to determine where screenshots and reports are saved on your system
    public static String filePath = System.getProperty("user.home") + "\\Desktop\\QA-Archetype-Files";
    // Location of attachment on your system
    public static String attachPath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    public static String attachLocation = attachPath("src/test/resources/attachments/sir_fluffington.jpg");
    // File name of the attachment with the type stripped out (i.e., If it's my-attachment.jpg, just use "my-attachment")
    public static String attachName = "sir_fluffington";
    // Spreadsheet for imports in AMA
    public static String importSheet = attachPath("src/test/resources/attachments/AMA_Import.xls");
    // Set this to the location of chromedriver.exe on your system
    public static String driverLocation = "C:\\chromedriver\\chromedriver.exe";

    // StepDef variables to allow transfer of information between StepDef files
    public static WebDriverWait wait;

    // Variables to track location and movement
    public static String currentLogin;
    public static String currentApp;
    public static String currentTab;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    // Variables for grid
    public static String workOrderNumber;
    public static int rowCount;

    public static Login login;
    public static NodeApp commonPage;
    public static GridFunctions gridFunctions;


    public void pageMap(String pageChoice) throws Exception {

        switch (pageChoice.toLowerCase()) {
            case "work order tracking":
                commonPage = new WOT(driver, js);
                break;

            default:
                commonPage = new WOT(driver, js);
//                throw new Exception("Could not find pageMap for " + pageChoice.toUpperCase() + " page. If one exists, please add it to StepDefs.");
        }

    }
    public static boolean pageLoaded() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return (Boolean) js.executeScript("return jQuery.active == 0");
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");
        try {
            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (TimeoutException e) {
            System.err.println("Timeout exception occurred for pageLoaded");
        }
        return false;

    }

}
