package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WaitExplicit
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();

		WebDriverWait	wait	= new WebDriverWait(driver, 10);
		driver.get("https://www.dezlearn.com/explicit-wait-example/");
		
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_97']")).sendKeys("De Zinnia");
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_98']")).sendKeys("Institutes");
		driver.findElement(By.xpath("//input[@id='ninja_forms_field_100']")).sendKeys("dezinnia@dezlearn.com");
		
		driver.findElement(By.id("u_5_6")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("u_5_7"))).click();
	}
}
