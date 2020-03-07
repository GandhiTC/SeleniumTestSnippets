package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FileUploadDownload
{
	static WebDriver		driver;
	static WebDriverWait 	wait;
	static String 			downloadPath 	= System.getProperty("user.dir");
	static String 			resourcePath	= downloadPath + "\\src\\test\\resources\\";
	static String 			pdfFileName		= resourcePath + "file-example_PDF_1MB.pdf";


	public static void main(String[] args) throws InterruptedException, IOException
	{
		try
		{
			//	start chrome browser and load web page
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);

			System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
			driver 	= new ChromeDriver(options);
			wait 	= new WebDriverWait(driver, 10);

			driver.get("https://altoconvertpdftojpg.com/");
			
			
			//	click upload button, create AutoIt script and compile it, let it run, then wait for page to load
			driver.findElement(By.cssSelector("[class*='btn--choose']")).click();
			CreateAutoITFiles();
			
			
			//	if annoying banner for cookies notice is in the way, remove it
			List<WebElement> cookiePopups = driver.findElements(By.xpath("//div[@class='cookies-popup__inner']/button"));
			
			if(!cookiePopups.isEmpty())
			{
				cookiePopups.get(0).click();
			}
			
			
			//	wait for "Convert Now!" button to be visible and clickable before trying to click it
			By				convertButton 	= By.cssSelector("button[class*='medium']");
			By				downloadButton 	= By.linkText("Download Now");

			wait.until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(convertButton),
					ExpectedConditions.elementToBeClickable(convertButton)
			));
			
			driver.findElement(convertButton).click();
			
			
			//	wait for "Download Now" button to be visible and clickable before trying to click it
			wait.until(ExpectedConditions.and(
					ExpectedConditions.visibilityOfElementLocated(downloadButton),
					ExpectedConditions.elementToBeClickable(downloadButton)
			));

			driver.findElement(downloadButton).click();
			
			
			Thread.sleep(7500L);

			
			File f = new File(downloadPath + "\\converted.zip");
			
			if(f.exists())
			{
				Assert.assertTrue(f.exists());
			}
			else
			{
				//	unnecessary stack traces are ugly to look at, lets keep it clean
				Assert.fail("\r\n\r\nFile download failed.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			File	f1	= new File(resourcePath + "fileupload.au3");
			File	f2	= new File(resourcePath + "fileupload.exe");
			File	f3  = new File(downloadPath + "\\converted.zip");

			if(f1.exists())
			{
				f1.delete();
			}

			if(f2.exists())
			{
				f2.delete();
			}

			if(f3.exists())
			{
				f3.delete();
			}

			driver.quit();
		}
	}


	private static synchronized void CreateAutoITFiles() throws IOException, InterruptedException
	{
		File			file		= new File(resourcePath + "fileupload.au3");
		
		List<String>	lines		= Arrays.asList("ControlFocus(\"Open\", \"\", \"Edit1\")",
													"ControlSetText(\"Open\", \"\", \"Edit1\", \"" + pdfFileName + "\")",
													"ControlClick(\"Open\", \"\", \"Button1\")");

		Files.write(file.toPath(), lines);

		Runtime.getRuntime().exec(new String[] {resourcePath + "Aut2exe.exe", "/in", "\"" + resourcePath + "fileupload.au3\""});

		File 			fileToRun 	= new File(resourcePath + "fileupload.exe");
		
		while(!fileToRun.exists())
		{
			Thread.sleep(1000L);
		}
		
		Runtime.getRuntime().exec(resourcePath + "fileupload.exe");
	}
}