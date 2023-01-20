package progressBarAndSliders;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class DrugAndDropSliders {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testSliders();

        driver.quit();
    }

    public static void testSliders() {

        List<WebElement> sliders = driver.findElements(By.cssSelector("h4 + div > input"));

        for (WebElement slider : sliders) {

            int initValue = Integer.parseInt(slider.getAttribute("value"));
            int minValue = Integer.parseInt(slider.getAttribute("min"));
            int maxValue = Integer.parseInt(slider.getAttribute("max"));

            for (int i = 0; i < maxValue; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }

            int currentMaxValue = Integer.parseInt(slider.getAttribute("value"));
            assertThat(initValue).isNotEqualTo(currentMaxValue);

            for (int i = maxValue; i >= minValue; i--) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }

            int currentMInValue = Integer.parseInt(slider.getAttribute("value"));
            assertThat(initValue).isNotEqualTo(currentMInValue);
        }

    }
}
