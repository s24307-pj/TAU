package zjazd2;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Before;
import static org.junit.Assert.*;
public class PjatkWikiTest extends BaseTest {
    @Before
    public void setUp() {
        setUp("chrome");
    }
    @Test
    public void testHeadingVisibility() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement heading = driver.findElement(By.id("firstHeading"));
        assertEquals("Heading should be visible and correct", "Polsko-Japońska Akademia Technik Komputerowych[edytuj]", heading.getText());
    }
    @Test
    public void testMainContentsExists() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement toc = driver.findElement(By.tagName("main"));
        assertNotNull("Main content should be present", toc);
    }
    @Test
    public void testInfoboxPresence() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement infobox = driver.findElement(By.className("infobox"));
        assertNotNull("Infobox should be present", infobox);
        assertTrue("Infobox should be displayed", infobox.isDisplayed());
    }
    @Test
    public void testLinkToExternalWebsite() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement externalLink = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table/tbody/tr[15]/td/a"));
        assertNotNull("Link to the official website should be present", externalLink);
        assertEquals("Official site link text should match", "Strona internetowa", externalLink.getText());
    }
    @Test
    public void testCategoryPresence() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement categorySection = driver.findElement(By.id("vector-toc"));
        assertNotNull("Category section should be present", categorySection);
        assertTrue("Category section should contain 'Historia'", categorySection.getText().contains("Historia"));
    }
    @Test
    public void testReferenceSectionExists() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement references = driver.findElement(By.className("references"));
        assertNotNull("References section should be present", references);
    }
    @Test
    public void testImagesInPage() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement image = driver.findElement(By.tagName("img"));
        assertNotNull("Page should contain images", image);
    }
    @Test
    public void testFooterLinks() {
        driver.get("https://pl.wikipedia.org/wiki/Polsko-Japo%C5%84ska_Akademia_Technik_Komputerowych");
        WebElement footer = driver.findElement(By.tagName("footer"));
        assertTrue("Footer should contain 'Dla deweloperów'", footer.getText().contains("Dla deweloperów"));
    }
}