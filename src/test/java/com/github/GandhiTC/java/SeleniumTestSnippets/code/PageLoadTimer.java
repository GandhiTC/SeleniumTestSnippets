package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PageLoadTimer
{
	@Test
	public void RunTest()
	{
		String URL = "http://www.linkedin.com";
		System.out.println("Time limit (in milliseconds) to wait for webpage to completely load before declaring failure?");
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(s, TimeUnit.MILLISECONDS);
		
		in.close();
		
		boolean timedOut = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long start = System.currentTimeMillis();
		
		try
		{
			driver.get(URL);
		}
		catch (TimeoutException TO)
		{
			timedOut = true;
			js.executeScript("window.stop();");
			Assert.fail(String.format("Time to load %s exceeded limit of %d seconds%n%n", URL, s));
		}
		finally
		{
			long finish = System.currentTimeMillis();
			long duration = finish - start;
			
			System.out.println(DurationFormatUtils.formatDuration(duration, "S 'milliseconds'", false));
			
			driver.quit();
			
			if(!timedOut)
			{
				Assert.assertTrue(true);
			}
		}
	}
}
