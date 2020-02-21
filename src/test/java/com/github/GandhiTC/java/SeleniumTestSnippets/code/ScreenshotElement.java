package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class ScreenshotElement
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		WebElement		logo		= driver.findElement(By.id("nav-logo"));
		File			screenshot	= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage 	fullScreen	= null;

		try
		{
			fullScreen = ImageIO.read(screenshot);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		Point			location	= logo.getLocation();
		int				width		= logo.getSize().getWidth();
		int				height		= logo.getSize().getHeight();
		BufferedImage	logoImage	= fullScreen.getSubimage(location.getX(), location.getY(), width, height);
		
		try
		{
			ImageIO.write(logoImage, "png", screenshot);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			FileUtils.copyFile(screenshot, new File("./Screenshots/screenshotElement.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		driver.quit();
	}
}