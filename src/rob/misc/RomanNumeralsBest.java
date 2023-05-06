package rob.misc;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * http://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
 */
public class RomanNumeralsBest {
    static NavigableMap<Integer,String> intRomanMap = new TreeMap<Integer, String>();
    
    static {
        intRomanMap.put(1000, "M");
        intRomanMap.put(900, "CM");
        intRomanMap.put(500, "D");
        intRomanMap.put(400, "CD");
        intRomanMap.put(100, "C");
        intRomanMap.put(90, "XC");
        intRomanMap.put(50, "L");
        intRomanMap.put(40, "XL");
        intRomanMap.put(10, "X");
        intRomanMap.put(9, "IX");
        intRomanMap.put(5, "V");
        intRomanMap.put(4, "IV");
        intRomanMap.put(1, "I");
    }

    public final static String toRoman(int number) {
        int lowKey = intRomanMap.floorKey(number);
        if ( number == lowKey ) {
            return intRomanMap.get(number);
        }
        return intRomanMap.get(lowKey) + toRoman(number-lowKey);
    }
    
    public static void main(String[] args) {
        System.out.println(toRoman(949));
    }
}    
