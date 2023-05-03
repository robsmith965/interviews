package rob.tree.siblings;

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
        
        void postOrder(Node root) {
                if(root==null)
                        return;
                postOrder(root.left);
                postOrder(root.right);
                System.out.println(root.data);
        }
        
        void preOrder(Node root) {
                if(root==null)
                        return;
                System.out.println(root.data);
                preOrder(root.left);
                preOrder(root.right);
        }
        
        // might need some refactoring
        Node insert(Node root, int x) throws Exception {
                if( this.root == null ) {
                        this.root = new Node(x);
                        return this.root;
                } else if( x < root.data ) {
                        if( root.left == null ) {
                                root.left = new Node(x);
                                return this.root;
                        }
                        insert(root.left, x);
                } else if( x > root.data ) {
                        if( root.right == null ) {
                                root.right = new Node(x);
                                return this.root;
                        }
                        insert(root.right, x);
                } else if( x == root.data ){ 
                        throw new Exception("duplicate entry!");
                }
                
                return this.root;
        }
        
        void inOrder(Node root) {
                if( root == null ) {
                        return;
                }
                inOrder(root.left);
                System.out.print(root.data);
                
                // for SiblingPointers:
                if( root.next != null ) {
                        System.out.print("  next: " + root.next.data);
                }
                
                System.out.println();
                inOrder(root.right);
        }
        
        
        
        public static void main(String[] args) throws Exception {
                Tree tree = new Tree();
                tree.insert(tree.root, 4);
                tree.insert(tree.root, 2);
                tree.insert(tree.root, 1);
                tree.insert(tree.root, 3);
                tree.insert(tree.root, 5);
                
                tree.inOrder(tree.root);
                
                
                
                int[] array = {1,2,3};
                int length = array.length;
                int x = 10 / length;
                System.out.println(x);
        }
}
