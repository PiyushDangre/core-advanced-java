import java.util.*;
import java.util.stream.Stream;

/**
 * --- This program was asked in an interview
 *
 *   Change the order of all the vowels in the given string into
 *   reverse order.
 *
 */
public class _01_String_Interview_Problem {

    public static void main(String[] args) {

        List<String> vowels = List.of("a", "e", "i", "o", "u");

        String str = "onomatopoeia";

        String[] strArray = str.split("");

        LinkedList<String> queue = new LinkedList<>();

        for (String s : strArray) {
            if (vowels.contains(s)) {
                queue.add(s);
            }
        }

      for(int i = strArray.length-1; i >= 0 ; i--){

          if(vowels.contains(strArray[i])){
              strArray[i] = queue.removeFirst();
          }
      }


        printArray(strArray); // Prints "animetopoaoo"


}

static boolean printArray(String[] strArray){
    Stream.of(strArray).forEach(System.out::print);
    return false;
}

}
