package rob.misc;

public class SwapNums {
    public static void main(String[] args) {
        int a = 1252;
        int b = 93;
        
        System.out.println(a);
        System.out.println(b);
        
        a = ( a + b ) - (b = a);
        
        
        
        System.out.println(a);
        System.out.println(b);
        
        /* OR:
         * 
         *  X := X XOR Y
            Y := X XOR Y
            X := X XOR Y
         */
        
        System.out.println(2^1);
    }
}
