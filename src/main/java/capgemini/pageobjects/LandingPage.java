package capgemini.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import capgemini.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
		// WebElement userEmails = driver.findElement(By.id("userEmail"));
		
		// PageFactory
		@FindBy(id = "userEmail")
		WebElement userEmail;
		
		@FindBy(id = "userPassword")
		WebElement userPassword;
		
		@FindBy(id = "login")
		WebElement submit;
		
		@FindBy(css = "[class*='flyInOut']")
		WebElement errorMessage;
		
		public ProductCatalogue loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			submit.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);;
			return productCatalogue;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client/");
		}
}
              