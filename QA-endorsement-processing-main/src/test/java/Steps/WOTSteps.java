package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import Pages.WOT;


public class WOTSteps extends BaseUtil {
    private String currentCompany;
    private String currentService;
    private HashMap<String, String> exceptionMap = new HashMap<>();
    private WOT WOT;

    public WOTSteps() {
        this.WOT = new WOT(driver, js);
    }

    @When("I open {string} for company {string}")
    public void iOpenForCompany(String service, String company) {
        WebElement selectCompany = driver.findElement(By.id("companyStartId"));
        selectCompany.click();
        Select list = new Select(selectCompany);
        list.selectByVisibleText(company);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(company + " - " + service))).click();
        } catch (ElementClickInterceptedException e) {
            commonPage.clickErrorHandle(e.toString(), driver.findElement(By.linkText(company + " - " + service)));
        }
        commonPage.pageLoaded();

        currentCompany = company;
        currentService = service;

    }

    @Then("The page for the selected company and service will be displayed")
    public void thePageForTheSelectedCompanyAndServiceWillBeDisplayed() {
        commonPage.pageLoaded();
        WebElement companySelector = driver.findElement(By.id("changecompany"));
        Select company = new Select(companySelector);
        String c = company.getFirstSelectedOption().getText();
        System.out.println("Selected company is: " + company.getFirstSelectedOption().getText());
        WebElement serviceSelector = driver.findElement(By.id("changeservice"));
        Select service = new Select(serviceSelector);
        String s = service.getFirstSelectedOption().getText();
        System.out.println("Selected service is: " + service.getFirstSelectedOption().getText());
        Assert.assertEquals(c, currentCompany, "Company on page (" + c + ") does not match previously selected company(" + currentCompany + ")");
        Assert.assertEquals(s, currentService, "Service on page (" + s + ") does not match previously selected service (" + currentService + ")");
        System.out.println("Verified current page is the " + s + " page for " + c);
    }

    @Then("The work order form will not be submitted")
    public void theWorkOrderFormWillNotBeSubmitted() {
        Assert.assertTrue(driver.findElement(By.id("folder_error")).isDisplayed(),
                "'Select Folder' error message is not displayed.");
        System.out.println("Verified work order with missing mandatory fields was not submitted");
    }

    @Then("The detail page for the new work order will be displayed")
    public void aTheDetailPageForTheNewWorkOrderWillBeDisplayed() {
        commonPage.pageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("liststyle")));
        String folder = commonPage.commonDropDownRead("Folder");
        Assert.assertEquals(folder, "Quality Assurance",
                "Current work order does not match created work order");
        String woNum = driver.findElement(By.xpath("//*[@id=\"liststyle\"]/div[1]/div[1]/label")).getText().replaceAll("\\D", "").trim();
        valueStore.put("WO #", woNum);
        System.out.println("Verified detail page opened for work order number " + woNum);
    }

    @And("I get the work order number from the confirmation pop-up")
    public void iGetTheWorkOrderNumberFromTheConfirmationPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"jconfirm\")]")));
        String woNum = driver.findElement(By.xpath("//div[@class=\"jconfirm-content-pane no-scroll\"]/descendant::font")).getText().replaceAll("\\D", "");
        valueStore.put("WO #", woNum);
        exceptionMap.put("Related Work Order #", woNum);
        System.out.println("created work order number is: " + woNum);
        commonPage.commonButton("OK");

    }

    @And("I find the work order in the grid")
    public void iFindTheWorkOrderInTheGrid() {
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("WO #", valueStore.get("WO #"));
        commonPage.pageLoaded();
        driver.findElement(By.linkText(valueStore.get("WO #"))).click();
        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();

        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.close();
                driver.switchTo().window(windowHandle);
            }
        }
        commonPage.pageLoaded();
    }

    @And("I get the exception number from the confirmation pop-up")
    public void iGetTheExceptionNumberFromTheConfirmationPopUp() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"jconfirm\")]")));
        String exNum = driver.findElement(By.xpath("//div[@class=\"jconfirm-content-pane no-scroll\"]/descendant::font")).getText().replaceAll("\\D", "");
        valueStore.put("Exception #", exNum);
        System.out.println("created exception number is: " + exNum);
        commonPage.commonButton("OK");
    }

    @And("I find the created exception in the grid")
    public void iFindTheCreatedExceptionInTheGrid() {
        commonPage.gridHeaderEnter("Exception #", valueStore.get("Exception #"));
        commonPage.pageLoaded();
        driver.findElement(By.linkText(valueStore.get("Exception #"))).click();
        Set<String> handles = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.close();
                driver.switchTo().window(windowHandle);
            }
        }
        commonPage.pageLoaded();
        commonPage.pageLoaded();
    }

    @Then("Verify the exception details match what was created")
    public void verifyTheExceptionDetailsMatchWhatWasCreated() {
        exceptionMap.forEach((key, value) -> {
            String result = "";
            System.out.println("Checking value for " + key);
            try {
                if (commonPage.commonFieldRead(key).equals(value)) {
                    result = key + " field edit verified as " + value;
                }
            } catch (NullPointerException ignored) {
            }
            try {
                if (commonPage.commonDropDownRead(key).equals(value)) {
                    result = key + " Drop down edit verified as " + value;
                }
            } catch (NullPointerException ignored) {
            }
            try {
                if (commonPage.commonTextAreaRead(key).equals(value)) {
                    result = key + " text area edit verified as " + value;
                }
            } catch (NullPointerException ignored) {
            }
            Assert.assertNotEquals(result, "", "Could not find entry for " + key);
            System.out.println(result);

        });

    }

    @And("I create an exception with the following details")
    public void iCreateAnExceptionWithTheFollowingDetails(Map<String, String> table) {
        String dateString = dateFormat.format(new Date());
        String timeString = timeFormat.format(new Date());
        table.forEach((key, value) -> {
            boolean foundItem = false;
            if (value.contains("<current date>")) {
                foundItem = true;
                value = value.replaceAll("<current date>", dateString + " " + timeString);
            }
            if (commonPage.commonFieldEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" field");
            } else if (commonPage.commonDropDownSelect(key, value)) {
                foundItem = true;
                System.out.println("Selecting \"" + value + "\" from \"" + key + "\" drop down");
            } else if (commonPage.commonTextAreaEnter(key, value)) {
                foundItem = true;
                System.out.println("Entering \"" + value + "\" into \"" + key + "\" text area");
            }
            Assert.assertTrue(foundItem, "Could not find " + key + " field, drop down, or text area!");
            exceptionMap.put(key, value);
        });

        driver.findElement(By.id("workOrder_submit")).click();
    }

    @And("I add {string} key and {string} value to exceptionMap")
    public void iAddKeyAndValueToExceptionMap(String key, String value) {
        exceptionMap.put(key, value);
    }

    @And("I add the following items to the exceptionMap")
    public void iAddTheFollowingItemsToTheExceptionMap(Map<String, String> table) {
        table.forEach((key, value) -> exceptionMap.put(key, value));
    }

    @And("I click on the Patra Logo")
    public void iClickOnPatraLogo() {
        commonPage.pageLoaded();
        driver.findElement(By.id("index")).click();
        commonPage.pageLoaded();
    }

    @When("I switch to the {string} company in App Header which is {string}")
    public void iOpenForCompanyHeader(String company, String validity) {
        WebElement selectCompany = driver.findElement(By.id("changecompany"));
        selectCompany.click();
        Select companyList = new Select(selectCompany);
        companyList.selectByVisibleText(company);
        commonPage.pageLoaded();

        switch (validity) {
            case "valid":
                currentCompany = company;
                break;
            case "invalid":
                System.out.println("Company is not changing");
                break;
            default:
                System.out.println("must be either 'valid' or 'invalid'");
        }


    }

    @When("I switch to the {string} service in App Header which is {string}")
    public void iOpenForServiceHeader(String service, String validity) {
        WebElement selectCompany = driver.findElement(By.id("changeservice"));
        selectCompany.click();
        Select companyList = new Select(selectCompany);
        companyList.selectByVisibleText(service);
        commonPage.pageLoaded();

        switch (validity) {
            case "valid":
                currentService = service;
                break;
            case "invalid":
                System.out.println("Service is not changing");
                break;
            default:
                System.out.println("must be either 'valid' or 'invalid'");
        }


    }

    @And("I enter the Work Order Number in the Global Search")
    public void enterWOInGlobalSearch() {
        commonPage.pageLoaded();
        WOT.doGlobalSearch(valueStore.get("WO #"));
        commonPage.pageLoaded();
        WOT.selectFirstGlobalSearchResult();
    }

    @Then("I verify I have opened the expected Work Order")
    public void iVerifyOpenedExpectedWO() {
        commonPage.pageLoaded();
        Assert.assertTrue(WOT.confirmExpectedWOOpen(valueStore.get("WO #")), "Opened wrong Work Order");

    }

    @And("I enter an invalid Work Order Number in the Global Search")
    public void enterInvalidWOInGlobalSearch() {
        commonPage.pageLoaded();
        WOT.doGlobalSearch("xxxx");
        commonPage.pageLoaded();
        Assert.assertTrue(WOT.confirmGlobalSearchError(), "Expected error is not present");
    }
}
