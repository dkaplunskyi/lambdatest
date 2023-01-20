package table;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class TableWithPagination {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/table-pagination-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testTablePagination();

        driver.quit();
    }

    public static void testTablePagination() {
        Select selectRows = new Select(driver.findElement(By.cssSelector("#maxRows")));
        int displayedRows = 0;
        List<WebElement> rows = driver.findElements(By.cssSelector("#table-id tbody tr"));

        displayedRows = rowsLooping(rows);
        assertThat(selectRows.getFirstSelectedOption().getAttribute("value").equals(Integer.toString(displayedRows))).isTrue();
        System.out.println(selectRows.getFirstSelectedOption().getAttribute("value"));
        System.out.println(displayedRows);

        selectRows.selectByValue("10");
        displayedRows = rowsLooping(rows);
        assertThat(selectRows.getFirstSelectedOption().getAttribute("value").equals(Integer.toString(displayedRows))).isTrue();
        System.out.println(selectRows.getFirstSelectedOption().getAttribute("value"));
        System.out.println(displayedRows);

        selectRows.selectByValue("15");
        displayedRows = rowsLooping(rows);
        assertThat(selectRows.getFirstSelectedOption().getAttribute("value").equals(Integer.toString(displayedRows))).isTrue();
        System.out.println(selectRows.getFirstSelectedOption().getAttribute("value"));
        System.out.println(displayedRows);

        selectRows.selectByValue("20");
        displayedRows = rowsLooping(rows);
        assertThat(selectRows.getFirstSelectedOption().getAttribute("value").equals(Integer.toString(displayedRows))).isTrue();
        System.out.println(selectRows.getFirstSelectedOption().getAttribute("value"));
        System.out.println(displayedRows);

        selectRows.selectByValue("30");
        displayedRows = rowsLooping(rows);
        assertThat(selectRows.getFirstSelectedOption().getAttribute("value").equals(Integer.toString(displayedRows))).isTrue();
        System.out.println(selectRows.getFirstSelectedOption().getAttribute("value"));
        System.out.println(displayedRows);
    }

    static int rowsLooping(List<WebElement> list) {
        int displayedItems = 0;
        for (WebElement i : list) {
            if (i.isDisplayed()) {
                displayedItems++;
            }
        }
        return displayedItems;
    }
}
