package com.vtiger.practice;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestNGAnnotationTest {
	

	
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Before suite1");
	}
	
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("After suite1");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Before Test1");
	}
	@AfterTest
	public void afterTest()
	{
		System.out.println("After Test1");
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Before Method1");
	}
	@BeforeMethod
	public void beforeMethod1()
	{
		System.out.println("Before Method2");
	}

	@AfterMethod
	public void afterMethod()
	{
		System.out.println("After Method1 ");
	}

}
