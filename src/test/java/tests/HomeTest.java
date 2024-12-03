package tests;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

@Listeners(utils.MobileListenerUtility.class)

public class HomeTest extends BaseTest{
	
    protected AndroidDriver driver;
	
    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Initialize driver before each test
        driver = (AndroidDriver) getDriver("android");
        if (driver == null) {
            throw new RuntimeException("Driver initialization failed!");
        }
        
        System.out.println("Driver initialized for Android: " + driver);
    }

	@AfterClass
	public void tearDown() {
	    if (driver != null) {
	        try {
	            driver.quit();
	            System.out.println("Driver quit successfully.");
	        } catch (Exception e) {
	            System.err.println("Error while quitting driver: " + e.getMessage());
	        }
	    }
	}
	
	@Test
	public void Launch_App_1()
	{
		System.out.println("DRIVER:" +driver);
		HomePage hp = new HomePage(driver);
		hp.getTitleOfTheApp();
        System.out.println("General Store Application launched successfully!");
	}
    
	@Test(dependsOnMethods = {"Launch_App_1"})
	public void Verify_launch_page_fields_2() 
	{
		HomePage hp = new HomePage(driver);
		hp.waitForPageToLoad();
		hp.getTitleOfTheApp();
		hp.getLabel();
		hp.getCountryName();
		hp.verifyTextBoxAndLable();
		hp.verifyRadioButtonAndLabel();
		hp.buttonIsClickable();
	}
	
	@Test(dependsOnMethods = {"Verify_launch_page_fields_2"})
	public void Verify_launch_page_Negative_3() 
	{
		HomePage hp = new HomePage(driver);
		hp.clickLetsShopButton();
	}
	
	@Test(dependsOnMethods = {"Verify_launch_page_fields_2"})
	public void Verify_launch_page_Positive_4() throws InterruptedException
	{

		HomePage hp = new HomePage(driver);
		hp.selectCountry();
		hp.enterName();
		hp.selectGender();
		hp.clickOnButton();
	}
	
	@Test(dependsOnMethods = {"Verify_launch_page_Positive_4"})
	public void Verify_Products_page_Add_to_cart_one_product_5() throws InterruptedException 
	{
		ProductPage pp = new ProductPage(driver);
		pp.verifyTitleOfTheProductPage();
		pp.selectProduct1();
		pp.verifyTheProductDetails1();
		pp.addProductToTheCart();
	}
	
	@Test(dependsOnMethods = {"Verify_Products_page_Add_to_cart_one_product_5"})
	public void Verify_Cart_page_Product_Price_6() throws InterruptedException
	{
		ProductPage pp = new ProductPage(driver);
		pp.clickTheCartButton();
		
		CartPage cp = new CartPage(driver);
		cp.waitForCartPageLoad();
		cp.verifyTheTitleOfTheCartPage();
		cp.addedProductDisplayed();
		cp.verifyPurchaseAmount();
	}
	
	@Test(dependsOnMethods = {"Verify_Cart_page_Product_Price_6"})
	public void Verify_Products_page_Add_to_cart_two_products_7() throws InterruptedException
	{		 
		
		CartPage cp = new CartPage(driver);
		cp.navigateToProductPage();
		
		ProductPage pp = new ProductPage(driver);
		pp.waitForProductPageLoad();
		pp.verifyTitleOfTheProductPage();		
		pp.selectProduct2();
		pp.verifyTheProductDetails2();
		pp.addProductToTheCart();
		pp.clickTheCartButton();
		
		CartPage cp1 = new CartPage(driver);
		cp1.waitForCartPageLoad();
		cp1.verifyTheTitleOfTheCartPage();
		cp1.addedTotalProductDisplayed();
		cp1.verifyTotalPurchaseAmount();
	}
	
	@Test(dependsOnMethods = {"Verify_Products_page_Add_to_cart_two_products_7"})
	public void Verify_Cart_page_Terms_and_notifications_checkBox_8()
	{
		CartPage cp = new CartPage(driver);
		cp.longPress();
		cp.verifyTitleOfTheAlert();
		cp.closeAlert();
		cp.verifyCheckboxIsUnchecked();    
	}
	
	@Test(dependsOnMethods = {"Verify_Cart_page_Terms_and_notifications_checkBox_8"})
	public void Verify_HybridApp_9()
	{
		CartPage cp = new CartPage(driver);
		cp.clickOnVisitToWebsiteButton();
		cp.goBackToTheApp();
	}

}
