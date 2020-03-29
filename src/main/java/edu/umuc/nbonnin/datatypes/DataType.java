package edu.umuc.nbonnin.datatypes;

/*
 *          *****DataType Interface*****
 *
 * DataType extends Comparable with type DataType
 * DataType extends java.io.Serializable
 *
 * DataType objects should be JavaBean and POJO compliant, even if it results in boilerplate
 *
 * The DataType interface defines the methods that a DataType object MUST implement
 * Objects implementing DataType are used within the node and tree packages
 * DataType ensures that there is some natural order so that search trees can be implemented
 * DataType allows the abstraction of the implementation of each DataType object
 * This abstraction prevents errors when DataType objects are updated
 *
 * Methods: toString:       Arguments - None
 *                          Returns -   String
 *          compareTo:      Arguments - DataType    -   Represents the object to compare this object to
 *                          Returns -   int         -   < 0 : this is smaller
 *                                                      = 0 : this is equal
 *                                                      > 0 : this is larger
 *
 * Note:    This interface is mainly to enforce that all objects implementing it are serializable
 *          This is important as other packages require that the object be serializable
 *          As a result, wrapper classes, such as IntType, are necessary
 *          ALL objects implementing this interface should also implement Comparable
 *
 */
public interface DataType extends Comparable<DataType>, java.io.Serializable {

    /*
     * toString ensures that any object used by the tree nodes can be converted to a string
     * Arguments    -   None
     * Returns      -   String representing the object
     */
    String toString();

    /*
     * Compares this object to another
     * Default implementation prevents code duplication
     *
     * Implements:  DataType.compareTo(DataType anotherDataType)
     *
     * @exception   -   IllegalArgumentException
     *
     * Note:    This method should never be called as comparing objects of different types should not be compared
     *          It is implemented so that the exception can be caught by the user
     *          It also allows for comparing of different types of objects in the future
     */
    default int compareTo(DataType anotherDataType) {
        if (!anotherDataType.getClass().equals(this.getClass())) {
            throw new IllegalArgumentException("Can not compare two different DataTypes");
        }
        return 0;
    }
}
