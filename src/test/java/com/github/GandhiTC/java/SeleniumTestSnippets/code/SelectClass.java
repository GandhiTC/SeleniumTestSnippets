package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;



public class SelectClass
{
	public WebDriver driver;
	
	public SelectClass()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://www.jqueryscript.net/demo/Responsive-jQuery-Dual-Select-Boxes-For-Bootstrap-Bootstrap-Dual-Listbox/");
	}
	

	@Test
	public void multiSelectBox() throws InterruptedException
	{
		WebElement	divBox		= driver.findElement(By.xpath("//div[@class='box1 col-md-6 filtered']"));
		Select 		demo2 		= new Select(driver.findElement(By.id("bootstrap-duallistbox-nonselected-list_duallistbox_demo2")));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", divBox);
		
		demo2.selectByIndex(0);
		Thread.sleep(750L);
		demo2.selectByValue("option8");
		Thread.sleep(750L);
		demo2.selectByVisibleText("Option 9");
		Thread.sleep(750L);
//		demo2.deselectAll();
//		Thread.sleep(750L);
		demo2.deselectByIndex(2);
		Thread.sleep(750L);
		demo2.deselectByValue("option7");
		Thread.sleep(750L);
		demo2.deselectByVisibleText("Option 8");
		
		Thread.sleep(3000L);
		driver.quit();
	}
}
