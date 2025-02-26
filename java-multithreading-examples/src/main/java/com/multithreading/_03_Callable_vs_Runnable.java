package com.multithreading;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable vs Runnable
 * - Runnable interface has a method which does not return any value
 * - Callable interface has method 'call' which returns a value of generic data type (defined by us).
 * - Runnable can be passed on t0 Thread() constructor but not callable.
 * - Callable can be passed on to ExecutorService.submit() to utilise a thread out of thread pool. 
 *  */
public class _03_Callable_vs_Runnable {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// Creating a Runnable instance
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("This is a thread created through runnable !");
				
			}
		};
		
		Thread t1 = new Thread(r);
		
		t1.start(); // Prints "This is a thread created through runnable !".
		
		
		// Creating a Callable Instance
		
		Callable<String> c = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "This is a thread created through callable !";
			}
		};
		
		ExecutorService exs = Executors.newFixedThreadPool(2); // Callable has to be passed on to executorservice.submit() method
		
		Future<String> future = exs.submit(c);
		
		System.out.println(future.get()); // Prints "This is a thread created through callable !".
		
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		
		System.out.println("No. of threads "+threadSet.size());
	
		exs.shutdown();
		
		
	}

}
