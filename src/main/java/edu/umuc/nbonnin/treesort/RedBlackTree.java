package edu.umuc.nbonnin.treesort;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public RedBlackTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
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
        this.root = deleteRecursive(this.root, key);
    }

    private Node deleteRecursive(Node node, K key) {
        if (node == null) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = deleteRecursive(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = deleteRecursive(node.right, key);
        } else {
            if (node.count > 1) {
                node.count--;
            } else {
                if (node.left == null) {
                    deletionAdjust(node);
                    return node.right;
                } else if (node.right == null) {
                    deletionAdjust(node);
                    return node.left;
                }
                Node temp = findMinimumInRightSubTree(node.right);
                node.key = temp.key;
                node.value = temp.value;
                //deletionAdjust(node);
                node.right = deleteRecursive(node.right, temp.key);
            }
        }
        return node;
    }

    private void insertionAdjust(Node node) {
        node.color = RED;
        //Double Red Problems
        if (node != null && node != root && node.parent.color == RED) {
            //Try to recolor first (if appropriate)
            if (getSibling(node.parent) != null && getSibling(node.parent).color == RED) {
                node.parent.color = BLACK;
                getSibling(node.parent).color = BLACK;
                node.parent.parent.color = RED;
                insertionAdjust(node.parent.parent);
            }
            //If parent is a left child
            else if (node.parent == node.parent.parent.left) {
                //If node is a right child
                if (node == node.parent.right) {
                    rotateLeft(node.parent);
                }
                //If node is a right or left child
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);
            }
            //If parent is a right child
            else if (node.parent == node.parent.parent.right) {
                //If node is a left child
                if (node == node.parent.left) {
                    rotateRight(node.parent);
                }
                //If node is a right or a left child
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    private void deletionAdjust(Node node) {
        if (node.color == RED || node.right.color == RED) {
            node.color = BLACK;
        } else if (node.color == BLACK && (node.right.color == BLACK || node.right == null)) {
            if ((getSibling(node).color == BLACK || getSibling(node) == null) &&
                    (getSibling(node).left.color == RED || getSibling(node).right.color == RED)) {
                if (getSibling(node) == getSibling(node).parent.left) {
                    if (getSibling(node).left.color == BLACK) {

                    }
                }
            }
        }
    }

    private void rotateRight(Node node) {
        if (node.left == null) {
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
        oldLeft.right = node;
    }

    private void rotateLeft(Node node) {
        if (node.right == null) {
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
        oldRight.left = node;
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

    private Node find(Node node, K key) {
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
        Node temp = node;
        while (node.left != null) {
            temp = node.left;
            node = node.left;
        }
        return temp;
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

        private void increment() {
            count++;
        }

        private void decrement() {
            count--;
        }
    }

}
