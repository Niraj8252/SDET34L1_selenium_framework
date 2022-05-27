package com.vtiger.organizationTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.vtiger.elementRepository.CreateNewOrganizationAndSavePage;
import com.vtiger.elementRepository.InformationPageForOrganization;
import com.vtiger.elementRepository.OrganizationPage;

public class CreateOrganizationWithDDIndustryTest extends BaseClass {
		
	@Test(groups="sanity")
	public void createOrganizationTest()
	{
		String organizationName=ExcelDataLibrary.getDataFromExcel("Organizations", 2, 1)+randomNumber;
			
	OrganizationPage organizationPage=new OrganizationPage(driver);
	CreateNewOrganizationAndSavePage createAndSaveOrganization = new CreateNewOrganizationAndSavePage(driver);
	InformationPageForOrganization organizationInformation = new InformationPageForOrganization(driver);
	
	homepage.clickOrganizations();
	organizationPage.organizationsCreateIcon();
	createAndSaveOrganization.enterOrganizationData(organizationName);
		
	createAndSaveOrganization.selectDropDownIndustry("Education","Press","Active");

	createAndSaveOrganization.save();
		
		javaLibrary.assertionThroughIfCondition(organizationInformation.checkOrganizationInformation(), organizationName, "OganizationName");

	}
	

}
