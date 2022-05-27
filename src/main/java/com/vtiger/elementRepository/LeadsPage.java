package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	@FindBy(xpath="//img[@alt='Create Lead...']")
	private WebElement createLeadIcon;
	
	@FindBy(xpath="//a[text()='Lead No']/../../following-sibling::tr[3]/td[1]//input[@name='selected_id']")
	private WebElement selectLeadList;
	
	@FindBy(xpath="//a[text()='Lead No']/../../following-sibling::tr[4]/td[1]//input[@name='selected_id']")
	private WebElement selectLeadList1;
	
	@FindBy(xpath="//input[@value='Delete']")
	private WebElement deleteLeadFromList;
	
	@FindBy(xpath="//a[text()='Last Name']/../../following-sibling::tr[3]/td[10]//a[text()='del']")
	private WebElement deleteLeadFromListTxt;
	
	//initialize the driver address to all the element through and make it as public

		public LeadsPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
			
		}
		
		//busuness library
		public void leadCreateIcon()
		{
			createLeadIcon.click();
		}
		public void selectLeadList()
		{
			selectLeadList.click();
		}
		public void selectMultipleLeadList()
		{
			selectLeadList.click();
			selectLeadList1.click();
		}

		public void deleteLeadFromList()
		{
			deleteLeadFromList.click();
		}
		public void deleteLeadFromLisTxt()
		{
			deleteLeadFromListTxt.click();
		}
	}
