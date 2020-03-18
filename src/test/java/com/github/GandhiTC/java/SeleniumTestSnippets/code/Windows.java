package com.github.GandhiTC.java.SeleniumTestSnippets.code;



//import java.util.Iterator;
//import java.util.Set;
//import java.util.ArrayList;
//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
This code will open the link in new Tab.
	String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
	driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

This code will open empty new Tab.
	String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
	driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpeninNewTab);

This code emulates pressing Control+T on the keyboard, which opens a new tab, its better than the previous code
	WebElement body = driver.FindElement(By.TagName("body"));
	body.SendKeys(Keys.Control + 't');
	
	to open a new window, use Control + N
	to open a link in a new window. use Keys.chord(Keys.SHIFT, Keys.RETURN)
*/



public class Windows
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		System.out.println("Test started");
		
		driver.get("http://demo.automationtesting.in/Windows.html");
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(1024,650));
		
		System.out.println("Window positioned and sized\n");
		
		String	homeHandle	= driver.getWindowHandle();
		String	homeTitle	= driver.getTitle();

		// Find and click on a link
//		driver.findElement(By.xpath("//*[@id='Tabbed']/a/button")).click();
		driver.findElement(By.cssSelector("#Tabbed a button")).click();
		System.out.println("Web link clicked\n");
		
		
		
		// Print each window's handle and title using a foreach loop, java 1.8+
		int[] numArray = {1};
		
		driver.getWindowHandles().forEach(handle->
		{
			int x = numArray[0];
			
			//	for use with selenium 2.53.1
//			com.google.common.base.Predicate<WebDriver> myPredicate = webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
//			new WebDriverWait(driver, 15).until(myPredicate);
			
			//	for use with selenium 3.141.59
			new WebDriverWait(driver, 15).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			driver.switchTo().window(handle);
			
			String title = driver.getTitle();
			System.out.println("Window " + x + " : \"" + title + "\" : " + handle);
//			System.out.println("\t" + x + ") " + handle);
//			System.out.println("\t" + x + ") " + title + "\n");
			
			x++;
			numArray[0] = x;
		});
		
		
		
//		// Print each window's handle and title using a for loop with index
//		System.out.println("The set of all window handles and titles:");
//
//		List<String> handles = new ArrayList<>(driver.getWindowHandles());
//
//		for(int i = 0; i < handles.size(); i++)
//		{
//			new WebDriverWait(driver, 15).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//
//			String handle = handles.get(i);
//
//			driver.switchTo().window(handle);
//			System.out.println("\t" + (i+1) + ") " + handle);
//			System.out.println("\t" + (i+1) + ") " + driver.getTitle() + "\n");
//		}
			
			
			
//		//	Print each window's handle and title using an iterator
//		Set<String> handles = driver.getWindowHandles();
//		Iterator<String> it = handles.iterator();
//		String parentID		= it.next();
//		String childID		= it.next();
//		driver.switchTo().window(childID);
//		System.out.println("Switched to child window");
//		System.out.println(driver.getTitle());
//		driver.switchTo().window(parentID);
//		System.out.println("Switched back to parent window");
//		System.out.println(driver.getTitle());
			
			
			
		// Close only the first window
		driver.switchTo().window(homeHandle).close();
		System.out.println("\nClosed parent window:  \"" + homeTitle + "\"");
		Thread.sleep(2000);
//		handles.remove(homeHandle);
//		driver.switchTo().window(handles.get(0));
		
		driver.quit();
		System.out.println("Closed remaining windows\n");
		
		System.out.println("Test completed\n");
	}
}
