package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.DataType;

public class BinarySearchTreeNode<T extends DataType> extends BinaryTreeNode<T> {

    public BinarySearchTreeNode(T value) {
        super(value);
    }

    public BinarySearchTreeNode() {
        super();
    }
}
