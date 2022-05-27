package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForContact {

	@FindBy(id="dtlview_First Name")
	private WebElement contactFirstInfoText;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement contactLastInfoText;
	

	
	public InformationPageForContact(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkContactFirstNameInformation()
	{
		
		return contactFirstInfoText.getText();
	}
	public String checkContactLastNameInformation()
	{
		
		return contactLastInfoText.getText();
	}
	

}
