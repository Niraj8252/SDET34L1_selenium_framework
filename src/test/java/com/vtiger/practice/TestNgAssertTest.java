package com.vtiger.practice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Listeners(com.sdet34l1.genericLibrary.ListenerImplementation.class)
public class TestNgAssertTest {
	SoftAssert softAssert = new SoftAssert();
	@Test(retryAnalyzer=com.sdet34l1.genericLibrary.RetryAnalyserImplementation.class)
	public void testNGPractice2Test()
	{
		Reporter.log("a-practice", true);
		Reporter.log("b-practice", true);
		Reporter.log("c-practice", true);
		Assert.fail("Expected Error");
		softAssert.assertEquals("abs", "mnd");
		Reporter.log("d-practice", true);
		softAssert.assertEquals("123", "456");
		Reporter.log("e-practice", true);	
	}
	
	@Test
	public void testNGPractice3Test()
	{
		Reporter.log("f-practice", true);
		Reporter.log("g-practice", true);
		Reporter.log("h-practice", true);
		softAssert.assertAll();
		Reporter.log("i-practice", true);
		Reporter.log("j-practice", true);

	}
	
	@Test
	public void testNGPractice4Test()
	{
		Reporter.log("a-practice", true);
		Reporter.log("b-practice", true);
		Reporter.log("c-practice", true);
		Reporter.log("d-practice", true);
		Reporter.log("e-practice", true);

	}


}
