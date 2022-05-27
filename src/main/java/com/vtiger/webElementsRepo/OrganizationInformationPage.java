package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class OrganizationInformationPage {
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement organizationNameText;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement organizationNameHeaderInfoText;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement organizationIndustryText;
	
	@FindBy(id = "dtlview_Type")
	private WebElement organizationTypeText;
	
	@FindBy(xpath = "//span[@id='dtlview_Billing Address']")
	private WebElement billingAddressText;
	
	@FindBy(xpath = "//span[@id='dtlview_Shipping Address']")
	private WebElement shippingAddressText;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public String getOrganizationName()
	{
		return organizationNameText.getText();
	}
	
	public String getOrganizationIndustryName()
	{
		return organizationIndustryText.getText();
	}
	
	public String getOrganizationTypeName()
	{
		return organizationTypeText.getText();
	}
	
	public WebElement getOrganizationNameInformation(SeleniumWebDriverLibrary seleniumLibrary)
	{
		return organizationNameHeaderInfoText;
	}
	
	public String getBillingaddress()
	{
		return billingAddressText.getText();
	}

	public String getShippingAddress()
	{
		return shippingAddressText.getText();
	}
}