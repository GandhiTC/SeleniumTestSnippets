package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import org.apache.commons.lang3.time.DurationFormatUtils;


public class Duration
{
	public static void main(String[] args)
	{
		System.out.println("Starting timer.");
		
		long	startTime	= System.currentTimeMillis();
		
		try
		{
			Thread.sleep(4321L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		long	endTime		= System.currentTimeMillis();
		long	duration	= endTime - startTime;
		
		System.out.println("Stopping timer.\r\n");
		System.out.println("It took:");
		System.out.println(DurationFormatUtils.formatDuration(duration, "dd 'days' HH 'hours' mm 'minutes' ss 'seconds' SSS 'milliseconds'", true));
		System.out.println("to complete running this test.");
	}
}
