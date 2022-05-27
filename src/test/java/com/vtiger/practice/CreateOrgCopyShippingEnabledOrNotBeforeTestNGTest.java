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

public class CreateOrgCopyShippingEnabledOrNotBeforeTestNGTest {
	
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
		
		
		//WebElement copyShippingRadio = driver.findElement(By.xpath("//b[text()='Copy Shipping address']/preceding-sibling::input"));
		
		//copyShippingRadio.click();
		createNewOrganizationPage.clickCopyShippingRadio();
		
		
		boolean copyShippingRadioEnabled = createNewOrganizationPage.getCopyShippingRadioElement().isSelected();
		jlib.printStatement("copyShippingRadioSelectedOnFirstClick :-  "+copyShippingRadioEnabled);
		
		createNewOrganizationPage.clickCopyShippingRadio();
		
		boolean copyShippingRadioDisabled = createNewOrganizationPage.getCopyShippingRadioElement().isSelected();
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
		
		//WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumLibrary.initializeActions(driver);
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(administrator, driver);
		
		home.signout(seleniumLibrary);
		
		//driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}

}
