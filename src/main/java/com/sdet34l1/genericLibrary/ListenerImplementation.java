package com.sdet34l1.genericLibrary;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author Nirbhay The Omen
 *
 */
public class ListenerImplementation implements ITestListener{

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart");
		ExtentSparkReporter  spark = new ExtentSparkReporter("./extentReport/extentreport.html");
		spark.config().setDocumentTitle("Document Title");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("ReportName");
		
		report = new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("Environment", "Testing Environment");
			report.setSystemInfo("Reporter Name", "Nirbhay Kumar Upadhyay");
			report.setSystemInfo("Platform", "Windows 11");
			report.setSystemInfo("Unit Testing Tool", "TestNG");
			report.setSystemInfo("Automation Tool", "Selenium");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");
		
		test=report.createTest(result.getMethod().getMethodName());
		
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		
	test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		
		test.log(Status.FAIL,result.getMethod().getMethodName()+" is failed" );
		test.log(Status.FAIL, result.getThrowable());
		
		
		
		try {
			test.addScreenCaptureFromPath(SeleniumWebDriverLibrary.takeScreenshot(result.getMethod().getMethodName(),BaseClass.staticDriver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		
		test.log(Status.SKIP,result.getMethod().getMethodName()+" is skipped" );
		test.log(Status.SKIP, result.getThrowable());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
		
		report.flush();
	}

	
	
	

}
