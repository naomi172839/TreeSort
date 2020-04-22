package edu.umuc.nbonnin.treesort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TreeFactoryTest {

    String s1, s2, s3, s4, s5;

    @BeforeEach
    void setUp() {
        s1 = "1 2 3 4 5 6 7 8 9 10";
        s2 = "10 9 8 7 6 5 4 3 2 1";
        s3 = "1/2 3/4 4/5 5/6 6/7 7/8 8/9 9/10";
        s4 = "9/10 8/9 7/8 6/7 5/6 4/5 3/4 1/2";
        s5 = "1.0 2.0 3.0 4.0 5.0";
    }

    @Test
    void testNewIntegerTree() {
        RedBlackTree<Integer, Integer> t1 = TreeFactory.newIntegerTree(s1);
        RedBlackTree<Integer, Integer> t2 = TreeFactory.newIntegerTree(s2);
        assertEquals(s1, t1.normalSort());
        assertEquals(s1, t2.normalSort());
        assertThrows(NumberFormatException.class, () -> TreeFactory.newIntegerTree(s3));
        assertThrows(NumberFormatException.class, () -> TreeFactory.newIntegerTree(s4));
        assertThrows(NumberFormatException.class, () -> TreeFactory.newIntegerTree(s5));
    }

    @Test
    void testNewFracTypeTree() {
        RedBlackTree<FracType, FracType> t3 = TreeFactory.newFracTypeTree(s3);
        RedBlackTree<FracType, FracType> t4 = TreeFactory.newFracTypeTree(s4);
        assertEquals(s3, t3.normalSort());
        assertEquals(s3, t4.normalSort());
        assertThrows(NumberFormatException.class, () -> TreeFactory.newFracTypeTree(s1));
        assertThrows(NumberFormatException.class, () -> TreeFactory.newFracTypeTree(s2));
        assertThrows(NumberFormatException.class, () -> TreeFactory.newFracTypeTree(s5));
    }

    @Test
    void testNewGenericTree() {
        RedBlackTree<?, ?> t1 = TreeFactory.newGenericTree(s1);
        RedBlackTree<?, ?> t2 = TreeFactory.newGenericTree(s2);
        RedBlackTree<?, ?> t3 = TreeFactory.newGenericTree(s3);
        RedBlackTree<?, ?> t4 = TreeFactory.newGenericTree(s4);
        assertEquals(s1, t1.normalSort());
        assertEquals(s1, t2.normalSort());
        assertEquals(s3, t3.normalSort());
        assertEquals(s3, t4.normalSort());
        String e1 = assertThrows(NumberFormatException.class, () -> TreeFactory.newGenericTree(s5)).getMessage();
        System.out.println(e1);
    }
}
