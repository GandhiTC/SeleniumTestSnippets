package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Scrolling
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver 	driver 	= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("http://www.jqueryscript.net/demo/Responsive-jQuery-Dual-Select-Boxes-For-Bootstrap-Bootstrap-Dual-Listbox/");


		//	Scroll until element comes completely into view
		WebElement	divBox	= driver.findElement(By.xpath("//div[@class='box1 col-md-6 filtered']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", divBox);
		Thread.sleep(1000L);


		//	Scroll to end of page
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight)");
		Thread.sleep(1000L);
		
		
		driver.get("https://wn.com/Countries-of-the-world/wikipedia");
		
		
		//	Scroll down 750 pixels from top of page
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 750)", "");
		Thread.sleep(1000L);
		
		
		//	Scroll down to 3500 pixels before end of page
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight - 3500)");
		Thread.sleep(1000L);
		
		
		//	Get current scroll position
//		long yPos = (long)((JavascriptExecutor) driver).executeScript("var scrollPos = window.scrollY || window.scrollTop || document.getElementsByTagName(\"html\")[0].scrollTop; return scrollPos;", "");
		long yPos = (long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset", "");
//		long xPos = (long)((JavascriptExecutor) driver).executeScript("return window.pageXOffset", "");
		System.out.println(yPos);
		Thread.sleep(1000L);
		
		//	Scroll to top of page
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -" + yPos + ")", "");
		Thread.sleep(1000L);
		
		
		driver.quit();
	}
}
