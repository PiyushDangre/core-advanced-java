package com.stream;


import java.util.*;
import java.util.stream.Collectors;

public class _05_Stream_Practice_Problems_2026 {

    public static void main(String[] args) {
        findMax();
        findMaxWithoutSortingAndInbuiltMaxFunction();
        countStringsWithSpecificPrefix();
        findFirstNonRepeatedChatracterInString();
        sumOfNumbersInList();
        findDuplicates();
        groupByLength();
        concatenateStrings();
        findLongest();
        findAllPalindromicStrings();
        findLongestWordFromSentence();

    }

    private static void findLongestWordFromSentence() {
        String sentence = "Java Stream API is very powerful";
        Arrays.stream(sentence.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst()
                .ifPresent(s-> System.out.println("The longest one is "+s));
    //.collect(Collectors.toMap(k-> k, v-> v.length()))
    }

    private static void findAllPalindromicStrings() {
        List<String> words = Arrays.asList("radar", "level", "world", "java");
        System.out.println("Palindromic strings are following");
        words.stream().filter(s-> s.equals(new StringBuilder(s).reverse().toString()))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void findLongest() {
        List<String> words = Arrays.asList("Java", "Stream", "API", "Development");

        // With Sorting method

        words.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst()
                .ifPresent(s-> System.out.println("The longest word is "+s));

        // Without sorting method --> Using reduce

        words.stream()
                .collect(Collectors.reducing((a, b) -> a.length() > b.length() ? a : b))
                .ifPresent(s -> System.out.println("The longest word is again " + s));
    }

    /**
     * Reducing function is used to give one single value from stream of values
     * First param is the identity -> the initial value to be given while reduction
     * Second param is the function (BinaryOperator) to be used while reduction.
     */
    private static void findMaxWithoutSortingAndInbuiltMaxFunction() {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        Integer max = numbers.stream().collect(Collectors.reducing(0, (a,b) -> a > b ? a : b));
        System.out.println("Max number is "+max);
    }

    private static void concatenateStrings() {
        List<String> words = Arrays.asList("Stream", "API", "is", "powerful");
        String word = words.stream().collect(Collectors.joining(" "));
        System.out.println(word);
    }

    private static void groupByLength() {
        List<String> words = Arrays.asList("Java", "Stream", "API", "Code", "Fun", "Java");
        words.stream().collect(Collectors.groupingBy(s->s.length())).forEach((k,v)-> System.out.println(k+" = "+v));
    }

    private static void findDuplicates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 1);
        HashSet<Integer> set = new LinkedHashSet<>();
        List<Integer> duplicates = numbers.stream().filter(i -> !set.add(i)).toList();
        System.out.println("Duplicates are "+duplicates);
    }

    /**
     * FOr using functions like sum(), avg(), max() etc --> convert stream to IntStream using mapToInt()
     */
    private static void sumOfNumbersInList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum is "+numbers.stream().mapToInt(Integer::intValue).sum());

        // With reduce
        numbers.stream()
                .collect(Collectors.reducing((a,b) -> a + b))
                .ifPresent(s-> System.out.println("The sum is "+s));

        // Parallel stream
        Integer sum = numbers.parallelStream().mapToInt(i-> i.intValue()).sum();
        System.out.println("The sum using parallelstream is "+sum);
    }

    private static void findFirstNonRepeatedChatracterInString() {
        String input = "swiswsjk";
        Arrays.stream(input.split(""))
                .collect(Collectors.groupingBy((s->s), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .ifPresent(s-> System.out.println("The first non repeating character is "+s.getKey()));
    }

    private static void countStringsWithSpecificPrefix() {
        List<String> names = Arrays.asList("Alice", "Bob", "Annie", "Alex", "Charlie");
        long t = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println("The count of names starting with A is "+t);
    }

    /**
     * Find max from array of Integers
     */
    private static void findMax() {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numbers.stream().mapToInt(Integer::intValue).max().ifPresent(System.out::println);
    }
}
