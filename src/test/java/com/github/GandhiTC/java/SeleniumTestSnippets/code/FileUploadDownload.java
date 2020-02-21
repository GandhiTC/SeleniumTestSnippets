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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FileUploadDownload
{
	static String downloadPath 	= System.getProperty("user.dir");
	static String pdfFileName	= "file-example_PDF_1MB.pdf";


	public static void main(String[] args) throws InterruptedException, IOException
	{
		WebDriver driver = null;

		try
		{
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);

			System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver(options);

			driver.get("https://altoconvertpdftojpg.com/");
			driver.findElement(By.cssSelector("[class*='btn--choose']")).click();

			CreateAutoITFiles();

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='medium']")));
			driver.findElement(By.cssSelector("button[class*='medium']")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));
			driver.findElement(By.linkText("Download Now")).click();
			Thread.sleep(5000);

			File f = new File(downloadPath + "\\converted.zip");
			if(f.exists())
			{
				Assert.assertTrue(f.exists());
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			File	f1	= new File(downloadPath + "\\fileupload.au3");
			File	f2	= new File(downloadPath + "\\fileupload.exe");
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


	private static void CreateAutoITFiles() throws IOException, InterruptedException
	{
		File			file	= new File(downloadPath + "\\fileupload.au3");
		List<String>	lines	= Arrays.asList("ControlFocus(\"Open\", \"\", \"Edit1\")",
												"ControlSetText(\"Open\", \"\", \"Edit1\", \"" + downloadPath + "\\" + pdfFileName + "\")",
												"ControlClick(\"Open\", \"\", \"Button1\")");

		Files.write(file.toPath(), lines);

		Runtime.getRuntime().exec(new String[] {downloadPath + "\\Aut2exe.exe", "/in", "\"" + downloadPath + "\\fileupload.au3\""});
		Thread.sleep(5000);

		Runtime.getRuntime().exec(downloadPath + "\\fileupload.exe");
	}
}