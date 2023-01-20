package inputForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;

public class SimpleForm {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testSingleInputForm();
        testTwoInputForm();

        driver.quit();
    }

    public static void testSingleInputForm() {
        String message = "Hello from Cyram";

        WebElement input = driver.findElement(By.cssSelector("input[id = 'user-message']"));
        input.sendKeys(message);

        WebElement button = driver.findElement(By.cssSelector("button[id = 'showInput']"));
        button.click();

        String messageDisplay = driver.findElement(By.cssSelector("p[id = 'message']")).getText();
        assertThat(message.equals(messageDisplay)).isTrue();
    }

    public static void testTwoInputForm() {
        WebElement input1 = driver.findElement(By.cssSelector("input[id = 'sum1']"));
        WebElement input2 = driver.findElement(By.cssSelector("input[id = 'sum2']"));
        input1.sendKeys("500");
        input2.sendKeys("500");

        WebElement button = driver.findElement(By.cssSelector("input[id = 'sum2'] + button"));
        button.click();

        String result = driver.findElement(By.cssSelector("p[id = 'addmessage']")).getText();
        assertThat(result.equals("1000")).isTrue();
    }

}
