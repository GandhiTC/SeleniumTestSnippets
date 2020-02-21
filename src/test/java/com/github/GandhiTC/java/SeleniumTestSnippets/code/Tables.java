package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Tables
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver 	driver 			= new ChromeDriver();
		
		driver.get("http://www.cricbuzz.com/live-cricket-scorecard/18970/pak-vs-sl-2nd-t20i-pakistan-v-sri-lanka-in-uae-2017");
		
		WebElement	table			= driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
//		int 		rowcount		= table.findElements(By.cssSelector("cb-col cb-col-100 cb-scrd-itms")).size();
		int			count			= table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();
		int 		sum 			= 0;
		
		System.out.println("\r\n" + (count - 2) + "\r\n");
		
		for(int i = 0; i < (count - 2); i++)
		{
			String	value				= table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText();
			int		valueInteger		= Integer.parseInt(value);
			
			sum = sum + valueInteger;
			
			System.out.println((i + 1) + ")  " + "+" + valueInteger + " = " + sum);
		}
		
		String		Extras				= driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText();
		int			extrasValue			= Integer.parseInt(Extras);
		int			TotalSumValue		= sum + extrasValue;
		String 		ActualTotal			= driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText();
		int 		ActualTotalVAlue	= Integer.parseInt(ActualTotal);
		
		System.out.println("\r\n" + TotalSumValue);
		System.out.println(ActualTotal + "\r\n");

		if(ActualTotalVAlue == TotalSumValue)
		{
			System.out.println("Counts Match");
		}
		else
		{
			System.out.println("Count Matching Failed");
		}
		
		driver.quit();
	}
}