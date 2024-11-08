import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Before;

import static org.junit.Assert.*;

public class TrojmiastoTest extends BaseTest {

    @Before
    public void setUp() {
        setUp("chrome");
    }

    @Test
    public void testPageTitle() {
        driver.get("https://www.trojmiasto.pl/");
        String pageTitle = driver.getTitle();
        assertEquals("Trojmiasto.pl - wiadomości i informacje z Trójmiasta.", pageTitle);
    }

    @Test
    public void testFeaturedArticleTitle() {
        driver.get("https://www.trojmiasto.pl/");
        WebElement element = driver.findElement(By.className("headerFeatured__title"));
        assertEquals("POLECAMY", element.getText());
    }

    @Test
    public void testRaportVisibility() {
        driver.get("https://www.trojmiasto.pl/raport/");
        WebElement newsSection = driver.findElement(By.className("raportItem__header"));
        assertNotNull(newsSection);
    }

    @Test
    public void testLoginButtonExists() {
        driver.get("https://www.trojmiasto.pl/");
        WebElement loginButton = driver.findElement(By.className("footerLinks__btn"));
        assertNotNull("Login button should be present", loginButton);
    }

    @Test
    public void testFooterText() {
        driver.get("https://www.trojmiasto.pl/");
        WebElement footer = driver.findElement(By.id("footer"));
        assertTrue("Stopka powinna zawierać tekst 'Wszystkie prawa zastrzeżone'",
                footer.getText().contains("Polityka prywatności"));
    }

    @Test
    public void testSportMenuLink() {
        driver.get("https://www.trojmiasto.pl/");
        WebElement sportLink = driver.findElement(By.linkText("Sport"));
        sportLink.click();
        assertEquals("https://www.trojmiasto.pl/sport/", driver.getCurrentUrl());
    }

    @Test
    public void testLogoVisibility() {
        driver.get("https://www.trojmiasto.pl/");
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"header-logo\"]/img"));
        assertTrue("Logo strony powinno być widoczne", logo.isDisplayed());
    }

    @Test
    public void testSearchFunction() {
        driver.get("https://www.trojmiasto.pl/wiadomosci/");
        WebElement searchInput = driver.findElement(By.name("string_news"));
        searchInput.sendKeys("Lechia Gdańsk");
        searchInput.submit();
        WebElement searchResults = driver.findElement(By.className("newsList"));
        assertTrue("Wyniki wyszukiwania powinny być widoczne", searchResults.isDisplayed());
    }

    @Test
    public void testNewsHasArticles() {
        driver.get("https://www.trojmiasto.pl/wiadomosci/");
        WebElement popularSection = driver.findElement(By.className("newsList"));
        WebElement article = popularSection.findElement(By.className("newsList__article"));
        assertNotNull("Sekcja 'Popularne' powinna zawierać artykuły", article);
    }
}
