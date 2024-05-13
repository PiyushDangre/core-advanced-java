package com.wipro.immutable_class;

public final class Immutable {  // Class is final

	public final String pancardnumber;  // Instance variable is final
	
	public Immutable(String pan) {
		this.pancardnumber = pan;
	}
	
	public String getPan() {  // No setter method of instance variable. This method is also converted into final method by the compiler.
		return pancardnumber;
	}
}
