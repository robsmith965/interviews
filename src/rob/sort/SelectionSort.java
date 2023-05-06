package rob.sort;

/**
 * Selection sort - walk the list, swap current value with 'selected' minValue in (current, end].
 * 
 * 
 * O(n^2) best case
 * O(1) space. no external DS required.
 * 
 * Selection sort has the advantage that you will have at most n-1 swaps.
 * when it's expensive to swap, consider insertion sort. (e.g. if it was more expensive to swap than to compare).
 * 
 * @author rrobsm
 *
 */
public class SelectionSort extends SortUtils {
    public static void selectionSort( int[] data ) {
        int minIndex;
        for( int i = 0; i < data.length; i++ ) {
            minIndex = findMinIndex(data, i, data.length-1);
            if( data[i] != data[minIndex] ) {  // this line should make the sort stable, i.e. insertion order preserved
                swap(data, i, minIndex);
            }
        }
    }
    
    static int findMinIndex( int[] data, int low, int high ) {
        int minIndex = low;
        for( int i = low; i <= high; i++ ) {
            if( data[i] < data[minIndex] ) {
                minIndex = i;
            }
        }
        
        return minIndex;
    }
    
    /**
     * Why do this recursive solution? Fewer lines of code?
     * 
     * That doesn't justify the extra stack space the recursion requires.
     */
    static void selectionSortRecursive( int[] data ) {
        selectionSortRecursive(data, 0);
    }
    
    static void selectionSortRecursive( int[] data, int start ) {
        if( start < data.length - 1 ) {
            swap( data, start, findMinIndex(data, start, data.length - 1) );
            selectionSortRecursive( data, start + 1 );
        }
    }
    
    public static void main(String[] args) {
        int[] data = {2,4,3,1,5};
        SortUtils.printArray(data);
        selectionSortRecursive(data);
        SortUtils.printArray(data); 
    }
}
