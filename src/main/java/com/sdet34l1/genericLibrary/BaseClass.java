package com.sdet34l1.genericLibrary;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * This class is used to common activities of the scripts in the form of annotations
 * @author Niraj
 * This is the supermost class of all scripts
 * this is updated by amit
 */
public class BaseClass {
	public WebDriverDataLibrary webDriverDataLibrary;
	public String username;
	public String password;
	public String browser;
	public WebDriver driver;
	public long longTimeOut;
	public LoginPage loginpage;
	public HomePage homepage;
    public JavaLibrary javaLibrary;  
    public int randomNumber;
    public static WebDriver staticdriver;
   
	
    
    /**
     * This annotation is used to open the property file and excel file
     * @throws IOException
     */
	@BeforeSuite(groups="baseclass")
	
	public void beforeSuiteTest() throws IOException
	{
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.PROPERTYFILEPATH);
		ExcelDataLibrary.openExcel(IconstantPathLibrary.EXCELFILEPATH);

	}
	
	/**
	 * This annotation is used to fetch the data frome property file
	 * create the instance for all utilities
	 * amit update base class
	 */
	
	//@Parameters("browser")
	@BeforeClass(groups="baseclass")
	public void beforeClassTest()
	{
	    

		javaLibrary=new JavaLibrary();

		String url = PropertyFileDataLibrary.getDataFromPropertyFile("url");
		System.out.println(url);
		 username = PropertyFileDataLibrary.getDataFromPropertyFile("userName");
//		System.out.println(username);
		 password = PropertyFileDataLibrary.getDataFromPropertyFile("password");
//		System.out.println(password);
		String timeout = PropertyFileDataLibrary.getDataFromPropertyFile("timeout");
		System.out.println(timeout);
		String browser = PropertyFileDataLibrary.getDataFromPropertyFile("browser");
//		//System.out.println(browser);
//		browser = System.getProperty("BROWSER");
//		username = System.getProperty("USERNAME");
//		password = System.getProperty("PASSWORD");
		
		 longTimeOut=javaLibrary.stringConvertToLong(timeout);
		 randomNumber = javaLibrary.generateRandomNum(1000);

		 switch(browser) {
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
		 staticdriver = driver;
		webDriverDataLibrary=new WebDriverDataLibrary(driver);
		WebDriverDataLibrary.browserSetting(longTimeOut, driver);
		webDriverDataLibrary.initializeAction(driver);
 		WebDriverDataLibrary.explicitlyWait(driver, longTimeOut);

		 loginpage=new LoginPage(driver);
		 homepage = new HomePage(driver);
		WebDriverDataLibrary.navigateApp(url, driver);

	}
	
	/**
	 * This annotation is used to login the application
	 */
	@BeforeMethod(groups="baseclass")
	public void beforeMethodTest()
	{
		loginpage.loginAction(username, password);

	}
	
	/**
	 * This annotation is used to logout the application 
	 */
	@AfterMethod(groups="baseclass")
	public void afterMethodTest()
	{
		homepage.signOut(driver, webDriverDataLibrary);
	}
	
	/**
	 * This annotation is used to close the browser
	 */
	@AfterClass(groups="baseclass")
	public void afterClassTest()
	{
		WebDriverDataLibrary.quitBrowser(driver);		
	}
	
	/**
	 * This annotation is used to close the excel
	 */
	@AfterSuite(groups="baseclass")
	public void afterSuiteTest()
	{
		ExcelDataLibrary.writeDataInExcel(IconstantPathLibrary.EXCELFILEPATH);
		ExcelDataLibrary.closeExcel();
	}
	
	

}
