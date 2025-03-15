package com.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
		
	    //s.chars().mapToObj(Character::toChars).peek(System.out::println).collect(Collectors.groupingBy(e -> e));

	    //System.out.println(map);
	  
	    // s.chars().forEach(System.out::println);
	    
	    // s.chars().mapToObj(e-> Character.toChars(e)).forEach(System.out::println);
	
	    //List li = s.chars().mapToObj(e-> Character.toChars(e)).collect(Collectors.toList());
	
//	    Iterator<Character> i = li.iterator();
	    
		/*
		 * while(i.hasNext()) { System.out.println(i.next().charValue()); }
		 */
	    
//	   System.out.println( s.chars().mapToObj(e-> char(e))
//			   .flatMap(e -> )
//			   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
	    
	   System.out.println( s.codePoints().mapToObj(Character::toString).collect(Collectors.groupingBy(e-> e)));
	   
	   s.codePoints().mapToObj(Character::toString).collect(Collectors.groupingBy(e-> e)).entrySet().stream().forEach(e -> System.out.println(e.getKey() + "-->"+ e.getValue().size()));
	   
	   /**
	    * Another way of turning string into a list of strings.
	    * 
	    * We have also used Function.identity() instead of s -> s . Any one can be used. 
	    */
	   
	   
	   String a = "ABBCCCDDDDE";
	   
	   List<String> ss = Arrays.asList(a.split(""));
	   
		ss.stream().collect(Collectors.groupingBy(Function.identity())).entrySet().stream()
				.forEach(e -> System.out.println(e.getKey() + "-->" + e.getValue().size()));
	   
	   //System.out.println(lengthMap);
	}
}
