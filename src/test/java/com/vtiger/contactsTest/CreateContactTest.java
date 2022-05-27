package com.vtiger.contactsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.vtiger.elementRepository.ContactsPage;
import com.vtiger.elementRepository.CreateNewContactPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForContact;

public class CreateContactTest extends BaseClass{
	
	@Test(groups="regration")
	public void createContactTest()
	{
	String contact=ExcelDataLibrary.getDataFromExcel("Contacts", 2, 1)+randomNumber;

ContactsPage contactPage = new ContactsPage(driver);
CreateNewContactPageAndSavePage createContact= new CreateNewContactPageAndSavePage(driver);
InformationPageForContact contactInformation = new InformationPageForContact(driver);

if(driver.getTitle().contains("Home"))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 12, 5,"Home page is Displayed");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 12, 6,"pass");
}

homepage.clickContacts();

if(driver.getTitle().contains("Contacts"))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 13, 5,"Contact page is displayed");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 13, 6,"pass");

}
contactPage.contactCreateIcon();

if(driver.getCurrentUrl().contains("Contacts&action"))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 14, 5,"Create Contact page is displayed");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 14, 6,"pass");

}
createContact.enterContactsLastData(contact);

if(driver.getCurrentUrl().contains("Contacts&parenttab"))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 15, 5,"Contact created successfully");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 15, 6,"pass");

}
createContact.save();
javaLibrary.assertionThroughIfCondition(contactInformation.checkContactLastNameInformation(), contact, "Contacts");

if(driver.getCurrentUrl().contains("DetailView&module") && contactInformation.checkContactLastNameInformation().equalsIgnoreCase(contact))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 16, 5,"same data created");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 16, 6,"pass");

}
if(driver.getCurrentUrl().contains("Login&module"))
{
	ExcelDataLibrary.setDataIntoExcel("Contacts", 17, 5,"login page is displyed");
	ExcelDataLibrary.setDataIntoExcel("Contacts", 17, 6,"pass");
}
		
}
}
