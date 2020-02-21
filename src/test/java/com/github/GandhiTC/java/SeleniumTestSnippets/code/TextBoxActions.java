package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class TextBoxActions
{
	public WebDriver driver;
	
	
	public TextBoxActions()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.jqueryscript.net/demo/jQuery-Plugin-To-Generate-Repeatable-Input-Fields-AddRemoveTextbox/");
	}
	
	
	@Test
	public void textBoxProgram()
	{
		WebElement txt0 = driver.findElement(By.id("txt[0]"));
		txt0.sendKeys("Tejas");
		
		String value = txt0.getAttribute("value");
		System.out.println("The value entered in the \"txt0\" textbox is:  " + value);
		
		String ti = txt0.getAttribute("tabindex");
		System.out.println("The tabindex of the \"txt0\" textbox is:  " + ti);
		
		String height = txt0.getCssValue("height");
		System.out.println("The height of the \"txt0\" textbox is:  " + height);
		
		driver.quit();
	}
}
