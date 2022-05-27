package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CreateNewDocumentPage;
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.DocumentInformationPage;
import com.vtiger.webElementsRepo.DocumentsPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationDropdownBeforeTestNGTest {

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
				
		
		
		

		WebDriver driver= null;
		
		switch(browser)
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
		}
			
		long longTimeOut = jLib.stringToLong(timeout);
		int randomNumber = jLib.getRandomNumber(1000);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		//From WebElement Repo all Classes objects
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		OrganizationPage organizationPage = new OrganizationPage(driver);
		CreateNewOrganizationPage createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		OrganizationInformationPage organizationInformationPage = new OrganizationInformationPage(driver);
		
		
		//Login
		//driver.findElement(By.name("user_name")).sendKeys(userName);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		login.loginAction(userName, password);
		
		
		
		//Click on create Organization
		//driver.findElement(By.linkText("Organizations")).click();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		home.clickOrganizations(driver);
		organizationPage.createOrganizationImg();
		
		
		//driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
		createNewOrganizationPage.enterOrganizationName(orgName);
		
		
		//select Education from Industry dropdown
		//WebElement industryDropdown = driver.findElement(By.xpath("//select[@name='industry']"));
		//Select s = new Select(industryDropdown);
		//s.selectByValue("Education");
		//SeleniumWebDriverLibrary.initializeSelect(industryDropdown);
		//SeleniumWebDriverLibrary.dropDownHandleByValue("Education", industryDropdown);
		
		createNewOrganizationPage.selectIndustryDropdown(driver);
		
		//select Press from type dropdown
		//WebElement typeDropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		//Select s1 = new Select(typeDropdown);
		//s1.selectByValue("Press");
		//SeleniumWebDriverLibrary.dropDownHandleByValue("Press", typeDropdown);
		createNewOrganizationPage.selectTypeDropdown(driver);
		
		
		//click on save
		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		createNewOrganizationPage.save();
		
		//validate
		//WebElement actOrgName = driver.findElement(By.id("dtlview_Organization Name"));
		
		jLib.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), orgName, "Organization");
		jLib.assertionThroughIfCondition(organizationInformationPage.getOrganizationIndustryName(), "Education", "correct industry");
		jLib.assertionThroughIfCondition(organizationInformationPage.getOrganizationTypeName(), "Press", "correct orgType");
		
		//logout
	//	WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act = new Actions(driver);
//		act.moveToElement(administrator).perform();
		seleniumLibrary.initializeActions(driver);
		home.signout(seleniumLibrary);
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(administrator, driver);
		//driver.findElement(By.linkText("Sign Out")).click();
		
		//driver.quit();
		WorkbookLibrary.closeExcel();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}
}
