package Pages;

import Base.KpiClass;
import Base.NodeApp;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Setup extends NodeApp {

    private HashMap<String, String> gridMap = new HashMap<>();

    public Setup(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
        initializeMaps();
    }


    @Override
    public boolean onCorrectPage() {
        return true;
    }

    @Override
    public KpiClass addKpi(int number) {
        return null;
    }

    @Override
    public boolean gridTab(String tabName) {
        return false;
    }

    @Override
    public int gridPageNumber(String tabName, String result) {
        return 0;
    }

    @Override
    public int gridRandomPage(String tabName) {
        return 0;
    }

    @Override
    public boolean gridNextPage(String tabName) {
        return false;
    }

    @Override
    public boolean gridPrevPage(String tabName) {
        return false;
    }

    @Override
    public void gridViewSelection(String tabName, String option) {

    }

    @Override
    public int gridRecordNumber(String tabName) {
        return 0;
    }

    @Override
    public int gridRowCount(String tabName) {
        return 0;
    }

    @Override
    public WebElement gridEntry(String tableRow, String tableColumn) {
        WebElement column = null;
        WebElement row = null;
        String rowPath;
        String colPath;

        List<WebElement> setCol = super.getDriver().findElements(By.xpath("" +
                "//th[text()=\""+ tableColumn +"\"]"));
        for(WebElement colElement : setCol) {
            if(colElement.isDisplayed()){
                column = colElement;
                break;
            }
        }

        assert column != null;
        colPath = generateXPATH(column, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d)");
        assert colPath != null;
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        if(tableRow.toLowerCase().contains("row")) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<tableRow.length(); i++) {
                if(Character.isDigit(tableRow.charAt(i))) {
                    stringBuilder.append(tableRow.charAt(i));
                }

            }
            rowPath = stringBuilder.toString();
        } else {
            List<WebElement> setRow = super.getDriver().findElements(By.xpath("" +
                    "//a[.//text()[normalize-space()=\""+ tableRow +"\"]]"));
            for(WebElement rowElement : setRow) {
                if(rowElement.isDisplayed()) {
                    row = rowElement;
                    break;
                }
            }

            assert row != null;
            rowPath = generateXPATH(row, "");
            Pattern rowP = Pattern.compile("(?<=tr\\[)(\\d)");
            assert rowPath != null;
            Matcher rowM = rowP.matcher(rowPath);
            while (rowM.find()) {
                rowPath = rowM.group();
            }
        }

        List<WebElement> results = super.getDriver().findElements(By.xpath("" +
                "//tbody/tr["+ rowPath +"]/td["+ colPath +"]"));
        for(WebElement resElement : results) {
            if(resElement.isDisplayed()) {
                return resElement;
            }
        }

        return null;
    }

    @Override
    public String gridHeaderEnter(String headerName, String input) {
        String colPath;
        WebElement selectedHeader = super.getDriver().findElement(By.xpath(
                "//th[.//text()[normalize-space()=\""+headerName+"\"]]"));;


        colPath = generateXPATH(selectedHeader, "");
        Pattern colP = Pattern.compile("(?<=th\\[)(\\d)");
        Matcher colM = colP.matcher(colPath);
        while (colM.find()) {
            colPath = colM.group();
        }

        selectedHeader = super.getDriver().findElement(By.xpath("//tbody/tr[2]/td["+colPath+"]/input"));

        if(input == null){
            assert selectedHeader != null;
            if(selectedHeader.getAttribute("value") == null) {
                return selectedHeader.getText();
            }
            return selectedHeader.getAttribute("value");

        }

        assert selectedHeader != null;
        try{
            selectedHeader.click();

        } catch (ElementClickInterceptedException e) {
            clickErrorHandle(e.toString(), selectedHeader);
        }

        selectedHeader.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        selectedHeader.sendKeys(input);
//        selectedHeader.click();
        selectedHeader.sendKeys(Keys.ENTER);
        return null;
    }

    public String gridOpenItem(String tabName, String column) {
        int rows = gridRowCount(tabName);
        return "rows in the grid called from gridOpenItem: " + rows;
    }

    private void initializeMaps() {

    }
}
