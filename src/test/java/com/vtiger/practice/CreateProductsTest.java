package com.vtiger.practice;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsTest {
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
		
		switch(browser)
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			 break;
			 
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			System.out.println("provide specific browser key");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		String actualproduct="ecommerce";
		driver.findElement(By.name("productname")).sendKeys(actualproduct);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String expectedproduct=driver.findElement(By.id("dtlview_Product Name")).getText();
		if(actualproduct.contains(expectedproduct))	
		{
			System.out.println("product created");
		}
		else
		{
			System.out.println("product creation failed");
		}
		
		WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();	
	}
}
