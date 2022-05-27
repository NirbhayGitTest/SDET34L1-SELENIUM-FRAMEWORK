package com.vtiger.practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchDatafromDatabaseTest {
	
	public static void main(String[] args) throws SQLException, EncryptedDocumentException, IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		String url=null,username=null,pwd=null,timeout=null,browserName=null;
		
		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "root");
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from logindata where s_no=2;");
		
		while(result.next())
		{
			url = result.getString("url");
			username = result.getString("username");
			pwd = result.getString("pwd");
			timeout = result.getString("timeout");
			browserName = result.getString("browserName");
			break;
		}
		long longTimeOut = Long.parseLong(timeout);
		WebDriver driver=null;
		
		//if(browserName.equalsIgnoreCase("chrome"))
		//{
		//	WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver();
		//}
		//else if(browserName.equalsIgnoreCase("firefox"))
		//{
		//	WebDriverManager.firefoxdriver().setup();
		//	driver = new FirefoxDriver();
		//}
		
		switch(browserName)
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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
		
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		
		String firstName = WorkbookLibrary.getDataFromExcel("FetchDataFromDatabase", 2, 1);
		String lastName = WorkbookLibrary.getDataFromExcel("FetchDataFromDatabase", 2, 2);
		
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		WebElement ActualFirstName = driver.findElement(By.id("dtlview_First Name"));
		WebElement ActualLastName = driver.findElement(By.id("dtlview_Last Name"));
		
		if(ActualFirstName.getText().equalsIgnoreCase(firstName) && ActualLastName.getText().equalsIgnoreCase(lastName))
		{
			jLib.printStatement("contact created successfully");
			jLib.printStatement("TC Pass");
		}
		
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions act = new Actions(driver);
		//act.moveToElement(administratorIcon).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administratorIcon);
		driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		connection.close();
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
