package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;



public class QRSeleniumSimple
{
	private	String 	pathOfImage 	= "./src/test/resources/zxing_images/qr-code-selenium.png";
	private	int		navbarHeight	= 33;


	@Test
	public void QRCodeTestSelenium() throws NotFoundException, IOException, InterruptedException
	{
		//	Set up WebDriver
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, System.getProperty("java.io.tmpdir") + "geckodriverlogs.txt");
		WebDriver 		driver 				= new FirefoxDriver();
		
		//	Manage WebDriver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://chercher.tech/java/qrcode-barcode-selenium");
		driver.manage().window().maximize();
		
		//	Close popup if open
		WebElement		closeX				= driver.findElement(By.xpath("//a[@class='closesubscribe' and @href='#']"));
		
		if(closeX.isDisplayed())
		{
			closeX.click();
		}
		
		//	Scroll to particular element taking height of navbar into consideration
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('picasso').scrollIntoView(true)");
		js.executeScript("window.scrollBy(0, -" + navbarHeight + ")");
		
		//	Locate Image element to capture screenshot.
		WebElement		element				= driver.findElement(By.id("picasso"));
		
		//	Capture viewable page screenshot as File.
		File			screen				= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//	Get location (x y coordinates) of the element.
		Point			point				= element.getLocation();
		int				xcordinate			= point.getX();
//		int				ycordinate			= point.getY();
		
		//	Get height and width of element.
		int				imageWidth			= element.getSize().getWidth();
		int				imageHeight			= element.getSize().getHeight();
		
		//	Reading full image screenshot.
		BufferedImage	img					= ImageIO.read(screen);
		
		//	Cut Image using height, width and x y coordinates parameters.
		BufferedImage	destination			= img.getSubimage(xcordinate + 1, navbarHeight + 2, imageWidth - 2, imageHeight - 2);
		ImageIO.write(destination, "png", screen);
		
		//	Save Image screenshot.
		FileUtils.copyFile(screen, new File(pathOfImage));
		
		BufferedImage	bufferedImage		= ImageIO.read(new File(pathOfImage));
		LuminanceSource	source				= new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap	bitmap				= new BinaryBitmap(new HybridBinarizer(source));
		Result			result				= new MultiFormatReader().decode(bitmap);
		String			textPresentInImage	= result.getText();
		
		System.out.println("Text Present in Image : " + textPresentInImage);
		
		Assert.assertEquals(textPresentInImage, "cherchertech");
		
		driver.quit();
	}
}
