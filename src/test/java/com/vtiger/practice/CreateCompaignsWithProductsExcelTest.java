package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class CreateCompaignsWithProductsExcelTest {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		JavaLibrary javaLibrary=new JavaLibrary();
		//fetch data to property file
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
		Sheet sh = book.getSheet("Compaign");
		Row row = sh.getRow(5);
		Cell cell = row.getCell(2);
	   String compaignname=cell.getStringCellValue()+num;
	   System.out.println(compaignname);
	   Row row1 = sh.getRow(5);
	 		Cell cell1 = row1.getCell(1);
	 		  String productvalue=cell1.getStringCellValue()+num;
	 		  System.out.println(productvalue);
	 	   
	 	  
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
	 		//step02----->login the app
	 		driver.findElement(By.name("user_name")).sendKeys(username);
	 		driver.findElement(By.name("user_password")).sendKeys(password);
	 		driver.findElement(By.id("submitButton")).click();
	 		if(driver.getTitle().contains("Home"))
	 		{
	 			book.getSheet("Compaign").getRow(11).createCell(5).setCellValue("Home page is Displayed");
	 			book.getSheet("Compaign").getRow(11).createCell(6).setCellValue("pass");
	 		}
	 		//step02----->click product
	 		driver.findElement(By.linkText("Products")).click();
	 		if(driver.getTitle().contains("Products"))
	 		{
	 			book.getSheet("Compaign").getRow(12).createCell(5).setCellValue("product page is Displayed");
	 			book.getSheet("Compaign").getRow(12).createCell(6).setCellValue("pass");
	 		}
	 		
			driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
			//step03------>click create product
			if(driver.getTitle().contains("Products"))
			{
				book.getSheet("Compaign").getRow(13).createCell(5).setCellValue("Create product page is Displayed");
				book.getSheet("Compaign").getRow(13).createCell(6).setCellValue("pass");
			}
			//step04---->enter mendatory fields and save
			driver.findElement(By.name("productname")).sendKeys(productvalue);
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			if(driver.getCurrentUrl().contains("Products&record"))
			{
				book.getSheet("Compaign").getRow(14).createCell(5).setCellValue("created product is Displayed");
				book.getSheet("Compaign").getRow(14).createCell(6).setCellValue("pass");
			}
	 		
	 		Thread.sleep(2000);
	 		WebElement more = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
	 		Actions act = new Actions(driver);
	 		act.moveToElement(more).perform();
	 		//step05----->click on compaign
	 		driver.findElement(By.linkText("Campaigns")).click();
	 		if(driver.getTitle().contains("Campaigns"))
	 		{
	 			book.getSheet("Compaign").getRow(15).createCell(5).setCellValue("Campaigns page is Displayed");
	 			book.getSheet("Compaign").getRow(15).createCell(6).setCellValue("pass");
	 		}
	 		//step06---->click on create compaign
	 		driver.findElement(By.cssSelector("[alt='Create Campaign...']")).click();
	 		if(driver.getTitle().contains("Campaigns"))
	 		{
	 			book.getSheet("Compaign").getRow(16).createCell(5).setCellValue("Created compaigns page is Displayed");
	 			book.getSheet("Compaign").getRow(16).createCell(6).setCellValue("pass");
	 		}
	 		driver.findElement(By.name("campaignname")).sendKeys(compaignname);
	 		//step07----->click on add product icon
	 		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
	 		
	 		 Set<String> allid = driver.getWindowHandles();
	 		 for(String id:allid)
	 		 {
	 			 driver.switchTo().window(id);
	 			 if(driver.getTitle().contains("Products&action"))
	 			 {
	 				break;
	 				
	 			 } 
	 		 }
	 		if(driver.getTitle().contains("Products&action"))
	 		{
	 			book.getSheet("Compaign").getRow(17).createCell(5).setCellValue("Product list page is Displayed");
	 			book.getSheet("Compaign").getRow(17).createCell(6).setCellValue("pass");
	 		}
	 		//step08------>search and select product
	 		driver.findElement(By.id("search_txt")).sendKeys(productvalue);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[.='"+productvalue+"']")).click();
//				if(driver.getCurrentUrl().contains("Campaigns&action"))
//				{
//					book.getSheet("Compaign").getRow(18).createCell(5).setCellValue("created compaign page is Displayed");
//					book.getSheet("Compaign").getRow(18).createCell(6).setCellValue("pass");
//				}
//	 		
				 Set<String> allid2 = driver.getWindowHandles();
		 		 for(String id2:allid2)
		 		 {
		 			 driver.switchTo().window(id2);
		 			 if(driver.getTitle().contains("Campaigns&action"))
		 			 {
		 				break;
		 				
		 			 } 
		 		 }
		 		 //step09---->compaign created
	 		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	 		if(driver.getCurrentUrl().contains("DetailView&module"))
	 		{
	 			book.getSheet("Compaign").getRow(19).createCell(5).setCellValue("Info page is Displayed");
	 			book.getSheet("Compaign").getRow(19).createCell(6).setCellValue("pass");
	 		}
	 		//step10----->validate
	 		WebElement actualname= driver.findElement(By.id("dtlview_Campaign Name"));
	 		if(actualname.getText().equalsIgnoreCase(compaignname))
	 		{
	 			System.out.println("Compaigns created successfully");
	 			System.out.println("Test case pass");
	 		}
	 		if(driver.getCurrentUrl().contains("DetailView&module") && actualname.getText().equalsIgnoreCase(compaignname))
	 		{
	 			book.getSheet("Compaign").getRow(20).createCell(5).setCellValue("same campaign created");
	 			book.getSheet("Compaign").getRow(20).createCell(6).setCellValue("pass");
	 		}
	 		//step11---->logout
	 		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			act.moveToElement(administrator).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			if(driver.getCurrentUrl().contains("Login&module"))
			{
				book.getSheet("Compaign").getRow(21).createCell(5).setCellValue("Login page is Displayed");
				book.getSheet("Compaign").getRow(21).createCell(6).setCellValue("pass");
			}
	
			FileOutputStream fos = new FileOutputStream("./src/test/resources/Testdata.xlsx");
			book.write(fos);
			book.close();
			driver.quit();
	}
}
