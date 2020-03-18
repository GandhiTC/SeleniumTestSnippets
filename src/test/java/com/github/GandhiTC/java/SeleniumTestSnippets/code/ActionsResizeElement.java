package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class ActionsResizeElement
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://jqueryui.com/resizable/");
		
		driver.switchTo().frame(0);
		
		WebElement	source	= driver.findElement(By.cssSelector("div.ui-icon-gripsmall-diagonal-se"));
		Actions		action	= new Actions(driver);
		
		Thread.sleep(2000);
		action.clickAndHold(source).moveByOffset(75, 75).release().build().perform();
	}
}
