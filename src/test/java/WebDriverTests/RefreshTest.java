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

public class RefreshTest {
    private final String BASE_URL = "http://localhost/clickmebaby.php";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(BASE_URL);
        for (int i = 0; i < 5; i++) {
            driver.findElement(By.id("clickMe")).click();
        }
        Assert.assertEquals("5", driver.findElement(By.id("clicks")).getText());
        driver.navigate().refresh();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("clicks")));
        Assert.assertEquals("0", driver.findElement(By.id("clicks")).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
