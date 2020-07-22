package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestNG_BeforeAfterAnnotations
{
	@BeforeSuite
    public void beforeSuiteDemo()
    {
        System.out.println("\nThis is before a <suite> start tag.");
    }


    @BeforeTest
    public void beforeTestDemo()
    {
        System.out.println("\tThis is before a <test> start tag.");
    }


    @BeforeClass
    public void beforeClassDemo()
    {
        System.out.println("\t\tThis is before a <class> start tag.\n");
    }


    @BeforeMethod
    public void beforeMethodDemo()
    {
        System.out.println("\t\t\tThis is before a method that is annotated by @Test.");
    }


    @Test
    public void testADemo()
    {
        System.out.println("\t\t\t\tThis is the testADemo() method.");
    }


    @Test
    public void testBDemo()
    {
        System.out.println("\t\t\t\tThis is the testBDemo() method.");
    }


    @Test
    public void testCDemo()
    {
        System.out.println("\t\t\t\tThis is the testCDemo() method.");
    }


    @AfterMethod
    public void afterMethodDemo()
    {
        System.out.println("\t\t\tThis is after a method that is annotated by @Test.\n");
    }


    @AfterClass
    public void afterClassDemo()
    {
        System.out.println("\t\tThis is after a </class> end tag.");
    }


    @AfterTest
    public void afterTestDemo()
    {
        System.out.println("\tThis is after a </test> end tag.");
    }


    @AfterSuite
    public void afterSuiteDemo()
    {
        System.out.println("This is after a </suite> end tag.");
    }
}
