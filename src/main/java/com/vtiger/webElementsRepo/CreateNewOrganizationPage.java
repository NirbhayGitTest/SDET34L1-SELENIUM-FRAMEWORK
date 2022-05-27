package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class CreateNewOrganizationPage {
	
	@FindBy(name = "accountname")
	private WebElement organizationNameText;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDropdownDrp;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDropdownDrp;
	
	@FindBy(xpath = "//b[text()='Copy Billing address']/preceding-sibling::input")
	private WebElement copyBillingRadio;
	
	@FindBy(xpath = "//b[text()='Copy Shipping address']/preceding-sibling::input")
	private WebElement copyShippingRadio;
	
	@FindBy(xpath = "//textarea[@name='bill_street']")
	private WebElement billingaddressTxt;
	
	@FindBy(xpath = "//textarea[@name='ship_street']")
	private WebElement shippingaddressTxt;
	
	//constructor
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business library
	
	public void enterOrganizationNameAndSave(String organizationName)
	{
		organizationNameText.sendKeys(organizationName);
		saveBtn.click();
	}
	
	public void enterOrganizationName(String organizationName)
	{
		organizationNameText.sendKeys(organizationName);
	}
	
	
	public void selectIndustryDropdown(WebDriver driver)
	{
		SeleniumWebDriverLibrary.dropDownHandleByValue("Education", industryDropdownDrp);
	}
	
	
	public void selectTypeDropdown(WebDriver driver)
	{
		SeleniumWebDriverLibrary.dropDownHandleByValue("Press", typeDropdownDrp);
	}
	
	
	public void save()
	{
		saveBtn.click();
	}
	
	public void clickCopyBillingRadio()
	{
		copyBillingRadio.click();
	}
	
	
	public WebElement getCopyBillingRadioElement()
	{
		return copyBillingRadio;
	}
	
	public void clickCopyShippingRadio()
	{
		copyShippingRadio.click();
	}
	
	
	public WebElement getCopyShippingRadioElement()
	{
		return copyShippingRadio;
	}
	
	public void enterBillingAddress(String billingAddress)
	{
		billingaddressTxt.sendKeys(billingAddress);
	}

	public void enterShippingAddress(String shippingAddress)
	{
		shippingaddressTxt.sendKeys(shippingAddress);
	}
	
	public WebElement getBillingAddressTextBox()
	{
		return billingaddressTxt;
	}
	
	public WebElement getShippingAddressTextBox()
	{
		return shippingaddressTxt;
	}
	
	
}
