package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;



public class DropDownSelect
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.cleartrip.com/");
		
		//	adults dropdown list
		Select			adultsSelect	= new Select(driver.findElement(By.cssSelector("#Adults")));
		adultsSelect.selectByIndex(adultsSelect.getOptions().size() - 3); //	picks 3rd from last option
		
		//	children dropdown list
		Select 			childrenSelect 	= new Select(driver.findElement(By.cssSelector("#Childrens")));
		childrenSelect.selectByVisibleText("2");
		
		//	infants dropdown list
		Select 			infantsSelect 	= new Select(driver.findElement(By.cssSelector("#Infants")));
		infantsSelect.selectByValue(Integer.toString(Integer.valueOf(adultsSelect.getFirstSelectedOption().getText()) - 2));
		
		Thread.sleep(3000L);
		driver.quit();
	}
}
