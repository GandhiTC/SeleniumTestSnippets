package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



/*
keytool -import -alias example -keystore  "C:\Program Files\Java\jdk1.8.0_231\jre\lib\security\cacerts" -file example.cer
keytool -import -alias example -keystore  "C:\Program Files\Java\jre1.8.0_241\lib\security\cacerts" -file example.cer

keytool -delete -alias example -keystore "C:\Program Files\Java\jdk1.8.0_231\jre\lib\security\cacerts"
keytool -delete -alias example -keystore "C:\Program Files\Java\jre1.8.0_241\lib\security\cacerts"

default password is: changeit



https://stackoverflow.com/questions/21076179/pkix-path-building-failed-and-unable-to-find-valid-certification-path-to-requ

https://docs.oracle.com/cd/E19683-01/817-2874/6migoia18/index.html
 */



public class BrokenLinks1
{
	public static void main(String[] args)
	{
		String					homePage	= "https://www.zlti.com";
		String					url			= "";
		HttpURLConnection		huc			= null;
		int						respCode	= 200;
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver				driver 		= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get(homePage);
		
		List<WebElement>		links		= driver.findElements(By.tagName("a"));
		Iterator<WebElement>	it			= links.iterator();

		while(it.hasNext())
		{
			url = it.next().getAttribute("href");
			System.out.println(url);

			if((url == null) || url.isEmpty())
			{
				System.out.println("URL is either not configured for anchor tag or it is empty\r\n");
				continue;
			}

			if(!url.startsWith(homePage))
			{
				System.out.println("URL belongs to another domain, skipping it.\r\n");
				continue;
			}

			try
			{
				huc 		= (HttpURLConnection)(new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode 	= huc.getResponseCode();

				if(respCode >= 400)
				{
					System.err.println(url + " is a broken link\r\n");
				}
				else
				{
					System.out.println(url + " is a valid link\r\n");
				}
			}
			catch(MalformedURLException e)
			{
				System.err.println(e.getMessage() + "\r\n");
			}
			catch(IOException e)
			{
				System.err.println(e.getMessage() + "\r\n");
			}
		}
		
		driver.quit();
	}
}
