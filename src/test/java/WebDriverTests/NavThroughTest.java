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

public class NavThroughTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/registracia.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(BASE_URL);
        String email = "hello@hello.com";
        driver.findElement(By.name("email")).sendKeys("hello@hello.com");
        driver.findElement(By.xpath("//a[@href='zenaalebomuz.php']")).click();
        driver.navigate().back();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        Assert.assertFalse(driver.findElement(By.name("email")).getAttribute("value").isEmpty());
        Assert.assertEquals(email, driver.findElement(By.name("email")).getAttribute("value"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
