package com.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ** -- This problem was asked in some interviews
 * 
 * - We have a string and we need to determine the number of occurence of each letter in String
 *  - We have to only use STREAM API.
 * 
 */
public class _01_Stream_Problem {

	public static void main(String[] args) {
		
		String s = "PPiiuiyushhh";

		/**
		 *  - Following 2 lines commented as Character.toString() method has been removed in Java 21.
		 *  1. System.out.println( s.codePoints().mapToObj(Character::toString).collect(Collectors.groupingBy(e-> e)));
		 *  2. s.codePoints().mapToObj(Character::toString).collect(Collectors.groupingBy(e-> e)).entrySet().stream().forEach(e -> System.out.println(e.getKey() + "-->"+ e.getValue().size()));
		 */

	   

	   /**
	    * Another way of turning string into a list of strings.
	    * 
	    * We have also used Function.identity() instead of s -> s . Any one can be used. 
	    */
	   
	   
	   String a = "ABBCCCDDDDEBBCA";
	   
	   List<String> ss = Arrays.asList(a.split(""));
	   
		ss.stream().collect(Collectors.groupingBy(Function.identity())).entrySet().stream()
				.forEach(e -> System.out.println(e.getKey() + "-->" + e.getValue().size()));
	   
	   //System.out.println(lengthMap);
		
		/**
		 * This is another way
		 * Here we have made use of Collectors.counting() method as parameter to Collectors.groupingBy()
		 * Also notice the we have created stream using Arrays.stream() method
		 */
		Arrays.stream(a.split("")).collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet().stream()
				.forEach(
						sss -> System.out.println("Key - " + sss.getKey() + " | No. of Occurence - " + sss.getValue()));


		/**
		 * - If we want to preserve the Order of the Map, we have to provide a Supplier Interface
		 * 	 to the groupingBy function.
		 * - This supplier interface should return LinkedHashMap instance of same type, so that
		 * 	 order of insertion is preserved.
		 */
		Arrays.stream(a.split("")).collect(Collectors.groupingBy(e -> e, () -> new LinkedHashMap<String, Long>(), Collectors.counting())).entrySet().stream()
				.forEach(
						sss -> System.out.println("Ordered Key - " + sss.getKey() + " | No. of Occurence - " + sss.getValue()));

	}
}
