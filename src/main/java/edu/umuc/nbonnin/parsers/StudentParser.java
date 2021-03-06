package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.treesort.Student;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *      *****StudentParser Class*****
 *
 * StudentParser implements DataParser
 * StudentParser is abstract and only its static methods can be used
 *
 * The StudentParser class takes a space delimited string of rational numbers in the below form
 * lastName,firstName,grade,studentID,gpa ...
 * The StudentParser class parses the string and returns an array of Student objects
 * This array can be used by a Tree to create Nodes
 *
 * NOTE:    This class is literally a skeleton
 *          It is included as a proof of concept rather than an actual real class
 *
 *
 * Constructor: 0 Argument  -   private constructor to prevent creation of IntParser objects
 *
 * Methods:     **Checks if the String is empty or null**
 *              checkValid  :   Arguments   :   String      -   represents the list of items to add
 *                              Returns     :   None
 *                              Throws      :   NumberFormatException   -   For null and empty strings
 *              **Turn the string into a IntType array**
 *              parse       :   Arguments   :   String      -   representing the list of items to add
 *                              Returns     :   Student[]  -   represents the list in object form
 *                              Throws      :   NumberFormatException   -   For invalid character
 */
public class StudentParser {

    /*
     * Checks if a string is either null or empty
     */
    private static void checkValid(String list) {
        if (list == null) {
            throw new NumberFormatException("List can not be null");
        }
        if (list.equals("")) {
            throw new NumberFormatException("List can not be empty");
        }
    }

    /*
     * Convert a string into a IntType array
     *
     * Throws:  NumberFormatException   -   if there are not enough strings or they are of the wrong type
     */
    public static Student[] parse(String list) {
        String lastName, firstName;
        int grade, studentID;
        double gpa;
        checkValid(list); //Ensures that the list is actually real
        ArrayList<String> toSplit = new ArrayList<>(Arrays.asList(list.split(" ")));
        toSplit.removeIf(s -> s.equals("")); //Removes empty characters
        ArrayList<Student> split = new ArrayList<>(); //Contains the final tokens
        for (String token : toSplit) {
            ArrayList<String> secondSplit = new ArrayList<>(Arrays.asList(token.split(",")));
            if (secondSplit.size() != 5) {
                throw new NumberFormatException("(Student) Illegal Expression: " + token);
            }
            lastName = secondSplit.get(0);
            firstName = secondSplit.get(1);
            grade = Integer.parseInt(secondSplit.get(2));
            studentID = Integer.parseInt(secondSplit.get(3));
            gpa = Double.parseDouble(secondSplit.get(4));
            split.add(new Student( //Creates a new student and adds it
                    lastName,
                    firstName,
                    grade,
                    studentID,
                    gpa
            ));
        }
        return split.toArray(new Student[0]);
    }


}
