package seleniumAssignments.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class StoreDemoqaPage {

	WebDriver driver;
		
	private By productCategory=By.xpath("//li[@id='menu-item-33']/a");
	private By iPhones=By.xpath("//li[@id='menu-item-37']/a");
	private By addToCart=By.xpath("//div[@class='default_product_display product_view_40 iphones group']/descendant::input[@name='Buy']");
	private By gotoCheckout=By.xpath("//div[@id='fancy_notification_content']/a[1]");
	private By contnue=By.xpath("//span[text()='Continue']");
	private By country=By.xpath("//*[@id='current_country']");
	private By calculate=By.xpath("//form[@id='change_country']/input[@value='Calculate']");
	
	public StoreDemoqaPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickOnIphonesInProductCategory(){
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(productCategory)).build().perform();
		driver.findElement(iPhones).click();
	}
	public void clickOnAddToCart(){
		driver.findElement(addToCart).click();
	}
	public boolean isElementPresent(){
		List<WebElement> myElements=driver.findElements(addToCart);
		if(myElements.size()==0){
			System.out.println("Element not present");
			return false;
		}		
		else
			return true;
	}
    public void clickOnGoToCheckout(){
    	driver.findElement(gotoCheckout).click();
	}
	public void clickOnContinue() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(contnue).click();
	}
	public void enterCountry(){
		Select select=new Select(driver.findElement(country));
		select.selectByVisibleText("India");
	}
	public void clickOnCalculate(){
		driver.findElement(calculate).click();
	}
	public String printCurrentPageUrl(){
		String url=driver.getCurrentUrl();
		System.out.println("current url is="+url);
		return url;
	}
}
