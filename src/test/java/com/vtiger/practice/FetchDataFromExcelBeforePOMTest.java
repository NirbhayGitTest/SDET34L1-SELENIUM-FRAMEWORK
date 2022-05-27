package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import com.sdet34l1.genericLibrary.IallPathDataLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;

public class FetchDataFromExcelBeforePOMTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		JavaLibrary jLib = new JavaLibrary();
		
//		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
//		Workbook book = WorkbookFactory.create(fis1);
//		Sheet sheet = book.getSheet("Document");
//		Row row = sheet.getRow(10);
//		Cell cell = row.getCell(0);
		
		WorkbookLibrary.openExcel(IallPathDataLibrary.EXCELFILEPATH);
		
		//By toString() method
		//String data = cell.toString();
		//String data = cell.getStringCellValue();	It will not work bcoz it is for string only , not numbers
		//System.out.println(data);
		
		
		//By DataFormatter class 
		//advantages :=>
		//					accepts both string and numeric value
		//					print the numeric value as it is (no decimal)
		//DataFormatter format = new DataFormatter();
		//String data = format.formatCellValue(cell);
		
		Cell cell = WorkbookLibrary.getNumericDataFromExcel("Document", 10, 0);
		
		String data = WorkbookLibrary.dataFormatter(cell);
		
		jLib.printStatement(data);
		
		//book.close();
		WorkbookLibrary.closeExcel();
		
	}

}
