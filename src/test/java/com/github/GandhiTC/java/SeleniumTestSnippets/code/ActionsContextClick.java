package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class ActionsContextClick
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement	btn		= driver.findElement(By.xpath("//span[text()='right click me']"));
		
		Actions		action	= new Actions(driver);
		action.contextClick(btn).perform();
		Thread.sleep(1000L);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(750L);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(750L);
		
		action.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(750L);
		
		action.sendKeys(Keys.RETURN).perform();
	}
}
