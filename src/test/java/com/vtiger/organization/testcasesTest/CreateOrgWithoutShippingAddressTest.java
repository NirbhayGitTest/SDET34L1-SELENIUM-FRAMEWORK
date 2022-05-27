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

public class CreateOrgWithoutShippingAddressTest extends BaseClass{

	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	
	
	@Test
	public void createOrgWithoutShippingAddressTest() {
		
		
		organizationPage = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		organizationInformationPage = new OrganizationInformationPage(driver);
		
		home.clickOrganizations(driver);
		
		organizationPage.createOrganizationImg();
		
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1);
		orgName = orgName + randomNumber;
		
		createNewOrganizationPage.enterOrganizationName(orgName);

		createNewOrganizationPage.clickCopyBillingRadio();
		
		String shippingAddress = WorkbookLibrary.getDataFromExcel("Organization", 2, 2);
		shippingAddress = shippingAddress+randomNumber;
		
		createNewOrganizationPage.enterShippingAddress(shippingAddress);
		javalibrary.printStatement("entered shipping address :-  "+shippingAddress);

		String valueInBillingAddress = createNewOrganizationPage.getBillingAddressTextBox().getAttribute("value");
		javalibrary.printStatement("valueCopiedInBillingAddress :-  " + valueInBillingAddress);

		createNewOrganizationPage.save();
		
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));
		
		String savedOrgName = organizationInformationPage.getOrganizationName();
		
		
		javalibrary.printStatement("savedOrgName :-  "+savedOrgName);
	}
	
	
}
