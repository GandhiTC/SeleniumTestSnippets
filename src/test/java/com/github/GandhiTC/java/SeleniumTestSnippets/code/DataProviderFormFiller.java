package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class DataProviderFormFiller
{
	public WebDriver driver;
	
	
	@FindBy(xpath="//iframe[contains(@title, 'Embedded Wufoo Form')]")
	public WebElement formFrame;
	
	@FindBy(id="Field2")
	public WebElement firstName;
	
	@FindBy(id="Field3")
	public WebElement lastName;
	
	@FindBy(id="Field4")
	public WebElement emailAddress;
	
	@FindBy(id="Field5")
	public WebElement areaCode;
	
	@FindBy(id="Field5-1")
	public WebElement firstThree;
	
	@FindBy(id="Field5-2")
	public WebElement lastFour;
	
	
	public DataProviderFormFiller()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		PageFactory.initElements(driver, this);
	}
	
	
	@BeforeTest
	public void setUp() throws InterruptedException
	{
		driver.get("https://www.wufoo.com/gallery/templates/forms/job-application/");
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].scrollIntoView(true);", formFrame);
		
		driver.switchTo().frame(formFrame);
		Thread.sleep(1500L);
	}


	@Test(dataProvider="DP1")
    public void testForm(String mob, String emp, String email) throws InterruptedException
    {
		firstName.clear();
		lastName.clear();
		emailAddress.clear();
		areaCode.clear();
		firstThree.clear();
		lastFour.clear();
		
		String[] arrOfEmp = emp.split(" ", 2);
		String field2  = arrOfEmp[0];
		String field3  = arrOfEmp[1];
		String field50 = mob.substring(0, 3);
		String field51 = mob.substring(4, 6);
		String field52 = mob.substring(7, 10);
		
		firstName.sendKeys(field2);
		lastName.sendKeys(field3);
		emailAddress.sendKeys(email);
		areaCode.sendKeys(field50);
		firstThree.sendKeys(field51);
		lastFour.sendKeys(field52);
		
		Thread.sleep(2000L);
    }
	
	
	@DataProvider(name="DP1", parallel=false)
    public Object[][] createData()
	{
        Object[][] retObjArr={
        					 {"1234567890","Jack Johnson","JJ@company.com"},
                             {"0987654321","John Brown","JB@company.com"},
                             {"1122334455","Mary Olson","MO@company.com"},
                             {"5544332211","George Clooney","GC@company.com"}
                             };
        return(retObjArr);
    }
	

	@AfterTest
	public void PostFormFill()
	{
		driver.quit();
	}
}
