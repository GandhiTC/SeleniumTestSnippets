package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class ScreenshotWholeBrowserWindow
{
	private static final 	String 	userDir			= System.getProperty("user.dir");
	private static final	String	resourcePath	= userDir + "\\src\\test\\resources\\";
	private static final 	String 	screenshotPath 	= userDir + "\\Screenshots";
	
	
	public static void main(String[] args)
	{
		WebDriver driver = null;
		
		try
		{
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
			driver = new ChromeDriver();

			driver.get("https://www.jqueryscript.net/demo/Responsive-jQuery-Dual-Select-Boxes-For-Bootstrap-Bootstrap-Dual-Listbox/");
			
			CreateAutoITFiles(driver, "Bootstrap 4 Dual Listbox Demos - Google Chrome");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			File	f1	= new File(resourcePath + "screenshot.au3");
			File	f2	= new File(resourcePath + "screenshot.exe");

			if(f1.exists())
			{
				f1.delete();
			}

			if(f2.exists())
			{
				f2.delete();
			}

			driver.quit();
		}
	}
	
	
	private static void CreateAutoITFiles(WebDriver driver, String title)
	{
		try
		{
			File 			pics 	  = new File(screenshotPath);
			File			file	  = new File(resourcePath + "screenshot.au3");
			File			compiled  = new File(resourcePath + "screenshot.exe");
			int 			maxTries1 = 0;
			int 			maxTries2 = 0;
			
			
			//	Make sure Screenshots folder exists
			if(!pics.exists())
			{
				pics.mkdir();
			}
			
			
			//	Create an .au3 file
			List<String>	lines	  = Arrays.asList("#include <AutoIt_Includes\\ScreenCapture.au3>",
													"Local $hWnd = WinGetHandle(\"" + title + "\")",
													"WinActivate ( $hWnd )",
													"_ScreenCapture_CaptureWnd(\"" + screenshotPath + "\\WholeBrowser.png\", $hWnd)");
			
			Files.write(file.toPath(), lines);
			
			
			//	Compile the .au3 file
			Runtime.getRuntime().exec(new String[] {resourcePath + "Aut2exe.exe", "/in", "\"" + resourcePath + "screenshot.au3\""});
			
			
			//	Wait for file to finish compiling
			while(!compiled.exists() && (maxTries1++ < 31))
			{
//				set focus on browser window
				((JavascriptExecutor)driver).executeScript("window.focus();");
				
				Thread.sleep(1000L);
			}
			
			if(!compiled.exists() && (maxTries1 > 29))
			{
				System.err.println("Screenshot not taken, there was an error while compiling .au3 file.");
				return;
			}
				
			
			//	Once compiled, run the .exe file
			Process			process   = Runtime.getRuntime().exec(resourcePath + "screenshot.exe");
			
			
			//	Wait for the process to complete and terminate itself
			while(process.isAlive() && (maxTries2++ < 31))
			{
				Thread.sleep(1000L);
			}
			
			if(process.isAlive() && (maxTries2 > 29))
			{
				process.destroy();
				System.err.println("Screenshot not taken, there was an error while running compiled file.");
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
