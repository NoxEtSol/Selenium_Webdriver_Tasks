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

public class ConchitaTest {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/zenaalebomuz.php";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void okTest() {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//input[@value='wurst']")).click();
        driver.findElement(By.xpath("//h1[@class='description text-center']")).getText();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='wurst']")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value='conchita']")).isSelected());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public static class CalculatorTest extends MainTest {

        @Before
        public void openBaseUrl() {
            driver.get(getBASE_URL() + "kalkulacka.php");
        }

        private void checkSum(String number, String number1) {
            driver.findElement(By.id("firstInput")).clear();
            driver.findElement(By.id("firstInput")).sendKeys(number);
            driver.findElement(By.id("secondInput")).clear();
            driver.findElement(By.id("secondInput")).sendKeys(number);
            driver.findElement(By.id("count")).click();
            Assert.assertEquals(number1, driver.findElement(By.id("result")).getText());
        }

        @Test
        public void testSum() {
            checkSum("5", "10");

            checkSum("10", "20");

            checkSum("20", "40");
        }

        private void checkDeduct(String number) {
            driver.findElement(By.id("firstInput")).sendKeys(number);
            driver.findElement(By.id("secondInput")).sendKeys(number);
            driver.findElement(By.id("deduct")).click();
            Assert.assertEquals("0", driver.findElement(By.id("result")).getText());
        }

        @Test
        public void testDeduct() {
            checkDeduct("5");

            checkDeduct("10");

            checkDeduct("20");

            checkDeduct("30");
        }

        @Test
        public void testReset() {
            enterInputs("5", "6");
            driver.findElement(By.id("count")).click();
            driver.findElement(By.id("reset")).click();

            Assert.assertTrue(driver.findElement(By.id("firstInput")).getAttribute("value").isEmpty());
            Assert.assertTrue(driver.findElement(By.id("secondInput")).getAttribute("value").isEmpty());
            Assert.assertTrue(driver.findElement(By.id("result")).getText().isEmpty());
        }

        @Test
        public void testInvalidInputs() {
            enterInputs("j", "p");
            driver.findElement(By.xpath("//button[text()='Spočítaj']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[input[@id='firstInput']]"))
                    .getAttribute("class").contains("has-error"));
            Assert.assertTrue(driver.findElement(By.xpath("//div[input[@id='secondInput']]"))
                    .getAttribute("class").contains("has-error"));
        }

        private void enterInputs(String firstInput, String secondInput) {
            driver.findElement(By.id("firstInput")).clear();
            driver.findElement(By.id("firstInput")).sendKeys(firstInput);
            driver.findElement(By.id("secondInput")).clear();
            driver.findElement(By.id("secondInput")).sendKeys(secondInput);
        }
    }

    public static class MainTest {
        public WebDriver driver;
        private String BASE_URL = "http://localhost/";

        public String getBASE_URL() {
            return BASE_URL;
        }

        public void setBASE_URL(String BASE_URL) {
            this.BASE_URL = BASE_URL;
        }

        @Before
        public void setUp(){
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }

        @After
        public void tearDown(){
            driver.quit();
        }
    }

    public static class RegistrationTest {
        private final String BASE_URL = "http://localhost/registracia.php";
        private WebDriver driver;

        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }

        @Test
        public void test() {
            driver.get(BASE_URL);
            driver.findElement(By.id("checkbox")).click();
            driver.findElement(By.tagName("button")).click();

            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']//strong[1]")).isDisplayed());
        }

        @Test
        public void testMissingPasswords() {
            driver.get(BASE_URL);
            driver.findElement(By.id("checkbox")).click();
            driver.findElement(By.name("email")).sendKeys("hello@hello.sk");
            driver.findElement(By.name("name")).sendKeys("hello");
            driver.findElement(By.name("surname")).sendKeys("hellovec");
            driver.findElement(By.tagName("button")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']//strong[1]")).isDisplayed());
        }

        @Test
        public void testMismatchPasswords() {
            driver.get(BASE_URL);
            driver.findElement(By.id("checkbox")).click();
            driver.findElement(By.name("email")).sendKeys("hello@hello.sk");
            driver.findElement(By.name("name")).sendKeys("hello");
            driver.findElement(By.name("surname")).sendKeys("hellovec");
            driver.findElement(By.name("password")).sendKeys("hellovec");
            driver.findElement(By.name("password-repeat")).sendKeys("helloveccccc");
            driver.findElement(By.tagName("button")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']//strong[1]")).isDisplayed());
        }

        @Test
        public void testSuccessfulRegistration() {
            driver.get(BASE_URL);
            driver.findElement(By.id("checkbox")).click();
            driver.findElement(By.name("email")).sendKeys("hello@hello.sk");
            driver.findElement(By.name("name")).sendKeys("hello");
            driver.findElement(By.name("surname")).sendKeys("hellovec");
            driver.findElement(By.name("password")).sendKeys("hellovec");
            driver.findElement(By.name("password-repeat")).sendKeys("hellovec");
            driver.findElement(By.tagName("button")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success']//strong[1]")).isDisplayed());
        }

        @Test
        public void testInputErrorBorder(){
            driver.get(BASE_URL);
            driver.findElement(By.tagName("button")).click();
            List<WebElement> formDivs = driver.findElements(By.xpath("//div[input]"));
            for (WebElement formDiv : formDivs) {
                Assert.assertTrue(formDiv.getAttribute("class").contains("has-error"));
            }
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='checkbox form-group ']//label[1]")).getAttribute("class").contains("has-error"));
        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }
}
