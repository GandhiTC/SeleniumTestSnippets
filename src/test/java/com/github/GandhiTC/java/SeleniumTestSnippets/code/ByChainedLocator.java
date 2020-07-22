package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;



public class ByChainedLocator
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver  driver 			= new ChromeDriver();
		
		driver.get("http://www.google.com");
		
		WebElement cssElement 		= driver.findElement(By.cssSelector("div#searchform input[name='q']"));
		WebElement xpathElement 	= driver.findElement(By.xpath("//div[@id='searchform']//input[@name='q']"));
		WebElement chainedElement 	= driver.findElement(new ByChained(By.id("searchform"), By.name("q")));
		
		if(cssElement.equals(xpathElement))
		{
			System.out.println("CSS and Xpath elements are equal");
		}
		else
		{
			System.out.println("CSS and Xpath are not the same");
		}
		
		if(cssElement.equals(chainedElement))
		{
			System.out.println("CSS and ByChained are equal");
		}
		else
		{
			System.out.println("CSS and ByChained are not the same");
		}
		
		if(xpathElement.equals(chainedElement))
		{
			System.out.println("Xpath and ByChained are equal");
		}
		else
		{
			System.out.println("Xpath and ByChained are not the same");
		}
		
		driver.quit();
	}
}
