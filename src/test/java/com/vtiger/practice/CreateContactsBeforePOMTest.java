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

public class CreateContactsBeforePOMTest {
	
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
		//Random r = new Random();
		//int num = r.nextInt(1000);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
			
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		//driver.manage().window().maximize();	
		//driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	
		//Login
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
//		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		
		//Click on Contacts
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Contacts']")).click();
		
		//Click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Enter mandatory field (last name)
		String lastName = WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(lastName);
		
		//Scroll
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollBy(0,800);");
		SeleniumWebDriverLibrary.initializeJs(driver);
		SeleniumWebDriverLibrary.scrollToSpecifiedHeight("800");
		
		//Click on save
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//Validate contact name
		String actualContactName = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']/span")).getText();
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
		WebElement logout = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
		//Actions act = new Actions(driver);
		seleniumLibrary.initializeActions(driver);
		//act.moveToElement(logout).perform();
		seleniumLibrary.mouseOverOnTheElement(logout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Close Browser
		//driver.quit();
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}

}
