package com.vtiger.practice;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateNewOrganizationAndSavePage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.InformationPageForOrganization;
import com.vtiger.elementRepository.LoginPage;
import com.vtiger.elementRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationTestPom {
	public static void main(String[] args) throws Throwable {

		JavaLibrary javaLibrary=new JavaLibrary();
		WebDriverDataLibrary webDriverDataLibrary=new WebDriverDataLibrary(null);

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
		int num =javaLibrary.generateRandomNum(1000);
		
		String organizationname=ExcelDataLibrary.getDataFromExcel("Organizations", 2, 1)+num;

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		OrganizationPage organizationPage=new OrganizationPage(driver);
		CreateNewOrganizationAndSavePage createAndSaveOrg = new CreateNewOrganizationAndSavePage(driver);
		InformationPageForOrganization organizationInformation = new InformationPageForOrganization(driver);
		
		WebDriverDataLibrary.navigateApp(url, driver);
		WebDriverDataLibrary.maximizebrowser(driver);
		webDriverDataLibrary.initializeAction(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
		
		loginpage.loginAction(username, password);

		homepage.clickOrganizations();
		
		organizationPage.organizationsCreateIcon();
		createAndSaveOrg.enterOrganizationData(organizationname);
		createAndSaveOrg.save();
		
		
		javaLibrary.assertionThroughIfCondition(organizationInformation.checkOrganizationInformation(), organizationname, "Organization");
//		if(actual_name.getText().contains("TestYantra"))
//		{
//			System.out.println("pass");
//		}
//		else
//		{
//			System.out.println("Fail");
//		}
		homepage.signOut(driver, webDriverDataLibrary );

		ExcelDataLibrary.closeExcel();

		WebDriverDataLibrary.quitBrowser(driver);
		
	}

}
