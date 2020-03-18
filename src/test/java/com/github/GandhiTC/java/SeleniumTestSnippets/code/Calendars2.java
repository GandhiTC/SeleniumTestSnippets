package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Calendars2
{
	public static void main(String[] args) throws Exception
	{
		String	expMonth	= "August";	//	"August 2020";
		String	expDate		= "15";
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.trivago.com/");
		driver.findElement(By.xpath("//button[@data-qa='calendar-checkin']")).click();
		
		String	currMonth	= driver.findElement(By.cssSelector("th.cal-heading-month")).getText();
		int		c			= 0;

		while(!currMonth.contains(expMonth) && (c < 12))
		{
			driver.findElement(By.cssSelector("button.cal-btn-next")).click();
			Thread.sleep(750L);
			
			currMonth = driver.findElement(By.cssSelector("th.cal-heading-month")).getText();
			c++;
		}
		
		List<WebElement> dates = driver.findElements(By.cssSelector("table.cal-month td"));

		for(WebElement date : dates)
		{
			String x = date.getText().trim();

			if(x.equals(expDate))
			{
				date.click();
				break;
			}
		}
	}
}
