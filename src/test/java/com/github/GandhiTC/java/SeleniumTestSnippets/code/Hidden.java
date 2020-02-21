package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



// Enter the letters BENG
// Verify if Airport option is displayed in the suggestive box
public class Hidden
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.ksrtc.in");
		
		WebElement fromPlace = driver.findElement(By.xpath("//input[@id='fromPlaceName']"));
		fromPlace.click();
		fromPlace.sendKeys("BENG");
		Thread.sleep(1000);
		
		// Javascript DOM can extract hidden elements
		// Because Selenium cannot identify hidden elements - (Ajax implementation)
		// Also see Popups.java, FluentWaiter.java, AjaxWait.java, and Change_WebElement_Display_Style.java
		// Investigate properties of an object for hidden text using JavascriptExecutor
		
		JavascriptExecutor	js		= (JavascriptExecutor)driver;
		String				script	= "return document.getElementById(\"fromPlaceName\").value;";
		String				text	= (String)js.executeScript(script);
		
		int					i		= 1;
		int					max 	= driver.findElements(By.xpath("//ul[@id='ui-id-1']/li")).size();

		// BENGALURU INTERNATION AIRPORT
//		while(!text.equalsIgnoreCase("BENGALURU INTATION AIRPORT")) // purposely misspelled for negative testing
		while(!text.equalsIgnoreCase("BENGALURU INTERNATION AIRPORT"))
		{
			if (i > max)
			{
				break;
			}
			
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.DOWN);
			
			text = (String)js.executeScript(script);
			System.out.println(i + ")  " + text);
			
			i++;
		}
		
		if(i <= max)
		{
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.ENTER);
			System.out.println("Element found");
		}
		else
		{
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.ESCAPE);
			System.out.println("Element not found");
		}
	}
}