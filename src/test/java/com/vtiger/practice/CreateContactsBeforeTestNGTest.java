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
import com.vtiger.webElementsRepo.CampaignInformationPage;
import com.vtiger.webElementsRepo.CampaignPage;
import com.vtiger.webElementsRepo.ContactInformationPage;
import com.vtiger.webElementsRepo.ContactPage;
import com.vtiger.webElementsRepo.CreateNewCampaignPage;
import com.vtiger.webElementsRepo.CreateNewContactPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsBeforeTestNGTest {
	
	public static void main(String[] args) throws IOException {
	
		JavaLibrary jLib = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		//String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
				
		long longTimeOut = jLib.stringToLong(timeout);
		
		int randomNumber = jLib.getRandomNumber(1000);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);

		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		//From WebElement Repo all Classes objects
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ContactPage contact = new ContactPage(driver);
		CreateNewContactPage createNewContact = new CreateNewContactPage(driver);
		ContactInformationPage contactInformationPage = new ContactInformationPage(driver);
		//
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		//Login
		//driver.findElement(By.name("user_name")).sendKeys(userName);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		//driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		login.loginAction(userName, password);
		
		
		//Click on Contacts
		//driver.findElement(By.xpath("//td[@align='center']/a[text()='Contacts']")).click();
		home.clickContacts(driver);
		
		
		//Click on create contact
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		contact.createContactImg();;
		
		//Enter mandatory field (last name)
		String lastName = WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		
		//driver.findElement(By.cssSelector("[name='lastname']")).sendKeys(lastName);
		createNewContact.enterContactLastNameAndSave(lastName);
		
		
		//Scroll
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollBy(0,800);");
		SeleniumWebDriverLibrary.initializeJs(driver);
		SeleniumWebDriverLibrary.scrollToSpecifiedHeight("800");
		
		//Validate contact name
		//String actualContactName = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']/span")).getText();
		//jLib.printStatement(actualContactName);
		
		jLib.assertionThroughIfCondition(contactInformationPage.getContactLastName(), lastName, "Contact last name");
		
		//Click on logout
		//WebElement logout = driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
		seleniumLibrary.initializeActions(driver);
		home.signout(seleniumLibrary);
		
		//Actions act = new Actions(driver);
		
		//act.moveToElement(logout).perform();
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(logout, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		//Close Browser
		//driver.quit();
	
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}

}
