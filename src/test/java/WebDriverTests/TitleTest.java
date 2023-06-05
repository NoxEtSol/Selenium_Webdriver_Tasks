package WebDriverTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class TitleTest {
    private final String BASE_URL = "http://localhost/vybersi.php";
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(BASE_URL);

        List<WebElement> pages = driver.findElements(By.xpath("//nav//ul/li/a"));
        List<String> pageUrls = new ArrayList<>();

        for (WebElement page : pages) {
            pageUrls.add(page.getAttribute("href"));
        }

        for (String pageUrl : pageUrls) {
            driver.get(pageUrl);
            String expectedTitle = pageUrl.substring(pageUrl.lastIndexOf("/") + 1, pageUrl.indexOf(".php"));
            Assert.assertTrue(driver.getTitle().contains(expectedTitle.substring(0, 1).toUpperCase() + expectedTitle.substring(1)));
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
