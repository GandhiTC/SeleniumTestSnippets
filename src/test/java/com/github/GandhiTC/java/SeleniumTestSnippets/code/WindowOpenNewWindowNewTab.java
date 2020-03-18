package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class WindowOpenNewWindowNewTab
{
	public static synchronized void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.duckduckgo.com");
		
		try
		{
			Thread.sleep(5000L);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		
		String			origHandle	= driver.getWindowHandle();
//		WebElement 		body 		= driver.findElement(By.tagName("body"));
		
		
		//	DOES NOT WORK
//		body.click();
//		body.sendKeys(Keys.chord(Keys.CONTROL + "n"));
		
		
		//	DOES NOT WORK
//		Actions			actions		= new Actions(driver);
//		actions.click(body).build().perform();
//		actions.keyDown(Keys.CONTROL).sendKeys("n").keyUp(Keys.CONTROL).build().perform();
		
		
		//	WORKS, BUT make sure browser has focus (OS focus, not just selenium focus)
		try
		{
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_N);
			
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		catch(AWTException e)
		{
			System.err.println(e.getMessage());
		}
		
		//	convert Set<String> to List<String>
		List<String> 	handles 	= new ArrayList<>(driver.getWindowHandles());
		String			lastHandle	= handles.get(handles.size() - 1);
		
		driver.switchTo().window(lastHandle);
		driver.get("https://www.marketwatch.com");
		driver.manage().window().maximize();
		
		try
		{
			Thread.sleep(5000L);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		
		//	force link to open in new tab
		driver.findElement(By.xpath("//a[@href[contains(., 'latest-news')]]")).sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));	//	use Shift + Return to open in new window
		
		//	update list
						handles 	= new ArrayList<>(driver.getWindowHandles());
		String			tabHandle	= handles.get(handles.size() - 1);
		
		driver.switchTo().window(tabHandle);
		
		try
		{
			Thread.sleep(10000L);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		
		driver.close();
		
		try
		{
			Thread.sleep(3000L);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		
		driver.switchTo().window(lastHandle);
		driver.close();
		
		try
		{
			Thread.sleep(3000L);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
		
		driver.switchTo().window(origHandle);
		driver.close();
	}
}
