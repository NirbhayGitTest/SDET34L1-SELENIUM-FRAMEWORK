package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class CampaignInformationPage {
	
	
	@FindBy(xpath="//span[@id='dtlview_Campaign Name']")
	private WebElement campaignNameText;
	

	@FindBy(xpath="//span[@id='dtlview_Product']/a")
	private WebElement productNameText;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement campaignNameHeaderInfoText;

	public CampaignInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	public String getCampaignName()
	{
		return campaignNameText.getText();
	}

	public String getProductName()
	{
		return productNameText.getText();
	}
	
	public WebElement getCampaignNameInformation(SeleniumWebDriverLibrary seleniumLibrary)
	{
		return campaignNameHeaderInfoText;
	}
}
