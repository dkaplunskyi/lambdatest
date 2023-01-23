package alertsAndModals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;


public class BootstrapModals {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testBootstrapModal();

        driver.quit();
    }

    public static void testBootstrapModal(){
        WebElement launchSingleModalBtn = driver.findElement(By.cssSelector("h2 + div > button"));
        WebElement modalWindow1 = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div"));
        WebElement closeModal1 = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[3]/button[1]"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        launchSingleModalBtn.click();
        wait.until(ExpectedConditions.visibilityOf(modalWindow1));
        assertThat(modalWindow1.isDisplayed()).isTrue();
        closeModal1.click();
        wait.until(ExpectedConditions.invisibilityOf(modalWindow1));
        assertThat(modalWindow1.isDisplayed()).isFalse();

        WebElement launchMultipleModalBtn = driver.findElement(By.xpath("//*[@id=\"__next\"]/section[4]/div/div/div[2]/div[2]/button"));
        WebElement modalWindow2 = driver.findElement(By.xpath("//*[@id=\"myMultiModal\"]/div/div"));

        WebElement launchModalBtn = driver.findElement(By.xpath("//*[@id=\"myMultiModal\"]/div/div/div[2]/button"));
        WebElement modalWindow3 = driver.findElement(By.xpath("//*[@id=\"mySecondModal\"]/div/div"));
        WebElement closeModal3 = driver.findElement(By.xpath("//*[@id=\"mySecondModal\"]/div/div/div[3]/button[1]"));
        WebElement closeModal2 = driver.findElement(By.xpath("//*[@id=\"myMultiModal\"]/div/div/div[3]/button[1]"));

        launchMultipleModalBtn.click();
        wait.until(ExpectedConditions.visibilityOf(modalWindow2));
        launchModalBtn.click();
        wait.until(ExpectedConditions.visibilityOf(modalWindow3));
        closeModal3.click();
        wait.until(ExpectedConditions.invisibilityOf(modalWindow3));
        assertThat(modalWindow3.isDisplayed()).isFalse();
        closeModal2.click();
        wait.until(ExpectedConditions.invisibilityOf(modalWindow2));
        assertThat(modalWindow2.isDisplayed()).isFalse();

    }
}
