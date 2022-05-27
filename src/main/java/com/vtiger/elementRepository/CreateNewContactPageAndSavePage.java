package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPageAndSavePage {
	
	// Declare all the element and specify the access specifier as private
			@FindBy(name="firstname")
			private WebElement firstNameTxt;
			
			@FindBy(name="lastname")
			private WebElement lastNameTxt;
			
			
			@FindBy(xpath="//input[@title='Save [Alt+S]']")
			private WebElement saveBtn;
			
			@FindBy(xpath="//td[contains(.,'Organization Name ') and @class='dvtCellLabel']/following-sibling::td/img")
			private WebElement addOrganizationIcon;
			
			
			
			//initialize the driver address to all the element through and make it as public
			public CreateNewContactPageAndSavePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			}
			
			//bussiness library
			public void enterContactsData(String firstName,String lastName)
			{
				firstNameTxt.sendKeys(firstName);
				lastNameTxt.sendKeys(lastName);
				saveBtn.click();
			}
			public void enterContactsLastData(String lastName)
			{
				lastNameTxt.sendKeys(lastName);
			}
			
			public void save()
			{
				saveBtn.click();
			}
			public void addOrganizationIcon() {
				addOrganizationIcon.click();
			}
}
