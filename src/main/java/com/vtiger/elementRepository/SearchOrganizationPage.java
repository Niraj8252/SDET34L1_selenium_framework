package com.vtiger.elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

public class SearchOrganizationPage {
	@FindBy(id="search_txt")
	private WebElement searchTxt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	public SearchOrganizationPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	
	public void selectOrganization(String organizationName,WebDriver driver) {
		WebDriverDataLibrary.switchToWindowBesedOnTitle(driver, "Accounts&action");
		searchTxt.sendKeys(organizationName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+organizationName+"']")).click();
	}

}


