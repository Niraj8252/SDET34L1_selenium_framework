package com.vtiger.elementRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewDocumentPageAndSavePage {
	
	// Declare all the element and specify the access specifier as private
			@FindBy(xpath="//input[@name='notes_title']")
			private WebElement documentTitleTxt;
			
			
			@FindBy(xpath="//input[@title='Save [Alt+S]']")
			private WebElement saveBtn;
			
			@FindBy(xpath="//body[@class='cke_show_borders']")
			private WebElement notesText;
			
			@FindBy(xpath="//a[@id='cke_5']/span[@class='cke_icon']")
			private WebElement boldText;
			
			@FindBy(xpath="//a[@id='cke_6']/span[@class='cke_icon']")
			private WebElement inclindText;
			
			@FindBy(id="filename_I__")
			private WebElement filepath;
			
			//initialize the driver address to all the element through and make it as public
			public CreateNewDocumentPageAndSavePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			}
			
			//bussiness library
			public void enterDocumentData(String documentTitle)
			{
				documentTitleTxt.sendKeys(documentTitle);
			}
			
			public void save()
			{
				saveBtn.click();
			}
			
			public void sendNotes(String notes) {
			
				notesText.sendKeys(notes,Keys.CONTROL+"a");
			}
			public void boldTextDucument()
			{
				boldText.click();
			}
			public void inclindTextDucument()
			{
				inclindText.click();
			}
			public void filepathtDucument(String path)
			{
				filepath.sendKeys(path);
			}

}