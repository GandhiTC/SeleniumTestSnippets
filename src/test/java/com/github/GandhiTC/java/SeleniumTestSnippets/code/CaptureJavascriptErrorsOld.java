package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.IOException;
import java.util.List;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/*
 * jserrorcollector is no longer available via a public repository
 * 
 * download jar file
 * https://github.com/mguillem/JSErrorCollector/blob/master/dist/JSErrorCollector-0.6.jar
 * 
 * choose one of the following options
 * 
 * 1)  Add JSErrorCollector-0.6.jar to project build path library via "Add external jar..."
 * 
 * 2)  cd to location of downloaded jar file, then run following command:
 *     mvn org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=JSErrorCollector-0.6.jar
 * 
 *     this will add jar to local repository
 *     from now on you can add dependency to pom files as usual
 * 
 *     add following dependency to pom.xml:
 *     <dependency>
 *			<groupId>net.jsourcerer.webdriver</groupId>
 *			<artifactId>JSErrorCollector</artifactId>
 *			<version>0.6</version>
 *	   </dependency>
 */



public class CaptureJavascriptErrorsOld
{
	private static WebDriver driver;
	
	
	@BeforeClass
	public void setUp() throws IOException
	{
		FirefoxProfile ffProfile = new FirefoxProfile();
		JavaScriptError.addExtension(ffProfile);

		FirefoxOptions ffOptions = new FirefoxOptions();
		ffOptions.setProfile(ffProfile);
		ffOptions.setCapability("marionette", true);

		System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
		driver = new FirefoxDriver(ffOptions);
		
//		driver.get("http://www.telegraaf.nl/");
//		driver.get("https://web.archive.org/web/20130719045503/http://selenium.polteq.com/en/capturing-javascript-errors-with-selenium-webdriver/");
		driver.get("https://www.tutorialspoint.com/javascript/javascript_error_handling.htm");
	}


	@AfterClass
	public void tearDown()
	{
		List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver);

		if(!jsErrors.isEmpty())
		{
			System.out.println("### Start displaying errors ###");
	
			for(int i = 0; i < jsErrors.size(); i++)
			{
				System.out.println(jsErrors.get(i).getErrorMessage());
				System.out.println(jsErrors.get(i).getLineNumber());
				System.out.println(jsErrors.get(i).getSourceName());
			}
	
			System.out.println("### End displaying errors ###");
		}
		else
		{
			System.out.println("No javascript errors found on this page.");
		}
		
		driver.quit();
	}


	@Test
	public void returnJavascriptErrors() throws InterruptedException
	{
		Thread.sleep(5000L);
	}
}
