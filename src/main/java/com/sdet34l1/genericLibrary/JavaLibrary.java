package com.sdet34l1.genericLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains java spacific methods
 * @author Niraj
 *i am conflict
 */

public class JavaLibrary {
	
	/**
	 * This method is used to convert String value to long data type
	 * @param value
	 * @return
	 */
	
	public long stringConvertToLong(String value)
	{
		return Long.parseLong(value);
	}
	
	/**
	 * This method is used to generate random number
	 * @param limit
	 * @return
	 */
	public int generateRandomNum(int limit)
	{
		Random r = new Random();
		 return r.nextInt(limit);
	}

	/**
	 * This method is used to print the statements
	 * @param message
	 */
	public void printStatement(String message)
	{
		System.out.println(message);
	}
	
	/**
	 * This method is used to validate the testcase
	 * @param actualResult
	 * @param expectedResult
	 * @param testCaseName
	 */
	public void assertionThroughIfCondition(String actualResult, String expectedResult, String testCaseName) {
		System.out.println("=============*******===========");
		System.out.println("Actual result : "+actualResult);
		System.out.println("Expected result : "+expectedResult);
		System.out.println("=============*******===========");

		if (actualResult.contains(expectedResult)) {
			System.out.println(testCaseName + " created successfully");
			System.out.println("Test case pass");
		}
	}
	
	/**
	 * This method is used for custum wait (thread.sleep)
	 * @param element
	 * @param pollingtime
	 * @param duration
	 */
	public void customWait(WebElement element, long pollingtime,int duration)
	{
		int count=0;
		while(count<=duration)
		try {
			element.click();
			break;
		}
		catch(Exception e) {
			try {
				Thread.sleep(pollingtime);
				count++;
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	/**
	 * This method is used to change date and time formate
	 * @return
	 */
	public String dateTimeInFormat()
	{
		return new SimpleDateFormat("yyyy_MM_dd_mm_sss").format(new Date());
	}
}
