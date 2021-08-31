package Steps;

import Base.BaseUtil;
import Pages.CommonPage;
import Pages.OrgManagement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OrgManagementStepDefs extends BaseUtil {

    private OrgManagement orgManagement;

    public OrgManagementStepDefs() {
        orgManagement = new OrgManagement(driver);
        commonPage = new CommonPage(driver, js);
        wait = new WebDriverWait(driver, 10);
    }

    @And("I click the hamburger menu")
    public void i_click_the_hamburger_menu() throws Exception {
        System.out.println("Clicking the Hamburger menu...");
        orgManagement.clickHamburgerMenu();
    }

    @And("I scroll down the page")
    public void i_scroll_down_the_page() {
        System.out.println("Scrolling down the page...");
        orgManagement.scroll(1000);
    }


    @And("I set password of the new user")
    public void i_set_password_of_the_owner() {
        System.out.println("Setting password...");
        orgManagement.setOrgPassword();
    }

    @And("I login as {string}")
    public void i_login_as(String userType) {
        System.out.println("Attempting login of the new user as " + userType);
        orgManagement.ensureUserLoginAs(userType);
    }

    @And("I enter randomEmail in the {string} field")
    public void iEnterRandomEmailInTheField(String field) {
        orgManagement.enterEmailToField(field);
    }

    @And("I verify that {string} validation error message appears")
    public void iVerifyThatValidationErrorMessageAppears(String errorMessage) {
        orgManagement.ensureValidationFails(errorMessage);
    }

    @Then("I Ensure that request is successful")
    public void iEnsureThatRequestIsSuccessful() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Request is successfully done')]")));
        System.out.println(msg);
    }


    @And("I select the {string} option")
    public void iSelectTheOption(String sliderMenu) {
        System.out.println("Selecting " + sliderMenu + " Option from the slider menu...");
        orgManagement.selectMenu(sliderMenu);
    }

    @And("I select staff having following values")
    public void iSelectStaffHavingFollowingValues(Map<String, String> table) {
        WebElement button = null;
        List<WebElement> elements = driver.findElements(By.xpath("//tbody/tr[not (@class = \"smallTableRow\")]"));
        for (int i = 0; i < elements.size(); i++) {
            String admin = commonPage.commonFieldRead(elements.get(i).findElement(By.className("admin")));
            String suspended = commonPage.commonFieldRead(elements.get(i).findElement(By.className("suspended")));
            String readOnly = commonPage.commonFieldRead(elements.get(i).findElement(By.className("readOnly")));
            String firstName = commonPage.commonFieldRead(elements.get(i).findElement(By.className("accountObject.firstName")));
            String givenAdmin = table.get("admin");
            String givenSuspended = table.get("suspended");
            String givenReadOnly = table.get("readOnly");

            if (!firstName.equalsIgnoreCase("QBIS/Markel") && givenAdmin.equalsIgnoreCase(admin)
                    && givenSuspended.equalsIgnoreCase(suspended) && givenReadOnly.equalsIgnoreCase(readOnly)) {
                System.out.println("Precondition matched in row:" + (i + 1) + ", clicking edit button.");
                button = elements.get(i).findElement(By.xpath("td[last()]"));
                String emailForLogin = commonPage.commonFieldRead(elements.get(i).findElement(By.className("accountObject.email")));
                System.out.println("Setting " + emailForLogin + " in valueStore to be used later.");
                valueStore.put("staffEmail", emailForLogin);
                if (i >= 5) {
                    orgManagement.scroll(800);
                }
                break;
            }
        }
        if (button == null)
            Assert.fail("Precondition of the scenario \"" + scenarioName + "\" is not satisfied.");
        commonPage.clickElement(button);
    }

    @And("I verify that staff is read only")
    public void iVerifyTheReadOnlyStaff() {
        boolean result = false;
        try {
            result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(), \"You are in read-only mode\")]"))).isDisplayed();
        } catch (TimeoutException ignored) {
            System.out.println(ignored);
        }
        Assert.assertTrue(result, "\"You are in read-only mode\" message is not found on the home page.");
    }

    @And("I select alternate staff")
    public void iSelectAlternateStaff() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'ConfirmationDialog')]")));
        System.out.println("Confirmation dialog is displayed.");
        commonPage.commonDropDownSelectByIndex("Other non suspended staff", 1);
        System.out.println("Alternate staff is selected.");
    }


    @And("I enter randomValue in the {string} field")
    public void iEnterRandomValueInTheField(String field) {
        String value = null;
        if (field.equalsIgnoreCase("Unique Short Name")) {
            value = generateRandomStringFor("Test Business-");
            valueStore.put("businessName", value);
        } else if (field.equalsIgnoreCase("Agency Name")) {
            value = generateRandomStringFor("Test Agent-");
            generateAndSaveMailSubject(value);
        } else {
            value = valueStore.get("businessName");
            generateAndSaveMailSubject(value);
        }
        System.out.println("Entering " + value + " in the " + field + " field.");
        commonPage.commonFieldEnter(field, value);

    }

    private String generateRandomStringFor(String base) {
        StringBuilder builder = new StringBuilder(base);
        String uuid = UUID.randomUUID().toString();
        builder.append(uuid.substring(0, uuid.indexOf('-')));
        return builder.toString();
    }

    private void generateAndSaveMailSubject(String value) {
        String mailSubject = "Welcome to Your " + value + " Account";
        System.out.println("Saving: " + mailSubject + " to verify owner creation mail.");
        valueStore.put("mailSubject", mailSubject);
    }

    @And("I verify that {string} is not selected for {string}")
    public void iVerifyThatIsNotSelectedFor(String value, String field) {
        String fname;
        if (field.equalsIgnoreCase("Read Only")) {
            fname = "readOnly";
        } else {
            fname = field.toLowerCase();
        }
        WebElement radio = driver.findElement(By.xpath("//input[@type='radio'][@name='" + fname + "'][@value='" + value + "']"));
        System.out.println("Selected state of " + value + " option of " + field + " radio button is: " + radio.isSelected());
        Assert.assertTrue(!radio.isSelected(), value + " option was not expected to be selected for " + field + " radio button.");
    }


    @And("I empty the {string} field")
    public void iEmptyTheField(String field) {
        System.out.println("Emptying " + field);
        WebElement element = commonPage.commonField(field);
        commonPage.commonFieldEmpty(element);
    }

    @And("I pick email of an existing staff")
    public void iPickEmailOfAnExistingStaff() {
        WebElement element = driver.findElements(By.xpath("//tbody/tr[not (@class = \"smallTableRow\")]")).get(0);
        String existingStaffEmail = commonPage.commonFieldRead(element.findElement(By.className("accountObject.email")));
        System.out.println("Setting " + existingStaffEmail + " in valueStore to be used later.");
        valueStore.put("randomEmail", existingStaffEmail);
    }

    @And("I enter duplicate email in the {string} field")
    public void iEnterDuplicateEmailInTheField(String field) {
        String email = valueStore.get("randomEmail");
        System.out.println(email + " is fetched from valueStore.");
        commonPage.commonFieldEnter(field, email);
    }

    @Then("I verify that {string} pop message is displayed")
    public void iVerifyThatPopMessageIsDisplayed(String message) {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + message + "')]")));
        System.out.println(msg.getText());
    }

    @And("I verify that {string} value is not {string}")
    public void iVerifyThatValueIsNot(String radio, String option) {
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='" + radio + "']/following-sibling::div/descendant::input[@value='" + option + "']"));
        Assert.assertFalse(element.isSelected());

    }
}
