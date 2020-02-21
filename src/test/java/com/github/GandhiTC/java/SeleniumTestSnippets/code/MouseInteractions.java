package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;



public class MouseInteractions
{
	public WebDriver driver;


	@Test
	public void DoSomething() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver 	driver 			= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.deal4loans.com/personal-loan.php");
		Thread.sleep(1000L);
		
		WebElement	mouseOverThis1	= driver.findElement(By.xpath("//a[contains(text(), 'Personal Loan ')]"));
		Actions		action			= new Actions(driver);
		action.moveToElement(mouseOverThis1).click().build().perform();
		System.out.println("Clicked on \"Personal Loan\" menu");
		Thread.sleep(1000L);
		
		WebElement mouseOverThis2 = driver.findElement(By.xpath("//a[@href='https://www.deal4loans.com/apply-personal-loan-continue.php']"));
		action.moveToElement(mouseOverThis2).build().perform();
		Thread.sleep(1000L);
		action.click().build().perform();
		System.out.println("\"Get Quote for Personal Loan\" submenu link clicked");
		
		Thread.sleep(2500);
		driver.quit();
	}
}
