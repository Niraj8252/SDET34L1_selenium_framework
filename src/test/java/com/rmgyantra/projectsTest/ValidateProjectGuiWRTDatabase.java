package com.rmgyantra.projectsTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sdet34l1.genericLibrary.DataBaseLibrary;
import com.sdet34l1.genericLibrary.ExcelDataLibrary;
import com.sdet34l1.genericLibrary.IconstantPathLibrary;
import com.sdet34l1.genericLibrary.JavaLibrary;
import com.sdet34l1.genericLibrary.PropertyFileDataLibrary;
import com.sdet34l1.genericLibrary.WebDriverDataLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectGuiWRTDatabase {
	public static void main(String[] args) throws SQLException, IOException {
		JavaLibrary javaLibrary = new JavaLibrary();
		int randomNumber=javaLibrary.generateRandomNum(1000);
			
		PropertyFileDataLibrary.openPropertyFile(IconstantPathLibrary.RMGYANTRA_PROPERTYFILEPATH);
		ExcelDataLibrary.openExcel(IconstantPathLibrary.RMGYANTRA_EXCELFILEPATH);
		
		String projectName = ExcelDataLibrary.getDataFromExcel("Projects", 1, 1)+"_"+randomNumber;
		String timeout = PropertyFileDataLibrary.getDataFromPropertyFile("timeout");
		long longTimeout = javaLibrary.stringConvertToLong(timeout);
		String userName = PropertyFileDataLibrary.getDataFromPropertyFile("dbuserName");
		System.out.println(userName);
		String password = PropertyFileDataLibrary.getDataFromPropertyFile("dbPassword");
		System.out.println(password);
		
		DataBaseLibrary.openDataBaseConnection(IconstantPathLibrary.DATABASEURL, userName, password);
		DataBaseLibrary.setDataInDataBase("insert into project value('TY_PROJ_"+randomNumber+"','Niraj kumar','28/04/2022','"+projectName+"','test',4)");
//		Driver driver1 = new Driver();
//		DriverManager.registerDriver(driver1);
//		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
//		Statement statement = connection.createStatement();
//		statement.executeUpdate("insert into project value('TY_PROJ_"+randomNum+"','Niraj kumar','28/04/2022','"+projectName+"','test',4)");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverDataLibrary.navigateApp(PropertyFileDataLibrary.getDataFromPropertyFile("rmgyantraurl"), driver);
		WebDriverDataLibrary.maximizebrowser(driver);
		WebDriverDataLibrary.waitImplicitlyWaitTillPageLoad(longTimeout, driver);
		
		String rmguserName = PropertyFileDataLibrary.getDataFromPropertyFile("rmgUserName");
		System.out.println(rmguserName);
		String rmgPassword = PropertyFileDataLibrary.getDataFromPropertyFile("rmgPassword");
		System.out.println(rmgPassword);
		driver.findElement(By.id("usernmae")).sendKeys(rmguserName,Keys.TAB,rmgPassword,Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		List<WebElement> projectlist = driver.findElements(By.xpath("//table/tbody/tr/td"));
		for(WebElement list:projectlist)
		{
			if(list.getText().equalsIgnoreCase(projectName))
			{
				System.out.println("project name is visible in Gui");
				System.out.println("TC pass");
			}
		}
		WebDriverDataLibrary.quitBrowser(driver);	
	}
}
