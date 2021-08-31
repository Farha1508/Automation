package Steps;

import Base.BaseUtil;
import Pages.GridFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridSteps extends BaseUtil {
    private GridFunctions gridFunctions;
    public GridSteps(){
        this.gridFunctions = new GridFunctions(driver, js);
    }


    @And("I get the {string} for {string}")
    public void iGetTheFor(String colName, String rowName) {
        BaseUtil.pageLoaded();
        System.out.println(colName + " for " + rowName + " is: " + gridFunctions.gridEntry(rowName, colName));
    }

    @And("I get the current grid page number")
    public void iGetTheCurrentGridPageNumber() throws Exception {
        BaseUtil.pageLoaded();
        int result = gridFunctions.gridPageNumber(currentTab, "current");
        if (result == 0) {
            System.out.println("Page number not displayed as there are no entries in the grid");
        } else if (result == -1) {
            throw new Exception("Unable to get page number");
        } else {
            System.out.println("Currently on page " + result + " of grid");
        }
    }

    @And("I move to the next page in the grid")
    public void iMoveToTheNextPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking next page button in " + currentTab + " grid");
        int currentPage = gridFunctions.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (gridFunctions.gridNextPage(currentTab)) {
                System.out.println("Moved to next page in grid");
            } else {
                System.out.println("No next page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to next page as the grid has no entries");
        } else {
            int newPage;
            if (gridFunctions.gridNextPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = gridFunctions.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage + 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to next page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to next page of grid as page " + currentPage + " is the last page");
            }
        }
    }

    @And("I move to the previous page in the grid")
    public void iMoveToThePreviousPageInTheGrid() {
        BaseUtil.pageLoaded();
        System.out.println("Clicking previous page button in " + currentTab + " grid");
        int currentPage = gridFunctions.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (gridFunctions.gridPrevPage(currentTab)) {
                System.out.println("Moved to previous page in grid");
            } else {
                System.out.println("No previous page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to previous page as the grid has no entries");
        } else {
            int newPage;
            if (gridFunctions.gridPrevPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = gridFunctions.gridPageNumber(currentTab, "current");
                Assert.assertEquals(newPage, currentPage - 1,
                        "Did not move to previous page after clicking previous page button! Previous page: " + currentPage + " current page: " + newPage);
                System.out.println("Successfully moved to previous page " + newPage + " from original page " + currentPage);
            } else {
                System.out.println("Could not go to previous page of grid as page " + currentPage + " is the first page");
            }
        }
    }

    @And("I change pages by entering a number")
    public void iEnterANumber() throws Exception {
        BaseUtil.pageLoaded();
        int currentPage = gridFunctions.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change pages as there are no entries in the grid");
        } else {
            int randPage = gridFunctions.gridRandomPage(currentTab);
            if (randPage == 1) {
                System.out.println("Only one page, grid displays all available records");

            } else if (randPage == -1) {
                throw new Exception("Unable to find page number");
            } else {
                System.out.println("Moved from page " + currentPage + " to " + randPage);
            }
        }
    }

    @When("I navigate to the {string} tab")
    public void iNavigateToTheTab(String tabName) {
        currentTab = tabName;
        Assert.assertTrue(gridFunctions.gridTab(currentTab), "Unable to navigate to " + tabName + " tab!");
        System.out.println("On " + tabName + " tab");
        BaseUtil.pageLoaded();
    }

    @When("I select the {string} option from the the Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
        //commonPage.pageLoaded();
        BaseUtil.pageLoaded();
        int currentPage = gridFunctions.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change grid size as there are no entries in the grid");
        } else {
            rowCount = Integer.parseInt(rowOption);
            gridFunctions.gridViewSelection(currentTab, rowOption);
        }

    }

    @Then("The number of rows displayed will be less than or equal to the number selected")
    public void theNumberOfRowsDisplayedWillBeLessThanOrEqualToTheNumberSelected() throws Exception {
        BaseUtil.pageLoaded();
        int currentPage = gridFunctions.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("No rows to count as there are no entries in the grid");
        } else {
            int recordCount = gridFunctions.gridRecordNumber(currentTab);
            int visibleRows = gridFunctions.gridRowCount(currentTab);
            Assert.assertTrue(visibleRows == rowCount || visibleRows == recordCount,
                    "Rows displayed (" + visibleRows + ") does note equal selection from Viewing dropdown (" + rowCount + "), or record count (" + recordCount + ")");
            System.out.println("Number of records currently displayed is " + visibleRows + " of a possible " + recordCount);
        }
    }

    @When("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) {
        String currentRow1 = gridFunctions.gridEntry("row 1", "Work Order #").getText();
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        headerChoice = header;
        headerInfo = textEntry;
        gridFunctions.gridHeaderField(header, textEntry);
        gridFunctions.waitForFilter(currentRow1, "Work Order #");
        BaseUtil.pageLoaded();
    }

    @Then("The {string} grid header is {string}")
    public void theGridHeaderIs(String header, String expectation){
        System.out.println("Checking the " + header + " header contents.");
        BaseUtil.pageLoaded();
        Assert.assertEquals(gridFunctions.gridHeaderFieldRead(header), expectation);
        System.out.println(expectation);
    }


    @And("I click the Doc retrieval Reset button")
    public void iClickTheResetButton(){
        gridFunctions.clickResetButton();
    }



    @And("I get the {string} for {string} in the grid")
    public void iGetTheForInTheGrid(String columnName, String rowName) {
        System.out.println(gridFunctions.gridEntry(rowName, columnName));
    }

    @And("I open the {string} grid item")
    public void iOpenTheGridItem(String gridItem) {
        System.out.println(gridFunctions.gridOpenItem(currentTab, gridItem));
    }


    @When("I select {string} from the {string} header in the grid")
    public void iSelectFromTheHeaderInTheGrid(String selection, String header) throws InterruptedException {
        System.out.println("Selecting " + selection + " from the " + header + " header");
        headerChoice = header;
        headerInfo = selection;
        Assert.assertTrue(gridFunctions.gridHeaderSelectorSelect(header, selection),
                "Could not find " + selection + " option in " + header + " grid header");
        Thread.sleep(3000);

    }

    @When("I get the number of records in the {string} tab")
    public void iGetTheNumberOfRecordsInTheTab(String tabName) {
        BaseUtil.pageLoaded();
        gridFunctions.gridTab(tabName);
        gridRecords = gridFunctions.gridRecordNumber(tabName);
        System.out.println("Number of records in " + tabName + " grid: " + gridRecords);
    }

    @And("I check if {string} is selected in the {string} header")
    public void iCheckIfIsSelectedInTheHeader(String selection, String headerName) {
        ArrayList<String> entries = gridFunctions.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        Assert.assertTrue(entries.contains(selection), selection + " not selected in " + headerName + " list!");
        System.out.println(selection + " is currently selected in " + headerName + " list");

    }

    @And("I check if the following items are selected in the {string} header")
    public void iCheckIfTheFollowingItemsAreSelectedInTheHeader(String headerName, List<List<String>> table) {
        List<String> items = table.get(0);
        ArrayList<String> entries = gridFunctions.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        for (String entry : items) {
            Assert.assertTrue(entries.contains(entry), entry + " not selected in " + headerName + " list!");
            System.out.println(entry + " is currently selected in " + headerName + " list");
        }
    }

    String rowInfo;

    @And("I get the {string} for the {string} row of the grid")
    public void iGetTheForTheRowOfTheGrid(String column, String row) {
        headerInfo = gridFunctions.gridEntry(row, column).getText();
        headerChoice = column;
        System.out.println(gridFunctions.gridEntry(row, column).getText());
    }
    @When("I get the {string} for {string} of the grid")
    public void iGetTheForOfTheGrid(String column, String row){

    }


    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() {
        gridFunctions.gridHeaderField("WO #", rowInfo);
    }

    String headerChoice;
    String headerInfo;

    @And("I get the {string} for {string} row of the grid")
    public void iGetTheForRowOfTheGrid(String column, String row) {
        headerChoice = column;
        headerInfo = gridFunctions.gridEntry(row, column).getText();
        valueStore.put("headerChoice", headerChoice);
        valueStore.put("headerInfo", headerInfo);
        System.out.println("Reading and storing the info in row: "+row+", and column: "+column+", which is: "+headerInfo);

    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String currentRow1 = gridFunctions.gridEntry("row 1", "Work Order #").getText();
        gridFunctions.gridHeaderField(headerChoice, headerInfo);
        gridFunctions.waitForFilter(currentRow1, "Work Order #");
        BaseUtil.pageLoaded();
    }

    @Then("The information in the first row of the grid will match what was entered")
    public void theInformationInTheFirstRowOfTheGridWillMatchWhatWasEntered() {
        if (gridFunctions.gridRecordNumber(currentTab) == 0) {
            System.out.println("No entries in grid match " + headerInfo + " in " + headerChoice + " column");
        } else {
            String result = gridFunctions.gridEntry("row 1", headerChoice).getText();
            System.out.println("result is: " + result);
            Assert.assertTrue(gridFunctions.gridEntry("row 1", headerChoice).getText().toLowerCase().contains(headerInfo.toLowerCase()),
                    "Grid data does not match. Expected '" + headerInfo + "', found '" + gridFunctions.gridEntry("row 1", headerChoice).getText() + "'");
            System.out.println("Entry for " + headerChoice + " header in first row of grid matches " + headerInfo);
        }
    }

    @And("I select that information from the grid header")
    public void iSelectThatInformationFromTheGridHeader() {
        gridFunctions.gridHeaderSelectorSelect(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(gridFunctions.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("Verify the following headers are present")
    public void verifyTheFollowingHeadersArePresent(List<String> table) {
        //commonPage.pageLoaded();
        BaseUtil.pageLoaded();

        for (String header : table) {
            Assert.assertTrue(gridFunctions.gridHeaderFind(currentTab, header),
                    header + " header not visible in " + currentTab + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }

    @And("I set the {string} field in the {string} header to {string}-{string}-{string}")
    public void iSetTheFieldInTheHeaderTo(String fromTo, String headerName, String day, String month, String year) {
        gridFunctions.gridHeaderDateSelect(headerName, fromTo, day, month, year);
    }

    @And("I set the date in the {string} header to the following")
    public void iSetTheDateInTheHeaderToTheFollowing(String header, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if(selections.containsKey("Time")) {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + selections.get("From / To") + " field of " + header + " header.");
            gridFunctions.gridHeaderDateSelect(header, selections);
        } else {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " into " + selections.get("From / To") + " field of " + header + " header.");
            gridFunctions.gridHeaderDateSelect(header, selections.get("From / To"), selections.get("Day"), selections.get("Month"), selections.get("Year"));
        }

    }

    @And("I set that date in the header")
    public void iSetThatDateInTheHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String[] date;
        Map<String, String> dateMap = new HashMap<>();
        if (headerInfo.contains("/")) {
            date = headerInfo.split("/");
        } else {
            date = headerInfo.split("-");
        }
        dateMap.put("Month", new DateFormatSymbols().getMonths()[Integer.parseInt(date[0].replaceAll("^0", ""))-1]);
        dateMap.put("Day", date[1].replaceAll("^0", ""));
        dateMap.put("Year", date[2].replaceAll(" .+", ""));

        if (driver.getCurrentUrl().contains("policychecking")) {
            String[] time = date[2].split(" ");
            dateMap.put("From / To", "From");
            dateMap.put("Time", time[1]);
            dateMap.put("AM / PM", time[2]);
            gridFunctions.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            gridFunctions.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            gridFunctions.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            gridFunctions.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }


        BaseUtil.pageLoaded();
    }

    @And("set the tab to {string}")
    public void setTheTabTo(String tabName) {
        currentTab = tabName;
    }

    /*@Then("I get the unique result")
    public void iGetUniqueResult() throws InterruptedException {
        gridFunctions.getUniqueResult();
    }

    @Then("I get the multiple result")
    public void iGetMultipleResult() throws InterruptedException {
        gridFunctions.getUniqueResult();
    }

    @Then("I get no result")
    public void iGetNoResult() throws InterruptedException {
        gridFunctions.getUniqueResult();
    }*/
    String button;

    @Then("I click on {string} button")
    public void iClickOnButton(String button)
    {
        System.out.println("All values appearing in the Search list");
    }

    String colDropdown;

    @When("I click on {string} column dropdown")
    public void iClickOnColumnDropdown(String colDropdown){
        System.out.println("Entering " + headerInfo + " into " + colDropdown + " Column Dropdown");
    }

    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        gridFunctions.gridHeaderSort(headerName, sort);
    }
}
