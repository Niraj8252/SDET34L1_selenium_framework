package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement organizationsPageLnk;

	
	//initialize the driver address to all the element through and make it as public

	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//busuness library
	public void organizationsCreateIcon()
	{
		organizationsPageLnk.click();
	}

}
