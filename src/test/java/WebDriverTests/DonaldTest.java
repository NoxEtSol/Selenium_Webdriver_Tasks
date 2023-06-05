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

public class DonaldTest {
    private final String BASE_URL = "http://localhost/moveme.php";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        WebElement donald = driver.findElement(By.id("donald"));
        WebElement finish = driver.findElement(By.id("tree"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(donald, finish).build().perform();

        System.out.println(driver.findElement(By.xpath("//h2[text()='Hooo hooooo !!!!']")).getText());

        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Hooo hooooo !!!!']")).isDisplayed());
        Assert.assertEquals("HOOO HOOOOO !!!!", driver.findElement(By.xpath("//h2[text()='Hooo hooooo !!!!']")).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
