package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
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

public class CreateOrganizationBeforeTestNGTest {
	
	public static void main(String[] args) throws IOException {
		
		
		JavaLibrary jLib = new JavaLibrary();
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		//String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
		
		//
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationPage organizationPage = new OrganizationPage(driver);
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage organizationInformationPage = new OrganizationInformationPage(driver);
		//
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		seleniumLibrary.browserSetting(longTimeOut, driver);
		seleniumLibrary.initializeActions(driver);
		
		int randomNumber = jLib.getRandomNumber(1000);
		
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		
		
		//Login
		//driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		//driver.findElement(By.name("user_name")).sendKeys(userName);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		login.loginAction(userName, password);
		
		
		//Click on Organization
		//driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		home.clickOrganizations(driver);
		
		//Click on create organization
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		organizationPage.createOrganizationImg();
		
		//Enter mandatory field (organization name)
		String actualOrgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
		//driver.findElement(By.cssSelector("[name='accountname']")).sendKeys(actualOrgName);
		createNewOrganizationPage.enterOrganizationNameAndSave(actualOrgName);

		//Validate organization name
		//String expOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/span")).getText();
		//jLib.printStatement(expOrgName);
		jLib.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), actualOrgName, "Organization");
		
//		jLib.assertionThroughIfCondition(actualOrgName, expOrgName, "CraeteOrganization");
		
		//Click on logout
		//WebElement logout = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
		home.signout(seleniumLibrary);
		//Actions act = new Actions(driver);
		//act.moveToElement(logout).perform();
		
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(logout, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Close excel
		WorkbookLibrary.closeExcel();
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
