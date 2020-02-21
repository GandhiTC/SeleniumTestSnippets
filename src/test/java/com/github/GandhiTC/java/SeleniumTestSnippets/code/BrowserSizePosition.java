package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.Toolkit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BrowserSizePosition
{
	public static void main(String[] args) throws InterruptedException
	{
		java.awt.Dimension	screenSize		= Toolkit.getDefaultToolkit().getScreenSize();
		int					screenHeight	= (int)(screenSize.height * .764);
		int					screenWidth		= (int)(screenHeight * 1.618);
		
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(screenWidth, screenHeight));
		driver.get("https://www.cleartrip.com/");
		
		Thread.sleep(3000L);
		driver.quit();
	}
}
