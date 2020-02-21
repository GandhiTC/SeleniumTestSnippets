package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class DragAndDrop
{
	@Test
	public void DoTheWork() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver	driver	= new ChromeDriver();
		Actions		action	= new Actions(driver);
		driver.manage().window().maximize();
		
		
		//	Test 1
		driver.get("http://jqueryui.com/draggable/");
		
		WebElement		InnerFrame	= driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", InnerFrame);
		driver.switchTo().frame(InnerFrame);
		
		WebElement		element		= driver.findElement(By.xpath("//div[@id='draggable']"));
		WebDriverWait	wait		= new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
		action.dragAndDropBy(element, 50, 75).build().perform();
		action.dragAndDropBy(element, 75, 50).build().perform();
		action.dragAndDropBy(element, -125, -125).build().perform();
		
		Thread.sleep(1500L);
		
		
		//	Test 2
		driver.get("http://jqueryui.com/demos/slider/");
		
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		WebElement slider = driver.findElement(By.xpath("//div[@id='slider']/span"));

		action.dragAndDropBy(slider, 90, 0).build().perform();
		action.dragAndDropBy(slider, -60, 0).build().perform();
		action.dragAndDropBy(slider, 30, 0).build().perform();
		
		Thread.sleep(1500L);
		
		
		//	Test 3
		driver.get("http://beej.us/blog/data/drag-n-drop/");
		
		WebElement Goat0 = driver.findElement(By.id("goat0"));
		WebElement Dropzone1 = driver.findElement(By.id("dropzone1"));
		
		action.dragAndDrop(Goat0, Dropzone1).perform();
		
		Thread.sleep(3000L);
		
		
		driver.quit();
	}
}
