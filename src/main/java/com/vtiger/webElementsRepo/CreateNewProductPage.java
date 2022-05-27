package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {

	@FindBy(name = "productname")
	private WebElement productNameText;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//constructor
	public CreateNewProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void enterProductNameAndSave(String productName)
	{
		productNameText.sendKeys(productName);
		saveBtn.click();
	}
	
}
