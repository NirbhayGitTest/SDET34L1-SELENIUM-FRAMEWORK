package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	
	
	@FindBy(xpath = "//input[@name='campaignname']")
	private WebElement campaignNameTxt;
	
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement clickproductLookUpImg;
	
	
	//constructor initialize
	public CreateNewCampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//business library
	public void enterCampaignNameAndSwitchToSearchProduct(String campaignname, WebDriver driver)
	{
		campaignNameTxt.sendKeys(campaignname);
		clickproductLookUpImg.click();
	}

	public void saveCampaign()
	{
		saveBtn.click();
	}
	
	public void enterCampaignNameAndSave(String campaignname)
	{
		campaignNameTxt.sendKeys(campaignname);
		saveBtn.click();
	}

}
