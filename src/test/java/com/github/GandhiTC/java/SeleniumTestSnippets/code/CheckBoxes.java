package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;



public class CheckBoxes
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://qaclickacademy.com/practice.php");
		WebElement firstChkBox = driver.findElement(By.cssSelector("input#checkBoxOption1"));
		
		firstChkBox.click();
		Assert.assertTrue(firstChkBox.isSelected());
		
		firstChkBox.click();
		Assert.assertFalse(firstChkBox.isSelected());
		
		List<WebElement>	allChkBoxes		= driver.findElements(By.xpath("//input[@type='checkbox']"));
		int					countChkBoxes	= allChkBoxes.size();
		
		System.out.println("Number of checkbox elements on this page:  " + countChkBoxes);
		driver.quit();
	}
}