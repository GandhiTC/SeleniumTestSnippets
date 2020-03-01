package com.github.GandhiTC.java.SeleniumTestSnippets.code;



public class ThreadedIncrementer implements Runnable
{
	private int value;
	
	
	public ThreadedIncrementer(int value)
	{
        this.value = value;
    }
	
	
	public synchronized int value()
	{
		return value;
	}
	
	
	public synchronized void increment()
	{
		value++;
	}
	
	
	public synchronized void reset()
	{
		this.value = 0;
	}


	@Override
	public void run()
	{
	}
}
