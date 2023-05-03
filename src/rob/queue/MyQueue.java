package rob.queue;

import rob.stack.Stack;

/**
 * Queue made of two stacks
 * @author AyMamaQueRico
 * 
 * s1:         
 * []
 * [5]
 * [5,7]
 * [5,7,9]
 * [5,7,9,11]
 * dequeue()
 * s2:  (pop from s1, to s2)
 * []
 * [11]
 * [11,9]
 * [11,9,7]
 * [11,9,7,5]
 * item = s2.pop() = 5;
 * (re-populate s1 by popping s2)
 * return item.
 * 
 * for case of 2 dequeue operations back-to-back, 
 * don't want to copy to s2, then back to s1, unnecessarily.
 * so have populateReversedStack() which runs only if s2 is empty.
 */
public class MyQueue {
        Stack stack;         // "real" stack
        Stack reversedStack; // "queue" stack
        
        public MyQueue() {
                stack = new Stack();
                reversedStack = new Stack();
        }
        
        void enqueue(int x) throws Exception {
                stack.push(x);
        }
        
        int dequeue() throws Exception {
                while(!stack.isEmpty()) {
                        reversedStack.push(stack.pop());
                }
                int item = reversedStack.pop();
                while(!reversedStack.isEmpty()) {
                        stack.push(reversedStack.pop());
                }
                return item;
        }
        
        // lazy approach to prevent needless copying 
        // in the case of 2 dequeue() calls back-to-back
        int dequeueLazy() throws Exception {
                populateReversedStack();
                return reversedStack.pop();
        }
        
        // leave elements in s2. no need to copy back into s1.
        private void populateReversedStack() throws Exception {
                if( reversedStack.isEmpty() ) {
                        while(!stack.isEmpty()) {
                                reversedStack.push(stack.pop());
                        }
                }
        }
        
        public static void main(String[] args) throws Exception {
                MyQueue myQueue = new MyQueue();
                myQueue.enqueue(5);
                myQueue.enqueue(7);
                myQueue.enqueue(9);
                System.out.println(myQueue.dequeue());  // 5
                myQueue.enqueue(11);
                System.out.println(myQueue.dequeue());  // 7
                
                MyQueue lazyQueue = new MyQueue();
                lazyQueue.enqueue(5);
                lazyQueue.enqueue(7);
                lazyQueue.enqueue(9);
                System.out.println(lazyQueue.dequeueLazy());  // 5
                lazyQueue.enqueue(11);
                System.out.println(lazyQueue.dequeueLazy());  // 7
        }
}
