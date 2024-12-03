package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class ProductPage extends BasePage{
	
	AndroidDriver driver;

	public ProductPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	public ProductPage(IOSDriver driver)
	{
		super(driver);
	}
	
	public void verifyTitleOfTheProductPage() throws InterruptedException {
		Thread.sleep(2000);
		String actualText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(actualText, "Products", "Product title doesn't match");	
	}
	
	public void selectProduct1() {
		
		WebElement product = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"))"));  
		Assert.assertEquals(product.getText(), "Jordan 6 Rings");
	}
	
	public void selectProduct2() {
		
		WebElement product = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"PG 3\"))"));  
		Assert.assertEquals(product.getText(), "PG 3");
	}
	
	public void verifyTheProductDetails1() {
		WebElement name = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]"));
		WebElement price = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$165.0\"]"));
		WebElement addToCartOption = driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]"));

		Assert.assertEquals(name.getText(), "Jordan 6 Rings");
		Assert.assertEquals(price.getText(), "$165.0");
		Assert.assertEquals(addToCartOption.getText(), "ADD TO CART");

	}
	
	public void verifyTheProductDetails2() {
		

		WebElement name = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"PG 3\"]"));
		WebElement price = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$110.0\"]"));
		WebElement addToCartOption = driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[1]"));

		Assert.assertEquals(name.getText(), "PG 3");
		Assert.assertEquals(price.getText(), "$110.0");
		Assert.assertEquals(addToCartOption.getText(), "ADD TO CART");

	}
	
	public void addProductToTheCart() {
		
		WebElement addToCartOption = driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]"));
		addToCartOption.click();
		WebElement addedToCart = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\" and @text=\"ADDED TO CART\"]"));
		Assert.assertEquals(addedToCart.getText(), "ADDED TO CART");
	}
	
	public AndroidDriver clickTheCartButton() {
		try
		{
			WebElement cartButton = driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]"));
			cartButton.click();		
			return driver;
		}
		catch(Exception e)
		{
			System.out.println("Error while clicking the cart button: " + e.getMessage());
            return null;
		}	
	}
	
	public void waitForProductPageLoad() {
		System.out.println("Waiting for the Product Page to load...");		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")));
        System.out.println("Product Page loaded successfully.");		
	}

}
