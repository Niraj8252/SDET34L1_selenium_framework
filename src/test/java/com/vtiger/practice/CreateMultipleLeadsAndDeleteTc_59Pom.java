package com.vtiger.practice;

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
import com.vtiger.elementRepository.CreateNewLeadPageAndSavePage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.InformationPageForLead;
import com.vtiger.elementRepository.LeadsPage;
import com.vtiger.elementRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateMultipleLeadsAndDeleteTc_59Pom {
	
public static void main(String[] args) throws IOException, InterruptedException {
		
	WebDriverDataLibrary webDriverDataLibrary =new WebDriverDataLibrary(null);

	JavaLibrary javaLibrary=new JavaLibrary();
		//property file
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
		//Excel file
		String leadFirstName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 2);
		System.out.println(leadFirstName);
		String leadLastName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 3)+num;
		System.out.println(leadLastName);
		String leadCompanyName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 4)+num;
		System.out.println(leadCompanyName);
		String leadFirstName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 2);
		System.out.println(leadFirstName1);
		String leadLastName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 3)+num;
		System.out.println(leadLastName1);
		String leadCompanyName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 4)+num;
		System.out.println(leadCompanyName1);
	
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
	
	LoginPage loginpage=new LoginPage(driver);
	HomePage homepage = new HomePage(driver);
	LeadsPage leadpage = new LeadsPage(driver);
	CreateNewLeadPageAndSavePage createLeadAndSave = new CreateNewLeadPageAndSavePage(driver);
	InformationPageForLead leadInformation = new InformationPageForLead(driver);
	
	webDriverDataLibrary.initializeAction(driver);
	WebDriverDataLibrary.maximizebrowser(driver);
	WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
	WebDriverDataLibrary.navigateApp(url, driver);
	
	loginpage.loginAction(username, password);
	homepage.clickLeadsTab();
	WebDriverDataLibrary.explicitlyWait(driver, longTimeOut);
	leadpage.leadCreateIcon();
	createLeadAndSave.enterLeadsData(leadFirstName, leadLastName, leadCompanyName);
	leadInformation.clickLeadLnk();
	leadpage.leadCreateIcon();
	createLeadAndSave.enterLeadsData(leadFirstName1, leadLastName1, leadCompanyName1);
	leadInformation.clickLeadLnk();
	leadpage.selectMultipleLeadList();

	leadpage.deleteLeadFromList();
	WebDriverDataLibrary.alertPopUpHandle(driver);
	
	homepage.signOut(driver, webDriverDataLibrary);

	ExcelDataLibrary.closeExcel();
	System.out.println("Tc pass");
	WebDriverDataLibrary.quitBrowser(driver);
	}


}
