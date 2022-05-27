package com.vtiger.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCompaignsTest {
	public static void main(String[] args) throws IOException {
		
		JavaLibrary javaLibrary=new JavaLibrary();
		//property file
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.PROPERTYFILEPATH);
		String url = PropertyFileDataLibrary.getDataFromPropertyFile("url");
		System.out.println(url);
		String username = PropertyFileDataLibrary.getDataFromPropertyFile("userName");
		System.out.println(username);
		String password = PropertyFileDataLibrary.getDataFromPropertyFile("password");
		System.out.println(password);
		String timeout = PropertyFileDataLibrary.getDataFromPropertyFile("timeout");
		System.out.println(timeout);
		String browser = PropertyFileDataLibrary.getDataFromPropertyFile("browser");
		System.out.println(browser);
		long longTimeOut=javaLibrary.stringConvertToLong(timeout);
		
		
				WebDriver driver=null;
				if(browser.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					 driver = new ChromeDriver();
				}
				else if(browser.equalsIgnoreCase("edge"))
				{
					WebDriverManager.edgedriver().setup();
					 driver = new EdgeDriver();
				}
				else if(browser.equalsIgnoreCase("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					 driver = new FirefoxDriver();
				}
//				WebDriverManager.chromedriver().setup();
//				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
				
				driver.get(url);
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				
				WebElement more =driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
				Actions act = new Actions(driver);
				act.moveToElement(more).perform();
				driver.findElement(By.linkText("Campaigns")).click();
				driver.findElement(By.cssSelector("[alt='Create Campaign...']")).click();
				String actualcompaign="Benner";
				driver.findElement(By.name("campaignname")).sendKeys(actualcompaign);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String expectedcompaign=driver.findElement(By.id("dtlview_Campaign Name")).getText();
				if(actualcompaign.contains(expectedcompaign))
				{
					System.out.println("Compaign created");
				}
				else {
					System.out.println("Compaign creation failed");
				}
				WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				act.moveToElement(administrator).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();			
	}

}
