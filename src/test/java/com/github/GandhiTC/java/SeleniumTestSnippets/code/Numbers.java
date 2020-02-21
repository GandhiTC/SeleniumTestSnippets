package com.github.GandhiTC.java.SeleniumTestSnippets.code;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class Numbers
{
	@Test
	public void UseNumbers()
	{
		System.out.println("How many numbers do you want to compare?");
		
		Scanner	in		= new Scanner(System.in);
		
		while(!in.hasNextInt())
		{
			System.err.println("\r\nPlease try again, enter a number");
			in.next();
		}
		
		int		HowMany	= in.nextInt();
		
		System.out.println("\n");
		List<Integer>	NumList	= new ArrayList<Integer>();
		int				num[]	= new int[HowMany];

		for(int i = 0; i < num.length; i++)
		{
			System.out.println("enter integer value for #" + (i + 1));
			num[i] = in.nextInt();
			NumList.add(num[i]);
		}
		
		System.out.println("\nEnter \"1\" if you want to get the largest number\nEnter \"2\" if you want to get the smallest number\nEnter \"3\" if you want to get the mean number\nEnter \"4\" if you want to get the median number");
		int WhichOne = in.nextInt();

		if((WhichOne != 1) & (WhichOne != 2) & (WhichOne != 3) & (WhichOne != 4))
		{
			System.out.println("Defaulting to getting largest number\n");
			WhichOne = 1;
		}
		/*
		 if you would like to call this class from another class, declare: Numbers OurNumbers = new Numbers();
		 then we can use OurNumbers.GetLargest(NumList) OurNumbers.GetMedian(NumList) etc etc
		 */

		if(WhichOne == 1)
		{
			int x = GetLargest(NumList);
			System.out.println("\n" + x + " is the largest number in your list\n");
		}
		else if(WhichOne == 2)
		{
			int y = GetSmallest(NumList);
			System.out.println("\n" + y + " is the smallest number in your list\n");
		}
		else if(WhichOne == 3)
		{
			double xx = GetMean(NumList);
			System.out.println("\n" + xx + " is the mean number in your list\n");
		}
		else if(WhichOne == 4)
		{
			double yy = GetMedian(NumList);
			System.out.println("\n" + yy + " is the median number in your list\n");
		}
		in.close();
	}


	public int GetLargest(List<Integer> numList)
	{
		// uses the smallest possible integer value as a place holder
		int CurrentLargest = Integer.MIN_VALUE;

		for(int x : numList)
		{

			if(x > CurrentLargest)
			{
				CurrentLargest = x;
			}
		}
		return CurrentLargest;
		/*
		// uses the smallest possible integer value as a place holder
		int CurrentLargest = Integer.MIN_VALUE;
		for (int i = 0; i < numList.size(); i++)
		{
			if (numList.get(i) > CurrentSmallest)
			{
				CurrentLargest = numList.get(i);
			}
		}
		
		return CurrentLargest;
		*/
	}


	public int GetSmallest(List<Integer> numList)
	{
		/*
		// uses the largest possible integer value as a place holder
		int CurrentSmallest = Integer.MAX_VALUE;
		
		for (int y : numList)
		{
			if (y < CurrentSmallest)
			{
				CurrentSmallest = y;
			}
		}
		
		return CurrentSmallest;
		*/
		// uses the largest possible integer value as a place holder
		int CurrentSmallest = Integer.MAX_VALUE;

		for(int i = 0; i < numList.size(); i++)
		{

			if(numList.get(i) < CurrentSmallest)
			{
				CurrentSmallest = numList.get(i);
			}
		}
		return CurrentSmallest;
	}


	public double GetMean(List<Integer> numList)
	{
		double sum = 0;

		for(double i : numList)
		{
			sum += i;
		}
		return sum / numList.size();
	}


	public double GetMedian(List<Integer> numList)
	{
		Collections.sort(numList);

		if(((numList.size() / 2) * 2) == numList.size())
		{
			return (double)(numList.get(numList.size() / 2) + numList.get((numList.size() / 2) - 1)) / 2;
		}
		else
		{
			return numList.get(numList.size() / 2);
		}
	}
}
