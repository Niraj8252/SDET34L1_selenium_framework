package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement contactsPageLnk;

	
	//initialize the driver address to all the element through and make it as public

	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//busuness library
	public void contactCreateIcon()
	{
		contactsPageLnk.click();
	}

}
