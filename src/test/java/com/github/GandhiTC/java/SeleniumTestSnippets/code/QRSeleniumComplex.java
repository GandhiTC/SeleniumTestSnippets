package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;



public class QRSeleniumComplex
{
	@Test
	public void QRCodeTestSelenium() throws NotFoundException, IOException
	{
		//	Set up WebDriver
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, System.getProperty("java.io.tmpdir") + "geckodriverlogs.txt");
		
		FirefoxOptions		options			= new FirefoxOptions();
		options.setHeadless(true);
		
		WebDriver 		driver 				= new FirefoxDriver(options);
		
		
		//	Manage WebDriver
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://chercher.tech/java/qrcode-barcode-selenium.php");
		driver.manage().window().maximize();
		
		
		//	Get browser user-agent info
		JavascriptExecutor	js				= (JavascriptExecutor)driver;
		String				script			= "return navigator.userAgent;";
		String				userAgent		= (String)js.executeScript(script);
		
		
		//	Find element then get its source attribute
		String 				src 			= driver.findElement(By.id("complex-qrcode")).getAttribute("src");
		System.out.println("\nimage url is : " + src + "\n\n");
		
		
		//	Temporarily save element as a binary bitmap
		URLConnection 		openConnection 	= new URL(src).openConnection();
		openConnection.addRequestProperty("User-Agent", userAgent);
		InputStream 		is				= openConnection.getInputStream();
		BufferedImage		bufferedImage	= ImageIO.read(is);
		LuminanceSource		source			= new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap		bitmap			= new BinaryBitmap(new HybridBinarizer(source));
		is.close();
		
		
		//	Prepare to store the details of the QR code
		Result				result			= new MultiFormatReader().decode(bitmap);
		String				decodedText		= result.getText().replace("MECARD:", "");
		Map<String, String>	contact			= new HashMap<String, String>();
		String[] 			abc 			= decodedText.split(";");
		
		
		//	Print to console while populating HashMap
		System.out.println("Decoded content = " + decodedText + "\n");
		
		System.out.println("**************************************");

		for(String str : abc)
		{
			System.out.println(str);
			String[] content = str.split(":");
			contact.put(content[0], content[1]);
		}
		
		System.out.println("**************************************\n\n");
		
		
		//	Print the contact HashMap
		System.out.println("Complete contact map :: ");
		System.out.println(contact + "\n\n");
		
		
		driver.quit();
	}
}
