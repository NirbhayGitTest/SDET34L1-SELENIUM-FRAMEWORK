package com.vtiger.documentTest;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
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
import com.vtiger.webElementsRepo.DocumentInformationPage;
import com.vtiger.webElementsRepo.DocumentsPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest extends BaseClass{
	
	DocumentsPage document;
	CreateNewDocumentPage createNewDocument;
	DocumentInformationPage documentInformationPage;
	
	String docName;
	String docPath;
	String docDescription;
	
	@Test(groups = "regression")
	public void createDocumentTest() {
		
	
		
		docName = WorkbookLibrary.getDataFromExcel("Document", 2, 1) + randomNumber;
		docPath = WorkbookLibrary.getDataFromExcel("Document", 2, 2);
		docDescription = WorkbookLibrary.getDataFromExcel("Document", 2, 3);
		
		
		document = new DocumentsPage(driver);
		createNewDocument = new CreateNewDocumentPage(driver);
		documentInformationPage = new DocumentInformationPage(driver);
		
		home.clickDocuments(driver);
		
		document.createDocumentImg();
		
		createNewDocument.enterDocumentTitleName(docName);

		createNewDocument.enterDocumentDescription(driver, docDescription);

		createNewDocument.boldAndItalic();

		createNewDocument.chooseFile(docPath);
	
		createNewDocument.saveDocument();
		
		seleniumLibrary.waitUntilElementVisible(documentInformationPage.getDocumentNameInformation(seleniumLibrary));

		javalibrary.assertionThroughIfCondition(documentInformationPage.getDocumentTitleName(), docName, "document");
		
		javalibrary.assertionThroughIfCondition(documentInformationPage.getDocumentDescription(), docDescription, "document description");
		
	}

}
