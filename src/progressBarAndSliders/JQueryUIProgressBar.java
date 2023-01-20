package progressBarAndSliders;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;


public class JQueryUIProgressBar {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/jquery-download-progress-bar-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testJQueryUIProgressBar();

        driver.quit();
    }

    public static void testJQueryUIProgressBar(){
        WebElement progress = driver.findElement(By.cssSelector(".progress-label"));
        WebElement downloadBtn = driver.findElement(By.cssSelector("#downloadButton"));
        downloadBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(progress, "Complete!"));
        assertThat(progress.getText()).isEqualTo("Complete!");
    }

}
