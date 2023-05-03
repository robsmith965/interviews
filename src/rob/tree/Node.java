package rob.tree;

public class Node {
        // making these public so i don't have to create a getter
        public int data;
        public int level;
        public boolean visited = false;
        public Node[] children = new Node[2];
        public Node next;  // used by SiblingPointers
        
        private static final int LEFT_CHILD_INDEX = 0;
        private static final int RIGHT_CHILD_INDEX = 1;
        
        public Node(int d) {
                this.data = d;
        }
        
        public void setLeft(Node n) {
                children[LEFT_CHILD_INDEX] =  n;
        }
        
        public void setRight(Node n) {
                children[RIGHT_CHILD_INDEX] =  n; 
        }
        
        public Node getLeft() {
                return children[LEFT_CHILD_INDEX];
        }
        
        public Node getRight() {
                return children[RIGHT_CHILD_INDEX];
        }
        
        public static void main(String[] args) {
                Node root = new Node(5);
                root.setLeft(new Node(3));
                root.setRight(new Node(7));
                
                System.out.println(root.data);
                System.out.println(root.getLeft().data);
                System.out.println(root.getRight().data);
        }
}
