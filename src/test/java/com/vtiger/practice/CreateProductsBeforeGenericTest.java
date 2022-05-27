package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsBeforeGenericTest {
	
	public static void main(String[] args) throws IOException {
		
		//
		//
		JavaLibrary jLib = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		//
		//
		
//		//convert the physical file into java readable object
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
		
		//utilization
		System.out.println(url);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(timeout);
		System.out.println(browser);
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		//
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.out.println("chrome open");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.out.println("firefox open");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		//
		//long longTimeOut = Long.parseLong(timeout);
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		driver.get(url);
		//Random r = new Random();
		//int num = r.nextInt(1000);
		String actProduct = "Product";
		actProduct = actProduct+randomNumber;
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(actProduct);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String productNameExp = driver.findElement(By.id("dtlview_Product Name")).getText();
		System.out.println(productNameExp);
		
		if(actProduct.contains(productNameExp))
		{
			System.out.println("product created successfully");
			System.out.println("TC Pass");
		}
		else
		{
			System.out.println("TC Fail");
		}
		
		WebElement administratorPic = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administratorPic).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}

}
