package edu.umuc.nbonnin.gui;

import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.RedBlackTree;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Viewer extends JFrame {
    private final int gridWidth;
    private final int gridHeight;
    private final Map<Node<?, ?>, Point> coordinates = new HashMap<>();
    private final RedBlackTree<?, ?> tree;
    JFrame viewer = new JFrame("Tree Viewer");
    JTextField valueField = new JTextField(80);
    JPanel buttons = new JPanel();
    private Node<?, ?> root;

    public Viewer(RedBlackTree<?, ?> tree, int width, int height) {
        this.tree = tree;
        this.root = tree.getRoot();
        this.gridWidth = width;
        this.gridHeight = height;

    }

    public void changeTree(Node<?, ?> root) {
        this.root = root;
        repaint();
    }

    public void paintComponent(final Graphics g) {
        super.paintComponents(g);

        if (root == null) {
            return;
        }
    }

}
