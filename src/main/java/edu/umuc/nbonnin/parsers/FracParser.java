package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.treesort.FracType;

import java.util.ArrayList;
import java.util.Arrays;

/*
 *      *****FracParser Class*****
 *
 * FracParser implements DataParser
 * FracParser is abstract and only its static methods can be used
 *
 * The FracParser class takes a space delimited string of rational numbers in the form 'x/y'
 * The FracParser class parses the string and returns an array of FracType objects
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
 *                              Returns     :   FracType[]  -   represents the list in object form
 *                              Throws      :   NumberFormatException   -   For invalid character
 */
public abstract class FracParser {

    /*
     * Convert a string into a IntType array
     *
     * Throws:  NumberFormatException   -   if there are non digit characters, except '-' and '/'
     */
    public static FracType[] parse(String list) {
        checkValid(list);
        ArrayList<String> toSplit = new ArrayList<>(Arrays.asList(list.split(" ")));
        toSplit.removeIf(s -> s.equals(""));
        ArrayList<FracType> split = new ArrayList<>();
        String[] temp;
        for (String token : toSplit) {
            if (!token.matches("(?:[-1-9][0-9]*|0)/[1-9][0-9]*")) {
                throw new NumberFormatException("(FracType) Invalid Expression: " + token + "\n");
            }
            temp = token.split("/");
            split.add(new FracType(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
        return split.toArray(new FracType[0]);
    }

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
}
