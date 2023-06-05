package WebDriverTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyThirdTest {
    WebDriver driver;
    private final String BASE_URL = "http://localhost/registracia.php";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test(){
        driver.get(BASE_URL);
        driver.findElement(By.name("email")).sendKeys("Test@test.com");
        driver.findElement(By.name("name")).sendKeys("Adam");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
