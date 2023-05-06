package rob.sort;

public class InsertionSort {
    
    /**
     * Insertion sort: walk the list, insert the low value in the beginning, and shift everything else over.
     * 
     * Best case running time: O(n). So insertion sort is an efficient way to add new elements to a presorted list.
     * 
     * avg & worst cases are O(n^2) though.
     * 
     * O(1) space.
     */
    public static void insertionSort( int[] data ) {
        for( int which = 1; which < data.length; which++ ) {
            int val = data[which];
            
            for( int i = 0; i < which; i++ ) {
                if( data[i] > val ) {
                    // Insert smaller value starting at i; move rest over.
                    System.arraycopy( data, i, data, i+1, which - i );  // src, srcPos, dest, destPos, len
                    data[i] = val;
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] data = {2,4,3,1,5};
        SortUtils.printArray(data);
        insertionSort(data);
        SortUtils.printArray(data); 
    }
}
