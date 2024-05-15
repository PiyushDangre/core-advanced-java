package com.wipro.synchronize;

public class Table3 {
	


	int val ; 
	
	public static synchronized void printTable(int n) throws InterruptedException {
			for(int i = 1 ; i <= n ; i++) {
				System.out.println(n*i);
				Thread.sleep(100);
			}
	}
}
