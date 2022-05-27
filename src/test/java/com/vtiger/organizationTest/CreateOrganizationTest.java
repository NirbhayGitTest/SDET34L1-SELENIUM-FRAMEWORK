package com.vtiger.organizationTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
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

public class CreateOrganizationTest extends BaseClass{
	
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	String actualOrgName;
	
	@Test(groups = {"sanity","baseclass"})
	public void createOrganizationTest() {
		
		
		
		organizationPage = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		organizationInformationPage = new OrganizationInformationPage(driver);
		
		home.clickOrganizations(driver);
		
		organizationPage.createOrganizationImg();
		
		actualOrgName = WorkbookLibrary.getDataFromExcel("Organization", 2, 1)+  randomNumber;
		
		createNewOrganizationPage.enterOrganizationNameAndSave(actualOrgName);
		
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));

		javalibrary.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), actualOrgName, "Organization");

	}

}
