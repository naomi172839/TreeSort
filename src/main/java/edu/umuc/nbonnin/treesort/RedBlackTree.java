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
        deleteFromSubtree(root, key);
    }

    private void deleteFromSubtree(Node node, K key) {
        node = find(node, key);
        if (node != null) {
            if (node.count > 1) {
                node.count--;
            } else {
                //If node has 2 children
                if (node.left != null && node.right != null) {
                    Node temp = findMinimumInRightSubTree(node.right);
                    node.key = temp.key;
                    node.value = temp.value;
                    node = temp;
                }
                //Now we are operating on a node with no or one child
                Node end;
                //Sets our end node to the opposite child if one child is null or null if both are null
                if (node.left == null) {
                    end = node.right;
                } else {
                    end = node.left;
                }
                //Only for cases in which there was an actual child
                if (end != null) {
                    //For trees with only two nodes
                    if (node == root) {
                        end.parent = null;
                        root = end;
                    }
                    //If node is a left child, replace node with end node
                    else if (node == node.parent.left) {
                        end.parent = node.parent;
                        node.parent.left = end;
                    }
                    //If node is a right child, replace node with end node
                    else {
                        end.parent = node.parent;
                        node.parent.right = end;
                    }
                    //Fixes double black problem
                    if (node.color == BLACK) {
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
                    if (node.color == BLACK) {
                        //Adjust BEFORE making the node null
                        deletionAdjust(node);
                    }
                    //Removes the node from its parent (deleting it)
                    if (node == node.parent.left) {
                        node.parent.left = end;
                    } else {
                        node.parent.right = end;
                    }
                }
            }
        }
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
        //Ensures that balancing will happen until tree is balanced
        while (node != root && node.color == BLACK) {
            //If node is a left child
            if (node == node.parent.left) {
                Node sibling = node.parent.right;
                //If nodes sibling is red (and also not null)
                if (sibling != null && sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                //If siblings children are both black (or null) after rotation
                assert sibling != null;
                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.right == null || sibling.right.color == BLACK) {
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            }
            //If node is a right child
            else {
                Node sibling = node.parent.left;
                if (sibling != null && sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }
                assert sibling != null;
                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.left == null || sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }

            }
            node.color = BLACK;
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
        oldLeft.parent = node.parent;
        oldLeft.right = node;
        node.parent = oldLeft;
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
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
