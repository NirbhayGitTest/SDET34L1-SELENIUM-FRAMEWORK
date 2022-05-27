package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to maintain all Excel specific common methods
 * @author Nirbhay The Omen
 *
 */
public class WorkbookLibrary {
	
	static Workbook book;
	
	/**
	 * This method is used to open the excel
	 * @param filePath
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static void openExcel(String filePath) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fisExcel = new FileInputStream(filePath);
		 book = WorkbookFactory.create(fisExcel);
	}
	
	/**
	 * This method is used to fetch the data from the excel sheet
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public static String getDataFromExcel(String sheetName, int rowNumber, int cellNumber)
	{
		String data = book.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;
	}
	
	/**
	 * This method is used to fetch Multiple data from Excel
	 * @param sheetName
	 * @return
	 */
	public static String[][] getMultipleDataFromExcel(String sheetName)
	{
		Sheet sh = book.getSheet(sheetName);
		String [][] arr = new String[sh.getLastRowNum()+1][sh.getRow(0).getLastCellNum()];
		for(int i=0; i<sh.getLastRowNum()+1; i++)
		{
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++)
			{
				arr[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(arr[i][j]);
			}
		}
		return arr;
	}
	
	
	/**
	 * This method is used to fetch the Numeric data from the excel sheet
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public static Cell getNumericDataFromExcel(String sheetName, int rowNumber, int cellNumber)
	{
		Cell data = book.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber);
		return data;
	}
	
	/**
	 * This method is used to convert cell value(numeric or char ,both)  to String 
	 * @param cell
	 * @return
	 */
	public static String dataFormatter(Cell cell)
	{
		DataFormatter dataFormatter = new DataFormatter();
		String value = dataFormatter.formatCellValue(cell);
		return value;
	}
	
	
	/**
	 * This method is used to Close Excel Workbook
	 * @throws IOException
	 */
	public static void closeExcel() throws IOException
	{
		book.close();
	}
	
	
	/**
	 * This method is used to Create Cell and pass the Message into it
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param message
	 */
	public static void setdataIntoexcel (String sheetName, int rowNumber, int cellNumber, String message)
	{
		book.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(message);
	}
	
	
	/**
	 * This method is used to Write the Data or Message and Save
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeDataInExcel (String filePath) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filePath);
		book.write(fos);
	}

}
