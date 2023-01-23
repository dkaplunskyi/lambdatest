package alertsAndModals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;


public class WindowPopup {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testWindowPopup();

        driver.quit();
    }

    public static void testWindowPopup() throws InterruptedException {
        WebElement twitterBtn = driver.findElement(By.cssSelector("p + a[href *= twitter]"));
        twitterBtn.click();
        String windowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
//        System.out.println(windowHandle);
//        for (String i : windowHandles) {
//            System.out.println(i);
//        }
        for (String window : windowHandles) {
            if (!window.equals(windowHandle)) {
                driver.switchTo().window(window);
            }
        }
        assertThat(windowHandles.size() > 1).isTrue();
    }
}
