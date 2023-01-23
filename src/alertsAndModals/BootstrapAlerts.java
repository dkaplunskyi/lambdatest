package alertsAndModals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;


public class BootstrapAlerts {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-alert-messages-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testBootstrapAlertMsg();

        driver.quit();
    }

    public static void testBootstrapAlertMsg() throws InterruptedException {
        List<WebElement> buttons = driver.findElements(By.cssSelector("div[class = 'container'] div > button"));
        List<WebElement> msgElements = driver.findElements(By.xpath("//*[@id=\"__next\"]/section[4]/div/div/div[2]/div/div[2]/div"));
        List<WebElement> msgElementsCrossBtn = driver.findElements(By.cssSelector("div > a[href = '#']"));

        WebDriverWait wait = new WebDriverWait(driver, 10);

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            wait.until(ExpectedConditions.visibilityOf(msgElements.get(i)));
            assertThat(msgElements.get(i).isDisplayed()).isTrue();

            if (buttons.get(i).getText().contains("Autoclosable") ) {
                wait.until(ExpectedConditions.invisibilityOf(msgElements.get(i)));
                try {
                    assertThat(msgElements.get(i).isDisplayed()).isFalse();
                }catch (Exception e){
                    System.out.println("Msg element disappeared");
                }
            }
        }

        for (WebElement webElement : msgElementsCrossBtn) {
            webElement.click();
        }

    }

}
