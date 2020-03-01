package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.Random;



public class ThreadLocalRandInt implements Runnable
{
	private ThreadLocal<Integer> 	value 		= new ThreadLocal<>();
	
	
	public ThreadLocalRandInt()
	{
		value.set(randomInt());
	}
	
	
	public int value()
	{
		return value.get();
	}


	private int randomInt()
	{
		//	(range size + 1) + minimum number
		//	range size							=	by default, 0 to given number - 1 (excludes range max number)
		//	+ 1									=	add 1 to include range max
		//	+ minimum number					=	the minimum value allowed for generated number
		
		//	Example
		//	new Random().nextInt(11 + 1) + 10
		//	Random().nextInt(11 + 1)			=	[ 0...11]
		//	+ 10 								=	[10...21]
		
//		return new Random().nextInt((max - min) + 1) + min;
//		return (int)(Math.random() * ((max - min) + 1)) + min;
		return new Random().ints(1, Integer.MIN_VALUE, Integer.MAX_VALUE).limit(1).findFirst().getAsInt();
	}


	@Override
	public void run()
	{
	}
}
