package Pages;

import Base.BaseUtil;
import Base.KpiClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CommonKPI {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public CommonKPI(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickKpi(String kpi) {
        WebElement kpiLink = driver.findElement(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]/descendant::text()[normalize-space()='" + kpi + "']/ancestor::a"));
        js.executeScript("arguments[0].click()", kpiLink);
        BaseUtil.pageLoaded();
    }

    public ArrayList<KpiClass> getKpi() {
        ArrayList<KpiClass> kpiList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]")));
        BaseUtil.pageLoaded();
        List<WebElement> kpi = driver.findElements(By.xpath("//a[contains(translate(@id, 'KPI', 'kpi'), 'kpi')]"));
        KpiClass listEntry;
        String numExtraction = "";
        StringBuilder kpiValue;
        String title;
        int intValue;
        float floatValue;

        for (WebElement element : kpi) {
            title = element.findElement(By.className("desc")).getText().replaceAll("\\n", " ").trim();
            int incrementer = 0;
            List<WebElement> values;
            List<WebElement> v1 = element.findElements(By.xpath(".//descendant::div[@class='number']/div[@class='desc']"));
            List<WebElement> v2 = element.findElements(By.xpath(".//descendant::div[@class='number']/span[text()]"));
            List<WebElement> v3 = element.findElements(By.xpath(".//descendant::div[@class='number']/div/span[text()]"));
            if (v1.size() > 0) {
                values = v1;
                title = "";
            } else if (v2.size() > 0) {
                values = v2;
            } else {
                values = v3;
            }

            for (WebElement ele2 : values) {
                StringBuilder vName;
                String cTitle = "";
                List<WebElement> small = ele2.findElements(By.xpath(".//preceding-sibling::small"));
                if (!title.equals("")) {
                    cTitle = title;
                }

                if (small.size() > 1) {
                    numExtraction = ele2.getText();
                    if (cTitle.equals("")) {
                        cTitle = small.get(incrementer).getText().trim();
                    } else {
                        cTitle += " " + small.get(incrementer).getText().trim();
                    }
                    incrementer++;
                } else if (small.size() == 1) {
                    numExtraction = ele2.getText();
                    if (cTitle.equals("")) {
                        cTitle = small.get(incrementer).getText().trim();
                    } else {
                        cTitle += " " + small.get(0).getText().replaceAll("[^\\w\\s\\>\\/]", "").trim();
                    }
                    incrementer++;
                } else {
                    numExtraction = ele2.getText().replaceAll("\\n", "");

                }

                if (numExtraction.matches(".*\\d\\.\\d.*")) {
                    kpiValue = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i)) || numExtraction.charAt(i) == '.') {
                            kpiValue.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i)) || numExtraction.charAt(i) == ' ') {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        if (title.equals("")) {
                            cTitle = vName.toString().trim();
                        } else {
                            cTitle += " " + vName.toString().trim();
                        }

                    }
                    floatValue = Float.parseFloat(kpiValue.toString());
                    listEntry = new KpiClass(cTitle, floatValue);
                } else {
                    kpiValue = new StringBuilder();
                    vName = new StringBuilder();
                    for (int i = 0; i < numExtraction.length(); i++) {
                        if (Character.isDigit(numExtraction.charAt(i))) {
                            kpiValue.append(numExtraction.charAt(i));
                        } else if (Character.isLetter(numExtraction.charAt(i)) || numExtraction.charAt(i) == ' ') {
                            vName.append(numExtraction.charAt(i));
                        }
                    }
                    if (vName.length() > 0) {
                        if (title.equals("")) {
                            cTitle = vName.toString().trim();
                        } else {
                            cTitle += " " + vName.toString().trim();
                        }
                    }
                    intValue = Integer.parseInt(kpiValue.toString());
                    listEntry = new KpiClass(cTitle, intValue);
                }
                kpiList.add(listEntry);
            }

        }
        return kpiList;
    }
}
