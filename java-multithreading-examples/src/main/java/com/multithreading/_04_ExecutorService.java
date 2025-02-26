package com.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * - java.util.concurrent.ExecutorService is used to delegate tasks to a threadpool implementation for carrying out them asynchronously.
 * - Once the thread has delegated the task to the ExecutorService, the thread continues its own execution independent of the execution of that task
 * - The ExecutorService then executes the task concurrently, independently of the thread that submitted the task.
 * - Executors is the factory class for creating implementation of ExecutorService.
 * 
 * - Source ==> https://jenkov.com/tutorials/java-util-concurrent/executorservice.html
 * 
 */
public class _04_ExecutorService {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		
		/**
		 * Various implementations of executorservice -> 
		 */
		
		ExecutorService executorService1 = Executors.newSingleThreadExecutor(); // Single thread

		ExecutorService executorService2 = Executors.newFixedThreadPool(10); // Fixed Thread Pool // Most commonly used with initial no. of threads created.

		ExecutorService executorService3 = Executors.newScheduledThreadPool(10); // Scheduled Thread Pool
		
		/**
		 * - ExecutorService can execute instances of Runnable and Callable both. 
		 * - Following are the different ways shown using execute(), submit(), invokeAny() and invokeAll() methods - 
		 */
		
		ExecutorService executorService = Executors.newFixedThreadPool(10); // defined Fixed Thread Pool first

		
		/** --- EXECUTE() METHOD ---
		 * 
		 *  - Using .execute method and passing runnable instance (most common way).
		 *  - In this we do not get back any return value from the thread after completion because Runnable has .run() method which has void return type. 
		 *  - We will not be able to make out if the thread has run successfully as we do not get any return value of .execute() method.
		 */
		executorService.execute(() -> System.out.println("This is Runnable in .execute() method"));
		
		/**
		 * --- SUBMIT() METHOD ---
		 * 
		 * - If we use .submit() method of executorservice then we can pass both runnable and callable. 
		 * - This method will give us Future return type so we will get to know if the operation is successfull by using future.isDone() flag. 
		 * - If we use callable we can also get return value back after the callable is executed. We can check if it is null to determine if operation was success or not.  
		 */
		
		//Using runnable
		Future f1 = executorService.submit(() -> System.out.println("This is runnable from .submit() method!"));
		
		while(!f1.isDone()) {
			Thread.sleep(100); // waits till f1 is done // This is a blocking call and not a good practice.
		}
		
		System.out.println("f1 is done : "+f1.isDone()); // Prints -> f1 is done : true
		
		// Using callable
		Future<String> f2 = executorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "This is callable from .submit() method!";
			}
		
		});
		
		while(!f2.isDone()) {
			Thread.sleep(100); // waits till f2 is done // This is a blocking call and not a good practice.
		}
		
		System.out.println("f2 is done : "+f2.isDone()); // Prints -> f2 is done : true
		
		// With callable we also get the return value. Note that this .get() is also a blocking call. 
		System.out.println("The callable f2 returned - "+f2.get()); // Prints -> The callable f2 returned - This is callable from .submit() method!

		// We can use timeout method variation of .get() where if response is not received in particular time, it will throw TimeoutException.
		System.out.println("The callable f2 returned within timeout - "+f2.get(1000, TimeUnit.MICROSECONDS)); // Prints -> The callable f2 returned within timeout - This is callable from .submit() method!
				
	/**
	 * - --- INVOKEANY() METHOD ---
	 * 
	 * - For invoke any we can pass a collection of callables
	 * - the return of executorService.invokeAny() will be the single return value string of any one callable. 
	 * - So any one Callable will be executed at random. We do not have control over which one gets executed. 
	 * - Does not accept runnable interface collection.
	 */
		
		List<Callable<String>> listOfCallables = new ArrayList<Callable<String>>();
		
		listOfCallables.add(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				return "Callable 1";
			}
		});
		
		listOfCallables.add(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				return "Callable 2";
			}
		});
		
		listOfCallables.add(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				return "Callable 3";
			}
		});
		
		String result = executorService.invokeAny(listOfCallables); 
		
		System.out.println("Result returned by invokeAny() in executorService => "+result); // Prints => "Result returned by invokeAny() in executorService => Callable 1"
		
		/**
		 * --- INVOKEALL() METHOD ---
		 * 
		 * Similarly, invokeAll will accept the collection of callables and give back collection of Futures.
		 * - We can traverse through the collection of futures and get each future value using future.get().
		 * - Does not accept runnable interface collection.
		 */
		List<Future<String>> listOfFutures =  executorService.invokeAll(listOfCallables);
		
		for(Future<String> future : listOfFutures) {
			System.out.println("Result in invokeAny -> "+ future.get()); // Prints all the returned values of futures.
		}
		
		executorService.shutdown(); // This is mandatory otherwise the threads will still live of the threadpool even after our calling program ends. 
		
		
		
	}

	
	
	

}

