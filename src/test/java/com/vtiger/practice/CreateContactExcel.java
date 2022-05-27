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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactExcel {
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
		
		//Properties File
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		Properties property = new Properties();
//		property.load(fis);
//		
//		String url = property.getProperty("url");
//		String userName = property.getProperty("userName");
//		String password = property.getProperty("password");
//		String timeout = property.getProperty("timeout");
//		String browser = property.getProperty("browser");
		
		//Excel
//		FileInputStream fis1 = new FileInputStream("./src/test/resources/testdata.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
		
		//Random r = new Random();
		//int random = r.nextInt(1000);
		
//		Sheet sh = wb.getSheet("Contact");
//		Row row = sh.getRow(2);
//		Cell cell = row.getCell(1);
//		String contactLastName = cell.getStringCellValue()+randomNumber;
		String contactLastName = WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		jLib.printStatement(contactLastName);
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			jLib.printStatement("chrome open");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			jLib.printStatement("firefox open");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		//long longTimeOut = Long.parseLong(timeout);
//		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
//		
//		driver.manage().window().maximize();
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String createdContact = driver.findElement(By.id("dtlview_Last Name")).getText();
		jLib.printStatement(createdContact);
		
		
		WebElement administratorPic = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act1 = new Actions(driver);
//		act1.moveToElement(administratorPic).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administratorPic);
		driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.closeExcel();
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
		
	}

}
