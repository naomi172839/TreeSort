package edu.umuc.nbonnin.treesort;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class FracTypeTest {

    @Test
    void getNumerator() {
        //Create FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Assert that the numerator is correct
        assertEquals(1, f1.getNumerator());
        assertEquals(10, f2.getNumerator());
        assertEquals(11, f3.getNumerator());
        assertEquals(10000, f4.getNumerator());
        assertEquals(99, f5.getNumerator());
    }

    @Test
    void setNumerator() {
        //Create FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Modify the numerators
        f1.setNumerator(5);
        f2.setNumerator(3);
        f3.setNumerator(54);
        f4.setNumerator(9);
        f5.setNumerator(-8);
        //Check if the numerators are correct
        assertEquals(5, f1.getNumerator());
        assertEquals(3, f2.getNumerator());
        assertEquals(54, f3.getNumerator());
        assertEquals(9, f4.getNumerator());
        assertEquals(-8, f5.getNumerator());
    }

    @Test
    void getDenominator() {
        //Create the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Assert that the denominators are correct
        assertEquals(2, f1.getDenominator());
        assertEquals(4, f2.getDenominator());
        assertEquals(17, f3.getDenominator());
        assertEquals(5000, f4.getDenominator());
        assertEquals(33, f5.getDenominator());
    }

    @Test
    void setDenominator() {
        //Create the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Modifies the denominators
        f1.setDenominator(5);
        f2.setDenominator(3);
        f3.setDenominator(54);
        f4.setDenominator(9);
        f5.setDenominator(-8);
        //Checks if the denominators contain their new values
        assertEquals(5, f1.getDenominator());
        assertEquals(3, f2.getDenominator());
        assertEquals(54, f3.getDenominator());
        assertEquals(9, f4.getDenominator());
        assertEquals(-8, f5.getDenominator());
    }

    @Test
    void getValue() {
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Ensures that the value (in string form) is the same as the value of the object
        //The string form is used as there is the potential for errors in floating point math
        //Those errors should not occurs at 5 significant figures
        assertEquals("0.50000", String.format("%.5f", f1.getValue()));
        assertEquals("2.50000", String.format("%.5f", f2.getValue()));
        assertEquals("0.64706", String.format("%.5f", f3.getValue())); //Rounds up, does not truncate
        assertEquals("2.00000", String.format("%.5f", f4.getValue()));
        assertEquals("3.00000", String.format("%.5f", f5.getValue()));
    }

    @Test
    void setValue() {
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Modifies the values
        f1.setValue(0.54323);
        f2.setValue(0.954323452);
        f3.setValue(2.00000);
        f4.setValue(9.12345);
        f5.setValue(0.000009); //Rounds up, does not truncate
        //Checks that the values are correct
        assertEquals("0.54323", String.format("%.5f", f1.getValue()));
        assertEquals("0.95432", String.format("%.5f", f2.getValue()));
        assertEquals("2.00000", String.format("%.5f", f3.getValue()));
        assertEquals("9.12345", String.format("%.5f", f4.getValue()));
        assertEquals("0.00001", String.format("%.5f", f5.getValue()));
    }

    @Test
    void testToString() {
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Ensures that the String returned is what is expected
        assertEquals("1/2", f1.toString());
        assertEquals("10/4", f2.toString());
        assertEquals("11/17", f3.toString());
        assertEquals("10000/5000", f4.toString());
        assertEquals("99/33", f5.toString());
    }

    @Test
    void toSimplifiedString() {
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        //Ensures that the String returned is the simplified fraction
        assertEquals("1/2", f1.toSimplifiedString());
        assertEquals("5/2", f2.toSimplifiedString());
        assertEquals("11/17", f3.toSimplifiedString());
        assertEquals("2/1", f4.toSimplifiedString());
        assertEquals("3/1", f5.toSimplifiedString());
    }

    @Test
    void compareTo() {
        //Create the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(10000, 5000);
        FracType f5 = new FracType(99, 33);
        FracType f6 = new FracType(6, 2);
        FracType f7 = new FracType(-5, 12);
        FracType f8 = new FracType(-2, 6);
        Integer i1 = 7;
        assertTrue(f1.compareTo(f2) < 0);
        assertTrue(f2.compareTo(f3) > 0);
        assertTrue(f3.compareTo(f4) < 0);
        assertTrue(f4.compareTo(f5) < 0);
        assertEquals(0, f5.compareTo(f6));   //Checks if two different fractions with the same value
        assertTrue(f7.compareTo(f8) < 0);   //Checks negative number comparison
        //Tests for incompatible types
        AtomicInteger i = new AtomicInteger(); //Atomic to capture the value returned if any
        assertThrows(IllegalArgumentException.class, () -> i.set(f1.compareTo(i1))); //Integer is not thread safe
        assertEquals(0, i.get()); //Ensures that nothing is actually returned
    }

    @Test
    void testBadConstructor() {
        assertThrows(IllegalArgumentException.class, FracType::new);
    }
}