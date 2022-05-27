package com.vtiger.practice;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateProductPageAndSavePage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.InformationPageForProduct;
import com.vtiger.elementRepository.LoginPage;
import com.vtiger.elementRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsTestPom {
	public static void main(String[] args) throws IOException {
		
		WebDriverDataLibrary webDriverDataLibrary=new WebDriverDataLibrary(null);

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
		
		WebDriver driver=null;
		
		switch(browser)
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			 break;
			 
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default:
			System.out.println("provide specific browser key");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		ProductPage productPage = new ProductPage(driver);
		CreateProductPageAndSavePage createProductPage = new CreateProductPageAndSavePage(driver);
		InformationPageForProduct productInfo = new InformationPageForProduct(driver);
		
		WebDriverDataLibrary.maximizebrowser(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeOut, driver);
		WebDriverDataLibrary.navigateApp(url, driver);
		
		loginpage.loginAction(username, password);

		homepage.clickProduct();
		
		productPage.productCreateIcon();
		
		String actualproduct="ecommerce";
		createProductPage.enterProductData(actualproduct);

		javaLibrary.assertionThroughIfCondition(productInfo.checkproductInformation(),actualproduct, "Products");
//		if(actualproduct.contains(expectedproduct))	
//		{
//			System.out.println("product created");
//		}
//		else
//		{
//			System.out.println("product creation failed");
//		}
		homepage.signOut(driver,webDriverDataLibrary);
		
		WebDriverDataLibrary.quitBrowser(driver);	
	}
}
