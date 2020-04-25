package edu.umuc.nbonnin.gui;


import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.RedBlackTree;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class Viewer extends JPanel {

    private final RedBlackTree<? extends Comparable<?>, ?> tree;
    private final Map<Node<? extends Comparable<?>, ?>, Point> cords = new HashMap<>();
    private final int GRID_WIDTH = 40;
    private final int GRID_HEIGHT = 40;

    public Viewer(RedBlackTree<?, ?> tree) {
        this.tree = tree;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.getRoot() == null) {
            return;
        }
        tree.inOrder(tree.getRoot(), new Node.Visitor() {
            private int x = GRID_WIDTH;

            @Override
            public <K extends Comparable<K>, V> void visit(Node<K, V> node) {
                cords.put(node, new Point(x, GRID_HEIGHT * (depth(node) + 1)));
                x += GRID_WIDTH;
            }
        });

        tree.postOrder(tree.getRoot(), new Node.Visitor() {
            @Override
            public <K extends Comparable<K>, V> void visit(Node<K, V> node) {
                String value = node.getValue().toString();
                Point current = cords.get(node);
                if (node.getParent() != null) {
                    Point parent = cords.get(node.getParent());
                    g.setColor(Color.black);
                    g.drawLine(current.x, current.y, parent.x, parent.y);
                }
                FontMetrics fm = g.getFontMetrics();
                Rectangle r = fm.getStringBounds(value, g).getBounds();
                r.setLocation(current.x - r.width / 2, current.y - r.height / 2);
                Color nodeColor = getNodeColor(node);
                Color textColor;
                if (nodeColor.getRed() + nodeColor.getGreen() + nodeColor.getBlue() > 380) {
                    textColor = Color.black;
                } else {
                    textColor = Color.white;
                }
                g.setColor(nodeColor);
                g.fillRect(r.x - 2, r.y - 2, r.width + 4, r.height + 4);
                g.setColor(textColor);
                g.drawString(value, r.x, r.y + r.height);
            }
        });
    }

    private int depth(Node<? extends Comparable<?>, ?> node) {
        if (node.getParent() == null) {
            return 0;
        } else {
            return 1 + depth(node.getParent());
        }
    }

    private Color getNodeColor(Node<? extends Comparable<?>, ?> node) {
        if (node.getColor()) {
            return Color.red;
        } else {
            return Color.black;
        }
    }


}
