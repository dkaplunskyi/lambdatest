package inputForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class SelectDropdownList {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testSelectList();
//        testMultiSelectList();

        driver.quit();
    }

    public static void testSelectList() {
        Select select = new Select(driver.findElement(By.cssSelector("select[id = 'select-demo']")));
        select.selectByValue("Saturday");
        String value = select.getFirstSelectedOption().getAttribute("value");

        WebElement msg = driver.findElement(By.cssSelector("p[class = 'selected-value text-size-14']"));
        assertThat(msg.getText().contains(value)).isTrue();
    }

    public static void testMultiSelectList() throws InterruptedException {
        WebElement firstSelectedBtn = driver.findElement(By.cssSelector("button[id = 'printMe']"));
        WebElement getAllSelectedBtn = driver.findElement(By.cssSelector("button[id = 'printAll']"));

        Select select = new Select(driver.findElement(By.cssSelector("select[id = 'multi-select']")));

        if (select.isMultiple()) {
            select.selectByIndex(1);
            select.selectByIndex(2);
            select.selectByIndex(3);
        }
        Thread.sleep(2000);
        firstSelectedBtn.click();
        Thread.sleep(2000);
        getAllSelectedBtn.click();

        WebElement firstSelected = select.getFirstSelectedOption();
        List<WebElement> allSelectedOptions = select.getAllSelectedOptions();

        WebElement firstSelectedMsg = driver.findElement(By.cssSelector("span[class = 'genderbutton']"));
        WebElement optionSelectedMsg = driver.findElement(By.cssSelector("span[class = 'groupradiobutton block break-words']"));

        String firstSelectedMsgText = firstSelectedMsg.getText();
        String optionSelectedMsgText = optionSelectedMsg.getText();

        assertThat(firstSelectedMsgText.contains(firstSelected.getText())).isTrue();

        for (WebElement i : allSelectedOptions) {
            assertThat(optionSelectedMsgText.contains(i.getText())).isTrue();
        }

    }

}
