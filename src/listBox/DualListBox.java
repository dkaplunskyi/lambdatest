package listBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class DualListBox {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-dual-list-box-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testBootstrapList();

        driver.quit();
    }

    public static void testBootstrapList() throws InterruptedException {
        List<WebElement> leftListItems = driver.findElements(By.xpath("//*[@id=\"__next\"]/section[4]/div/div/div[2]/div/div/div[1]/div/ul/li"));
        List<WebElement> rightListItems = driver.findElements(By.xpath("//*[@id=\"__next\"]/section[4]/div/div/div[2]/div/div/div[3]/div/ul/li"));

        WebElement moveToLeft = driver.findElement(By.xpath("//div[contains(@class, 'list-arrows')]/button[1]"));
        WebElement moveToRight = driver.findElement(By.xpath("//div[contains(@class, 'list-arrows')]/button[2]"));

        for (WebElement i : leftListItems) {
            i.click();
        }

        moveToRight.click();
        Thread.sleep(1000);

        for (WebElement i : rightListItems) {
            i.click();
        }
        moveToLeft.click();
        Thread.sleep(1000);

    }
}
