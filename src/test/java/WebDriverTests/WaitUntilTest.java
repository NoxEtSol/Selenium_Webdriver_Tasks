package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUntilTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/zjavenie.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(BASE_URL);

        driver.findElement(By.id("showHim")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='brano']")));
                                        //.presenceOfElementLocated

        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='brano']")).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
