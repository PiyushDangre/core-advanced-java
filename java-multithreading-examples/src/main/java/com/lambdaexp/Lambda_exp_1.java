package com.lambdaexp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 *  Write a Java program that takes a list of integers and performs the following
 *  set of operations using lambda expressions -
 *
 *   -- Filter out even numbers
 *   -- Square each remaining number
 *   -- Sort the squared numbers in descending order
 *   -- print the final result
 */
public class Lambda_exp_1 {

    public static void main(String[] args) {

        Integer[] input = {3,7,2,4,9,5};

       Arrays.asList(input).stream()
                .filter(p -> p % 2 == 1)
                .map(p -> p*p)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);



    }




}
