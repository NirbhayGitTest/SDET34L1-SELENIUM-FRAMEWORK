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

public class CreateOrgCopyShippingEnabledOrNotBeforePOMTest {
	
	public static void main(String[] args) throws IOException {
		
		JavaLibrary jlib = new JavaLibrary();
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		long longTimeout = jlib.stringToLong(timeout);
		SeleniumWebDriverLibrary.browserSetting(longTimeout, driver);
		int randomNum = jlib.getRandomNumber(1000);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
			
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1);
		orgName = orgName + randomNum;
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		
		WebElement copyShippingRadio = driver.findElement(By.xpath("//b[text()='Copy Shipping address']/preceding-sibling::input"));
		
		copyShippingRadio.click();
		
		
		
		boolean copyShippingRadioEnabled = copyShippingRadio.isSelected();
		jlib.printStatement("copyShippingRadioSelectedOnFirstClick :-  "+copyShippingRadioEnabled);
		
		copyShippingRadio.click();
		
		boolean copyShippingRadioDisabled = copyShippingRadio.isSelected();
		if(copyShippingRadioDisabled == false)
		{
			jlib.printStatement("CopyShipping Diselecting on clicking again");
			jlib.printStatement("TestCase Pass");
		}
		else
		{
			jlib.printStatement("CopyShipping radio is not Diselecting on clicking again");
			jlib.printStatement("TestCase Fail");
		}
		
		//driver.findElement(By.xpath("//b[text()='Description Information']/../../following-sibling::tr[2]//input[@class='crmbutton small save']")).click();
		
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administrator);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}

}
