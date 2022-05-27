package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {
	
	@FindBy(xpath="//img[@alt='Create Document...']")
	private WebElement createDocumentIcon;
	
	
	//initialize the driver address to all the element through and make it as public

		public DocumentPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}
		
		//busuness library
		public void ducumentCreateIcon()
		{
			createDocumentIcon.click();
		}

}
