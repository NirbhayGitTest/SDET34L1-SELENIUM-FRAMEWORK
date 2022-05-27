package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgDemo1Test extends TestNgPracticeAnnotationsTest{

	
	@Test
	public void practice1Test()
	{
		Reporter.log("TestNgPractice1Test --> Test1", true);
	}
	
	
	@Test
	public void practice2Test()
	{
		Reporter.log("TestNgPractice2Test --> Test2", true);
	}
	
	
	@Test
	public void practice3Test()
	{
		Reporter.log("TestNgPractice3Test --> Test3", true);
	}
}
