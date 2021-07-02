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


public class TestUpdate {
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
    public void test_Pas_aan_gaat_naar_voeg_toe_page() {
        WebElement update = driver.findElement(By.linkText("Pas aan"));
        update.click();

        assertEquals("TV-Shows | Voeg toe", driver.getTitle());
    }

    @Test
    public void test_Form_is_shown_again_with_error_message_If_score_and_uitgekeken_fields_are_empty() {
        WebElement update = driver.findElement(By.linkText("Pas aan"));
        update.click();

        WebElement score = driver.findElement(By.id("score"));
        score.clear();
        score.sendKeys("");

        Select uit = new Select(driver.findElement(By.id("uitgekeken")));
        uit.selectByIndex(0);

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Voeg toe", driver.getTitle());

        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementWithText(list, "Score moet tussen 0 en 10 liggen."));
        assertTrue(containsWebElementWithText(list, "Specifieer of de show is uitgekeken of niet."));
    }

    @Test
    public void test_Form_is_shown_again_with_error_message_If_score_is_empty() {
        WebElement update = driver.findElement(By.linkText("Pas aan"));
        update.click();

        WebElement score = driver.findElement(By.id("score"));
        score.clear();
        score.sendKeys("");


        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Voeg toe", driver.getTitle());

        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementWithText(list, "Score moet tussen 0 en 10 liggen."));
    }

    @Test
    public void test_Form_is_shown_again_with_error_message_If_uitgekeken_is_empty() {
        WebElement update = driver.findElement(By.linkText("Pas aan"));
        update.click();

        Select uit = new Select(driver.findElement(By.id("uitgekeken")));
        uit.selectByIndex(0);

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Voeg toe", driver.getTitle());

        ArrayList<WebElement> list = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementWithText(list, "Specifieer of de show is uitgekeken of niet."));
    }

    @Test
    public void test_Overzicht_is_shown_when_form_valid_cases_submitted() {
        WebElement update = driver.findElement(By.linkText("Pas aan"));
        update.click();

        WebElement button = driver.findElement(By.id("submit-button"));
        button.click();

        assertEquals("TV-Shows | Overzicht", driver.getTitle());

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