package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



public class WaitForPageToLoad
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver 	driver 	= new ChromeDriver();
		
		driver.get("https://www.marketwatch.com");
		waitForPageToLoad(driver);
		
		System.out.println("Page has finished loading, exiting in 2 seconds.");
		Thread.sleep(2000L);
		
		driver.quit();
	}
	
	
	public static void waitForPageToLoad(WebDriver driver)
	{
		ExpectedCondition<Boolean>	pageLoadCondition;
		Wait<WebDriver>				wait;
															
		pageLoadCondition		= 	new ExpectedCondition<Boolean>()
									{
									@Override
										public Boolean apply(WebDriver driver)
									{
										return ((JavascriptExecutor)driver)
												.executeScript("return document.readyState")
												.equals("complete");
									}
									};

		wait					= 	new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(30))
									.pollingEvery(Duration.ofSeconds(1));

		wait.until(pageLoadCondition);
	}
}
