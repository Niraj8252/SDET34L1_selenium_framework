package com.sdet34l1.genericLibrary;

import org.openqa.selenium.WebDriver;

import com.vtiger.elementRepository.CommonInformationPage;
import com.vtiger.elementRepository.HomePage;
import com.vtiger.elementRepository.LoginPage;

public class GetterAndSetterForListener {
	
	private static GetterAndSetterForListener instance;
	
	public WebDriverDataLibrary webDriverDataLibrary;
	public String username;
	public String password;
	public WebDriver driver;
	public String url;
	public String browser;
	public long longTimeOut;
	public LoginPage loginpage;
	public HomePage homepage;
    public JavaLibrary javaLibrary; 
    public ExcelDataLibrary excelDataLibrary;
    public PropertyFileDataLibrary propertyFileDataLibrary;
    public CommonInformationPage commonInformationPage;
    public int randomNumber;

    private GetterAndSetterForListener() {
    	}
    
    public static GetterAndSetterForListener getInstance() {
    	if(instance==null) {
    		instance=new GetterAndSetterForListener();
    	}
    	return instance;
    }
    

	public static void setInstance(GetterAndSetterForListener instance) {
		GetterAndSetterForListener.instance = instance;
	}

	public void setWebDriverDataLibrary(WebDriverDataLibrary webDriverDataLibrary) {
		this.webDriverDataLibrary = webDriverDataLibrary;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setLongTimeOut(long longTimeOut) {
		this.longTimeOut = longTimeOut;
	}

	public void setLoginpage(LoginPage loginpage) {
		this.loginpage = loginpage;
	}

	public void setHomepage(HomePage homepage) {
		this.homepage = homepage;
	}

	public void setJavaLibrary(JavaLibrary javaLibrary) {
		this.javaLibrary = javaLibrary;
	}

	public void setExcelDataLibrary(ExcelDataLibrary excelDataLibrary) {
		this.excelDataLibrary = excelDataLibrary;
	}

	public void setPropertyFileDataLibrary(PropertyFileDataLibrary propertyFileDataLibrary) {
		this.propertyFileDataLibrary = propertyFileDataLibrary;
	}

	public void setCommonInformationPage(CommonInformationPage commonInformationPage) {
		this.commonInformationPage = commonInformationPage;
	}

	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}

	public WebDriverDataLibrary getWebDriverDataLibrary() {
		return webDriverDataLibrary;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getBrowser() {
		return browser;
	}

	public long getLongTimeOut() {
		return longTimeOut;
	}

	public LoginPage getLoginpage() {
		return loginpage;
	}

	public HomePage getHomepage() {
		return homepage;
	}

	public JavaLibrary getJavaLibrary() {
		return javaLibrary;
	}

	public ExcelDataLibrary getExcelDataLibrary() {
		return excelDataLibrary;
	}

	public PropertyFileDataLibrary getPropertyFileDataLibrary() {
		return propertyFileDataLibrary;
	}

	public CommonInformationPage getCommonInformationPage() {
		return commonInformationPage;
	}

	public int getRandomNumber() {
		return randomNumber;
	}
    
    
    }
    
    

