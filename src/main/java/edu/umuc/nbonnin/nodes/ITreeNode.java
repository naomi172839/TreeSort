package edu.umuc.nbonnin.nodes;

import java.util.ArrayList;

public interface ITreeNode<T> extends Comparable<ITreeNode<T>> {

    T getValue();

    void setValue(T value);

    ITreeNode<T> getParent();

    void setParent(ITreeNode<T> parent);

    void addChild(ITreeNode<T> child);

    ArrayList<ITreeNode<T>> getChildren();

    int compareTo(ITreeNode<T> o);

}
