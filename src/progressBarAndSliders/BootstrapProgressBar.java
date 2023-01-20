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


public class BootstrapProgressBar {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-download-progress-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        bootstrapProgressBar();

        driver.quit();
    }

    public static void bootstrapProgressBar(){
        WebElement downloadBtn = driver.findElement(By.cssSelector("#dwnBtn"));
        WebElement completeMsg = driver.findElement(By.cssSelector(".progress + p"));

        downloadBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElement(completeMsg, "Dowload completed!"));
        assertThat(completeMsg.getText()).isEqualTo("Dowload completed!");
    }

}
