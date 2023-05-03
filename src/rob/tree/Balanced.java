package rob.tree;

import rob.queue.Queue;

/**
 * Binary tree. Not a BST.
 */
public class Balanced {
        
        /**
         * @param root of a binary tree
         */
        public static boolean isBalancedIterative(Node root) throws Exception {
                Queue<Node> q = new Queue<Node>();
                int level = 0;
                root.level = level;
                System.out.println(root.data);
                q.enqueue(root);
                
                while( !q.isEmpty() ) {
                        Node n = (Node) q.dequeue();
                        level++;
                        for( Node child : n.children ) {
                                if( child != null ) {
                                        child.level = level;
                                        System.out.println(child.data);
                                        q.enqueue(child);
                                }
                        }
                }
                
                // traverse tree again, check each subtree's balance
                if( !isSubTreeBalanced(root) ) {
                        return false;
                }
                q.enqueue(root);
                
                while( !q.isEmpty() ) {
                        Node n = (Node) q.dequeue();
                        for( Node child : n.children ) {
                                if( child != null ) {
                                        if( !isSubTreeBalanced(child) ) {
                                                return false;
                                        }
                                        q.enqueue(child);
                                }
                        }
                }
                
                return true;
        }
        
        private static boolean isSubTreeBalanced(Node n) {
                int l = 0;
                int r = 0;
                if( n.getLeft() != null ) {
                        l = n.getLeft().level;
                }
                if( n.getRight() != null ) {
                        r = n.getRight().level;
                }
                
                return Math.abs(l-r) < 1;
        }
        
        static int getHeight(Node root) {
                if( root == null ) {
                        return 0;
                }
                
                return Math.max(getHeight(root.getLeft()), getHeight(root.getRight()) ) + 1;
        }
        
        /**
         * Best one right here.
         * 
         * O(n lg n)
         */
        public static boolean isBalanced(Node root) {
                if( root == null ) {
                        return true;
                }
                
                int heightDiff = getHeight(root.getLeft()) - getHeight(root.getRight());
                if( Math.abs(heightDiff) > 1 ) {
                        return false;
                } else {
                        return isBalanced(root.getLeft()) && isBalanced(root.getRight());
                }
        }
        
        public static int checkHeight(Node root) {
                if( root == null ) {
                        return 0;
                }
                
                int leftHeight = checkHeight(root.getLeft());
                if( leftHeight == -1 ) {
                        return -1;
                }
                
                int rightHeight = checkHeight(root.getRight());
                if( rightHeight == -1 ) {
                        return -1;
                }
                
                int heightDiff = leftHeight - rightHeight;
                if( Math.abs(heightDiff) > 1 ) {
                        return -1;
                } else {
                        return Math.max(leftHeight, rightHeight) + 1;
                }
        }
        
        public static boolean isBalancedBetter(Node root) {
                if( checkHeight(root) == -1 ) {
                        return false;
                } else {
                        return true;
                }
        }

        public static void main(String[] args) throws Exception {
                Tree tree = new Tree();
                tree.insert(tree.root, 4);
                tree.insert(tree.root, 2);
                tree.insert(tree.root, 1);
                tree.insert(tree.root, 3);
                tree.insert(tree.root, 5);
                
                Tree stalk = new Tree();
                stalk.insert(stalk.root, 4);
                stalk.insert(stalk.root, 2);
                stalk.insert(stalk.root, 1);
                
                if( isBalanced(tree.root) ) {
                        System.out.println("tree is balanced!");
                }
                
                if( isBalanced(stalk.root) ) {
                        System.out.println("tree is balanced!");
                } else {
                        System.out.println("tree is NOT balanced!");
                }
                
//                if( isBalancedBetter(stalk.root) ) {
//                        System.out.println("tree is balanced!");
//                } else {
//                        System.out.println("tree is NOT balanced!");
//                }
                
//                System.out.println(getHeight(stalk.root));
                
                
        }
}
