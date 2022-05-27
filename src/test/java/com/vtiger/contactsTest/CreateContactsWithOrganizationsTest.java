package com.vtiger.contactsTest;

import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileLibrary;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.ContactInformationPage;
import com.vtiger.webElementsRepo.ContactPage;
import com.vtiger.webElementsRepo.CreateNewContactPage;
import com.vtiger.webElementsRepo.CreateNewOrganizationPage;
import com.vtiger.webElementsRepo.OrganizationInformationPage;
import com.vtiger.webElementsRepo.OrganizationPage;
import com.vtiger.webElementsRepo.SearchOrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsWithOrganizationsTest extends BaseClass{
	
	ContactPage contact ;
	CreateNewContactPage createNewContact ;
	ContactInformationPage contactInformationPage ;
	String lastName ;
	String organizationvalue;
	SearchOrganizationPage searchOrganizationPage;
	OrganizationPage organization;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	
	@Test
	public void createContactsWithOrganizationsTest(){
		
		contact = new ContactPage(driver);
		createNewContact = new CreateNewContactPage(driver);
		contactInformationPage = new ContactInformationPage(driver);
		searchOrganizationPage = new SearchOrganizationPage(driver);
		organization = new OrganizationPage(driver);
		createNewOrganizationPage = new CreateNewOrganizationPage(driver);
		organizationInformationPage = new OrganizationInformationPage(driver);
		
		lastName=WorkbookLibrary.getDataFromExcel("Contact", 4, 2)+randomNumber;
		
		javalibrary.printStatement(lastName);

		organizationvalue=WorkbookLibrary.getDataFromExcel("Contact", 4, 1)+randomNumber;
		javalibrary.printStatement(organizationvalue);
		
		
		home.clickOrganizations(driver);
		organization.createOrganizationImg();
		createNewOrganizationPage.enterOrganizationNameAndSave(organizationvalue);
		
		seleniumLibrary.waitUntilElementVisible(organizationInformationPage.getOrganizationNameInformation(seleniumLibrary));
		
		home.clickContacts(driver);
		
		contact.createContactImg();
		
		createNewContact.enterContactNameAndSwitchToSearchOrganization(lastName, driver);
		
		searchOrganizationPage.searchOrganizationAndSelect(organizationvalue, driver);
	
		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver, "Contacts");
		
		createNewContact.saveContact();
		
		seleniumLibrary.waitUntilElementVisible(contactInformationPage.getContactNameInformation(seleniumLibrary));
	
		String actualLastName= contactInformationPage.getContactLastName();
		
		javalibrary.assertionThroughIfCondition(actualLastName, lastName, "Contact");

	}

}