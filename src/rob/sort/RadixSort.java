package rob.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Base-10 radix sort.
 * 
 * 10 buckets: 0,1,...,9.
 * 
 * at each pass, empty the buckets into the input array. 
 * 
 * Continue k times where k is the number of digits in the largest number.
 * 
 * Stable sort.
 * 
 * {10,2,7}
 *
 */
public class RadixSort {
    public static void decimalRadixSort( int[] array ) {
        final int RADIX = 10;
        List<Integer>[] buckets = new ArrayList[RADIX];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        
        int place = 1; // 1s place, 10s place, etc.
        int digit = -1;
        boolean maxLenFound = false;
        while( !maxLenFound ) {
            maxLenFound = true; // assume max len found; mark false, i.e. allow another pass, only when integer division > 0. 
            for( int i : array ) {
                digit = i / place;  // digit is int, so 2.5/10 = 2.
                buckets[ digit % RADIX ].add(i);
                if( digit > 0 ) {
                    maxLenFound = false;  // found a number with a digit in the "place" place. 
                }
            }
            
            if( maxLenFound == false ) {
                // dump buckets into input array
                int a = 0;
                for( List<Integer> bucket : buckets ) {
                    for( int i : bucket ) {
                        array[a++] = i;
                    }
                    bucket.clear();
                }
            }
            place *= RADIX;
        }
    }
    
    /*
     * first find k, where k = the number of digits in the MSD.
     * 
     * for example, in {100, 2, 7}, k = 3.
     * 
     * then make k passes of bucket sort, dumping all buckets into
     * the input array on each pass.
     */
    /**
     * might be buggy
     */
    static void decimalRadixSortBetter( int[] array ) {
        final int RADIX = 10;
        List<Integer>[] buckets = new ArrayList[RADIX];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        
        int place = 1; // 1s place, 10s place, etc.
        int quotient = Integer.MAX_VALUE;
        int k = 0;  // # of digits in MSD
        while( quotient > 0 ) {
            for( int i : array ) {
                quotient = i / place;
            }
            place *= RADIX;
            k++;
        }
        k--;  // since did k++ one more time after finding quotient > 0.
        int digit;
        for( int i = 0; i < k; i++ ) {
            for( int j : array ) {
                digit = j / place;  // digit is int, so 2.5/10 = 2.
                buckets[ digit % RADIX ].add(j);
            }
            
            // dump buckets into input array
            int idx = 0;
            for( List<Integer> bucket : buckets ) {
                for( int n : bucket ) {
                    array[idx++] = n;
                }
                bucket.clear();
            }
        }
    }
    
    static void decimalRadixSortBest( int[] array ) {
        final int RADIX = 10;
        List<Integer>[] buckets = new ArrayList[RADIX];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        
        int max = findMax(array);
        int place = 1; // 1s place, 10s place, etc.
        int k = 0;  // # of digits in MSD
        while( max / place > 0 ) {
            place *= RADIX;
            k++;
        }
        place = 1;
        int quotient;
        for( int i = 0; i < k; i++ ) {
            for( int j : array ) {
                quotient = j / place;
                buckets[ quotient % RADIX ].add(j);
            }
            
            // dump buckets into input array
            int idx = 0;
            for( List<Integer> bucket : buckets ) {
                for( int n : bucket ) {
                    array[idx++] = n;
                }
                bucket.clear();
            }
            
            place *= RADIX;
        }
    }
    
    static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for( int i : arr ) {
            if( i > max ) {
                max = i;
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] ints = {100,9};
//        decimalRadixSort(ints);
//        for( int i : ints ) {
//            System.out.print(i + " ");
//        }
        
        decimalRadixSortBest(ints);
        for( int i : ints ) {
            System.out.print(i + " ");
        }
    }
}
