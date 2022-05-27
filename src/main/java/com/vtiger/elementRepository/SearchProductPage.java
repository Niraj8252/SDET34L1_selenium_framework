package com.vtiger.elementRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

public class SearchProductPage {
	
	@FindBy(id="search_txt")
	private WebElement searchTxt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	public SearchProductPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	
	public void selectProduct(String productName,WebDriver driver) {
		WebDriverDataLibrary.switchToWindowBesedOnTitle(driver, "Products&action");
		searchTxt.sendKeys(productName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
	}
}


	
	