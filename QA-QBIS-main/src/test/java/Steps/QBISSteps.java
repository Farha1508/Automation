package Steps;

import Base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QBISSteps extends BaseUtil {
    @And("I open the New Application form")
    public void iOpenTheNewApplicationForm() {
        System.out.println("Opening new application form");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='root']/div/div[1]/div[4]/div/header/div/button")))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='root']/div/div[1]/div[6]/div/div[2]/button/span"))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[1]/div[3]/div[2]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //div[@class='qbisForm']")));


    }

    @And("Ensure I am taken to the Pick Your Product page")
    public void ensureIAmTakenToThePickYourProductPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ApplicationWizardProductSelection']")));

    }

    @Then("The General Questions page will be displayed")
    public void theGeneralQuestionsPageWillBeDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ApplicationWizardSmartPreQuoteQuestions']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#root > div > div.wrap > div.loadingOverlay")));
    }

    @Then("The Employment Practices Liability Questions form will be displayed")
    public void theEmploymentPracticesLiabilityQuestionsFormWillBeDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fieldSection_EPL\"]/div/div/span")));
    }

    @When("I choose the {string} option from the {string} question")
    public void iChooseTheOptionFromTheQuestion(String option, String radio) {
        String value;
        // Some radio buttons have true || false value while others have yes || no
        if (option.equalsIgnoreCase("yes") || option.equalsIgnoreCase("no")) {
            value = option.equalsIgnoreCase("yes") ? "true" : "false";
        } else {
            value = option;
        }
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()=\""+radio+"\"]/following-sibling::div/descendant::input[@value=\""+value+"\"]"))).click();
        driver.findElement(By.xpath("//span[normalize-space()=\"" + radio + "\"]/following-sibling::div/descendant::input[@value=\"" + value + "\"]")).click();
    }

    @And("I pick the following from the {string} multi-select")
    public void iPickTheFollowingFromTheMultiSelect(String mSelect, List<List<String>> table) {
        List<String> picks = table.get(0);
        commonPage.commonMultiSelect(mSelect, picks);
//        picks.forEach((entry) -> {
//            System.out.println("Table entry: "+ entry);
//        });
    }
}
