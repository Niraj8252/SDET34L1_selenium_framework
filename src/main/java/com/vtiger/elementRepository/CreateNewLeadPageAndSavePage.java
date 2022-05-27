package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPageAndSavePage {
	
	// Declare all the element and specify the access specifier as private
				@FindBy(name="firstname")
				private WebElement firstNameTxt;
				
				@FindBy(name="lastname")
				private WebElement lastNameTxt;
				
				@FindBy(name="company")
				private WebElement companyNameTxt;
				
				
				@FindBy(xpath="//input[@title='Save [Alt+S]']")
				private WebElement saveBtn;
				
				
				//initialize the driver address to all the element through and make it as public
				public CreateNewLeadPageAndSavePage(WebDriver driver) {
				PageFactory.initElements(driver, this);
				}
				
				//bussiness library
				public void enterLeadsData(String firstName,String lastName,String companyName )
				{
					firstNameTxt.sendKeys(firstName);
					lastNameTxt.sendKeys(lastName);
					companyNameTxt.sendKeys(companyName);
					saveBtn.click();
				}
				public void enterLeadsLastData(String lastName)
				{
					lastNameTxt.sendKeys(lastName);
				}
				
				public void save()
				{
					saveBtn.click();
				}
//				public void addOrganizationIcon() {
//					addOrganizationIcon.click();
//				}

}
