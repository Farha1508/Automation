package Steps;


import Base.BaseUtil;
import Pages.CommonGrid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.util.*;

public class GridSteps extends BaseUtil {

    @And("I get the {string} for {string}")
    public void iGetTheFor(String colName, String rowName) {
        BaseUtil.pageLoaded();
        System.out.println(colName + " for " + rowName + " is: " + commonGrid.gridEntry(rowName, colName));
    }

    @And("I get the current grid page number")
    public void iGetTheCurrentGridPageNumber() throws Exception {
        BaseUtil.pageLoaded();
        int result = commonGrid.gridPageNumber(currentTab, "current");
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
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridNextPage(currentTab)) {
                System.out.println("Moved to next page in grid");
            } else {
                System.out.println("No next page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to next page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridNextPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
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
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == -1) {
            if (commonGrid.gridPrevPage(currentTab)) {
                System.out.println("Moved to previous page in grid");
            } else {
                System.out.println("No previous page to move to in grid");
            }
        } else if (currentPage == 0) {
            System.out.println("Cannot go to previous page as the grid has no entries");
        } else {
            int newPage;
            if (commonGrid.gridPrevPage(currentTab)) {
                BaseUtil.pageLoaded();
                newPage = commonGrid.gridPageNumber(currentTab, "current");
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
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change pages as there are no entries in the grid");
        } else {
            int randPage = commonGrid.gridRandomPage(currentTab);
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
        currentTab = tabName.replaceAll("\\s", "");
        Assert.assertTrue(commonGrid.gridTab(currentTab), "Unable to navigate to " + tabName + " tab!");
        System.out.println("On " + tabName + " tab");
    }

    @When("I select the {string} option from the the Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("Cannot change grid size as there are no entries in the grid");
        } else {
            rowCount = Integer.parseInt(rowOption);
            commonGrid.gridViewSelection(currentTab, rowOption);
        }

    }

    @Then("The number of rows displayed will be less than or equal to the number selected")
    public void theNumberOfRowsDisplayedWillBeLessThanOrEqualToTheNumberSelected() {
        BaseUtil.pageLoaded();
        int currentPage = commonGrid.gridPageNumber(currentTab, "current");
        if (currentPage == 0) {
            System.out.println("No rows to count as there are no entries in the grid");
        } else {
            int recordCount = commonGrid.gridRecordNumber(currentTab);
            int visibleRows = commonGrid.gridRowCount(currentTab);
            Assert.assertTrue(visibleRows == rowCount || visibleRows == recordCount,
                    "Rows displayed (" + visibleRows + ") does note equal selection from Viewing dropdown (" + rowCount + "), or record count (" + recordCount + ")");
            System.out.println("Number of records currently displayed is " + visibleRows + " of a possible " + recordCount);
        }
    }

    @And("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) {
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        headerChoice = header;
        headerInfo = textEntry;
        commonGrid.gridHeaderField(header, textEntry);
        BaseUtil.pageLoaded();
    }

    @And("I get the {string} for {string} in the grid")
    public void iGetTheForInTheGrid(String columnName, String rowName) {
        System.out.println(commonGrid.gridEntry(rowName, columnName));
    }

    @And("I open the {string} grid item")
    public void iOpenTheGridItem(String gridItem) {
        System.out.println(commonGrid.gridOpenItem(currentTab, gridItem));
    }

    @And("I select {string} from the {string} header in the grid")
    public void iSelectFromTheHeaderInTheGrid(String selection, String header) {
        System.out.println("Selecting " + selection + " from the " + header + " header");
        headerChoice = header;
        headerInfo = selection;
        Assert.assertTrue(commonGrid.gridHeaderSelectorSelect(header, selection),
                "Could not find " + selection + " option in " + header + " grid header");
        BaseUtil.pageLoaded();
    }

    @And("I check if {string} is selected in the {string} header")
    public void iCheckIfIsSelectedInTheHeader(String selection, String headerName) {
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        Assert.assertTrue(entries.contains(selection), selection + " not selected in " + headerName + " list!");
        System.out.println(selection + " is currently selected in " + headerName + " list");

    }

    @And("I check if the following items are selected in the {string} header")
    public void iCheckIfTheFollowingItemsAreSelectedInTheHeader(String headerName, List<List<String>> table) {
        List<String> items = table.get(0);
        ArrayList<String> entries = commonGrid.gridHeaderSelectorRead(headerName);
        Assert.assertNotNull(entries, "No active selections in " + headerName + " list!");
        for (String entry : items) {
            Assert.assertTrue(entries.contains(entry), entry + " not selected in " + headerName + " list!");
            System.out.println(entry + " is currently selected in " + headerName + " list");
        }
    }

    String rowInfo;

    @And("I get the {string} for the {string} row of the grid")
    public void iGetTheForTheRowOfTheGrid(String column, String row) {
        headerInfo = commonGrid.gridEntry(row, column).getText();
        headerChoice = column;
        System.out.println(commonGrid.gridEntry(row, column).getText());
    }

    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() {
        commonGrid.gridHeaderField("WO #", rowInfo);
    }

    @And("I get the {string} for {string} row of the grid")
    public void iGetTheForRowOfTheGrid(String column, String row) {
        headerChoice = column;
        headerInfo = commonGrid.gridEntry(row, column).getText();
        valueStore.put("headerChoice", headerChoice);
        valueStore.put("headerInfo", headerInfo);
        System.out.println("headerChoice is: "+headerChoice);
        System.out.println("headerInfo is: "+headerInfo);

    }

    @And("I enter the {string} value of {string} into value store as {string}")
    public void iEnterValueToValueStore(String header, String row, String valueStoreKey) {
        headerInfo = commonGrid.gridEntry(row, header).getText();
        valueStore.put(valueStoreKey, headerInfo);
    }

    @And("I verify the {string} checkbox status for {string} row of the grid is {string}")
    public void iGetTheCheckboxStatus(String column, String row, String expectedBoolean) {
        WebElement checkbox = commonGrid.gridEntry(row, column);
        String xpath = commonGrid.generateXPATH(checkbox, "/input");
        Boolean expected = Boolean.parseBoolean(expectedBoolean);
        Boolean isChecked = false;
        try {
            isChecked = driver.findElement(By.xpath(xpath)).getAttribute("checked").equals("true");

        } catch (NullPointerException ignored) {
           
        } catch (NoSuchElementException nsee) {
            Assert.fail("The column selected was not a checkbox");
        }
        System.out.println("Checkbox is checked: "+isChecked);
        Assert.assertEquals(expected, isChecked, "Checkbox is not checked");

    }


    @And("I get the {string} checkbox status for {string} row of the grid")
    public void iGetTheCheckboxStatus(String column, String row) {
        WebElement headerCheckStatus = commonGrid.gridEntry(row, column);
        String xpath = commonGrid.generateXPATH(headerCheckStatus, "/input");
        Assert.assertNotNull(xpath);
        Boolean isChecked = false;
        try {
            isChecked = driver.findElement(By.xpath(xpath)).getAttribute("checked").equals("true");
            System.out.println("checkbox is checked: "+isChecked);
        } catch (NullPointerException npe){
            System.out.println("Checkbox is checked:"+ isChecked);
        } catch (NoSuchElementException nsee) {
            Assert.fail("Selected column is not a checkbox column");
        }
    }

    @Then("I verify that value of {string} for {string} matches")
    public void iVerifyGridValueMatchesOriginal(String columnName, String rowNumber) {
        newHeaderInfo = commonGrid.gridEntry(rowNumber, columnName).getText();
        Assert.assertTrue(headerInfo.equals(newHeaderInfo), "header info is " + headerInfo + "newHeaderInfo is " + newHeaderInfo);
    }

    @Then("I verify that value of {string} for {string} is {string}")
    public void iVerifyGridValueIs(String columnName, String rowNumber, String value) {
        headerChoice = columnName;
        headerInfo = commonGrid.gridEntry(rowNumber, columnName).getText();
        System.out.println("I expect " + value + " and I found " + headerInfo);
        Assert.assertTrue(headerInfo.contains(value), "Expected " + value + " but found " + headerInfo);
    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        commonGrid.gridHeaderField(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("I enter a filter that will return no results")
    public void iEnterAFilterWithNoResults() {
        System.out.println("Entering XXXXX into the Client Name field");
        commonPage.gridHeaderEnter("Client Name", "XXXXX");
        BaseUtil.pageLoaded();
    }

    @Then("The information in the first row of the grid will match what was entered")
    public void theInformationInTheFirstRowOfTheGridWillMatchWhatWasEntered() {
        if (commonGrid.gridRecordNumber(currentTab) == 0) {
            System.out.println("No entries in grid match " + headerInfo + " in " + headerChoice + " column");
        } else {
            String result = commonGrid.gridEntry("row 1", headerChoice).getText();
            System.out.println("result is: " + result);
            Assert.assertTrue(commonGrid.gridEntry("row 1", headerChoice).getText().toLowerCase().contains(headerInfo.toLowerCase()),
                    "Grid data does not match. Expected '" + headerInfo + "', found '" + commonGrid.gridEntry("row 1", headerChoice).getText() + "'");
            System.out.println("Entry for " + headerChoice + " header in first row of grid matches " + headerInfo);
        }
    }

    @And("I select that information from the grid header")
    public void iSelectThatInformationFromTheGridHeader() {
        commonGrid.gridHeaderSelectorSelect(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
        } catch (TimeoutException ignored) {
        }
        BaseUtil.pageLoaded();
    }

    @And("Verify the following headers are present")
    public void verifyTheFollowingHeadersArePresent(List<String> table) {
        for (String header : table) {
            BaseUtil.pageLoaded();
            Assert.assertTrue(commonGrid.gridHeaderFind(currentTab, header),
                    header + " header not visible in " + currentTab + " grid!");
            System.out.println(header + " header verified on grid");
        }
    }

    @And("I set the {string} field in the {string} header to {string}-{string}-{string}")
    public void iSetTheFieldInTheHeaderTo(String fromTo, String headerName, String day, String month, String year) {
        commonGrid.gridHeaderDateSelect(headerName, fromTo, day, month, year);
    }

    @And("I set the date in the {string} header to the following")
    public void iSetTheDateInTheHeaderToTheFollowing(String header, List<Map<String, String>> table) {
        Map<String, String> selections = table.get(0);
        if(selections.containsKey("Time")) {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " " + selections.get("Time") + selections.get("AM / PM") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections);
        } else {
            System.out.println("Entering " + selections.get("Month") + "/" + selections.get("Day") + "/" + selections.get("Year") + " into " + selections.get("From / To") + " field of " + header + " header.");
            commonGrid.gridHeaderDateSelect(header, selections.get("From / To"), selections.get("Day"), selections.get("Month"), selections.get("Year"));
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

        if (driver.getCurrentUrl().contains("endorsements")) {
            String[] time = date[2].split(" ");
            dateMap.put("From / To", "From");
            dateMap.put("Time", time[1]);
            dateMap.put("AM / PM", time[2]);
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            commonGrid.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            commonGrid.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            commonGrid.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }


        BaseUtil.pageLoaded();
    }

    @And("set the tab to {string}")
    public void setTheTabTo(String tabName) {
        currentTab = tabName;
    }

    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        System.out.println("Sorting the " + headerName + " coloumn by " + sort);
        commonGrid.gridHeaderSort(headerName, sort);
    }

    @And("The top row background colour is {string}")
    public void iVerifiedTheWorkOrderBackGroundColor(String colourName) {
        // colourName should be one of the words in the Switch statement.
        // Or you can send in a custom colour using the format "RRR,GGG,BBB,A"

        System.out.println("Checking the background colour of the first row.");
        String expectatedColour;
        String actualColour = commonGrid.verifyBackGroundColor(currentTab);
        switch (colourName) {
            case "White":
                expectatedColour="rgba(249, 249, 249, 1)";
                break;
            case "Rush - Yellow":
                expectatedColour="rgba(255, 255, 102, 1)";
                break;
            case "DueDateToday -Purple":
                expectatedColour="rgba(222, 90, 193, 1)";
                break;
            case "SLA -Red":
                expectatedColour="rgba(238, 67, 67, 1)";
                break;
            default:
                System.out.println("Colour: " + colourName + " was not in the switch statement. Using the manually entered value.");
                expectatedColour = "rgba("+colourName+")";
        }
        Assert.assertEquals(actualColour,expectatedColour, "The colour of the first row ("+actualColour+") did not match the expected colour ("+expectatedColour+")");
    }

    @And("I find the Endorsement Processing work order in the grid")
    public void iFindTheWorkOrderInTheGrid() {
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("Work Order #", valueStore.get("WO #"));
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(valueStore.get("WO #"))));
        } catch(TimeoutException e) {
            Assert.fail(e.toString());
        }
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("Work Order #", "");
    }

    @And("I find the Endorsement Processing work order in the Time Record Admin grid")
    public void iFindTheWorkOrderInTheTimeRecordAdminGrid() {
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("WO#", valueStore.get("WO #"));
        Assert.assertEquals(commonGrid.gridEntry("row 1", "WO#").getText(), valueStore.get("WO #"));
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("WO#", "");
    }

    @And("I verify the Endorsement Processing work order is not in the {string} grid")
    public void iDoNotFindTheWorkOrderInTheGrid(String gridCountTab) {
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter("Work Order #", valueStore.get("WO #"));
        commonPage.pageLoaded();
        Assert.assertTrue(commonGrid.gridCount(gridCountTab).equals("0"));
    }

    @And("I open the Work Order")
    public void iOpenTheWorkOrder() {
        BaseUtil.pageLoaded();
        String button = valueStore.get("WO #");
        Assert.assertTrue(commonGrid.openWorkOrder(button), "Cannot Open Work Order");
        BaseUtil.pageLoaded();
    }

    @Then("I verify the content in {string} matches what was entered in the {string} field")
    public void iVerifyTheTextMatches(String fieldCreated, String fieldEntered) {
        String fieldText = commonPage.commonFieldRead(fieldCreated);
        System.out.println("Text currently visible in " + fieldCreated + " field: " + fieldText);
        valueStore.put(fieldCreated, fieldText);
        Assert.assertTrue(fieldText.equals(valueStore.get(fieldEntered)));
    }

    @Then("I verify that the number of rows in the {string} tab has not changed")
    public void iVerifyTheNumberOfRecordsInTheTab(String tabName) {
        BaseUtil.pageLoaded();
        commonGrid.gridTab(tabName);
        int currentGridRecords = commonGrid.gridRecordNumber(tabName);
        Assert.assertEquals(gridRecords, currentGridRecords);
        System.out.println("Number of records in " + tabName + " grid is now: " + currentGridRecords);

    }

    @When("I get the number of records in the {string} tab")
    public void iGetTheNumberOfRecordsInTheTab(String tabName) {
        BaseUtil.pageLoaded();
        commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        valueStore.put("numberOfRecords", String.valueOf(gridRecords));
        System.out.println("Number of records in " + tabName + " grid: " + gridRecords);
    }

    @When("I click the {string} tab Reset button")
    public void iClickTheResetButton(String tab) {

        BaseUtil.pageLoaded();
        Assert.assertTrue(commonGrid.clickResetButton(tab), "Reset Button not found");
        BaseUtil.pageLoaded();
    }

    @And("I get the current record count in the {string} tab")
    public void iGetCurrentRecordCount(String tab) {
        BaseUtil.pageLoaded();
        String count = commonGrid.gridCount(tab);
        Assert.assertTrue(count != null, "grid count is null");
        valueStore.put("recordCount", count);
        System.out.println(valueStore.get("recordCount"));
    }

    @Then("I verify the number of {string} records in KPI has {string}")
    public void iVerifyIfRecordsChanged(String tab, String changeTest) {
        BaseUtil.pageLoaded();
        Integer currentCount = Integer.valueOf(commonGrid.gridCount(tab));
        Integer oldCount = Integer.valueOf(valueStore.get("recordCount"));
        Assert.assertTrue(currentCount != null, "Current count is null");
        Assert.assertTrue(oldCount != null, "Old count is null");
        if (changeTest.toLowerCase(Locale.ROOT).equals("increased")){
            Assert.assertTrue(currentCount - oldCount == 1, "Expected increased count is wrong, should be " + (oldCount+1) + " but was " + currentCount);
            System.out.println("the records were successfully increased");
        } else if (changeTest.toLowerCase(Locale.ROOT).equals("decreased")) {
            Assert.assertTrue(oldCount - currentCount == 1, "Expected decrease count is wrong, should be " + (oldCount - 1) + " but was " + currentCount);
            System.out.println("the records were successfully decreased");
        } else if (changeTest.toLowerCase(Locale.ROOT).equals("not changed")){
            Assert.assertTrue(oldCount.equals(currentCount), "Expected count was wrong, should be " + oldCount + " but was " + currentCount);
            System.out.println("the records were not changed");
        } else {
            Assert.assertTrue(false, "changeTest must be set to 'increased' or 'decreased' or 'not changed' but was " + changeTest);
        }
    }

    @Then("I verify the number of records in {string} is 0")
    public void iVerifyTheRecordsAreZero(String tab) {
        BaseUtil.pageLoaded();
        Integer currentCount = Integer.valueOf(commonGrid.gridCount(tab));
        Assert.assertTrue(currentCount == 0);
    }

    @And("I filter by {string} in the {string} text field")
    public void iFilterByTextField(String filter, String header) {
        commonPage.pageLoaded();
        commonPage.gridHeaderEnter(header, filter);
        commonPage.pageLoaded();
    }

    @Then("The number of records in the {string} tab is {string}")
    public void iCheckTheNumberOfRecordsInTheTab(String tabName, String expectation){
        // expectations should be "increased", "decreased", or "the same"
        System.out.println("Checking the number of records in the " + tabName + " grid.");
        commonGrid.gridTab(tabName);
        gridRecords = commonGrid.gridRecordNumber(tabName);
        int oldNumber = Integer.parseInt(valueStore.get("numberOfRecords"));
        if(expectation.equalsIgnoreCase("decreased")){
            Assert.assertTrue(gridRecords < oldNumber);
        }
        else if(expectation.equalsIgnoreCase("increased")){
            Assert.assertTrue(gridRecords > oldNumber);
        }
        else{
            Assert.assertEquals(gridRecords, oldNumber);
        }
    }

    @And("I click the Reset button in the grid header")
    public void iClickTheResetButton() {
        System.out.println("Clicking the Reset button to clear all filters");
        Assert.assertTrue(commonGrid.clickResetButton(), "Failed to click the Reset button");
    }

    @Then("I expect {string} in the {string} grid header")
    public void iReadTheGridHeader(String textEntry, String header) {
        System.out.println("Reading the " + header + " column filter");
        Assert.assertEquals(commonGrid.gridHeaderFieldRead(header),textEntry);
        BaseUtil.pageLoaded();
    }

    @Then("The date in the first row of the grid will match what was entered")
    public void theDateInTheFirstRowOfTheGridWillMatchWhatWasEntered() {
        if (commonGrid.gridRecordNumber(currentTab) == 0) {
            System.out.println("No entries in grid match " + headerInfo + " in " + headerChoice + " column");
        } else {
            // Split by a space to remove the time component.
            String actual = commonGrid.gridEntry("row 1", headerChoice).getText().split(" ")[0];
            String expected = headerInfo.toLowerCase().split(" ")[0];
            System.out.println("result is: " + actual);
            Assert.assertTrue(actual.toLowerCase().contains(expected),
                    "Grid data does not match. Expected '" + expected + "', found '" + actual + "'");
            System.out.println("Entry for " + headerChoice + " header in first row of grid matches " + expected);
        }
    }

    /**
     * This step was made for the Hours column in the Time Rec Admin grid, which has two fields that need to be filled with the same value.
     * */
    @And("I enter that information into both grid headers")
    public void iEnterThatInformationIntoBothGridHeaders() {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        String currentRow1 = commonGrid.gridEntry("row 1", "WO#").getText();
        commonGrid.gridHeaderFields(headerChoice, headerInfo);
        try {
            wait.until(ExpectedConditions.stalenessOf(commonGrid.gridEntry("row 1", headerChoice)));
            if (commonGrid.waitForFilter(currentRow1, "WO#") == 1) {
                System.out.println("Grid is sorted");
                Assert.assertTrue(true);
            } else if (commonGrid.waitForFilter(currentRow1, "WO#") == 0) {
                System.out.println("Timer timed out or top result is same as original");
            } else if (commonGrid.waitForFilter(currentRow1, "WO#") == -1) {
                Assert.fail("Sorting was interrupted");
            }
        } catch (TimeoutException ignored) {
            System.out.println("failed due to wot-pc#336 (Time-out errors when sorting grid).");
        }
        BaseUtil.pageLoaded();
    }

    @Then("I test the sorting of the following columns")
    public void iTestTheSortingOfFollowingColumns(List<String> columns) {
        // This is a compound method combining sorting, searching, and asserting

        // Depending on the table, we will wait on a different column to confirm that the grid has resorted
        String waitColumn = "";
        if(commonGrid.gridHeaderFind(currentTab, "WO#")){
            waitColumn="WO#";
        }
        else if(commonGrid.gridHeaderFind(currentTab, "Employee")){
            waitColumn="Employee";
        }
        else if(commonGrid.gridHeaderFind(currentTab, "Start Time")){
            waitColumn="Start Time";
        }
        else if(commonGrid.gridHeaderFind(currentTab, "Date Created")){ //Used in the Attachments grid
            waitColumn="Date Created";
        }

        for(String column : columns){
            System.out.println("Starting test for "+column+" column");
            // Check the sorting
            String currentRow1 = commonGrid.gridEntry("row 1", waitColumn).getText();
            System.out.println("Checking that the '"+column+"' can be sorted ascending.");
            commonGrid.gridHeaderSort(column, "ascending");
            commonGrid.waitForFilter(currentRow1, waitColumn, 20000);
            String rowOne = commonGrid.gridEntry("row 1", column).getText();
            String rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to floats for a proper check.
                float row1 = Float.parseFloat(rowOne);
                float row2 = Float.parseFloat(rowTwo);
                Assert.assertTrue(row1<=row2, "The " + column + " was not sorted (ascending) correctly. Expected " + row2 + " before " + row1);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)<=0, "The " + column + " was not sorted (ascending) correctly. Expected " + rowTwo + " before " + rowOne);
            }
            System.out.println(column + " ascending check passed.");

            currentRow1 = commonGrid.gridEntry("row 1", waitColumn).getText();
            System.out.println("Checking that the '"+column+"' can be sorted descending.");
            commonGrid.gridHeaderSort(column, "descending");
            commonGrid.waitForFilter(currentRow1, waitColumn, 20000);
            rowOne = commonGrid.gridEntry("row 1", column).getText();
            rowTwo = commonGrid.gridEntry("row 2", column).getText();
            try{ // If the column is only numerical values (eg. "# of Holder Certs"), we need to convert to ints for a proper check.
                float row1 = Float.parseFloat(rowOne);
                float row2 = Float.parseFloat(rowTwo);
                Assert.assertTrue(row1>=row2, "The " + column + " was not sorted (descending) correctly. Expected " + row2 + " before " + row1);
            }catch(NumberFormatException e){
                Assert.assertTrue(rowOne.compareToIgnoreCase(rowTwo)>=0, "The " + column + " was not sorted (descending) correctly. Expected " + rowTwo + " before " + rowOne);
            }
            System.out.println(column + " descending check passed.");
        }
    }

    @Then("All search results will contain the following values")
    public void allSearchResultsWillContainTheFollowingValues(Map<String, String> valuesToCheck) {
        //valuesToCheck has the form "header", "value"
        // This is a more general version of the previous step, and can accomodate empty grids.
        System.out.println("Checking the contents of the Advanced Search results");
        if(valuesToCheck.containsKey("empty")){
            Assert.assertEquals(commonGrid.topRowText(), valuesToCheck.get("empty"));
        }
        else{
            for(int i=1; i<=commonGrid.gridRowCount("Advanced Search"); i++){
                int finalI = i; // IntelliJ recommends this for stability.
                valuesToCheck.forEach((key, value) -> {
                    if(value.contains("<current user>")){
                        value.replaceAll("<current user>", valueStore.get("currentUser"));
                    }
                    Assert.assertEquals(commonGrid.gridEntry("row "+ Integer.toString(finalI), key).getText(), value);
                });
            }
        }
    }

    @Then("The work order will be displayed in the grid")
    public void theWorkOrderWillBeDisplayedInTheGrid() {
        System.out.println("Checking that the most recent work order is displayed in the grid.");
        Assert.assertEquals(commonGrid.gridEntry("row 1", "WO#").getText(), valueStore.get("WO #"));
    }

    @Then("There are no results")
    public void thereAreNoResults() {
        System.out.println("Checking that there are no results returned by the search.");
        Assert.assertEquals(commonGrid.gridRowCount(currentTab), 1); //There is one row with the empty message
        if(currentTab.equalsIgnoreCase("Time Record Admin")){
            Assert.assertEquals(commonGrid.topRowText(), "No matching records found");
        }else{
            Assert.assertEquals(commonGrid.topRowText(), "No data available in table");
            // This will not work on the default Time Rec Admin grid because there is no "Viewing N of M records" text to read
            Assert.assertEquals(commonGrid.gridRecordNumber(currentTab), 0);
        }
    }

    @Then("There are results")
    public void thereAreResults() {
        System.out.println("Checking that some values were returned by the search.");
        Assert.assertNotEquals(commonGrid.gridRowCount(currentTab), 1);
        Assert.assertNotEquals(commonGrid.gridRecordNumber(currentTab), 0);
    }


}
