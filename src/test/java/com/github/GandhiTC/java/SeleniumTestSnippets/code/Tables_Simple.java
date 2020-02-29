package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Tables_Simple
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		WebElement			table	= driver.findElement(By.id("customers"));
		List<WebElement>	rows	= table.findElements(By.tagName("tr"));

		for(WebElement row : rows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));

			for(WebElement col : cols)
			{
				System.out.print(col.getText() + "\t\t");
			}
			
			System.out.println();
		}
		
		driver.quit();
	}
}