package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.datatypes.DataType;

/*
 *      *****DataParser Interface*****
 *
 * DataParser is a functional interface
 *
 * The DataParser interface is a functional interface that allows the user to define
 * a custom parse method at run time
 * This give significant flexibility when used in conjunction with GUI elements
 *
 * Methods:     **Abstract method to be implemented as needed**
 *              parseCustom :   Arguments   :   String  -   Represents the string to be parsed
 *                              Returns     :   DataType-   Array containing the appropriate types
 */
@FunctionalInterface
public interface DataParser {

    /*
     * Allows the functional implementation of a custom parsing method
     */
    DataType[] parseCustom(String list);

}
