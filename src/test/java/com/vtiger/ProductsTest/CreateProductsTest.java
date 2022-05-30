package com.vtiger.ProductsTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.vtiger.elementRepository.CreateProductPageAndSavePage;
import com.vtiger.elementRepository.InformationPageForProduct;
import com.vtiger.elementRepository.ProductPage;

public class CreateProductsTest extends BaseClass {
		
		
		@Test(retryAnalyzer=com.sdet34l1.genericLibrary.RetryAnalyserImplementation.class)
		public void createProductTest()
		{
			String productName=ExcelDataLibrary.getDataFromExcel("Products", 2, 1)+randomNumber;

		ProductPage productPage = new ProductPage(driver);
		CreateProductPageAndSavePage createProductPage = new CreateProductPageAndSavePage(driver);
		InformationPageForProduct productInformation = new InformationPageForProduct(driver);
		
		homepage.clickProduct();
		
		productPage.productCreateIcon();
		
		
		createProductPage.enterProductData(productName);

		javaLibrary.assertionThroughIfCondition(productInformation.checkproductInformation(),productName, "Products");
	}
}
