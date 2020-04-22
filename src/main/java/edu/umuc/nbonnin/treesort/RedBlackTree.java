package edu.umuc.nbonnin.treesort;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private StringBuilder returnedString;

    public RedBlackTree() {
        root = null;
        returnedString = new StringBuilder();
    }

    public void insert(K key, V value) {
        Node newNode = new Node(key, value);
        this.root = insertRecursive(this.root, newNode);
        insertionAdjust(find(this.root, key));

    }

    private Node insertRecursive(Node node, Node newNode) {
        if (node == null) {
            return newNode;
        } else if (newNode.key.compareTo(node.key) == 0) {
            node.count++;
        } else if (newNode.key.compareTo(node.key) < 0) {
            node.left = insertRecursive(node.left, newNode);
            assert node.left != null;
            node.left.parent = node;
        } else {
            node.right = insertRecursive(node.right, newNode);
            assert node.right != null;
            node.right.parent = node;
        }
        return node;
    }

    public void delete(K key) {
        deleteFromSubtree(root, key);
    }

    private void deleteFromSubtree(Node node, K key) {
        node = find(node, key);
        if (node != null) {
            if (node.count > 1) {
                node.count--;
            } else {
                //If node has 2 children
                if (getLeft(node) != null && getRight(node) != null) {
                    Node temp = findMinimumInRightSubTree(node.right);
                    node.key = temp.key;
                    node.value = temp.value;
                    node = temp;
                }
                //Now we are operating on a node with no or one child
                Node end;
                //Sets our end node to the opposite child if one child is null or null if both are null
                if (getLeft(node) == null) {
                    end = getRight(node);
                } else {
                    end = getLeft(node);
                }
                //Only for cases in which there was an actual child
                if (end != null) {
                    //For trees with only two nodes
                    if (node == root) {
                        end.parent = null;
                        root = end;
                    }
                    //If node is a left child, replace node with end node
                    else if (node == getLeft(getParent(node))) {
                        end.parent = getParent(node);
                        node.parent.left = end;
                    }
                    //If node is a right child, replace node with end node
                    else {
                        end.parent = getParent(node);
                        node.parent.right = end;
                    }
                    //Fixes double black problem
                    if (getColor(node) == BLACK) {
                        deletionAdjust(end);
                    }
                }
                //Case for trees with single node
                else if (node == root) {
                    root = null;
                }
                //Case for if the node has no children
                else {
                    //Fixes the double black problem
                    if (getColor(node) == BLACK) {
                        //Adjust BEFORE making the node null
                        deletionAdjust(node);
                    }
                    //Removes the node from its parent (deleting it)
                    if (node == getLeft(getParent(node))) {
                        node.parent.left = null;
                    } else {
                        node.parent.right = null;
                    }
                }
            }
        }
    }

    private void insertionAdjust(Node node) {
        setColor(node, RED);
        //Double Red Problems
        if (node != null && node != root && getColor(getParent(node)) == RED) {
            //Try to recolor first (if appropriate)
            if (getColor(getSibling(getParent(node)))) {
                setColor(getParent(node), BLACK);
                setColor(getSibling(node.parent), BLACK);
                setColor(getGrandParent(node), RED);
                insertionAdjust(getGrandParent(node));
            }
            //If parent is a left child
            else if (getParent(node) == getLeft(getGrandParent(node))) {
                //If node is a right child
                if (node == getRight(getParent(node))) {
                    rotateLeft(getParent(node));
                }
                //If node is a right or left child
                setColor(getParent(node), BLACK);
                setColor(getGrandParent(node), RED);
                rotateRight(getGrandParent(node));
            }
            //If parent is a right child
            else if (getParent(node) == getRight(getGrandParent(node))) {
                //If node is a left child
                if (node == getLeft(getParent(node))) {
                    rotateRight(getParent(node));
                }
                //If node is a right or a left child
                setColor(getParent(node), BLACK);
                setColor(getGrandParent(node), RED);
                rotateLeft(getGrandParent(node));
            }
        }
        setColor(root, BLACK);
    }

    private void deletionAdjust(Node node) {
        //Ensures that balancing will happen until tree is balanced
        while (node != root && getColor(node) == BLACK) {
            //If node is a left child
            if (node == getLeft(getParent(node))) {
                Node sibling = getRight(getParent(node));
                //If nodes sibling is red (and also not null)
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParent(node), RED);
                    rotateLeft(getParent(node));
                    sibling = getRight(getParent(node));
                }
                //If siblings children are both black (or null) after rotation
                assert sibling != null;
                if (getColor(getLeft(sibling)) == BLACK &&
                        getColor(getRight(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParent(node);
                } else {
                    if (getColor(getRight(sibling)) == BLACK) {
                        setColor(getLeft(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateRight(sibling);
                        sibling = getRight(getParent(node));
                    }
                    setColor(sibling, getColor(getParent(node)));
                    setColor(getParent(node), BLACK);
                    setColor(getRight(sibling), BLACK);
                    rotateLeft(node.parent);
                    node = root;
                }
            }
            //If node is a right child
            else {
                Node sibling = getLeft(getParent(node));
                if (getColor(sibling) == RED) {
                    setColor(sibling, BLACK);
                    setColor(getParent(node), RED);
                    rotateRight(node.parent);
                    sibling = getLeft(getParent(node));
                }
                assert sibling != null;
                if (getColor(getLeft(sibling)) == BLACK &&
                        getColor(getRight(sibling)) == BLACK) {
                    setColor(sibling, RED);
                    node = getParent(node);
                } else {
                    if (getColor(getLeft(sibling)) == BLACK) {
                        setColor(getRight(sibling), BLACK);
                        setColor(sibling, RED);
                        rotateLeft(sibling);
                        sibling = getLeft(getParent(node));
                    }
                    setColor(sibling, getColor(getParent(node)));
                    setColor(getParent(node), BLACK);
                    setColor(getLeft(sibling), BLACK);
                    rotateRight(node.parent);
                    node = root;
                }

            }
            setColor(node, BLACK);
        }
    }

    private void rotateRight(Node node) {
        if (getLeft(node) == null) {
            return;
        }
        Node oldLeft = node.left;
        node.left = oldLeft.right;
        if (node == root) {
            root = oldLeft;
        } else if (node == node.parent.left) {
            node.parent.left = oldLeft;
        } else {
            node.parent.right = oldLeft;
        }
        oldLeft.parent = node.parent;
        oldLeft.right = node;
        node.parent = oldLeft;
    }

    private void rotateLeft(Node node) {
        if (getRight(node) == null) {
            return;
        }
        Node oldRight = node.right;
        node.right = oldRight.left;
        if (node == root) {
            root = oldRight;
        } else if (node == node.parent.right) {
            node.parent.right = oldRight;
        } else {
            node.parent.left = oldRight;
        }
        oldRight.parent = node.parent;
        oldRight.left = node;
        node.parent = oldRight;
    }

    private Node getSibling(Node node) {
        if (node != null && node.parent != null) {
            if (node == node.parent.left) {
                return node.parent.right;
            } else if (node == node.parent.right) {
                return node.parent.left;
            }
        }
        return null;
    }

    private Node getParent(Node node) {
        if (node != null && node.parent != null) {
            return node.parent;
        }
        return null;
    }

    private Node getGrandParent(Node node) {
        if (node != null && node.parent != root) {
            return node.parent.parent;
        }
        return null;
    }

    private Node getLeft(Node node) {
        if (node != null && node.left != null) {
            return node.left;
        }
        return null;
    }

    private Node getRight(Node node) {
        if (node != null && node.right != null) {
            return node.right;
        }
        return null;
    }

    private boolean getColor(Node node) {
        if (node != null && node.color == RED) {
            return RED;
        }
        return BLACK;
    }

    private void setColor(Node node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    public Node find(Node node, K key) {
        if (node == null || key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return find(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return find(node.right, key);
        }
        return node;
    }

    private Node findMinimumInRightSubTree(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        int tempCount = node.count;
        while (tempCount > 0) {
            returnedString.append(node.value.toString()).append(" ");
            tempCount--;
        }
        inOrder(node.right);
    }

    private void reverseOrder(Node node) {
        if (node == null) {
            return;
        }
        reverseOrder(node.right);
        int tempCount = node.count;
        while (tempCount > 0) {
            returnedString.append(node.value.toString()).append(" ");
            tempCount--;
        }
        reverseOrder(node.left);
    }

    public String normalSort() {
        inOrder(root);
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }

    public String reverseSort() {
        reverseOrder(root);
        String temp = returnedString.toString();
        returnedString = new StringBuilder();
        return temp.trim();
    }


    public Node getRoot() {
        return root;
    }

    private class Node {

        private K key;
        private V value;
        private Node left, right, parent;
        private boolean color;
        private int count;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = parent = null;
            count = 1;
            color = BLACK;
        }
    }

}
