package zjazd2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Before;
import static org.junit.Assert.*;
public class DropticaTest extends BaseTest {
    @Before
    public void setUp() {
        setUp("chrome");
    }
    @Test
    public void testPageTitle() {
        driver.get("https://www.droptica.pl/");
        String pageTitle = driver.getTitle();
        assertEquals("Droptica - solidne rozwiązania Open Source dla ambitnych firm", pageTitle);
    }
    @Test
    public void testNavigationMenuVisibility() {
        driver.get("https://www.droptica.pl/");
        WebElement menu = driver.findElement(By.className("header"));
        assertTrue("Navigation menu should be visible", menu.isDisplayed());
    }
    @Test
    public void testContactButtonExists() {
        driver.get("https://www.droptica.pl/");
        WebElement contactButton = driver.findElement(By.linkText("Kontakt"));
        assertNotNull("Contact button should be present", contactButton);
    }
    @Test
    public void testLogoVisibility() {
        driver.get("https://www.droptica.pl/");
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"navbar-main\"]/div/div/div/a/img"));
        assertTrue("Logo should be visible", logo.isDisplayed());
    }
    @Test
    public void testFooterLinks() {
        driver.get("https://www.droptica.pl/");
        WebElement footer = driver.findElement(By.tagName("footer"));
        assertTrue("Footer should contain 'Sitemap'", footer.getText().contains("Sitemap"));
    }
    @Test
    public void testBlogSectionHasArticles() {
        driver.get("https://www.droptica.pl/blog/");
        WebElement article = driver.findElement(By.tagName("article"));
        assertNotNull("Blog section should contain articles", article);
    }
    @Test
    public void testContactFormExists() {
        driver.get("https://www.droptica.pl/kontakt/");
        WebElement contactForm = driver.findElement(By.className("hbspt-form"));
        assertNotNull("Contact form should be present", contactForm);
    }
    @Test
    public void testNewUserPopup() {
        driver.get("https://www.droptica.pl/");
        WebElement popup = driver.findElement(By.className("d-gtm-scripts-modal"));
        assertNotNull("Popup should be present", popup);
    }
}