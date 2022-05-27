package com.vtiger.practice;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentBeforePOMTest {
	
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
				
		long longTimeOut = jLib.stringToLong(timeout);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, null);
		
		int randomNumber = jLib.getRandomNumber(1000);
		//
		//
		//
		//
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		Properties property = new Properties();
//		property.load(fis);
//		String url = property.getProperty("url");
//		String userName = property.getProperty("userName");
//		String password = property.getProperty("password");
//		String timeout = property.getProperty("timeout");
//		String browser = property.getProperty("browser");
		
		//FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
		//Workbook book = WorkbookFactory.create(fis1);
		
//		Random r = new Random();
//		int randomNum = r.nextInt(1000);
		
//		Sheet sh = book.getSheet("Document");
//		Row row = sh.getRow(2);
//		Cell cell = row.getCell(1);
		
		//or
		
		//String docName = book.getSheet("Document").getRow(2).getCell(1).getStringCellValue()+randomNumber;
		//String docPath = book.getSheet("Document").getRow(2).getCell(2).getStringCellValue();
		//String docDescription = book.getSheet("Document").getRow(2).getCell(3).getStringCellValue();
		
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
		
		//long longTimeout= Long.parseLong(timeout);
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		//login
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//click on create document
		driver.findElement(By.linkText("Documents")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		
		driver.findElement(By.name("notes_title")).sendKeys(docName);
		
		
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
		//driver.switchTo().frame(iframe);
		SeleniumWebDriverLibrary.switchToFrame(driver, iframe);
		
		//with Robot class send description inside textbox
//		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(docDescription);
//		Robot rbt = new Robot();
//		rbt.keyPress(KeyEvent.VK_CONTROL);
//		rbt.keyPress(KeyEvent.VK_A);
//		rbt.keyRelease(KeyEvent.VK_CONTROL);
//		rbt.keyRelease(KeyEvent.VK_A);
		
		//or 
		//send description inside textbox with sendKeys
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(docDescription,Keys.CONTROL+"a");
		
		//copy paste by context click code (paste not supported on this frame but code is correct)
//		WebElement description = driver.findElement(By.xpath("//body[@class='cke_show_borders']"));
//		Actions act = new Actions(driver);
//		act.contextClick(description).perform();
//		Robot rbt = new Robot();
//		rbt.keyPress(KeyEvent.VK_DOWN);
//		rbt.keyRelease(KeyEvent.VK_DOWN);
//		rbt.keyPress(KeyEvent.VK_DOWN);
//		rbt.keyRelease(KeyEvent.VK_DOWN);
//		rbt.keyPress(KeyEvent.VK_ENTER);
//		rbt.keyRelease(KeyEvent.VK_ENTER);
//		
//		rbt.keyPress(KeyEvent.VK_RIGHT);
//		rbt.keyRelease(KeyEvent.VK_RIGHT);
//		rbt.keyPress(KeyEvent.VK_ENTER);
//		rbt.keyRelease(KeyEvent.VK_ENTER);
//		
//		rbt.keyPress(KeyEvent.VK_CONTROL);
//		rbt.keyPress(KeyEvent.VK_V);
//		rbt.keyRelease(KeyEvent.VK_CONTROL);
//		rbt.keyRelease(KeyEvent.VK_V);
//		Thread.sleep(3000);
		
		//to print the description passed in the textbox in console
		//String desctext = description.getAttribute("value");
		//System.out.println(desctext);
		//
		
		
		//driver.switchTo().defaultContent();
		SeleniumWebDriverLibrary.switchToHomeWebPage(driver);
		driver.findElement(By.xpath("//a[@id='cke_5']/span[@class='cke_icon']")).click();
		driver.findElement(By.xpath("//a[@id='cke_6']/span[@class='cke_icon']")).click();
		
		WebElement fileButton = driver.findElement(By.id("filename_I__"));
		fileButton.sendKeys(docPath);
		
		//this not work now => driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']"))
		.click();
		
		
		//WebDriverWait wait = new WebDriverWait(driver,longTimeOut);
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		WebElement headerText = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		seleniumLibrary.explicitlyWait(driver, longTimeOut);
		seleniumLibrary.waitUntilElementVisible(headerText);
		
		WebElement actDoc = driver.findElement(By.xpath("//td[@id='mouseArea_Title']/span"));
		//if(actDoc.getText().equalsIgnoreCase(docName))
		//	{
		//		jLib.printStatement("doc with file upload successfully");
		//	}
		//else
		//	{
		//		jLib.printStatement("fail");
		//	}
		jLib.assertionThroughIfCondition(actDoc.getText(), docName, "CreateDocument");
		
		//logout
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//Actions act = new Actions(driver);
		//act.moveToElement(administrator).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administrator);
		driver.findElement(By.linkText("Sign Out")).click();
				
		WorkbookLibrary.closeExcel();
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}
