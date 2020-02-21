package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.InputMismatchException;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;



/*
 * By default, tests are run in alphabetical order.
 * To use your test order, add @Test annotation's "priority" attribute
 */



public class HomeLoans
{
	public WebDriver driver;


	@Test
	public void A_OpenBrowser()
	{
		try
		{
			String newLine = System.getProperty("line.separator"); //This will retrieve line separator dependent on OS.
			System.out.println("Enter:" + newLine + "\"1\" for firefox" + newLine + "\"2\" for chrome" + newLine + "\"3\" for internet explorer" + newLine + "\"4\" for edge");
			
			Scanner	in	= new Scanner(System.in);
			
			while(!in.hasNextInt())
			{
				System.err.println("\r\nPlease try again, enter a number");
				in.next();
			}
			
			int		s	= in.nextInt();
			
			if(s == 1)
			{
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(s == 2)
			{
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(s == 3)
			{
				System.setProperty("webdriver.ie.driver", "./src/test/resources/Drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else
			{
				System.setProperty("webdriver.edge.driver", "./src/test/resources/Drivers/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
			}
			
			in.close();
		}
		catch(InputMismatchException e)
		{
			// System.out.println(e);
			e.printStackTrace();
			System.exit(0);
		}
		
		driver.manage().window().maximize();
	}


	@Test
	public void B1_OpenWebLink()
	{
		driver.get("https://www.deal4loans.com/apply-home-loans.php");
	}


	@Test
	public void B2_TestAssertEquals()
	{
		WebElement		GQButton	= driver.findElement(By.xpath("//input[@type='submit']"));
		
		WebDriverWait	wait		= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(GQButton));
		
		GQButton.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.className("hintanchor")));
		
		String NameError = driver.findElement(By.className("hintanchor")).getText();
		Assert.assertEquals(NameError, "Please Enter Your name");
	}


	@Test
	public void C_TestNameField() throws InterruptedException
	{
		WebElement NameField = driver.findElement(By.id("Name"));
		NameField.sendKeys("FirstName LastName");
		Thread.sleep(750);
		NameField.clear();
		Thread.sleep(750);
		NameField.sendKeys("Tejas Gandhi");
		Thread.sleep(750);
	}


	@Test
	public void D_TestDOBdd() throws InterruptedException
	{
		WebElement DobDd = driver.findElement(By.id("day"));
		DobDd.sendKeys("08");
		Thread.sleep(750);
	}


	@Test
	public void E_TestDOBmm() throws InterruptedException
	{
		WebElement DobMm = driver.findElement(By.id("month"));
		DobMm.sendKeys("11");
		Thread.sleep(750);
	}


	@Test
	public void F_TestDOByr() throws InterruptedException
	{
		WebElement DobYr = driver.findElement(By.id("year"));
		DobYr.sendKeys("1970");
		Thread.sleep(750);
		DobYr.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE);
		Thread.sleep(750);
		DobYr.sendKeys("69");
		Thread.sleep(750);
	}


	@Test
	public void G_TestPhone() throws InterruptedException
	{
		WebElement Phone = driver.findElement(By.id("Phone"));
		Phone.sendKeys("9876453120");
		Thread.sleep(750);
	}


	@Test
	public void H_TestCity() throws InterruptedException
	{
		// Select SelectedCity = new Select(driver.findElement(By.id("City")));
		// SelectedCity.selectByValue("Surat");
		driver.findElement(By.xpath("//option[@value='Surat']")).click();
		Thread.sleep(750);
	}


	@Test
	public void I_TestCityOther() throws InterruptedException
	{

		if(driver.findElement(By.id("City_Other")).isEnabled())
		{
			driver.findElement(By.id("City_Other")).sendKeys("Mumbai");
		}
		else
		{
			System.out.println("'Other city' textbox is disabled");
		}
		
		Thread.sleep(750);
	}


	@Test
	public void J_TestEmail() throws InterruptedException
	{
		WebElement Email = driver.findElement(By.id("Email"));
		Email.sendKeys("some@email.com");
		Thread.sleep(750);
	}


	@Test
	public void K_TestEMI() throws InterruptedException
	{
		WebElement EMI = driver.findElement(By.name("obligations"));
		EMI.sendKeys("2000000");
		Thread.sleep(750);
	}


	@Test
	public void L_TestPincode() throws InterruptedException
	{
		WebElement Pincode = driver.findElement(By.name("Pincode"));
		Pincode.sendKeys("456321");
		Thread.sleep(750);
	}


	@Test
	public void M_TestEmpStat() throws InterruptedException
	{
		Select EmpStat = new Select(driver.findElement(By.name("Employment_Status")));
		EmpStat.selectByValue("1");
//		driver.findElement(By.xpath("//td//div//option[@value='1']")).click();
		Thread.sleep(750);
	}


	@Test
	public void N_TestGAS() throws InterruptedException
	{
		WebElement GAS = driver.findElement(By.name("Net_Salary"));
		GAS.sendKeys("3000000");
		Thread.sleep(750);
	}


	@Test
	public void O_TestLoanAmount() throws InterruptedException
	{
		WebElement LoanAmount = driver.findElement(By.name("Loan_Amount"));
		LoanAmount.sendKeys("15000000");
		Thread.sleep(750);
	}


	@Test
	public void P_TestPropVal() throws InterruptedException
	{
		WebElement PropVal = driver.findElement(By.name("property_value"));
		PropVal.sendKeys("22000000");
		Thread.sleep(750);
	}


	@Test
	public void Q_TestPropIDed() throws InterruptedException
	{
		WebElement PropIdYes = driver.findElement(By.xpath("//input[@id='Property_Identified' and @value='1']"));
		PropIdYes.click();
		Thread.sleep(750);
		
		WebElement PropIdNo = driver.findElement(By.xpath("//input[@id='Property_Identified' and @value='0']"));
		PropIdNo.click();
		Thread.sleep(750);
	}


	@Test
	public void R_TestCoAppli() throws InterruptedException
	{
		WebElement CoAppli = driver.findElement(By.name("co_appli"));
		CoAppli.click();
		Thread.sleep(750);
		
		CoAppli.click();
		Thread.sleep(750);
	}


	@Test
	public void S_TestAuthorize() throws InterruptedException
	{
		WebElement Auth = driver.findElement(By.name("accept"));
		Auth.click();
		Thread.sleep(750);
	}


	@Test
	public void T_TestGetQuote()
	{
		WebElement GQButton = driver.findElement(By.xpath("//input[@type='submit']"));
		GQButton.click();
	}
}
