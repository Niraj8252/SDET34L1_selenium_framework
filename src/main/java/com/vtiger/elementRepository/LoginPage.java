package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//create the class as webpage name and make it as public
public class LoginPage {
	
	// Declare all the element and specify the access specifier as private
	@FindBy(name="user_name")
	private WebElement userNameTxt;
	
	@FindBy(name="user_password")
	private WebElement passwordTxt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//initialize the driver address to all the element through and make it as public
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//business library
	public void loginAction(String userName,String password)
	{
		userNameTxt.sendKeys(userName);
		passwordTxt.sendKeys(password);
		loginBtn.click();
	}

}
