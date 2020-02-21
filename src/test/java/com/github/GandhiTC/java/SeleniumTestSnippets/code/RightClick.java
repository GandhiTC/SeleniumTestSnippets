package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class RightClick
{
	public WebDriver driver;
	
	
	@FindBy(xpath="//span[@class='context-menu-one btn btn-neutral']")
	private WebElement rClickThis;
	
	@FindBy(xpath="//li[@class='context-menu-item context-menu-icon context-menu-icon-edit']")
	private WebElement lClickThis;
	
	
	@Test
	public void LetsDoIt() throws InterruptedException, AWTException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		PageFactory.initElements(driver, this);
		
		driver.manage().window().maximize();
//		driver.get("http://medialize.github.io/jQuery-contextMenu/demo.html");
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		Actions			action	= new Actions(driver);
		WebDriverWait	wait	= new WebDriverWait(driver, 3);
		
		wait.until(ExpectedConditions.visibilityOf(rClickThis));
		action.contextClick(rClickThis).perform();
		Thread.sleep(750);
		
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(lClickThis));
		Thread.sleep(750);
		
		action.moveToElement(lClickThis).perform();
		action.click().perform();
		Thread.sleep(750);
		
		//	to detect the popup in java (not webdriver)
		//	in case you want to test both ok and cancel buttons
		//	we would have to use sikuli or autoid
		Robot Robbie = new Robot();
		Robbie.keyPress(KeyEvent.VK_ENTER);
		Robbie.keyRelease(KeyEvent.VK_ENTER);
	}
}
