package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
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

import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsWithOrganizationsTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
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
		
		//fetch data to excel file
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook book=WorkbookFactory.create(fis1);
		Sheet sh = book.getSheet("Contacts");
		Row row = sh.getRow(5);
		Cell cell = row.getCell(2);
	   String lastnamevalue=cell.getStringCellValue()+num;
	   System.out.println(lastnamevalue);
	   Row row1 = sh.getRow(5);
		Cell cell1 = row1.getCell(1);
		  String organizationvalue2=cell1.getStringCellValue()+num;
		  System.out.println(organizationvalue2);
	    book.close();
	  
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys(organizationvalue2);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastnamevalue);
		driver.findElement(By.xpath("//td[contains(.,'Organization Name ') and @class='dvtCellLabel']/following-sibling::td/img")).click();
		
		//String mainid = driver.getWindowHandle();
		Set<String> allid = driver.getWindowHandles();
		for(String id:allid)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains("Accounts&action"))
			{
				break;
			}
		}
			driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(organizationvalue2);
			driver.findElement(By.xpath("//input[@name='search']")).click();
			driver.findElement(By.xpath("//a[.='"+organizationvalue2+"']")).click();
			
			Set<String> allid2 = driver.getWindowHandles();
			for(String id2:allid2)
			{
				driver.switchTo().window(id2);
				if(driver.getTitle().contains("Contacts&action"))
				{
					break;
				}
			}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement actualLastName= driver.findElement(By.id("dtlview_Last Name"));
		
		if(actualLastName.getText().equalsIgnoreCase(lastnamevalue))
		{
					System.out.println("Contact created successfully");
					System.out.println("TC pass");
		}
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
