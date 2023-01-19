import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RadioButtons {

    public static void testRadioButtons() {
        WebDriver driver = Main.driver;
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");

        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[name = 'optradio']"));
        WebElement submit = driver.findElement(By.cssSelector("#buttoncheck"));
        WebElement msg = driver.findElement(By.cssSelector("button[id = 'buttoncheck'] + p"));

        for (WebElement i : radioButtons) {
            i.click();
            String value = i.getAttribute("value");
            submit.click();
            String msgText = msg.getText();
            Assert.assertTrue(msgText.contains(value));
        }

    }
}
