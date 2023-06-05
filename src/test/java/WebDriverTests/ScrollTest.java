package WebDriverTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScrollTest {
    private final String BASE_URL = "http://localhost/moveme.php";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws IOException {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        WebElement donald = driver.findElement(By.id("donald"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(donald).moveByOffset(3000, 0).release().build().perform();

        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenShot, new File("C://Users//Adamk//Pictures//Screenshots//TestReport//screenshot.png"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
