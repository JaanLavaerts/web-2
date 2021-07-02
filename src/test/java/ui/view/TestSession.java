package ui.view;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestSession {
    private static final String url = "http://cyclone3.uclllabs.be:8081/Jaan-Lavaerts-v4/";
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Z:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url + "Servlet?command=logboek");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Overzicht_aangeduid_als_current() {
        driver.get(url + "Servlet?command=overzicht");
        WebElement link = driver.findElement(By.id("current"));
        assertEquals("OVERZICHT", link.getText());
    }


    @Test
    public void test_Opgezochte_product_wordt_toegevoegd_aan_logboek() {
        driver.get(url + "Servlet?command=zoek");
        driver.findElement(By.id("naam")).sendKeys("LOST");
        driver.findElement(By.id("submit-button")).click();
        driver.get(url + "Servlet?command=logboek");
        assertEquals("LOST", driver.findElement(By.tagName("td")).getText() );
    }

    @Test
    public void test_Andere_browser_heeft_ander_logboek() {
        driver.get(url + "Servlet?command=zoek");
        driver.findElement(By.id("naam")).sendKeys("LOST");
        driver.findElement(By.id("submit-button")).click();
        driver.get(url + "Servlet?command=logboek");
        assertEquals("LOST", driver.findElement(By.tagName("td")).getText() );
        System.setProperty("webdriver.gecko.driver", "Z:\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get(url + "Servlet?command=logboek");
        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementWithText(list, "U heeft nog geen shows gezocht."));
        driver.quit();
    }

    private boolean containsWebElementWithText(ArrayList<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

}

