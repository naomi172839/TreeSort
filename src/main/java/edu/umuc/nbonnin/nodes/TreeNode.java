package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.util.ArrayList;

/*
 *          *****TreeNode Class*****
 *
 * TreeNode implements ITreeNode
 * TreeNode is comparable
 * TreeNode is serializable
 * TreeNode is generic (Type T)
 *
 * The TreeNode class defines a basic Tree node
 * The TreeNode class is the minimum implementation of a node
 *
 * Variables:   value       :   T           -   Represents the value of the object
 *              parent      :   ITreeNode   -   Represents the parent of this node
 *              children    :   ArrayList   -   Represents the children of this node
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   T           -   Represents the value of the object
 *
 * Methods:     **Gets the value of the object**
 *              getValue    :   Arguments   :   None
 *                              Returns     :   T       -   Represents the value of the object
 *
 *              **Sets the value of the object**
 *              setValue    :   Arguments   :   T       -   Represents the value of the object
 *                              Returns     :   None
 *
 *              **Gets the parent node**
 *              getParent   :   Arguments   :   None
 *                              Returns     :   ITreeNode-  Represents the parent node
 *
 *              **Sets the parent node**
 *              setParent   :   Arguments   :   ITreeNode-  Represents the new parent node
 *                              Returns     :   None
 *
 *              **Gets the children of this node**
 *              getChildren :   Arguments   :   None
 *                              Returns     :   ArrayList-  Contains all of this nodes children
 *
 *              **Adds a child to this node**
 *              addChild    :   Arguments   :   ITreeNode-  Represents the child to add
 *                              Returns     :   None
 *
 *              **Compares this object to another**
 *              compareTo   :   Arguments   :   IntType -   Represents the object to compare this to
 *                              Returns     :   int     -   < 0 : this is smaller
 *                                                          = 0 : this is equal
 *                                                          > 0 : this is larger
 */
public class TreeNode<T extends DataType> implements ITreeNode<T> {

    /*
     *      *****Instance Variables*****
     *
     * value    :   T       -   Represents the value of the object
     * parent   :   ITreeNode-  Represents the parent of this node
     * children :   ArrayList-  Contains the children of this node
     */
    private T value;
    private ITreeNode<T> parent;
    private ArrayList<ITreeNode<T>> children;

    /*
     * 1 Argument constructor
     * Create a new TreeNode
     */
    public TreeNode(T value) {
        parent = null;
        children = new ArrayList<>();
        this.value = value;
    }

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public TreeNode() {
        throw new IllegalArgumentException("Must supply a value!");
    }

    /*
     * Getter for value
     */
    public T getValue() {
        return value;
    }

    /*
     * Setter for value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /*
     * Getter for parent
     */
    public ITreeNode<T> getParent() {
        return this.parent;
    }

    /*
     * Setter for parent
     */
    public void setParent(ITreeNode<T> parent) {
        this.parent = parent;
    }

    /*
     * Adds a child to the list of this nodes children
     * Also sets this node as the parent of that child
     */
    public void addChild(ITreeNode<T> child) {
        child.setParent(this);
        children.add(child);
    }

    /*
     * Getter for children
     */
    public ArrayList<ITreeNode<T>> getChildren() {
        return this.children;
    }

    /*
     * Compares this object to another of the same inherited type
     * Relies on the DataType compareTo method
     *
     * returns  int < 0 if  this is smaller
     *          int = 0 if  this is equal
     *          int > 0 if  this is larger
     *
     * @exception   -   IllegalArgumentException    -    class mismatch
     */
    @Override
    public int compareTo(ITreeNode<T> o) {
        return value.compareTo(o.getValue());
    }
}
