package com.vtiger.webElementsRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class SearchProductsPage {
	
	@FindBy(id = "search_txt")
	private WebElement searchTxt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	//initialize the driver address to all the elements through contructors and make it as public
	public SearchProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Business library
	public void searchProductAndSelect(String productName,WebDriver driver)
	{
		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver,"Products");
		searchTxt.sendKeys(productName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
		
	}
}
