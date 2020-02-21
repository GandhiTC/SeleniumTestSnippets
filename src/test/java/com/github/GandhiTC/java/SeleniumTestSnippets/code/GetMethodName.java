package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.testng.annotations.Test;
//import org.junit.Test;



public class GetMethodName
{
	private void doIt()
	{
		//	Test 1
		Object	myObject	= new Object(){ };
		String	className	= myObject.getClass().getEnclosingClass().getName();
		String	methodName	= myObject.getClass().getEnclosingMethod().getName();
		
		System.out.println("\r\n" + "Test 1");
		System.out.println("Class  Name: " + className);
		System.out.println("Method Name: " + methodName + "\r\n");
		
		
		//	Test 2
		StackTraceElement[]	stacktrace		= Thread.currentThread().getStackTrace();
		StackTraceElement	element			= stacktrace[2];
							methodName		= element.getMethodName();
							className		= element.getClassName();
		
		System.out.println("Test 2");
		System.out.println("Class  Name: " + className);
		System.out.println("Method Name: " + methodName + "\r\n");
	}
	
	
	@Test
	public void runTests()
	{
		doIt();
	}
}
