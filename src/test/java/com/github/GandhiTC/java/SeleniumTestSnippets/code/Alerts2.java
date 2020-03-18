package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Alerts2
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.dezlearn.com/javascript-alerts/");
		
		//	Basic
		driver.findElement(By.id("s_alert1")).click();
		Thread.sleep(3000);
		
		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());
		a.accept();
		
		//	Confirmation
		driver.findElement(By.id("c_alert2")).click();
		Thread.sleep(3000);
		
		Alert b = driver.switchTo().alert();
		System.out.println(b.getText());
		b.dismiss();
		
		//	Prompt
		driver.findElement(By.id("p_alert3")).click();
		Thread.sleep(3000);
		
		Alert c = driver.switchTo().alert();
		a.sendKeys("Mumbai");
		Thread.sleep(1000);
		System.out.println(c.getText());
		c.accept();
		
		driver.close();
	}
}
