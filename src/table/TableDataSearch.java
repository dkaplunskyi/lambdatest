package table;

import graphql.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TableDataSearch {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/table-search-filter-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testTableSearchBy();
        testTableFilter();

        driver.quit();
    }

    public static void testTableSearchBy() {
        String taskData = "Wireframes";
        String assigneeData = "John Smith";
        String statusData = "in progress";

        WebElement input = driver.findElement(By.cssSelector("#task-table-filter"));
        List<WebElement> resultTaskItems = driver.findElements(By.xpath("//table[@id = 'task-table']//tr/td[2]"));
        List<WebElement> resultAssigneeItems = driver.findElements(By.xpath("//table[@id = 'task-table']//tr/td[3]"));
        List<WebElement> resultStatusItems = driver.findElements(By.xpath("//table[@id = 'task-table']//tr/td[4]"));

        input.clear();
        input.sendKeys(taskData);
        loopingColumn(resultTaskItems, taskData);

        input.clear();
        input.sendKeys(assigneeData);
        loopingColumn(resultAssigneeItems, assigneeData);

        input.clear();
        input.sendKeys(statusData);
        loopingColumn(resultStatusItems, statusData);
    }

    public static void testTableFilter() throws InterruptedException {
        String idData = "3";
        String usernameData = "jQuery library";
        String fullNameData = "Mike Trout";
        String statusData = "in progress";

        WebElement filterBtn = driver.findElement(By.xpath("//button[text() = 'Filter']"));
        WebElement idInput = driver.findElement(By.cssSelector("input[placeholder = '#']"));
        WebElement usernameInput = driver.findElement(By.cssSelector("input[placeholder = 'Username']"));
        WebElement fullNameInput = driver.findElement(By.cssSelector("input[placeholder = 'First Name']"));
        WebElement statusInput = driver.findElement(By.cssSelector("input[placeholder = 'Last Name']"));

        List<WebElement> resultIdItems = driver.findElements(By.xpath("//table[@class = 'table table-hover table-responsive' and not(@id = 'task-table')]//tr/td[1]"));
        List<WebElement> resultAssigneeItems = driver.findElements(By.xpath("//table[@class = 'table table-hover table-responsive' and not(@id = 'task-table')]//tr/td[2]"));
        List<WebElement> resultFullNameItems = driver.findElements(By.xpath("//table[@class = 'table table-hover table-responsive' and not(@id = 'task-table')]//tr/td[3]"));
        List<WebElement> resultStatusItems = driver.findElements(By.xpath("//table[@class = 'table table-hover table-responsive' and not(@id = 'task-table')]//tr/td[4]"));

        filterBtn.click();

        stepsExecutor(idInput, idData);
        loopingColumn(resultIdItems, idData);

        stepsExecutor(usernameInput, usernameData);
        loopingColumn(resultAssigneeItems, usernameData);

        stepsExecutor(fullNameInput, fullNameData);
        loopingColumn(resultFullNameItems, fullNameData);

        stepsExecutor(statusInput, statusData);
        loopingColumn(resultStatusItems, statusData);
    }

    static void stepsExecutor(WebElement input, String data) throws InterruptedException {
        input.clear();
        input.sendKeys(data);
        Thread.sleep(2000);
        input.clear();
    }

    static void loopingColumn(List<WebElement> list, String data) {
        for (WebElement i : list) {
            if (i.isDisplayed()) {
                Assert.assertTrue(i.getText().equals(data));
            }
        }
    }
}
