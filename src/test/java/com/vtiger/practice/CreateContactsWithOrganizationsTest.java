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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsWithOrganizationsTest {
	
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
		
		String lastnamevalue=WorkbookLibrary.getDataFromExcel("Contact", 4, 2)+randomNumber;
		
		jLib.printStatement(lastnamevalue);

		String organizationvalue2=WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		jLib.printStatement(organizationvalue2);
	   
		WebDriver driver=null;
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
 		seleniumLibrary.explicitlyWait(driver, longTimeOut);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
		{
			WorkbookLibrary.setdataIntoexcel("Contact", 14, 5, "Home page is displayed");
			WorkbookLibrary.setdataIntoexcel("Contact", 14, 6, "Pass");
		}
		//click on contacts
		driver.findElement(By.linkText("Contacts")).click();
		
		if(driver.getTitle().contains("Contacts"))
		{
			WorkbookLibrary.setdataIntoexcel("Contact", 15, 5, "Contact page is displayed");
			WorkbookLibrary.setdataIntoexcel("Contact", 15, 6, "Pass");
		}
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastnamevalue);
		
		//this is wrong , dont take index
		//driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='contact_name']/following-sibling::img")).click();
		
/////////********
		//
		String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			if(!id.equals(mainid))
			{
				driver.switchTo().window(id);
				driver.findElement(By.id("search_txt")).sendKeys(organizationvalue2);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.id("1")).click();
			}	
		}
		driver.switchTo().window(mainid);
		//
////////*********
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement actualLastName= driver.findElement(By.id("dtlview_Last Name"));
		
		//
		//
//		if(actualLastName.getText().equalsIgnoreCase(lastnamevalue))
//		{
//			jLib.printStatement("Contact created successfully");
//			jLib.printStatement("TC pass");
//		}
		
		jLib.assertionThroughIfCondition(actualLastName.getText(), lastnamevalue, "Contact");
		
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		seleniumLibrary.initializeActions(driver);
		seleniumLibrary.mouseOverOnTheElement(administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		
		WorkbookLibrary.writeDataInExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		WorkbookLibrary.closeExcel();
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
	}

}