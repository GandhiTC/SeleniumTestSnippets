package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class FramesNested
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.dezlearn.com/nested-iframes/");
		Thread.sleep(2000);
		
		driver.switchTo().frame("demo_parent_iframe");
		
		driver.switchTo().frame("demo_frame1");
		driver.findElement(By.id("u_5_6")).click();
		Thread.sleep(2000);
		
		driver.switchTo().parentFrame();
		driver.findElement(By.id("u_5_5")).click();
		
		driver.switchTo().defaultContent();
	}
}
