package rob.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of chars, determine the
 * max length subarry containing no dups
 *
 * return indices of max len subarray
 *
 * maintain start, end - final indices
 *
 * i,j: temp start, end
 *
 * maxLen
 *
 * len: temp len
 *
 * need len/maxLen to know when to update i/start
 *
 *
 * O(n) time
 * O(1) space
 *
 */
public class MaxSubArrayNoDups {

    private static class Pair {
        int start;
        int end;
        public Pair(int s, int e) {
            start = s;
            end = e;
        }
        public int getStart() {
            return start;
        }
        public int getEnd() {
            return end;
        }
        public String toString() {
            return "s: " + start + " e: " + end;
        }
    }

    Pair maxSubArrayNoDups(char[] arr) {
        if( arr == null ) return null;
        if( arr.length == 1 ) return new Pair(0,0);
        int start, end, i, j, len, maxLen;
        start = end = i = j = len = maxLen = 0;
        Map<Character, Integer> seen = new HashMap<Character, Integer>();

        while( j <= arr.length - 1 ) {
            if( !seen.containsKey(arr[j]) ) {
                seen.put(arr[j], j);
            }
            else {
                i = seen.get(arr[j]) + 1;  // update current start to last seen index + 1, which is <= j.
            }
            len = j - i + 1;
            if( len > maxLen ) {
                maxLen = len;
                start = i;
                end = j;
            }
            j++;
        }

        return new Pair(start,end);
    }

    public static void main(String[] args) {
        char[] bar = {'b','e', 'x', 'b', 'a', 'x', 'y', 'w'}; // max sub-array with no duplicates is [b, a, x, y, w]
        MaxSubArrayNoDups m = new MaxSubArrayNoDups();
        Pair p = m.maxSubArrayNoDups(bar);
        System.out.println(p.toString());
        assert(p.getStart() == 3);
        assert(p.getEnd() == 7);
    }
}
