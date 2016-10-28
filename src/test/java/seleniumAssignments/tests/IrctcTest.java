package seleniumAssignments.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({seleniumAssignments.tests.TestListener.class})
public class IrctcTest {

	static WebDriver driver;
	String firstWind;
	String secondWind;
	
	private  XSSFSheet ExcelWSheet;
	 private  XSSFWorkbook ExcelWBook;
	 private  XSSFCell Cell;
	
	 @Test(dataProvider="dp")
	 public void testIrctc(String a,String b) throws InterruptedException, IOException{
	  String source=a;
	  String destination=b;
	  
	//WebDriver driver=new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver", "E:\\downloads\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("https://www.irctc.co.in/");
			
			String firstWind=driver.getWindowHandle();
			//System.out.println(firstWind);
			driver.findElement(By.linkText("Flight Tickets")).click();
			//driver.findElement(By.xpath("//a[text()='Flight Tickets']")).click();
			Set<String> allWindow=driver.getWindowHandles();
			for(String s:allWindow){
				driver.switchTo().window(s);
				secondWind =s;
				if(driver.getTitle().equals("IRCTC, Flight Ticket, Air Ticket, Cheap Flight, Low Fare Flight, Online Booking, Flight,Hotel")){
					break;
				}
			}
			//System.out.println(secondWind);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@id='where']/div[@class='containerfld']/input[@id='origin']")).sendKeys(source);
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//*[@id='destination']")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath("//div[@id='where']/div[@class='containerfld']/input[@id='origin']")).sendKeys(Keys.TAB);
			
			//driver.findElement(By.id("ui-active-menuitem")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath("//*[@id='destination']")).sendKeys(destination);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='ui-active-menuitem']")).click();
			
			driver.findElement(By.xpath("//input[@name='departDate']/following-sibling::img")).click();
			driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div[1]//a[text()='30']")).click();
	        //driver.findElement(By.xpath("//*[@id='departDate']")).sendKeys("30/11/2016");
			
			Select select = new Select(driver.findElement(By.xpath("//*[@id='noOfAdult']")));
			select.selectByIndex(1);
			select = new Select(driver.findElement(By.xpath("//*[@id='noOfChild']")));
			select.selectByIndex(1);
			driver.findElement(By.xpath("//input[@name='submitVal']/following-sibling::div")).click();
			Thread.sleep(5000);
			
			List<WebElement> fltList=driver.findElements(By.xpath("//div[contains(@id,'flight_')]"));
			System.out.println("Total no of flights available="+fltList.size());
			
			System.out.println("assertion for flight List:"+(fltList.size()>0 && fltList.size()<100));
			Assert.assertFalse((fltList.size()>0 && fltList.size()<100));
			
			
			Thread.sleep(3000);
			driver.close();
			driver.switchTo().window(firstWind);
			driver.close();
	 }
	 
	 @DataProvider
	 public Object[][] dp(){
	  Object[][] tabArray = null;
	  try {
	   FileInputStream ExcelFile = new FileInputStream("E:\\GspanWorkSpCe\\MavenProject\\irctcData.xlsx");
	   // Access the required test data sheet
	   ExcelWBook = new XSSFWorkbook(ExcelFile);
	   ExcelWSheet = ExcelWBook.getSheetAt(0);
	   int startRow = 1;
	   int startCol = 0;
	   int ci, cj;
	   int totalRows = ExcelWSheet.getLastRowNum();
	   System.out.println("TR = "+totalRows);
	   int totalCols = ExcelWSheet.getRow(1).getLastCellNum();
	   System.out.println("TC = "+totalCols);
	   tabArray = new String[totalRows][totalCols];
	   ci = 0;
	   for (int i = startRow; i <= totalRows; i++, ci++) {
	    cj = 0;
	    for (int j = startCol; j < totalCols; j++, cj++) {
	     try {
	      tabArray[ci][cj] = getCellData(i, j);
	     } catch (Exception e) {
	      break;
	     }
	    }
	   }

	  }

	  catch (FileNotFoundException e) {
	   e.printStackTrace();
	  }

	  catch (IOException e) {
	   e.printStackTrace();
	  }
	  return (tabArray);
	 }

	 public Object getCellData(int RowNum, int ColNum) throws Exception {

	  try {

	   Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

	   int dataType = Cell.getCellType();

	   if (dataType == 3) {

	    return "";

	   } else if (dataType == 0) {
	    Object CellData = Cell.getRawValue();
	    return CellData;

	   } else {
	    Object CellData = Cell.getStringCellValue();
	    return CellData;
	   }

	  } catch (Exception e) {

	   System.out.println(e.getMessage());

	   throw (e);

	  }
	 }
}
