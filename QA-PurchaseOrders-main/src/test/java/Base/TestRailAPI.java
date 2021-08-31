package Base;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class TestRailAPI {

    private boolean testRailRun;
    private HashMap<String, String> runIds;
    private int testRun;
    private final APIClient client;
    private final Properties tRailProp = new Properties();
    private final Properties config = new Properties();

    public TestRailAPI() {
        this.runIds = new HashMap<>();
        this.client = new APIClient("https://patra.testrail.io/");
        try {
            this.tRailProp.load(getClass().getClassLoader().getResourceAsStream("testrail.properties"));
            this.config.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println(e);
        }

        this.testRailRun = tRailProp.getProperty("runtestrail").equals("true");
        if (testRailRun) {
            client.setUser(tRailProp.getProperty("email"));
            client.setPassword(tRailProp.getProperty("password"));

            int projId = Integer.parseInt(tRailProp.getProperty("projectId"));
            int runId = Integer.parseInt(tRailProp.getProperty("runId"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(new Date());
            String runTitle = config.getProperty("environment").toUpperCase() +" "+ tRailProp.getProperty("runTitle") + " - " +dateString;
            boolean runExists = false;
            JSONArray runs = new JSONArray();
            try {
                runs = (JSONArray) client.sendGet("get_runs/"+projId+"/&is_completed=0");
            } catch (APIException | IOException e) {
                System.out.println(e);
            }

            for(Object run : runs) {
                JSONObject test = (JSONObject) run;
                if(test.get("name").toString().equals(runTitle)) {
                    runExists = true;
                    runId = Integer.parseInt(test.get("id").toString());
                }
            }


            if (tRailProp.getProperty("createrun").equals("true") && !runExists) {
                createRun(projId, runTitle);
            } else {
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
        String[] sections = tRailProp.getProperty("sectionIds").split(",");
        try {
            JSONArray cases = (JSONArray) client.sendGet("get_cases/" + projId);

            for (Object item : cases) {
                JSONObject test = (JSONObject) item;
                // Add test cases by section ID
                if (!sections[0].equals("")) {
                    for (String section : sections) {
                        if (test.get("section_id").toString().equals(section.trim())) {
                            caseIds.add(Integer.parseInt(test.get("id").toString()));
                        }
                    }
                }
                // If you want automation type:
//                if (test.get("custom_automation_type").toString().equals("1")) {
//                    caseIds.add(Integer.parseInt(test.get("id").toString()));
//
//                }

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

    private boolean runExists(String runTitle, int projId) {
        boolean runExists = false;
        JSONArray runs = new JSONArray();
        try {
            runs = (JSONArray) client.sendGet("get_runs/"+projId+"/&is_completed=0");
        } catch (APIException | IOException e) {
            System.out.println(e);
        }

        System.out.println("Size of array is: " + runs.size());
        for(Object item : runs) {
            JSONObject test = (JSONObject) item;
            if(test.get("name").toString().equals(runTitle)) {
                runExists = true;
            }
        }
        return runExists;
    }

}
