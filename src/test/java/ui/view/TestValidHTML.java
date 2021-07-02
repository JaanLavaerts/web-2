package ui.view;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertEquals;



public class TestValidHTML {
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
    public void is_Valid_HTML() {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys("http://cyclone3.uclllabs.be:8081/Jaan-Lavaerts-v4/");

        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");

        WebElement button = driver.findElement(By.className("submit"));
        button.click();

        WebElement pass = driver.findElement(By.cssSelector("p.success"));
        assertEquals("Document checking completed. No errors or warnings to show.", pass.getText());

    }
}