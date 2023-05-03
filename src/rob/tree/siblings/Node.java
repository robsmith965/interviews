package rob.tree.siblings;

public class Node {
        // making these public so i don't have to create a getter
        public int data;
        public Node left;
        public Node right;
        public Node next;  // used by SiblingPointers
        
        public Node(int d) {
                this.data = d;
        }
        
        public static void main(String[] args) {
                Node root = new Node(5);
        }
}
