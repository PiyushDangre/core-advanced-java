package com.multithreading;

/**
 * - Threadlocal is another way of making code thread safe.
 * - Primarily while using threadlocal, we are setting and getting the values in threadlocal instance. 
 * - Even if two threads are accessing the same instance of threadlocal, they cannot get and set values of each other.
 * - That means the values set in threadlocal are invisible to two different threads. Thus making code thread safe as their cannot be any race condition. 
 * - Even if the get/set are called from synchronized blocks, it doesn't matter. The get returns value only if set is called within the thread, and not from any other thread.
 */
public class _02_ThreadLocal {

	public static void main(String[] args) {
		
		MyBank bank = new MyBank();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (bank) {
					bank.deposit(); // ThreadLocal.set()
					bank.checkBalance(); // ThreadLocal.get()
				}
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (bank) {
					bank.checkBalance(); // ThreadLocal.get()
				}
			}
		};
		
		t1.start();
		t2.start();
		
		/** Prints 
		 * 	Thread-0 - 1000 rs deposited.
			Thread-0 - Balance is 1000
			Thread-1 - Balance is null
		 */
	
	}
}

class MyBank {
	
	ThreadLocal<Integer> myBankAccount = new ThreadLocal<>();
	
	public void deposit() {
		myBankAccount.set(1000);
		System.out.println(Thread.currentThread().getName()+ " - 1000 rs deposited.");
	}
	
	public void checkBalance() {
		Integer balance = myBankAccount.get();
		System.out.println(Thread.currentThread().getName()+ " - Balance is "+balance);
	}
	
}
