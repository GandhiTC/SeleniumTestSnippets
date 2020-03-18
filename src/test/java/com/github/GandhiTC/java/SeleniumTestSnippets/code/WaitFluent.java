package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;



public class WaitFluent
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver		driver		= new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		
		driver.findElement(By.cssSelector("#start button")).click(); 										//	*[id='start'] button
		WebElement		finisher	= driver.findElement(By.cssSelector("#finish h4"));						//	[id='finish'] h4
		
		//	for selenium 3.141.59
		Wait<WebDriver>	wait		= new FluentWait<WebDriver>(driver)
											.withTimeout(Duration.ofSeconds(30))
											.pollingEvery(Duration.ofSeconds(1))
											.ignoreAll(new ArrayList<Class<? extends Throwable>>()
											{
										        private static final long serialVersionUID = 1L;
												{
										          add(StaleElementReferenceException.class);
										          add(NoSuchElementException.class);
										          add(TimeoutException.class);
										          add(InvalidElementStateException.class);
										          add(WebDriverException.class);
										          add(ElementClickInterceptedException.class);
										        }
										    })
											.withMessage("Unable to locate element.");
		
		wait.until(ExpectedConditions.visibilityOf(finisher));
		
		Assert.assertEquals(finisher.getText(), "Hello World!");
		System.out.println("\nEverything checked out, test case passed!\n");
		
		driver.quit();
	}
}
