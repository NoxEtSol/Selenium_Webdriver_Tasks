package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class NavigationTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        String expectedClass = "active";
        List<String> pages = new ArrayList<>();
        pages.add("vybersi");
        pages.add("registracia");
        pages.add("semafor");
        pages.add("nemenne");

        for (String page : pages) {
            driver.get(BASE_URL + "/" + page + ".php");
            Assert.assertTrue(driver.findElement(By.xpath("//li[a/@href='" + page + ".php']"))
                    .getAttribute("class")
                    .contains(expectedClass));
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
