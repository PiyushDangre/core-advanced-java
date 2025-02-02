package com.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class StringPractice {

	public static void main(String[] args) {
		
		/**
		 * .intern() method adds the String created using new keyword also in pool.
		 * Ideally without intern(), the object is created in heap memory.
		 */
		
		String s1 = new String("This is a string").intern();
		
		String s2 = "This is a string";
		
		System.out.println(s1==s2); // Should be false generally. But intern() method adds s1 in pool so is true.
	
	/**
	 * - split(delimeter) method accepts a parameter of delimiter and returns String[] according to split.
	 * - actually the delimeter above can be any regex that is matching.
	 * 
	 */
		
		String s3 = "Help is on the way!";
		String[] s3Array = s3.split(" ");
		
		Arrays.asList(s3Array).forEach(System.out::print); // Prints Helpisontheway!
		

		
		/**
		 * - Substring() method accepts 1 or 2 parameters.
		 * - Substring gives back a subset of the string according to the positions passed by us.
		 * - .substring(startPosition) gives back subset string which is inclusive of startPosition string.
		 * - .substring(startPosition, endPosition) gives back subset string which is inclusive of startPosition string but is exclusive of endPosition string.
		 * - Here we are using .indexOf() to get the indexes of start and end position based on actual string that we are passing.
		 */
		String s3SubString = s3.substring(s3.indexOf("is"));
		System.out.println("\n"+s3SubString); // Prints "is on the way!"
		
		String s3SubString2 = s3.substring(s3.indexOf("on"), s3.indexOf("way"));
		System.out.println(s3SubString2); // Prints "on the"
		
		/**
		 * Using split() method to convert below string into map of key-value pairs
		 */
		String s4 = "acsTransId= 123Hyt || phone= 8299 || city= New York";
		
		String[] s4Array = s4.split("\\|\\|"); // We need to add escape character \\ as | is a special character in regex
		
		System.out.println("\n------------------\n");
		Map<String, String> map = new HashMap<>();
		
		for(String ss : s4Array) {
			System.out.println(ss.trim());
			String[] keyValue = ss.split("=");
			map.put(keyValue[0].trim(), keyValue[1].trim()); // trim() used to eradicate the spaces.
		}
		
		System.out.println("\nTraversing Map ---\n");
		
		for(Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key - "+entry.getKey()+" and value - "+entry.getValue());
		}
		
		/**
		 * Join two strings using delimiter - 
		 * - There are two ways of doing it -
		 *  -- Using String.join()
		 *  -- Using StringJoiner instance
		 */
		
		String statement  = String.join("||", s4Array);
		System.out.println(statement); // Prints-> acsTransId= 123Hyt || phone= 8299 || city= New York
		
		StringJoiner sJoiner = new StringJoiner("||");
		for(String ss : s4Array) {
			sJoiner.add(ss);
		}
		
		System.out.println(sJoiner.toString()); // Prints-> acsTransId= 123Hyt || phone= 8299 || city= New York
	
	}
}
