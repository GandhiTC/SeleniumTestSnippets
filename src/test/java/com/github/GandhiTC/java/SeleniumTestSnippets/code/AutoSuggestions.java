package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class AutoSuggestions
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		WebDriver driver=new ChromeDriver(options);

		driver.get("http://www.qaclickacademy.com/practice.php");
		
		WebElement inputField = driver.findElement(By.id("autocomplete"));
		inputField.click();
		inputField.sendKeys("uni");
		Thread.sleep(1000L);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"autocomplete\").value;";
		String text = (String) js.executeScript(script);
		
		int	num = 1;
		int	max = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li")).size();
		
		while(!text.equalsIgnoreCase("United States"))	//	"United States (USA)"
		{
			if (num > max)
			{
				break;
			}
			
			driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys(Keys.DOWN);

			text = (String) js.executeScript(script);
			System.out.println(num + ")  " + text);
			
			num++;
		}
		
		if(num <= max)
		{
			inputField.sendKeys(Keys.ENTER);
			System.out.println("Element found");
		}
		else
		{
			inputField.sendKeys(Keys.ESCAPE);
			System.out.println("Element not found");
		}
		
		Thread.sleep(2500L);
		driver.quit();
	}
}