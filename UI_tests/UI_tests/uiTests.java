package UI_tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Test class for the UI element testing for the Trademe Website.

public class uiTests {
 	
	// Create a test driver (in Chrome engine)
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
	// Need to specify Chromedriver path
	@Before
	public void setUp() {
	    System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	}
	
	// function for finding the label containing the string, then returns the ancestor class's inner span text.
	public WebElement getElementTextContent(String newElement) {
		return driver.findElement(By.cssSelector("label.motors-attribute-label"))
						.findElement(By.xpath(String.format("//*[text()='%s']", newElement)))
						.findElement(By.xpath(".."))
						.findElement(By.xpath(".."))
						.findElement(By.cssSelector("div span.motors-attribute-value"));
	}
	
	// function for getting necessary test attributes
	public void testElementDetails() {
		WebElement numPlateAttributeText = getElementTextContent("Number plate");
		WebElement kilometresAttributeText = getElementTextContent("Kilometres");
		WebElement bodyAttributeText = getElementTextContent("Body");
		WebElement seatsAttributeText = getElementTextContent("Seats");
		assertNotEquals(numPlateAttributeText.getText(), "");
		assertNotEquals(kilometresAttributeText.getText(), "");
		assertNotEquals(bodyAttributeText.getText(), "");
		assertNotEquals(seatsAttributeText.getText(), "");
	}
	
	// Add the link to the used car entry to the parameter
	@Test
	public void testBmw() {
	  try {
		  driver.get("https://www.tmsandbox.co.nz/motors/used-cars/bmw/auction-2149252463.htm");
		  testElementDetails();
		  
      } finally {
          driver.quit();
      }
	}
	
	@Test
	public void testMini() {
	  try {
		  driver.get("https://www.tmsandbox.co.nz/motors/used-cars/mini/auction-2149252158.htm");
		  testElementDetails();
		  
      } finally {
          driver.quit();
      }
	}
	
}