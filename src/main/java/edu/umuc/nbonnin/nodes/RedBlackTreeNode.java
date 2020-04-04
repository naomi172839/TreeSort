package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.util.ArrayList;

/*
 *          *****RedBlackTreeNode Class*****
 *
 * RedBlackTreeNode extends BinarySearchTreeNode
 * RedBlackTreeNode is comparable
 * RedBlackTreeNode is serializable
 * RedBlackTreeNode is generic (Type T)
 *
 * The RedBlackTreeNode class defines a basic RedBlackTree node
 * The RedBlackTreeNode class is the minimum implementation of a RedBlackTree node
 *
 * Variables:   left        :   RedBlackTreeNode    -   Represents the left child
 *              right       :   RedBlackTreeNode    -   Represents the left child
 *              parent      :   RedBlackTreeNode    -   Represents the parent
 *              color       :   boolean             -   Represents the color, true for red, false for black
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   T               -   Represents the value of the object
 *
 * Methods:     **Gets the left child of the node**
 *              getLeft     :   Arguments   :   None
 *                              Returns     :   RedBlackTreeNode  -   Represents the left child
 *
 *              **Sets the left child of the node**
 *              setLeft     :   Arguments   :   RedBlackTreeNode  -   Represents the new left child
 *                              Returns     :   None
 *
 *              **Gets the right child of the node**
 *              getRight    :   Arguments   :   None
 *                              Returns     :   RedBlackTreeNode  -   Represents the right child
 *
 *              **Sets the right child of the node**
 *              setRight    :   Arguments   :   RedBlackTreeNode  -   Represents the new right child
 *                              Returns     :   None
 *              **Gets the count of the node**
 *              getColor    :   Arguments   :   boolean           -   Represents the current color
 *                              Returns     :   Node
 *              **Sets the count of the node**
 *              setColor    :   Arguments   :   boolean           -   Represents the new color
 *                              Returns     :   None
 *              **Gets the children of this node**
 *              getChildren :   Arguments   :   None
 *                              Returns     :   ArrayList-  Contains all of this nodes children
 */
public class RedBlackTreeNode<T extends DataType> extends BinarySearchTreeNode<T> {

    /*
     *      *****Instance Variables*****
     *
     * left     :   BinarySearchTreeNode    -   Represents the left child
     * right    :   BinarySearchTreeNode    -   Represents the right child
     * parent   :   BinarySearchTreeNode    -   Represents the parent
     * color    :   boolea                  -   Represents the node color
     */
    private RedBlackTreeNode<T> left, right, parent;
    private boolean color;                                          //True if red

    /*
     * 1 Argument constructor
     * Create a new RedBlackTreeNode
     */
    public RedBlackTreeNode(T value) {
        super(value);
        color = true;
    }

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public RedBlackTreeNode() {
        super();
    }

    /*
     * Getter for left
     */
    @Override
    public RedBlackTreeNode<T> getLeft() {
        return this.left;
    }

    /*
     * Setter for left
     */
    public void setLeft(RedBlackTreeNode<T> left) {
        this.left = left;
        left.setParent(this);
    }

    /*
     * Getter for right
     */
    @Override
    public RedBlackTreeNode<T> getRight() {
        return this.right;
    }

    /*
     * Setter for right
     */
    public void setRight(RedBlackTreeNode<T> right) {
        this.right = right;
        right.setParent(this);
    }

    /*
     * Getter for parent
     */
    @Override
    public RedBlackTreeNode<T> getParent() {
        return this.parent;
    }

    /*
     * Setter for parent
     */
    public void setParent(RedBlackTreeNode<T> parent) {
        this.parent = parent;
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

    /*
     * Getter for color
     */
    public boolean getColor() {
        return this.color;
    }

    /*
     * Setter for color
     */
    public void setColor(boolean color) {
        this.color = color;
    }
}
