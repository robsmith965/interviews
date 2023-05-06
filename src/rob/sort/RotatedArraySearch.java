package rob.sort;

public class RotatedArraySearch {
    public static int search(int[] A, int target) {
        if( A == null || A.length == 0 ) {
            return -1;
        }
        return search(A, 0, A.length-1, target);   
    }
    
    public static int search(int[] A, int start, int end, int target) {
        if( start > end ) {
            return -1;
        }
        int mid = (start+end)/2;
        if( A[mid] == target ) {
            return mid;
        }
        // need below 2 if blocks to hand [3,1] , an unsorted array where neither (or both?) sides are sorted.
        if( A[start] == target ) {
            return start;
        }
        if( A[end] == target ) {
            return end;
        }
        // find out which side is sorted. if target lies in there, search it. otherwise search other side.
        if( A[start] < A[mid] ) {//L side is sorted
            if( target < A[mid] && target >= A[start] ) {
                return search(A, start, mid-1, target);
            } else {
                return search(A, mid+1, end, target);
            }
        } else { //R side is sorted
            if( target > A[mid] && target <= A[end] ) {
                return search(A, mid+1, end, target);
            } else {
                return search(A, start, mid-1, target);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] ints = {3,1};
        System.out.println(search(ints, 1));
    }
}
