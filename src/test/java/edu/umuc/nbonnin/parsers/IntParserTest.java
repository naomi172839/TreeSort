package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.datatypes.IntType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntParserTest {

    @Test
    void testIntParser() {
        String s1 = "1 2 3 4 5";                //Normal list, all integers
        String s2 = "5 10 15 20 30 40";         //Normal list, all integers, longer
        String s3 = "  5 6 7 8 9";              //Leading white space, all integers
        String s4 = "1 3 5 7 9   ";             //Tailing white space, all integers
        String s5 = "2  4   6    8     10";     //Varying internal white space, all integers
        String s6 = "-5 -4 -3 -2 -1";           //Normal list, all negative integers
        String s7 = "  -10 -9 -8   -7  -6  ";   //White space issues, all negative integers
        String s8 = "1 2 3 a 4 5";              //Numeric character, all integers
        String s9 = "1/2 1 3/2 2 5/2 3";        //Rational characters
        String s10 = "0.5, 1.0, 2.0, 2.34, 4.8";//Decimal characters
        String s11 = "1 2 3 -a 4 5";            //Alpha character, all integers, negative before alpha
        String s12 = "-1/2 1 3-/2 2 5/-2 3";    //Rational characters, random negative
        String s13 = "-0.5, 1 2 -2.34, -4.8";   //Decimal characters
        String s14 = "";                        //Empty string
        String s15 = null;                      //null string
        IntType[] i1 = new IntType[]{
                new IntType(1),
                new IntType(2),
                new IntType(3),
                new IntType(4),
                new IntType(5),
        };
        IntType[] i2 = new IntType[]{
                new IntType(5),
                new IntType(10),
                new IntType(15),
                new IntType(20),
                new IntType(30),
                new IntType(40),
        };
        IntType[] i3 = new IntType[]{
                new IntType(5),
                new IntType(6),
                new IntType(7),
                new IntType(8),
                new IntType(9),
        };
        IntType[] i4 = new IntType[]{
                new IntType(1),
                new IntType(3),
                new IntType(5),
                new IntType(7),
                new IntType(9),
        };
        IntType[] i5 = new IntType[]{
                new IntType(2),
                new IntType(4),
                new IntType(6),
                new IntType(8),
                new IntType(10),
        };
        IntType[] i6 = new IntType[]{
                new IntType(-5),
                new IntType(-4),
                new IntType(-3),
                new IntType(-2),
                new IntType(-1),
        };
        IntType[] i7 = new IntType[]{
                new IntType(-10),
                new IntType(-9),
                new IntType(-8),
                new IntType(-7),
                new IntType(-6),
        };
        //The to string method is used to prevent writing an additional class to check 
        //if the arrays are equal by value
        assertEquals(Arrays.toString(i1), Arrays.toString(IntParser.parse(s1)));
        assertEquals(Arrays.toString(i2), Arrays.toString(IntParser.parse(s2)));
        assertEquals(Arrays.toString(i3), Arrays.toString(IntParser.parse(s3)));
        assertEquals(Arrays.toString(i4), Arrays.toString(IntParser.parse(s4)));
        assertEquals(Arrays.toString(i5), Arrays.toString(IntParser.parse(s5)));
        assertEquals(Arrays.toString(i6), Arrays.toString(IntParser.parse(s6)));
        assertEquals(Arrays.toString(i7), Arrays.toString(IntParser.parse(s7)));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s8));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s9));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s10));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s11));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s12));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s13));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s14));
        assertThrows(NumberFormatException.class, () -> IntParser.parse(s15));
    }
}
