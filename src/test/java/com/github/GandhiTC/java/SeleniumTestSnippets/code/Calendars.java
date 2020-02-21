package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Calendars
{
	public static void main(String[] args)
	{
		String	chosenMonth	= "April";
		String	chosenDate	= "21";
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.path2usa.com/travel-companions/");
		
		//	click on date picker
		driver.findElement(By.xpath(".//*[@id='travel_date']")).click();

		//	keep clicking on next month arrow until current month is April
		while(!driver.findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText().contains(chosenMonth))
		{
			driver.findElement(By.cssSelector("[class='datepicker-days'] th[class='next']")).click();
		}
		
		List<WebElement>	dates	= driver.findElements(By.className("day"));
		int					count	= dates.size();

		for(int i = 0; i < count; i++)
		{
			String text = dates.get(i).getText();

			if(text.equals(chosenDate))
			{
				dates.get(i).click();
				break;
			}
		}
	}
}
