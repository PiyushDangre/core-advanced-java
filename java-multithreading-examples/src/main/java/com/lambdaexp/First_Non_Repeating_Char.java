package com.lambdaexp;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Write a program to find the first non repeating character in a string
 */
public class First_Non_Repeating_Char {

    public static void main(String[] args) {

        String s  = "Huawweaiissheukeioukoam";

        // Using normal Java way
        printFirstNonRepeatableCharacter(s);

        // Using Stream API and Lambda expressions
        printFirstNonRepeatableCharacterUsingLambda(s);

        // Prints -->
        // The first non repeatable character is m
        // According to Stream API = m
    }



    private static void printFirstNonRepeatableCharacter(String s) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        String[] list = s.toLowerCase().split("");

        for(String ch : list){

            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            }else {
                map.put(ch, 1);
            }
        }


        if(map.containsValue(1)){
            for(Map.Entry<String, Integer> e : map.entrySet()){
                if(e.getValue() == 1){
                    System.out.println("The first non repeatable character is "+e.getKey());
                    break;
                }
            }
        }else{
            System.out.println("There are no repeatable characters");
        }
    }

    private static void printFirstNonRepeatableCharacterUsingLambda(String s) {

        String firstNonRepeatableChar = Arrays.stream(s.split(""))
                .map(m -> m.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), () -> {
            return new LinkedHashMap<String, Long>();
        }, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .map(e -> e.getKey())
                .orElse(null);

        System.out.println("According to Stream API = "+firstNonRepeatableChar);



    }

}

