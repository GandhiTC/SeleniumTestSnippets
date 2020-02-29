package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



/*
<tr>
    <td>Nash</td>
    <td><a href="mailto:nash@test.com">Nash@test.com</a></td>
    <td>
        <div>
            <label for="user128_admin">Admin</label>
            <input type="checkbox" id="user128_admin" checked="true"/>
            <label for="user128_cm">Content Manager</label>
            <input type="checkbox" id="user128_cm"/>
            <label for="user128_browser">Browser</label>
            <input type="checkbox" id="user128_browser"/>
        </div>
    </td>
</tr>
 */



public class Tables_ChildElements
{
	public static void main(String[] args)
	{
		/*
		//	cssSelector
		WebElement adminCheckBox = driver.findElement(By.cssSelector("td:contains('Nash')+td+td>div>label:contains('Admin')+input"));

		//	xpath
		WebElement adminCheckBox = driver.findElement(By.xpath("//td[contains(text(),'Nash')]/following-sibling::td/descendant::div/label[contains(text(),'Admin')]/following-sibling::input"));
		
		adminCheckBox.click();
		*/
	}
}