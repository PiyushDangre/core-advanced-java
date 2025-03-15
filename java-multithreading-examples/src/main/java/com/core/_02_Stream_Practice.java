package com.core;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _02_Stream_Practice {

	public static void main(String[] args) {
		
		sumOfEvenNos();
		calculateAvg();
		nosOfStringsStartingWith("P");
		sort();
		mergeSortedStreams();
		sumOfTxnsForEachDay();
		
		
		
	}
	
	private static void sumOfTxnsForEachDay() {
		List<Transaction> transactions = Arrays.asList(
			    new Transaction("2022-01-01", 100),
			    new Transaction("2022-01-01", 200),
			    new Transaction("2022-01-02", 300),
			    new Transaction("2022-01-02", 400),
			    new Transaction("2022-01-03", 500)
			);
		
// My first attempt way
		
		transactions.stream().collect(Collectors.groupingBy(s -> s.date)).entrySet().stream().forEach((s) -> {
			String key = s.getKey();
			Integer sum = s.getValue().stream().mapToInt(t -> t.amt).sum();
			System.out.println("Key - " + key + " and sum - " + sum);
		});		
		
		
		/**
		 * The above code prints -
		 * 	Key - 2022-01-03 and sum - 500
			Key - 2022-01-01 and sum - 300
			Key - 2022-01-02 and sum - 700
		 */
		
		
		// Another refined way. Creates map of key -> date string , value -> Full IntSummaryStatistics object
		
		Map map = transactions.stream().collect(Collectors.groupingBy(s -> s.date , Collectors.summarizingInt(s -> s.amt)));
		
		System.out.println(map); // Creates map of key -> date string , value -> Full IntSummaryStatistics object which has sum
		
		// Another refined way. Creates map of key -> date string , value -> Integer Sum
		// In above example .summarizingInt() is used. Here .summingInt() is used. Note the difference in names and the data returned.

		Map map2 = transactions.stream().collect(Collectors.groupingBy(s -> s.date , Collectors.summingInt(s -> s.amt)));
		System.out.println(map2);
	}

	/**
	 * Merge two sorted lists into a single sorted list using Java streams:
	 */
	private static void mergeSortedStreams() {
		List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
		List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
		List<Integer> concatenatedSortedList = Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList());
		System.out.println(concatenatedSortedList);
	}
	
	

	private static void sort() {
	    List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
	    List < String > colorsSortedAsc = colors.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
		System.out.println("Asc order => "+colorsSortedAsc);
	    List < String > colorsSortedDesc = colors.stream().sorted((s1, s2) -> s2.compareTo(s1)).collect(Collectors.toList());
	    System.out.println("Desc order => "+colorsSortedDesc);
	}

	/**
	 * Write a Java program to count the number of strings in a list that start with a specific letter using streams.
	 * @param string
	 */
	private static void nosOfStringsStartingWith(String string) {
		List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
		Long count = colors.stream().filter(s -> s.startsWith(string)).count();
		System.out.println("Nos of strings starting with letter "+string+" is "+count);
		
	}

	/**
	 * Write a Java program to calculate the average of a list of integers using streams.
	 */
	private static void calculateAvg() {
		List<Integer> nums = Arrays.asList(1, 3, 6, 8, 10, 18, 36);
		OptionalDouble average = nums.stream().mapToInt(s -> s.intValue()).average();
		System.out.println("Average is " + average.getAsDouble()); // Average is 11.714285714285714

	}


/**
 * Find the sum of all even numbers in a list of integers.
 */
	private static void sumOfEvenNos() {
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

		Integer sum = list.stream().filter(s -> s % 2 == 0).mapToInt(s -> s.intValue()).sum();

		System.out.println("Sum of even numbers is "+sum); // Sum of even numbers is 30
		
	}
	
	

}


class Transaction {
	String date;
	Integer amt;
	
	public Transaction(String date, Integer amt) {
		super();
		this.date = date;
		this.amt = amt;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", amt=" + amt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amt, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(amt, other.amt) && Objects.equals(date, other.date);
	}
	
	
	
	
	
}