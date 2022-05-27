package com.vtiger.organization.testcasesTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;

public class CreateOrgWithShippingAddressTest extends BaseClass{
	
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	
	
	@Test
	public void createOrgWithShippingAddressTest() {
		
		organizationPage = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		organizationInformationPage = new OrganizationInformationPage(driver);
		
		
		
		home.clickOrganizations(driver);
		
		organizationPage.createOrganizationImg();
		
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+ randomNumber;
		
		
		createNewOrganizationPage.enterOrganizationName(orgName);
		
		createNewOrganizationPage.clickCopyBillingRadio();
		
		String shippingAddress = WorkbookLibrary.getDataFromExcel("Organization", 2, 2)+randomNumber;
		
		
		createNewOrganizationPage.enterShippingAddress(shippingAddress);
		javalibrary.printStatement("entered shipping address :-  "+shippingAddress);
		
		createNewOrganizationPage.clickCopyShippingRadio();
		
		String valueInBillingAddress = createNewOrganizationPage.getBillingAddressTextBox().getAttribute("value");
		javalibrary.printStatement("valueCopiedInBillingAddress :-  " + valueInBillingAddress);
		
		createNewOrganizationPage.save();
		
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));
		
		String savedOrgName = organizationInformationPage.getOrganizationName();
			
		javalibrary.printStatement("savedOrgName :-  "+savedOrgName);
		
		String savedBillingaddress= organizationInformationPage.getBillingaddress();
		
		javalibrary.assertionThroughIfCondition(savedBillingaddress, shippingAddress, "Org saved with shipping address");
			
	}

}
