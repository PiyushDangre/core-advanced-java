package com.multithreading;

/**
 * - wait(), notify() and notifyAll() methods are the entities using which we can communicate between threads explicitly.
 * - This is also called interthread signalling.
 * - In the example below there are two synchronized blocks which have been synchronized on the same instance of monitor object
 * - monitor object here is bankAccount instance. 
 * - That means any new thread will not enter any synchronized block of the monitor object instance until the current thread
 *   executing any of the synchronized block exits the synchronized block.
 *  - More info in the end of the class. Scroll down. 
 *  - Also check jenkov -> https://jenkov.com/tutorials/java-concurrency/thread-signaling.html
 * 
 */
public class _01_InterThreadCommunication {

	public static void main(String[] args) {
		
		/**
		 * We create two threads first
		 */
		
		BankAccount bankAccount = new BankAccount();
		Bank bank = new Bank(bankAccount);
		
		Thread t1 = new Thread() {
			
			@Override
			public void run() {
				bank.withdraw();			
			}
		};
		
		Thread t2 = new Thread() {
			
			@Override
			public void run() {
				bank.deposit();			
			}
		};
		
		t1.start();
		t2.start();
	}

}

class BankAccount {

	public void depositMoney() {
		System.out.println("Money Deposited!");
	}
	
	public void withdrawMoney() {
		System.out.println("Money Withdrawn!");
	}
}

/**
 * - In the example below if we do not use wait and notify methods, then "Money Withdrawn!" is printed before "Money Deposited!". Which is wrong. 
 * - So we use wait() before withdrawing money, when we get notification from another thread that money has been deposited, only then we withdraw money.
 * - That means the wait() method makes the current thread in the monitor object wait indefinately until another thread sends any notification. 
 * - For this signalling system to work, both the sync blocks should be synchronized on same instance of same object.
 * - Just like notify() which wakes up just one waiting thread, there is notifyAll() method which wakes up all the threads waiting on the same instance.
 */
class Bank {
	
	public BankAccount bankAccount ;
	
	public Bank(BankAccount ba) {
		this.bankAccount = ba;
	}
	
	public void withdraw() {
		synchronized (bankAccount) {
			try {
				bankAccount.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bankAccount.withdrawMoney();
		}
	}
	
	public void deposit() {
		synchronized (bankAccount) {
			bankAccount.depositMoney();		
			bankAccount.notify();
		}
	}
	
}
