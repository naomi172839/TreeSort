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
 *
 * TODO: Add in the methods comments above the class
 */
public class Viewer extends JPanel {

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
     * viewerFrame
     *          :   JFrame                      -   The Window to contain the viewer application
     *
     */

    private final Map<Node<? extends Comparable<?>, ?>, Point> cords = new HashMap<>();

    private final int GRID_WIDTH;
    private final int GRID_HEIGHT;

    private final JFrame viewerFrame;

    private final JScrollPane display;

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
     * We also know that this application uses the following amount of grids:
     * Height:  (1/2) * maxHeight
     * Weight:  2 * maxLeafNodes
     *
     * We add a padding of 120px on either side to account for larger then normal nodes
     * ***************************************************************************************
     */
    public Viewer(String list) {
        GRID_HEIGHT = 40;
        GRID_WIDTH = 40;
        tree = TreeFactory.newGenericTree(list);    //Creates a tree with all of the elements, allows the initial view
        viewerFrame = new JFrame("Viewer");    //Creates the Frame
        display = new JScrollPane(this);      //Adds this to a ScrollPane as the image can get huge
        int count = getNodes();                     //Gets the total number of nodes in the tree
        double maxHeight = 2 * (Math.log(count + 1) / Math.log(2)); //See above
        int preferredHeight = (int) Math.ceil(maxHeight * GRID_HEIGHT * 0.5) + 120; //See above
        double maxLeafNodes = (count + 1) / 2.0;    //See above
        int preferredWidth = (int) Math.ceil((maxLeafNodes) * GRID_WIDTH * 2.0) + 120;  //See above
        this.setPreferredSize(new Dimension(preferredWidth, preferredHeight));  //Sets the dimensions of the Panel
        updateTree(list);   //Calls the update methodTree method which animates the tree creation
    }

    /*
     * Overrides the paint method to ensure that the frame is always displaying the right information
     */
    public void paint(Graphics g) {
        this.removeAll();   //Probably not necessary, but in here to be safe
        paintComponent(g);  //Adds the components in the proper place
        display.validate(); //Validates the call
        viewerFrame.pack(); //Repacks the frame, also calls repaint
        viewerFrame.repaint();  //Explicit repaint call to be safe
    }

    /*
     * Helper method to get the final node count in the full tree
     */
    private int getNodes() {
        final int[] count = {0};    //Stored as an array so that the abstract method can access and change it
        /*
         * Traverses the tree in order and uses the defined visitor to count the nodes
         */
        tree.inOrder(tree.getRoot(), new Node.Visitor() {
            @Override
            public <K extends Comparable<K>, V> void visit(Node<K, V> node) {
                count[0]++;
            }
        });
        return count[0];
    }

    /*
     * Overrides the paintComponent method
     * Creates and adds all of the components to the JPane
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //Super constructor since the overall pane is not changing
        if (tree.getRoot() == null) { //Catches all of the null pointer exceptions hopefully
            return;
        }
        /*
         * Traverses the tree in order with the defined visitor
         * Going in order, the cordinates are calculated for each node as follows:
         * x = x+GRID_WIDTH
         * y = GRID_HEIGHT*1+ the depth of the node
         */
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

