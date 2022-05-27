package com.vtiger.practice;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CreateNewDocumentPage;
import com.vtiger.webElementsRepo.DocumentInformationPage;
import com.vtiger.webElementsRepo.DocumentsPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentBeforeTestNGTest {
	
	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		
		
		JavaLibrary jLib = new JavaLibrary();
		
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		//fetch the data using key
		String url = PropertyFileLibrary.getDataFromPropertyFile("url");
		String userName = PropertyFileLibrary.getDataFromPropertyFile("userName");
		String password = PropertyFileLibrary.getDataFromPropertyFile("password");
		String timeout = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		String browser = PropertyFileLibrary.getDataFromPropertyFile("browser");
		
		int randomNumber = jLib.getRandomNumber(1000);
		
		
		String docName = WorkbookLibrary.getDataFromExcel("Document", 2, 1) + randomNumber;
		String docPath = WorkbookLibrary.getDataFromExcel("Document", 2, 2);
		String docDescription = WorkbookLibrary.getDataFromExcel("Document", 2, 3);
		
		WebDriver driver=null;
		
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
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		
		
		long longTimeOut = jLib.stringToLong(timeout);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);

		//From WebElement Repo all Classes objects
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		DocumentsPage document = new DocumentsPage(driver);
		CreateNewDocumentPage createNewDocument = new CreateNewDocumentPage(driver);
		DocumentInformationPage documentInformationPage = new DocumentInformationPage(driver);
		
		
		//click on create document
		//driver.findElement(By.linkText("Documents")).click();
		//driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		//driver.findElement(By.name("notes_title")).sendKeys(docName);
		
		login.loginAction(userName, password);
		
		home.clickDocuments(driver);
		
		document.createDocumentImg();
		
		//just enter document title name
		createNewDocument.enterDocumentTitleName(docName);
		
		
		//WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
		//driver.switchTo().frame(iframe);
		
		//SeleniumWebDriverLibrary.switchToFrame(driver, iframe);
		//driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(docDescription,Keys.CONTROL+"a");

		
		createNewDocument.enterDocumentDescription(driver, docDescription);
		
		
		//driver.switchTo().defaultContent();
		//SeleniumWebDriverLibrary.switchToHomeWebPage(driver);
		//driver.findElement(By.xpath("//a[@id='cke_5']/span[@class='cke_icon']")).click();
		//driver.findElement(By.xpath("//a[@id='cke_6']/span[@class='cke_icon']")).click();
		
		createNewDocument.boldAndItalic();
		
		
		//WebElement fileButton = driver.findElement(By.id("filename_I__"));
		//fileButton.sendKeys(docPath);
		
		createNewDocument.chooseFile(docPath);
		
		
		//this not work now => driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//driver.findElement(By.xpath("//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']"))
		//.click();
		
		createNewDocument.saveDocument();
		
		//WebElement actDoc = driver.findElement(By.xpath("//td[@id='mouseArea_Title']/span"));
		
		//jLib.assertionThroughIfCondition(actDoc.getText(), docName, "CreateDocument");
		jLib.assertionThroughIfCondition(documentInformationPage.getDocumentTitleName(), docName, "document");
		jLib.assertionThroughIfCondition(documentInformationPage.getDocumentDescription(), docDescription, "document description");
		//logout
		//WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions act = new Actions(driver);
		//act.moveToElement(administrator).perform();
		seleniumLibrary.initializeActions(driver);
		home.signout(seleniumLibrary);
		//SeleniumWebDriverLibrary.mouseOverOnTheElement(administrator, driver);
		//driver.findElement(By.linkText("Sign Out")).click();
				
		WorkbookLibrary.closeExcel();
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
