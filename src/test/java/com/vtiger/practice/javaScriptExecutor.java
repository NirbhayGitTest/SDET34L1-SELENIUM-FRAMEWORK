package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class javaScriptExecutor {

	public static void main(String[] args) throws IOException {
		
		JavaLibrary javalibrary = new JavaLibrary();
		PropertyFileLibrary.openPropertyFile(IallPathDataLibrary.PROPERTYFILEPATH);
		String timeOut = PropertyFileLibrary.getDataFromPropertyFile("timeout");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location='http://localhost:8888'");
		SeleniumWebDriverLibrary.initializeJs(driver);
		
//		driver.manage().window().maximize();
		long longTimeOut = javalibrary.stringToLong(timeOut);
		SeleniumWebDriverLibrary.browserSetting(longTimeOut, driver);
		
		SeleniumWebDriverLibrary seleniumLibrary = new SeleniumWebDriverLibrary(driver);
		
		SeleniumWebDriverLibrary.navigateApplicationThroughJs("http://localhost:8888");
		SeleniumWebDriverLibrary.enterDataThroughJs(driver.findElement(By.name("user_name")), "admin");
		SeleniumWebDriverLibrary.enterDataThroughJs(driver.findElement(By.name("user_password")), "root");
		SeleniumWebDriverLibrary.clickThroughJs(driver.findElement(By.id("submitButton")));
		SeleniumWebDriverLibrary.scrollUptoElement(driver.findElement(By.xpath("//b[contains(.,'Upcoming Activities')]")));
		
////////line 42 should make generic or not ??????
		String fileName1 = new javaScriptExecutor().getClass().getName();
		seleniumLibrary.takeScreenshot(fileName1,driver);
		
		SeleniumWebDriverLibrary.quitBrowser(driver);
		
	}
}
