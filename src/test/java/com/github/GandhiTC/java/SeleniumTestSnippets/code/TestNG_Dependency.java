package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import static org.testng.Assert.fail;
import org.testng.annotations.Test;



public class TestNG_Dependency
{
	@Test
	public void testA()
	{
		System.out.println("test A");
	}
	
	
	@Test
	public void testB()
	{
		System.out.println("test B");
		fail();
	}
	
	
	@Test(dependsOnMethods = "testB")
	public void testC()
	{
		System.out.println("test C");
	}
	
	
	@Test(dependsOnMethods = {"testA", "testB"})
	public void testD()
	{
		System.out.println("test D");
	}
	
	
	//	Disabling a test is the same as not adding @Test annotation
	@Test(enabled = false)
	public void testE()
	{
		System.out.println("test E");
	}
	
	
	//	If 'ignoreMissingDependencies' is not set to true
	//	while depending on a disabled or missing test method, or a method without @Test annotation
	//	TestNGException will be thrown without any tests being run
	@Test(dependsOnMethods = "testE", ignoreMissingDependencies = true)
	public void testF()
	{
		System.out.println("test F");
	}
}
