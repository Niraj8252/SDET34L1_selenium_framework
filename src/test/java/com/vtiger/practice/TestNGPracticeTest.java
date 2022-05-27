package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNGPracticeTest extends TestNGAnnotationTest {
	
	@Test
	public void testNGPractice1Test()
	{
		System.out.println("Test1 method running");
	}
	
	@Test
	public void testNGPractice2Test()
	{
		Reporter.log("Test2 method running", true);
		
	}
	
	@Test
	public void testNGPractice3Test()
	{
		Reporter.log("Test3 method running",true);
	}
	@Test
	public void testNGPractice4Test()
	{
		
		System.out.println("Test4 method running");
	}
	@Test
	public void testNGPractice5Test()
	{
		System.out.println("Test5 method running"); 
	}

}
