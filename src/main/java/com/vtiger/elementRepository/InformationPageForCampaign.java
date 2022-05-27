package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForCampaign {
	
	@FindBy(id="dtlview_Campaign Name")
	private WebElement compaingnInformationText;
	
	@FindBy(xpath="//span[@id='dtlview_Product']/a")
	private WebElement productInformationText;
	
	public InformationPageForCampaign(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkCampaignInformation()
	{
		return compaingnInformationText.getText();
	}
	
	public String checkProductInformationInCampain()
	{
		return productInformationText.getText();
	}
	
}
