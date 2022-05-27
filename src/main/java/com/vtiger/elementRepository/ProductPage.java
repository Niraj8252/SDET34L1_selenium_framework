package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductIcon;
	
	
	//initialize the driver address to all the element through and make it as public

		public ProductPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}
		
		//busuness library
		public void productCreateIcon()
		{
			createProductIcon.click();
		}

}

