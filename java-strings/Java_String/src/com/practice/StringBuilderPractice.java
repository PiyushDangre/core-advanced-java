package com.practice;

/**
 * - StringBuilder is Mutable
 * - It is not thread safe (Use stringbuffer for thread safety)
 * - Manipulation is faster
 */
public class StringBuilderPractice {
	
		public static void main(String[] args) {
			StringBuilder sb = new StringBuilder("Piyush ");
			
			sb.append("and Aprajita!");
			
			System.out.println(sb.toString()); // Piyush and Aprajita!
			
			sb.replace(sb.indexOf("and"), sb.indexOf(" A"), "weds");
			
			System.out.println(sb.toString()); // Piyush weds Aprajita!
			
			sb.insert(sb.length(), " Forever and ever..."); // Piyush weds Aprajita! Forever and ever...
			
			System.out.println(sb.toString());
			
			sb.reverse(); // Reverses entire stringx
			
			System.out.println(sb.toString()); // ...reve dna reveroF !atijarpA sdew hsuyiP
			
			System.out.println(sb.capacity()); // get stringbuilder current capacity
			
			sb.reverse() ; // Reversing back to original string
			
			sb.deleteCharAt(sb.indexOf(" A"));
			
			System.out.println(sb.toString()); // Piyush wedsAprajita! Forever and ever...

			String s = "There are many people who love many other people!";
			
			StringBuilder sPract = new StringBuilder(s);
			
			// Using lastIndex() to find index of last ocurring substring within string
			
			sPract.replace(sPract.lastIndexOf("people"), sPract.indexOf("!"), "animals");
			
			System.out.println(sPract.toString()); // There are many people who love many other animals!
			
		}
		
}
