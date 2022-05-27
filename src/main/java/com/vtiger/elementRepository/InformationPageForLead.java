package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForLead {
	
	@FindBy(className="dvHeaderText")
	private WebElement leadInfoText;
	
	@FindBy(linkText="Leads")
	private WebElement leadTextLnk;
	
	
	public InformationPageForLead(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkLeadInfo()
	{
		return leadInfoText.getText();
	}
	public void clickLeadLnk()
	{
	leadTextLnk.click();
	}

}
