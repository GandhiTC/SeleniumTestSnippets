package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class ActionsMouseHover
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.actitime.com/");
		
		Actions		action	= new Actions(driver);
		WebElement	clients	= driver.findElement(By.linkText("Clients"));
		
		action.moveToElement(clients).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("IT")).click();
		Thread.sleep(2000);
		
		System.out.println(driver.getCurrentUrl());
	}
}
