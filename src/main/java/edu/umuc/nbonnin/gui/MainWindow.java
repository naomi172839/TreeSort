package edu.umuc.nbonnin.gui;

import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.TreeFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

/*
 *          *****Main Window Class*****
 *
 * The MainWindow class defines the applications main window
 * This class not only creates the window, it also contains the action listeners for the buttons
 *
 * The MainWindow class require no arguments and no instance of the class needs to be saved
 *
 * Constructor: 0 Argument  -   Public constructor, calls the SHOW method from below
 *
 * Methods:     **Calls CREATEWINDOW and shows the window to the user**
 *              show            :   Arguments   :   None
 *                                  Returns     :   None
 *              **Calls all of the CREATExxx methods from below, creates each GUI component**
 *              createWindow    :   Arguments   :   None
 *                                  Returns     :   None
 *              **Creates all of the elements used by all of the GUI components**
 *              createElements  :   Arguments   :   None
 *                                  Returns     :   None
 *              **Creates the user input panel**
 *              createInputPanel:   Arguments   :   None
 *                                  Returns     :   None
 *              **Creates the button panel**
 *              createButtonPanel
 *                              :   Arguments   :   None
 *                                  Returns     :   None
 *              **Creates the panel to define the ordering**
 *              CreateOrderPanel:   Arguments   :   None
 *                                  Returns     :   None
 *              **Creates the object type panel**
 *              createTypePanel :   Arguments   :   None
 *                                  Returns     :   None
 *              **Called from the SORT, sorts the list based off of user supplied options
 *              getSorted       :   Arguments   :   None
 *                                  Returns     :   None
 *                                  Creates     :   JOptionPane     :   Displays an error message if the list is invalid
 *              **Called from the VIEW button, shows a graphical tree representation of the tree**
 *              showViewer      :   Arguments   :   None
 *                                  Returns     :   None
 *                                  Creates     :   JFrame          :   Displays a graphical tree, disposed on exit
 */

public class MainWindow {

    /*
     *      **Instance Variables**
     *
     * main         :   JFrame      -   The "main" JFrame which contains most of the GUI elements
     *
     * originalLabel:   JLabel      -   Labels the unordered list input
     * sortedLabel  :   JLabel      -   Labels the sorted list output
     * orderNormal  :   JLabel      -   Labels the ascending order sort radio button
     * orderReverse :   JLabel      -   Labels the descending order sort radio button
     * typeInt      :   JLabel      -   Labels the integer data type radio button
     * typeFrac     :   JLabel      -   Labels the fractional data type radio button
     * typeStudent  :   JLabel      -   Labels the student data type radio button
     * typeDetect   :   JLabel      -   Labels the detect data type radio button
     *
     * originalList :   JTextField  -   The textfield used for the user input
     * sortedList   :   JTextField  -   The textfield used for the output to the user
     *
     * sort         :   JButton     -   Button to get the sorted list and display it to the user
     * view         :   JButton     -   Button to get and show a graphical representation of the tree
     *
     * normal       :   JRadioButton-   Radiobutton for ascending sort
     * reverse      :   JRadioButton-   Radiobutton for descending sort
     * integer      :   JRadioButton-   Radiobutton for integer data type
     * fraction     :   JRadioButton-   Radiobutton for fraction data type
     * student      :   JRadioButton-   RadioButton for student data type
     * detect       :   JRadioButton-   RadioButton for detecting the data type
     *
     * order        :   ButtonGroup -   Group for the sort order; contains normal and reverse
     * type         :   ButtonGroup -   Group for the data type ; contains integer, fraction, student and detect
     *
     * input        :   JPanel      -   Panel to contain the input elements
     * buttons      :   JPanel      -   Panel to contain the button elements
     * orderGroup   :   JPanel      -   Panel to contain the sort order radio buttons
     * typeGroup    :   JPanel      -   Panel to contain the data type radio buttons
     */
    private JFrame main;
    private JLabel originalLabel, sortedLabel, orderNormal, orderReverse, typeInt, typeFrac, typeStudent, typeDetect;
    private JTextField originalList, sortedList;
    private JButton sort, view;
    private JRadioButton normal, reverse, integer, fraction, student, detect;
    private ButtonGroup order, type;    //Order could be local.  Included here for consistency
    private JPanel input, buttons, typeGroup, orderGroup;

