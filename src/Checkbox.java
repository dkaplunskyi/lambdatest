import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Checkbox {

    public static void testSingleCheckbox(){
        WebDriver driver = Main.driver;
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        WebElement checkbox = driver.findElement(By.cssSelector("#isAgeSelected"));
        checkbox.click();

        String msg = driver.findElement(By.cssSelector("#txtAge")).getText();
        Assert.assertTrue(msg.contains("Success"));
    }

    public static void testMultipleCheckbox(){
        WebDriver driver = Main.driver;
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");

        WebElement btn = driver.findElement(By.cssSelector("input[type = 'button']"));
        btn.click();

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("div > input[type = 'checkbox']"));
        for (WebElement i : checkboxes) {
            boolean selected = i.isSelected();
            Assert.assertTrue(selected);
        }
    }

}
