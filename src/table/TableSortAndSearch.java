package table;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class TableSortAndSearch {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/table-sort-search-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        testTableSort();
        testSearch();

        driver.quit();
    }

    public static void testTableSort() throws InterruptedException {
        int switcherSelectorCounter = 1;
        List<WebElement> columnTypeSelectors = driver.findElements(By.cssSelector("tr > th"));

        for (WebElement column : columnTypeSelectors) {
            if (!column.getAttribute("class").equals("sorting_asc")) {
                column.click();

                List<WebElement> columnData = driver.findElements(By.cssSelector(String.format("td:nth-of-type(%d)", switcherSelectorCounter)));
                ArrayList<String> dataText = new ArrayList<>();

                for (WebElement data : columnData) {
                    dataText.add(data.getText());
                }

                Collections.sort(dataText);

                for (int j = 0; j < dataText.size(); j++) {
                    boolean equals = dataText.get(j).equals(columnData.get(j).getText());
                    assertThat(equals).isTrue();
                }
            }
            switcherSelectorCounter++;
            Thread.sleep(1000);
        }
    }

    public static void testSearch(){
        String searchingData = "London";
        WebElement searchInput = driver.findElement(By.cssSelector("input[type = 'search']"));
        searchInput.sendKeys(searchingData);

        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

        for (WebElement i : rows) {
            assertThat(i.getText().contains(searchingData)).isTrue();
        }

        WebElement msg = driver.findElement(By.cssSelector("#example_info"));
        assertThat(msg.getText().contains(Integer.toString(rows.size()))).isTrue();
    }
}
