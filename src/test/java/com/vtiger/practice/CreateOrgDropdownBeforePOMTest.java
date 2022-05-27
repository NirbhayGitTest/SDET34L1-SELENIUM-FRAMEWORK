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

public class CreateOrgDropdownBeforePOMTest {

	public static void main(String[] args) throws IOException {
		
		//
		//
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
		//
		//
//		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
//		Properties property = new Properties();
//		property.load(fis);
//		
//		String url = property.getProperty("url");
//		String userName = property.getProperty("userName");
//		String password = property.getProperty("password");
//		String timeout = property.getProperty("timeout");
//		String browser = property.getProperty("browser");
		
		//fetch Org name from excel
//		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
//		Workbook book = WorkbookFactory.create(fis1);
//		Sheet sh = book.getSheet("Organization");
//		Row row = sh.getRow(2);
//		Cell cell = row.getCell(1);
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
				
//		Random r = new Random();
//		int randomNum = r.nextInt(1000);
				
		//String orgName = cell.getStringCellValue()+randomNumber;
		
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
			
			
		//long longTimeOut = Long.parseLong(timeout);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		//Login
		//driver.get(url);
		SeleniumWebDriverLibrary.navigateApp(url, driver);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//Click on create Organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		
		//select Education from Industry dropdown
		WebElement industryDropdown = driver.findElement(By.xpath("//select[@name='industry']"));
		//Select s = new Select(industryDropdown);
		//s.selectByValue("Education");
		SeleniumWebDriverLibrary.initializeSelect(industryDropdown);
		SeleniumWebDriverLibrary.dropDownHandleByValue("Education", industryDropdown);
		
		//select Press from type dropdown
		WebElement typeDropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		//Select s1 = new Select(typeDropdown);
		//s1.selectByValue("Press");
		SeleniumWebDriverLibrary.dropDownHandleByValue("Press", typeDropdown);
		
		//click on save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//validate
		WebElement actOrgName = driver.findElement(By.id("dtlview_Organization Name"));
		
		//if(actOrgName.getText().equalsIgnoreCase(orgName))
		//{
		//	jLib.printStatement("press type education org created successfully");
		//}
		//else
		//{
		//	jLib.printStatement("fail");
		//}
		jLib.assertionThroughIfCondition(actOrgName.getText(), orgName, "press type education org");
		
		//logout
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act = new Actions(driver);
//		act.moveToElement(administrator).perform();
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		
		//driver.quit();
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}
}
