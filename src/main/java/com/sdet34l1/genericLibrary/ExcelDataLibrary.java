package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains excel file specific common methods
 * @author Niraj
 *
 */
public class ExcelDataLibrary {
	
	static Workbook book;
	
	/**
	 * This method is used to fetch the data from excel
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
	 * This methos is used to open the execlsheet
	 * @param filePath
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static void openExcel(String filePath)  
	{
		FileInputStream fisExcel = null;
		try {
			fisExcel = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			book=WorkbookFactory.create(fisExcel);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to close the excelsheet
	 */
	public static void closeExcel() 
	{
		try {
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * THis method is used to write data in to excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @param msg
	 */
			
	public static void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber,String msg)
	{
		 book.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(msg);
		
	}
	
	/**
	 * This method is used to fetch the multiple data from excel
	 * @param sheetName
	 * @return
	 */
	public static String[][] getMultipleDataFromExcel(String sheetName)
	{
		
		Sheet sh = book.getSheet(sheetName);
		String [][] arr= new String[sh.getLastRowNum()+1][sh.getRow(0).getLastCellNum()];
		for(int i=0; i<sh.getLastRowNum()+1; i++)
		{
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++)
			{
				arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(arr[i][j]);
			}
		}
		
		return arr;
	}
	
	/**
	 * This method is used to send in excel
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeDataInExcel(String filePath) 
	{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			book.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
