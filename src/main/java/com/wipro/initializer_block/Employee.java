package com.wipro.initializer_block;

public class Employee {
	
	public int salary;
	public String age;
	
	static {
		System.out.println("Static block of Employee");
	}
	
	public Employee() {
		System.out.println("Default constructor of Employee");
	}
	
	{
		System.out.println("Instance initialization block of Employee");
	}

}
