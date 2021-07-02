package ui.view;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;



public class TestZoek {
    private static final String url = "http://cyclone3.uclllabs.be:8081/Jaan-Lavaerts-v4/";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url + "Servlet?command=zoek");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Foute_zoekopdracht_toont_niet_gevonden_page() {
        WebElement naam = driver.findElement(By.id("naam"));
        naam.clear();
        naam.sendKeys("jgfsnjgkndsjnds");

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Niet gevonden", driver.getTitle());
    }

    @Test
    public void test_Juiste_zoekopdracht_toont_gevonden_page() {
        WebElement naam = driver.findElement(By.id("naam"));
        naam.clear();
        naam.sendKeys("LOST");

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Gevonden!", driver.getTitle());
    }
}