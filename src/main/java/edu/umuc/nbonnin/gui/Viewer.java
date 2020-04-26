package edu.umuc.nbonnin.gui;


import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.RedBlackTree;
import edu.umuc.nbonnin.treesort.TreeFactory;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
 *      *****Viewer Class*****
 *
 * The Viewer class extends JPanel
 * The Viewer class overrides the paintComponent method to create the tree
 */
public class Viewer extends JPanel {

    private final int GRID_HEIGHT;
    private final Map<Node<? extends Comparable<?>, ?>, Point> cords = new HashMap<>();
    private final int GRID_WIDTH;
    JFrame viewerFrame;
    JScrollPane display;
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
     * height = max black nodes OR max red nodes AND max black nodes = max red nodes
     * therefore we know that the height of any RBTree can be defined as
     * h = log2(n+1)
     * ***************************************************************************************
     * We know that the more nodes there are, the more space those nodes need
     */
    public Viewer(String list) {
        tree = TreeFactory.newGenericTree(list);
        viewerFrame = new JFrame("Viewer");
        display = new JScrollPane(this);
        int count = getNodes();
        double maxHeight = 2 * (Math.log(count + 1) / Math.log(2));
        GRID_HEIGHT = 40;
        GRID_WIDTH = 40;
        int preferredHeight = (int) Math.ceil(maxHeight * GRID_HEIGHT * 0.5) + 120;
        int preferredWidth = (int) Math.ceil((count + 1) / 2.0 * GRID_WIDTH * 2.0) + 120;
        this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        updateTree(list);
    }

    public void paint(Graphics g) {
        this.removeAll();
        paintComponent(g);
        display.validate();
        viewerFrame.pack();
        viewerFrame.repaint();

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

    public void updateTree(String list) {
        display.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        display.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        display.setAutoscrolls(true);
        viewerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Releases the resources
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        viewerFrame.setPreferredSize(screenSize.getSize());  //Sets a preferred size
        viewerFrame.setLocationRelativeTo(null);    //Should places window centerish on the screen
        viewerFrame.getContentPane().add(display);
        display.setViewportView(this);
        viewerFrame.pack();
        viewerFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewerFrame.setVisible(true);
        StringBuilder temp = new StringBuilder();
        ArrayList<String> original = new ArrayList<>(Arrays.asList(list.split(" ")));
        LinkedHashSet<String> toConvert = new LinkedHashSet<>(original);
        ArrayList<String> toSplit = new ArrayList<>(toConvert);
        Timer timer = new Timer(1000, new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                viewerFrame.setTitle("Viewer -- Drawing Tree");
                if (i >= toSplit.size()) {
                    viewerFrame.setTitle("Viewer -- Completed Drawing");
                    return;
                }
                temp.append(toSplit.get(i)).append(" ");
                tree = TreeFactory.newGenericTree(temp.toString());
                validate();
                i++;
            }
        });
        timer.setInitialDelay(30000);
        timer.start();
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
