package com.vtiger.compaignTest;

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
import com.vtiger.webElementsRepo.CampaignInformationPage;
import com.vtiger.webElementsRepo.CampaignPage;
import com.vtiger.webElementsRepo.CreateNewCampaignPage;
import com.vtiger.webElementsRepo.CreateNewProductPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.ProductInformationPage;
import com.vtiger.webElementsRepo.ProductPage;
import com.vtiger.webElementsRepo.SearchProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCompaignsWithProductTest extends BaseClass{


	String campaignname;
	String productname ;
	ProductPage product;
	CreateNewProductPage createNewProduct;
	SearchProductsPage searchProducts;
	CampaignPage campaign;
	CreateNewCampaignPage createNewCampaign;
	CampaignInformationPage campaignInformation;	
	ProductInformationPage productInformationPage;

	@Test
	public void createCompaignsWithProductsTest()
	{
		campaignname = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;		
		javalibrary.printStatement(campaignname);
		productname = WorkbookLibrary.getDataFromExcel("Campaign", 4, 1) + randomNumber;
		javalibrary.printStatement(productname);

		campaign = new CampaignPage(driver);
		createNewCampaign = new CreateNewCampaignPage(driver);
		campaignInformation = new CampaignInformationPage(driver);
		productInformationPage = new ProductInformationPage(driver);
		product = new ProductPage(driver);
		createNewProduct = new CreateNewProductPage(driver);
		searchProducts = new SearchProductsPage(driver);



		home.clickProducts(driver);

		product.createProductImg();

		createNewProduct.enterProductNameAndSave(productname);

		seleniumLibrary.waitUntilElementVisible(productInformationPage.getProductNameInformation(seleniumLibrary));

		home.clickCampaign(seleniumLibrary);

		campaign.createCampaignImg();

		createNewCampaign.enterCampaignNameAndSwitchToSearchProduct(campaignname, driver);

		searchProducts.searchProductAndSelect(productname, driver);

		SeleniumWebDriverLibrary.switchToWindowBasedOnTitle(driver,"Campaigns");

		createNewCampaign.saveCampaign();
		
		seleniumLibrary.waitUntilElementVisible(campaignInformation.getCampaignNameInformation(seleniumLibrary));

		javalibrary.assertionThroughIfCondition(campaignInformation.getCampaignName(), campaignname,"campaign with product");
		javalibrary.assertionThroughIfCondition(campaignInformation.getProductName(), productname,"correct product name");

	}


}