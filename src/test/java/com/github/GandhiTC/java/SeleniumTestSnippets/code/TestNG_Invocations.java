package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import static org.testng.Assert.fail;
import org.testng.annotations.Test;



public class TestNG_Invocations
{
	@Test(invocationCount = 5, invocationTimeOut = 5000)
	public void testA() throws InterruptedException
	{
		System.out.println("test A");
		Thread.sleep(500);
	}
	
	
	@Test(invocationCount = 10, invocationTimeOut = 5000)
	public void testB() throws InterruptedException
	{
		System.out.println("test B");
		Thread.sleep(1500);
	}
	
	
	@Test(invocationCount = 10, skipFailedInvocations = true)
	public void testC() throws InterruptedException
	{
		System.out.println("test C");
		fail("Forced first invocation to fail so that the remaining invocations could be skipped.");
	}
}
