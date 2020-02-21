package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class Cookies
{
	public WebDriver driver;


	@Test
	public void LetsPlayWithCookies() throws InterruptedException, ParseException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// this url requires you to enter your age before you can continue
		driver.manage().window().maximize();
		driver.get("http://heineken.com/Home.aspx");
		System.out.println("1)  Gateway page opened");
		
		driver.manage().deleteAllCookies();
		
		// add cookie to bypass age check
		DateTimeFormatter	dtf				= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime		now				= LocalDateTime.now();
		LocalDateTime		validDate		= now.minusYears(21L).minusDays(1L);
		String				dob				= "dob=" + dtf.format(validDate);
		LocalDateTime		expireDate		= LocalDateTime.now(Clock.systemUTC()).plusYears(1L);
		Date				expiryDate		= Date.from(expireDate.atZone(ZoneId.of("UTC")).toInstant());
		
		Cookie		dobCookie		= new Cookie("AgeGatewayDob", dob, ".heineken.com", "/", expiryDate, true, true);
		Cookie		allowedCookie	= new Cookie("AgeGatewayAllowed", "allowed=True", ".heineken.com", "/", expiryDate, true, true);
		Cookie		darkCookie		= new Cookie("AgeGatewayDarkMarket", "isdark=False", ".heineken.com", "/", expiryDate, true, true);
		Cookie		greyCookie		= new Cookie("AgeGatewayGreyMarket", "isgrey=False", ".heineken.com", "/", expiryDate, true, true);
		Cookie		codCookie		= new Cookie("AgeGatewayCod", "cod=--", ".heineken.com", "/", expiryDate, true, true);
		Cookie		cooCookie		= new Cookie("AgeGatewayCoo", "coo=us", ".heineken.com", "/", expiryDate, true, true);
		driver.manage().addCookie(dobCookie);
		driver.manage().addCookie(allowedCookie);
		driver.manage().addCookie(darkCookie);
		driver.manage().addCookie(greyCookie);
		driver.manage().addCookie(codCookie);
		driver.manage().addCookie(cooCookie);
		System.out.println("2)  Age check cookies set");
		
		// bypass age check and verify with a web element
		driver.get("http://heineken.com/Home.aspx");
		
		if(!driver.findElement(By.cssSelector("a.logo[href='/'] img[alt='Heineken - Open Your World']")).isDisplayed())
		{
			Thread.sleep(1500L);
			
			if(driver.findElement(By.id("ensBtnYes")).isDisplayed())
			{
				driver.findElement(By.id("ensBtnYes")).click();
			}
			
			Thread.sleep(1500L);
		}
		
		Assert.assertTrue(driver.findElement(By.cssSelector("a.logo[href='/'] img[alt='Heineken - Open Your World']")).isDisplayed());
		System.out.println("3)  Age check bypassed");
		
		// deleting single cookie
//		driver.manage().deleteCookie(dobCookie);
		
		// deletes all cookies from only the domain that is currently on screen
		driver.manage().deleteAllCookies();
		System.out.println("4)  Deleted all cookies from current domain");
		
		// refresh the page to verify cookie(s) is/were removed
		System.out.println("5)  Refreshing page to verify age checker\n        (NOTE:  Re-entering the gateway page could recreate some cookies)");
		driver.navigate().refresh();
		
		// lets post whats left of the cookie
		Set<Cookie>	allCookies	= driver.manage().getCookies();
		boolean		FoundIt		= false;

		for(Cookie t : allCookies)
		{

			if(t.getName().equals(dobCookie.getName()))
			{
				FoundIt = true;
				break;
			}
		}

		if(FoundIt)
		{
			System.out.println("6)  Our manually created DOB cookie \"" + dobCookie.getName() + "\" still exsists!");
		}
		else
		{
			System.out.println("6)  Our manually created DOB cookie \"" + dobCookie.getName() + "\" was deleted");
		}
		
		System.out.println("7)  Remaining cookies are ...");
		Cookie[] C = allCookies.toArray(new Cookie[allCookies.size()]);

		for(int i = 0; i < C.length; i++)
		{
			if(C.length == 0)
			{
				System.out.println("        All cookies from this domain have been deleted!");
				break;
			}
			else
			{
				if(C[i].getName().equals(dobCookie.getName()))
				{
					System.out.println("        ***  " + C[i].getName() + "  ***");
				}
				else
				{
					System.out.println("        " + C[i].getName());
				}
			}
		}
		
		System.out.println("\n\n");
		Thread.sleep(1500L);
		
		System.out.println("8)  Quiting WebDriver");
		driver.quit();
	}
}
