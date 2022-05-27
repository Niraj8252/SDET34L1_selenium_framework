package com.vtiger.contactsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.ContactsPage;
import com.vtiger.elementRepository.CreateNewContactPageAndSavePage;
import com.vtiger.elementRepository.CreateNewOrganizationAndSavePage;
import com.vtiger.elementRepository.InformationPageForContact;
import com.vtiger.elementRepository.InformationPageForOrganization;
import com.vtiger.elementRepository.OrganizationPage;
import com.vtiger.elementRepository.SearchOrganizationPage;

public class CreateContactWithOrganizationTest extends BaseClass{
	
	@Test(groups="regration")
	public void createContactWithOrganizationTest()
	{
			String lastNameValue=ExcelDataLibrary.getDataFromExcel("Contacts", 5, 2)+randomNumber;
			String organizationName=ExcelDataLibrary.getDataFromExcel("Contacts", 5, 1)+randomNumber;
		  
			ContactsPage contactPage = new ContactsPage(driver);
			CreateNewContactPageAndSavePage createContact= new CreateNewContactPageAndSavePage(driver);
			InformationPageForContact contactInformation = new InformationPageForContact(driver);
			OrganizationPage organizationPage = new OrganizationPage(driver);
			CreateNewOrganizationAndSavePage createOrganization = new CreateNewOrganizationAndSavePage(driver);
			SearchOrganizationPage searchOrganization = new SearchOrganizationPage(driver);
			InformationPageForOrganization organizationInformation=new 	InformationPageForOrganization(driver);
			
			homepage.clickOrganizations();
			organizationPage.organizationsCreateIcon();
			createOrganization.enterOrganizationData(organizationName);
			createOrganization.save();
			WebDriverDataLibrary.waitUntilElementClickable(organizationInformation.getOrganizationInformation());
			homepage.clickContacts();
			contactPage.contactCreateIcon();
			createContact.enterContactsLastData(lastNameValue);
			createContact.addOrganizationIcon();
			
			searchOrganization.selectOrganization(organizationName, driver);

				WebDriverDataLibrary.switchToWindowBesedOnTitle(driver, "Contacts&action");
				createContact.save();
			
				javaLibrary.assertionThroughIfCondition(contactInformation.checkContactLastNameInformation(), lastNameValue, "Contacts");
		
		}

}
