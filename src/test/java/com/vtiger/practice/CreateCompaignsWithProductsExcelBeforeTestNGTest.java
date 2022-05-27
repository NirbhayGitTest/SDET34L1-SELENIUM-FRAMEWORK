package com.vtiger.practice;

import java.io.IOException;
import java.util.Set;
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
import com.vtiger.webElementsRepo.CampaignInformationPage;
import com.vtiger.webElementsRepo.CampaignPage;
import com.vtiger.webElementsRepo.CreateNewCampaignPage;
import com.vtiger.webElementsRepo.CreateNewProductPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.ProductPage;
import com.vtiger.webElementsRepo.SearchProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCompaignsWithProductsExcelBeforeTestNGTest {
	
	public static void main(String[] args) throws IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		WebDriver driver = null;
		
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

		
		
		String campaignname = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;
		
	    jLib.printStatement(campaignname);
	   
	   
	   
	   String productname = WorkbookLibrary.getDataFromExcel("Campaign", 4, 1);
	   
	   jLib.printStatement(productname);
	   
	    
	 	
	 		//WebDriver driver=null;
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
	 		seleniumLibrary.initializeActions(driver);
	 		
	 		

	 		//from webElementsRepo package
			LoginPage loginPage = new LoginPage(driver);
			HomePage homePage = new HomePage(driver);
			CampaignPage campaignPage = new CampaignPage(driver);
			CreateNewCampaignPage createNewCampaignPage = new CreateNewCampaignPage(driver);
			CampaignInformationPage campaignInformationPage = new CampaignInformationPage(driver);
			ProductPage productPage = new ProductPage(driver);
			CreateNewProductPage createNewProductPage = new CreateNewProductPage(driver);
			SearchProductsPage searchProductsPage = new SearchProductsPage(driver);
			
			
			loginPage.loginAction(userName, password);
			
			homePage.clickProducts(driver);
	 		
	 		productPage.createProductImg();
	 		
	 		createNewProductPage.enterProductNameAndSave(productname);
			
	 		seleniumLibrary.explicitlyWait(driver, longTimeOut);
	 		seleniumLibrary.waitUntilElementClickable(homePage.getMoreDropdown(driver));
	 		
	 		homePage.clickCampaign(seleniumLibrary);
			
			campaignPage.createCampaignImg();
	 		
			createNewCampaignPage.enterCampaignNameAndSwitchToSearchProduct(campaignname, driver);
	 		
			SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver,"Campaigns");
			
			createNewCampaignPage.saveCampaign();
			
	 		searchProductsPage.searchProductAndSelect(productname, driver);
	 		
	 		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver,"Campaign");
	 		
	 		jLib.assertionThroughIfCondition(campaignInformationPage.getCampaignName(), campaignname,"campaign with product");
			jLib.assertionThroughIfCondition(campaignInformationPage.getProductName(), productname,"correct product name");
			
	 		homePage.signout(seleniumLibrary);
	 		
	 		
	 		//book.close();
			WorkbookLibrary.closeExcel(); 
			//driver.quit();
			SeleniumWebDriverLibrary.quitBrowser(driver);
	 		
	 		
	 		/*
	 		 * WebDriverUtility.navigateApp(url, driver);
			
			loginPage.loginAction(username, password);
				
			homePage.clickProduct(driver);
			
			productPage.clickcreateProductLookUpImg();
			
			createNewProductPage.enterProductNameAndSave(productname);
			
//			WebDriverUtility.waitUntilElementClickable(driver.findElement(By.linkText("More")));
			WebDriverUtility.waitUntilElementClickable(homePage.getMoreDropdown(driver));
			
			homePage.clickCampaign(driver);
			
			campaignPage.clickcreateCampaignLookUpImg();
			
			createCampaignPage.enterCampaignNameAndEnterAndSwitchToSearchProduct(campaignname,driver);
			
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
			
			createCampaignPage.saveCampaign();	
			
			jutil.assertionThroughIfCondition(campaignInformationPage.getCampaignName(), campaignname,"campaign with product");
			jutil.assertionThroughIfCondition(campaignInformationPage.getProductName(), productname,"correct product name");
			
			homePage.signout(driver);
			
			WebDriverUtility.quitBrowser(driver);
	 		 */
	 		

			
			
	}
}