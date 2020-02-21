package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



public class ErrorCodes
{
	public WebDriver driver;
	
	
	public List<String> GetWikiList()
	{
		driver.get("http://en.wikipedia.org/wiki/List_of_HTTP_status_codes");
		
		List<WebElement>	DtErrorList		= driver.findElements(By.xpath("//dt"));
		List<WebElement>	AErrorList		= driver.findElements(By.xpath("//dt//a"));
		List<WebElement>	OldErrorList	= new ArrayList<WebElement>();
		OldErrorList.addAll(AErrorList);
		OldErrorList.addAll(DtErrorList);
		
		List<String> ErrorList = new ArrayList<String>(OldErrorList.size());
		
		for(WebElement myWE : OldErrorList)
		{
			if((!ErrorList.contains(myWE.getText())) && (Character.isDigit(myWE.getText().charAt(0))))
			{
				ErrorList.add(myWE.getText());
			}
		}
		
		Collections.sort(ErrorList);
		
		List<String> 		FinalList 		= new ArrayList<String>(ErrorList.size());
		
		for(String EachError : ErrorList)
		{
			String TheError;
			
			int		Start	= 0;
			int		End		= EachError.lastIndexOf(" (");
			
			if(EachError.contains("("))
			{
				TheError = EachError.substring(Start, End);
			}
			else
			{
				TheError = EachError;
			}
			
			FinalList.add(TheError);
		}
		
		return FinalList;
	}
	
	
	public List<String> GetW3List()
	{
		driver.get("http://www.w3schools.com/tags/ref_httpmessages.asp");
		
		List<WebElement>	W3ErrorList		= driver.findElements(By.xpath("//tr//td[1]"));
		
		List<WebElement>	OldErrorList	= new ArrayList<WebElement>();
		OldErrorList.addAll(W3ErrorList);
		
		List<String> 		ErrorList 		= new ArrayList<String>(OldErrorList.size());
		
		for(WebElement myWE : OldErrorList)
		{
			try
			{
				if((!ErrorList.contains(myWE.getText())) && (Character.isDigit(myWE.getText().charAt(0))))
				{
					ErrorList.add(myWE.getText());
					continue;
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				continue;
			}
		}
		
		Collections.sort(ErrorList);
		
		/*
		List<String> FinalList = new ArrayList<String>(ErrorList.size());
		
		for(String EachError : ErrorList)
		{
			String TheError;
			
			if(EachError.contains("("))
			{
				TheError = EachError.substring(0, EachError.lastIndexOf(" ("));
			}
			else
			{
				TheError = EachError;
			}
			
				FinalList.add(TheError);
		}
		
		return FinalList;
		*/
		
		return ErrorList;
	}
	
	
	@Test
	public void GetTotalList()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		List<String>	WikiList		= GetWikiList();
		List<String>	W3List			= GetW3List();
		List<String>	OldErrorList	= new ArrayList<String>();
		OldErrorList.addAll(WikiList);
		OldErrorList.addAll(W3List);
		
		List<String> 	ErrorList 		= new ArrayList<String>(OldErrorList.size());
		
		for(String myString : OldErrorList)
		{
			if((!ErrorList.contains(myString)))
			{
				ErrorList.add(myString);
			}
		}
		
		Collections.sort(ErrorList);
		
		System.err.println("List from Wikipedia:");
		for(String currmsg : WikiList)
		{
			System.out.println(currmsg);
		}
		
		System.err.println("\nList from W3 Schools:");
		for(String currmsg : W3List)
		{
			System.out.println(currmsg);
		}
		
		System.err.println("\nCombined list:");
		for(String currmsg : ErrorList)
		{
			System.out.println(currmsg);
		}
		
		driver.quit();
	}
}
