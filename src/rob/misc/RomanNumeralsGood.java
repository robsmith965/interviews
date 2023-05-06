package rob.misc;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeralsGood {
    static Map<Integer,String> intRomanMap = new HashMap<Integer, String>();
    
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
            n = n % div;  // chop off leftmost digit
            div /= 10;
        }
        digit = n % 10;  // get rightmost digit
        sb.append(getRoman(digit, 1));
        
        return sb.toString();
    }
    
    static String getRoman(int n, int place) {
        switch(n) {
        case 0 : return ""; //toRoman() will have already handled this case in prev iteration
        case 1 : return intRomanMap.get(1*place);
        case 4 : return intRomanMap.get(4*place);
        case 5 : return intRomanMap.get(5*place);
        case 9 : return intRomanMap.get(9*place);
        }
        StringBuilder sb = new StringBuilder();
        if( n > 1 && n < 4 ) {
            sb.append(intRomanMap.get(1*place));
            return( sb.append(getRoman(n-1, place)) ).toString();
        }
        
        // n is 6, 7, or 8.
        sb.append(intRomanMap.get(5*place));
        return( sb.append(getRoman(n-5, place)) ).toString();
    }
    
    public static void main(String[] args) {
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
        
        System.out.println(toRoman(904));
    }
}    
