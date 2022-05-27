package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectGuiWRTDatabase {
	public static void main(String[] args) throws SQLException {
	
		String projectname = "Testyantra";
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);
		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement statement = connection.createStatement();
		statement.executeUpdate("insert into project value('TY_PROJ_026','Niraj kumar','28/04/2022','"+projectname+"','test',4)");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		List<WebElement> projectlist = driver.findElements(By.xpath("//table/tbody/tr/td"));
		for(WebElement list:projectlist)
		{
			if(list.getText().equalsIgnoreCase(projectname))
			{
				System.out.println("project name is visible in Gui");
				System.out.println("TC pass");
			}
		}
		driver.quit();	
	}
}
