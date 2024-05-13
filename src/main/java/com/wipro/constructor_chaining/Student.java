package com.wipro.constructor_chaining;

public class Student {
	
	int age;
	String grade;
	
	public Student(int age, String grade) {
		this(age);
		this.grade = grade;
	}
	
	public Student(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", grade=" + grade + "]";
	}
	
}
