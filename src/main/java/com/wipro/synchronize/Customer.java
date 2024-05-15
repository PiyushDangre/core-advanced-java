package com.wipro.synchronize;

public class Customer {

	String customerName = "Piyush Dangre";
	int amount = 10000;

	public synchronized void withdraw(int amt) throws InterruptedException {
		System.out.println(customerName + " is going to withdraw amount " + amt);
		
		if(amt > this.amount) {
			System.out.println("Bank balance not enough. You are broke! Waiting for you to deposit...");
			wait();
		}
		
		if(amt <= this.amount) {
			System.out.println("Withdrawn money!");
			this.amount = this.amount - amt;
		}
	}
	
	public synchronized void deposit(int amt) {
		System.out.println("Deposit amount == " + amt);
		this.amount = this.amount + amt ;
		notify();
	}

}
