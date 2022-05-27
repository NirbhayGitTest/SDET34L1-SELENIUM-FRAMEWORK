package com.vtiger.organizationTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CreateNewDocumentPage;
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.DocumentInformationPage;
import com.vtiger.webElementsRepo.DocumentsPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationDropdownTest extends BaseClass{

	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	
	@Test
	public void createOrganizationDropdownTest() {
		
		organizationPage = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		organizationInformationPage = new OrganizationInformationPage(driver);
		home.clickOrganizations(driver);
		organizationPage.createOrganizationImg();
		String orgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
		createNewOrganizationPage.enterOrganizationName(orgName);
		createNewOrganizationPage.selectIndustryDropdown(driver);
		createNewOrganizationPage.selectTypeDropdown(driver);
		createNewOrganizationPage.save();
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));
		javalibrary.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), orgName, "Organization");
		javalibrary.assertionThroughIfCondition(organizationInformationPage.getOrganizationIndustryName(), "Education", "correct industry");
		javalibrary.assertionThroughIfCondition(organizationInformationPage.getOrganizationTypeName(), "Press", "correct orgType");
	}
}
