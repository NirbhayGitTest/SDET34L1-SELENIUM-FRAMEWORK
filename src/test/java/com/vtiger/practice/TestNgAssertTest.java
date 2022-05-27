package com.vtiger.practice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertTest {
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test(retryAnalyzer=com.sdet34l1.genericLibrary.RetryAnalyzerImplementation.class)
	public void practice1Test()
	{
		Reporter.log("a-practice1",true);
		Reporter.log("b-practice1",true);
		Reporter.log("c-practice1",true);
		Assert.fail();
		softAssert.fail();
		Reporter.log("d-practice1",true);
		softAssert.assertAll();
		Reporter.log("e-practice1",true);
	}
	
	@Test
	public void practice2Test()
	{
		Reporter.log("f-practice1",true);
		softAssert.fail();
		Reporter.log("g-practice1",true);
		Reporter.log("h-practice1",true);
		softAssert.assertAll();
	}
	
	

}
