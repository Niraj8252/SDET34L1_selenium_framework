package com.vtiger.documentsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;
import com.vtiger.elementRepository.CreateNewDocumentPageAndSavePage;
import com.vtiger.elementRepository.DocumentPage;
import com.vtiger.elementRepository.InformationPageForDocument;

public class CreateDocumentTest extends BaseClass {
	
		@Test(groups="sanity")
		public void createDocumentTest()
		{
			String documentTitle=ExcelDataLibrary.getDataFromExcel("Documents", 2, 1)+randomNumber;
			String notesMessage=ExcelDataLibrary.getDataFromExcel("Documents", 2, 2);
			String filePath=ExcelDataLibrary.getDataFromExcel("Documents", 2, 3);
			
			DocumentPage documentPage = new DocumentPage(driver);
			CreateNewDocumentPageAndSavePage createDocument = new CreateNewDocumentPageAndSavePage(driver);
			InformationPageForDocument documentInformation = new InformationPageForDocument(driver);
		
			homepage.clickDocumentsTab();
			documentPage.ducumentCreateIcon();
			createDocument.enterDocumentData(documentTitle);
	 		
	 		WebDriverDataLibrary.switchToFrame(driver, 0);
	 		createDocument.sendNotes(notesMessage);
	 		
	 		WebDriverDataLibrary.swicthBackToMainPage(driver);
	 		createDocument.boldTextDucument();
	 		createDocument.inclindTextDucument();

	 		createDocument.filepathtDucument(filePath);
	 		createDocument.save();
			
	 		javaLibrary.assertionThroughIfCondition(documentInformation.checkDocumentInformation(), documentTitle, "Documents");	
		}
}
