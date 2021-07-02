package ui.view;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestVerwijder {
    private static final String url = "http://cyclone3.uclllabs.be:8081/Jaan-Lavaerts-v4/";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url + "Servlet?command=overzicht");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Verwijder_gaat_naar_verwijderconfirm_page() {
        WebElement verwijder = driver.findElement(By.linkText("Verwijder"));
        verwijder.click();

        assertEquals("TV-Shows | Confirm", driver.getTitle());
    }

    @Test
    public void test_Verwijder_confirm_ja_gaat_naar_overzicht_page() {
        WebElement verwijder = driver.findElement(By.linkText("Verwijder"));
        verwijder.click();

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Overzicht", driver.getTitle());
    }

}