package rob.queue;

import rob.data.Animal;
import rob.data.AnimalType;
import rob.data.GenericNode;

import java.util.LinkedList;
import java.util.List;

public class Queue <T> {

        public GenericNode first;
        GenericNode last;
        public int size;
        int capacity;
        protected static final int DEFAULT_CAPACITY = 5;
        
        public Queue(int capacity) {
                this.first = null;
                this.last = null;
                this.size = 0;
                this.capacity = capacity;
        }
        
        public Queue() {
                this.first = null;
                this.last = null;
                this.size = 0;
                this.capacity = DEFAULT_CAPACITY;
        }
        
        public void enqueue(Object x) throws Exception {
                if( size == capacity ) {
                        throw new Exception("queue is full!");
                }
                GenericNode n = new GenericNode(x);
                if( this.isEmpty() ) {
                        first = n;
                        last = first;
                }
                else {
                        last.next = n;
                        last = n;
                }
                size++;
        }
        
        public Object dequeue() throws Exception {
                if( this.isEmpty() ) {
//                        throw new Exception("empty queue!");
                        return null;
                }
                Object item = first.data;
                first = first.next;
                size--;
                return item;
        }
        
        public boolean isEmpty() {
                return size == 0;
        }
        
        public List<Object> listAllData() {
                LinkedList<Object> allData = new LinkedList<Object>();
                GenericNode n = first;
                while(true) {
                        allData.add(n.data);
                        if( n.next == null ) {
                                break;
                        }
                        else {
                                n = n.next;
                        }
                }
                return allData;
        }
        
        public static void main(String[] args) throws Exception {
                Queue<Integer> queue = new Queue<Integer>(3);
                queue.enqueue(5);
                queue.enqueue(7);
                queue.dequeue();
                
                System.out.println(queue.listAllData());  //[7]
                
                Queue<Animal> animals = new Queue<Animal>(3);
                Animal rebel = new Animal(AnimalType.DOG, "rebel");
                Animal maxi = new Animal(AnimalType.DOG, "maxi");
                Animal kikiba = new Animal(AnimalType.CAT, "kikiba");
                animals.enqueue(rebel);
                animals.enqueue(maxi);
                animals.enqueue(kikiba);
                animals.dequeue();
                
                System.out.println(animals.size); //2, first is maxi, rebel got deq'd
        }

        public Object peek() {
                return first.data;
        }
        
        class Node {
                Node left;
                Node right;
                Node next;
        }
}