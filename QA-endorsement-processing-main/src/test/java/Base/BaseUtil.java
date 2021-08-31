package Base;

import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
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
    // Driver variables
    public static WebDriver driver;
    public static JavascriptExecutor js;
    public static WebDriverWait wait;

    public static String scenarioName;

    // TestRail variables:
    public static TestRailAPI testRun = new TestRailAPI();
    public static String testRailStatus;

    // StepDef variables to allow transfer of information between StepDef functions
    // Variables to track location and movement
    public static String currentLogin;
    public static String currentApp;
    public static String currentTab;
    // Values used by Step Definitions
    public static Map<String, String> valueStore = new HashMap<>();
    public static HashMap<String, String> editedValues = new HashMap<>();
    public static ArrayList<KpiClass> kpiCompare;
    // Variables for grid
    public static String workOrderNumber;
    public static int rowCount;
    public static int gridRecords;
    public static String headerInfo;
    public static String headerChoice;
    public static String newHeaderInfo;
    // Date time variables
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    // Class initializers
    public static Login login;
    public static NodeApp commonPage;
    public static WOT woT;
    public static CommonForm commonForm;
    public static CommonGrid commonGrid;
    public static boolean paramTestNext;


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


    public void pageMap(String pageChoice) throws Exception {

        switch (pageChoice.toLowerCase()) {
            case "work order tracking":
                commonPage = new WOT(driver, js);
                woT = new WOT(driver, js);
                commonForm = new CommonForm(driver, js);
                commonGrid = new CommonGrid(driver, js);
                break;

            default:
                commonPage = new CommonPage(driver, js);
//                throw new Exception("Could not find pageMap for " + pageChoice.toUpperCase() + " page. If one exists, please add it to StepDefs.");
        }

    }

    public static boolean pageLoaded() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> (Boolean) js.executeScript("return jQuery.active == 0");

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);

    }
}
