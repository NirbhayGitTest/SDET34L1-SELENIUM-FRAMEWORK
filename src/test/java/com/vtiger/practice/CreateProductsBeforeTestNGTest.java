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
import com.vtiger.webElementsRepo.CreateNewProductPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.ProductInformationPage;
import com.vtiger.webElementsRepo.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsBeforeTestNGTest {
	
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

		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			jLib.printStatement("chrome open");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			jLib.printStatement("firefox open");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		
		seleniumLibrary.initializeActions(driver);
		
		//From WebElement Repo all Classes objects
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		
		ProductPage product = new ProductPage(driver);
		CreateNewProductPage createNewProduct = new CreateNewProductPage(driver);
		ProductInformationPage productInformationPage = new ProductInformationPage(driver);
		//
		
		
		login.loginAction(userName, password);
		
		//Click on Contacts
		//driver.findElement(By.linkText("Products")).click();
		home.clickProducts(driver);
		
		//Click on create product
		product.createProductImg();
		
		//enter product name and save
		String productName = WorkbookLibrary.getDataFromExcel("Product", 2, 1)+ randomNumber;
		
		createNewProduct.enterProductNameAndSave(productName);
		
		
		jLib.assertionThroughIfCondition(productInformationPage.getProductName(), productName, "product");
		

		home.signout(seleniumLibrary);
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
		
		
		
	}

}
