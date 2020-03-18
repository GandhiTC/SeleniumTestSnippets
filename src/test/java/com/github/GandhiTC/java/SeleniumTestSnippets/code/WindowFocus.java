package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class WindowFocus
{
	public static synchronized void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.duckduckgo.com");
		
		((JavascriptExecutor) driver).executeScript("alert('Refocusing')");
		String alertText = "";
		
		try
		{
			Thread.sleep(1500L);
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
		
		try
		{
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
		}
		catch(NoAlertPresentException e)
		{
		}
		
		String window = driver.getWindowHandle();
		
		Dimension 	origSize 	= driver.manage().window().getSize();
		Point		origPos		= driver.manage().window().getPosition();
		
		Robot robot = null;

		try
		{
			robot = new Robot();
		}
		catch(AWTException e)
		{
			System.out.println(e.getMessage());
			return;
		}

		robot.keyPress(KeyEvent.VK_WINDOWS);
		
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		
		try
		{
			Thread.sleep(300L);
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
		
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		
		try
		{
			Thread.sleep(3000L);
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
		
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("alert('" + alertText + "')");
		driver.switchTo().alert().accept();
		driver.switchTo().window(window);
		((JavascriptExecutor) driver).executeScript("window.focus();");
		driver.manage().window().setSize(origSize);
		driver.manage().window().setPosition(origPos);
		
		if(!alertText.isEmpty())
		{
			((JavascriptExecutor) driver).executeScript("alert('" + alertText + "')");
		}
		
//		driver.quit();
	}
}
