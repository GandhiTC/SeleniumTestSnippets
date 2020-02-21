package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Scope
{
	public static void main(String[] args) throws InterruptedException
	{
		//  1. Give me the count of links on the page.
		//  2. Count of footer section.
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.get("http://qaclickacademy.com/practice.php");
		
		String homeTitle = driver.getTitle();
		
		//	1. Get count of links on the page.
		System.out.println(driver.findElements(By.tagName("a")).size());
		
		//	Limiting webdriver scope
		WebElement footerdriver = driver.findElement(By.id("gf-BIG"));
		
		//	2. Get count of links in the footer section.
		System.out.println(footerdriver.findElements(By.tagName("a")).size());
		
		//	Further limiting webdriver scope
		WebElement columndriver = footerdriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
		
		//	3. Get count of links in column.
		System.out.println(columndriver.findElements(By.tagName("a")).size());

		//	4. Click on each link in the column and check if the pages are opening.
		for(int i = 1; i < columndriver.findElements(By.tagName("a")).size(); i++)
		{
			String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			columndriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
			Thread.sleep(5000L);
		}

		Set<String>			abc	= driver.getWindowHandles();
		Iterator<String>	it	= abc.iterator();

		while(it.hasNext())
		{
			driver.switchTo().window(it.next());
			
			if(!driver.getTitle().equalsIgnoreCase(homeTitle))
			{
				System.out.println(driver.getTitle());
				driver.close();
			}
		}
	}
}