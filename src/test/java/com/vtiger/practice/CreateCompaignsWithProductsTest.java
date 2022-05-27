package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCompaignsWithProductsTest {
	
	public static void main(String[] args) throws IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		
		String campaignName = WorkbookLibrary.getDataFromExcel("Campaign", 4, 1)+randomNumber;
		String productName = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1)+randomNumber;
		
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
 		
 		SeleniumWebDriverLibrary.navigateApp(url, driver);
 		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
 		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
 		seleniumLibrary.explicitlyWait(driver, longTimeOut);
 		
 		driver.findElement(By.name("user_name")).sendKeys(userName);
 		driver.findElement(By.name("user_password")).sendKeys(password);
 		driver.findElement(By.id("submitButton")).click();
 		
 		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(productName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		seleniumLibrary.waitUntilElementClickable(driver.findElement(By.linkText("More")));
		
		WebElement moreLink = driver.findElement(By.linkText("More"));
		seleniumLibrary.mouseOverOnTheElement(moreLink);
		
		driver.findElement(By.linkText("Campaigns")).click();
 		driver.findElement(By.cssSelector("[alt='Create Campaign...']")).click();
 		driver.findElement(By.name("campaignname")).sendKeys(campaignName);
 		
 		driver.findElement(By.xpath("//td[contains(.,'Product') and @class='dvtCellLabel']/following-sibling::td/img")).click();
	    
 		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver, "Products");
 		
 		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productName);
 		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
 		
 		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
 		
 		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver, "Campaigns");
 		
 		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
 		WebElement actualCampaignName= driver.findElement(By.id("dtlview_Campaign Name"));
 		WebElement actualProductName= driver.findElement(By.xpath("//span[@id='dtlview_Product']/a"));
 		
 		jLib.assertionThroughIfCondition(actualCampaignName.getText(), campaignName, "campaign with product");
 		jLib.assertionThroughIfCondition(actualProductName.getText(), productName, "");
 		
 		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
 		seleniumLibrary.initializeActions(driver);
 		seleniumLibrary.mouseOverOnTheElement(administratorIcon);
 		driver.findElement(By.linkText("Sign Out")).click();
 		
 		WorkbookLibrary.closeExcel();
 		SeleniumWebDriverLibrary.quitBrowser(driver);
 		
	}
}