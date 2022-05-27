package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CampaignInformationPage;
import com.vtiger.webElementsRepo.CampaignPage;
import com.vtiger.webElementsRepo.CreateNewCampaignPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignBeforeTestNGTest {
	
	public static void main(String[] args) throws IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		WebDriver driver=null;
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		//long longTimeOut = Long.parseLong(timeout);
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		
			
		String campaignname = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;
				
				
				switch(browser)
				{
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				default:
					System.out.println("please specify proper browser key");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				}
				
				SeleniumWebDriverLibrary.navigateApp(url, driver);
				SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
				SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
				seleniumLibrary.initializeActions(driver);
				
				
//		//		//from webElementsRepo package
				LoginPage login = new LoginPage(driver);
				HomePage home = new HomePage(driver);
				CampaignPage campaign = new CampaignPage(driver);
				CreateNewCampaignPage createNewCampaign = new CreateNewCampaignPage(driver);
				CampaignInformationPage campaignInformation = new CampaignInformationPage(driver);
				
				
				login.loginAction(userName, password);
				
				home.clickCampaign(seleniumLibrary);
				
				campaign.createCampaignImg();
				
				createNewCampaign.enterCampaignNameAndSave(campaignname);
				
				jLib.assertionThroughIfCondition(campaignInformation.getCampaignName(), campaignname, "campaign");
				
				
				//from webElementsRepo package
				home.signout(seleniumLibrary);
				
				WorkbookLibrary.closeExcel();
				
				SeleniumWebDriverLibrary.quitBrowser(driver);
				//driver.quit();
	}
}
