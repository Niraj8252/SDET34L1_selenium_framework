package com.vtiger.practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationTest {
	public static void main(String[] args) {
		Random r = new Random();
		int num = r.nextInt(1000);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.cssSelector("[name='accountname']")).sendKeys("TestYantra"+num);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String expected_name =driver.findElement(By.className("dvHeaderText")).getText();
		System.out.println(expected_name);
		
		if(expected_name.contains("TestYantra"))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		
	}

}
