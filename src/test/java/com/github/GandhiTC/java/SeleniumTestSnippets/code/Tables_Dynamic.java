package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



public class Tables_Dynamic
{
	private static 	WebDriver 			driver;
	private static	String				pageURL1		= "https://finance.yahoo.com/gainers",
										pageURL2		= "https://demo.guru99.com/test/table.html";
	private static	int					numRows		= -1,
										numCols		= -1,
										curFails	=  0,
										maxFails	=  10;
	
	
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		System.out.println("\r\n");
		moveBrowserRight();
		
		driver.get(pageURL1);
		
		getNumOfRowsAndCols();
		getByRowAndColNums(3, 3);
		getMaxPercentChanged();
		getNumOfCells();
		
		driver.close();
	}
	
	
	public static void getNumOfRowsAndCols()
	{
		//	Number of columns
		List<WebElement> cols = driver.findElements(By.xpath("//table//tr[1]/th"));
		System.out.println("Num of cols : " + cols.size());
		
		//	Number of rows
		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody//tr/td[1]"));
		System.out.println("Num of rows : " + rows.size());
		
		//	Verify number of rows
		String 	rowsSelection 	= driver.findElement(By.xpath("//table//parent::div/following-sibling::div[1]/span/div/span/span")).getText();
		int		firstSpace		= rowsSelection.indexOf(" ");
		int		lastSpace		= rowsSelection.lastIndexOf(" ");
		String	actualRows		= rowsSelection.substring(firstSpace, lastSpace).trim();
		int		expectedRows	= Integer.parseInt(actualRows);
		
		System.out.println("Actual rows : " + actualRows);
		
		if(rows.size() == expectedRows)
		{
			System.out.println("\r\nNumber of rows counted correctly.\r\n\r\n");
		}
		else
		{
			System.err.println("\r\nCounted rows does not match actual rows.\r\n\r\n");
		}
		
		numRows = expectedRows;
		numCols = cols.size();
	}
	
	
	public static void getByRowAndColNums(int numOfRow, int numOfCol)
	{
		//	Make sure class variables are set first
		if((numRows == -1) || (numCols == -1))
		{
			getNumOfRowsAndCols();
		}
		
		//	Make sure supplied parameters are within valid ranges
		numOfRow = Math.max(numOfRow, 1);
		numOfCol = Math.max(numOfCol, 1);
		
		numOfRow = Math.min(numOfRow, numRows);
		numOfCol = Math.min(numOfCol, numCols);
		
		//	Get row and cell WebElements
		final WebElement bod = driver.findElement(By.xpath("//table/tbody"));
		final WebElement row = bod.findElement(By.xpath(".//tr[" + numOfRow + "]"));
		final WebElement col = row.findElement(By.xpath(".//td[" + numOfCol + "]"));
		
		//	Row Option 1 - Easy single liner, but ugly to look at
//		String rowText = row.getText().replaceAll("\r", "\t").replaceAll("\n", "\t");
//		System.out.println("Row  content : " + rowText);
		
		//	Row Option 2 - Add bigger spaces between each string
		final 	List<WebElement> 	cols 			= row.findElements(By.xpath(".//td"));
		final 	String 				replacement 	= "    ";
				String 				rowText 		= "";
		
		for(WebElement eachTD : cols)
		{
			rowText += (eachTD.getText() + replacement);
		}
		
		//	Print whole row
		rowText.replaceAll("\r", replacement).replaceAll("\n", replacement);
		System.out.println("Whole    row  content : " + rowText);
		
		//	Print selected cell
		String colText = col.getText();
		System.out.println("Selected cell content : " + colText + "\r\n\r\n");
	}
	
	
	public static synchronized void getMaxPercentChanged()
	{
		curFails = 0;
		
		waitForPageToLoad();
		
		int errorSpanCount = driver.findElements(By.xpath("//span[contains(., 'Please try reloading the page.')]")).size();
		
		if(errorSpanCount > 0)
		{
			driver.get(pageURL1);
			getMaxPercentChanged();
			return;
		}
		
		//	By default, the table is sorted by "% Change",
		//	lets re-sort by "Volume" to make sure script works correctly.
		clickVolumeHeaderAndWait(true);
		
		//	Get row and cell WebElements
		double 					max		= 0.00;
		final WebElement 		tbod 	= driver.findElement(By.xpath("//table/tbody"));
		final List<WebElement> 	rows 	= tbod.findElements(By.xpath(".//tr"));
		List<Double>			raws	= updatedStringsList(rows);
		
		if(raws == null)
		{
			return;
		}
		
		for(int x = 0; x < raws.size(); x++)
		{
			DecimalFormat 	df2 = new DecimalFormat("#.00");
							max = Math.max(max, raws.get(x));

			System.out.println("Current val : " + df2.format(raws.get(x)));
			System.out.println("Max     val : " + df2.format(max) + "\r\n");
		}
		
		System.out.println(" ");
	}
	
	
	public static void getNumOfCells()
	{
		driver.get(pageURL2);
		
		WebElement			table		= driver.findElement(By.xpath("/html/body/table/tbody"));
		List<WebElement>	rowList		= table.findElements(By.tagName("tr"));
		int					rowCount	= rowList.size();

		System.out.println("---------------------------------------------------- ");
		
		for(int row = 0; row < rowCount; row++)
		{
			List<WebElement>	rowColsList	= rowList.get(row).findElements(By.tagName("td"));
			int					colCount	= rowColsList.size();
			String				cells		= colCount == 1 ? "cell" : "cells";
			
			System.out.println(colCount + " " + cells + " in row " + (row + 1));

			for(int col = 0; col < colCount; col++)
			{
				String celtext = rowColsList.get(col).getText();
				System.out.println("[row " + (row + 1) + ", column " + (col + 1) + "] = " + celtext);
			}
			
			System.out.println("---------------------------------------------------- ");
		}
	}
	
	
	public static void clickVolumeHeaderAndWait(boolean reverseOrder)
	{
		try
		{
//			waitForPageToLoad();
//
//			int errorSpanCount = driver.findElements(By.xpath("//span[contains(., 'Please try reloading the page.')]")).size();
//
//			if(errorSpanCount > 0)
//			{
//				driver.get(pageURL1);
//				getMaxPercentChanged();
//				return;
//			}
//			else
//			{
				int numOfClicks = reverseOrder ? 2 : 1;
				
				for(int x = 1; x <= numOfClicks; x++)
				{
					//	Wait for the "Volume" header to become clickable, then click on it
					WebElement 		volumeHeader 	= driver.findElement(By.xpath("//table//tr[1]/th[contains(., 'Volume')]"));
					Wait<WebDriver>	fluentWait		= new FluentWait<WebDriver>(driver)
															.withTimeout(Duration.ofSeconds(30))
															.pollingEvery(Duration.ofSeconds(1))
															.ignoring(NoSuchElementException.class);
					
					fluentWait.until(ExpectedConditions.elementToBeClickable(volumeHeader));
					
					Thread.sleep(500L);
					
					Actions actions = new Actions(driver);
					actions.moveToElement(volumeHeader).click().build().perform();
					
					System.out.println("\"Volume\" header clicked.");
				}
//			}
			
			System.out.println(" ");
		}
		catch(StaleElementReferenceException ex)
		{
			clickVolumeHeaderAndWait(reverseOrder);
		}
		catch(InterruptedException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	
	/**
	 * Because the web page updates its data frequently,
	 * we are likely to come across StaleElementReferenceException.
	 * Therefore, we use a recursive method which restarts itself
	 * only if/when it encounters the error/exception.
	 * 
	 * @param rows	A List<WebElement> representing a list of rows.
	 */
	public static List<Double> updatedStringsList(List<WebElement> rows)
	{
		List<Double> 	raws 		= new ArrayList<Double>();
		boolean			isFinished	= false;
		
		while((curFails < maxFails) && !isFinished)
		{
			try
            {
                for(int x = 0; x < rows.size(); x++)
                {
                    String  raw = rows.get(x).findElement(By.xpath(".//td[5]")).getText().trim();
                    int     idx = raw.lastIndexOf("%");
                    String  str = raw.substring(0, idx);
                    double  val = Double.parseDouble(str);
    
                    raws.add(val);
                }
                
                isFinished = true;
            }
            catch(StaleElementReferenceException ex)
            {
            	int errorSpanCount = driver.findElements(By.xpath("//span[contains(., 'Please try reloading the page.')]")).size();
    			
    			if(errorSpanCount > 0)
    			{
    				driver.get(pageURL1);
    				getMaxPercentChanged();
    				return null;
    			}
    			else
    			{
    				curFails++;
    				
	            	final WebElement		newTBod		= driver.findElement(By.xpath("//table/tbody"));
					final List<WebElement>	newRows		= newTBod.findElements(By.xpath(".//tr"));
					
					return updatedStringsList(newRows);
    			}
            }
			finally
			{
				if(isFinished)
				{
					return raws;
				}
				else if(curFails >= maxFails)
				{
					System.err.println("Failure : " + curFails);
					System.err.println("Max failures reached, ending test.");
					return null;
				}
				else
				{
					System.err.println("Failure    : " + curFails);
					System.err.println("isFinished : " + isFinished + "\r\n");
				}
			}
		}
		
		return raws;
	}
	
	
	public static void waitForPageToLoad()
	{
		ExpectedCondition<Boolean>	pageLoadCondition;
		Wait<WebDriver>				wait;
															
		pageLoadCondition		= 	new ExpectedCondition<Boolean>()
									{
									@Override
										public Boolean apply(WebDriver driver)
									{
										return ((JavascriptExecutor)driver)
												.executeScript("return document.readyState")
												.equals("complete");
									}
									};

		wait					= 	new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(30))
									.pollingEvery(Duration.ofSeconds(1));

		wait.until(pageLoadCondition);
	}
	
	
	public static void moveBrowserRight()
	{
		//	set focus on browser window
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.focus();");
		
		try
		{
			Robot robot = new Robot();
			
			//	pressing keys
			robot.keyPress(KeyEvent.VK_WINDOWS);
			robot.keyPress(KeyEvent.VK_RIGHT);
			
			//	releasing keys
			robot.keyRelease(KeyEvent.VK_WINDOWS);
			robot.keyRelease(KeyEvent.VK_RIGHT);
		}
		catch(AWTException e)
		{
			System.err.println("\r\n"+ e.getMessage() + "\r\n");
		}
	}
}
