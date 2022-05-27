package com.vtiger.practice;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsBeforeGenericTest {
	
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
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		
//		Random r = new Random();
//		int num = r.nextInt(1000);
//		
//		//Login
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
//		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		
		//Click on Contacts
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Contacts']")).click();
		
		//Click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Enter mandatory field (last name)
		driver.findElement(By.cssSelector("[name='lastname']")).sendKeys("Upadhyay"+randomNumber);
		
		//Scroll
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,800);");
		
		//Click on save
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//Validate contact name
		String actualContactName = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']/span")).getText();
		System.out.println(actualContactName);
		
		if(actualContactName.contains("Upadhyay"))
		{
			System.out.println("Correct contact last name");
		}
		else
		{
			System.out.println("Incorrect contact last name");
		}
		
		//Click on logout
		WebElement logout = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
		Actions act = new Actions(driver);
		act.moveToElement(logout).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Close Browser
		driver.quit();
		
	}

}
