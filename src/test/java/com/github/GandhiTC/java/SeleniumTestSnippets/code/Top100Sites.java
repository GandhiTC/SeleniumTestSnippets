package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class Top100Sites
{
	@Test
	public void GetW3List()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://en.wikipedia.org/wiki/List_of_most_popular_websites");
		
		List<WebElement>	SitesElementsList	= driver.findElements(By.xpath("//tr//td[2]"));
		List<WebElement>	OldSitesList		= new ArrayList<WebElement>();
		OldSitesList.addAll(SitesElementsList);
		List<String> 		SitesList 			= new ArrayList<String>(OldSitesList.size());

		for(WebElement myWE : OldSitesList)
		{
			try
			{
				if((!SitesList.contains(myWE.getText())))
				{
					SitesList.add(myWE.getText());
					continue;
				}
			}
			catch(StringIndexOutOfBoundsException e)
			{
				continue;
			}
		}
		
		Collections.sort(SitesList);
		// return SitesList;

		for(String MyList : SitesList)
		{
			System.out.println("http://www." + MyList);
		}
		
		driver.quit();
	}
}
