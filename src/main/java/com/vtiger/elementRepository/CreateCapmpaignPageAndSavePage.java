package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//create the class as webpage name and make it as public
public class CreateCapmpaignPageAndSavePage {
	
	// Declare all the element and specify the access specifier as private
	@FindBy(name="campaignname")
	private WebElement campaignNameTxt;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement addProductIcon;
	
	
	//initialize the driver address to all the element through and make it as public
	public CreateCapmpaignPageAndSavePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	//bussiness library
	public void enterCampaignDataAndSave(String campaignname)
	{
		campaignNameTxt.sendKeys(campaignname);
		saveBtn.click();
	}
	
	public void addProduct() {
		addProductIcon.click();
	}
	
	public void enterCampaignData(String campaignname)
	{
		campaignNameTxt.sendKeys(campaignname);
	}
	public void save() {
	saveBtn.click();
	}
}
