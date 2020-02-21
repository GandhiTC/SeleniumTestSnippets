package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.time.Duration;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class CaptureJavascriptErrorsChrome
{
	private static WebDriver driver;
	
	
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		ChromeOptions		chOptions	= new ChromeOptions();
		LoggingPreferences	logs		= new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.WARNING);
		chOptions.setCapability(CapabilityType.LOGGING_PREFS, logs);
		driver = new ChromeDriver(chOptions);
		
//		driver.get("http://www.htmlgoodies.com/html5/tutorials/introducing-html-5-web-workers-bringing-multi-threading-to-javascript.html");
		driver.get("https://www.tutorialspoint.com/javascript/javascript_error_handling.htm");
	}


	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}


	@Test
	public void returnJavascriptErrors()
	{
		waitForPageToLoad();
		
		List<String>		errorTypes	= new ArrayList<String>();
		LogEntries			logEntries	= driver.manage().logs().get(LogType.BROWSER);
		List<String>		jsErrors	= new ArrayList<String>();
		
		errorTypes.add("SyntaxError");
		errorTypes.add("EvalError");
		errorTypes.add("ReferenceError");
		errorTypes.add("RangeError");
		errorTypes.add("TypeError");
		errorTypes.add("URIError");

		for (LogEntry entry : logEntries)
		{
			for(String errorType : errorTypes)
			{
				String entryAsString = entry.toString();
//				String entryAsString = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
				
				if(entryAsString.contains(errorType))
				{
					jsErrors.add(entryAsString);
				}
			}
		}

		if(!jsErrors.isEmpty())
		{
			System.out.println("### Start displaying errors ###");
			
			for(String error : jsErrors)
			{
				System.out.println(error);
			}

			System.out.println("### End displaying errors ###");
		}
		else
		{
			System.out.println("No javascript errors found on this page.");
		}
	}
	
	
	public void waitForPageToLoad()
	{
		ExpectedCondition<Boolean>	pageLoadCondition;
		Wait<WebDriver>				wait;
															
		pageLoadCondition	= 	new ExpectedCondition<Boolean>()
								{
									@Override
									public Boolean apply(WebDriver driver)
									{
										return ((JavascriptExecutor)driver)
												.executeScript("return document.readyState")
												.equals("complete");
									}
								};

		wait				= 	new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(30))
								.pollingEvery(Duration.ofSeconds(1));

		wait.until(pageLoadCondition);
	}
}
