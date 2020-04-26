package edu.umuc.nbonnin.gui;


import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.RedBlackTree;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/*
 *      *****Viewer Class*****
 *
 * The Viewer class extends JPanel
 * The Viewer class overrides the paintComponent method to create the tree
 */
public class Viewer extends JPanel {

    private final int GRID_HEIGHT = 40;
    private final Map<Node<? extends Comparable<?>, ?>, Point> cords = new HashMap<>();
    private final int GRID_WIDTH = 40;
    JFrame viewerFrame;
    JScrollPane display;
    Thread T;
    /*
     *  ***Instance Variables***
     *
     * tree     :   RedBlackTree<?,?>           -   Stores the tree for use in all methods
     *
     * cords    :   HashMap<Node<?,?>, Point>   -   Stores the coordinates of the each node
     *
     * GRID_WIDTH
     *          :   int                         -   How far apart each node should be horizontally
     * GRID_HEIGHT
     *          :   int                         -   How far apart each node should be vertically
     *
     */
    private RedBlackTree<? extends Comparable<?>, ?> tree;

    /*
     * 1 Argument Constructor
     *
     * Sets all of the variables to be used by the internal methods
     *
     * A note on some of the math:
     *
     * ***************************************************************************************
     * We know that a complete Binary Tree has the relationship:
     * n=2^(k+1)-1 where n=nodes and k=maximum depth of black nodes
     * Which means that:
     * k = log2(n+1)-1
     * We also know that the total number of red nodes is constrained by the number of black nodes
     * Which means we know that:
     * height = max black nodes + max red nodes AND max black nodes = max red nodes
     * therefore we know that the height of any RBTree can be defined as
     * h = 2*log2(n+1)
     * ***************************************************************************************
     * We know that the more nodes there are, the more space those nodes need
     */
    public Viewer(RedBlackTree<?, ?> tree) {
        viewerFrame = new JFrame("Viewer");
        display = new JScrollPane(this);
        this.tree = tree;
        if (this.tree == null) {
            return;
        }
        int count = getNodes();
        double maxHeight = 2 * (Math.log(count + 1) / Math.log(2));
        int preferredHeight = (int) (maxHeight * (GRID_HEIGHT * 1.5));
        int preferredWidth = (int) (maxHeight * preferredHeight * 0.75);
        this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        T = new Thread();
        display.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        display.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        display.setAutoscrolls(true);
        viewerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Releases the resources
        viewerFrame.setSize(new Dimension(400, 600));       //Sets the underlying frames size
        viewerFrame.setPreferredSize(new Dimension(768, 512));  //Sets a preferred size
        viewerFrame.setLocationRelativeTo(null);    //Should places window centerish on the screen
        viewerFrame.add(display);
        display.setViewportView(this);
        viewerFrame.pack();
        viewerFrame.setVisible(true);
    }

    private int getNodes() {
        final int[] count = {0};
        tree.inOrder(tree.getRoot(), new Node.Visitor() {
            @Override
            public <K extends Comparable<K>, V> void visit(Node<K, V> node) {
                count[0]++;
            }
        });
        return count[0];
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
                if (cords.containsKey(node)) {
                    return;
                }
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

    public void update(RedBlackTree<?, ?> tree) throws InvocationTargetException, InterruptedException {
        System.out.println("Update");
        SwingUtilities.invokeLater(() -> {
            setTree(tree);
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
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

    public void setTree(RedBlackTree<?, ?> tree) {
        this.tree = tree;
    }


}
