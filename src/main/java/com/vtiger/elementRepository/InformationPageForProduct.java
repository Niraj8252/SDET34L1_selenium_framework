package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPageForProduct {
	
	@FindBy(id="dtlview_Product Name")
	private WebElement productInfoText;
	
	
	public InformationPageForProduct(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public String checkproductInformation()
	{
		return productInfoText.getText();
	}

}
