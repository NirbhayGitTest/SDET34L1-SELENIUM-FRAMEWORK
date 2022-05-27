package com.sdet34l1.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class is used to maintain all webdriver common actions
 * @author Nirbhay The Omen
 *
 */
public class SeleniumWebDriverLibrary {
	
	static WebDriver driver;
	
	WebDriverWait wait;
	Actions action;
	static Select select;
	static JavascriptExecutor js;
	static JavaLibrary javalibrary = new JavaLibrary();
	
	//static WebDriver driver;
	/**
	 * This method is used to initialize and launch the browser
	 * @param browser
	 
	public static void launchBrowser(String browser)
	{
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
	}
*/	
	
	/**
	 * This method is used to return driver instance
	 * @param driver
	 * @return
	 
	public static WebDriver getDriver(WebDriver driver)
	{
		return driver;
	}
*/
	
	/**
	 * This method is used to navigate to the application
	 * @param url
	 * @param driver
	 */
	
	
	public SeleniumWebDriverLibrary(WebDriver staticDriver)
	{
		
	}
	
	
	public static void navigateApp(String url,WebDriver driver)
	{
		driver.get(url);
	}
	
	/**
	 * This method is used to maximize the browser and Implicitly wait
	 * @param longTimeOut
	 * @param driver
	 */
	public static void browserSetting(long longTimeOut, WebDriver driver)
	{
		maximizeBrowser(driver);
		waitTillPageLoad(longTimeOut, driver);
	}
	
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to implicitly wait till page load
	 * @param longTimeOut
	 * @param driver
	 */
	public static void waitTillPageLoad(long longTimeOut, WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used to initialize the Actions class
	 * @param driver
	 */
	public void initializeActions(WebDriver driver)
	{
		action = new Actions(driver);
	}
	
	/**
	 * This method is used to mouseOver to the element
	 * @param moreLink
	 * @param driver
	 */
	public void mouseOverOnTheElement(WebElement element)
	{
		//Actions act = new Actions(driver);
		action.moveToElement(element).perform();
		
	}
	
	/**
	 * This method is used to Double click on the element
	 * @param element
	 */
	public void doubleClick(WebElement element)
	{
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to Double click
	 */
	public void doubleClick()
	{
		action.doubleClick().perform();
	}
	
	/**
	 * This method is used to close the browser instance
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
	/**
	 * This method is used to wait the control until the particular element is clickable
	 * @param element
	 */
	public void waitUntilElementClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is used to wait the control until the particular element is visible
	 * @param element
	 */
	public void waitUntilElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to initialize wait instance
	 * @param driver
	 * @param timeout
	 */
	public void explicitlyWait(WebDriver driver , long timeout)
	{
		wait = new WebDriverWait(driver, timeout);
	}
	
	/**
	 * This method is used to switch the window based on title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver , String partialText)
	{
		Set<String> sessionIDs = driver.getWindowHandles();
		for(String id:sessionIDs)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialText))
			{
				break;
			}
		}
	}
	
	/**
	 * This method is used to initialize the Select class
	 * @param element
	 */
	public static void initializeSelect(WebElement element)
	{
		select = new Select(element);
	}
	
	/**
	 * This method is used to handle dropdown by Visible text
	 * @param element
	 * @param visibleText
	 */
	public static void dropDownHandleByVisibleText(WebElement element, String visibleText)
	{
		select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method is used to handle dropdown by Index values
	 * @param element
	 * @param index
	 */
	public static void dropDownHandleByIndex(WebElement element, int index)
	{
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to handle dropdown by Values
	 * @param value
	 * @param element
	 */
	public static void dropDownHandleByValue(String value, WebElement element)
	{
		select = new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method is used to switch to the Frame based on Index
	 * @param driver
	 * @param index
	 */
	public static void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch to the Frame based on Name or ID attribute values
	 * @param driver
	 * @param nameorId
	 */
	public static void switchToFrame(WebDriver driver, String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
	
	/**
	 * This method is used to switch to the Frame based on WebElement located
	 * @param driver
	 * @param element
	 */
	public static void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch to the immediate Parent Frame
	 * @param driver
	 */
	public static void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method is used to switch to the Main Webpage
	 * @param driver
	 */
	public static void switchToHomeWebPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to Initialize the JavaScriptExecutor
	 * @param driver
	 */
	public static void initializeJs(WebDriver driver)
	{
		js = (JavascriptExecutor) driver;
	}
	
	/**
	 * This method is used to send or pass the data through JavaScriptExecutor
	 * @param element
	 * @param data
	 */
	public static void enterDataThroughJs(WebElement element, String data)
	{
		js.executeScript("arguments[0].value=arguments[1]", element, data);
	}
	
	/**
	 * This method is used to perform Click using JavaScriptExecutor
	 * @param element
	 */
	public static void clickThroughJs(WebElement element)
	{
		js.executeScript("arguments[0].click()", element);
	}
	
	/**
	 * This method is used to navigate to the Application by using JavaScriptExecutor
	 * @param url
	 */
	public static void navigateApplicationThroughJs(String url)
	{
		js.executeScript("window.location=arguments[0]", url);
	}
	
	/**
	 * This method is used to scroll upto provided height
	 * @param height
	 */
	public static void scrollToSpecifiedHeight(String height)
	{
		js.executeScript("window.scrollBy(0,"+height+")");
	}
	
	/**
	 * This method is used to scroll upto bottom of webpage
	 */
	public static void scrollToBottom()
	{
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	/**
	 * This method is used to scroll upto particular WebElement
	 * @param element
	 */
	public static void scrollUptoElement(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView()",element);
	}
	
	/**
	 * This method is used to Take Screenshot
	 * @param fileName
	 * @param driver
	 * @throws IOException
	 */
	public static String takeScreenshot(String fileName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =  (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("./screenshot/"+fileName+"_"+javalibrary.getDateTimeInFormat()+".png");
		System.out.println(trg.getAbsolutePath());
		FileUtils.copyFile(src, trg);
		return trg.getAbsolutePath();
	}
	
	/**
	 * This method is used to handle Alert by Accept or click on Ok
	 * @param driver
	 */
	public static void alertAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to handle Alert by Dismiss or click on Cancel
	 * @param driver
	 */
	public static void alertDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();;
	}
	
	/**
	 * This method is used to Send the Data into Alert
	 * @param driver
	 * @param data
	 */
	public static void alertSendData(WebDriver driver, String data)
	{
		driver.switchTo().alert().sendKeys(data);
	}
	
	/**
	 * This method is used to Fetch the text from Alert
	 * @param driver
	 * @return
	 */
	public static String getalertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method is used to perform Right click action
	 */
	public void rightClick()
	{
		action.contextClick().perform();
	}
	
	/**
	 * This method is used to Right click action on particular WebElement
	 * @param element
	 */
	public void rightClick(WebElement element)
	{
		action.contextClick(element).perform();
	}
}
