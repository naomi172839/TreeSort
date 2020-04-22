package edu.umuc.nbonnin.parsers;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *      *****IntParser Class*****
 *
 * IntParser implements DataParser
 * IntParser is abstract and only its static methods can be used
 *
 * The IntParser class takes a space delimited string of integers
 * The IntParser class parses the string and returns an array of IntType objects
 * This array can be used by a Tree to create Nodes
 *
 * Constructor: 0 Argument  -   private constructor to prevent creation of IntParser objects
 *
 * Methods:     **Checks if the String is empty or null**
 *              checkValid  :   Arguments   :   String      -   represents the list of items to add
 *                              Returns     :   None
 *                              Throws      :   NumberFormatException   -   For null and empty strings
 *              **Turn the string into a IntType array**
 *              parse       :   Arguments   :   String      -   representing the list of items to add
 *                              Returns     :   IntType[]   -   represents the list in object form
 *                              Throws      :   NumberFormatException   -   For invalid character
 */
public abstract class IntParser {

    /*
     * 0 Argument Constructor
     * Private to prevent creation of objects
     */
    private IntParser() {
    }

    /*
     * Checks if a string is either null or empty
     */
    private static void checkValid(String list) {
        if (list == null) {
            throw new NumberFormatException("String is null");
        }
        if (list.equals("")) {
            throw new NumberFormatException("String is empty");
        }
    }

    /*
     * Convert a string into a IntType array
     *
     * Throws:  NumberFormatException   -   if there are non digit characters, except '-'
     */
    public static Integer[] parse(String list) {
        checkValid(list);
        ArrayList<String> toSplit = new ArrayList<>(Arrays.asList(list.split(" ")));
        toSplit.removeIf(s -> s.equals(""));
        ArrayList<Integer> split = new ArrayList<>();
        for (String token : toSplit) {
            if (!token.matches("[0-9-]+")) {
                throw new NumberFormatException("(Integer) Invalid Expression: " + token + "\n");
            }
            split.add(Integer.parseInt(token));
        }
        return split.toArray(new Integer[0]);
    }
}
