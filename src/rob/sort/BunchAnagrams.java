package rob.sort;

import java.util.*;

public class BunchAnagrams extends SortUtils {
    public static String[] bunchAnagrams(String[] strings) {
        Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
        List<String> output = new ArrayList<String>();
        for( String s : strings ) {
            String key = alphabetize(s);
            if( !map.containsKey(key) ) {
                map.put(key,  new LinkedList<String>() );
            }
            // get a 'copy' of the list in the map
            // add to this 'copy'
            // java is pass-by-value, so this 'copy' is
            // actually the real thing.    
            LinkedList<String> anagrams = map.get(key);
            anagrams.add(s);
        }
        
        for( String s : strings ) {
            String key = alphabetize(s);
            if( map.containsKey(key) ) {
                for( String string : map.get(key) ) {
                    output.add(string);
                }
                map.remove( key ); // so that the list is not printed multiple times
            }
        }
        
        return output.toArray(new String[output.size()]);  // or, just loop thru list, and populate an array.
    }
    
    // could have used Arrays.sort, which uses dual-pivot quicksort.
    private static String alphabetize(String s) {
        int[] asciiVals = new int[s.length()];
        for( int i = 0; i < s.length(); i++ ) {
            asciiVals[i] = (int) s.charAt(i);
        }
        /*
         * use insertion sort here because shifting of elements over by 1 is not bad; +
         * just shifting over ints. If we were shifting over complex objects, might have
         * been better to use selection sort - where at most n-1 swaps occur. 
         */
        InsertionSort.insertionSort(asciiVals);
        StringBuilder alphabetized = new StringBuilder();
        for( int asciiVal : asciiVals ) {
            alphabetized.append((char) asciiVal);
        }
        
        return alphabetized.toString();
    }
    
    public static void main(String[] args) {
        String[] strings = {"dog", "cat", "god"};
        String[] anagramsBunched = (bunchAnagrams(strings));
        for( String s : anagramsBunched ) {
            System.out.println(s);
        }
        
//        String s = "dog";
//        char[] chars = s.toCharArray();
//        Arrays.sort(chars);
//        for( char c : chars ) {
//            System.out.print(c);
//        }
    }
}
