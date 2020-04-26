package edu.umuc.nbonnin.treesort;

import java.util.ArrayList;

/*
 *      ****Node Class****
 *
 * Node is doubly generic with the key requried to implement comparable and value as a subclass of Object
 * The class itself is public, though package-private would be better
 *
 * See below for methods
 * TODO: Add method comments here
 *  Add remove from multiple method for delete
 */
public class Node<K extends Comparable<K>, V> {

    /*
     *      ****Instance Variables****
     *
     * multiple     :       ArrayList   :   Type V list containing any duplicate key value pairs
     * key          :       K           :   Comparable object representing the key
     * value        :       V           :   Type V object representing the data or value
     * left         :       Node<K,V>   :   Represents the left child
     * right        :       Node<K,V>   :   Represents the right child
     * parent       :       Node<K,V>   :   Represents the parent of the node
     * color        :       boolean     :   Represents the nodes color (Red = true; Black = false)
     * count        :       int         :   Redundant but counts total occurrences
     */
    private final ArrayList<V> multiple;
    private K key;
    private V value;
    private Node<K, V> left, right, parent;
    private boolean color;
    private int count;

    /*
     * 2 Argument Constructor
     * Initilizes all values as appropriate;
     */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        left = right = parent = null;
        count = 1;
        multiple = new ArrayList<>();
    }

    /*
     * Getter for key
     */
    public K getKey() {
        return key;
    }

    /*
     * Setter for key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /*
     * Getter for value
     */
    public V getValue() {
        return value;
    }

    /*
     * Setter for value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /*
     * Getter for left
     */
    public Node<K, V> getLeft() {
        return left;
    }

    /*
     * Setter for left
     * Properly creates all the links and removes old ones
     */
    public void setLeft(Node<K, V> newChild) {
        checkAncestor(newChild);    //Checks if this is a valid child
        if (left != null) {
            left.parent = null;     //Unlinks the left node
        }
        if (newChild != null) {
            newChild.removeParent();    //Removes newChild from its current parent
            newChild.parent = this;     //Set newChild's parent as this
        }
        this.left = newChild;           //Sets the left child as newChild
    }

    /*
     * Getter for right
     */
    public Node<K, V> getRight() {
        return right;
    }

    /*
     * Setter for right
     */
    public void setRight(Node<K, V> child) {
        checkAncestor(child);   //Checks if this is a valid child
        if (right != null) {
            right.parent = null;    //Unlinks the right node
        }
        if (child != null) {
            child.removeParent();   //Removes the parent
            child.parent = this;    //Sets chlld's parent as this
        }
        this.right = child;         //Sets the right child as child
    }

    /*
     * Getter for parent
     */
    public Node<K, V> getParent() {
        return parent;
    }

    /*
     * A setter for parents of sorts
     * Does not take any arguments and properly unlinks each of the nodes
     */
    public void removeParent() {
        if (parent != null) {
            if (parent.left == this) {
                parent.left = null;
            } else if (parent.right == this) {
                parent.right = null;
            }
            this.parent = null;
        }
    }

    /*
     * Checks if the node is on the path to the root
     */
    private void checkAncestor(Node<K, V> child) {
        for (Node<K, V> node = this; node != null; node = node.parent) {    //Iterates through until root
            if (node == child) {
                throw new IllegalArgumentException("(Node) Can not be ancestor: " + child.value.toString());
            }
        }
    }

    /*
     * Getter for color
     */
    public boolean getColor() {
        return color;
    }

    /*
     * Setter for color
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /*
     * Getter for Count
     */
    public int getCount() {
        return count;
    }

    /*
     * Increments the node counter
     */
    public void increment() {
        count++;
    }

    /*
     * Decrements the node counter
     */
    public void decrement() {
        count--;
    }

    /*
     * Getter for multiple
     */
    public ArrayList<V> getMultiple() {
        return multiple;
    }

    /*
     * Adds an item to multiple
     */
    public void addMultiple(V value) {
        multiple.add(value);
    }

    /*
     * Defines a visitor interface in accordance with the visitor design pattern by the GOF
     */
    public interface Visitor {
        <K extends Comparable<K>, V> void visit(Node<K, V> node);
    }
}
