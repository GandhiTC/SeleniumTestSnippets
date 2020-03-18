package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class WaitImplicit
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.dezlearn.com/test-sync-example/");
		
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_97']")).sendKeys("De Zinnia");
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_98']")).sendKeys("Institutes");
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_100']")).sendKeys("dezinnia@dezlearn.com");
		
		driver.findElement(By.id("u_5_6")).click();
		driver.findElement(By.linkText("Go to Next Page")).click();
	}
}
