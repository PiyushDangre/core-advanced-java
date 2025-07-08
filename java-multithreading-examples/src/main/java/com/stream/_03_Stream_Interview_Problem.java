package com.stream;

import java.util.Arrays;
import java.util.HashSet;

/**
 * -- This program was asked in an interview
 * -- Given a names[] string array , filter out the names starting with "aa".
 * -- For these filtered out names - figure out the number of unique character in each word.
 * <p>
 * -- Output should be :
 * <p>
 * aaryanna - 4
 * aayanna - 3
 */
public class _03_Stream_Interview_Problem {

    public static void main(String[] args) {

        String[] names = {"aaryanna", "aayanna", "airianna", "alassandra", "allanna", "allannah", "allessandra", "allianna",
                "allyanna", "anastaisa", "anastashia", "anastasia", "annabella", "annabelle", "annebelle"};

        /**
         * ---        Normal Java way
         * - Where there is talk of removing duplicates - there is set.
         */

        for (String s : names) {
            if (s.substring(0, 2).equals("aa")) {
                HashSet<String> set = new HashSet<>();
                for (String letter : s.split("")) {
                    set.add(letter);
                }
                System.out.println(s + " - " + set.size());
            }

        }

        /**
         * ---        Stream API way
         *
         *  - Used distinct() and count() to figure out number of distinct characters
         *    within a string.
         *
         */

        Arrays.stream(names).filter((s1) -> s1.startsWith("aa")).forEach((s1) -> {
            Long count = Arrays.stream(s1.split("")).distinct().count();
            System.out.println("String : " + s1 + " - " + count);

        });

    }
}