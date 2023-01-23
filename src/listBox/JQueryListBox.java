package listBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;


public class JQueryListBox {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/jquery-dual-list-box-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testJQueryDualListBox();

        driver.quit();
    }

    public static void testJQueryDualListBox() {
        Select leftSelect = new Select(driver.findElement(By.xpath("//*[@id=\"pickList\"]/div/div[1]/select")));
        Select rightSelect = new Select(driver.findElement(By.xpath("//*[@id=\"pickList\"]/div/div[3]/select")));
        List<WebElement> buttons = driver.findElements(By.xpath("//*[@id=\"pickList\"]/div/div[2]/button"));

        leftSelect.selectByVisibleText("Julia");
        buttons.get(0).click();

        List<WebElement> rightSelectOptions = rightSelect.getOptions();
        ArrayList<String> rightOptionsText = new ArrayList<>();

        for (WebElement option : rightSelectOptions) {
            rightOptionsText.add(option.getText());
        }
        assertThat(rightOptionsText.contains("Julia")).isTrue();


    }
}
