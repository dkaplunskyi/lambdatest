import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

//        SimpleForm.testSingleInputForm();
//        SimpleForm.testTwoInputForm();

//        Checkbox.testSingleCheckbox();
//        Checkbox.testMultipleCheckbox();

        RadioButtons.testRadioButtons();



        driver.quit();
    }
}