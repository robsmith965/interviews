package rob.misc;

/**
 * integer division: ignore the remainder
 *
 * so you can find how many significant digits are in a number
 * by dividing by 10, 100, 1000 ... until you get 0
 * 0 means this number goes into 1000 0 times, with an unknown remainder.
 *
 * modulus: the remainder.
 *
 *
 *
 * 1234 / 10 = 123          chop off rightmost digit
 * 1234 % 1000 = 234        chop off leftmost digit
 * 2234 % 1000 = 234
 * 234 / 100 = 2            get the leftmost digit
 * 24792483 % 10 = 3        get the rightmost digit
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1312211));
    }

    static boolean isPalindrome(int num) {
        /*
         * obvious: take string representation, and iterate from beg/end,
         * make sure they match.
         *
         * that requires extra space.
         *
         * how about doing it in-place?
         *
         * extract L and R, make sure they match. L++, R--
         *
         *
         */

        int div = 1;
        while( num / div > 0 ) {
            div *= 10;
        }
        div /= 10;
        while( div > 0 ) {
            int l = num / div;
            int r = num % 10;
            if( l != r ) {
                return false;
            }

            // chop off leftmost digit
            num = num % div;

            // chop off rightmost digit
            num = num / 10;

            div /= 100;
        }

        return true;
    }
}

/**
 *  originally considered finding "numDigits", then looping numDigits/2 times.
 *  Then realized that's not necessary. simply calculate div, then chop off both ends.
 */
