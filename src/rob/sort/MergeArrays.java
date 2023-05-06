package rob.sort;

import java.util.Arrays;


public class MergeArrays extends SortUtils {
    public static int[] merge( int[] a, int[] b ) {
        int[] merged = new int[a.length];
        int[] aTrimmed = new int[a.length - b.length];
        System.arraycopy(a, 0, aTrimmed, 0, a.length-b.length);
        System.arraycopy(a, 0, merged, 0, aTrimmed.length);
        for( int i = 0; i < b.length; i++ ) {
            if( b[i] < merged[i] ) {
                System.arraycopy(merged, i, merged, i+1, aTrimmed.length-i);
                merged[i] = b[i];
            } else {
                System.arraycopy(merged, i+1, merged, i+2, aTrimmed.length-i);
                merged[i+1] = b[i];
            }
        }
        
        return merged;
    }
    
    // assume 'a' has exactly enough buffer to hold all elements of b
    public static void mergeBetter( int[] a, int[] b ) {
        int lastAIdx = (a.length - b.length) - 1;
        int lastBIdx = b.length - 1;
        int lastMergedIdx = a.length - 1;
        
        while( lastBIdx >= 0 ) {
            if( a[ lastAIdx ] > b[ lastBIdx ] ) {
                a[ lastMergedIdx ] = a[ lastAIdx ];
                lastAIdx--;
            } else {
                a[ lastMergedIdx ] = b[ lastBIdx ];
                lastBIdx--;
            }
            lastMergedIdx--;
        }
    }
    
    public static void main(String[] args) {
        int[] a = new int[5];
        Arrays.fill(a, -1);
        a[0] = 3;
        a[1] = 10;
        a[2] = 11;
        
        int[] b = {4,5};
        
        printArray(a);
        printArray(b);
        
        mergeBetter(a,b);
        
        printArray(a);
        printArray(b);
    }
}
