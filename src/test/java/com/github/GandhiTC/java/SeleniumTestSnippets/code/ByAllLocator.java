package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;



//	"ByAll" is misleading, "ByAny" would be more appropriate
//	considering WebDriver will search using all listed By's,
//	but only until any/the first By returns a valid value,
//	all remaining By's are then skipped.



public class ByAllLocator
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver  driver 			= new ChromeDriver();
		
		driver.get("http://www.google.com");
		
		WebElement element = driver.findElement(new ByAll(By.cssSelector("div#searchform input[name='q']"), By.xpath("//div[@id='searchform']//input[@name='q']")));
		
		System.out.println(element);
		
		driver.quit();
	}
}