    /*
     * 0 argument constructor
     * Calls the SHOW method
     * Creating a ManWindow object will display the GUI
     */
    public MainWindow() {
        show();
    }

    /*
     * Calls CREATEWINDOW and sets the main GUI to be visible
     * Sets the JFrame to exit the application on a close
     */
    private void show() {
        createWindow();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.pack();
        main.setVisible(true);
    }

    /*
     * Calls all of the CREATExxx methods
     * Adds all of the subpanels to the main JFrame
     *
     */
    private void createWindow() {
        main = new JFrame("Tree Sort Window");
        createElements();
        createInputPanel();
        createButtonPanel();
        createOrderPanel();
        createTypePanel();
        main.setLayout(new GridBagLayout());    //GridBag Layout is harder to work with but looks better when resized
        GridBagConstraints c = new GridBagConstraints();    //General constraint objects

        /*
         * Input Panel
         * Located in Row 1, Column 1
         * Takes up the entire row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);  //AKA padding
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(input, c);

        /*
         * Button Panel
         * Located in Row 2, Column 1
         * Takes up the entire row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5); //AKA padding
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(buttons, c);

        /*
         * Sort Order Panel
         * Located in Row 3, Column 1
         * Takes up half the Row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);    //AKA padding
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(orderGroup, c);

        /*
         * Data Type panel
         * Located in Row 3, Column 2
         * Takes up half the row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);    //AKA padding
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(typeGroup, c);

    }

    /*
     * Creates all of the elements to be used by the GUI
     *
     */
    private void createElements() {
        /*
         * Borders
         * No real use beyond making the GUI a little better looking
         */
        BasicBorders.FieldBorder fieldBorder = new BasicBorders.FieldBorder(    //For Fields
                Color.GRAY,
                Color.DARK_GRAY,
                Color.WHITE,
                Color.WHITE);
        BasicBorders.ButtonBorder buttonBorder = new BasicBorders.ButtonBorder( //For buttons
                Color.GRAY,
                Color.DARK_GRAY,
                Color.WHITE,
                Color.WHITE);
        /*
         * Labels
         *
         * NOTE: The RadioButtons can have a title and do not necessarily require a separate label
         *       I chose to keep the two elements separate so as to allow easier changes
         */
        originalLabel = new JLabel("Unsorted List");
        sortedLabel = new JLabel("Sorted List");
        orderNormal = new JLabel("Ascending");
        orderReverse = new JLabel("Descending");
        typeInt = new JLabel("Integer");
        typeFrac = new JLabel("Fraction");
        typeStudent = new JLabel("Student");
        typeDetect = new JLabel("Detect Type");
        /*
         * Text Fields
         */
        originalList = new JTextField();
        originalList.setBorder(fieldBorder);    //Sets the border to be slightly better looking
        originalList.setColumns(30);            //Defines the default number of columns
        sortedList = new JTextField();
        sortedList.setColumns(30);              //Defines the default number of columns
        sortedList.setBorder(fieldBorder);      //Sets the border to be slightly better looking
        sortedList.setEditable(false);          //Makes the textfield uneditable, used for output only
        sortedList.setBackground(new Color(222, 222, 222));       //Very light grey
        /*
         *
         */
        sort = new JButton("Sort");
        sort.setBorder(buttonBorder);       //Make the border slightly better looking
        sort.addActionListener(e -> getSorted());   //Tells Java to call the getSorted method when button is clicked
        view = new JButton("View Tree");
        view.setBorder(buttonBorder);       //Makes the border slightly better looking
        view.addActionListener(e -> showViewer());  //Tells Java to call the showViewer method when the button is clicked
        /*
         * Radio Buttons
         */
        normal = new JRadioButton();
        normal.setMnemonic(-1);     //Sets a numeric representation for the button, used in switch
        reverse = new JRadioButton();
        reverse.setMnemonic(-2);    //Sets a numeric representation for the button, used in switch
        integer = new JRadioButton();
        integer.setMnemonic(1);     //Sets a numeric representation for the button, used in switch
        fraction = new JRadioButton();
        fraction.setMnemonic(2);    //Sets a numeric representation for the button, used in switch
        student = new JRadioButton();
        student.setMnemonic(3);     //Sets a numeric representation for the button, used in switch
        detect = new JRadioButton();
        detect.setMnemonic(4);      //Sets a numeric representation for the button, used in switch
        /*
         * Button Groups
         *
         * Used to prevent multiple selected in same group
         * Allows the used of getSelected to prevent the need to check individual buttons
         */
        order = new ButtonGroup();
        order.add(normal);      //Adds to the group
        order.add(reverse);     //Adds to the group
        normal.setSelected(true);       //Default selection
        type = new ButtonGroup();
        type.add(integer);      //Adds to the group
        type.add(fraction);     //Adds to the group
        type.add(student);      //Adds to the group
        type.add(detect);       //Adds to the group
        detect.setSelected(true);      //Default selection
    }

    /*
     * Creates the Input pane
     * Adds input labels and objects to the panel
     */
    private void createInputPanel() {
        input = new JPanel();
        input.setLayout(new GridBagLayout());   //Allows for more complex layouts
        GridBagConstraints c = new GridBagConstraints();    //General constraints

        /*
         * Input Label
         * Located in Row 1, Column 1
         * Takes up left 1/4 ish of the row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 10);
        c.weightx = 0.0;
        c.weighty = 0.2;
        input.add(originalLabel, c);

        /*
         * Input TextField
         * Located in Row 1, Column 2
         * Takes up right 3/4ish of the row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 5;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 1.0;
        input.add(originalList, c);

        /*
         * Output Label
         * Located in Row 2, Column 1
         * Takes up left 1/4 ish of the row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.2;
        input.add(sortedLabel, c);

        /*
         * Output TextField
         * Located in Row 2, Column 2
         * Takes up right 3/4 ish of the row
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 5;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 1.0;
        input.add(sortedList, c);

    }

    /*
     * Creates the Button Panel
     *
     */
    private void createButtonPanel() {
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());     //GridBag allows more complex layouts, overkill here
        GridBagConstraints c = new GridBagConstraints();    //General constraint object

        /*
         * Sort button
         * Located in Row 1, Column 1
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.3;
        buttons.add(sort, c);

        /*
         * View button
         * Located in Row 1, Column 2
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.3;
        buttons.add(view, c);

    }

    /*
     * Creates the Panel containing the sort order choices
     */
    private void createOrderPanel() {
        orderGroup = new JPanel();
        orderGroup.setLayout(new GridBagLayout());  //GridBag allows more complex layouts
        orderGroup.setBorder(BorderFactory.createTitledBorder("Sort Order"));   //Allows a title around the group
        GridBagConstraints c = new GridBagConstraints();    //General constraint object

        /*
         * Ascending Label
         * Located in Row 1, Column 1
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        orderGroup.add(orderNormal, c);

        /*
         * Ascending RadioButton
         * Located in Row 1, Column 2
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        orderGroup.add(normal, c);

        /*
         * Descending Label
         * Located in Row 2, Column 1
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        orderGroup.add(orderReverse, c);

        /*
         * Descending RadioButton
         * Located in Row 2, Column 2
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        orderGroup.add(reverse, c);
    }

    /*
     * Creates the Panel containing the DataType objects
     */
    private void createTypePanel() {
        typeGroup = new JPanel();
        typeGroup.setLayout(new GridBagLayout());   //GridBag allows for more complex layouts
        typeGroup.setBorder(BorderFactory.createTitledBorder("Type"));  //Creates a titled border
        GridBagConstraints c = new GridBagConstraints();    //General Constraint object

        /*
         * Integer Type Label
         * Located in Row 1, Column 1
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeInt, c);

        /*
         * Integer RadioButton
         * Located in Row 1, Column 2
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(integer, c);

        /*
         * Fraction Label
         * Located in Row 2, Column 1
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeFrac, c);

        /*
         * Fraction RadioButton
         * Located in Row 2, Column 2
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(fraction, c);

        /*
         * Student Label
         * Located in Row 1, Column 3
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeStudent, c);

        /*
         * Student RadioButton
         * Located in Row 1, Column 4
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 5;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(student, c);

        /*
         * Detect Label
         * Located in Row 2, Column 3
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeDetect, c);

        /*
         * Detect RadioButton
         * Located in Row 2, Column 4
         *
         * NOTE: All of the GridBag constants are repeated due to the added clarity
         */
        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(detect, c);
    }

    /*
     * Gets the unsorted list, creates a tree, traverses it, and displays the result
     *
     * NOTE:    Ideally, I would have defined the visitor within this method/class rather than
     *          putting it in the RedBlackTree class.  I did not switch to using the visitor pattern
     *          until I decided to implement a viewer (I actually had never heard of it before that).
     *          because of that, I kept the logic where it originally was.
     *          Time permitting, I will fix this and you will never see this message.
     */
    private void getSorted() {
        String list = originalList.getText();   //Gets the unsorted list
        Node.Visitor visitor = null;            //Defines a blank visitor
        try {
            switch (type.getSelection().getMnemonic()) {    //Gets the datatype selection
                case (1):   //Integer
                    if (normal.isSelected()) {  //Normal order
                        sortedList.setText(TreeFactory.newIntegerTree(list).normalSort(visitor));
                    } else {    //Reverse order
                        sortedList.setText(TreeFactory.newIntegerTree(list).reverseSort(visitor));
                    }
                    break;
                case (2):   //Fraction
                    if (normal.isSelected()) {  //Normal order
                        sortedList.setText(TreeFactory.newFracTypeTree(list).normalSort(visitor));
                    } else {    //Reverse order
                        sortedList.setText(TreeFactory.newFracTypeTree(list).reverseSort(visitor));
                    }
                    break;
                case (3):   //Student type
                    /*
                     * Three choices to sort by.
                     * Technically, all of the fields are comparable and could be sorted but just the numeric
                     *      options are listed here.
                     * With more time, I would fix that and actually flush out the student class
                     */
                    String[] choices = new String[]{"Grade Level", "Student ID", "GPA"};    //Sort options
                    String key = (String) JOptionPane.showInputDialog(  //Displays popup with choices for the user
                            main,
                            "Please choose what to sort by",
                            "Sort By Choice",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            choices,
                            "Grade Level"
                    );
                    if (normal.isSelected()) {  //Normal order
                        sortedList.setText(TreeFactory.newStudentTree(list, key).normalSort(visitor));
                    } else {    //Reverse order
                        sortedList.setText(TreeFactory.newStudentTree(list, key).reverseSort(visitor));
                    }
                    break;
                /*
                 * The default case is also the default selection
                 * This also covers choice 4 as they are identical
                 */
                default:
                    if (normal.isSelected()) {  //Normal order
                        sortedList.setText(TreeFactory.newGenericTree(list).normalSort(visitor));
                    } else {    //Reverse order
                        sortedList.setText(TreeFactory.newGenericTree(list).reverseSort(visitor));
                    }
            }
        } catch (NumberFormatException e) { //Catches any issues in tree creation
            JOptionPane.showMessageDialog(main, e.getMessage());
        }

    }

    /*
     * Shows a graphical representation of the tree
     *
     * Will create a JOptionPane if there is an invalid tree
     * Will create a new JFrame showing the tree
     *
     * NOTE:    This is NOT perfect.
     *          It works really well for smaller trees
     *          Trees with more then 200 elements seem to cause issues with drawing
     *          Specifically, the issue appears to be that some parents are drawn with three children
     *              when the underlying tree has only 2.  This may also present as subtrees being drawn sideways
     *              This appears to be dependent on size of the window and the underlying grid.
     *              With more time, this would be generated dynamically but it is Sunday and the project is due
     *              in 20ish hours.
     *          If time permits, I will work more on this
     */
    private void showViewer() {
        try {
            new Viewer(originalList.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(main, e.getMessage());
        }
    }
}



