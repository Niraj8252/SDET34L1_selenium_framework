package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectDatabaseWRTGui {
	public static void main(String[] args) {
		Random r = new Random();
		int num = r.nextInt(1000);
		String projectname = "SDET34_"+num;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		 js.executeScript("document.getElementsByName('teamSize').values='5'");
		 driver.findElement(By.name("createdBy")).sendKeys("Mohan sir");
		 WebElement projectStatusDropdown = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		 Select s = new Select(projectStatusDropdown);
		 s.selectByVisibleText("On Goging");
		 driver.findElement(By.cssSelector("[value='Add Project']")).click();
		 
		 //String expected=driver.findElement(By.className("table-hover")).getText();
		// System.out.println(expected);
		Driver driver1;
		Connection connection=null;
		try {
			driver1 = new Driver();
			DriverManager.registerDriver(driver1);
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result=statement.executeQuery("select * from project");
			
			while(result.next())
			{
				if(result.getString("project_name").equals(projectname))
			{
					System.out.println("project created successfully");		
				}
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			driver.quit();
	}
	}
}
