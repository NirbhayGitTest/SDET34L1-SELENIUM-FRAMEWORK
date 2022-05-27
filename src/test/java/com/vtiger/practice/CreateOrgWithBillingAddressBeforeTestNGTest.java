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
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithBillingAddressBeforeTestNGTest {

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
		
		//
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationPage organizationPage = new OrganizationPage(driver);
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage organizationInformationPage = new OrganizationInformationPage(driver);
		//
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		//driver.findElement(By.name("user_name")).sendKeys(userName);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		login.loginAction(userName, password);
		
		//driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		home.clickOrganizations(driver);
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		organizationPage.createOrganizationImg();
	
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1);
		orgName = orgName + randomNum;
		//driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		createNewOrganizationPage.enterOrganizationName(orgName);
		
		
		String billingAddress = WorkbookLibrary.getDataFromExcel("Organization", 2, 3);
		billingAddress = billingAddress+randomNum;
		//driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(billingAddress);
		createNewOrganizationPage.enterBillingAddress(billingAddress);
		jlib.printStatement("entered billing address :-  "+billingAddress);
				
		//WebElement copyBillingRadio = driver.findElement(By.xpath("//b[text()='Copy Billing address']/preceding-sibling::input"));
		//copyBillingRadio.click();
		createNewOrganizationPage.clickCopyBillingRadio();
		

		String valueInShippingAddress = createNewOrganizationPage.getShippingAddressTextBox().getAttribute("value");
		jlib.printStatement("valueCopiedInShippingAddress :-  " + valueInShippingAddress);
		
		//driver.findElement(By.xpath("//b[text()='Description Information']/../../following-sibling::tr[2]//input[@class='crmbutton small save']")).click();
		createNewOrganizationPage.save();
		
		//WebElement savedOrg =  driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String savedOrgName = organizationInformationPage.getOrganizationName();
		seleniumLibrary.explicitlyWait(driver, longTimeout);
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));
		
		//String savedOrgName = savedOrg.getText();
		jlib.printStatement("savedOrgName :-  "+savedOrgName);
		
		//String savedBillingaddress = driver.findElement(By.xpath("//span[@id='dtlview_Billing Address']")).getText();
		//String savedShippingaddress = driver.findElement(By.xpath("//span[@id='dtlview_Shipping Address']")).getText();
		String savedBillingaddress= organizationInformationPage.getBillingaddress();
		//String savedShippingaddress = organizationInformationPage.getShippingAddress();
		
		//jlib.assertionThroughIfCondition(savedBillingaddress, savedShippingaddress, "Org saved with billing address");
		jlib.assertionThroughIfCondition(savedBillingaddress, billingAddress, "correct billing address ");
		
		
		//WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumLibrary.initializeActions(driver);
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(administrator, driver);
		home.signout(seleniumLibrary);
		//driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
		
	}	
}