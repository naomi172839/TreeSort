package edu.umuc.nbonnin.datatypes;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class IntTypeTest {

    @Test
    void getValue() {
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(10003);
        IntType i4 = new IntType(-8);
        IntType i5 = new IntType(-3);
        assertEquals(10, i1.getValue());
        assertEquals(7, i2.getValue());
        assertEquals(10003, i3.getValue());
        assertEquals(-8, i4.getValue());
        assertEquals(-3, i5.getValue());
    }

    @Test
    void setValue() {
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(10003);
        IntType i4 = new IntType(-8);
        IntType i5 = new IntType(-3);
        i1.setValue(3);
        i2.setValue(9);
        i3.setValue(-6);
        i4.setValue(-91);
        i5.setValue(14);
        assertEquals(3, i1.getValue());
        assertEquals(9, i2.getValue());
        assertEquals(-6, i3.getValue());
        assertEquals(-91, i4.getValue());
        assertEquals(14, i5.getValue());
    }

    @Test
    void testToString() {
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(10003);
        IntType i4 = new IntType(-8);
        IntType i5 = new IntType(-3);
        assertEquals("10", i1.toString());
        assertEquals("7", i2.toString());
        assertEquals("10003", i3.toString());
        assertEquals("-8", i4.toString());
        assertEquals("-3", i5.toString());
    }

    @Test
    void compareTo() {
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(10003);
        IntType i4 = new IntType(-8);
        IntType i5 = new IntType(-3);
        FracType f1 = new FracType(3, 1);
        assertTrue(i1.compareTo(i2) > 0);
        assertTrue(i2.compareTo(i3) < 0);
        assertTrue(i3.compareTo(i4) > 0);
        assertTrue(i4.compareTo(i5) < 0);
        assertTrue(i5.compareTo(i1) < 0);
        //Tests for incompatible types
        AtomicInteger i = new AtomicInteger(); //Atomic to capture the value returned if any
        assertThrows(IllegalArgumentException.class, () -> i.set(i1.compareTo(f1))); //Integer is not thread safe
        assertEquals(0, i.get()); //Ensures that nothing is actually returned
    }

    @Test
    void testBadConstructor() {
        assertThrows(IllegalArgumentException.class, IntType::new);
    }
}