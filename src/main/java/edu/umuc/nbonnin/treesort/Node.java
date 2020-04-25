package edu.umuc.nbonnin.treesort;

import java.util.ArrayList;

public class Node<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private final ArrayList<V> multiple;
    private K key;
    private V value;
    private Node<K, V> left, right, parent;
    private boolean color;
    private int count;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        left = right = parent = null;
        count = 1;
        color = BLACK;
        multiple = new ArrayList<>();
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public ArrayList<V> getMultiple() {
        return multiple;
    }

    public void addMultiple(V value) {
        multiple.add(value);
    }

    public interface Visitor {
        <K extends Comparable<K>, V> void visit(Node<K, V> node);
    }
}
