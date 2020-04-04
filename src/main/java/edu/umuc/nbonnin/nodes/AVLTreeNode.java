package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.util.ArrayList;

/*
 *          *****AVLTreeNode Class*****
 *
 * AVLTreeNode extends BinarySearchTreeNode
 * AVLTreeNode is comparable
 * AVLTreeNode is serializable
 * AVLTreeNode is generic (Type T)
 *
 * The AVLTreeNode class defines a basic AVLTree node
 * The AVLTreeNode class is the minimum implementation of a AVLTree node
 *
 * Variables:   left        :   AVLTreeNode    -   Represents the left child
 *              right       :   AVLTreeNode    -   Represents the left child
 *              parent      :   AVLTreeNode    -   Represents the parent
 *              height      :   int            -   Represents the subtree height
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   T               -   Represents the value of the object
 *
 * Methods:     **Gets the left child of the node**
 *              getLeft     :   Arguments   :   None
 *                              Returns     :   AVLTreeNode  -   Represents the left child
 *
 *              **Sets the left child of the node**
 *              setLeft     :   Arguments   :   AVLTreeNode  -   Represents the new left child
 *                              Returns     :   None
 *
 *              **Gets the right child of the node**
 *              getRight    :   Arguments   :   None
 *                              Returns     :   AVLTreeNode  -   Represents the right child
 *
 *              **Sets the right child of the node**
 *              setRight    :   Arguments   :   AVLTreeNode  -   Represents the new right child
 *                              Returns     :   None
 *              **Gets the count of the node**
 *              getHeight   :   Arguments   :   int          -   Represents the current height
 *                              Returns     :   Node
 *              **Sets the count of the node**
 *              setHeight   :   Arguments   :   int          -   Represents the new height
 *                              Returns     :   None
 *              **Gets the children of this node**
 *              getChildren :   Arguments   :   None
 *                              Returns     :   ArrayList-  Contains all of this nodes children
 */
public class AVLTreeNode<T extends DataType> extends BinarySearchTreeNode<T> {

    /*
     *      *****Instance Variables*****
     *
     * left     :   BinarySearchTreeNode    -   Represents the left child
     * right    :   BinarySearchTreeNode    -   Represents the right child
     * parent   :   BinarySearchTreeNode    -   Represents the parent
     * height   :   int                     -   Represents the subtree height
     */
    private int height;
    private AVLTreeNode<T> left, right, parent;

    /*
     * 1 Argument constructor
     * Create a new AVLTreeNode
     */
    public AVLTreeNode(T value) {
        super(value);
        height = 0;
    }

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public AVLTreeNode() {
        super();
    }

    /*
     * Getter for left
     */
    @Override
    public AVLTreeNode<T> getLeft() {
        return this.left;
    }

    /*
     * Setter for left
     */
    public void setLeft(AVLTreeNode<T> left) {
        this.left = left;
        left.setParent(this);
    }

    /*
     * Getter for right
     */
    @Override
    public AVLTreeNode<T> getRight() {
        return this.right;
    }

    /*
     * Setter for right
     */
    public void setRight(AVLTreeNode<T> right) {
        this.right = right;
        right.setParent(this);
    }

    /*
     * Getter for parent
     */
    @Override
    public AVLTreeNode<T> getParent() {
        return this.parent;
    }

    /*
     * Setter for parent
     */
    public void setParent(AVLTreeNode<T> parent) {
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
     * Getter for height
     */
    public int getHeight() {
        return this.height;
    }

    /*
     * Setter for height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
