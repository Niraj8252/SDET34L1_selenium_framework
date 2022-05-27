package com.vtiger.leadsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateNewLeadPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForLead;
import com.vtiger.elementRepository.LeadsPage;

public class CreateLeadAndDeleteFromTextFieldTc_58Test extends BaseClass {
	
       @Test(groups="sanity")
       public void createLeadAndDeleteFromTxtFieldsTest()
       {
	
		String leadFirstName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 2);
		String leadLastName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 3)+randomNumber;
		String leadCompanyName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 4)+randomNumber;
		
	LeadsPage leadpage = new LeadsPage(driver);
	CreateNewLeadPageAndSavePage createLeadAndSave = new CreateNewLeadPageAndSavePage(driver);
	InformationPageForLead leadInfo = new InformationPageForLead(driver);
	
	homepage.clickLeadsTab();
	leadpage.leadCreateIcon();
	createLeadAndSave.enterLeadsData(leadFirstName, leadLastName, leadCompanyName);
	leadInfo.clickLeadLnk();
	leadpage.selectLeadList();
	leadpage.deleteLeadFromLisTxt();

	WebDriverDataLibrary.alertPopUpHandle(driver);
	
	}
}
