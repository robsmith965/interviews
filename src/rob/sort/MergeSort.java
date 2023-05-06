package rob.sort;

/**
 * Merge sort is good when your data doesn't fit into memory. break the data into small files.
 * each of the small files is read into memory, and sorted.
 * 
 * then perform a merge operation using the sorted files as input, and the sorted data is
 * written directly to the final output file.
 * 
 * O(n log(n)) time - guaranteed
 * O(n) space - additional memory required
 * 
 * Stable, but not in-place.
 */
public class MergeSort extends SortUtils {

    static int[] mergeSortSimple( int[] data ) {
        System.out.println("splitting...");
        if( data.length < 2 ) {
            return data;
        }
        
        // optimization: insertion sort has lower overhead than mergesort
//        if( data.length < 10 ) {
//            InsertionSort.insertionSort(data);
//            return data;
//        }
        
        // Split the array into two subarrays of approx equal size.
        
        int mid = data.length / 2;
        int[] left = new int[mid];
        int[] right = new int[ data.length - mid ];
        
        /*
         * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         */
        System.arraycopy( data, 0, left, 0 , left.length );
        System.arraycopy( data, mid, right, 0 , right.length );
        
        // Sort each subarray, then merge the result.
        
        mergeSortSimple( left );
        mergeSortSimple( right );
        
        return merge( data, left, right );
    }
    
    // Merge two smaller arrays into a larger array.
    private static int[] merge( int[] dest, int[] left, int[] right ) {
        System.out.println("in merge helper");
//        System.out.println("left.length:" + left.length);
//        System.out.println("right.length:" + right.length);
        int dind = 0;
        int lind = 0;
        int rind = 0;
        
        // Merge arrays while there are elements in both
        while( lind < left.length && rind < right.length ) {
            if( left[lind] <= right[rind] ) {
                dest[ dind++ ] = left[lind++];
            } else {
                dest[ dind++ ] = right[rind++];
            }
        }
        
        // Copy rest of whichever array remains
        while( lind < left.length ) {
            dest[dind++] = left[lind++];
        }
        
        while( rind < right.length ) {
            dest[dind++] = right[rind++];
        }
        
        return dest;
    }
    
    public static void main(String[] args) {
        int[] data = {2,4,3,1,5};
        printArray(data);
        mergeSortSimple(data);
        printArray(data); 
    }
}
