package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class CaseInsensitive
{
	@Test
	public void TestCase()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://my.hostmonster.com/cgi/help/594");
		
		String textToLookFor = "500 InTeRnAL sErVeR ErRoR";
		lookForText(driver, "https://my.hostmonster.com/cgi/help/594", textToLookFor);
		lookForText(driver, "http://www.facebook.com", textToLookFor);
		
		driver.quit();
	}


	public void lookForText(WebDriver thisDriver, String thisURL, String thisText)
	{
		try
		{
			thisDriver.get(thisURL);

			if(thisDriver.findElement(
					By.xpath("//*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
							+ thisText.toLowerCase() + "')]")) != null)
			{
				System.out.println(thisURL + " ... text found");
			}
		}
		catch(Exception ex)
		{
			System.out.println(thisURL + " ... text NOT found");
		}
	}
}
