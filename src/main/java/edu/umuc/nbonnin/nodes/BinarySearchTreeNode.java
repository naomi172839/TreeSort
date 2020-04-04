package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.util.ArrayList;

/*
 *          *****BinarySearchTreeNode Class*****
 *
 * BinarySearchTreeNode extends BinaryTreeNode
 * BinarySearchTreeNode is comparable
 * BinarySearchTreeNode is serializable
 * BinarySearchTreeNode is generic (Type T)
 *
 * The BinarySearchTreeNode class defines a basic BinarySearchTree node
 * The BinarySearchTreeNode class is the minimum implementation of a BinarySearchTree node
 *
 * Variables:   left        :   BinarySearchTreeNode    -   Represents the left child
 *              right       :   BinarySearchTreeNode    -   Represents the left child
 *              parent      :   BinarySearchTreeNode    -   Represents the parent
 *              count       :   int                     -   Represents the number of nodes present
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   T               -   Represents the value of the object
 *
 * Methods:     **Gets the left child of the node**
 *              getLeft     :   Arguments   :   None
 *                              Returns     :   BinarySearchTreeNode  -   Represents the left child
 *
 *              **Sets the left child of the node**
 *              setLeft     :   Arguments   :   BinarySearchTreeNode  -   Represents the new left child
 *                              Returns     :   None
 *
 *              **Gets the right child of the node**
 *              getRight    :   Arguments   :   None
 *                              Returns     :   BinarySearchTreeNode  -   Represents the right child
 *
 *              **Sets the right child of the node**
 *              setRight    :   Arguments   :   BinarySearchTreeNode  -   Represents the new right child
 *                              Returns     :   None
 *              **Gets the count of the node**
 *              getCount    :   Arguments   :   BinarySearchTreeNode  -   Represents the count of nodes
 *                              Returns     :   Node
 *              **Sets the count of the node**
 *              setCount    :   Arguments   :   BinarySearchTreeNode  -   Represents the new node count
 *                              Returns     :   None
 *              **Increments the count of the node**
 *              ***Included for code clarity***
 *              incrementCount
 *                          :   Arguments   :   None
 *                              Returns     :   None
 *              **Increments the count of the node**
 *              ***Included for code clarity***
 *              decrementCount
 *                          :   Arguments   :   None
 *                              Returns     :   None
 *              **Gets the children of this node**
 *              getChildren :   Arguments   :   None
 *                              Returns     :   ArrayList-  Contains all of this nodes children
 */
public class BinarySearchTreeNode<T extends DataType> extends BinaryTreeNode<T> {

    /*
     *      *****Instance Variables*****
     *
     * left     :   BinarySearchTreeNode    -   Represents the left child
     * right    :   BinarySearchTreeNode    -   Represents the right child
     * parent   :   BinarySearchTreeNode    -   Represents the parent
     * count    :   int                     -   Represents the node counts
     */
    private int count;
    private BinarySearchTreeNode<T> left, right, parent;

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public BinarySearchTreeNode(T value) {
        super(value);
        incrementCount();
    }

    /*
     * 1 Argument constructor
     * Create a new BinarySearchTreeNode
     */
    public BinarySearchTreeNode() {
        super();
    }

    /*
     * Getter for count
     */
    public int getCount() {
        return this.count;
    }

    /*
     * Setter for count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /*
     * Getter for left
     */
    @Override
    public BinarySearchTreeNode<T> getLeft() {
        return this.left;
    }

    /*
     * Setter for left
     */
    public void setLeft(BinarySearchTreeNode<T> left) {
        this.left = left;
        left.setParent(this);
    }

    /*
     * Getter for right
     */
    @Override
    public BinarySearchTreeNode<T> getRight() {
        return this.right;
    }

    /*
     * Setter for right
     */
    public void setRight(BinarySearchTreeNode<T> right) {
        this.right = right;
        right.setParent(this);
    }

    /*
     * Getter for parent
     */
    @Override
    public BinarySearchTreeNode<T> getParent() {
        return this.parent;
    }

    /*
     * Setter for parent
     */
    public void setParent(BinarySearchTreeNode<T> parent) {
        this.parent = parent;
    }

    /*
     * Increments the node count
     * Included for code clarity
     */
    public void incrementCount() {
        count++;
    }

    /*
     * Decrements the node count
     * Included for code clarity
     */
    public void decrementCount() {
        count--;
    }

    /*
     * Overrides method from TreeNode
     * Returns list of children
     *
     * Uses new ArrayList to ignore the uses of TreeNode.add from the superclass
     */
    @Override
    public ArrayList<ITreeNode<T>> getChildren() {
        ArrayList<ITreeNode<T>> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        return list;
    }
}
