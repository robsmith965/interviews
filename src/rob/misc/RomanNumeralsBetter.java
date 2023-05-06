package rob.misc;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanNumeralsBetter {
    /*
     * tree maps have O(log n) put/get.
     * 
     * in a tree map you can do a floorKey(K key)
     * which will return the greatest key <= input key.
     */
    static NavigableMap<Integer,String> intRomanMap = new TreeMap<Integer, String>();
    static {
        intRomanMap.put(1,"I");
        intRomanMap.put(4,"IV");
        intRomanMap.put(5,"V");
        intRomanMap.put(9,"IX");
        intRomanMap.put(10,"X");
        intRomanMap.put(40,"XL");
        intRomanMap.put(50,"L");
        intRomanMap.put(90,"XC");
        intRomanMap.put(100,"C");
        intRomanMap.put(400,"CD");
        intRomanMap.put(500,"D");
        intRomanMap.put(900,"CM");
        intRomanMap.put(1000,"M");
    }
    static String toRoman(int n) {
        if( n > 1000 || n < 1 ) {
            System.out.println("supported numbers: 1 - 1000.");
            System.exit(1);
        }
        int div = 10;
        while( n / div > 0 ) {
            div *= 10;
        }
        div /= 10;
        
        int digit = -1;
        /*
         * to be pedantic, petty, and pettifogging, using StringBuilder here.
         * should just use String since '+' concatenation
         * will not be a performance issue here.
         * 
         * should keep readability and only optimize if 
         * performance actually becomes an issue.
         * 
         * leaving SB usage here so i remember to bring it up in interviews.
         */
        StringBuilder sb = new StringBuilder();
        while( div >= 10 ) {
            digit = n / div;
            sb.append(getRoman(digit, div));
            n %= div;  // chop off leftmost digit
            div /= 10;
        }
        digit = n % 10;  // get rightmost digit
        sb.append(getRoman(digit, 1));
        
        return sb.toString();
    }
    
    static String getRoman(int digit, int place) {
        if( digit == 0 ) {
            return "";
        }
        int lowestKey = intRomanMap.floorKey(digit*place);
        if( digit*place == lowestKey ) {
            return intRomanMap.get(digit*place);
        }
        return intRomanMap.get(digit*place) + getRoman(digit - lowestKey, place);
    }
    
    public static void main(String[] args) {
        System.out.println(toRoman(949));
    }
}    
