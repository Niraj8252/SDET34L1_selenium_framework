package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class getDataFromEcelForLogIn {
	
	public static void main(String[] args) throws IOException  {

		JavaLibrary javaLibrary=new JavaLibrary();
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.PROPERTYFILEPATH);
		ExcelDataLibrary.openExcel(IconstantPathLibrary.EXCELFILEPATH);
		String timeout=PropertyFileDataLibrary.getDataFromPropertyFile("timeout");
		long longTimeOut=javaLibrary.stringConvertToLong(timeout);
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet("Logindata");
		String[][] arr=new String[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for(int i=1;i<sh.getLastRowNum();i++)
		
		{
			for(int j=0;j<sh.getRow(i).getLastCellNum();j++)
			{
				arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
			String username=arr[i][0];
			String password=arr[i][1];
				
		
		
			WebDriver driver=null;
			switch(PropertyFileDataLibrary.getDataFromPropertyFile("browser"))
			{
			case "chrome":
				{
					WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				break;
				}
			case "Edge":
				{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				break;
				}
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut,TimeUnit.SECONDS);
		driver.get(PropertyFileDataLibrary.getDataFromPropertyFile("url"));
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		if(driver.getTitle().contains("Home"))
		{
			WebElement a=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions act=new Actions(driver);
			act.moveToElement(a).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			book.close();
			driver.quit();
			System.out.println(username);
			System.out.println(password);
			System.out.println("logined succesfully");
			break;
		}	
		System.out.println(username);
		System.out.println(password);
		System.out.println("login failed");
		driver.quit();
		}
		
	}

}


