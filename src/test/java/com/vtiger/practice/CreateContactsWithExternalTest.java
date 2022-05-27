package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class CreateContactsWithExternalTest {
	
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
		int num = javaLibrary.generateRandomNum(1000);
		//Excel file
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook book=WorkbookFactory.create(fisExcel);
		Sheet sh = book.getSheet("Contacts");
		Row row = sh.getRow(2);
		Cell cell = row.getCell(1);
	String value=cell.getStringCellValue()+num;
	
	System.out.println(value);
	
	
//	WebDriverManager.chromedriver().setup();
//	WebDriver driver=new ChromeDriver();
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
	
	//test step-->1 login to the app
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	if(driver.getTitle().contains("Home"))
	{
		book.getSheet("Contacts").getRow(12).createCell(5).setCellValue("Home page is Displayed");
		book.getSheet("Contacts").getRow(12).createCell(6).setCellValue("pass");
	}
	
	//test case step02---> click on contacts
	driver.findElement(By.linkText("Contacts")).click();
	
	if(driver.getTitle().contains("Contacts"))
	{
		book.getSheet("Contacts").getRow(13).createCell(5).setCellValue("Contact page is displayed");
		book.getSheet("Contacts").getRow(13).createCell(6).setCellValue("pass");
	}
	//testcase step03---->click on create contact
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	if(driver.getCurrentUrl().contains("Contacts&action"))
	{
		book.getSheet("Contacts").getRow(14).createCell(5).setCellValue("Create Contact page is displayed");
		book.getSheet("Contacts").getRow(14).createCell(6).setCellValue("pass");
	}
	//testcase step04----->enter mendatory field and click on save
	driver.findElement(By.name("lastname")).sendKeys(value);
	driver.findElement(By.className("crmButton")).click();
	if(driver.getCurrentUrl().contains("Contacts&parenttab"))
	{
		book.getSheet("Contacts").getRow(15).createCell(5).setCellValue("Contact created successfully");
		book.getSheet("Contacts").getRow(15).createCell(6).setCellValue("pass");
	}
	//testcase step05---->validat
	WebElement actualLastName= driver.findElement(By.id("dtlview_Last Name"));	
	if(actualLastName.getText().equalsIgnoreCase(value))
	{
				System.out.println("Contact created successfully");
				System.out.println("TC pass");
	}
	if(driver.getCurrentUrl().contains("DetailView&module") && actualLastName.getText().equalsIgnoreCase(value))
	{
		book.getSheet("Contacts").getRow(16).createCell(5).setCellValue("same data created");
		book.getSheet("Contacts").getRow(16).createCell(6).setCellValue("pass");
	}
	//testcase step06--->logout
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	if(driver.getCurrentUrl().contains("Login&module"))
			{
		book.getSheet("Contacts").getRow(17).createCell(5).setCellValue("login page is displyed");
		book.getSheet("Contacts").getRow(17).createCell(6).setCellValue("pass");
			}
	FileOutputStream fos = new FileOutputStream("./src/test/resources/Testdata.xlsx");
	book.write(fos);
	book.close();
	driver.quit();			
	}

}
