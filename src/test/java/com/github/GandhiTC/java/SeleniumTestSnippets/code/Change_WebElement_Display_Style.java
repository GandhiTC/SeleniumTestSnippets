package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



//	Also see Popups.java, FluentWaiter.java, AjaxWait.java, and Hidden.java



public class Change_WebElement_Display_Style
{
	public WebDriver driver;


	@Test
	public void DoSomething() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		
		//	Test 1
		driver.get("http://www.qualitytesting.info");
		
		WebElement diver = driver.findElement(By.xpath("//li[@id='xg_tab_xn9']//div[@class='xg_subtab']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'z-index: 100; position: absolute; display: block; left: 616px; top: 261px;');", diver);
		
		Thread.sleep(2000L);
		driver.findElement(By.xpath("//span[contains(text(), 'Poll of the Day')]")).click();
		
		Thread.sleep(1000);
		driver.quit();
	}
}
