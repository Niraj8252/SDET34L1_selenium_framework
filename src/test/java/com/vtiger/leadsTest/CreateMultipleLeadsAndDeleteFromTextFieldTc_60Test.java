package com.vtiger.leadsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateNewLeadPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForLead;
import com.vtiger.elementRepository.LeadsPage;

public class CreateMultipleLeadsAndDeleteFromTextFieldTc_60Test extends BaseClass {
	
	@Test(groups="sanity")
	public void createMultipleLeadsAndDeleteTextFielsTest()
	{
		String leadFirstName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 2);
		String leadLastName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 3)+randomNumber;
		String leadCompanyName=ExcelDataLibrary.getDataFromExcel("Leads", 2, 4)+randomNumber;
		String leadFirstName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 2);
		String leadLastName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 3)+randomNumber;
		String leadCompanyName1=ExcelDataLibrary.getDataFromExcel("Leads", 3, 4)+randomNumber;
	
	LeadsPage leadpage = new LeadsPage(driver);
	CreateNewLeadPageAndSavePage createLeadAndSave = new CreateNewLeadPageAndSavePage(driver);
	InformationPageForLead leadInfo = new InformationPageForLead(driver);

	homepage.clickLeadsTab();
	leadpage.leadCreateIcon();
	createLeadAndSave.enterLeadsData(leadFirstName, leadLastName, leadCompanyName);
	leadInfo.clickLeadLnk();
	leadpage.leadCreateIcon();

	createLeadAndSave.enterLeadsData(leadFirstName1, leadLastName1, leadCompanyName1);

	leadInfo.clickLeadLnk();
	leadpage.selectLeadList();
	leadpage.deleteLeadFromLisTxt();

	WebDriverDataLibrary.alertPopUpHandle(driver);
	leadpage.selectLeadList();
	leadpage.deleteLeadFromLisTxt();
	WebDriverDataLibrary.alertPopUpHandle(driver);
		
	}


}
