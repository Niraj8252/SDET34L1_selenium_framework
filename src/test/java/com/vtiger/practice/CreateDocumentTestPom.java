package com.vtiger.practice;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateNewDocumentPageAndSavePage;
import com.vtiger.elementRepository.DocumentPage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.InformationPageForDocument;
import com.vtiger.elementRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTestPom {
	public static void main(String[] args) throws IOException, AWTException {
		
		WebDriverDataLibrary webDriverDataLibrary =new WebDriverDataLibrary(null);
		JavaLibrary javaLibrary=new JavaLibrary();
		//fetch data from property file
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.PROPERTYFILEPATH);
		ExcelDataLibrary.openExcel(IconstantPathLibrary.EXCELFILEPATH);
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
		String documenttitle=ExcelDataLibrary.getDataFromExcel("Documents", 2, 1)+num;
		System.out.println(documenttitle);
		String notes=ExcelDataLibrary.getDataFromExcel("Documents", 2, 2);
		System.out.println(notes);
		String path=ExcelDataLibrary.getDataFromExcel("Documents", 2, 3);
		System.out.println(path);
		
		
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
		
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		DocumentPage documentPage = new DocumentPage(driver);
		CreateNewDocumentPageAndSavePage createDocument = new CreateNewDocumentPageAndSavePage(driver);
		InformationPageForDocument documentInfo = new InformationPageForDocument(driver);
		
		WebDriverDataLibrary.maximizebrowser(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
		WebDriverDataLibrary.navigateApp(url, driver);
		
		loginpage.loginAction(username, password);
 		
		homepage.clickDocumentsTab();
		documentPage.ducumentCreateIcon();
		createDocument.enterDocumentData(documenttitle);
 		
 		//WebElement frame = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
 		//driver.switchTo().frame(frame);
 		//frame.sendKeys(notes);
 		
 		//WebDriverDataLibrary.switchToFrame(frame, driver);
 		WebDriverDataLibrary.switchToFrame(driver, 0);
 		createDocument.sendNotes(notes);
 		//driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(notes,Keys.CONTROL+"a");

 		//driver.switchTo().defaultContent();
 		WebDriverDataLibrary.swicthBackToMainPage(driver);
 		createDocument.boldTextDucument();
 		createDocument.inclindTextDucument();

 		createDocument.filepathtDucument(path);
 		createDocument.save();
		
		javaLibrary.assertionThroughIfCondition(documentInfo.checkDocumentInformation(), documenttitle, "Documents");
//		
//		if(actualtitle.getText().equalsIgnoreCase(documenttitle))
//		{
//			System.out.println("Doc with file upload successfully");
//		}
//		else
//		{
//			System.out.println("fail");
//		}
		
		homepage.signOut(driver,webDriverDataLibrary);

		ExcelDataLibrary.closeExcel();
		WebDriverDataLibrary.quitBrowser(driver);
 		
	}


}
