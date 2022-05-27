package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTestOtherWay {

	public static void main(String[] args) throws IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		//String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		//
		//
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
//		
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		
//		Random r = new Random();
//		int num = r.nextInt(1000);
//		
//		//Login
//		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on Contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
		//Enter mandatory field (last name)
		//driver.findElement(By.name("lastname")).sendKeys("Upadhyay"+randomNumber);
		String lastName = WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		
		//Scroll
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,800);");
		SeleniumWebDriverLibrary.initializeJs(driver);
		SeleniumWebDriverLibrary.scrollToSpecifiedHeight("800");
				
		//Click on save
		driver.findElement(By.className("crmButton")).click();
		
		//Validate contact name
		String actualContactName=driver.findElement(By.className("dvHeaderText")).getText();
		jLib.printStatement(actualContactName);
		
		//if(actualContactName.contains("Upadhyay"))
		//{
		//	jLib.printStatement("Correct contact last name");
		//}
		//else
		//{
		//	jLib.printStatement("Incorrect contact last name");
		//}
		jLib.assertionThroughIfCondition(actualContactName, lastName, "Correct contact last name");
		
		//Click on logout
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
//		Actions act = new Actions(driver);
//		act.moveToElement(logout).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(logout);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Close
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
