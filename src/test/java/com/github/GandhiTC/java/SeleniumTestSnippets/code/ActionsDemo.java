package com.github.GandhiTC.java.SeleniumTestSnippets.code;


//import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class ActionsDemo
{

	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
//		Actions    jackson 		= new Actions(driver);
		WebElement frameElement = null;
		WebElement draggable    = null;
		WebElement droppable    = null;
		
		
//		driver.get("https://www.adidas.com/us");
//		jackson.moveToElement(driver.findElement(By.cssSelector("a[manual_cm_sp='header-_-Men']"))).build().perform();
//		Thread.sleep(2000);
//		jackson.moveToElement(driver.findElement(By.cssSelector("a[manual_cm_sp='header-_-Brands']"))).build().perform();
//		Thread.sleep(2000);
		
		
//		driver.get("http://jqueryui.com/demos/draggable/");
////		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
////		System.out.println(iframes.size() + " iframes on this webpage");
////		frameElement = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
//		frameElement = driver.findElement(By.className("demo-frame"));
//		driver.switchTo().frame(frameElement);
//		draggable    = driver.findElement(By.id("draggable"));
//		new Actions(driver).dragAndDropBy(draggable, 120, 120).dragAndDropBy(draggable, -60, -60).build().perform();
//		Thread.sleep(2000);
        
		
		driver.get("http://jqueryui.com/demos/droppable/");
		frameElement = driver.findElement(By.cssSelector("iframe.demo-frame"));
		driver.switchTo().frame(frameElement);
		draggable    = driver.findElement(By.id("draggable"));
		droppable    = driver.findElement(By.id("droppable"));
		new Actions(driver).dragAndDrop(draggable, droppable).build().perform();
		Thread.sleep(2000);
		
		
//		driver.get("http://jqueryui.com/demos/selectable/");
//		frameElement = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
//		driver.switchTo().frame(frameElement);
//		List<WebElement> listItems = driver.findElements(By.cssSelector("ol#selectable *"));
//		jackson      = new Actions(driver).clickAndHold(listItems.get(1)).clickAndHold(listItems.get(2)).click();
//		jackson.build().perform();
//		Thread.sleep(2000);
		
		
//		driver.get("http://jqueryui.com/demos/slider/");
//		frameElement = driver.findElement(By.cssSelector("iframe[class='demo-frame']"));
//		driver.switchTo().frame(frameElement);
//		draggable    = driver.findElement(By.className("ui-slider-handle"));
//		for(int i = 0; i < 10; i++)
//		{
//			new Actions(driver).dragAndDropBy(draggable, 50, 0).dragAndDropBy(draggable, -25, 0).build().perform();
//		}
//		Thread.sleep(2000);
		
		
		driver.get("http://www.amazon.com");
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
//		int offsetX = ((searchBox.getRect().width / 2) - 30) * -1;
//		int offsetY = 10;
//		new Actions(driver).moveToElement(searchBox, offsetX, offsetY).click().keyDown(Keys.SHIFT).sendKeys("h").keyUp(Keys.SHIFT).sendKeys("ello").pause(750).doubleClick().sendKeys(Keys.ESCAPE).contextClick().build().perform();
		new Actions(driver).moveToElement(searchBox).click().sendKeys("Hello").pause(1000).sendKeys(Keys.ESCAPE).doubleClick().keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
//		new Actions(driver).moveToElement(searchBox).click().sendKeys("Hello").pause(1000).sendKeys(Keys.ESCAPE).doubleClick().build().perform();
//		new Actions(driver).pause(1000).sendKeys(Keys.ESCAPE).contextClick(searchBox).build().perform();
//		new Actions(driver).pause(1000).sendKeys(Keys.ESCAPE).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		
		
//		driver.quit();
	}

}
