package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class Frames_Alerts
{
	public WebDriver driver;


	@Test
	public void Alerts1() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.switchTo().frame("iframeResult");
		
		WebElement TryItButton = driver.findElement(By.xpath("//button[contains(text(), 'Try it')]"));
		TryItButton.click();
		Thread.sleep(1500L);
		
		Alert A = driver.switchTo().alert();
		// A.accept();
		A.dismiss();
		
		Thread.sleep(1500L);
		driver.quit();
	}
}
