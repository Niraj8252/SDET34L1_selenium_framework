package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForOrganization {
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement organizationInfoText;
	
	
	public InformationPageForOrganization(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkOrganizationInformation()
	{
		return organizationInfoText.getText();
	}
	public WebElement getOrganizationInformation()
	{
		return organizationInfoText;
	}

}
