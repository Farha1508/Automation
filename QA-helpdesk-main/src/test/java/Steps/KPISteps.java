package Steps;

import Base.BaseUtil;
import Base.KpiClass;
import Pages.CommonKPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.ArrayList;

public class KPISteps extends BaseUtil {

    @And("I get the count for {string} KPI")
    public void getTheCountForKPI(String kpiName) {
        boolean kpiFound = false;
        kpiCompare = new ArrayList<>();
        kpiCompare = commonKPI.getKpi();

        // If "All" was used as the KPI name then it will print out a list of all KPIs visible with their values
        if (kpiName.equalsIgnoreCase("all")) {
            kpiFound = true;
            for (KpiClass kpiClass : kpiCompare) {
                // Checking if the KpiClass object is using a float or int value
                // If the getKpiValueInt is less than 0, then the KPIs value is being stored as a float
                if (kpiClass.getKpiValueInt() < 0) {
                    System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueFloat());
                    valueStore.put(kpiClass.getKpiTitle(), Float.toString(kpiClass.getKpiValueFloat()));
                } else {
                    System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueInt());
                    valueStore.put(kpiClass.getKpiTitle(), Integer.toString(kpiClass.getKpiValueInt()));
                }
            }
        } else {
            // Otherwise it will only print out the value of the KPI specified, but all will be stored
            for (KpiClass kpiClass : kpiCompare) {
                if (kpiClass.getKpiTitle().equalsIgnoreCase(kpiName)) {
                    kpiFound = true;
                    if (kpiClass.getKpiValueInt() < 0) {
                        System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueFloat());
                        valueStore.put(kpiClass.getKpiTitle(), Float.toString(kpiClass.getKpiValueFloat()));
                    } else {
                        System.out.println(kpiClass.getKpiTitle() + " current value is " + kpiClass.getKpiValueInt());
                        valueStore.put(kpiClass.getKpiTitle(), Integer.toString(kpiClass.getKpiValueInt()));
                    }

                }
            }
        }

        Assert.assertTrue(kpiFound, kpiName + " was not found, please ensure you are on the correct page");


    }

    @Then("Verify that {string} KPI has {string}")
    public void verifyThatKPIHas(String kpiName, String kpiStatus) throws Exception {

        int comparison = Integer.MAX_VALUE;
        int status;
        String originalValue = "No value found";
        String updatedValue = "No value found";
        boolean kpiFound = false;
        ArrayList<KpiClass> kpiUpdate;
        kpiUpdate = commonKPI.getKpi();


        switch (kpiStatus.toLowerCase()) {
            case "increased":
                status = 1;
                break;
            case "decreased":
                status = -1;
                break;
            case "not changed":
                status = 0;
                break;
            default:
                throw new Exception(kpiStatus + " status not recognized, please use 'Increased', 'Decreased', or 'Unchanged'.");
        }

        // Checking if requested KPI is in the kpiUpdate array
        for (KpiClass updatedKpi : kpiUpdate) {
            if (updatedKpi.getKpiTitle().equalsIgnoreCase(kpiName)) {
                // Requested KPI found in kpiUpdate array, now finding a match in kpiCompare array
                for (KpiClass originalKpi : kpiCompare) {
                    if (originalKpi.getKpiTitle().equalsIgnoreCase(kpiName)) {
                        kpiFound = true;
                        if (originalKpi.getKpiValueInt() < 0) {
                            comparison = Float.compare(updatedKpi.getKpiValueFloat(), originalKpi.getKpiValueFloat());
                            originalValue = Float.toString(originalKpi.getKpiValueFloat());
                            updatedValue = Float.toString(updatedKpi.getKpiValueFloat());
                            // Setting the value in the kpiCompare array to be equal to the value in kpiUpdate
                            originalKpi.setKpiValueFloat(updatedKpi.getKpiValueFloat());
                            valueStore.put(originalKpi.getKpiTitle(), Float.toString(originalKpi.getKpiValueFloat()));

                        } else {
                            comparison = Integer.compare(updatedKpi.getKpiValueInt(), originalKpi.getKpiValueInt());
                            originalValue = Integer.toString(originalKpi.getKpiValueInt());
                            updatedValue = Integer.toString(updatedKpi.getKpiValueInt());
                            // Setting the value in the kpiCompare array to be equal to the value in kpiUpdate
                            originalKpi.setKpiValueInt(updatedKpi.getKpiValueInt());
                            valueStore.put(originalKpi.getKpiTitle(), Integer.toString(originalKpi.getKpiValueInt()));
                        }
                        break;
                    }
                }
            }

        }
        if (!kpiFound) Assert.fail("Could not find " + kpiName + " please ensure you are on the right page");

        Assert.assertEquals(comparison, status, "Value for " + kpiName + "  has not been " + kpiStatus + ". Original value: " + originalValue + " updated value " + updatedValue);

        System.out.println("Verified that value for " + kpiName + " has been " + kpiStatus + ". Original value: " + originalValue + " updated value " + updatedValue);


    }

    @And("I click on the {string} KPI")
    public void iClickOnTheKPI(String kpi) {
        commonKPI.clickKpi(kpi);
    }
}
