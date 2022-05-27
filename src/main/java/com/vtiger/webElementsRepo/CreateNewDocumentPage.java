package com.vtiger.webElementsRepo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;

public class CreateNewDocumentPage {
	
	@FindBy(name = "notes_title")
	private WebElement documentTitleTxt;
	
	@FindBy(xpath = "//iframe[contains(@title,'Rich text editor')]")
	private WebElement frame1;
	
	@FindBy(xpath = "//body[@class='cke_show_borders']")
	private WebElement docDescriptionTxt;
	
	@FindBy(xpath = "//a[@id='cke_5']/span[@class='cke_icon']")
	private WebElement boldIcon;
	
	@FindBy(xpath = "//a[@id='cke_6']/span[@class='cke_icon']")
	private WebElement italicIcon;
	
	@FindBy(id = "filename_I__")
	private WebElement chooseFileBtn;
	
	
	@FindBy(xpath = "//b[.='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//constructor
	public CreateNewDocumentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business library
	
	
	public void enterDocumentTitleName(String documentTitleName)
	{
		documentTitleTxt.sendKeys(documentTitleName);
	}
	

	
	public void enterDocumentDescription(WebDriver driver, String documentDescription)
	{
		SeleniumWebDriverLibrary.switchToFrame(driver, frame1);
		docDescriptionTxt.sendKeys(documentDescription,Keys.CONTROL+"a");
		SeleniumWebDriverLibrary.switchToHomeWebPage(driver);
	}
	
	
	
	public void boldAndItalic()
	{
		boldIcon.click();
		italicIcon.click();
	}
	
	
	public void chooseFile(String documentPath)
	{
		chooseFileBtn.sendKeys(documentPath);
	}
	
	
	public void enterDocumentTitleNameAndSave(String documentTitleName)
	{
		documentTitleTxt.sendKeys(documentTitleName);
		saveBtn.click();
	}
	
	
	public void saveDocument()
	{
		saveBtn.click();
	}
	
	
	
	

}
