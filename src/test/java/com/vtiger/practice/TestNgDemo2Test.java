package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgDemo2Test extends TestNgPracticeAnnotationsTest{
	

	@Test
	public void practice4Test()
	{
		Reporter.log("TestNgPractice4Test --> Test4", true);
	}
	
	
	@Test
	public void practice5Test()
	{
		Reporter.log("TestNgPractice5Test --> Test5", true);
	}
	
	
	@Test
	public void practice6Test()
	{
		Reporter.log("TestNgPractice6Test --> Test6", true);
	}

}
