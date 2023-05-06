package rob.sort;

public class QuickSort extends SortUtils {

    /**
     * Quicksort is a Series of Swaps.
     *
     * "pivot-and-split" the data, recursively, starting with the left half...
     *
     * until the "left halves" can't be split (len=1)
     *
     * recurse up, now process the right half...
     *
     * continue recursing up.
     *
     * best case:
     * the "number of times you can divide n by 2 until you get 1" is log(n).
     * every time you do a pivot-and-split, you "look at" each element in each subarray.
     * That "looking" is considered a constant-time operation.
     *
     * so best case run time is n*log(n).
     *
     * worst case:
     * bad pivot? n^2. selection and insertion sort have this terrible time complexity.
     *
     * optimization:
     * in n < threshold, return selectionSort(data)
     *
     * space complexity: O(log(n)) due to recursion (?).
     */
    public static int[] quicksortSimple( int[] data ) {
        System.out.println("beginning");
        if( data.length < 2 ) {
            return data;
        }

        int pivotIndex = data.length / 2;
        int pivotValue = data[ pivotIndex ];

        int leftCount = 0;

        // Count how many are less than the pivot

        for( int i = 0; i < data.length; ++i ) {
            if( data[i] < pivotValue ) {
                ++leftCount;
            }
        }

        // Allocate the arrays and create the subsets
        int[] left = new int[ leftCount ];
        int[] right = new int[ data.length - leftCount - 1 ];

        int l = 0;
        int r = 0;

        for( int i = 0; i < data.length; ++i ) {
            if( i == pivotIndex ) {
                continue;
            }

            int val = data[i];

            if( val < pivotValue ) {
                left[ l++ ] = val;
            } else {
                right[ r++ ] = val;
            }
        }

        // Sort the subsets

        left = quicksortSimple( left );
        System.out.println("about to print right");
        right = quicksortSimple( right );

        // Combine the sorted arrays and the pivot back into the original array

        // args: (src, srcPos, tgt, tgtPos, len)
        System.arraycopy( left, 0, data, 0, left.length );
        data[ left.length ] = pivotValue;
        System.arraycopy( right, 0, data, left.length + 1, right.length );

        return data;
    }

    static void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if( left < index - 1 ) {
            quickSort(arr, left, index - 1);
        }
        if( index < right ) {
            quickSort(arr, index, right);
        }
    }

    /**
     * sorts [left, pivot)
     * returns index of middle of [left, pivot)
     */
    static int partition(int arr[], int left, int right) {
        int pivot = arr[ (left + right) / 2 ];
        while( left <= right ) {
            System.out.println("here");
            while( arr[left] < pivot ) {
                left++;
            }
            while( arr[right] > pivot ) {
                right--;
            }

            // Swap elements, move L and R indices
            if( left <= right ) {
                swap( arr, left, right );
                left++;
                right--;
            }
        }

        return left;
    }

    // not stable. relative order of dups not maintained.
    static void qsort(int arr[], int left, int right) {
        int index = qpartition(arr, left, right);
        if (left < index - 1)
            qsort(arr, left, index - 1);
        if (index < right)
            qsort(arr, index, right);
    }

    static int qpartition(int arr[], int lo, int hi) {
        int l = lo, r = hi;
        int pivot = arr[(lo + hi) / 2];

        // why need l <=r here?
        while (l <= r) {
            // search for 1st element on left to be swapped
            while (arr[l] < pivot) l++; // so if you find pivot, do not increment l. found an element to be swapped.
            // search for 1st element on right to be swapped
            while (arr[r] > pivot) r--;
            // why need l <= r here?
            if (l <= r) {
                System.out.println("swap " + arr[l] + arr[r]);
                swap(arr, l, r);
                l++;
                r--;
            }
        }
        System.out.print(":: ");
        printArray(arr);

        return l;
    }

    /**
     * easist to understand:
     *
     * no base case. why does it stop?
     */
    static void qsort2(int[] arr, int lo, int hi) {
        int pivot = arr[(lo+hi)/2];
        int left = lo;
        int right = hi;

        while( left <= right ) {
            while(arr[left] < pivot) left++;
            while(arr[right] > pivot) right--;
            if( left <= right ) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        if( lo < right )
            qsort2(arr, lo, right);
        if( left < hi )
            qsort2(arr, left, hi);
    }

    public static void main(String[] args) {
        int[] ints = {1, 12, 5, 26, 7, 1, 3, 7, 2}; //{5,1,4,2,3};
        printArray(ints);
        qsort2(ints, 0, ints.length-1);
        printArray(ints);
    }
}
