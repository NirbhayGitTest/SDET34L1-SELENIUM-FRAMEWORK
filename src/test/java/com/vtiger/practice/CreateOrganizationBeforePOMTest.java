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

public class CreateOrganizationBeforePOMTest {
	
	public static void main(String[] args) throws IOException {
		
		//
		//
		JavaLibrary jLib = new JavaLibrary();
		//WebDriver driver=null;
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//driver=new ChromeDriver();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		//String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		int randomNumber = jLib.getRandomNumber(1000);
		//
		//
		
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
//		
//		Random r = new Random();
//		int num = r.nextInt(1000);
		
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		
		//String actualOrgName = "TestYantra";
		//actualOrgName = actualOrgName + randomNumber;
		String actualOrgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
		
		//Login
		//driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on Organization
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		
		//Click on create organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Enter mandatory field (organization name)
		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys(actualOrgName);
		
		//Scroll
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("window.scrollBy(0,500);");
		SeleniumWebDriverLibrary.initializeJs(driver);
		SeleniumWebDriverLibrary.scrollToSpecifiedHeight("500");
		
		//Click on save
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[2]")).click();
		
		
		//Validate organization name
		String expOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/span")).getText();
		jLib.printStatement(expOrgName);
		
		//if(actualOrgName.equals(expOrgName))
		//{
		//	jLib.printStatement("Correct org name");
		//}
		//else
		//{
		//	jLib.printStatement("Incorrect org name");
		//}
		
		jLib.assertionThroughIfCondition(actualOrgName, expOrgName, "CraeteOrganization");
		
		//Click on logout
		WebElement logout = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
		//Actions act = new Actions(driver);
		//act.moveToElement(logout).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(logout);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Close
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
