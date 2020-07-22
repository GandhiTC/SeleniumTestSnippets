package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.io.IOException;
import org.testng.annotations.Test;



public class TestNG_Exceptions
{
	//	Test fails if it does NOT result in the expected exception being thrown
	@Test(expectedExceptions = IOException.class)
	public void testA()
	{
		System.out.println("test A");
		throw new ArithmeticException();
	}
	
	
	//	Test passes if it results in the expected exception being thrown
	@Test(expectedExceptions = IOException.class)
	public void testB() throws IOException
	{
		System.out.println("test B");
		throw new IOException();
	}
	
	
	//	Test passes if it results in any of the expected exceptions being thrown
	@Test(expectedExceptions = {ArithmeticException.class, IOException.class})
	public void testC()
	{
		System.out.println("test C");
		throw new ArithmeticException();
	}
	
	
	//	Test fails if it does NOT result in any of the expected exceptions being thrown
	@Test(expectedExceptions = {ArithmeticException.class, IOException.class})
	public void testD()
	{
		System.out.println("test D");
		throw new NullPointerException();
	}
}
