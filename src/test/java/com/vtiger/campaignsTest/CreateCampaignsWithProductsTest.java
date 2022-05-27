package com.vtiger.campaignsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CampaignPage;
import com.vtiger.elementRepository.CreateCapmpaignPageAndSavePage;
import com.vtiger.elementRepository.CreateProductPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForCampaign;
import com.vtiger.elementRepository.ProductPage;
import com.vtiger.elementRepository.SearchProductPage;

public class CreateCampaignsWithProductsTest extends BaseClass{
	
	
	@Test(groups="sanity")
	  public void createCampaignWithProductTest()
	  {
		String compaignName=ExcelDataLibrary.getDataFromExcel("Compaign", 2, 1)+randomNumber;
		String productName=ExcelDataLibrary.getDataFromExcel("Compaign", 5, 1)+randomNumber;
	 		
			ProductPage productPage = new ProductPage(driver);
			CreateProductPageAndSavePage createAndSave = new CreateProductPageAndSavePage(driver);
			CampaignPage compaignPage = new CampaignPage(driver);
			CreateCapmpaignPageAndSavePage createAndSaveCampain = new CreateCapmpaignPageAndSavePage(driver);
			SearchProductPage searchProduct = new SearchProductPage(driver);
			InformationPageForCampaign campaignInformation = new InformationPageForCampaign(driver);
	 		
	 		homepage.clickProduct();
	 		productPage.productCreateIcon();
	 		createAndSave.enterProductData(productName);
	 	
	 		homepage.clickCampaign(driver, webDriverDataLibrary);
	 		compaignPage.campaignCreateIcon();
	 		createAndSaveCampain.enterCampaignData(compaignName);
	 		createAndSaveCampain.addProduct();
	 		searchProduct.selectProduct(productName, driver);
				
				WebDriverDataLibrary.switchToWindowBesedOnTitle(driver, "Campaigns&action");
				createAndSaveCampain.save();
	 		
	 		javaLibrary.assertionThroughIfCondition(campaignInformation.checkCampaignInformation(), compaignName, "Campaigns");
	 		javaLibrary.assertionThroughIfCondition(campaignInformation.checkProductInformationInCampain(), productName, "");		
	}

}
