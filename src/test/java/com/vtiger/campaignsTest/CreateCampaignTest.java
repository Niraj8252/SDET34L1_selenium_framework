package com.vtiger.campaignsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.vtiger.elementRepository.CampaignPage;
import com.vtiger.elementRepository.CreateCapmpaignPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForCampaign;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CreateCampaignTest extends BaseClass {
	
			
			@Test(groups="sanity")
			@Description("Description:- CreateCampaignTest")
			@Epic("Epic:-CreateCampaignTest")
			@Story("Story:- CreateCampaignTest")
			@Severity(SeverityLevel.BLOCKER)
			public void createCampaignTest()
			{
				String campaignName=ExcelDataLibrary.getDataFromExcel("Compaign", 2, 1)+randomNumber;

				CampaignPage campaignPage = new CampaignPage(driver);
				CreateCapmpaignPageAndSavePage createCampaignPage = new CreateCapmpaignPageAndSavePage(driver);
				InformationPageForCampaign campaignInformationPage = new InformationPageForCampaign(driver);
				
				homepage.clickCampaign(driver, webDriverDataLibrary);
				campaignPage.campaignCreateIcon();
				createCampaignPage.enterCampaignData(campaignName);
				createCampaignPage.save();
				javaLibrary.assertionThroughIfCondition( campaignInformationPage.checkCampaignInformation(),campaignName, "Compaign");
	}

}
