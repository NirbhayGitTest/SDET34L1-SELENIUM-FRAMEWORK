package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	
	@FindBy(xpath = "//img[@title='Create Campaign...']")
	private WebElement createCampaignImg;

	
	//Constructor initialize
	public CampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Business library
	public void createCampaignImg()
	{
		createCampaignImg.click();
	}
	
}
