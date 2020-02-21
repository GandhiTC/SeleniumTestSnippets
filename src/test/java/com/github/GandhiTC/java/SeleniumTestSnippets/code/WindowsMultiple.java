package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;



public class WindowsMultiple
{
	public WebDriver driver;
	
	
	@FindBy(xpath="//a[@href='/Privacy.php' and contains(text(), 'PRIVACY POLICY')]")
	public WebElement privLink;
	
//	@FindBy(id="fullname")
//	public WebElement fullNameTextbox;
	
	@FindBy(xpath="//a[@href='/personal-loan-offers.php']//b[contains(text(), ' www.deal4loans.com/personal-loan-offers.php')]")
	public WebElement plLink;
	
	@FindBy(xpath="//a[@href='/apply-personal-loan-continue.php' and contains(text(), 'Apply for  Personal Loan')]")
	public WebElement apLink;
	
	
	public WindowsMultiple()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		PageFactory.initElements(driver, this);
		
//		driver.manage().window().maximize();
		driver.get("http://www.deal4loans.com");
	}
	
	
	@Test
	public void LetsDoIt() throws InterruptedException
	{
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		privLink.sendKeys(selectLinkOpeninNewTab);
		Thread.sleep(2500L);
		
//		fullNameTextbox.sendKeys("Some Name");
		
		
		System.out.println("Number of windows open:  " + driver.getWindowHandles().size());
		
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			if(driver.getTitle().equals("Privacy Policy - Deal4loans.com's Services | Online Loan Application And Information"))
			{
				plLink.click();
				Thread.sleep(2500L);
				break;
			}
		}
		
		
		System.out.println("Number of windows open:  " + driver.getWindowHandles().size());
		
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			if(driver.getTitle().equals("Personal Loan Festival Offers | Deal4loans"))
			{
				apLink.click();
				driver.close();
				Thread.sleep(2500L);
				break;
			}
		}
		
		
		System.out.println("Number of windows open:  " + driver.getWindowHandles().size());
		
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			if(driver.getTitle().equals("Privacy Policy - Deal4loans.com's Services | Online Loan Application And Information"))
			{
				driver.close();
				Thread.sleep(2500L);
				break;
			}
		}
		
		
		System.out.println("Number of windows open:  " + driver.getWindowHandles().size());
		
		for(String handle : driver.getWindowHandles())
		{
			driver.switchTo().window(handle);
			
			if(driver.getTitle().equals("Apply and Compare online Loans in India"))
			{
				Thread.sleep(1500L);
//				fullNameTextbox.clear();
				break;
			}
		}
		
		
		driver.quit();
	}
}
