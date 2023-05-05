package rob.queue;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * My submission on LeetCode: https://leetcode.com/problems/lru-cache/discuss/46200/java-solution-with-doubly-linked-list-hash-map
 *
 * LRU cache. When cache is full, evict most stale entry.
 *
 * Cache locally on phones, etc. to avoid expensive network calls.
 *
 * The cache itself is a doubly linked list where items at the tail get evicted first.
 * So new items are inserted into the head of the list.
 *
 * Need a hash table mapping keys to Nodes for quick lookup.
 *
 * Can't use java's LinkedList because for efficient removal we must update pointers
 * directly on Nodes returned from the map.
 *
 * If thread-safety is required and list scans can be tolerated, use Java's
 * ConcurrentLinkedQueue and HashTable.
 */
public class LRUCache {
    private Map<Integer, Node> map;
    private Node head; // dummy "fence" head
    private Node tail; // dummy "fence" tail
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if( !map.containsKey(key) ) {
            return -1;
        }
        Node n = map.get(key);
        promoteToHead(n);
        return n.val;
    }

    public void put(int key, int value) {
        Node n;
        // update existing key; does not alter cache size
        if( map.containsKey(key) ) {
            n = map.get(key);
            n.val = value;   // map.get(n.key) will now return node with new val
            promoteToHead(n);

            return;
        }
        if( map.size() == capacity ) {
            Node last = tail.prev;
            map.remove(last.key);
            remove(last);
        }
        n = new Node(key, value);
        addFirst(n);
        map.put(key, n);
    }

    /**
     * Move given Node to head of queue.
     */
    private void promoteToHead(Node n) {
        if( head != n ) {
            remove(n);
            addFirst(n);
        }
    }

    /**
     * Remove given Node from queue.
     */
    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    /**
     * Insert given Node to head of queue.
     */
    private void addFirst(Node n) {
        // first insert looks like:
        //  -1 <-> -1
        //  -1 <-> n <-> -1
        Node temp = head.next;
        head.next = n;
        n.prev = head;
        n.next = temp;
        n.next.prev = n;
    }

    public void printCache() throws Exception {
        if( head.next == tail ) {
            throw new Exception("empty cache!");
        }
        Node n = head.next;
        System.out.print("[ ");
        while( n != tail ) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) throws Exception {
//        LRUCache cache = new LRUCache(3);
//        cache.put(1, 11);
//        cache.put(2, 22);
//        cache.put(3, 33);
//        cache.printCache();  // [33,22,11]
//        cache.put(4, 44);
//        cache.printCache();  // [44,33,22]

        LRUCache cache = new LRUCache(2);
//        cache.put(2, 6);
//        cache.put(1, 5);
//        cache.put(1, 2);
        cache.printCache();
    }
}


/**
 * Basic Idea:
 *
 *
 *
 public class LRUCache {

 private LinkedHahMap<Integer, Integer> map;
 private int capcity;

 public LRUCache(int capacity) {
 map = new LinkedHashMap<Integer, Integer>(capacity);
 this.capacity = capacity;
 }

 public int get(int key) {
 if( map.containsKey(key) ) {
 map.getList().setHead(key);
 return map.get(key);
 }

 return -1;
 }

 public void set(int key, int value) {
 if( map.getList().size() == capacity ) {
 map.getList().removeLast();
 }
 map.put(key, value);
 map.getList().setHead(key);
 }
 }
 *
 *
 *
 */
