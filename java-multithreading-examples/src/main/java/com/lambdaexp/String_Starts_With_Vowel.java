package com.lambdaexp;

import java.util.Arrays;

public class String_Starts_With_Vowel {
	
	/**
	 * Write a program to print cities starting with a vowel character
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] cities = {"Nagpur", "Delhi", "Ukraine", "Ahmedabad"};
		
		String[] vowels = {"A", "E", "I", "O", "U"};
		
		Arrays.stream(cities).filter(s -> Arrays.asList(vowels).contains(s.substring(0, 1)))
				.forEach(System.out::println);
		
	}
	
	
	

}