        /*
         * Traverses the tree in post order with the defined visitor
         * Going in post order, the nodes and lines are drawn as below
         */
        tree.postOrder(tree.getRoot(), new Node.Visitor() {
            @Override
            public <K extends Comparable<K>, V> void visit(Node<K, V> node) {
                String value = node.getValue().toString(); //Gets the name of the node
                Point current = cords.get(node);    //Local variable for the coordinates of the current node
                if (node.getParent() != null) { //If the node has a parent, i.e. if it is not the root
                    Point parent = cords.get(node.getParent()); //Get the location of the parent node
                    g.setColor(Color.black);
                    g.drawLine(current.x, current.y, parent.x, parent.y); //Draws a line between the two points
                }
                FontMetrics fm = g.getFontMetrics();    //Gets the font information being used
                Rectangle r = fm.getStringBounds(value, g).getBounds(); //Gets the bounds of the string
                r.setLocation(current.x - r.width / 2, current.y - r.height / 2); //Sets the x/y position of the text
                Color nodeColor = getNodeColor(node);
                Color textColor;
                if (nodeColor.getRed() + nodeColor.getGreen() + nodeColor.getBlue() > 380) { //Per google, this is the cutoff
                    textColor = Color.black;
                } else {
                    textColor = Color.white;
                }
                g.setColor(nodeColor); //Gets the nodes color
                g.fillRect(r.x - 2, r.y - 2, r.width + 4, r.height + 4); //Fills the node
                g.setColor(textColor);
                g.drawString(value, r.x, r.y + r.height); //Draws the string
            }
        });
    }

    /*
     * The meat and potatoes of the class
     * Update will initially display the entire tree
     * It will then animate each point being added in the original order
     *
     * NOTE:    This is not done efficiently
     *          The entire tree is recreated each time rather than using the existing tree
     */
    private void updateTree(String list) {
        display.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        display.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        display.setAutoscrolls(true);   //As needed, autoscroll
        viewerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Releases the resources
        Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();  //Screen size
        viewerFrame.setPreferredSize(screenSize.getSize());  //Sets a preferred size
        viewerFrame.setLocationRelativeTo(null);    //Should places window centerish on the screen
        viewerFrame.getContentPane().add(display);  //Adds the ScrollPane to the JFrame
        display.setViewportView(this);  //Set the viewport to be on the JPanel with the tree
        display.getViewport().setViewPosition(new Point(this.getPreferredSize().width / 2 - 120, 0)); //Start at root
        viewerFrame.pack(); //Make sure everything is laid out right
        viewerFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Fill the entire screen, leave the close button
        viewerFrame.setVisible(true);   //Shows it to the user
        StringBuilder temp = new StringBuilder();   //Used for the animation
        /*
         * Below is a very inefficient way to remove any duplicates from the list
         * It is fast and easy to do though and is a placeholder until I have time to flushout a better method
         */
        ArrayList<String> original = new ArrayList<>(Arrays.asList(list.split(" ")));
        LinkedHashSet<String> toConvert = new LinkedHashSet<>(original);
        ArrayList<String> toSplit = new ArrayList<>(toConvert);
        /*
         * Defines a Swing Timer object used to animate the tree
         * Delay time of 750ms
         */
        Timer timer = new Timer(750, new ActionListener() {
            int i = 0;  //Used to get the next element

            @Override
            public void actionPerformed(ActionEvent e) {
                viewerFrame.setTitle("Viewer -- Drawing Tree");
                if (i >= toSplit.size()) {  //Prevents the null pointer exception
                    viewerFrame.setTitle("Viewer -- Completed Drawing");
                    return;
                }
                temp.append(toSplit.get(i)).append(" ");    //Adds the next character to the string
                tree = TreeFactory.newGenericTree(temp.toString()); //Recreates the tree
                validate(); //Forces the redraw of the panel
                i++;
            }
        });
        timer.setInitialDelay(15000);   //Shows the complete tree for 15 seconds
        timer.start();  //Starts the timer loop
    }

    /*
     * Helper method to calculate the depth of any given node
     * Recursive method that simply counts the path until hitting a leaf node
     */
    private int depth(Node<? extends Comparable<?>, ?> node) {
        if (node.getParent() == null) {
            return 0;
        } else {
            return 1 + depth(node.getParent());
        }
    }

    /*
     * Helper method to get a nodes color as a actual color
     */
    private Color getNodeColor(Node<? extends Comparable<?>, ?> node) {
        if (node.getColor()) {  //If red
            return Color.red;
        } else {    //If black
            return Color.black;
        }
    }

}
