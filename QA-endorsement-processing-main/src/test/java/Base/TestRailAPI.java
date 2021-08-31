package Base;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class TestRailAPI {

    private boolean testRailRun;
    private HashMap<String, String> runIds;
    private int testRun;
    private final APIClient client;
    Properties trail = new Properties();

    public TestRailAPI() {
        this.runIds = new HashMap<>();
        this.client = new APIClient("https://patra.testrail.io/");
        try {
            this.trail.load(getClass().getClassLoader().getResourceAsStream("testrail.properties"));
        } catch (IOException e) {
            System.err.println(e);
        }

        this.testRailRun = trail.getProperty("runtestrail").equals("true");
        if (testRailRun) {
            client.setUser(trail.getProperty("email"));
            client.setPassword(trail.getProperty("password"));

            if (trail.getProperty("createrun").equals("true")) {
                int projId = Integer.parseInt(trail.getProperty("projectId"));
                createRun(projId, trail.getProperty("runTitle"));
            } else {
                int runId = Integer.parseInt(trail.getProperty("runId"));
                getRun(runId);
            }
        }


    }

    public boolean isTestRailRun() {
        return testRailRun;
    }

    public boolean checkCaseId(String testId) {
        return runIds.containsKey(testId);

    }

    /**
     * The getRun function is used to retrieve test runs that have already been created in TestRail. Either manually
     * or through the createRun method.
     *
     * @param run Accepts an int containing the ID of the run to be retrieved for testing
     */
    public void getRun(int run) {
        this.testRun = run;
        ArrayList<String> testIds = new ArrayList<>();
        try {
            JSONArray rt = (JSONArray) client.sendGet("get_tests/" + testRun);

            for (Object tests : rt) {
                HashMap data = (HashMap) tests;
                runIds.put("@" + data.get("case_id").toString(), data.get("id").toString());
                testIds.add("@" + data.get("case_id").toString());
            }
        } catch (APIException | IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("Initializing test run: \n" + testRun + "\n" +
                "With the following testIds: \n" + testIds.toString());
    }

    /**
     * The createRun function will create a new run in TestRail at the start of the test. It is currently set to add
     * all of the test cases in a project have Automation Type set to Selenium.
     *
     * @param projId   Accepts an int. This is the ID of the project where you wish to create the run (e.g., the ID of CertVault is 4)
     * @param runTitle Accepts a String. This is the name you want the run to have.
     */
    public void createRun(int projId, String runTitle) {
        ArrayList<Integer> caseIds = new ArrayList<>();
        try {
            JSONArray cases = (JSONArray) client.sendGet("get_cases/" + projId);

            System.out.println("Size of array is: " + cases.size());
            for (Object item : cases) {
                JSONObject test = (JSONObject) item;
                // If you want automation type:
                if (test.get("custom_automation_type").toString().equals("1")) {
                    caseIds.add(Integer.parseInt(test.get("id").toString()));

                }

//            if(test.get("section_id").toString().equals("633") || test.get("section_id").toString().equals("634")) {
//                caseIds.add(Integer.parseInt(test.get("id").toString()));
//            }
            }


            HashMap data = new HashMap();
            data.put("name", runTitle);
            data.put("include_all", false);
            data.put("case_ids", caseIds);

            JSONObject newRun = (JSONObject) client.sendPost("add_run/" + projId, data);
            System.out.println("New Run ID: " + newRun.get("id").toString());


            getRun(Integer.parseInt(newRun.get("id").toString()));
        } catch (APIException | IOException e) {
            System.out.println(e);
            System.exit(1);
        }


    }

    /**
     * Sets the status and adds a comment to the test case after that test case has been run
     *
     * @param currentTest The ID of the current case being run
     * @param status      The status ID to be passed as an int (e.g., 1 is Pass, 5 is Fail)
     * @param comment     The comment to be added to the case
     */
    public void setStatus(String currentTest, int status, String comment) {
        HashMap data = new HashMap();
        data.put("status_id", status);
        data.put("comment", comment);
        try {
            client.sendPost("add_result/" + runIds.get(currentTest), data);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
