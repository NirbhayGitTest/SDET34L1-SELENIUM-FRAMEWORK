package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcelBeforeGenericTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		Sheet sheet = book.getSheet("Document");
		Row row = sheet.getRow(10);
		Cell cell = row.getCell(0);
		
		//By toString() method
		//String data = cell.toString();
//		String data = cell.getStringCellValue();	It will not work bcoz it is for string only , not numbers
		//System.out.println(data);
		
		
		//By DataFormatter class 
		//advantages :=>
		//					accepts both string and numeric value
		//					print the numeric value as it is (no decimal)
		DataFormatter format = new DataFormatter();
		String data = format.formatCellValue(cell);
		System.out.println(data);
		
		book.close();
		
	}

}
