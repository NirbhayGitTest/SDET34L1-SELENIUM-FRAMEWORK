package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class ProductInformationPage {
	
	@FindBy(id = "dtlview_Product Name")
	private WebElement productNameText;
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement productNameHeaderInfoText;
	
	public ProductInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public String getProductName()
	{
		return productNameText.getText();
	}
	
	public WebElement getProductNameInformation(SeleniumWebDriverLibrary seleniumLibrary)
	{
		return productNameHeaderInfoText;
	}

}
