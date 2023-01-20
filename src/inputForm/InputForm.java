package inputForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.*;

public class InputForm {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testInputForm();

        driver.quit();
    }

    public static void testInputForm() {
        WebElement nameInput = driver.findElement(By.cssSelector("input[id = 'name']"));
        WebElement emailInput = driver.findElement(By.cssSelector("input[id = 'inputEmail4']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[id = 'inputPassword4']"));
        WebElement companyInput = driver.findElement(By.cssSelector("input[id = 'company']"));
        WebElement websiteInput = driver.findElement(By.cssSelector("input[id = 'websitename']"));
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name = 'country']")));
        WebElement cityInput = driver.findElement(By.cssSelector("input[id = 'inputCity']"));
        WebElement address1Input = driver.findElement(By.cssSelector("input[id = 'inputAddress1']"));
        WebElement address2Input = driver.findElement(By.cssSelector("input[id = 'inputAddress2']"));
        WebElement stateInput = driver.findElement(By.cssSelector("input[id = 'inputState']"));
        WebElement zipInput = driver.findElement(By.cssSelector("input[id = 'inputZip']"));
        WebElement submit = driver.findElement(By.xpath("//button[text() = 'Submit']"));
        WebElement msg = driver.findElement(By.cssSelector("p[class = 'success-msg hidden']"));

        nameInput.sendKeys("Julia");
        emailInput.sendKeys("somewhereinrussia@gmail.com");
        passwordInput.sendKeys("julia777");
        companyInput.sendKeys("pigs&dogs");
        websiteInput.sendKeys("pigs&dogs.com");
        countrySelect.selectByValue("US");
        cityInput.sendKeys("Washington");
        address1Input.sendKeys("Washington Street 123");
        address2Input.sendKeys("apt.#321");
        stateInput.sendKeys("MD");
        zipInput.sendKeys("777");
        submit.click();

        assertThat(msg.getText().contains("Thanks for contacting us")).isTrue();
    }

}
