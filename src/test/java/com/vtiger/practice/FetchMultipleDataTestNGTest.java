package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

public class FetchMultipleDataTestNGTest {
	
	@Test(dataProvider="fetchMultipleData")
	public void getMultipleDataExcelTestNgTest(String username, String password)
	{
		Reporter.log(username+ "--------->"+ password, true);
	}
	
	@DataProvider
	public String[][] fetchMultipleData() throws EncryptedDocumentException, IOException
	{
	
			WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		return WorkbookLibrary.getMultipleDataFromExcel("MultipleloginData");
	}
	
	
}

