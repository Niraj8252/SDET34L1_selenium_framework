package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPageAndSavePage {
	
	// Declare all the element and specify the access specifier as private
		@FindBy(name="productname")
		private WebElement productnameNameTxt;
		
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		
		
		//initialize the driver address to all the element through and make it as public
		public CreateProductPageAndSavePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
		
		//bussiness library
		public void enterProductData(String campaignname)
		{
			productnameNameTxt.sendKeys(campaignname);
			saveBtn.click();
		}

}
