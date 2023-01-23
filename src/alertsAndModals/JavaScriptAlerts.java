package alertsAndModals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;


public class JavaScriptAlerts {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testJSAlerts();

        driver.quit();
    }

    public static void testJSAlerts() throws InterruptedException {
        List<WebElement> clickMeBtns = driver.findElements(By.xpath("//button[text() = 'Click Me']"));
        for (WebElement btn : clickMeBtns) {
            btn.click();
            Thread.sleep(1000);
            driver.switchTo().alert().accept();
        }
    }

}
