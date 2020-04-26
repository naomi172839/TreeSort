package edu.umuc.nbonnin.treesort;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root;
    private StringBuilder returnedString;

    public RedBlackTree() {
        root = null;
        returnedString = new StringBuilder();
    }

    public void insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        this.root = insertRecursive(this.root, newNode);
        insertionAdjust(newNode);

    }

    private Node<K, V> insertRecursive(Node<K, V> node, Node<K, V> newNode) {
        if (node == null) {
            return newNode;
        }
        if (newNode.getKey().compareTo(node.getKey()) == 0) {
            node.increment();
            node.addMultiple(newNode.getValue());
            insertRecursive(null, newNode);
        } else if (newNode.getKey().compareTo(node.getKey()) < 0) {
            node.setLeft(insertRecursive(node.getLeft(), newNode));
        } else {
            node.setRight(insertRecursive(node.getRight(), newNode));
        }
        return node;
    }

    public void delete(K key) {
        deleteFromSubtree(root, key);
    }

    private void deleteFromSubtree(Node<K, V> node, K key) {
        node = find(node, key);
        if (node != null) {
            if (node.getCount() > 1) {
                node.decrement();
            } else {
                //If node has 2 children
                if (node.getLeft() != null && node.getRight() != null) {
                    Node<K, V> temp = findMinimumInRightSubTree(node);
                    node.setKey(temp.getKey());
                    node.setValue(temp.getValue());
                    node = temp;
                }
                //Now we are operating on a node with no or one child
                Node<K, V> end;
                //Sets our end node to the opposite child if one child is null or null if both are null
                if (getLeftNode(node) == null) {
                    end = getRightNode(node);
                } else {
                    end = getLeftNode(node);
                }
                //Only for cases in which there was an actual child
                if (end != null) {
                    //For trees with only two nodes
                    if (node == root) {
                        setRoot(end);
                    }
                    //If node is a left child, replace node with end node
                    else if (node == node.getParent().getLeft()) {
                        node.getParent().setLeft(end);
                    }
                    //If node is a right child, replace node with end node
                    else {
                        node.getParent().setRight(end);
                    }
                    //Fixes double black problem
                    if (getColor(node) == BLACK) {
                        deletionAdjust(end);
                    }
                }
                //Case for trees with single node
                else if (node == root) {
                    setRoot(null);
                }
                //Case for if the node has no children
                else {
                    //Fixes the double black problem
                    if (getColor(node) == BLACK) {
                        //Adjust BEFORE making the node null
                        deletionAdjust(node);
                    }
                    node.removeParent();
                }
            }
        }
    }

    private void insertionAdjust(Node<K, V> node) {
        setColor(node, RED);
        //Double Red Problems
        if (node != null && node != root && getColor(getParentNode(node)) == RED) {
            //Try to recolor first (if appropriate)
            if (getColor(getSiblingNode(getParentNode(node))) == RED) {
                setColor(getParentNode(node), BLACK);
                setColor(getSiblingNode(getParentNode(node)), BLACK);
                setColor(getGrandParentNode(node), RED);
                insertionAdjust(getGrandParentNode(node));
            }
            //If parent is a left child
            else if (getParentNode(node) == getLeftNode(getGrandParentNode(node))) {
                //If node is a right child
                if (node == getRightNode(getParentNode(node))) {
                    rotateLeft(node = getParentNode(node));
                }
                //If node is a right or left child
                setColor(getParentNode(node), BLACK);
                setColor(getGrandParentNode(node), RED);
                rotateRight(getGrandParentNode(node));
            }
            //If parent is a right child
            else if (getParentNode(node) == getRightNode(getGrandParentNode(node))) {
                //If node is a left child
                if (node == getLeftNode(getParentNode(node))) {
                    rotateRight(node = getParentNode(node));
                }
                //If node is a right or a left child
                setColor(getParentNode(node), BLACK);
                setColor(getGrandParentNode(node), RED);
                rotateLeft(getGrandParentNode(node));
            }
        }
    }

    private void deletionAdjust(Node<K, V> node) {
        //Ensures that balancing will happen until tree is balanced
        while (node != root && getColor(node) == BLACK) {
            //If node is a left child
            if (node == getLeftNode(getParentNode(node))) {
                Node<K, V> sibling = getRightNode(getParentNode(node));
                //If nodes sibling is red (and also not null)
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParentNode(node), RED);
                    rotateLeft(getParentNode(node));
                    sibling = getRightNode(getParentNode(node));
                }
                //If siblings children are both black (or null) after rotation
                assert sibling != null;
                if (getColor(getLeftNode(sibling)) == BLACK &&
                        getColor(getRightNode(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParentNode(node);
                } else {
                    if (getColor(getRightNode(sibling)) == BLACK) {
                        setColor(getLeftNode(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateRight(sibling);
                        sibling = getRightNode(getParentNode(node));
                    }
                    setColor(sibling, getColor(getParentNode(node)));
                    setColor(getParentNode(node), BLACK);
                    setColor(getRightNode(sibling), BLACK);
                    rotateLeft(node.getParent());
                    node = root;
                }
            }
            //If node is a right child
            else {
                Node<K, V> sibling = getLeftNode(getParentNode(node));
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParentNode(node), RED);
                    rotateRight(node.getParent());
                    sibling = getLeftNode(getParentNode(node));
                }
                assert sibling != null;
                if (getColor(getLeftNode(sibling)) == BLACK &&
                        getColor(getRightNode(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParentNode(node);
                } else {
                    if (getColor(getLeftNode(sibling)) == BLACK) {
                        setColor(getRightNode(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateLeft(sibling);
                        sibling = getLeftNode(getParentNode(node));
                    }
                    setColor(sibling, getColor(getParentNode(node)));
                    setColor(getParentNode(node), BLACK);
                    setColor(getLeftNode(sibling), BLACK);
                    rotateRight(node.getParent());
                    node = root;
                }

            }
            setColor(node, BLACK);
        }
    }

    private void rotateRight(Node<K, V> node) {
        if (getLeftNode(node) == null) {
            return;
        }
        Node<K, V> oldLeft = node.getLeft();
        node.setLeft(oldLeft.getRight());
        if (node.getParent() == null) {
            root = oldLeft;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(oldLeft);
        } else {
            node.getParent().setRight(oldLeft);
        }
        oldLeft.setRight(node);
    }

    private void rotateLeft(Node<K, V> node) {
        if (getRightNode(node) == null) {
            return;
        }
        Node<K, V> oldRight = node.getRight();
        node.setRight(oldRight.getLeft());
        if (node.getParent() == null) {
            root = oldRight;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(oldRight);
        } else {
            node.getParent().setRight(oldRight);
        }
        oldRight.setLeft(node);
    }

    private Node<K, V> getSiblingNode(Node<K, V> node) {
        if (node != null && node.getParent() != null) {
            if (node == node.getParent().getLeft()) {
                return node.getParent().getRight();
            } else if (node == node.getParent().getRight()) {
                return node.getParent().getLeft();
            }
        }
        return null;
    }

    private Node<K, V> getParentNode(Node<K, V> node) {
        if (node != null && node.getParent() != null) {
            return node.getParent();
        }
        return null;
    }

    private Node<K, V> getGrandParentNode(Node<K, V> node) {
        if (node != null && node.getParent() != root) {
            return node.getParent().getParent();
        }
        return null;
    }

    private Node<K, V> getLeftNode(Node<K, V> node) {
        if (node != null && node.getLeft() != null) {
            return node.getLeft();
        }
        return null;
    }

    private Node<K, V> getRightNode(Node<K, V> node) {
        if (node != null && node.getRight() != null) {
            return node.getRight();
        }
        return null;
    }

    private boolean getColor(Node<K, V> node) {
        if (node != null && node.getColor() == RED) {
            return RED;
        }
        return BLACK;
    }

    private void setColor(Node<K, V> node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    public Node<K, V> find(Node<K, V> node, K key) {
        if (node == null || key.compareTo(node.getKey()) == 0) {
            return node;
        }
        if (key.compareTo(node.getKey()) < 0) {
            return find(node.getLeft(), key);
        }
        if (key.compareTo(node.getKey()) > 0) {
            return find(node.getRight(), key);
        }
        return node;
    }

    private Node<K, V> findMinimumInRightSubTree(Node<K, V> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void preOrder(Node<K, V> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node);
        preOrder(node.getLeft(), visitor);
        preOrder(node.getRight(), visitor);
    }

    public void postOrder(Node<?, ?> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft(), visitor);
        postOrder(node.getRight(), visitor);
        visitor.visit(node);
    }

    public void inOrder(Node<?, ?> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), visitor);
        visitor.visit(node);
        inOrder(node.getRight(), visitor);
    }

    private void reverseOrder(Node<K, V> node, Node.Visitor visitor) {
        if (node == null) {
            return;
        }
        reverseOrder(node.getRight(), visitor);
        visitor.visit(node);
        reverseOrder(node.getLeft(), visitor);
    }

    public String normalSort(Node.Visitor visitor) {
        inOrder(root, new Node.Visitor() {
            @Override
            public <k extends Comparable<k>, v> void visit(Node<k, v> node) {
                if (node == null) {
                    return;
                }
                returnedString.append(node.getValue().toString()).append(" ");
                for (v value : node.getMultiple()) {
                    returnedString.append(value.toString()).append(" ");
                }
            }
        });
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }

    public String reverseSort(Node.Visitor visitor) {
        reverseOrder(root, new Node.Visitor() {

            @Override
            public <k extends Comparable<k>, v> void visit(Node<k, v> node) {
                if (node == null) {
                    return;
                }
                returnedString.append(node.getValue().toString()).append(" ");
                for (v value : node.getMultiple()) {
                    returnedString.append(value.toString()).append(" ");
                }
            }
        });
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }


    public Node<K, V> getRoot() {
        return root;
    }

    private void setRoot(Node<K, V> node) {
        if (node != null) {
            node.removeParent();
        }
        root = node;
    }


}
