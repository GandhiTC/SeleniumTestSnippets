package com.github.GandhiTC.java.SeleniumTestSnippets.code;



//import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class SelectDropDown
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, System.getProperty("java.io.tmpdir") + "geckodriverlogs.txt");
		WebDriver 	driver 	= new FirefoxDriver();

		driver.get("https://www.cleartrip.com/");
		
		
		Actions 	actions 			= new Actions(driver);
		
		
		//	WebElements
		WebElement 	body				= driver.findElement(By.tagName("body"));
		WebElement 	adultsDDList		= driver.findElement(By.cssSelector("#Adults"));
		WebElement 	childrensDDList		= driver.findElement(By.cssSelector("#Childrens"));
		WebElement 	infantssDDList		= driver.findElement(By.cssSelector("#Infants"));
		
		//	DropDown Lists
		Select		adultsSelect		= new Select(adultsDDList);
		Select 		childrenSelect 		= new Select(childrensDDList);
		Select 		infantsSelect 		= new Select(infantssDDList);
		
		
		//	adults dropdown list   - Picks 3rd from last option
		adultsSelect.selectByIndex(adultsSelect.getOptions().size() - 3);
		Thread.sleep(500L);
		
		//	children dropdown list - Picks by value of visible text
		childrenSelect.selectByVisibleText("2");
		Thread.sleep(500L);
		
		//	infants dropdown list  - Picks Adults value minus 2
		String		selectionText		= adultsSelect.getFirstSelectedOption().getText();
		int			selectionIntValue	= Integer.valueOf(selectionText);
		infantsSelect.selectByValue(Integer.toString(selectionIntValue - 2));
		actions.sendKeys(infantssDDList, Keys.ENTER).build().perform();
		actions.click(body).build().perform();
		Thread.sleep(500L);
		
		
		System.out.println(" ");
		System.out.println(adultsDDList.getAttribute("value"));
		System.out.println(childrensDDList.getAttribute("value"));
		System.out.println(infantssDDList.getAttribute("value"));
		
		
		//	One alternative way to select an option
		List<WebElement> adultsOptions	= adultsDDList.findElements(By.tagName("option"));
//		List<WebElement> adultsOptions	= driver.findElements(By.cssSelector("#Adults>option"));

		for(WebElement option : adultsOptions)
		{
			if(option.getText().trim().equals("3"))
			{
				option.click();
				break;
			}
		}
		
//		Iterator<WebElement> iterator = adultsOptions.iterator();
//
//		while(iterator.hasNext())
//		{
//			WebElement option = iterator.next();
//
//			if(option.getText().trim().equals("3"))
//			{
//				option.click();
//				break;
//			}
//		}
		
		
		//	Another alternative way
		actions.click(childrensDDList).sendKeys(childrensDDList, Keys.DOWN, Keys.DOWN, Keys.ENTER).build().perform();
		
		
		System.out.println(" ");
		System.out.println(adultsDDList.getAttribute("value"));
		System.out.println(childrensDDList.getAttribute("value"));
		System.out.println(infantssDDList.getAttribute("value"));
		System.out.println(" ");
		
		
		Thread.sleep(3000L);
		driver.quit();
	}
}
