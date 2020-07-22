package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class Popups
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
//		driver.get("http://www.qaClickAcademy.com");
		driver.get("https://www.w3schools.com/howto/howto_css_modals.asp");
		
		/*
		Be careful when using IsDisplayed with in-page "pop-ups"
		Even though the pop-up may not be visible on the actual web page, it will still be visible on the HTML DOM
		therefore IsDisplayed will return true
		To get around this, use FindElements
		check for the size of the returned list
		if size is greater than 0, then there is something there on the actual web page
		also see Hidden.java and FluentWaiter.java
		*/
		
//		List<WebElement> noThanks = driver.findElements(By.xpath("//button[text()='NO THANKS']"));
		
//		if(noThanks.size() > 0)
//		{
//			noThanks.get(0).click();
//			System.out.println("\"No Thanks\" clicked");
//		}
//		else
//		{
//			System.out.println("There was no popup");
//		}
		
		
		List<WebElement> modalX = driver.findElements(By.xpath("//span[@class='w3-button w3-xlarge w3-display-topright w3-hover-red w3-hover-opacity']"));
		
		if((modalX.size() > 0) && modalX.get(0).isDisplayed())
		{
			System.out.println("Modal popup is already opened.");
		}
		else
		{
			System.out.println("Modal popup currently not visible.");
			
			WebElement modalButton = driver.findElement(By.xpath("//button[text()='Open Modal']"));
			modalButton.click();
			System.out.println("\"Open Modal\" clicked");
		}
		
		Thread.sleep(1500);
		
		if(modalX.size() > 0)
		{
			modalX.get(0).click();
			System.out.println("Modal popup closed.");
			Thread.sleep(1500);
		}
		else
		{
			System.out.println("Modal popup not found.");
		}
		
		driver.quit();
	}
}