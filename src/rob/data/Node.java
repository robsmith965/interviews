package rob.data;

/**
 * singly-linked list.
 * holds integers.
 * not generic.
 */
public class Node {
    public Node next = null;
    public int data;
    public int localMin;  // not needed for minimal implementation
    
    public Node(int d) {
        this.data = d;
    }
    
    public Node(int d, int min) {
        data = d;
        localMin = min;
    }
    
    /*
     * append data (ints), not Nodes. Nodes 
     * are internal details.
     */
    public void appendToTail(int x) {
        Node newTail = new Node(x);
        Node oldTail = this;
        
        while(oldTail.next != null) {
            oldTail = oldTail.next;
        }
        
        oldTail.next = newTail;
    }
    
    void deleteBad(Node n) {
        n = n.next;
//        n.next = n.next.next; //causes NPE
    }
    
    /*
     * Works because memory locations of head are updated
     * when prev.next = n.next;
     * 
     * ASSUME NODES CONTAIN INTS
     */
    void deleteBetter(Node head, int x) {
        Node prev = null;
        Node n = head;
        while( n.data != x) {   // need cast because of generics
            prev = n;
            n = n.next;
        }
        prev.next = n.next;
    }
    
    // assume nodes contain ints
    Node delete(Node head, int x) {
        Node n = head;   // save head into n. any updates to n's pointers update head's pointers.
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

    public static void printAll(Node head) {
        
        while(true) {
            if( head == null ) {
                break;
            }
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Node n = new Node(5);
        n.appendToTail(77);
        n.appendToTail(88);
        
        Node runner = n;
        printAll(runner);
        
        n.delete(n, 77);
        
        System.out.println("deleted 77.");
        printAll(runner);
    }
}
