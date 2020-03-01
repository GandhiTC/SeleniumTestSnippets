package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ThreadedActions
{
	public static void main(String[] args)
	{
		//	Test 1	-	Get the id of the thread thats running this script
		System.out.println("Thread ID running main()          \t\t: " + Thread.currentThread().getId());
		
		
		
		//	Test 2	-	Get the id of a new thread that invokes a new Runnable()
		Thread newThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				//	do something
			}
		});
		
		newThread.start();
		
		System.out.println("Thread ID running a new thread    \t\t: " + newThread.getId());
		
		
		
		//	Test 3	-	Get the id of a new thread that invokes an existing class
		ThreadedIncrementer	incrementer	= new ThreadedIncrementer(0);
		Thread 				classThread	= new Thread(incrementer);
		
		classThread.start();
		
		System.out.println("Thread ID running a class thread  \t\t: " + classThread.getId());
		
		
		
//		new Thread(MyClass::doWork).start();
//		new Thread(() -> doWork(someParam));
		
		
		
		//	Test 4	-	Get the id of a new thread thats running a static method in this class
		Thread staticMethodThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				staticMethod();
			}
		});
		
		staticMethodThread.start();
		
		
		
		//	Test 5	-	Get the id's of new threads managed by an executor service that uses a fixed thread pool
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
		
		
		
		//	Test 6	-	Get the id of a new thread managed by a single thread executor service
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
		
		
		
		//	Test 7	-	Get id's of new threads which call on a class that uses a ThreadLocal variable.
		//				1)  Before : create variables and print to console the thread id and randomly generated integer
		//				2)  During : using a new thread, re-assign the same variables, then print to console again - values should differ from step 1 values
		//				3)	After  : without re-assigning the variables, print to console again - values should match step 1 values
		ThreadLocalRandInt 	randInt1	= new ThreadLocalRandInt();
		ThreadLocalRandInt 	randInt2	= new ThreadLocalRandInt();
		
		Thread 				randCls1	= new Thread(randInt1);
		Thread 				randCls2	= new Thread(randInt1);
		
		randCls1.start();
		randCls2.start();
		
		//	1)  Before
		System.out.println("randInt1");
		System.out.println("\tThread ID (Before) = " + randCls1.getId());
		System.out.println("\tRandom #  (Before) = " + randInt1.value());
		
		System.out.println("randInt2");
		System.out.println("\tThread ID (Before) = " + randCls2.getId());
		System.out.println("\tRandom #  (Before) = " + randInt2.value());
		
		//	2)	During
		Thread addedThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				ThreadLocalRandInt 	randInt1	= new ThreadLocalRandInt();
				ThreadLocalRandInt 	randInt2	= new ThreadLocalRandInt();
				
				Thread 				randCls1	= new Thread(randInt1);
				Thread 				randCls2	= new Thread(randInt1);
				
				randCls1.start();
				randCls2.start();
				
				System.out.println("randInt1");
				System.out.println("\tThread ID (During) = " + randCls1.getId());
				System.out.println("\tRandom #  (During) = " + randInt1.value());
				
				System.out.println("randInt2");
				System.out.println("\tThread ID (During) = " + randCls2.getId());
				System.out.println("\tRandom #  (During) = " + randInt2.value());
			}
		});
		addedThread.start();
		
		//	3)	After
		System.out.println("randInt1");
		System.out.println("\tThread ID (After) = " + randCls1.getId());
		System.out.println("\tRandom #  (After) = " + randInt1.value());
		
		System.out.println("randInt2");
		System.out.println("\tThread ID (After) = " + randCls2.getId());
		System.out.println("\tRandom #  (After) = " + randInt2.value());
	}
	
	
	public static synchronized void staticMethod()
	{
		System.out.println("Thread ID running a static method \t\t: " + Thread.currentThread().getId());
	}
}
