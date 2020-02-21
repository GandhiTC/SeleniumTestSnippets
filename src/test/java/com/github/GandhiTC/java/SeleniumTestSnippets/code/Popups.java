package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Popups
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.get("http://www.qaClickAcademy.com");
		
		/*
		Be careful when using IsDisplayed with in-page "pop-ups"
		Even though the pop-up may not be visible on the actual web page, it will still be visible on the HTML DOM
		therefore IsDisplayed will return true
		To get around this, use FindElements
		check for the size of the returned list
		if size is greater than 0, then there is something there on the actual web page
		also see Hidden.java and FluentWaiter.java
		*/
		
		List<WebElement> noThanks = driver.findElements(By.xpath("//button[text()='NO THANKS']"));
		
		if(noThanks.size() > 0)
		{
			noThanks.get(0).click();
			System.out.println("\"No Thanks\" clicked");
		}
		else
		{
			System.out.println("There was no popup");
		}
		
		driver.quit();
	}
}