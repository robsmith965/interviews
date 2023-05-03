package rob.tree.siblings;

import java.util.LinkedList;

public class SiblingPointers {

    public static Node assignNextPointersBUG1(Node root) throws Exception {
        LinkedList<Node> currentLevelNodes = new LinkedList<Node>();
        if( root != null ) {
            currentLevelNodes.add(root);
        }

        /* 
         * On each iteration, save the list of Nodes
         * from the previous level into a 'parents' list.
         * Then populate the new 'currentLevelNodes' by adding
         * each child of each parent from the 'parents' list. 
         */
        while( currentLevelNodes.size() > 0 ) {
            LinkedList<Node> parents = currentLevelNodes;
            currentLevelNodes.clear();
            for( int i = 0; i < parents.size(); i++ ) {
                Node parent = parents.get(i);
                if( i != parents.size() - 1 ) { // assign next pointers to all but rightmost Node
                    parent.next = parents.get(i+1);
                }
                if( parent.left != null ) {
                    currentLevelNodes.add(parent.left);
                }
                if( parent.right != null ) {
                    currentLevelNodes.add(parent.right);
                }
            }
        }
        
        return root;
    }

    public static void assignNextPointersBUG2(Node root) {
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            for (int i = 0; i < q.size() - 1; i++) {
                q.get(i).next = q.get(i + 1);   // the queue does not know that the head is a terminal node. bug.
            }
            Node n = q.poll();
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
    }
    
    /**
     * have siblings point to their "next" node on the same level.
     * start with a BFS-esque level-order traversal.
     * 
     * don't need a  typical BFS level order traversal using a queue.
     * well, i do, but modified.
     * put all the current level nodes into a queue, and work with that queue. don't depend
     * on curt enqueue() and dequeue() operations. put several children into this queue at a time,
     * not just one at a time like in classic BFS.
     * @param root
     */
    public static void assignNextPointers(Node root) {
        LinkedList<Node> currentLevelNodes = new LinkedList<Node>();
        currentLevelNodes.add(root);
        while (!currentLevelNodes.isEmpty()) {
            LinkedList<Node> parents = new LinkedList<Node>();
            for (Node n : currentLevelNodes) {
                parents.add(n);
            }
            currentLevelNodes.clear();
            for (int i = 0; i < parents.size() - 1; i++) {
                parents.get(i).next = parents.get(i + 1);
            }
            for (Node parent : parents) {
                if (parent.left != null) {
                    currentLevelNodes.add(parent.left);
                }
                if (parent.right != null) {
                    currentLevelNodes.add(parent.right);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        tree.insert(tree.root, 5);
        tree.insert(tree.root, 3);
        tree.insert(tree.root, 10);
        tree.insert(tree.root, 1);
        tree.insert(tree.root, 4);
        tree.insert(tree.root, 7);
        
        tree.inOrder(tree.root);
        
        System.out.println("..................");

        assignNextPointers(tree.root);
        
        tree.inOrder(tree.root);
    }
}
