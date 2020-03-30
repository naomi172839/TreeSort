package edu.umuc.nbonnin.datatypes;

/*
 *          *****IntType Class*****
 *
 * IntType implements DataType
 * IntType is comparable
 * IntType is serializable
 *
 * The IntType class defines an integer to be used by the Tree Nodes
 * The IntType class exists to ensure that all Nodes have compatible types
 * The IntType class does not define any math method
 *
 * Variables:   value       :   Integer     -   Represents the value of the object
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              1 Argument  -   int         -   Represents the value of the object
 *
 * Methods:     **Gets the value of the object**
 *              getValue    :   Arguments   :   None
 *                              Returns     :   Integer -   Represents the value of the object
 *
 *              **Sets the value of the object**
 *              setValue    :   Arguments   :   Integer -   Represents the value of the object
 *                              Returns     :   None
 *
 *              **Gets a string representation of the object**
 *              toString    :   Arguments   :   None
 *                              Returns     :   String  -   String representation of the object
 *
 *              **Compares this object to another**
 *              compareTo   :   Arguments   :   IntType -   Represents the object to compare this to
 *                              Returns     :   int     -   < 0 : this is smaller
 *                                                          = 0 : this is equal
 *                                                          > 0 : this is larger
 */
public class IntType implements DataType {

    /*
     *      *****Instance Variables*****
     *
     * value    :   Integer -   Represents the value of the object
     */
    private Integer value;

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public IntType() {
        throw new IllegalArgumentException("You must provide a value");
    }

    /*
     * 1 Argument constructor
     * Create a new IntType object
     */
    public IntType(int value) {
        this.value = value;
    }

    /*
     * Getter for value
     */
    public Integer getValue() {
        return value;
    }

    /*
     * Setter for value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /*
     * Gets a string representation of the object
     *
     * Implements:  DataType.toString()
     * Overrides:   Object.toString()
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /*
     * Compares this object to another of the same type
     * Cheats and uses the Integer.compareTo method
     *
     * returns  int < 0 if  this is smaller
     *          int = 0 if  this is equal
     *          int > 0 if  this is larger
     */
    public int compareToInt(IntType anotherIntType) {
        return value.compareTo(anotherIntType.getValue());
    }

    /*
     * Compares this object to another of the same inherited type
     * Cheats and uses the Integer.compareTo method
     *
     * returns  int < 0 if  this is smaller
     *          int = 0 if  this is equal
     *          int > 0 if  this is larger
     *
     * @exception   -   IllegalArgumentException    -    class mismatch
     */
    public int compareTo(DataType anotherDataType) {
        if (!anotherDataType.getClass().equals(this.getClass())) {
            throw new IllegalArgumentException("Can not compare two different DataTypes");
        }
        return compareToInt((IntType) anotherDataType);
    }
}
