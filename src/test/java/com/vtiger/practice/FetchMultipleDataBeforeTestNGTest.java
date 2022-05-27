package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

public class FetchMultipleDataBeforeTestNGTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		
		FileInputStream fis = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet("loginData");
		
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		WorkbookLibrary.getDataFromExcel("loginData", 0, 0);
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			for(int j=0;j<sh.getRow(i).getLastCellNum();j++)
			{
				jLib.printStatement(sh.getRow(i).getCell(j).getStringCellValue());
			}
		}
		WorkbookLibrary.closeExcel();
	}
}

