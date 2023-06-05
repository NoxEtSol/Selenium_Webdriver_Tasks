package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TableTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/tabulka.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]")).getText();
        System.out.println(driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]")).getText());

        int rows = Integer.parseInt(driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]")).getText());
        for (int i = 1; i < rows + 1; i++) {
            Assert.assertFalse(driver.findElement(By.xpath("//table/tbody/tr[ " + i + "]")).getText().isEmpty());
        }
    }

    @Test
    public void elementsTest(){
        driver.get(BASE_URL);
        List< WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        for (WebElement row : rows) {
            System.out.println(row.findElement(By.xpath("./td[3]")).getText());
            Assert.assertFalse(row.findElement(By.xpath("./td[3]")).getText().isEmpty());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
