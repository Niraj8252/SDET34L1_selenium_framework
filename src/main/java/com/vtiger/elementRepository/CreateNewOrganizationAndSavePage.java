package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

public class CreateNewOrganizationAndSavePage {
	
	// Declare all the element and specify the access specifier as private
			@FindBy(name="accountname")
			private WebElement organizationNameTxt;
			
			
			@FindBy(xpath="//input[@title='Save [Alt+S]']")
			private WebElement saveBtn;

			@FindBy(xpath="//select[@name='industry']")
			private WebElement adddropDownIndustry;

			@FindBy(xpath="//select[@name='accounttype']")
			private WebElement addtype;

			@FindBy(xpath="//select[@name='rating']")
			private WebElement addRating;
			
			
			//initialize the driver address to all the element through and make it as public
			public CreateNewOrganizationAndSavePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			}
			
			//bussiness library
			public void enterOrganizationData(String organizationName)
			{
				organizationNameTxt.sendKeys(organizationName);
				//save();
			}
			
			
			public void save() {
				saveBtn.click();
			}
			public void selectDropDownIndustry(String industryValue , String typeValue,String ratingValue)
			{
				WebDriverDataLibrary.selectListBox(adddropDownIndustry, industryValue);
				WebDriverDataLibrary.selectListBox(addtype,typeValue );
				WebDriverDataLibrary.selectListBox(addRating,ratingValue );
			}
	}



