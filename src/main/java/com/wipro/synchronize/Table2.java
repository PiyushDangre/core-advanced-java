package com.wipro.synchronize;

public class Table2 {

	int val ; 
	
	public synchronized void printTable(int n) throws InterruptedException {
		synchronized(this){
			for(int i = 1 ; i <= n ; i++) {
				System.out.println(n*i);
				Thread.sleep(100);
			}
		}
	}
}
