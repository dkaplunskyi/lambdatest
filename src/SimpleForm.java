import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SimpleForm {

    public static void testSingleInputForm(){
        WebDriver driver = Main.driver;
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        String message = "Hello from Cyram";

        WebElement input = driver.findElement(By.cssSelector("input[id = 'user-message']"));
        input.sendKeys(message);

        WebElement button = driver.findElement(By.cssSelector("button[id = 'showInput']"));
        button.click();

        String messageDisplay = driver.findElement(By.cssSelector("p[id = 'message']")).getText();
        Assert.assertTrue(message.equals(messageDisplay));
    }

    public static void testTwoInputForm(){
        WebDriver driver = Main.driver;
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");

        WebElement input1 = driver.findElement(By.cssSelector("input[id = 'sum1']"));
        WebElement input2 = driver.findElement(By.cssSelector("input[id = 'sum2']"));
        input1.sendKeys("500");
        input2.sendKeys("500");

        WebElement button = driver.findElement(By.cssSelector("input[id = 'sum2'] + button"));
        button.click();

        String result = driver.findElement(By.cssSelector("p[id = 'addmessage']")).getText();
        Assert.assertTrue(result.equals("1000"));
    }

}
