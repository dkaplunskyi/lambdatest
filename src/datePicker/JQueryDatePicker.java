package datePicker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class JQueryDatePicker {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/jquery-date-picker-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testDateRangePicker();

        driver.quit();
    }

    public static void testDateRangePicker() throws InterruptedException {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();

        WebElement fromInput = driver.findElement(By.cssSelector("input[id = 'from']"));
        WebElement toInput = driver.findElement(By.cssSelector("input[id = 'to']"));

        fromInput.click();
        WebElement currentDay = driver.findElement(By.xpath(String.format("//td/a[contains(text(), '%d')]", day)));
        currentDay.click();
        Thread.sleep(1000);

        toInput.click();
        WebElement nextDay = driver.findElement(By.xpath(String.format("//td/a[contains(text(), '%d')]", day+1)));
        nextDay.click();
        Thread.sleep(2000);

    }
}
