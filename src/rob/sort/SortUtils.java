package rob.sort;

public class SortUtils {
    public static void swap( int arr[], int left, int right ) {
        if( left != right ) {
            int temp = arr[ left ];
            arr[ left ] = arr[ right ];
            arr[ right ] = temp;
        }
    }
    
    public static void printArray(int[] array) {
        for( int i : array ) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    //generic
    public static <T> void printArray(T[] array) {
        for( Object obj : array ) {
            System.out.print(obj.toString() + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        String[] strings = {"noby", "yoko"};
        printArray(strings);
    }
}
