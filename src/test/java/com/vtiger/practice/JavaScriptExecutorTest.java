package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutorTest {
	public static void main(String[] args) throws IOException {
		WebDriverDataLibrary webDriverDataLibrary=new WebDriverDataLibrary(null);
		JavaLibrary javaLibrary=new JavaLibrary();

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		WebDriverDataLibrary.maximizebrowser(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(10, driver);
		WebDriverDataLibrary.initializejse(driver);
		webDriverDataLibrary.initializeAction(driver);
		WebDriverDataLibrary.navigateAppThroughJse("http://localhost:8888");
		
		WebDriverDataLibrary.enterDataThroughjse(driver.findElement(By.name("user_name")), "admin");
		WebDriverDataLibrary.enterDataThroughjse(driver.findElement(By.name("user_password")), "root");
		WebDriverDataLibrary.clickThoughJse(driver.findElement(By.id("submitButton")));
		WebDriverDataLibrary.scrollTillElement(driver.findElement(By.xpath("//b[contains(.,'Key Metrics')]")));
		//WebDriverDataLibrary.scrollToSpecificHeight("300");
		
//		JavascriptExecutor jse=(JavascriptExecutor)driver;
//		jse.executeScript("window.location='http://localhost:8888'");
//		jse.executeScript("arguments[0].value=arguments[1]", driver.findElement(By.name("user_name")), "admin");
//		jse.executeScript("arguments[0].value=arguments[1]",driver.findElement(By.name("user_password")), "root");
//		jse.executeScript("arguments[0].click();",driver.findElement(By.id("submitButton")));
//		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//		jse.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//b[contains(.,'Key Metrics')]")));
	
	
		String fileName=new JavaScriptExecutorTest().getClass().getName();
		WebDriverDataLibrary.takeScreenShot(fileName, driver);
		WebDriverDataLibrary.scrollToSpecificHeight("-300");
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverDataLibrary.mouseOverOnElement(administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		WebDriverDataLibrary.quitBrowser(driver);
	
	}

}
