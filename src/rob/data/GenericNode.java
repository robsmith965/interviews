package rob.data;

/**
 * singly-linked list
 */
public class GenericNode {
    public GenericNode next = null;
    public Object data;
    public int localMin;  // not needed for minimal implementation
    
    public GenericNode(Object d) {
        this.data = d;
    }
    
    public GenericNode(Object d, int min) {
        data = d;
        localMin = min;
    }
    
    void appendToTail(Object x) {
        GenericNode newTail = new GenericNode(x);
        GenericNode oldTail = this;
        
        while(oldTail.next != null) {
            oldTail = oldTail.next;
        }
        
        oldTail.next = newTail;
    }
    
    void deleteBad(GenericNode n) {
        n = n.next;
//        n.next = n.next.next; //causes NPE
    }
    
    /*
     * Works because memory locations of head are updated
     * when prev.next = n.next;
     */
    void deleteBetter(GenericNode head, Object x) {
        GenericNode prev = null;
        GenericNode n = head;
        while( n.data != x) {
            prev = n;
            n = n.next;
        }
        prev.next = n.next;
    }
    
    GenericNode delete(GenericNode head, Object x) {
        GenericNode n = head;   // save head into n. any updates to n's pointers update head's pointers.
                                // because java is pass by value
        if( n.data == x ) {
            return head.next;   // new head
        }
        
        while( n.next != null ) {
            if( n.next.data == x ) {
                n.next = n.next.next;
                return head;  // same head, different middle men
            }
            n = n.next;
        }
        
        return head;  //return same list
    }

    static void printAll(GenericNode head) {
        while(true) {
            if( head == null ) {
                break;
            }
            System.out.println(head.data);
            head = head.next;
        }    
    }
    
    public static void main(String[] args) {
        GenericNode n = new GenericNode(5);
        n.appendToTail(77);
        n.appendToTail(88);
        
        GenericNode runner = n;
        printAll(runner);
        
        n.delete(n, 77);
        
        System.out.println("deleted 77.");
        printAll(runner);
    }
}
