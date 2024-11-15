package zjazd2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Before;
import static org.junit.Assert.*;
public class OtomotoTest extends BaseTest {
    @Before
    public void setUp() {
        setUp("chrome");
    }
    @Test
    public void testPageTitle() {
        driver.get("https://www.otomoto.pl/");
        String pageTitle = driver.getTitle();
        assertTrue("Page title should contain 'OTOMOTO'", pageTitle.contains("OTOMOTO"));
    }
    @Test
    public void testSearchFormPresence() {
        driver.get("https://www.otomoto.pl/");
        WebElement searchForm = driver.findElement(By.className("ooa-1mw1sv4"));
        assertNotNull("Search form should be present", searchForm);
        assertTrue("Search form should be visible", searchForm.isDisplayed());
    }
    @Test
    public void testLoginButtonExists() {
        driver.get("https://www.otomoto.pl/");
        WebElement loginButton = driver.findElement(By.className("ooa-ucvp6h"));
        assertNotNull("Login button should be present", loginButton);
    }
    @Test
    public void testMainBannerVisibility() {
        driver.get("https://www.otomoto.pl/");
        WebElement mainBanner = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div/main/div[1]/div[1]/button"));
        assertTrue("Main banner should be visible", mainBanner.isDisplayed());
    }
    @Test
    public void testFooterContainsPrivacyPolicy() {
        driver.get("https://www.otomoto.pl/");
        WebElement footer = driver.findElement(By.tagName("footer"));
        assertTrue("Footer should contain 'Polityka prywatności'", footer.getText().contains("Polityka prywatności"));
    }
    @Test
    public void testAdvancedSearch() {
        driver.get("https://www.otomoto.pl/");
        WebElement addListingButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div/main/div[2]/div[3]/div[10]/ol/li[3]/a"));
        addListingButton.click();
        assertEquals("https://www.otomoto.pl/osobowe/gdansk?search%5Bdist%5D=50", driver.getCurrentUrl());
    }
    @Test
    public void testOffersSectionPresence() {
        driver.get("https://www.otomoto.pl/");
        WebElement popularMakesSection = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div/main/div[2]/div[2]"));
        assertTrue("Popular makes section should be present", popularMakesSection.isDisplayed());
    }
    @Test
    public void testPartsPresence() {
        driver.get("https://www.otomoto.pl/czesci");
        WebElement main = driver.findElement(By.tagName("main"));
        assertNotNull("Main content should be present", main);
    }
}
