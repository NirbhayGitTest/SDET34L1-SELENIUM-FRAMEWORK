package com.vtiger.contactsTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.ContactInformationPage;
import com.vtiger.webElementsRepo.ContactPage;
import com.vtiger.webElementsRepo.CreateNewContactPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class CreateContactsTest extends BaseClass{
	

	ContactPage contact ;
	CreateNewContactPage createNewContact ;
	ContactInformationPage contactInformationPage ;
	String lastName ;
	
	
	@Test(groups = "sanity" , description = "testng :- CreateContactsTest")
	@Description("Description:-CreateContactsTest")
	@Epic("Epic:-CreateContactsTest")
	@Story("Story:-CreateContactsTest")
	@Step("Step:-CreateContactsTest")
	@Severity(SeverityLevel.BLOCKER)
	public void createContactsTest() {

		contact = new ContactPage(driver);
		createNewContact = new CreateNewContactPage(driver);
		contactInformationPage = new ContactInformationPage(driver);
		
		home.clickContacts(driver);
		
		contact.createContactImg();
		
		lastName = WorkbookLibrary.getDataFromExcel("Contact", 2, 1)+randomNumber;
		
		createNewContact.enterContactLastNameAndSave(lastName);
		
		seleniumLibrary.waitUntilElementVisible(contactInformationPage.getContactNameInformation(seleniumLibrary));
		
		javalibrary.assertionThroughIfCondition(contactInformationPage.getContactLastName(), lastName, "Contact last name");
		
	}

}
