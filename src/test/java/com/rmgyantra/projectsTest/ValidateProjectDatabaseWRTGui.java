package com.rmgyantra.projectsTest;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sdet34l1.genericLibrary.DataBaseLibrary;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectDatabaseWRTGui {
	public static void main(String[] args) throws IOException, SQLException {
		
		JavaLibrary javaLibrary = new JavaLibrary();
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.RMGYANTRA_PROPERTYFILEPATH);
		ExcelDataLibrary.openExcel(IconstantPathLibrary.RMGYANTRA_EXCELFILEPATH);
		
		int randomNumber=javaLibrary.generateRandomNum(1000);
		
		String timeout = PropertyFileDataLibrary.getDataFromPropertyFile("timeout");
		long longTimeout = javaLibrary.stringConvertToLong(timeout);
		String userName = PropertyFileDataLibrary.getDataFromPropertyFile("dbuserName");
		System.out.println(userName);
		String password = PropertyFileDataLibrary.getDataFromPropertyFile("dbPassword");
		System.out.println(password);
		String dbName = PropertyFileDataLibrary.getDataFromPropertyFile("dbName");
		System.out.println(dbName);
		String rmguserName = PropertyFileDataLibrary.getDataFromPropertyFile("rmgUserName");
		System.out.println(rmguserName);
		String rmgPassword = PropertyFileDataLibrary.getDataFromPropertyFile("rmgPassword");
		System.out.println(rmgPassword);
		
		String projectName=ExcelDataLibrary.getDataFromExcel("Projects", 1, 1)+randomNumber;
		System.out.println(projectName);
		String createdBy=ExcelDataLibrary.getDataFromExcel("Projects", 1, 2);
		System.out.println(createdBy);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		WebDriverDataLibrary.navigateApp(PropertyFileDataLibrary.getDataFromPropertyFile("rmgyantraurl"), driver);
		
		WebDriverDataLibrary.maximizebrowser(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeout, driver);
		driver.findElement(By.id("usernmae")).sendKeys(rmguserName,Keys.TAB,rmgPassword,Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		
		JavascriptExecutor js =(JavascriptExecutor)driver;
		 js.executeScript("document.getElementsByName('teamSize').values='5'");
		 
		 driver.findElement(By.name("createdBy")).sendKeys(createdBy);
		 WebElement projectStatusDropdown = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
//		 Select s = new Select(projectStatusDropdown);
//		 s.selectByVisibleText("On Goging");
		 WebDriverDataLibrary.slectListBox("On Goging", projectStatusDropdown);
		 driver.findElement(By.cssSelector("[value='Add Project']")).click();
		 
//		 String expected=driver.findElement(By.className("table-hover")).getText();
//		 System.out.println(expected);
		 
		 DataBaseLibrary.openDataBaseConnection(IconstantPathLibrary.DATABASEURL,userName , password);
		// DataBaseLibrary.getDataFromDataBase("select * from project", "project_name");
		boolean status = DataBaseLibrary.validateDataInDataBase("select * from project", "project_name", projectName);
		 if(status == true)
		 {
			 System.out.println("TC pass");
		 }
		 else {
			 System.out.println("TC failed");
		 }
		 	DataBaseLibrary.closeDataBaseConnection();
//		
			WebDriverDataLibrary.quitBrowser(driver);
//	}
	}
}
