package edu.umuc.nbonnin.treesort;

/*
 *      *****RedBlackTree*****
 *
 * The Meat and Potatoes of the application
 * A RedBlackTree (RBT) is a self- balancing BST
 * All of the methods are implemented for a complete data structure however not all are used
 *
 * NOTE: I used many sources for the general algorithms.  The ones that I can remember visiting are below!
 *
 * https://cs.lmu.edu/~ray/classes/dsa/
 * https://bradfieldcs.com/algos/trees/avl-trees/
 * https://www.geeksforgeeks.org/red-black-tree-set-2-insert/
 * http://pages.cs.wisc.edu/~hasti/cs367-common/readings/Red-Black-Trees/index.html
 * https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree
 * https://www.programiz.com/dsa/insertion-in-a-red-black-tree#insertfix
 *
 * TODO: Add all of the method comments here, time permitting
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    /*
     *      ****Instance Variables****
     *
     * RED      :       boolean     :   true for Red color
     * BLACK    :       boolean     :   false for red color
     * root     :       Node<K,V>   :   holds the root node
     * returnedString
     *          :       StringBuilder
     *                              :   Used to get the sorted string
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root;
    private StringBuilder returnedString;

    /*
     * 0 Argument Constructor
     * Initializes variables as appropriate
     */
    public RedBlackTree() {
        root = null;
        returnedString = new StringBuilder();
    }

    /*
     * Inserts the key value pair into the tree
     * Wraps both insertRecursive and insertionAdjust
     */
    public void insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        insertRecursive(this.root, newNode);    //Starting at the root, insert node
        insertionAdjust(newNode);               //Starting at the new node, adjust the tree
        if (root == null) {                     //If root does not exist, make one!
            root = newNode;
        }

    }

    /*
     * Recursive method to insert the new node into the tree
     */
    private void insertRecursive(Node<K, V> node, Node<K, V> newNode) {
        if (node != null) { //Helps prevent null pointer issues
            /*
             * If the key value pair already exists
             * Add it to the multiple list
             * Also increment the count (depreciated)
             */
            if (node.getKey().compareTo(newNode.getKey()) == 0) {
                node.increment();
                node.addMultiple(node.getValue());
                return;
                /*
                 * If the new node's key is smaller than the current node
                 * Recurse down the tree until node.left is null
                 * adds new node as null.left
                 */
            } else if (node.getKey().compareTo(newNode.getKey()) > 0) {
                if (node.getLeft() != null) {   //Prevents null pointer issues
                    insertRecursive(node.getLeft(), newNode);
                    return;
                } else {
                    node.setLeft(newNode);
                }
                /*
                 * If the new nodes key is larger than the current node
                 * Recurse down the tree until node.right is null
                 * ass the new node as node.right
                 */
            } else {
                if (node.getRight() != null) {  //Prevents null pointer issues
                    insertRecursive(node.getRight(), newNode);
                    return;
                } else {
                    node.setRight(newNode);
                }
            }
        }
        setColor(newNode, RED); //New nodes need to be red
    }

    /*
     * Rebalance the tree
     * Calls each of the case methods
     */
    private void insertionAdjust(Node<K, V> node) {
        if (getParentNode(node) == null) {
            insertCase1(node);
        } else if (getColor(getParentNode(node)) == BLACK) {
            insertCase2(node);
        } else if (getSiblingNode(getParentNode(node)) != null && getColor(getSiblingNode(getParentNode(node))) == RED) {
            insertCase3(node);
        } else {
            insertCase4(node);
        }
    }

    /*
     * Case 1: Node is root
     * Set the root to be black
     */
    private void insertCase1(Node<K, V> n) {
        setColor(n, BLACK);
    }

    /*
     * Case 2: Node has a black parent
     * Do nothing as this is valid
     */
    private void insertCase2(Node<K, V> n) {
        return;
    }

    /*
     * Case 3: Node is red, parent is red and uncle is red
     * Recolor parent, uncle and grandparent
     * Rechecks tree
     */
    private void insertCase3(Node<K, V> n) {
        setColor(getParentNode(n), BLACK);
        setColor(getSiblingNode(getParentNode(n)), BLACK);
        setColor(getGrandParentNode(n), RED);
        insertionAdjust(getGrandParentNode(n));
    }

    /*
     * Case 4: node is red, parent is red, uncle is black
     * Step 1: if node is a right child and parent is a left child,
     *          rotate left
     *         if node is a left child and parent is a right child
     *          rotate right
     *         continue to step 2
     */
    private void insertCase4(Node<K, V> n) {
        Node<K, V> p = getParentNode(n);
        Node<K, V> g = getGrandParentNode(n);

        if (n == getRightNode(p) && p == getLeftNode(g)) {
            rotateLeft(p);
            n = getLeftNode(n);
        } else if (n == getLeftNode(p) && p == getRightNode(g)) {
            rotateRight(p);
            n = getRightNode(n);
        }
        insertCase4Step2(n);
    }

    /*
     * Case 4: See above
     * Step 2:  if node is a left child, rotate right
     *          if node is a right child, rotate left
     *          recolor parent and grandparent
     */
    private void insertCase4Step2(Node<K, V> n) {
        Node<K, V> p = getParentNode(n);
        Node<K, V> g = getGrandParentNode(n);

        if (n == getLeftNode(p)) {
            rotateRight(g);
        } else {
            rotateLeft(g);
        }
        setColor(p, BLACK);
        setColor(g, RED);
    }

    /*
     * deletes the given node from the tree
     * wrapper for deleteFromSubtree method
     */
    public void delete(K key) {
        deleteFromSubtree(root, key);   //call from root to include entire tree
    }

    /*
     * Removes the node from the tree, starting at the given point
     * No longer recursive
     */
    private void deleteFromSubtree(Node<K, V> node, K key) {
        node = find(node, key); //Finds the node to delete
        if (node != null) { //Helps prevent null pointer
            if (node.getCount() > 1) {
                node.decrement();   //Nees to be updated to check multiple field
            } else {
                //If node has 2 children
                if (node.getLeft() != null && node.getRight() != null) {
                    Node<K, V> temp = findMinimumInRightSubTree(node);  //Finds the minimum in right subtree
                    node.setKey(temp.getKey()); //Swap data
                    node.setValue(temp.getValue()); //Swap data
                    node = temp;    //Sets node to be the smalleser in right subtree
                }
                //Now we are operating on a node with no or one child
                Node<K, V> end;
                //Sets our end node to the opposite child if one child is null or null if both are null
                if (getLeftNode(node) == null) {
                    end = getRightNode(node);
                } else {
                    end = getLeftNode(node);
                }
                //Only for cases in which there was an actual child
                if (end != null) {
                    //For trees with only two nodes
                    if (node == root) {
                        setRoot(end);
                    }
                    //If node is a left child, replace node with end node
                    else if (node == node.getParent().getLeft()) {
                        node.getParent().setLeft(end);
                    }
                    //If node is a right child, replace node with end node
                    else {
                        node.getParent().setRight(end);
                    }
                    //Fixes double black problem
                    if (getColor(node) == BLACK) {
                        deletionAdjust(end);
                    }
                }
                //Case for trees with single node
                else if (node == root) {
                    setRoot(null);
                }
                //Case for if the node has no children
                else {
                    //Fixes the double black problem
                    if (getColor(node) == BLACK) {
                        //Adjust BEFORE making the node null
                        deletionAdjust(node);
                    }
                    node.removeParent();
                }
            }
        }
    }

    /*
     * Deletion adjust rebalances the tree after a node is removed
     * TODO: Put more comments here, this is a complicated method
     */
    private void deletionAdjust(Node<K, V> node) {
        //Ensures that balancing will happen until tree is balanced
        while (node != root && getColor(node) == BLACK) {
            //If node is a left child
            if (node == getLeftNode(getParentNode(node))) {
                Node<K, V> sibling = getRightNode(getParentNode(node));
                //If nodes sibling is red (and also not null)
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParentNode(node), RED);
                    rotateLeft(getParentNode(node));
                    sibling = getRightNode(getParentNode(node));
                }
                //If siblings children are both black (or null) after rotation
                assert sibling != null;
                if (getColor(getLeftNode(sibling)) == BLACK &&
                        getColor(getRightNode(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParentNode(node);
                } else {
                    if (getColor(getRightNode(sibling)) == BLACK) {
                        setColor(getLeftNode(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateRight(sibling);
                        sibling = getRightNode(getParentNode(node));
                    }
                    setColor(sibling, getColor(getParentNode(node)));
                    setColor(getParentNode(node), BLACK);
                    setColor(getRightNode(sibling), BLACK);
                    rotateLeft(node.getParent());
                    node = root;
                }
            }
            //If node is a right child
            else {
                Node<K, V> sibling = getLeftNode(getParentNode(node));
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParentNode(node), RED);
                    rotateRight(node.getParent());
                    sibling = getLeftNode(getParentNode(node));
                }
                assert sibling != null;
                if (getColor(getLeftNode(sibling)) == BLACK &&
                        getColor(getRightNode(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParentNode(node);
                } else {
                    if (getColor(getLeftNode(sibling)) == BLACK) {
                        setColor(getRightNode(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateLeft(sibling);
                        sibling = getLeftNode(getParentNode(node));
                    }
                    setColor(sibling, getColor(getParentNode(node)));
                    setColor(getParentNode(node), BLACK);
                    setColor(getLeftNode(sibling), BLACK);
                    rotateRight(node.getParent());
                    node = root;
                }

            }
            setColor(node, BLACK);
        }
    }

    /*
     * Classic right rotation around supplied node
     */
    private void rotateRight(Node<K, V> node) {
        if (getLeftNode(node) == null) {
            return;
        }
        /*
         * Create a copy of the nodes left child
         */
        Node<K, V> old = node.getLeft();
        /*
         * Set the left child of the node to the right child of the nodes old left child
         */
        node.setLeft(old.getRight());
        /*
         * If noe was the root, then the old left child becomes the root
         */
        if (node == root) {
            setRoot(old);
        }
        /*
         * If node was a left child
         */
        else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(old);
        }
        /*
         * If node was a right child
         */
        else if (node == node.getParent().getRight()) {
            node.getParent().setRight(old);
        }
        /*
         * Sets node to be the right child of its old left child
         */
        old.setRight(node);

    }

    /*
     * Classic left rotation
     * inverse of the above method
     */
    private void rotateLeft(Node<K, V> node) {
        if (getRightNode(node) == null) {
            return;
        }
        Node<K, V> oldRight = node.getRight();
        node.setRight(oldRight.getLeft());
        if (node == root) {
            setRoot(oldRight);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(oldRight);
        } else {
            node.getParent().setRight(oldRight);
        }
        oldRight.setLeft(node);
    }

    /*
     * Helper method to get the sibling of a node
     */
    private Node<K, V> getSiblingNode(Node<K, V> node) {
        if (node == null || node.getParent() == null) { //Prevents null pointer stuff
            return null;
        } else {
            if (node == node.getParent().getLeft()) {   //If node is a left child
                return node.getParent().getRight();
            } else {                                    //If node is a right child
                return node.getParent().getLeft();
            }
        }
    }

    /*
     * Helper method to get the parent node
     * Really just relocates the null checks
     */
    private Node<K, V> getParentNode(Node<K, V> node) {
        if (node == null) {
            return null;
        } else {
            return node.getParent();
        }
    }

    /*
     * Helper method to get the grandparent
     * Really just relocates the null checks
     */
    private Node<K, V> getGrandParentNode(Node<K, V> node) {
        if (node == null || node.getParent() == null) {
            return null;
        } else {
            return node.getParent().getParent();
        }

    }

    /*
     * helper method to get the left node
     * Really just relocates the null checks
     */
    private Node<K, V> getLeftNode(Node<K, V> node) {
        if (node == null) {
            return null;
        } else {
            return node.getLeft();
        }
    }

    /*
     * Helper method to get the right node
     * Really just relocates the null checks
     */
    private Node<K, V> getRightNode(Node<K, V> node) {
        if (node == null) {
            return null;
        } else {
            return node.getRight();
        }
    }

    /*
     * Helper method to get the color
     * Vital because leaf nodes are black
     */
    private boolean getColor(Node<K, V> node) {
        if (node == null) {
            return BLACK;
        } else {
            return node.getColor();
        }

    }

    /*
     * Helper method to set color
     * Really just locates the null checks here
     */
    private void setColor(Node<K, V> node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    /*
     * Get root is vital to starting at the root in other methods
     */
    public Node<K, V> getRoot() {
        return root;
    }

    /*
     * Helper method to set node
     * really just relocates the null checks
     */
    private void setRoot(Node<K, V> node) {
        if (node != null) {
            node.removeParent();
        }
        root = node;
    }


    /*
     * Helper method to find a node within a tree
     * Recurses down tree in usual fashion
     */
    public Node<K, V> find(Node<K, V> node, K key) {
        if (node == null || key.compareTo(node.getKey()) == 0) {
            return node;
        }
        if (key.compareTo(node.getKey()) < 0) {
            return find(node.getLeft(), key);
        }
        if (key.compareTo(node.getKey()) > 0) {
            return find(node.getRight(), key);
        }
        return node;
    }

    /*
     * Finds the smallest node in the right subtree of the supplied node
     */
    private Node<K, V> findMinimumInRightSubTree(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /*
     * Defines the preOrder traversal, is not actually used
     */
    public void preOrder(Node<K, V> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node);
        preOrder(node.getLeft(), visitor);
        preOrder(node.getRight(), visitor);
    }

    /*
     * Defines the postOrder traversal
     */
    public void postOrder(Node<?, ?> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft(), visitor);
        postOrder(node.getRight(), visitor);
        visitor.visit(node);
    }

    /*
     * Defines the in order traversal
     */
    public void inOrder(Node<?, ?> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), visitor);
        visitor.visit(node);
        inOrder(node.getRight(), visitor);
    }

    /*
     * Defines the reverse inorder traversal
     */
    public void reverseOrder(Node<K, V> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        reverseOrder(node.getRight(), visitor);
        visitor.visit(node);
        reverseOrder(node.getLeft(), visitor);
    }

    /*
     * returns a string representing the sorted string
     */
    public String normalSort() {
        //Visitor pattern to add string to builder
        inOrder(root, new Node.Visitor() {
            @Override
            public <k extends Comparable<k>, v> void visit(Node<k, v> node) {
                if (node == null) {
                    return;
                }
                returnedString.append(node.getValue().toString()).append(" ");
                for (v value : node.getMultiple()) { //In case there are multiple in one node
                    returnedString.append(value.toString()).append(" ");
                }
            }
        });
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }

    /*
     * See above, Returns reverse order
     */
    public String reverseSort() {
        reverseOrder(root, new Node.Visitor() {

            @Override
            public <k extends Comparable<k>, v> void visit(Node<k, v> node) {
                if (node == null) {
                    return;
                }
                returnedString.append(node.getValue().toString()).append(" ");
                for (v value : node.getMultiple()) {
                    returnedString.append(value.toString()).append(" ");
                }
            }
        });
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }

}
