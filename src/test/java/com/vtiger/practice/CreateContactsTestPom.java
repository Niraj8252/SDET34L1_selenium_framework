package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.ContactsPage;
import com.vtiger.elementRepository.CreateNewContactPageAndSavePage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.InformationPageForContact;
import com.vtiger.elementRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateContactsTestPom {

public static void main(String[] args) throws SQLException {
	WebDriverDataLibrary webDriverDataLibrary =new WebDriverDataLibrary(null);

	String url=null,username=null,password=null,timeout=null,browserName=null;
	Driver d = new Driver();
	DriverManager.registerDriver(d);
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "root");
	Statement statement=connection.createStatement();
	ResultSet result = statement.executeQuery("select * from vtigerdata");
	while(result.next())
	{
		url = result.getString("url");
		username = result.getString("username");
		password = result.getString("password");
		timeout=result.getString("timeout");
		browserName=result.getString("browserName");
	}
	long longTimeOut= Long.parseLong(timeout);
	WebDriver driver=null;
//	if(browserName.equalsIgnoreCase("chrome"))
//	{
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//	}
//	else if(browserName.equalsIgnoreCase("firefox"))
//	{
//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();
//	}
	
	switch(browserName) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
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
	
	
//	WebDriverManager.chromedriver().setup();
//   driver = new ChromeDriver();
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		ContactsPage contactPage = new ContactsPage(driver);
		CreateNewContactPageAndSavePage createContact= new CreateNewContactPageAndSavePage(driver);
		InformationPageForContact contactInfo = new InformationPageForContact(driver);

	WebDriverDataLibrary.navigateApp(url, driver);
	WebDriverDataLibrary.maximizebrowser(driver);
	WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
	
	loginpage.loginAction(username, password);
	homepage.clickContacts();
	contactPage.contactCreateIcon();

	String firstName="sdet34";
	String lastName="L1";
	createContact.enterContactsData(firstName, lastName);

	String actualFirstName =contactInfo.checkContactFirstNameInformation();
	String actualLastName = contactInfo.checkContactLastNameInformation();
	
	if(actualFirstName.equalsIgnoreCase(firstName)&&actualLastName.equalsIgnoreCase(lastName))
	{
				System.out.println("Contact created successfully");
				System.out.println("TC pass");
	}	
	
	homepage.signOut(driver,webDriverDataLibrary );


	WebDriverDataLibrary.quitBrowser(driver);	
}
}
 	