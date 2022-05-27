package com.vtiger.organization.testcasesTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgCopyBillingEnabledOrNotTest extends BaseClass{
	
	OrganizationPage organizationPage ;
	CreateNewOrganizationPage createNewOrganizationPage;
	

	@Test
	public void createOrgCopyBillingEnabledOrNotTest() {
		
		
		organizationPage = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		
		
		home.clickOrganizations(driver);
		organizationPage.createOrganizationImg();
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1);
		orgName = orgName + randomNumber;
		javalibrary.printStatement(orgName);
		createNewOrganizationPage.enterOrganizationName(orgName);
		
		createNewOrganizationPage.clickCopyBillingRadio();
		
		boolean copyBillingRadioEnabled = createNewOrganizationPage.getCopyBillingRadioElement().isSelected();
		javalibrary.printStatement("copyShippingRadioSelectedOnFirstClick :-  "+copyBillingRadioEnabled);
		
		createNewOrganizationPage.getCopyBillingRadioElement().click();
		
		boolean copyBillingRadioDisabled = createNewOrganizationPage.getCopyBillingRadioElement().isSelected();
		if(copyBillingRadioDisabled == false)
		{
			javalibrary.printStatement("CopyBilling Diselecting on clicking again");
			javalibrary.printStatement("TestCase Pass");
		}
		else
		{
			javalibrary.printStatement("CopyBilling radio is not Diselecting on clicking again");
			javalibrary.printStatement("TestCase Fail");
		}
		
	}

}
