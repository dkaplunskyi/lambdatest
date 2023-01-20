package inputForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class RadioButtons {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testRadioButtons();
        testGroupRadioButton();

        driver.quit();
    }

    public static void testRadioButtons() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[name = 'optradio']"));
        WebElement submit = driver.findElement(By.cssSelector("#buttoncheck"));
        WebElement msg = driver.findElement(By.cssSelector("button[id = 'buttoncheck'] + p"));

        for (WebElement i : radioButtons) {
            i.click();
            String value = i.getAttribute("value");
            submit.click();
            String msgText = msg.getText();
            assertThat(msgText.contains(value)).isTrue();
        }
    }

    public static void testGroupRadioButton() {
        List<WebElement> genderRadioButtons = driver.findElements(By.cssSelector("input[name = 'gender']"));
        List<WebElement> ageRadioButtons = driver.findElements(By.cssSelector("input[name = 'ageGroup']"));

        WebElement genderMsg = driver.findElement(By.cssSelector("span[class = 'genderbutton']"));
        WebElement ageMsg = driver.findElement(By.cssSelector("span[class = 'groupradiobutton']"));

        WebElement submit = driver.findElement(By.xpath("//button[text() = 'Get values']"));

        for (WebElement i : genderRadioButtons) {
            i.click();
            String value = i.getAttribute("value");
            submit.click();
            String msgText = genderMsg.getText();
            assertThat(msgText.contains(value)).isTrue();
        }

        for (WebElement i : ageRadioButtons) {
            i.click();
            String value = i.getAttribute("value");
            submit.click();
            String msgText = ageMsg.getText();
            assertThat(msgText.contains(value)).isTrue();
        }

    }

}
