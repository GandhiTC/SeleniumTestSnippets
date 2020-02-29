package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Tooltip_Simple
{
	@Test
	public void verifyTooltipText() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver 	driver 			= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/test/social-icon.html");
		Thread.sleep(1000L);
		
		String expectedTooltip = "Github";
        
        // Find the Github icon at the top right of the header
        WebElement github = driver.findElement(By.xpath(".//a[@class='github']"));
        
        //	Get the value of the "title" attribute of the github icon
        String actualTooltip = github.getAttribute("title");
        
        //	Assert the tooltip's value is as expected
		if(actualTooltip.equals(expectedTooltip))
		{
			System.out.println("Test Case Passed");
		}
		else
		{
			Assert.assertTrue(false);
		}
		
		driver.quit();
	}
}
