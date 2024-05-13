package com.wipro;

import com.wipro.constructor_chaining.Student;
import com.wipro.initializer_block.Cashier;
import com.wipro.initializer_block.Employee;
import com.wipro.typecasting.Animal;
import com.wipro.typecasting.Dog;

public class Main {

	/**
	 * Static block which executes even before the main method. This is executed by classloader.
	 */
	static {
		System.out.println("This is a static block which executes before main method");
	}
	
	public static void main(String args[]) {
		System.out.println("This is main method. Project working");
		
		/**
		 *  Constructor chaining example.
		 *  - this() is used for chaining constructor. this() calls the default construtor.
		 *  - this() should be used as the first line in constructor from which its being called or chained to.
		 *  
		 */
		Student st = new Student(32, "Phd");
		System.out.println("Student created - "+ st.toString());
		
		/**
		 * Instance initialization and static block
		 * Order of calling is as below :-
		 * 1. Static block of Parent
		 * 2. Static block of Child
		 * 3. Constructor of Parent
		 * 4. Initialization block of Parent
		 * 5. Constructor of Child
		 * 6. Initialization block of child.
		 * - Even though in sysout the init block is printed before constructor. Its because its copied by JVM inside the constructor in that way.
		 * --> https://www.javatpoint.com/instance-initializer-block
		 */
		Employee e = new Cashier();
		
		/**
		 * Typecasting
		 *  --> There are two types -> Upcasting and downcasting.
		 *  - Upcasting is natural in java. Where Superclass reference is pointing to subclass object.
		 *  - Downcasting is when the subclass reference will point to superclass object. This has to be explicitly cast.
		 *  --> https://www.javatpoint.com/downcasting-with-instanceof-operator
		 */
		
		Animal a = new Dog(); // Upcasting
		System.out.println(" Type is - "+a.getType()); //  Type is - Mammal : DOG
		
		// Dog d = new Animal(); --> This throws compiler error. Have to cast.
		
		// Dog d = (Dog) new Animal(); --> This is also called as Downcasting. This compiles but throws ClassCastException because compiler is not sure if the Animal is really an instance of Dog. It can be an instance of Cat also. Hence compiler confused and throws exception.
		
		// We can avoid the exception using instanceOf operator.
		
		Animal ab = new Animal();
		if(ab instanceof Dog) {
			Dog d  = (Dog)ab;  // This is valid Downcasting. Now we are not getting exception and this stmt gets compiled also because control doesn't reach here. InstanceOf makes sure that the incoming instance is really of type dog.
		}

		String s1 = "Piyush";
		String s2 = "Piyush";
		String s3 = new String("Piyush");
		
		//System.out.println(s2.hashCode());
		//System.out.println(s1.hashCode()); // Both the hashcodes are same indicating the same instamce used from stringpool
		//System.out.println(s3.hashCode());
		
		System.out.println("String equals --> " + s1.equals(s3)); // true
		System.out.println(s1==s2); // true
		System.out.println(s1==s3); // false
		
		String s4 = "Dangre";
		String s5 = new String("Dangre");
		String s6 = s4.intern();
		
		System.out.println(s6);
		System.out.println(s6==s4); // same string is returned from pool.
		
		String s = null;
		
		try {
			s.concat("Null");	
		}catch(RuntimeException ex) {
			System.out.println("Exception caught --> "+ ex.getMessage() );
		}
		
		
	}
}
