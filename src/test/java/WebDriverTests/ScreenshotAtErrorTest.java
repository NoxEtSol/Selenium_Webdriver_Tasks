package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class ScreenshotAtErrorTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/vybersi.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void okTest() {
        driver.get(BASE_URL);
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("Charmander");
        String actualTitle = driver.findElement(By.xpath("//div[contains()@class, 'pokemon']/h3")).getText();
        Assert.assertTrue(actualTitle.contains("Pikachu"));

    }

    @After
    public void tearDown() throws IOException {
        System.out.println("Predsa som tu");
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenShot, new File("C://Users//Adamk//Pictures//Screenshots//TestReport//screenshot.png"));
        //driver.getPageSource();           zdrojovy kod
    }

}
