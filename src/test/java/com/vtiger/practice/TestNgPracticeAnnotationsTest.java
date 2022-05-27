package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestNgPracticeAnnotationsTest {
	
	
	@BeforeSuite
	public void beforeSuite1Test()
	{
		Reporter.log("beforeSuite1", true);
	}
	
	@AfterSuite
	public void afterSuite1Test()
	{
		Reporter.log("afterSuite1", true);
	}
	
	@BeforeClass
	public void beforeClass1Test()
	{
		Reporter.log("beforeClass1", true);
	}
	
	@AfterClass
	public void afterClass1Test()
	{
		Reporter.log("afterClass1", true);
	}
	
	@BeforeTest
	public void beforeTest1Test()
	{
		Reporter.log("beforeTest1", true);
	}
	
	@AfterTest
	public void afterTest1Test()
	{
		Reporter.log("afterTest1", true);
	}
	
	@BeforeMethod
	public void beforeMethod1Test()
	{
		Reporter.log("beforeMethod1", true);
	}
	
	
	@AfterMethod
	public void afterMethod1Test()
	{
		Reporter.log("afterMethod1", true);
	}

}
