package edu.umuc.nbonnin.gui;

import edu.umuc.nbonnin.treesort.Node;
import edu.umuc.nbonnin.treesort.RedBlackTree;
import edu.umuc.nbonnin.treesort.TreeFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class MainWindow {

    private JFrame main;
    private JLabel originalLabel, sortedLabel, orderNormal, orderReverse, typeInt, typeFrac, typeStudent, typeDetect;
    private JTextField originalList, sortedList;
    private JButton sort, view;
    private JRadioButton normal, reverse, integer, fraction, student, detect;
    private ButtonGroup order, type;
    private JPanel input, buttons, typeGroup, orderGroup;

    public MainWindow() {
        show();
    }

    private void show() {
        createWindow();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.pack();
        main.setVisible(true);
    }

    private void createWindow() {
        main = new JFrame("Tree Sort Window");
        createElements();
        createInputPanel();
        createButtonPanel();
        createOrderPanel();
        createTypePanel();
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Row 1, Item 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(input, c);

        //Row 2, Item 1
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(buttons, c);

        //Row 3, Item 1
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(orderGroup, c);

        //Row 3, Item 1
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        main.add(typeGroup, c);

    }

    private void createElements() {
        //Borders
        BasicBorders.FieldBorder fieldBorder = new BasicBorders.FieldBorder(
                Color.GRAY,
                Color.DARK_GRAY,
                Color.WHITE,
                Color.WHITE);
        BasicBorders.ButtonBorder buttonBorder = new BasicBorders.ButtonBorder(
                Color.GRAY,
                Color.DARK_GRAY,
                Color.WHITE,
                Color.WHITE);
        BasicBorders.MarginBorder marginBorder = new BasicBorders.MarginBorder();
        //Labels
        originalLabel = new JLabel("Unsorted List");
        sortedLabel = new JLabel("Sorted List");
        orderNormal = new JLabel("Ascending");
        orderReverse = new JLabel("Descending");
        typeInt = new JLabel("Integer");
        typeFrac = new JLabel("Fraction");
        typeStudent = new JLabel("Student");
        typeDetect = new JLabel("Detect Type");
        //Text Fields
        originalList = new JTextField();
        originalList.setBorder(fieldBorder);
        originalList.setColumns(30);
        sortedList = new JTextField();
        sortedList.setColumns(30);
        sortedList.setBorder(fieldBorder);
        sortedList.setEditable(false);
        sortedList.setBackground(new Color(222, 222, 222));       //Very light grey
        //Buttons
        sort = new JButton("Sort");
        sort.setBorder(buttonBorder);
        sort.addActionListener(e -> getSorted());
        view = new JButton("View Tree");
        view.setBorder(buttonBorder);
        view.addActionListener(e -> showViewer());
        //Radio Buttons
        normal = new JRadioButton();
        normal.setMnemonic(-1);
        reverse = new JRadioButton();
        reverse.setMnemonic(-2);
        integer = new JRadioButton();
        integer.setMnemonic(1);
        fraction = new JRadioButton();
        fraction.setMnemonic(2);
        student = new JRadioButton();
        student.setMnemonic(3);
        detect = new JRadioButton();
        detect.setMnemonic(4);
        //Button Groups
        order = new ButtonGroup();
        order.add(normal);
        order.add(reverse);
        normal.setSelected(true);       //Default selection
        type = new ButtonGroup();
        type.add(integer);
        type.add(fraction);
        type.add(student);
        type.add(detect);
        detect.setSelected(true);      //Default selection
    }

    private void showViewer() {
        try {
            RedBlackTree<?, ?> tree = TreeFactory.newGenericTree(originalList.getText());
            Viewer view = new Viewer(tree);
            view.setSize(1024, 512);
            JFrame viewerFrame = new JFrame("Viewer");
            JScrollPane display = new JScrollPane();
            display.setViewportView(view);
            display.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            display.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            viewerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewerFrame.setSize(new Dimension(400, 600));
            viewerFrame.setPreferredSize(new Dimension(768, 512));
            viewerFrame.setLocationRelativeTo(null);
            viewerFrame.add(display);
            viewerFrame.pack();
            viewerFrame.setVisible(true);

            //view.changeTree(tree.getRoot());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(main, e.getMessage());
        }
    }

    private void createInputPanel() {
        input = new JPanel();
        input.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Row 1, Item 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 10);
        c.weightx = 0.0;
        c.weighty = 0.2;
        input.add(originalLabel, c);

        //Row 1, Item 2
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 5;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 1.0;
        input.add(originalList, c);

        //Row 2, Item 1
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.2;
        input.add(sortedLabel, c);

        //Row 2, Item 2
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

    private void createButtonPanel() {
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Row 1, Item 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.3;
        buttons.add(sort, c);

        //Row 1, Item 2
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 15, 10, 15);
        c.weightx = 0.0;
        c.weighty = 0.3;
        buttons.add(view, c);

    }

    private void createOrderPanel() {
        orderGroup = new JPanel();
        orderGroup.setLayout(new GridBagLayout());
        orderGroup.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        GridBagConstraints c = new GridBagConstraints();

        //Row 1, Item 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        orderGroup.add(orderNormal, c);

        //Row 1, Item 2
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        orderGroup.add(normal, c);

        //Row 2, Item 1
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        orderGroup.add(orderReverse, c);

        //Row 2, Item 2
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        orderGroup.add(reverse, c);
    }

    private void createTypePanel() {
        typeGroup = new JPanel();
        typeGroup.setLayout(new GridBagLayout());
        typeGroup.setBorder(BorderFactory.createTitledBorder("Type"));
        GridBagConstraints c = new GridBagConstraints();

        //Row 1, Item 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeInt, c);

        //Row 1, Item 2
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(integer, c);

        //Row 2, Item 1
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeFrac, c);

        //Row 2, Item 2
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(fraction, c);

        //Row 1, Item 3
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeStudent, c);

        //Row 1, Item 4
        c.gridx = 5;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(student, c);

        //Row 2, Item 3
        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.2;
        typeGroup.add(typeDetect, c);

        //Row 2, Item 3
        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 10, 10, 5);
        c.weightx = 0.0;
        c.weighty = 0.5;
        typeGroup.add(detect, c);
    }

    private void getSorted() {
        String list = originalList.getText();
        Node.Visitor visitor = null;
        try {
            switch (type.getSelection().getMnemonic()) {
                case (1):
                    if (normal.isSelected()) {
                        sortedList.setText(TreeFactory.newIntegerTree(list).normalSort(visitor));
                    } else {
                        sortedList.setText(TreeFactory.newIntegerTree(list).reverseSort(visitor));
                    }
                    break;
                case (2):
                    if (normal.isSelected()) {
                        sortedList.setText(TreeFactory.newFracTypeTree(list).normalSort(visitor));
                    } else {
                        sortedList.setText(TreeFactory.newFracTypeTree(list).reverseSort(visitor));
                    }
                    break;
                case (3):
                    String[] choices = new String[]{"Grade Level", "Student ID", "GPA"};
                    String key = (String) JOptionPane.showInputDialog(
                            main,
                            "Please choose what to sort by",
                            "Sort By Choice",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            choices,
                            "Grade Level"
                    );
                    if (normal.isSelected()) {
                        sortedList.setText(TreeFactory.newStudentTree(list, key).normalSort(visitor));
                    } else {
                        sortedList.setText(TreeFactory.newStudentTree(list, key).reverseSort(visitor));
                    }
                    break;
                //Covers case 4
                default:
                    if (normal.isSelected()) {
                        sortedList.setText(TreeFactory.newGenericTree(list).normalSort(visitor));
                    } else {
                        sortedList.setText(TreeFactory.newGenericTree(list).reverseSort(visitor));
                    }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(main, e.getMessage());
        }

    }
}


