package com.vtiger.productsTest;

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
import com.vtiger.webElementsRepo.CreateNewProductPage;
import com.vtiger.webElementsRepo.HomePage;
import com.vtiger.webElementsRepo.LoginPage;
import com.vtiger.webElementsRepo.ProductInformationPage;
import com.vtiger.webElementsRepo.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsTest extends BaseClass{
	
	ProductPage product;
	CreateNewProductPage createNewProduct;
	ProductInformationPage productInformationPage;
	
	@Test(groups="smoke")
	public void createProductsTest() {
		
		product = new ProductPage(driver);
		createNewProduct = new CreateNewProductPage(driver);
		productInformationPage = new ProductInformationPage(driver);
		home.clickProducts(driver);
		product.createProductImg();
		String productName = WorkbookLibrary.getDataFromExcel("Product", 2, 1)+ randomNumber;
		createNewProduct.enterProductNameAndSave(productName);
		seleniumLibrary.waitUntilElementVisible(productInformationPage.getProductNameInformation(seleniumLibrary));
		javalibrary.assertionThroughIfCondition(productInformationPage.getProductName(), productName, "product");
		
	}

}
