package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class ContactInformationPage {
	
	@FindBy(xpath="//span[@id='dtlview_Last Name']")
	private WebElement contactLastNameText;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactNameHeaderInfoText;
	
	
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String getContactLastName()
	{
		return contactLastNameText.getText();
	}
	
	
	public WebElement getContactNameInformation(SeleniumWebDriverLibrary seleniumLibrary)
	{
		return contactNameHeaderInfoText;
	}

}
