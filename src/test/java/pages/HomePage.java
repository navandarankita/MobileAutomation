package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.time.Duration;

public class HomePage extends BasePage {
	
	AndroidDriver driver;
		
	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;

	}
	public HomePage(IOSDriver driver) {
		super(driver);
	}
	
	public void getTitleOfTheApp() {
		String actualText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText();
	    Assert.assertEquals(actualText, "General Store");

	}
	public void getLabel() {
		String label = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Select the country where you want to shop\"]\n")).getText();
		Assert.assertEquals(label, "Select the country where you want to shop");
	}
	public void getCountryName() {
		String countryName = driver.findElement(AppiumBy.id("android:id/text1")).getText();
		Assert.assertEquals(countryName, "Afghanistan");
	}
	
	public void verifyTextBoxAndLable() {
		
		WebElement textBoxText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
		Assert.assertEquals(textBoxText.getText(), "Enter name here");
		
		String textBoxLabel = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Your Name\"]")).getText();
		Assert.assertEquals(textBoxLabel, "Your Name");
				
        Assert.assertTrue(textBoxText.isEnabled(), "The second field is not enabled.");
	}
	
	public void verifyRadioButtonAndLabel()
	{
		WebElement radioLabel = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Gender\"]"));
		Assert.assertEquals(radioLabel.getText(), "Gender");
		
		WebElement maleRadioButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale"));
	    WebElement femaleRadioButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));

	    // Verify the radio button options are present
	    Assert.assertNotNull(maleRadioButton, "Male radio button not found.");
	    Assert.assertNotNull(femaleRadioButton, "Female radio button not found.");
	    
        String checkableMale = maleRadioButton.getAttribute("checked");
        String checkableFemale = femaleRadioButton.getAttribute("checked");

	    // Verify that "Male" is selected by default
        Assert.assertEquals(checkableMale, "true", "Male radio button is not selected by default.");
        Assert.assertEquals(checkableFemale, "false", "Female radio button is not selected by default.");
	}
	public void buttonIsClickable() {
		
		WebElement letsShopButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
		Assert.assertEquals(letsShopButton.getAttribute("clickable"), "true");	
	}
	public void clickLetsShopButton() {
		
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(AppiumBy.xpath("//android.widget.Toast[1]")).getText();
        Assert.assertEquals(toastMessage, "Please enter your name", "Toast message does not match the expected text.");

	}
	public void selectCountry() throws InterruptedException {
		String valueToSelect = "India";
		WebElement dropdownButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"));
        dropdownButton.click();
        
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("android:id/text1"))); // Wait for the list to appear

        // Step 3: Use UiAutomator2 to scroll through the list to find the value
        
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"India\"))"));        

        // Step 4: Click the option once found
        WebElement option = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + valueToSelect + "']"));
        option.click();
	}
	public void enterName() {
		WebElement name = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
		name.sendKeys("Amy");
		Assert.assertEquals(name.getText(), "Amy");
		
	}
	public void selectGender() {
		
		WebElement femaleRadioButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));
		femaleRadioButton.click();
		String checkableFemale = femaleRadioButton.getAttribute("checked");
        Assert.assertEquals(checkableFemale, "true", "Female radio button is selected.");
	}
	
	public AndroidDriver clickOnButton()
	{
		WebElement letsShopButton = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
		letsShopButton.click();
		return driver;
	}
	
	public void waitForPageToLoad() {
		System.out.println("Waiting for the Home Page to load...");		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")));
        System.out.println("Home Page loaded successfully.");	
	}
}
