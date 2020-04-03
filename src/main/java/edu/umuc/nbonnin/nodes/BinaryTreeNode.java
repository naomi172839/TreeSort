package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.util.ArrayList;

/*
 *          *****BinaryTreeNode Class*****
 *
 * BinaryTreeNode extends TreeNode
 * BinaryTreeNode is comparable
 * BinaryTreeNode is serializable
 * BinaryTreeNode is generic (Type T)
 *
 * The BinaryTreeNode class defines a basic Tree node
 * The BinaryTreeNode class is the minimum implementation of a node
 *
 * Variables:   left        :   BinaryTreeNode  -   Represents the left child
 *              right       :   BinaryTreeNode  -   Represents the left child
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   T               -   Represents the value of the object
 *
 * Methods:     **Gets the left child of the node**
 *              getLeft     :   Arguments   :   None
 *                              Returns     :   BinaryTreeNode  -   Represents the left child
 *
 *              **Sets the left child of the node**
 *              setLeft    :   Arguments   :   BinaryTreeNode  -   Represents the new left child
 *                              Returns     :   None
 *
 *              **Gets the right child of the node**
 *              getRight    :   Arguments   :   None
 *                              Returns     :   BinaryTreeNode  -   Represents the right child
 *
 *              **Sets the right child of the node**
 *              setRight    :   Arguments   :   BinaryTreeNode  -   Represents the new right child
 *                              Returns     :   None
 *
 *              **Gets the children of this node**
 *              getChildren :   Arguments   :   None
 *                              Returns     :   ArrayList-  Contains all of this nodes children
 */
public class BinaryTreeNode<T extends DataType> extends TreeNode<T> {

    /*
     *      *****Instance Variables*****
     *
     * left     :   BinaryTreeNode  -   Represents the left child
     * right    :   BinaryTreeNode  -   Represents the right child
     * parent   :   BinaryTreeNode  -   Represents the parent
     */
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private BinaryTreeNode<T> parent;

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public BinaryTreeNode() {
        super();
    }

    /*
     * 1 Argument constructor
     * Create a new BinaryTreeNode
     */
    public BinaryTreeNode(T value) {
        super(value);
    }

    /*
     * Getter for left
     */
    public BinaryTreeNode<T> getLeft() {
        return this.left;
    }

    /*
     * Setter for left
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
        left.setParent(this);
    }

    /*
     * Getter for right
     */
    public BinaryTreeNode<T> getRight() {
        return this.right;
    }

    /*
     * Setter for right
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
        right.setParent(this);
    }

    /*
     * Overrides method from TreeNode
     * Returns a parent object of the appropriate type
     * Example of covariant return type
     */
    @Override
    public BinaryTreeNode<T> getParent() {
        return this.parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
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
}
