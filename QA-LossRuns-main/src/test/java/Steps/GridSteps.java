package Steps;

import Base.BaseUtil;
import Pages.CommonForm;
import Pages.CommonGrid;
import Pages.GridFunctions;
import Pages.LossRunsHappyPathPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridSteps extends BaseUtil {
    private final CommonForm commonForm;
    private final CommonGrid commonGrid;

    public GridSteps() {
        this.commonForm = new CommonForm(driver, js);
        this.commonGrid = new CommonGrid(driver,js);
    }

    private final GridFunctions gridFunctions = new GridFunctions(driver);

    @And("I get the {string} for {string}")
    public void iGetTheFor(String colName, String rowName) {
        System.out.println(colName + " for " + rowName + " is: " + gridFunctions.gridEntry(rowName, colName));
    }

    @And("I get the current grid page number")
    public void iGetTheCurrentGridPageNumber() throws Exception {
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
    public void iMoveToThePreviousPageInTheGrid() throws InterruptedException {
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
                Thread.sleep(3000);
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
    public void iNavigateToTheTab(String tabName) throws Exception {
        currentTab = tabName;
        Assert.assertTrue(commonGrid.gridTab(currentTab), "Unable to navigate to " + tabName + " tab!");
        System.out.println("On " + tabName + " tab");
        BaseUtil.pageLoaded();
    }

    @When("I select the {string} option from the the Viewing drop down")
    public void iSelectTheOptionFromTheTheViewingDropDown(String rowOption) {
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

    @And("I enter {string} into the {string} grid header")
    public void iEnterIntoTheGridHeader(String textEntry, String header) throws InterruptedException {
        System.out.println("Searching for " + textEntry + " in " + header + " column");
        headerChoice = header;
        headerInfo = textEntry;
        gridFunctions.gridHeaderField(header, textEntry);
        Thread.sleep(3000);
    }

    @And("I get the {string} for {string} in the grid")
    public void iGetTheForInTheGrid(String columnName, String rowName) {
        System.out.println(gridFunctions.gridEntry(rowName, columnName));
    }

    @And("I open the {string} grid item")
    public void iOpenTheGridItem(String gridItem) {
        System.out.println(gridFunctions.gridOpenItem(currentTab, gridItem));
    }

    @And("I select {string} from the {string} header in the grid")
    public void iSelectFromTheHeaderInTheGrid(String selection, String header) throws InterruptedException {
        System.out.println("Selecting " + selection + " from the " + header + " header");
        Thread.sleep(3000);
        headerChoice = header;
        headerInfo = selection;
        Assert.assertTrue(gridFunctions.gridHeaderSelectorSelect(header, selection),
                "Could not find " + selection + " option in " + header + " grid header");
    }

    @When("I get the number of records in the {string} tab")
    public void iGetTheNumberOfRecordsInTheTab(String tabName) {
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
        rowInfo = gridFunctions.gridEntry(row, column).getText();
        System.out.println(gridFunctions.gridEntry(row, column).getText());
    }

    @And("I enter that wo number into the grid")
    public void iEnterThatWoNumberIntoTheGrid() throws InterruptedException {
        gridFunctions.gridHeaderField("WO #", rowInfo);
    }

    String headerChoice;
    String headerInfo;

    @And("I get the {string} for {string} row of the grid")
    public void iGetTheForRowOfTheGrid(String column, String row) {
        headerChoice = column;
        headerInfo = gridFunctions.gridEntry(row, column).getText();
        System.out.println(headerInfo);
    }

    @Then("Enter Work order Id under Global search field and verify")
    public void enterWorkOrderIdUnderGlobalSearchFieldAndVerify() throws InterruptedException, IOException {
        LossRunsHappyPathPage page = new LossRunsHappyPathPage(driver);
        page.GlobalSearch(headerInfo);
        Thread.sleep(3000);
    }

    @And("I enter that information into the grid header")
    public void iEnterThatInformationIntoTheGridHeader() throws InterruptedException {
        System.out.println("Entering " + headerInfo + " into " + headerChoice + " header");
        gridFunctions.gridHeaderField(headerChoice, headerInfo);
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
    public void iSelectThatInformationFromTheGridHeader() throws InterruptedException {
        gridFunctions.gridHeaderSelectorSelect(headerChoice, headerInfo);

    }

    @And("Verify the following headers are present")
    public void verifyTheFollowingHeadersArePresent(List<String> table) {
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
        if (selections.containsKey("Time")) {
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
        dateMap.put("Month", new DateFormatSymbols().getMonths()[Integer.parseInt(date[0].replaceAll("^0", "")) - 1]);
        dateMap.put("Day", date[1].replaceAll("^0", ""));
        dateMap.put("Year", date[2].replaceAll(" .+", ""));

        if (driver.getCurrentUrl().contains("lossruns")) {
            dateMap.put("From / To", "From");
            gridFunctions.gridHeaderDateSelect(headerChoice, dateMap);
            dateMap.put("From / To", "To");
            gridFunctions.gridHeaderDateSelect(headerChoice, dateMap);

        } else {

            gridFunctions.gridHeaderDateSelect(headerChoice, "From", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
            gridFunctions.gridHeaderDateSelect(headerChoice, "To", dateMap.get("Day"), dateMap.get("Month"), dateMap.get("Year"));
        }

    }


    @And("I sort the {string} grid column by {string}")
    public void iSortTheGridColumnBy(String headerName, String sort) {
        gridFunctions.gridHeaderSort(headerName, sort);
    }

    @Then("I set that duration range in the header")
    public void iSetThatDurationRangeInTheHeader() throws InterruptedException {
        gridFunctions.enterduration(headerInfo);
    }

    @Then("I test the sorting of the following columns")
    public void iTestTheSortingOfFollowingColumns(List<String> columns) {
        System.out.println(currentTab);
        // This is a compound method combining sorting, searching, and asserting

        // Depending on the table, we will wait on a different column to confirm that the grid has resorted
        String waitColumn = "";
        if(commonGrid.gridHeaderFind(currentTab, "WO #")){
            waitColumn="WO #";
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

        else if(commonGrid.gridHeaderFind(currentTab, "Date")){ //Used in the History grid
            waitColumn="Date";
        }

        else if(commonGrid.gridHeaderFind(currentTab, "User Name")){ //Used in the History grid
            waitColumn="User Name";
        }

        for(String column : columns){
            System.out.println("Starting test for "+column+" column");
            // Check the sorting
            System.out.println(waitColumn);
            String currentRow1 = commonGrid.gridEntry("row 1", waitColumn).getText();
            System.out.println("Checking that the '"+column+"' can be sorted ascending.");
            commonGrid.gridHeaderSort(column, "ascending");
            commonGrid.waitForFilter(currentRow1, waitColumn, 10000);
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
            commonGrid.waitForFilter(currentRow1, waitColumn, 10000);
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

    @And("I enter the {string} value of {string} into value store as {string}")
    public void iEnterValueToValueStore(String header, String row, String valueStoreKey) {
        headerInfo = commonGrid.gridEntry(row, header).getText();
        valueStore.put(valueStoreKey, headerInfo);
    }

    @And("I open the Work Order")
    public void iOpenTheWorkOrder() {
        BaseUtil.pageLoaded();
        String button = valueStore.get("WO #");
        Assert.assertTrue(commonGrid.openWorkOrder(button), "Cannot Open Work Order");
        BaseUtil.pageLoaded();
    }

    @And("I click the Reset button in the grid header")
    public void iClickTheResetButton() {
        System.out.println("Clicking the Reset button to clear all filters");
        commonGrid.clickResetButton();
    }

    @Then("I verify that value of {string} for {string} matches")
    public void iVerifyGridValueMatchesOriginal(String columnName, String rowNumber) {
        newHeaderInfo = commonGrid.gridEntry(rowNumber, columnName).getText();
        Assert.assertTrue(headerInfo.equals(newHeaderInfo), " header info is " + headerInfo + " newHeaderInfo is " + newHeaderInfo);
    }
}


