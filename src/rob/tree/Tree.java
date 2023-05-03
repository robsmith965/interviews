package rob.tree;

import rob.queue.Queue;

import java.util.Stack;

/**
 * Binary tree. Not a BST.
 */
public class Tree {
        public Node root;
        
        public Tree(Node root) {
                this.root = root;
        }
        
        public Tree() {
                this.root = null;
        }

        // might need some refactoring
        Node insert(Node root, int x) throws Exception {
                if( this.root == null ) {
                        this.root = new Node(x);
                        return this.root;
                } else if( x < root.data ) {
                        if( root.getLeft() == null ) {
                                root.setLeft(new Node(x));
                                return this.root;
                        }
                        insert(root.getLeft(), x);
                } else if( x > root.data ) {
                        if( root.getRight() == null ) {
                                root.setRight(new Node(x));
                                return this.root;
                        }
                        insert(root.getRight(), x);
                } else if( x == root.data ){ 
                        throw new Exception("duplicate entry!");
                }
                
                return this.root;
        }
        
        void inOrder(Node root) {
                if( root == null ) {
                        return;
                }
                inOrder(root.getLeft());
                System.out.print(root.data);
                
                // for SiblingPointers:
                if( root.next != null ) {
                        System.out.print("  next: " + root.next.data);
                }
                
                System.out.println();
                inOrder(root.getRight());
        }
        
        void postOrder(Node root) {
                if(root==null)
                        return;
                postOrder(root.getLeft());
                postOrder(root.getRight());
                System.out.println(root.data);
        }
        
        void preOrder(Node root) {
                if(root==null)
                        return;
                System.out.println(root.data);
                preOrder(root.getLeft());
                preOrder(root.getRight());
        }
        
        void preOrderIterativeBad(Node root) {
                Stack<Node> s = new Stack<Node>();
                s.push(root);
                
                while( !s.isEmpty() ) {
                        Node n = s.pop();
                        n.visited = true;  // necessary to avoid inf loops in connected graphs. not nec. in binary trees.
                        System.out.println(n.data);
                        for( int i = n.children.length-1; i >= 0 ; i-- ) {
                                if( n.children[i] != null && n.children[i].visited == false ) {
                                        s.push(n.children[i]);
                                }
                        }
                }
        }
        
        /**
         * WRONG
         * 
         * BFS uses a queue. Don't try depth-first searches with queues.
         */
        void preOrderIterativeBad2(Node root) throws Exception {
                Queue<Node> q = new Queue<Node>(6);
                
                while( !q.isEmpty() || root != null ) {
                        if( root != null ) {
                                root.visited = true;
                                q.enqueue(root);
                                root = root.getLeft();
                        } else {
                                root = (Node) q.dequeue();
                                System.out.println(root.data);
                                root = root.getRight();
                        }
                }
        }

        // WRONG. nice try ;)
        void inOrderIterativeBad(Node root) {
                Stack<Node> s = new Stack<Node>();
                if( root == null ) {
                        System.out.println("root is null!");
                        return;
                }
                Node n = root;
                do {
                        if( n.getRight() != null && n.getRight().visited == false ) {
                                s.push(n.getRight());
                        }
                        if( n.visited == false ) {
                                n.visited = true;
                                s.push(n);
                        }
                        if( n.getLeft() != null && n.getLeft().visited == false ) {
                                s.push(n.getLeft());
                        }
                        n = s.pop();
                        System.out.println("popped " + n.data);
                } while( !s.isEmpty() );
        }

        void postOrderIterativeBad(Node root) {
                Stack<Node> s = new Stack<Node>();
                
                Node n = root;                
                while( n != null ) {
                        if( n.visited == false ) {
                                s.push(n);                                
                        }
                        n.visited = true;
                        n = n.getRight();
                }
                while( !s.isEmpty() ) {
                        System.out.println(s.pop().data);
                }
        }

        void preOrderIterative(Node root) {
                Stack<Node> s = new Stack<Node>();
                Node n = root;
                while( n != null || !s.isEmpty() ) {
                        if( n != null) {
                                System.out.println(n.data);
                                if( n.getRight() != null ) {
                                        s.push(n.getRight());
                                }
                                n = n.getLeft();
                        } else {
                                n= s.pop();
                        }
                }
        }
        
        void inOrderIterative(Node root) {
                Stack<Node> s = new Stack<Node>();
                while( !s.isEmpty() || root != null ) {
                        if( root != null ) {
                                s.push(root);
                                root = root.getLeft();
                        } else {
                                root = s.pop();
                                System.out.println(root.data);
                                root = root.getRight();
                        }
                }
        }
        
        void postOrderIterative(Node root) {
                Stack<Node> s = new Stack<Node>();
                Node lastNodePrinted = null;
                while( root != null || !s.isEmpty() ) {
                        if( root != null ) {
                                s.push(root);
                                root = root.getLeft();
                        } else {
                                Node top = s.peek();
                                /* careful here.
                                 * need to check that top.right
                                 * hasn't already been printed. */
                                if( top.getRight() != null 
                                    && lastNodePrinted != top.getRight() ) {
                                        root = top.getRight();
                                } else {
                                        System.out.println(top.data);
                                        lastNodePrinted = s.pop();   // top = s.pop
                                }
                        }
                }
        }
        
        void bfs(Node root) throws Exception {
                Queue<Node> q = new Queue<Node>();
                root.visited = true;
                System.out.println(root.data);
                q.enqueue(root);
                
                while( !q.isEmpty() ) {
                        Node n = (Node) q.dequeue();
                        for( Node child : n.children ) {
                                if( child != null && child.visited == false ) {
                                        child.visited = true;
                                        System.out.println(child.data);
                                        q.enqueue(child);
                                }
                        }
                }
        }
        
        public static void main(String[] args) throws Exception {
                Tree tree = new Tree();
                tree.insert(tree.root, 4);
                tree.insert(tree.root, 2);
                tree.insert(tree.root, 1);
                tree.insert(tree.root, 3);
                tree.insert(tree.root, 5);
                
//                tree.inOrder(tree.root);
                
//                tree.preOrderIterative(tree.root);
                
//                tree.preOrderIterative(tree.root);
                
//                tree.inOrderIterative(tree.root);
                
//                tree.preOrderQueue(tree.root);
                
//                tree.postOrderIterative(tree.root);
                
                tree.postOrder(tree.root);
                
//                tree.bfs(tree.root);
        }
}
