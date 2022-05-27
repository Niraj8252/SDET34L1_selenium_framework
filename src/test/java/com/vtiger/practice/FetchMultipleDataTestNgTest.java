package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;

public class FetchMultipleDataTestNgTest {
	
	
	@Test(dataProvider="loginData")
	public void getMultipleDataFromExcel(String username, String password)
	
	{
		Reporter.log( username+ " =====> "+ password, true);	
		}
		@DataProvider
	public String[][] loginData()
	{
		ExcelDataLibrary.openExcel(IconstantPathLibrary.EXCELFILEPATH);
		return ExcelDataLibrary.getMultipleDataFromExcel("Logindata");
	}
}
