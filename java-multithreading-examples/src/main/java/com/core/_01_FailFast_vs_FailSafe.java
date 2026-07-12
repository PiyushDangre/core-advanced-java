package com.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class _01_FailFast_vs_FailSafe {

    public static void main(String[] args) {

        /**
         * Fail Fast Iterator
         */

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Apples");
        map.put(2, "Bananas");

        Iterator<Map.Entry<Integer, String>> failFastIterator =  map.entrySet().iterator();

        /**
         * If we try to modify the collection while iterating,
         * it will throw ConcurrentModification Exception
         *
          */

        while(failFastIterator.hasNext()){
            System.out.println(failFastIterator.next());
            //map.put(3, "Pineapples");
        }


//        try {
//            int i=9/0;
//            System.out.println(i);
//            return;
//        } catch (Exception e) {
//            System.out.println("exception caught");
//            return;
//        }


        try {
            int i = 9/0;
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("exception caught");
            return ;
        } finally {
            System.out.println("finally block executing");
        }
        System.out.println("end");
        return ;

    }


}
