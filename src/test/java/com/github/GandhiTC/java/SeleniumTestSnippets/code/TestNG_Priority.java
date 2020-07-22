package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.testng.annotations.Test;



//	By default, TestNG tests in alphabetical order
//	To control order of execution, use 'priority' property



public class TestNG_Priority
{
	@Test(priority = 1)
	public void testC()
	{
		System.out.println("test C");
	}
	
	
	@Test(priority = 2)
	public void testA()
	{
		System.out.println("test A");
	}
	
	
	//	Not setting priority is the same as setting it to 0
	@Test
	public void testB1()
	{
		System.out.println("test B1");
	}
	
	
	//	Within the same priority, TestNG falls back to alphabetical order
	@Test
	public void testB2()
	{
		System.out.println("test B2");
	}
	
	
	@Test(priority = -1)
	public void testD()
	{
		System.out.println("test D");
	}
}
