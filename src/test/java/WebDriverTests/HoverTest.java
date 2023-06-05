package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HoverTest {
    private final String BASE_URL = "http://localhost/semafor.php";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        String expectedGreen = "rgba(10, 129, 0, 1)";
        String expectedRed = "rgba(205, 58, 63, 1)";
        String expectedOrange = "rgba(191, 111, 7, 1)";

        driver.get(BASE_URL);
        WebElement trafficLight = driver.findElement(By.className("light"));
        String actualRedColor = trafficLight.getCssValue("background-color");
        Assert.assertEquals(expectedRed, actualRedColor);

        Actions actions = new Actions(driver);
        actions.moveToElement(trafficLight).build().perform();

        String actualGreenColor = trafficLight.getCssValue("background-color");
        Assert.assertEquals(expectedGreen, actualGreenColor);

        actions.clickAndHold(trafficLight).build().perform();
        String actualOrange = trafficLight.getCssValue("background-color");
        System.out.println(actualOrange);
        Assert.assertEquals(expectedOrange, actualOrange);
    }

    @After
    public void tearDown() {
        //iba ak je proces testu ukonceny
//        driver.close();
//        driver.quit();
    }
}
