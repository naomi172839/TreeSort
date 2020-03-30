package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.datatypes.FracType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FracParserTest {

    @Test
    public void testFracParser() {
        String s1 = "3/4 5/8 9/10 7/2";                             //Normal list, all fractions
        String s2 = "3/4 5/8 9/10 7/2 12/4 6/9 3/2";                //Normal list, longer
        String s3 = "      5/8 2/3 9/6 3/4";                        //Leading white space
        String s4 = "1/2 3/4 5/6 7/8      ";                        //Trailing white space
        String s5 = "2/4  4/6   6/8    8/10";                       //Inline extra white space
        String s6 = "    1/3 3/5  5/7   7/9     ";                  //Mixed white space issues
        String s7 = "1 2 3 4";                                      //All integers (not valid)
        String s8 = "2/3 3/4 5 6/7";                                //Integer mixed in
        String s9 = "1/2 2/3 a/4 4/5";                              //Alpha character in numerator
        String s10 = "1/2 2/3 a/b 4/5";                             //Alpha character in both
        String s11 = "1/2 2/3 3/a 4/5";                             //Alpha character in denominator
        String s12 = "1/2 2/3 a 4/5";                               //Alpha character non fractional
        String s13 = "1/2/3 3/4 4/5 5/6";                           //Invalid fraction
        String s14 = "-1/2 2/3 3/4 4/5";                            //Negative in numerator
        String s15 = "-1/2 -2/3 -3/4 -4/5";                         //All negative
        String s16 = "-1/-2 3/4 4/5 5/6";                           //Negative in both (invalid)
        String s17 = "1/-2 2/3 3/4 4/5";                            //Negative in denominator(invalid)
        String s18 = "1/2, 2/3, .3/4 4/5";                          //Invalid character in numerator
        String s19 = "1/2 2/3 3/.2 4/5";                            //Invalid in denominator
        String s20 = "";                                            //Empty string
        String s21 = null;                                          //null string

        /*
         * Creating the objects to compare to
         * This could have also simply been comparison strings
         * This methods allows easier implementation of array comparison later
         */
        FracType[] f1 = new FracType[]{
                new FracType(3, 4),
                new FracType(5, 8),
                new FracType(9, 10),
                new FracType(7, 2),
        };
        FracType[] f2 = new FracType[]{
                new FracType(3, 4),
                new FracType(5, 8),
                new FracType(9, 10),
                new FracType(7, 2),
                new FracType(12, 4),
                new FracType(6, 9),
                new FracType(3, 2),
        };
        FracType[] f3 = new FracType[]{
                new FracType(5, 8),
                new FracType(2, 3),
                new FracType(9, 6),
                new FracType(3, 4),
        };
        FracType[] f4 = new FracType[]{
                new FracType(1, 2),
                new FracType(3, 4),
                new FracType(5, 6),
                new FracType(7, 8),
        };
        FracType[] f5 = new FracType[]{
                new FracType(2, 4),
                new FracType(4, 6),
                new FracType(6, 8),
                new FracType(8, 10),
        };
        FracType[] f6 = new FracType[]{
                new FracType(1, 3),
                new FracType(3, 5),
                new FracType(5, 7),
                new FracType(7, 9),
        };
        FracType[] f14 = new FracType[]{
                new FracType(-1, 2),
                new FracType(2, 3),
                new FracType(3, 4),
                new FracType(4, 5),
        };
        FracType[] f15 = new FracType[]{
                new FracType(-1, 2),
                new FracType(-2, 3),
                new FracType(-3, 4),
                new FracType(-4, 5),
        };
        //The toString method is used to eliminate the need to create a class
        //to compare arrays
        assertEquals(Arrays.toString(f1), Arrays.toString(FracParser.parse(s1)));
        assertEquals(Arrays.toString(f2), Arrays.toString(FracParser.parse(s2)));
        assertEquals(Arrays.toString(f3), Arrays.toString(FracParser.parse(s3)));
        assertEquals(Arrays.toString(f4), Arrays.toString(FracParser.parse(s4)));
        assertEquals(Arrays.toString(f5), Arrays.toString(FracParser.parse(s5)));
        assertEquals(Arrays.toString(f6), Arrays.toString(FracParser.parse(s6)));
        assertEquals(Arrays.toString(f14), Arrays.toString(FracParser.parse(s14)));
        assertEquals(Arrays.toString(f15), Arrays.toString(FracParser.parse(s15)));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s7));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s8));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s9));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s10));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s11));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s12));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s13));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s16));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s17));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s18));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s19));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s20));
        assertThrows(NumberFormatException.class, () -> FracParser.parse(s21));
    }

}
