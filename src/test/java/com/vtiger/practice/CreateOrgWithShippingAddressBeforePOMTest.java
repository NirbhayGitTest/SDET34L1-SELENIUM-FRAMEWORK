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

public class CreateOrgWithShippingAddressBeforePOMTest {
	
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
		
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
			
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1);
		orgName = orgName + randomNum;
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		
		WebElement copyBillingRadio = driver.findElement(By.xpath("//b[text()='Copy Billing address']/preceding-sibling::input"));
		copyBillingRadio.click();
		
		String shippingAddress = WorkbookLibrary.getDataFromExcel("Organization", 2, 2);
		shippingAddress = shippingAddress+randomNum;
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shippingAddress);
		jlib.printStatement("entered shipping address :-  "+shippingAddress);
		
		WebElement copyShippingRadio = driver.findElement(By.xpath("//b[text()='Copy Shipping address']/preceding-sibling::input"));
		copyShippingRadio.click();
		
		
		String valueInBillingAddress = driver.findElement(By.xpath("//textarea[@name='bill_street']")).getAttribute("value");
		jlib.printStatement("valueCopiedInBillingAddress :-  " + valueInBillingAddress);
		
		driver.findElement(By.xpath("//b[text()='Description Information']/../../following-sibling::tr[2]//input[@class='crmbutton small save']")).click();

		WebElement savedOrg =  driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		seleniumLibrary.explicitlyWait(driver, longTimeout);
		seleniumLibrary.waitUntilElementVisible(savedOrg);
		
		String savedOrgName = savedOrg.getText();
		jlib.printStatement("savedOrgName :-  "+savedOrgName);
		
		String savedBillingaddress = driver.findElement(By.xpath("//span[@id='dtlview_Billing Address']")).getText();
		String savedShippingaddress = driver.findElement(By.xpath("//span[@id='dtlview_Shipping Address']")).getText();
		
		jlib.assertionThroughIfCondition(savedBillingaddress, savedShippingaddress, "Org saved with shipping address");
		
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administrator);
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}

}
