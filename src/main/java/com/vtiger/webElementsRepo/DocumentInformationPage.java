package com.vtiger.webElementsRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class DocumentInformationPage {

	@FindBy(id = "dtlview_Title")
	private WebElement documentTitleText;
	
	@FindBy(xpath = "//td[text()='Notes']/following-sibling::td")
	private WebElement documentDescriptionText;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement documentNameHeaderInfoText;
	
	public DocumentInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public String getDocumentTitleName()
	{
		return documentTitleText.getText();
	}
	
	public String getDocumentDescription()
	{
		return documentDescriptionText.getText();
	}
	
	public WebElement getDocumentNameInformation(SeleniumWebDriverLibrary seleniumLibrary)
	{
		return documentNameHeaderInfoText;
	}
	
	public WebElement getDocumentDescriptionNameInformation()
	{
		return documentDescriptionText;
	}
	
}
