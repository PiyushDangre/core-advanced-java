package com.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class has practice questions solutions for common stream api questions.
 * 
 * Source -> https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-1-dc39e3575727
 */
public class _02_Stream_Practice {

	public static void main(String[] args) {
		
		sumOfEvenNos();
		calculateAvg();
		nosOfStringsStartingWith("P");
		sort();
		mergeSortedStreams();
		sumOfTxnsForEachDay();
		kthSmallestElementInArray();
		wordFrequency();
		evenOddPartitioning();
		joinStringsSuffixPrefix();
		compareCollections();
		mergeUnsortedArray();
		sumOfAllDigitsInAnInteger();
		secondLargestNumberinArray();
	}

	/**
	 * Find second largest number in an integer array?
	 * 
	 * 	- We have used mapToObj after generating IntStream using Arrays.stream(). 
	 * 		-- This was done to convert int into Integer object to apply .sorted() method with Comparator.reverseOrder() as parameter.
	 * 	- We have used skip(1) to skip first element after reverse ordering.
	 * 	- we have used findFirst() to get the second element after skipping first.
	 */
	private static void secondLargestNumberinArray() {
		int[] array = { 45, 12, 56, 15, 24, 75, 31, 89 };
		Optional<Integer> secondLargets = Arrays.stream(array).mapToObj(i -> (Integer) i)
				.sorted(Comparator.reverseOrder()).skip(1).findFirst();
		System.out.println("Second largest integer is " + secondLargets.get());
	}

	/**
	 * - Find sum of all digits of a number in Java 8?
	 * - We are using Collectors.summingInt(). Inside that method we are parsing integer from the existing string stream.
	 * - Notice initially we have converted the int to String stream by using String.valueOf(integer) and then calling .split("") method.
	 */
	private static void sumOfAllDigitsInAnInteger() {
		int i = 15623;
		Integer sum = Arrays.stream(String.valueOf(i).split(""))
				.collect(Collectors.summingInt(s -> Integer.parseInt(s)));
		System.out.println("Sum of digits in number is " + sum); // Sum of digits in number is 17
	}

	/**
	 * - Merge two unsorted arrays into single sorted array without duplicates?
	 * - Notice usage of IntStream to concat
	 * - Notice usage of .toArray() terminal method
	 */
	private static void mergeUnsortedArray() {
		int[] a = new int[] { 4, 2, 5, 1 };
		int[] b = new int[] { 8, 1, 9, 5 };
		int[] ab = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).sorted().distinct().toArray();
		System.out.println("Sorted and de-duplicated array - " + Arrays.toString(ab)); // Sorted and de-duplicated array
	}

	/**
	 * Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
	 * 
	 * - Here we are using Collectors.joining() method. It always returns String.
	 */
	private static void joinStringsSuffixPrefix() {
        List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        String s = listOfStrings.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println("Joined String - "+s);
	}

	/**
	 * Implement a method to partition a list into two groups based on a predicate using Java streams:
	 * 
	 * Source - https://www.geeksforgeeks.org/collectors-partitioningby-method-in-java/
	 */
	private static void evenOddPartitioning() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		Map map = numbers.stream().collect(Collectors.partitioningBy(s -> s % 2 == 0));
		
		List<Integer> evenNos = (List<Integer>) map.get(true);
		List<Integer> oddNos = (List<Integer>) map.get(false);

		System.out.println("Even numbers list --> "+evenNos);
		System.out.println("Odd numbers list --> "+oddNos);
		
	}

	/**
	 * Given a list of strings, find the frequency of each word using Java streams:
	 */
	private static void wordFrequency() {
		String[] strArray = {"apple", "banana", "apple", "cherry", 
                "banana", "apple"};
		
		Map map = Arrays.stream(strArray).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		
		System.out.println("Word frequency map 1 ==> "+map); // Word frequency map ==> {banana=2, cherry=1, apple=3}

		/**
		 * - Below is another way of turning string into stream of letters.
		 * - Here the difference is that string has spaces. So we have to use flatmap to convert to individual letters
		 *   after splitting using " " (space) delimiter.
		 */
		
		String inputString = "Java Concept Of The Day";
		
		Map map2 = Arrays.stream(inputString.toLowerCase().split(" ")).flatMap(s -> Arrays.stream(s.split("")))
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		
		System.out.println("Word frequency map2 =>"+map2); // Word frequency map2 =>{p=1, a=3, c=2, d=1, t=2, e=2, f=1, v=1, h=1, y=1, j=1, n=1, o=2}
	}

	/**
	 * Q. Find the 3rd smallest element in an array using Java streams:
	 */
	private static void kthSmallestElementInArray() {
		int[] array = {4, 2, 7, 1, 5, 3, 6};
		/*
		 * Arrays.asList(array).stream().mapToInt(s -> Integer.valueOf(s)) .sorted()
		 * .limit(3) .max();
		 */
		
		OptionalInt thirdSmallest = Arrays.stream(array)
		 .sorted()
		 .limit(3)
		 .max();
		
		System.out.println("third smallest elemement = " + thirdSmallest.getAsInt()); // third smallest elemement = 3
	}

	/**
	 * Sum of transaction amount according to date
	 */
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
	
	
	/**
	 * Sort in ascending and descending order
	 */
	private static void sort() {
	    List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
	    List < String > colorsSortedAsc = colors.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
		System.out.println("Asc order => "+colorsSortedAsc);
	    List < String > colorsSortedDesc = colors.stream().sorted((s1, s2) -> s2.compareTo(s1)).collect(Collectors.toList());
	    System.out.println("Desc order => "+colorsSortedDesc);
	    
	    /**
	     * - Another way of reverse ordering is using Comparator.reverseOrder()
	     */
	    
	    List<String> colorsReversed = colors.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	    System.out.println("Desc order of colors using Comparator.reverseOrder() - "+colorsReversed); // Desc order of colors using Comparator.reverseOrder() - [Red, Pink, Green, Brown, Blue]
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
	
	/**
	 * - here we are sorting collections on the basis of more than one keys. 
	 * - We are using comparator.compare() method to generate custom comparator on the basis of keys(fields) that we pass.
	 */
	private static void compareCollections() {
		
		List<Person> people = new ArrayList<Person>();
		people.add(new Person(30, "Piyush", "Dangre"));
		people.add(new Person(30, "Aprajita", "Murthy"));
		people.add(new Person(2, "Daichi", "Sawamura"));
		
		List<String> list1 = people.stream().sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
				.map(p -> p.getFirstName()).collect(Collectors.toList());
		System.out.println(list1); // [Daichi, Piyush, Aprajita]

		List<String> list2 = people.stream().sorted(Comparator.comparing(Person::getAge)).map(p -> p.getFirstName())
				.collect(Collectors.toList());
		System.out.println(list2); // [Daichi, Piyush, Aprajita]

		List<String> list3 = people.stream()
				.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getFirstName))
				.map(p -> p.getFirstName()).collect(Collectors.toList());
		System.out.println(list3); // [Daichi, Aprajita, Piyush]
		
	}
	
	

}


/**
 * Sample class created for demo usage
 */
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

/**
 * Sample Person class
 */
class Person {
	
	private int age;
	private String firstName;
	private String lastName;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Person(int age, String firstName, String lastName) {
		super();
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [age=");
		builder.append(age);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}
	
	
}