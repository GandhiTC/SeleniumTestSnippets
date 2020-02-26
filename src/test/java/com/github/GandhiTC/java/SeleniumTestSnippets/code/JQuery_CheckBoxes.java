package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;



public class JQuery_CheckBoxes
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://qaclickacademy.com/practice.php");
		
		//	Expected list of unchecked checkboxes
	    List<String> 		unChecked 	=  Arrays.asList("checkBoxOption1", "checkBoxOption2", "checkBoxOption3");
	    
	    //	Create an instance of JavaScript Executor from driver
	    JavascriptExecutor 	js 			= (JavascriptExecutor) driver;

	    //	Locate all the Checkboxes which are checked by calling jQuery.find() method, which returns elements in array
//	    List<WebElement> 	elements 	= (List<WebElement>) js.executeScript("return jQuery.find(':checked')");
	    
	    //	Locate all the Checkboxes which are unchecked
	    List<WebElement> 	elements 	= (List<WebElement>) js.executeScript("return jQuery.find('input:checkbox:not(:checked)')");

	    //	Verify three unchecked boxes
	    Assert.assertEquals(elements.size(), 3);
	    System.out.println("Correct number of unchecked Checkboxes found on webpage.");

	    //Verify correct Checkbox are selected
	    for (WebElement element : elements)
		{
	    	String id = element.getAttribute("id");
			Assert.assertTrue(unChecked.contains(id));
			System.out.println("\"" + id + "\" is in expected list");
		}
	    
		driver.quit();
	}
}