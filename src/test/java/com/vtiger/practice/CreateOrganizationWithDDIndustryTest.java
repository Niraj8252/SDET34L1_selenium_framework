package com.vtiger.practice;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationWithDDIndustryTest {
	public static void main(String[] args) throws Throwable {
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
		int num = javaLibrary.generateRandomNum(1000);
		
		//Excel file
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook book=WorkbookFactory.create(fisExcel);
		Sheet sh = book.getSheet("Organizations");
		Row row = sh.getRow(2);
		Cell cell = row.getCell(1);	
	String organizationname=cell.getStringCellValue()+num;
	book.close();
	System.out.println(organizationname);
	
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
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	
	
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys(organizationname);
		
		//dropdown
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		industry.click();
		Select s = new Select(industry);
		s.selectByValue("Education");
		industry.click();
		
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		s=new Select(type);
		s.selectByValue("Press");
		type.click();
		
		WebElement rating = driver.findElement(By.xpath("//select[@name='rating']"));
		s= new Select(rating);
		s.selectByValue("Active");
		rating.click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actual_name =driver.findElement(By.className("dvHeaderText")).getText();  
		System.out.println(actual_name);
		if(actual_name.contains(organizationname))
		{
			System.out.println("Test Case pass");
		}
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
	

}
