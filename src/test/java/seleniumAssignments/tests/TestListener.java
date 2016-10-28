package seleniumAssignments.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{

		public void onTestFailure(ITestResult arg0) {
			// TODO Auto-generated method stub
			System.out.println("shit, test failed");
			
			/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = simpleDateFormat.format(new Date());
	*/		
			SimpleDateFormat sdf=new SimpleDateFormat("dd@mm@yyyy@hh@mm@ss");
			
			Date date=new Date();
			String d1=sdf.format(date);
			System.out.println(d1);
			
			File screenshotFile =((TakesScreenshot)IrctcTest.driver).getScreenshotAs(OutputType.FILE);		
			try {
				FileUtils.copyFile(screenshotFile, new File("E:\\Assigmts\\Screenshot\\irctc_shot_listner"+d1+".jpeg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		public void onTestSuccess(ITestResult arg0) {
			// TODO Auto-generated method stub
			System.out.println("wow, test passed");
		}


		public void onFinish(ITestContext arg0) {
			// TODO Auto-generated method stub
			
		}


		public void onStart(ITestContext arg0) {
			// TODO Auto-generated method stub
			
		}


		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}


		public void onTestSkipped(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}


		public void onTestStart(ITestResult arg0) {
			// TODO Auto-generated method stub
			
		}

		 
	}

