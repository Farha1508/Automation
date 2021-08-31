package test;

import Base.BaseUtil;
import com.gurock.testrail.APIClient;
import org.json.simple.JSONArray;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class testgoogle<arrli> extends BaseUtil {

    public static boolean runStart = false;
    public static String TEST_Proj_ID = "4";
    public static String TEST_RUN_ID = "596";
    //   public static int TEST_RUN_ID;
    public static String TESTRAIL_USERNAME = "xxxxxx";
    public static String TESTRAIL_PASSWORD = "xxxx";
    public static String RAILS_ENGINE_URL = "https://patra.testrail.io";
    public static final int TEST_CASE_PASSED_STATUS = 1;
    public static final int TEST_CASE_FAILED_STATUS = 5;
    public static ArrayList<String> arrli = new ArrayList();

    @Test
    public static void API() throws Exception, com.gurock.testrail.APIException {
        String projectid = TEST_Proj_ID;
        APIClient client = new APIClient(RAILS_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        System.out.println(TEST_RUN_ID);

        //Adding a new run by fetching all testcases in a project
   /*     ArrayList<Integer> caseIds = new ArrayList<>();
        JSONArray cases = (JSONArray) client.sendGet("get_cases/" + projectid);
        System.out.println("No. of test cases in project: " + cases.size());
        for(Object item : cases) {

            JSONObject test = (JSONObject) item;
          //  System.out.println(test);
            // If you want custom case selection [ex:automation type]:
            if(((JSONObject) item).get("custom_automation_type").toString().equals("1")) {
                caseIds.add(Integer.parseInt(test.get("id").toString()));
            }
        }

        Map data = new HashMap();
        data.put("name", "generating run demo");
        data.put("include_all", false);
        data.put("case_ids", caseIds);
        JSONObject newrun = (JSONObject) client.sendPost("add_run/" + projectid, data);  //POST index.php?/api/v2/add_run/:project_id
        System.out.println("Generated run id is:" + newrun.get("id").toString());

        JSONArray tests = (JSONArray) client.sendGet("get_tests/" + newrun.get("id").toString()); //Returns a list of tests for a test run.GET index.php?/api/v2/get_tests/:run_id
        System.out.println("Existing test cases in Run Id:" + tests.get(Integer.parseInt("1")).toString());
    */
    }

    public static void addResultForTestCase(String testCaseId, int status, String error) throws Exception {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String BrowserName = cap.getBrowserName();
        if(!runStart) {
            API();
            runStart = true;
        }

        APIClient client = new APIClient(RAILS_ENGINE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);

        JSONArray arrVal = (JSONArray) client.sendGet("get_tests/" + TEST_RUN_ID);  //Returns a list of tests for a test run.GET index.php?/api/v2/get_tests/:run_id

        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "Test Executed - Status updated automatically from Selenium test automation on browser:"+ BrowserName);
        client.sendPost("add_result_for_case/" + TEST_RUN_ID + "/" + testCaseId + "", data); // POST index.php?/api/v2/add_results_for_cases/:run_id
        System.out.println("add_result_for_case/" + TEST_RUN_ID + "/" + testCaseId + "" + data);
    }
}

