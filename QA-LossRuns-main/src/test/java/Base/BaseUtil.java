package Base;


import Pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseUtil {
    public static WebDriver driver;

    public static String scenarioName;


    // TestRail variables:
    public static TestRailAPI testRun = new TestRailAPI();
    public static String testRailStatus;
    public static String currentLogin;
    public static String currentApp;


    //Initilize class
    public static Login login;

    // Date time variables
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");




    public static String filePath = System.getProperty("user.home")+ "/git/QA-loss-runs";

    public static String attachPath(String relativePath) {
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    public static String attachLocation = attachPath("src/test/resources/Attachments/sample.pdf");
    public static String attachName = "sample";


    // Variables for grid
    public static Map<String, String> valueStore = new HashMap<>();
    public static HashMap<String, String> editedValues = new HashMap<>();
    public static ArrayList<KpiClass> kpiCompare;
    public static String currentTab;
    public static String currentPage;
    public static int rowCount;
    public static int record_count;
    public static String newHeaderInfo;

    public static NodeApp commonPage;
    public static CommonForm commonForm;
    public static LossRunsHappyPathPage lossrunspage;
    public static int gridRecords;
    public static  int LRR_Count;
    public static int TR_count;
    public static  int PDR_count;
    public static  int RR_count;
    public static  int PCR_count;
    public static  int PR_count;
    public static  int CRN_count;
    public static  int OHR_count;
    public static String WO_number;

    public void pageMap(String pageChoice) throws Exception {
        commonPage = new CommonPage(driver,js);
        commonForm = new CommonForm(driver, js);

    }



    //Keeping the page loader
    public static boolean pageLoaded() {
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> js.executeScript("return document.readyState").toString().equals("complete");
        try {
            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (Exception e) {
            return true;
        }
    }

    public static JavascriptExecutor js;

    // StepDef variables to allow transfer of information between StepDef files
    public static WebDriverWait wait;




}