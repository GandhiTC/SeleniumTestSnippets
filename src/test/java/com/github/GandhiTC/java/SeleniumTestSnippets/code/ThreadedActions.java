package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ThreadedActions
{
	public static void main(String[] args)
	{
		Thread newThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				
			}
		});
		newThread.start();
		
		
		ThreadedIncrementer	incrementer	= new ThreadedIncrementer(0);
		Thread 				classThread	= new Thread(incrementer);
		classThread.start();
		
//		new Thread(MyClass::doWork).start();
//		new Thread(() -> doWork(someParam));
		
		
		Thread staticMethodThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				staticMethod();
			}
		});
		staticMethodThread.start();
		
		
		System.out.println("Thread ID running main()          \t\t: " + Thread.currentThread().getId());
		System.out.println("Thread ID running a new thread    \t\t: " + newThread.getId());
		System.out.println("Thread ID running a class thread  \t\t: " + classThread.getId());
		
		
		final int		numThreads		= 5;
		ExecutorService	execService1	= Executors.newFixedThreadPool(numThreads);

		for(int i = 0; i < numThreads; i++)
		{
			execService1.execute(new Runnable()
			{
				@Override
				public void run()
				{
					long threadId = Thread.currentThread().getId();
					long threadCt = (threadId % numThreads) +1;
					System.out.println("I am thread " + threadId + " (" + threadCt + " of " + numThreads + ")");
				}
			});
		}
		
		execService1.shutdown();
		
		
		//	good for a quick one-timer
//		Executors.newSingleThreadExecutor().submit(someClass::someMethod);
		
		
		ExecutorService	execService2	= Executors.newSingleThreadExecutor();
				
		execService2.submit(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("Thread ID running newSingleThreadExecutor()\t: " + Thread.currentThread().getId());
			}
		});
		
		execService2.shutdown();
	}
	
	
	public static synchronized void staticMethod()
	{
		System.out.println("Thread ID running a static method \t\t: " + Thread.currentThread().getId());
	}
}
