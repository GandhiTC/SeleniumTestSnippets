package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;



public class MinimizeBrowser
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.get("about:blank");
		System.out.println("browser opened");

		try
		{
			//	set focus on browser window
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.focus();");
			
			//	pressing keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_DOWN);
			
			//	releasing keys
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_DOWN);
			
			System.out.println("browser minimized");
		}
		catch(AWTException e1)
		{
			//	e1.printStackTrace();
			//	System.err.println("Error while minimizing!");
			Reporter.log("Error while minimizing!", true);
		}
		finally
		{
			driver.quit();
			driver = null;
			System.out.println("browser closed");
		}
	}
}
