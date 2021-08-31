package Pages;

import Base.KpiClass;
import Base.NodeApp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;

public class WOT extends NodeApp {
    private HashMap<String, String> gridMap = new HashMap<>();

    public WOT(WebDriver driver, JavascriptExecutor js) {

        super(driver, js);
        initializeMaps();
    }

    @FindBy(how = How.ID, using = "companyStartId")
    private WebElement companySelector;
    @FindBy(how = How.XPATH, using = "//button[@data-id=\"serviceStartId\"]")
    private WebElement serviceSelector;


    @Override
    public boolean onCorrectPage() {
        pageLoaded();
        Select company = new Select(companySelector);
        String selectedComp = company.getFirstSelectedOption().getText().trim();
        String selectedServ = serviceSelector.getText().trim();
        return selectedComp.equals("Select Company")
                && selectedServ.equals("Select Service");

    }

    @Override
    public KpiClass addKpi(int number) {
        return null;
    }

    @Override
    public boolean gridTab(String tabName) {
        String tabFix = tabName.toLowerCase().replaceAll("\\s+", "");
        WebElement tab = super.getDriver().findElement(By.id(gridMap.get(tabFix + "tab")));


        try {
            if (!tab.getAttribute("class").contains("active")) {
                while(!tab.getAttribute("class").contains("active")) {
                    try{
                        tab.click();
                    } catch (ElementClickInterceptedException e) {
                        clickErrorHandle(e.toString(), tab);
                    }
                }

            } else {
                System.out.println(tabName + " is already the current tab.");
            }
        } catch (NoSuchElementException e) {
            return false;
        }

        super.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\""+gridMap.get(tabFix)+"\"]/thead//tr/th/div")));

        return true;

    }

    WebElement gridPageNum;
    WebElement totalPages;

    @Override
    public int gridPageNumber(String tabName, String result) {
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();

        if(super.getDriver().findElement(By.id(gridMap.get(tabNameFixed) + "_paginate")).getAttribute("style").equals("display: none;")) {
            return 0;
        }
        try{
            gridPageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
            totalPages = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-total"));
        } catch (NoSuchElementException e) {
            return -1;
        }

        String pageNum = gridPageNum.getAttribute("value");
        if(result.equals("current")) {
            return Integer.parseInt(pageNum);
        } else if(result.equals("total")) {
            return Integer.parseInt(totalPages.getText());
        }

        return -1;
    }

    @Override
    public int gridRandomPage(String tabName) {
        int totalPages = gridPageNumber(tabName, "total");
        String tabNameFixed = tabName.replaceAll("\\s+", ""). toLowerCase();
        WebElement pageNum = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabNameFixed) + "_paginate .pagination-panel-input"));
        System.out.println("Total number of available pages is: " + totalPages);
        if(totalPages == 1){
            return totalPages;
        } else {
            int randomPage = (int) (Math.random() * (totalPages - 1)) + 1;
            pageNum.click();
            pageNum.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
            pageNum.sendKeys(Integer.toString(randomPage));
            pageNum.sendKeys(Keys.ENTER);
            return gridPageNumber(tabName, "current");
        }

    }

    @Override
    public boolean gridNextPage(String tabName) {
        WebElement nextBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_next"));
        if(nextBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            nextBtn.findElement(By.tagName("a")).click();
            return true;
        }
    }

    @Override
    public boolean gridPrevPage(String tabName) {
        WebElement prevBtn = super.getDriver().findElement(By.cssSelector("#" + gridMap.get(tabName.replaceAll("\\s+", ""). toLowerCase()) + "_previous"));
        if(prevBtn.getAttribute("class").contains("disabled")) {
            return false;
        } else {
            prevBtn.findElement(By.tagName("a")).click();
            return true;
        }
    }

    @Override
    public void gridViewSelection(String tabName, String option) {
        System.out.println("Selecting " + option + " from Viewing dropdown");
        WebElement dropDown = super.getDriver().findElement(By.xpath("//*[@id=\""+gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""))+"_length\"]/label/select"));
        Select rows = new Select(dropDown);
        dropDown.click();
        rows.selectByVisibleText(option);
    }

    @Override
    public int gridRecordNumber(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));
        int recordCount;

        String records = super.getDriver().findElement(By.id(currentTab + "_info")).getText();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < records.length(); i++) {
            if(Character.isDigit(records.charAt(i))) {
                stringBuilder.append(records.charAt(i));
            }
        }
        if(stringBuilder.length() == 0) {
            return 0;
        }

        recordCount = Integer.parseInt(stringBuilder.toString());

        return recordCount;
    }

    @Override
    public int gridRowCount(String tabName) {
        String currentTab = gridMap.get(tabName.toLowerCase().replaceAll("\\s+", ""));

        List<WebElement> rows = super.getDriver().findElements(By.xpath("//*[@id=\""+currentTab+"\"]/tbody/tr"));
        return rows.size();

    }

    private void initializeMaps() {
        gridMap.put("openpolicies", "dtopenpolicies");
        gridMap.put("openpoliciestab", "tabbtnopenpolicies");
        gridMap.put("open", "dtprocessing");
        gridMap.put("opentab", "tabbtnprocessing");
        gridMap.put("completed", "dtcompleted");
        gridMap.put("completedtab", "tabbtncompleted");
        gridMap.put("discarded", "dtdiscarded");
        gridMap.put("discardedtab", "tabbtndiscarded");
    }

}
