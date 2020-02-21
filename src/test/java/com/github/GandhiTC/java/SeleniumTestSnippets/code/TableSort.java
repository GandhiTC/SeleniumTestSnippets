package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;



public class TableSort
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		WebElement vegFruitNameHeader = driver.findElement(By.cssSelector("tr th:nth-child(2)"));

		vegFruitNameHeader.click();
		vegFruitNameHeader.click();	//	comment out this line if you want to test reverse order below
		
		List<WebElement> firstSortableColList	= driver.findElements(By.cssSelector("tr td:nth-child(2)"));
		ArrayList<String> originalList			= new ArrayList<String>();
		ArrayList<String> copiedList			= new ArrayList<String>();

		for(int i = 0; i < firstSortableColList.size(); i++)
		{
			originalList.add(firstSortableColList.get(i).getText());
		}

		for(int i = 0; i < originalList.size(); i++)
		{
			copiedList.add(originalList.get(i));
		}

		Collections.sort(copiedList);
		//	Collections.reverse(copiedList);	//	comment out the 2nd click() command above if you want to test reverse order
		
		System.out.println("\r\n*******************\r\nSorted Table List\r\n*******************");
		for(String s1 : originalList)
		{
			System.out.println(s1);
		}

		System.out.println("\r\n*******************\r\nExpected List\r\n*******************");

		for(String s2 : copiedList)
		{
			System.out.println(s2);
		}

		driver.quit();
		Assert.assertTrue(originalList.equals(copiedList));
	}
}