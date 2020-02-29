package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class BrokenLinks2
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver			driver 			= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/test/newtours/");
		
		String 				underConsTitle 	= "Under Construction: Mercury Tours";
		WebElement			container		= driver.findElement(By.xpath("//div[2]/table"));
		List<WebElement>	linkElements	= container.findElements(By.tagName("a"));
		String[]			linkTexts		= new String[linkElements.size()];
		int					i				= 0;

		//	extract the link texts of each link element
		for(WebElement e : linkElements)
		{
			linkTexts[i] = e.getText();
			i++;
		}
		
		//	test each link
		for(String t : linkTexts)
		{
			driver.findElement(By.linkText(t)).click();

			if(driver.getTitle().equals(underConsTitle))
			{
				System.out.println("\"" + t + "\"" + " is under construction.\r\n");
			}
			else
			{
				System.out.println("\"" + t + "\"" + " is working.\r\n");
			}
			
			driver.navigate().back();
		}
		
		driver.quit();
	}
}
