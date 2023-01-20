package inputForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;

public class Checkbox {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testSingleCheckbox();
        testMultipleCheckbox();

        driver.quit();
    }

    public static void testSingleCheckbox() {

        WebElement checkbox = driver.findElement(By.cssSelector("#isAgeSelected"));
        checkbox.click();

        String msg = driver.findElement(By.cssSelector("#txtAge")).getText();
        assertThat(msg.contains("Success")).isTrue();
    }

    public static void testMultipleCheckbox() {
        WebElement btn = driver.findElement(By.cssSelector("input[type = 'button']"));
        btn.click();

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("div > input[type = 'checkbox']"));
        for (WebElement i : checkboxes) {
            boolean selected = i.isSelected();
            assertThat(selected).isTrue();
        }
    }

}
