package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Alerts
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://demo.guru99.com/test/delete_customer.php");
		driver.findElement(By.name("cusid")).sendKeys("53920");
		driver.findElement(By.name("submit")).submit();
		Thread.sleep(2000L);

		if(isAlertPresent(driver) == true)
		{
			Alert alerter = driver.switchTo().alert();
			System.out.println(alerter.getText());
			alerter.dismiss();
			driver.switchTo().defaultContent();
		}
		
		((JavascriptExecutor) driver).executeScript("alert('The test script has completed, getting ready to exit.');");
		Thread.sleep(2000L);
		
		driver.quit();
	}


	public static boolean isAlertPresent(WebDriver driver)
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException Ex)
		{
			driver.switchTo().defaultContent();
			System.out.println("\n\n" + Ex + "\n\n");
			return false;
		}
	}
}