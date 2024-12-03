package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
	
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	public CartPage(IOSDriver driver)
	{
		super(driver);
	}

	public void verifyTheTitleOfTheCartPage() throws InterruptedException {
		
		String actualText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertEquals(actualText, "Cart", "title doesn't match");	
		
	}
	
	public void addedProductDisplayed()
	{
		WebElement cartEle1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Jordan 6 Rings\")"));
		Assert.assertEquals(cartEle1.getText(), "Jordan 6 Rings");
	}
	
	public void verifyPurchaseAmount()
	{
		WebElement TotalPurchaseAmount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertEquals(TotalPurchaseAmount.getText(), "$ 165.0");
	}
	
	public void addedTotalProductDisplayed()
	{
		WebElement cartEle1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Jordan 6 Rings\")"));
		WebElement cartEle2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PG 3\")"));
		Assert.assertEquals(cartEle1.getText(), "Jordan 6 Rings");
		Assert.assertEquals(cartEle2.getText(), "PG 3");
	}
	
	public void verifyTotalPurchaseAmount()
	{
		WebElement TotalPurchaseAmount = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl"));
		Assert.assertEquals(TotalPurchaseAmount.getText(), "$ 275.0");
	}
	
	public void longPress()
	{
		 WebElement element = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/termsButton"));

	     Actions actions = new Actions(driver);

	      actions.moveToElement(element)          // Move to the element
	               .clickAndHold()                 // Start holding the element
	               .pause(Duration.ofSeconds(2))  // Hold for 2 seconds
	               .release()                      // Release the element
	               .perform();    
	}
	public void verifyTitleOfTheAlert() {
		
		WebElement alertTitleElement = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/alertTitle"));
        String alertTitle = alertTitleElement.getText();
        Assert.assertEquals(alertTitle, "Terms Of Conditions", "Alert title is incorrect!");

	}
	public void verifyCheckboxIsUnchecked() {
		
		WebElement checkBoxEle = driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]"));
        boolean isChecked = checkBoxEle.isSelected();
        if (!isChecked) {
            System.out.println("Checkbox is unchecked.");
        } else {
            System.out.println("Checkbox is checked.");
        }	
	}
	public void closeAlert() {
		
		driver.findElement(AppiumBy.id("android:id/button1")).click();	
	}
	
	public void clickOnVisitToWebsiteButton() {
		
		WebElement visitToWebsiteButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed"));	
		visitToWebsiteButton.click();
	}
	
	public void goBackToTheApp() {
		
		driver.navigate().back();
	}
	
	public AndroidDriver navigateToProductPage()
	{
		WebElement goBackButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_back"));
		goBackButton.click();
		return driver;
	}
	public void waitForCartPageLoad() {
		System.out.println("Waiting for the Cart Page to load...");		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")));
        System.out.println("Cart Page loaded successfully.");	
		
	}

}
