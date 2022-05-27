package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentsPage {
	
	@FindBy(xpath = "//img[@title='Create Document...']")
	private WebElement createDocumentImg;
	
	public DocumentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void createDocumentImg()
	{
		createDocumentImg.click();
	}
	

}
