package com.vtiger.practice;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class CreateDocumentTest {
	public static void main(String[] args) throws IOException, AWTException {
		
		
		JavaLibrary javaLibrary=new JavaLibrary();
		//fetch data from property file
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
		
		//fetch data from excelsheet
		FileInputStream excelfis=new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook book = WorkbookFactory.create(excelfis);
		Sheet sh = book.getSheet("Documents");
		Row row = sh.getRow(2);
		Cell cell = row.getCell(1);
		String documenttitle = cell.getStringCellValue()+num;
		Row row1 = sh.getRow(2);
		Cell cell1 = row1.getCell(2);
		String notes = cell1.getStringCellValue();
		System.out.println(notes);
		Row row2 = sh.getRow(2);
		Cell cell2 = row2.getCell(3);
		String path=cell2.getStringCellValue();
		System.out.println(path);
		book.close();
		System.out.println(documenttitle);
		
		WebDriver driver=null;
		switch("browser")
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
		
 		driver.manage().window().maximize();
 		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
 		driver.get(url);
 		driver.findElement(By.name("user_name")).sendKeys(username);
 		driver.findElement(By.name("user_password")).sendKeys(password);
 		driver.findElement(By.id("submitButton")).click();
 		
 		driver.findElement(By.linkText("Documents")).click();
 		driver.findElement(By.xpath("//img[@alt='Create Document...']")).click();
 		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(documenttitle);
 		
// 		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
// 		driver.switchTo().frame(frame);
 		driver.switchTo().frame(0);
 		//frame.sendKeys(notes);
 		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(notes,Keys.CONTROL+"a");
// 		Robot robot= new Robot();
// 		robot.keyPress(KeyEvent.VK_CONTROL);
// 		robot.keyPress(KeyEvent.VK_A);
// 		robot.keyRelease(KeyEvent.VK_TAB);
// 		robot.keyRelease(KeyEvent.VK_A);
 		
 		driver.switchTo().defaultContent();
 		driver.findElement(By.xpath("//a[@id='cke_5']/span[@class='cke_icon']")).click();
		driver.findElement(By.xpath("//a[@id='cke_6']/span[@class='cke_icon']")).click();
		
		WebElement fileButton = driver.findElement(By.id("filename_I__"));
		fileButton.sendKeys(path);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		WebElement actualtitle = driver.findElement(By.id("dtlview_Title"));
		if(actualtitle.getText().equalsIgnoreCase(documenttitle))
		{
			System.out.println("doc with file upload successfully");
		}
		else
		{
			System.out.println("fail");
		}
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(administrator).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
 		
	}


}
