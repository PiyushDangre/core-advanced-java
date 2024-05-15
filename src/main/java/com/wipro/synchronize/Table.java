package com.wipro.synchronize;

public class Table {

	int val ; 
	
	public synchronized void printTable(int n) throws InterruptedException {
		for(int i = 1 ; i <= n ; i++) {
			System.out.println(n*i);
			Thread.sleep(100);
		}
	}
}
