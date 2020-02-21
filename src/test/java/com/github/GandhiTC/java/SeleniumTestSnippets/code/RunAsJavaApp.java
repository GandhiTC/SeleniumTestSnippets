package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class RunAsJavaApp
{
	// To call a non-static method from within a static method
	// the non-static method needs to be from another class
	public static void main(String[] args)
	{
		InvokeFirefox firefox = new InvokeFirefox();
		firefox.Maximize();
		firefox.GoTo("http://www.google.com");
		firefox.ShutDown(1500);
		End();
	}


	// End() was called from within a static method in the same class
	// therefore End() must be static
	public static void End()
	{
		try
		{
			System.out.println("End of java application");
			System.exit(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
//	public RunAsJavaApp()
//	{
//		InvokeFirefox firefox = new InvokeFirefox();
//		firefox.Maximize();
//		firefox.GoTo("http://www.google.com");
//		firefox.ShutDown(1500);
//		End();
//	}
}



class InvokeFirefox
{
	public WebDriver driver;


	public InvokeFirefox()
	{
		try
		{
//			System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Open browser executed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void Maximize()
	{
		try
		{
			driver.manage().window().maximize();
			System.out.println("Maximize browser executed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void GoTo(String url)
	{
		try
		{
			driver.get(url);
			System.out.println("Open url executed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void ShutDown(int milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
//			driver.close();
			driver.quit();
			System.out.println("Shutdown browser executed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
