package com.wipro.initializer_block;

public class Cashier extends Employee {
	
	public int workExp;
	
	static {
		System.out.println("Cashier static block.");
	}
	
	public Cashier() {
		System.out.println("Cashier constructor is called.");
	}
	
	{
		System.out.println("Instance initialization block of Cashier");
	}

}
