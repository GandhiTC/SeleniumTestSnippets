package com.github.GandhiTC.java.SeleniumTestSnippets.code;



//import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class ListElements
{
	public WebDriver driver;
	
	
	@Test
	public void ListTheseElements() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver 			driver 			= new ChromeDriver();
		
		driver.get("http://2008.kelvinluck.com/assets/jquery/datePicker/v2/demo/datePicker.html");
		
		//	click on the small calendar icon and add dates to list of WebElements
		driver.findElement(By.xpath("//input[@id='date1']/..//a[@class='dp-choose-date']")).click();
		List<WebElement>	myElements		= driver.findElements(By.xpath("//table[@class='jCalendar']//tr//td"));
		
		
		//	Method 1 : click on the calendar element that uses the class attribute value which points to the current day's date
		for(WebElement date : myElements)
		{
			if(date.getAttribute("class").toLowerCase().contains("today"))
			{
				date.click();
				break;
			}
		}
		
		
		//	Method 2 : click on the calendar element whose text matches the current day's date
//		Calendar			cal				= Calendar.getInstance();
//		int					dayOfMonth		= cal.get(Calendar.DAY_OF_MONTH);
//		String				dayOfMonthStr	= String.valueOf(dayOfMonth);
//
//		for(WebElement e : myElements)
//		{
//		 	if(e.getText().equals(dayOfMonthStr))
//		 	{
//		 		e.click();
//		 		break;
//		 	}
//		}
		
		
		//	Method 3 : same as Method 2, but loops through array indexes of WebElements rather than looping through WebElements themselves
//		WebElement[] 		arrDates 		= myElements.toArray(new WebElement[myElements.size()]);
//
//		for(int i = 0; i < arrDates.length; i++)
//		{
//			if(arrDates[i].getText().equals(dayOfMonthStr))
//			{
//				arrDates[i].click();
//				break;
//			}
//		}
		
		
		Thread.sleep(3000L);
		driver.quit();
	}
}
