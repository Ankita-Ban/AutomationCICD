package capgemini.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import capgemini.TestComponents.BaseTest;
import capgemini.TestComponents.Retry;
import capgemini.pageobjects.CartPage;
import capgemini.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("diyas@gmail.com", "@Diya345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		landingPage.getErrorMessage();
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("Ankit@gmail.com", "@Ankit12345");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}

}