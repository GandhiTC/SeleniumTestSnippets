package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;



public class JQuery_Injection
{
	static WebDriver			driver;
	static JavascriptExecutor 	js;
	
	
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver 	= new ChromeDriver();
		js 		= (JavascriptExecutor) driver;
		
		driver.get("http://www.columbia.edu/~fdc/sample.html");
		
		if (!jQueryLoaded())
		{
			injectjQuery();
		}
	}


	public static Boolean jQueryLoaded()
	{
		Boolean loaded;

		try
		{
			loaded = (Boolean)js.executeScript("return jQuery()!=null");
		}
		catch(WebDriverException e)
		{
			loaded = false;
		}
		return loaded;
	}


	public static void injectjQuery()
	{
		js.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');" + "newScript.type = 'text/javascript';"
				+ "newScript.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
}