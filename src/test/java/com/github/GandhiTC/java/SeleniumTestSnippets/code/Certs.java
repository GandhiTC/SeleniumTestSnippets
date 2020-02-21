package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;



public class Certs {

	public static void main(String[] args)
	{
		DesiredCapabilities dc = DesiredCapabilities.chrome();	//	.firefox()	.edge()	.internetExplorer()  etc
		//dc.acceptInsecureCerts();
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, false);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		ChromeOptions options = new ChromeOptions();			//	FirefoxOptions etc
		options.merge(dc);
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("https://www.yahoo.com");
	}

}