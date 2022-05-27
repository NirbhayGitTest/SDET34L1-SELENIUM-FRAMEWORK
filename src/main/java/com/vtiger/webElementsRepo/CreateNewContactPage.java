package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	@FindBy(name = "lastname")
	private WebElement contactLastNameText;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement clickorganizationLookUpImg;
	
	//constructor
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void enterContactLastNameAndSave(String contactLastName)
	{
		contactLastNameText.sendKeys(contactLastName);
		saveBtn.click();
	}
	
	public void enterContactLastName(String contactLastName)
	{
		contactLastNameText.sendKeys(contactLastName);
		
	}
	
	public void enterContactNameAndSwitchToSearchOrganization(String contactLastName, WebDriver driver)
	{
		contactLastNameText.sendKeys(contactLastName);
		clickorganizationLookUpImg.click();
	}
	
	public void saveContact()
	{
		saveBtn.click();
	}

}
