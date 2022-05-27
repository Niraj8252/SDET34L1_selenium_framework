package com.sdet34l1.genericLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer{

	
	int count=0;
	int maxRetry=5;
	@Override
	public boolean retry(ITestResult result) {
		
		if (count<maxRetry)
		{
			count++;
			return true;
		}
		return false;
	}

}
