package com.sdet34l1.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is used to maintain all webdriver common actions
 * @author Niraj
 *
 */
public class WebDriverDataLibrary {
	
	static WebDriverWait wait;
	static Select select;
	 public Actions act;
	static JavascriptExecutor jse;
	static JavaLibrary javaLibrary= new JavaLibrary();
	static WebDriver driver;
	public WebDriverDataLibrary(WebDriver staticdriver) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This methos is used to launch the browser
	 * @param url
	 * @param driver
	 */
	public static void navigateApp(String url,WebDriver driver)
	{
		driver.get(url);
	}
	
	/**
	 * This method is used to maximize the browser and implicitly wait
	 * @param longTimeOut
	 * @param driver
	 */
	public static void browserSetting(long longTimeOut,WebDriver driver)
	{
		maximizebrowser(driver);
		waitImplicitlyWaitTillPageLoad(longTimeOut,driver);
	}
	 /**
	  * This method is used to wait till page load(implicitly wait)
	  * @param longTimeOut
	  * @param driver
	  */
	public static void waitImplicitlyWaitTillPageLoad(long longTimeOut,WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
	}
	
	
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public static void maximizebrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to doubli click in particular element 
	 * @param element
	 */
	public  void doubleClick(WebElement element)
	{

		act.doubleClick(element).perform();
		
	}
	
	/**
	 * This method is used to doubli click in web page
	 */
	public void doubleClick()
	{

		act.doubleClick().perform();
		
	}
	
	
	/**
	 * This method is used to mouse over on the element
	 * @param administratorIcone
	 * @param driver
	 */
	public void mouseOverOnElement(WebElement administratorIcone)
	{

		act.moveToElement(administratorIcone).perform();
		
	}
	
	/**
	 * This method is used to initialize actions class
	 * @param driver
	 */
	
	public void initializeAction(WebDriver driver)
	{
		act = new Actions(driver);
	}
	
	/**
	 * This method is user to close the browser
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();		
	}
	
	/**
	 * This method is used to wait till element is click
	 * @param element
	 */
	public static void waitUntilElementClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is used to explicitly wait till the particular elemenet is visile
	 * @param element
	 */
	public static void waitUntilElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to initialize wait instance(explicit wait)
	 * @param driver
	 * @param timeOut
	 */
	public static void explicitlyWait(WebDriver driver,long timeOut)
	{
		wait = new WebDriverWait(driver,timeOut);
	}
	
	/**
	 * This method is used to swith the window based on title 
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBesedOnTitle(WebDriver driver,String partialText )
	{
		Set<String> allid = driver.getWindowHandles();
		 for(String id:allid)
		 {
			 driver.switchTo().window(id);
			 if(driver.getTitle().contains(partialText))
			 {
				break;
				
			 } 
		 }
	}
	
	/**
	 * This method is used to select the dropdown by index
	 * @param element
	 * @param index
	 */
	public static void selectListBox(WebElement element,int index) {
		select=new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to select the dropdown by value
	 * @param element
	 * @param value
	 */
	public static void selectListBox(WebElement element,String value)
	{
		select=new Select(element);
		select.selectByValue(value);
	}
	
	/**
	 * This method is used toselect the dropdown by visible text
	 * @param visibleText
	 * @param element
	 */
	public static void slectListBox(String visibleText,WebElement element)
	{
		select=new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method is used to switch frame to another frame by using index
	 * @param driver
	 * @param index
	 */
	public static void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch frame to another frame by using webelement
	 * @param element
	 * @param driver
	 */
	public static void switchToFrame(WebElement element, WebDriver driver) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch frame to another frame by using name or id
	 * @param driver
	 * @param nameOrId
	 */
	public static void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method is used to switch back to main page  
	 * @param driver
	 */
	public static void swicthBackToMainPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to initialize the java script
	 * @param driver
	 */
	public static void initializejse(WebDriver driver)
	{
		jse=(JavascriptExecutor) driver;
	}
	
	/**
	 * This method is used to enter the data through java script
	 * @param element
	 * @param data
	 */
	public static void enterDataThroughjse(WebElement element,String data)
	{
		jse.executeScript("arguments[0].value=arguments[1]", element, data);
	}
	
	/**
	 * This method is used to click the particular element through java Script
	 * @param element
	 */
	public static void clickThoughJse(WebElement element)
	{
		jse.executeScript("arguments[0].click()", element);
	}
	
	/**
	 * This method is used to provide the url of the application through javaScript
	 * @param url
	 */
	public static void navigateAppThroughJse(String url)
	{
		jse.executeScript("window.location=arguments[0]",url);
	}
	
	/**
	 * This method is used to scroll web page from top to down through javaScript
	 * @param height
	 */
	public static void scrollToSpecificHeight(String height)
	{
		jse.executeScript("window.scrollBy(0,"+height+")");
	}
	
	/**
	 * This method is used to scroll web page from top to bottom through javaScript
	 */
	public static void scrollTopToBottom()
	{
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	/**
	 * This method is used to scroll the web page till particular element through javaScript
	 * @param element
	 */
	public static void scrollTillElement(WebElement element)
	{
		jse.executeScript("arguments[0].scrollIntoView()",element);
	}
	
	/**
	 * This method is used to take screenshot of web page
	 * @param fileName
	 * @param driver
	 * @return 
	 * @throws IOException 
	 */
	public static String takeScreenShot(String fileName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshot/"+fileName+"_"+ javaLibrary.dateTimeInFormat()+".png");
		System.out.println(dst.getAbsolutePath());
		
			FileUtils.copyFile(src, dst);
		return dst.getAbsolutePath();
		
	}
	
	/**
	 * This method is used to handle the alert popup
	 * @param driver
	 */
	public static void alertPopUpHandle(WebDriver driver)
	{
		Alert alt = driver.switchTo().alert();
		alt.accept();
		
	}
}

