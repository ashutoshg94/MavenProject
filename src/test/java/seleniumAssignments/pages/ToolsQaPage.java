package seleniumAssignments.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ToolsQaPage {

	WebDriver driver;
	Alert alert;
	
	private By frame2Id=By.xpath("//iframe[@id='IF2']");
	private By tab3=By.xpath("//a[@id='ui-id-3']");
	private By tab3Content=By.xpath("//div[@id='tabs-3']/b");
	private By demoSites=By.xpath("//ul[@id='primary-menu']//span[text()='DEMO SITES']");
	private By HandlingAlertsSelenium=By.xpath("//ul[@id='primary-menu']//span[text()='Handling Alerts using Selenium WebDriver']"); 
	private By simpleAlert=By.xpath("//button[text()='Simple Alert']");
	private By confirmPopup=By.xpath("//button[text()='Confirm Pop up']");
	private By promptPopup=By.xpath("//button[text()='Prompt Pop up']");
	
	
	public ToolsQaPage(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void clickOnTab3(){
		driver.switchTo().frame(driver.findElement(frame2Id));
		driver.findElement(tab3).click();
	}
	
	public String getTab3Content(){
		return driver.findElement(tab3Content).getText();
	}
	
	public void clickOnHandlingAlertInsideDemoSites(){
		driver.switchTo().defaultContent();
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(demoSites)).build().perform();
		driver.findElement(HandlingAlertsSelenium).click();
	}
	
	public void clickOnSimpleAlert(){
		driver.findElement(simpleAlert).click();
	}
	public String getSimpleAlertText(){
		alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		return alert.getText();
	}
	public void acceptSimpleAlert() throws InterruptedException{
		Thread.sleep(3000);
		alert.accept();
	}
	public void clickOnConformPopup(){
		driver.findElement(confirmPopup).click();
	}
	public void dismissConformPopupAlert() throws InterruptedException{
		alert=driver.switchTo().alert();
		Thread.sleep(3000);
		alert.dismiss();
	}
	public void clickOnPromptPopup(){
		driver.findElement(promptPopup).click();
	}
	public void acceptPromptPopupAlert() throws InterruptedException{
		alert=driver.switchTo().alert();
		alert.sendKeys("Yes");
		Thread.sleep(3000);
		alert.accept();
	}
	
}
