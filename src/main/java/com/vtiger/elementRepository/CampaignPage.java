package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//create the class as webpage name and make it as public

public class CampaignPage {
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement compaignPageLnk;

	
	//initialize the driver address to all the element through and make it as public

	public CampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//busuness library
	public void campaignCreateIcon()
	{
		compaignPageLnk.click();
	}
}
