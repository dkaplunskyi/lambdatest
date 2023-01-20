package datePicker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;

public class DataRange1 {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-date-picker-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testDateRangePicker2();

        driver.quit();
    }

    public static void testDateRangePicker2() throws InterruptedException {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        WebElement startDatePicker = driver.findElement(By.cssSelector("input[placeholder = 'Start date']"));
        WebElement endDatePicker = driver.findElement(By.cssSelector("input[placeholder = 'End date']"));

        startDatePicker.click();
        WebElement currentDay = driver.findElement(By.xpath(String.format("//td[contains(text(), '%d')]", day)));
        currentDay.click();
        Thread.sleep(1000);

        endDatePicker.click();
        WebElement nextDay = driver.findElement(By.xpath(String.format("//td[contains(text(), '%d')]", day + 1)));
        nextDay.click();
        Thread.sleep(2000);
    }
}
