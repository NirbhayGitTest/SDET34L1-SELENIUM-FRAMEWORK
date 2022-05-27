package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignBeforeGenericTest extends BaseClass{
	
	@Test
	public void createCampaignBeforeGenericTest() throws IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		WebDriver driver=null;
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		//long longTimeOut = Long.parseLong(timeout);
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		
		//
		//OLD
		//convert the physical file into java readable object
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		//create object for properties class
//		Properties property = new Properties();
//		//load all keys
//		property.load(fis);
//		//fetch the data using key
//		String url = property.getProperty("url");
//		String userName = property.getProperty("userName");
//		String password = property.getProperty("password");
//		String timeout = property.getProperty("timeout");
//		String browser = property.getProperty("browser");
		//
		//
		
		//Old excel code
		//FileInputStream fisExcel = new FileInputStream("./src/test/resources/testData.xlsx");
		//Workbook wb = WorkbookFactory.create(fisExcel);
				
		String campaignname = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;
				
				//utilization
//				System.out.println(url);
//				System.out.println(userName);
//				System.out.println(password);
//				System.out.println(timeout);
//				System.out.println(browser);
				
//				WebDriverManager.chromedriver().setup();
//				WebDriver driver = new ChromeDriver();
//				driver.manage().window().maximize();
				//
				
				switch(browser)
				{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				default:
					System.out.println("please specify proper browser key");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				}
				//
				
				driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
				
				//Old getUrl code
				//driver.get(url);
				
				SeleniumWebDriverLibrary.navigateApp(url, driver);
				SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
				
				//String actCampaign = "Campaign";
				//campaignname = campaignname+randomNumber;
				driver.findElement(By.name("user_name")).sendKeys(userName);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				
				WebElement moreLink = driver.findElement(By.linkText("More"));
				seleniumLibrary.mouseOverOnTheElement(moreLink);
//				Actions act = new Actions(driver);
//				act.moveToElement(more).perform();
				driver.findElement(By.name("Campaigns")).click();
				driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
				driver.findElement(By.name("campaignname")).sendKeys(campaignname);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				String campaignNameExp = driver.findElement(By.id("dtlview_Campaign Name")).getText();
				System.out.println(campaignNameExp);
				
				if(campaignname.contains(campaignNameExp))
				{
					jLib.printStatement("campaign created successfully");
					jLib.printStatement("TC Pass");
				}
				else
				{
					jLib.printStatement("Fail");
				}
		
		
				WebElement administratorPic = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act1 = new Actions(driver);
				act1.moveToElement(administratorPic).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				driver.quit();
	}

}
