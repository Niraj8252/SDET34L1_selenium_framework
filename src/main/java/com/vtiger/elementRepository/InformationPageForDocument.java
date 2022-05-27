package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForDocument {
	
	@FindBy(id="dtlview_Title")
	private WebElement documentInfoText;
	
	
	public InformationPageForDocument(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkDocumentInformation()
	{
		return documentInfoText.getText();
	}
	

}
