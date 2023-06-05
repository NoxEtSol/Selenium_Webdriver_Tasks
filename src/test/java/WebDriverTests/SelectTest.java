package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/vybersi.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("Pikachu");
        driver.findElement(By.xpath("//div/h3")).getText();
        Assert.assertTrue(driver.findElement(By.xpath("//div/h3")).getText().contains("Pikachu"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
