package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

//create the class as webpage name and make it as public

public class HomePage {
	
	//declare all the elements and specify the access specifier as private
	
	@FindBy(linkText = "More")
	private WebElement moreDropDown;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsTab;
	
	@FindBy(linkText = "Products")
	private WebElement ProductsTab;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsTab;
	
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationsTab;
	
	@FindBy(linkText = "Documents")
	private WebElement DocumentsTab;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	
	//initialize the driver address to all the elements through constructors and make it as public
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//business library
	
	public void clickCampaign(SeleniumWebDriverLibrary seleniumLibrary)
	{
		seleniumLibrary.mouseOverOnTheElement(moreDropDown);
		campaignsTab.click();
	}
	
	public void signout(SeleniumWebDriverLibrary seleniumLibrary)
	{
		seleniumLibrary.mouseOverOnTheElement(administratorIcon);
		signOutLink.click();
	}
	
	
	public void clickProducts(WebDriver driver)
	{
		ProductsTab.click();
	}
	
	public void clickContacts(WebDriver driver)
	{
		ContactsTab.click();
	}
	
	public void clickOrganizations(WebDriver driver)
	{
		OrganizationsTab.click();
	}
	
	public void clickDocuments(WebDriver driver)
	{
		DocumentsTab.click();
	}
	
	public WebElement getMoreDropdown(WebDriver driver)
	{
		return moreDropDown;
	}
	
	public WebElement getContactTab(WebDriver driver)
	{
		return ContactsTab;
	}
	
}
