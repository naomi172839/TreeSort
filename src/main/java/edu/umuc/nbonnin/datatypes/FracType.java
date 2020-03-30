package edu.umuc.nbonnin.datatypes;

/*
 *          *****FracType Class*****
 *
 * FracType implements DataType
 * FracType is comparable
 * FracType is serializable
 *
 * The FracType class defines an rational number to be used by the Tree Nodes
 * The FracType class exists to ensure that all Nodes have compatible types
 * The FracType class does not define any math method
 * The FracType stores the negative in the numerator
 *
 * Variables:   value       :   double      -   Represents the value of the object
 *              numerator   :   int         -   Represents the numerator of the rational number
 *              denominator :   int         -   Represents the denominator of the rational number
 *
 * Constructor: 0 Argument  -   Throws IllegalArgumentException
 *              2 Argument  -   int         -   Represents the value of the numerator
 *                              int         -   Represents the value of the denominator
 *
 * Methods:     **Gets the value of the object**
 *              getValue    :   Arguments   :   None
 *                              Returns     :   Double  -   Represents the value of the object
 *
 *              **Sets the value of the object**
 *              setValue    :   Arguments   :   Double  -   Represents the value of the object
 *                              Returns     :   None
 *
 *              **Gets the value of the value**
 *              getNumerator:   Arguments   :   None
 *                              Returns     :   Double  -   Represents the value of the Numerator
 *
 *              **Sets the value of the numerator**
 *              setNumerator:   Arguments   :   int     -   Represents the value of the numerator
 *                              Returns     :   None
 *
 *              **Gets the value of the value**
 *              getDenominator: Arguments   :   None
 *                              Returns     :   Double  -   Represents the value of the Denominator
 *
 *              **Sets the value of the numerator**
 *              setDenominator: Arguments   :   int     -   Represents the value of the numerator
 *                              Returns     :   None
 *
 *              **Gets a string representation of the object**
 *              toString    :   Arguments   :   None
 *                              Returns     :   String  -   String representation of the object
 *
 *              **Gets a string representation of the reduced fraction**
 *             -toSimplifiedString
 *                          :   Arguments   :   None
 *                              Returns     :   String  -   String representing the reduced fraction
 *
 *              **Finds any common denominator**
 *             -toCommonDenominator
 *                          :   Arguments   :   FracType-   First fraction
 *                                              FracType-   Second fraction
 *                              Returns     :   FracType[]
 *                                                      -   0 position is the first fraction
 *                                                      -   1 position is the second fraction
 *              **Simplifies a fraction**
 *             -simplify    :   Arguments   :   None
 *                              Returns     :   FracType-   new FracType representing the simplified fraction
 *
 *              **Finds the greatest common divisor**   *Recursive*
 *             -greatestCommonDivisor
 *                          :   Arguments   :   int     -   Represents the numerator (initially)
 *                                              int     -   Represents the denominator (initially)
 *                              Returns     :   int     -   Representing the greatest common divisor
 *
 *              **Compares this object to another**
 *              compareTo   :   Arguments   :   FracType-   Represents the object to compare this to
 *                              Returns     :   int     -   < 0 : this is smaller
 *                                                          = 0 : this is equal
 *                                                          > 0 : this is larger
 */
public class FracType implements DataType {

    /*
     *      *****Instance Variables*****
     *
     * value    :   double      -   Represents the value of the object
     * int      :   int         -   Represents the numerator
     * int      :   int         -   Represents the denominator
     */
    private int numerator, denominator;
    private double value;

    /*
     * 0 Argument constructor
     * Should not be used as the object should not exist without a value
     *
     * @exception   -   IllegalArgumentException
     */
    public FracType() {
        throw new IllegalArgumentException("You must provide a value");
    }

    /*
     * 2 Argument constructor
     * Create a new FracType object
     */
    public FracType(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        //Converts the integer to a float to prevent integer division
        this.value = (numerator * 1.0) / denominator;
    }

    /*
     * Converts to a common denominator between two fractions
     *
     * returns  FracType[] containing the two modified fractions
     *
     * Note:    This does NOT find the greatest common denominator as that requires more time
     *          This methods is only to allow for a comparison between the two without worrying
     *          about floating point precision
     *          This is important for equivalent fractions as they might have slightly different
     *          floating point representations
     */
    private static FracType[] toCommonDenominator(FracType fraction, FracType anotherFraction) {
        FracType[] newFractions = new FracType[2];
        int fractionNumerator = fraction.numerator * anotherFraction.denominator;
        int fractionDenominator = fraction.denominator * anotherFraction.denominator;
        int anotherFractionNumerator = anotherFraction.numerator * fraction.denominator;
        int anotherFractionDenominator = anotherFraction.denominator * fraction.denominator;
        newFractions[0] = new FracType(fractionNumerator, fractionDenominator);
        newFractions[1] = new FracType(anotherFractionNumerator, anotherFractionDenominator);
        return newFractions;
    }

    /*
     * Getter for numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /*
     * Setter for numerator
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /*
     * Getter for denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /*
     * Setter for denominator
     */
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /*
     * Getter for value
     */
    public double getValue() {
        return value;
    }

    /*
     * Setter for value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /*
     * Gets a string representation of the object
     * Uses / as the separator for numerator and denominator
     *
     * Implements:  DataType.toString()
     * Overrides:   Object.toString()
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /*
     * Compares this object to another of the same inherited type
     *
     * returns  int < 0 if  this is smaller
     *          int = 0 if  this is equal
     *          int > 0 if  this is larger
     *
     * @exception   -   IllegalArgumentException    -   Class mismatch
     */
    public int compareTo(DataType anotherDataType) {
        if (!anotherDataType.getClass().equals(this.getClass())) {
            throw new IllegalArgumentException("Can not compare two different DataTypes");
        }
        return compareToFrac((FracType) anotherDataType);
    }

    /*
     * Gets a string representation of the object after it is simplified
     * Uses / as the separator for numerator and denominator
     *
     * Implements:  DataType.toString()
     * Overrides:   Object.toString()
     */
    public String toSimplifiedString() {
        FracType simple = this.simplify();
        return simple.toString();
    }

    /*
     * Compares this object to another of the same type
     *
     * returns  int < 0 if  this is smaller
     *          int = 0 if  this is equal
     *          int > 0 if  this is larger
     */
    public int compareToFrac(FracType anotherFracType) {
        FracType[] newFractions = toCommonDenominator(this, anotherFracType);
        //Case for if both are negative
        if (newFractions[0].numerator < 0 && newFractions[1].numerator < 0) {
            return Math.abs(newFractions[1].numerator) - Math.abs(newFractions[0].numerator);
        }
        return newFractions[0].numerator - newFractions[1].numerator;

    }

    /*
     * Reduces the fraction to its simplest form
     *
     * returns  FracType representing the simplified fraction
     *
     * Note:    This method does not modify this object
     */
    private FracType simplify() {
        int n = numerator / greatestCommonDivisor(numerator, denominator);
        int d = denominator / greatestCommonDivisor(numerator, denominator);
        return new FracType(n, d);
    }

    /*
     * Finds the greatest common divisor for a given fraction
     *
     * returns  int representing this greatest common divisor
     *
     * Note:    This method is not recursive and should not recurse infinitely for a valid fraction
     *          Ensure the fraction passed to this method is valid before calling
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private int greatestCommonDivisor(int x, int y) {
        if (y == 0) {
            return x;
        }
        return greatestCommonDivisor(y, x % y);
    }
}
