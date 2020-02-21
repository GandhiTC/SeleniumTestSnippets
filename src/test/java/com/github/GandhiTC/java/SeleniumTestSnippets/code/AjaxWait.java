package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class AjaxWait
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.itgeared.com/demo/1506-ajax-loading.html");
		
//		driver.findElement(By.xpath("//a[@href='javascript: void(0);loadAjax();']")).click();
		driver.findElement(By.linkText("Click to load get data via Ajax!")).click();
		
		WebElement results = driver.findElement(By.cssSelector("div#results"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(results));
		
		String resultsText = results.getText();
		Assert.assertEquals(resultsText, "Process completed! This response has been loaded via the Ajax request directly from the web server, without reoading this page.");
		System.out.println(resultsText);
		
		driver.quit();
	}
}