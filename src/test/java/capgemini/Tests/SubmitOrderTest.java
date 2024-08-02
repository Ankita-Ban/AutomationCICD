package capgemini.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import capgemini.TestComponents.BaseTest;
import capgemini.pageobjects.CartPage;
import capgemini.pageobjects.CheckoutPage;
import capgemini.pageobjects.ConfirmationPage;
import capgemini.pageobjects.OrderPage;
import capgemini.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationmessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// ZARA COAT 3
		// To verify ZARA COAT 3 is displaying in orders page
		ProductCatalogue productCatalogue = landingPage.loginApplication("diyas@gmail.com", "@Diya12345");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +"\\src\\test\\java\\capgemini\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	/*
	 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
	 * "diyas@gmail.com"); map.put("password", "@Diya12345"); map.put("productName",
	 * "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "shetty@gmail.com"); map1.put("password", "Iamking@000");
	 * map1.put("productName", "ADIDAS ORIGINAL");
	 */
}