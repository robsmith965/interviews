package rob.sort;

public class NullsInterspersed {
    public static int search( String[] array, int left, int right, String s ) throws Exception {
        if( array.length < 1 || s == null || s == "" ) {  // TODO figure out diff btwn null and "" other.strings
            return -1;
        }        
        if( left >= right ) {
            throw new Exception("left index must be < right!");
        }
                
        int mid = (left+right)/2;
        if( array[mid].equals("") ) {
            // walk right until non-empty string found
            for( int i = mid; i <= right; i++ ) {
                if( !array[i].equals("") ) {
                    mid = i;
                    break;
                }
            }
            
            if( array[mid].equals("") ) {
                // walk left
                for( int i = mid; i <= left; i-- ) {
                    if( !array[i].equals("") ) {
                        mid = i;
                        break;
                    }
                }
            }
        }
        
        if( array[mid].equals(s) ) {
            return mid;
        }
        if( array[mid].compareTo(s) < 0 ) {
            return search(array, mid+1, right, s);
        } else if( array[mid].compareTo(s) > 0 ) {
            return search(array, left, mid-1, s);
        }
        
        return -1;
    }
    
    public static void main(String[] args) throws Exception {
        String[] strings = {"", "", "", "", "dog", "", "rat", "", "raze", ""};
        int idx = search( strings, 0, strings.length-1, "rat" );
        System.out.println(idx);
    }
}
