package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.ArrayList;

/*
 *          *****TreeNode Interface*****
 *
 * ITreeNode extends Comparable with type ITreeNode
 * ITreeNode extends java.io.Serializable
 *
 * ITreeNode is generic and accepts objects that implement the DataType interface
 *
 * ITreeNode require that all classes be JavaBean compliant
 *
 * The ITreeNode interface defines all of the methods that any TreeNode object needs
 * This is important as other interfaces and classes will implement/extend this
 * Those other classes will define the stricter types of TreeNodes such as BinarySearchTreeNodes
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
@JavaBean
public interface ITreeNode<T extends DataType> extends Comparable<ITreeNode<T>>, Serializable {

    T getValue();

    void setValue(T value);

    ITreeNode<T> getParent();

    void setParent(ITreeNode<T> parent);

    void addChild(ITreeNode<T> child);

    ArrayList<ITreeNode<T>> getChildren();

    int compareTo(ITreeNode<T> o);

}
