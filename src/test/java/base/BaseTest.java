
package base;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import constants.CapabilitiesConst;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {
	
	static AppiumDriver driver = null;//This can be androidDriver or iosDriver
	AppiumDriverLocalService service;
	
	@BeforeSuite
	public void startServer() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/Users/yourName/.npm-global/lib/node_modules/appium/index.js"))
				.withIPAddress("127.0.0.1")
				.withArgument(GeneralServerFlag.BASEPATH,"/wd/hub/").usingPort(4723).build();
		 try {
	            service.start();
	            if (service.isRunning()) {
	                System.out.println("Appium server started successfully.");
	            } else {
	                System.err.println("Failed to start Appium server.");
	            }
	        } catch (Exception e) {
	            System.err.println("Error starting Appium server: " + e.getMessage());
	        }
	}
	
	@AfterSuite
	public void stopServer() {
		 if (service != null && service.isRunning()) {
	            service.stop();
	            System.out.println("Appium server stopped successfully.");
	        } else {
	            System.out.println("Appium server is already stopped or was not started.");
	        }
	}

	
	public static AppiumDriver getDriver(String driverName) throws MalformedURLException 
	{

		 String name = driverName.toLowerCase();
	        System.out.println("Initializing driver for: " + name);

	        switch (name) {
	            case "android":
	                URL url = new URL("http://127.0.0.1:4723/wd/hub");
	                System.out.println("Connecting to: " + url);
	                UiAutomator2Options uio = new UiAutomator2Options();
	                uio.setCapability("platformName", "Android");
	                uio.setCapability("deviceName", "ZY22K9S5PG");
	                uio.setCapability("platformVersion", "14");
	                uio.setCapability("automationName", "UiAutomator2");
	                uio.setCapability("app", System.getProperty("user.dir")+"/src/test/resources/apkFiles/General-Store.apk");
	                uio.setCapability("appPackage", CapabilitiesConst.PACKAGE_NAME); 
	                uio.setCapability("appActivity", CapabilitiesConst.ACTIVITY_NAME);
	                driver = new AndroidDriver(url, uio);
	                System.out.println("Android driver connected. App launched successfully.");
	                break;
	                
	            case "iOS":
	                URL url1 = new URL("http://127.0.0.1:4723/wd/hub");
	                System.out.println("Connecting to: " + url1);
	                XCUITestOptions xcui = new XCUITestOptions();
	                xcui.setCapability("platformName", "ios");
	                xcui.setCapability("deviceName", "ZY22K9S5PG");
	                xcui.setCapability("platformVersion", "14");
	                xcui.setCapability("automationName", "XCUITDriver");
	                xcui.setCapability("app", System.getProperty("user.dir")+"/src/test/resources/apkFiles/General-Store.apk");
	                xcui.setCapability("appPackage", CapabilitiesConst.PACKAGE_NAME); 
	                xcui.setCapability("appActivity", CapabilitiesConst.ACTIVITY_NAME);
	                driver = new AndroidDriver(url1, xcui);
	                System.out.println("IOS driver connected.");
	                break;

	            default:
	                System.out.println("Unsupported driver name: " + driverName);
	                break;
	        }

	        return driver;
	    }
	
	}

	



