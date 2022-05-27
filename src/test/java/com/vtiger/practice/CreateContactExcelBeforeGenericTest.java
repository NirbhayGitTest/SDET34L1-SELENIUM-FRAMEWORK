package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

public class CreateContactExcelBeforeGenericTest {
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
		
		//Properties File
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		Properties property = new Properties();
//		property.load(fis);
//		
//		String url = property.getProperty("url");
//		String userName = property.getProperty("userName");
//		String password = property.getProperty("password");
//		String timeout = property.getProperty("timeout");
//		String browser = property.getProperty("browser");
		
		//Excel
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		//Random r = new Random();
		//int random = r.nextInt(1000);
		
		Sheet sh = wb.getSheet("Contact");
		Row row = sh.getRow(2);
		Cell cell = row.getCell(1);
		String contactLastName = cell.getStringCellValue()+randomNumber;
		System.out.println(contactLastName);
		
		
		WebDriverManager.chromedriver().setup();
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
		
		//long longTimeOut = Long.parseLong(timeout);
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String createdContact = driver.findElement(By.id("dtlview_Last Name")).getText();
		System.out.println(createdContact);
		
		//create organization
		
		
		
		
		WebElement administratorPic = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act1 = new Actions(driver);
		act1.moveToElement(administratorPic).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
		
	}

}
