package com.wipro.abstract_class;

public interface Container {
	
	public static final int capacity = 0;
	
	public String getCapacity() ;
	
	public default int getNumber() {
		return 1;
	}
	
	public static int getSince() {
		return 3;
	}
	
	
}
