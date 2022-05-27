package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleDataTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet("Logindata");
		String [][] arr= new String[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for(int i=0; i<sh.getLastRowNum(); i++)
		{
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++)
			{
				arr[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
				System.out.println(arr[i][j] );
			}
		}
	}

}
