package com.vtiger.elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

// create the class as webpage name and make it as public
public class HomePage {
	
	
	// Declare all the element and specify the access specifier as private
	@FindBy(linkText="More")
	private WebElement moreDropDown;
	
	@FindBy(linkText="Campaigns")
	private WebElement compaignsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcone;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	@FindBy(linkText="Products")
	private WebElement productTab;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsTab;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationsTab;
	
	@FindBy(linkText="Documents")
	private WebElement documentsTab;
	
	@FindBy(linkText="Leads")
	private WebElement leadsTab;
	
	
	
	@FindBy(linkText="Products")
	private WebElement productsTab;
	
	
	//initialize the driver address to all the element through and make it as public 
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	// business library
	public void clickCampaign(WebDriver driver,WebDriverDataLibrary webDriverDataLibrary)
	{
		webDriverDataLibrary.mouseOverOnElement(moreDropDown);
		compaignsTab.click();
	}
	
	public void signOut(WebDriver driver,WebDriverDataLibrary webDriverDataLibrary)
	{
		webDriverDataLibrary.mouseOverOnElement(administratorIcone);
		signOutLink.click();
	}
	
	public void clickProduct()
	{
		productTab.click();	
	}
	public void clickContacts() {
		contactsTab.click();
	}
	public WebElement getContacts() {
	return	contactsTab;
	}
	public void clickOrganizations() {
		organizationsTab.click();
	}
	
	public void clickDocumentsTab() {
		documentsTab.click();
	}
	
	public void clickProductsTab() {
		productsTab.click();
	}

	public void clickLeadsTab() {
		leadsTab.click();
	}
}
