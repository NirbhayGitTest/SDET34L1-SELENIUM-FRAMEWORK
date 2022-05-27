package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsWithOrganizationsBeforeGenericTest {
	
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
		//fetch data to property file
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		Properties property = new Properties();
//		property.load(fis);
//		String url = property.getProperty("url");
//		System.out.println(url);
//		String username = property.getProperty("userName");
//		System.out.println(username);
//		String password = property.getProperty("password");
//		System.out.println(password);
//		String timeout = property.getProperty("timeout");
//		System.out.println(timeout);
//		String browser = property.getProperty("browser");
//		System.out.println(browser);
		
		//fetch data to excel file
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook book=WorkbookFactory.create(fis1);
		Sheet sh = book.getSheet("Contact");
		Row row = sh.getRow(4);
		Cell cell = row.getCell(2);
		
//		Random r = new Random();
//		int num = r.nextInt(1000);
	   String lastnamevalue=cell.getStringCellValue()+randomNumber;
	   System.out.println(lastnamevalue);
	   Row row1 = sh.getRow(2);
	   Cell cell1 = row1.getCell(1);
	   String organizationvalue2=cell1.getStringCellValue();
	   System.out.println(organizationvalue2);
	   
	  
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		}
		//long longTimeOut = Long.parseLong(timeout);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		driver.get(url);
		//testcase step==>1 login to the app
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
		{
			book.getSheet("Contact").getRow(14).createCell(5).setCellValue("Home page is displayed");
			book.getSheet("Contact").getRow(14).createCell(6).setCellValue("pass");
		}
		//click on contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		if(driver.getTitle().contains("Contacts"))
		{
			book.getSheet("Contact").getRow(15).createCell(5).setCellValue("Contact page is displayed");
			book.getSheet("Contact").getRow(15).createCell(6).setCellValue("pass");
		}
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastnamevalue);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			if(!id.equals(mainid))
			{
				driver.switchTo().window(id);
				driver.findElement(By.id("search_txt")).sendKeys(organizationvalue2);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.id("1")).click();
			}	
		}
		driver.switchTo().window(mainid);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement actualLastName= driver.findElement(By.id("dtlview_Last Name"));
		
		if(actualLastName.getText().equalsIgnoreCase(lastnamevalue))
		{
			System.out.println("Contact created successfully");
			System.out.println("TC pass");
		}
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/testData.xlsx");
		book.write(fos);
		book.close();
		driver.quit();
	}

}