package com.vtiger.practice;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgPracticeTest {
	
	
	@Test
	public void practiceTest() //Test Annotation Method
	{
		System.out.println("maza aa gaya in console only");  //it will print in console only
		Reporter.log("bht jyada maza aaya in report only");  //it will print in report only
		Reporter.log("bht jyada maza aaya in console also", true);  //it will print in console and in report also
		Reporter.log("bht jyada maza aaya in report only", false); //it will print in report only
	}
	
	@Test  //called as Test annotation
	public void prctice1Test()
	{
		
		System.out.println("kyun sopln");
		System.out.println("batao sopln");
	}

}
