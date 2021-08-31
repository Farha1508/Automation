package Pages;

import Base.KpiClass;
import Base.NodeApp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CommonPage extends NodeApp {

    public CommonPage(WebDriver driver, JavascriptExecutor js) {
        super(driver, js);
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
}
