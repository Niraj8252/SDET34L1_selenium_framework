package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonInformationPage {
	
	@FindBy(className="dvHeaderText")
	private WebElement comomnInfoText;
	
	public CommonInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkCommonInformation()
	{
		return comomnInfoText.getText();
	}

}
