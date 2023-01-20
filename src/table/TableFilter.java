package table;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;

public class TableFilter {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/table-records-filter-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testTableFilter();

        driver.quit();
    }

    public static void testTableFilter() throws InterruptedException {
        List<WebElement> buttons = driver.findElements(By.cssSelector("button[type = 'button']"));
        List<WebElement> elements = driver.findElements(By.cssSelector("h4 > span"));

        for (int i = 0; i < buttons.size()-1; i++) {
            buttons.get(i).click();
            Thread.sleep(1000);
            String btnText = buttons.get(i).getText();
            String text = elements.get(i).getText();
            assertThat(text.contains(btnText)).isTrue();
        }
    }
}
