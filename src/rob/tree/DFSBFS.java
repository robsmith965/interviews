package rob.tree;

import rob.queue.Queue;

import java.util.Stack;

/**
 * DFS = pre-order traversal.
 */
public class DFSBFS {
        public static boolean found = false;
        /*
         * how to count # of recursive calls?
         */
        public static void dfs(Node root, int x) {
                /*
                 * point at which to stop recursing down, and "recurse up"
                 */
                if( root == null ) {
                        return;
                }
                System.out.println(root.data);
                if( root.data == x ) {
                        System.out.println("found " + x + " !");
                        //System.exit(0)  //stop searching
                        return;
                }
                /*
                 * we could do a DFS by sweeping from L to R, 
                 * or from R to L. let's do L to R.
                 */
                dfs(root.getLeft(), x);
                dfs(root.getRight(), x);
        }
        
        /**
         * Need to keep track of visited nodes
         * in GRAPHS, otherwise, infinite loop.
         */
        public static void dfsGraph(Node root, int x) {
                /*
                 * point at which to stop recursing down, and "recurse up"
                 */
                if( root == null ) {
                        return;
                }
                root.visited = true;
                System.out.println(root.data);
                if( root.data == x ) {
                        System.out.println("found " + x + " !");
                        return;
                }
                /*
                 * we could do a DFS by sweeping from L to R, 
                 * or from R to L. let's do L to R.
                 * 
                 * TODO WHY DO WE NEED THE NULL CHECKS BELOW?
                 */
                if( root.getLeft() != null && root.getLeft().visited == false ) {
                        dfsGraph(root.getLeft(), x);        
                }
                if( root.getRight() != null && root.getRight().visited == false ) {
                        dfsGraph(root.getRight(), x);        
                }
        }
        
        public static boolean dfsBoolean(Node root, Node target) {
                /*
                 * point at which to stop recursing down, and "recurse up"
                 */
                if( root == null ) {
                        return false;
                }
                root.visited = true;
                System.out.println(root.data);
                if( root.data == target.data ) {
                        System.out.println("found " + target.data + " !");
                        return true;
                }
                /*
                 * we could do a DFS by sweeping from L to R, 
                 * or from R to L. let's do L to R.
                 * 
                 * TODO WHY DO WE NEED THE NULL CHECKS BELOW?
                 */
                return ((root.getLeft() != null) && (root.getLeft().visited == false)
                                && (dfsBoolean(root.getLeft(), target))) ||
                       ((root.getRight() != null) && (root.getRight().visited == false)
                                && dfsBoolean(root.getRight(), target));
        }
        
        public static void dfsIterative(Node root, int x) {
                Stack<Node> stack = new Stack<Node>();
                stack.push(root);
                
                while(!stack.isEmpty()) {
                        Node n = stack.pop();
                        System.out.println("popped " + n.data);
                        if( n.data == x ) { 
                                System.out.println("found " + x + "!");
                                return;
                        }
                        for( int i = n.children.length-1; i >= 0 ; i-- ) {
                                if( n.children[i] != null ) {
                                        stack.push(n.children[i]);
                                }
                        }
                }
        }
        
        // only difference: use a queue
        public static void bfs(Node root, int x) throws Exception {
                Queue<Node> queue = new Queue<Node>();
                queue.enqueue(root);
                
                while(!queue.isEmpty()) {
                        Node n = (Node) queue.dequeue();
                        System.out.println("dequeued " + n.data);
                        if( n.data == x ) { 
                                System.out.println("found " + x + "!");
                                return;
                        }
                        for( int i = 0; i < n.children.length; i++ ) {
                                if( n.children[i] != null ) {
                                        queue.enqueue(n.children[i]);
                                }
                        }
                }
        }
        
        public static void bfsGraph(Node root, int x) throws Exception {
                Queue<Node> queue = new Queue<Node>();
                queue.enqueue(root);
                
                while(!queue.isEmpty()) {
                        Node n = (Node) queue.dequeue();
                        System.out.println("dequeued " + n.data);
                        if( n.data == x ) { 
                                System.out.println("found " + x + "!");
                                return;
                        }
                        for( int i = 0; i < n.children.length; i++ ) {
                                if( n.children[i] != null ) {
                                        queue.enqueue(n.children[i]);
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
//                tree.printInOrder(tree.root);  // 1,2,3,4,5
                
//                dfsIterative(tree.root, 3);  // 4,2,1,3
                
                dfs(tree.root, 3);  // 4,2,5,1,3
                
//                Node target = new Node(3);
//                boolean found = dfsBoolean(tree.root, target);
//                if( found ) {
//                        System.out.println("true");
//                } else {
//                        System.out.println("false");
//                }
        }
}
