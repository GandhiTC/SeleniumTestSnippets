package com.github.GandhiTC.java.SeleniumTestSnippets.code;



public class CallStaticMethodOrVariable
{
//	public CallStaticMethodOrVariable()
	public static void main(String[] args)
	{
//		RunAsJavaApp runAsJavaApp = new RunAsJavaApp();
		RunAsJavaApp.main(null);
		// RunAsJavaApp is the class name from the RanAsJavaApp file
		// it is NOT a new object (the line declaring a new object is commented out)
		// you can call static methods/variables directly by ClassName.StaticMethodName / ClassName.StaticVariableName
		// you can NOT call static methods/variables from instances/objects
		// if you want to use an instance/object, RunAsJavaApp.main() needs to be replaced by a constructor
	}
}
