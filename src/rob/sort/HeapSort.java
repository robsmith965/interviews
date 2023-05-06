package rob.sort;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSort {
    /*
     * O(N) where N = total # of nodes because
     * each node is compared, that's a constant-time operation, N times.
     */
    public static ListNode mergeKListsSLOW(List<ListNode> lists) {
        if( lists.size() == 0 ) {
            return null;
        }
        ListNode result = lists.get(0);
        for( int i = 1; i < lists.size(); i++ ) {
            result = merge(result, lists.get(i));
        }
        return result;
    }
    
    public static ListNode merge(ListNode l1, ListNode l2) {
        if( l1 == null && l2 == null ) {
            return null;
        }
        if( l1 == null ) {
            return l2;
        }
        if( l2 == null ) {
            return l1;
        }
        
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        
        while( l1 != null && l2 != null ) {
            if( l1.val < l2.val ) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2; // adding l2 if l2 <= l1
                l2 = l2.next;
            }
            tail = tail.next;
        }
        
        // add whatever remains in l1 or l2.
        while( l1 != null ) {
            tail.next = l1;
            tail = tail.next;
            l1 = l1.next;
        }
        while( l2 != null ) {
            tail.next = l2;
            tail = tail.next;
            l2 = l2.next;
        }
        
        return dummyHead.next;
    }
    
    public static ListNode mergeKLists(List<ListNode> lists) {
        // put all nodes in a PriorityQueue  -- orders items according to their natural ordering.
        // insertions are O(log(N))
        // removals are O(log(N))
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for( ListNode head : lists ) {
            while( head != null ) {
                queue.add( head.val );
                head = head.next;
            }
        }
        
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        
        while( !queue.isEmpty() ) {
            tail.next = new ListNode( queue.poll() );   // poll retrieves and removes head.
            tail = tail.next;
        }
        
        return dummyHead.next;
    }
    
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        
        ListNode list2 = new ListNode(3);
        list2.next = new ListNode(4);
        
        List<ListNode> lists = new LinkedList<ListNode>();
        lists.add(list1);
        lists.add(list2);
        ListNode node = mergeKLists(lists);
        while( node != null ) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
