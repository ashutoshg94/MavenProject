package seleniumAssignments.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seleniumAssignments.pages.StoreDemoqaPage;
import seleniumAssignments.pages.ToolsQaPage;

public class ToolsQaStoreDemoTest {

	WebDriver driver;
			
	@BeforeMethod
	public void testBeforeMethod(){
	
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	

	@Test(priority=1)
	public void testToolsQa() throws InterruptedException{
		
		driver.get("http://toolsqa.com/iframe-practice-page/");
		ToolsQaPage toolsQaPgobject=new ToolsQaPage(driver);
		toolsQaPgobject.clickOnTab3();
		Assert.assertEquals(toolsQaPgobject.getTab3Content(), "Content 3 Title");
		toolsQaPgobject.clickOnHandlingAlertInsideDemoSites();
		toolsQaPgobject.clickOnSimpleAlert();
		Assert.assertEquals(toolsQaPgobject.getSimpleAlertText(), "A simple Alert");
		toolsQaPgobject.acceptSimpleAlert();
		toolsQaPgobject.clickOnConformPopup();
		toolsQaPgobject.dismissConformPopupAlert();
		toolsQaPgobject.clickOnPromptPopup();
		toolsQaPgobject.acceptPromptPopupAlert();
	}
	
	
	@Test(priority=2)
	public void testStoreDemo() throws InterruptedException{
		
		driver.get("http://store.demoqa.com/");
		StoreDemoqaPage StorePgObject=new StoreDemoqaPage(driver);
		StorePgObject.clickOnIphonesInProductCategory();
		Assert.assertTrue(StorePgObject.isElementPresent());
		StorePgObject.clickOnAddToCart();
		StorePgObject.clickOnGoToCheckout();
		StorePgObject.clickOnContinue();
		StorePgObject.enterCountry();
		StorePgObject.clickOnCalculate();
		String curentUrl=StorePgObject.printCurrentPageUrl();
		Assert.assertEquals(curentUrl, "http://store.demoqa.com/products-page/checkout/");
		
	}
	
	
	
	@AfterMethod
	public void testAfterMethod(){
		driver.close();
		
	}
	
}
