package com.sdet34l1.genericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;

public class GettersAndSetterForListener {
	
	private static GettersAndSetterForListener instance;
	
	public WebDriver driver;
	public JavaLibrary javalibrary;
	public String userName;
	public String password;
	public String browser;
	public String url;
	public long longTimeOut;
	public int randomNumber;
	public LoginPage login;
	public HomePage home;
	public Actions action;
	public WebDriverWait wait;
	public SeleniumWebDriverLibrary seleniumLibrary;
	public WorkbookLibrary workbookLibrary;
	public PropertyFileLibrary propertyfilelibrary;
	
	
	private GettersAndSetterForListener() {}
	
	
	public static GettersAndSetterForListener getInstance() {
		
		if(instance==null) {
			instance= new GettersAndSetterForListener();
		}
		return instance;
	}
	
	public static void setInstance(GettersAndSetterForListener instance) {
		GettersAndSetterForListener.instance = instance;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public JavaLibrary getJavalibrary() {
		return javalibrary;
	}
	public void setJavalibrary(JavaLibrary javalibrary) {
		this.javalibrary = javalibrary;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getLongTimeOut() {
		return longTimeOut;
	}
	public void setLongTimeOut(long longTimeOut) {
		this.longTimeOut = longTimeOut;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public LoginPage getLogin() {
		return login;
	}
	public void setLogin(LoginPage login) {
		this.login = login;
	}
	public HomePage getHome() {
		return home;
	}
	public void setHome(HomePage home) {
		this.home = home;
	}
	public Actions getAction() {
		return action;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
	public WebDriverWait getWait() {
		return wait;
	}
	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}
	public SeleniumWebDriverLibrary getSeleniumLibrary() {
		return seleniumLibrary;
	}
	public void setSeleniumLibrary(SeleniumWebDriverLibrary seleniumLibrary) {
		this.seleniumLibrary = seleniumLibrary;
	}
	public WorkbookLibrary getWorkbookLibrary() {
		return workbookLibrary;
	}
	public void setWorkbookLibrary(WorkbookLibrary workbookLibrary) {
		this.workbookLibrary = workbookLibrary;
	}
	
	public PropertyFileLibrary getPropertyfilelibrary() {
		return propertyfilelibrary;
	}
	public void setPropertyfilelibrary(PropertyFileLibrary propertyfilelibrary) {
		this.propertyfilelibrary = propertyfilelibrary;
	}
	

}
