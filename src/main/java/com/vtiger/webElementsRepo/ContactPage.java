package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactImg;

	
	//Constructor initialize
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Business library
	public void createContactImg()
	{
		createContactImg.click();
	}

}
