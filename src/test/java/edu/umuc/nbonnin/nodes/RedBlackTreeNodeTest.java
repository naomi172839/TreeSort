package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.FracType;
import edu.umuc.nbonnin.datatypes.IntType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeNodeTest {

    @Test
    void testGetValue() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Checks that the value can be retrieved
        assertEquals(i1, n1.getValue());
        assertEquals(i2, n2.getValue());
        assertEquals(i3, n3.getValue());
        assertEquals(i4, n4.getValue());
        assertEquals(f1, n5.getValue());
        assertEquals(f2, n6.getValue());
        assertEquals(f3, n7.getValue());
        assertEquals(f4, n8.getValue());
    }

    @Test
    void testSetValue() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        n1.setValue(i3);
        n2.setValue(i4);
        n5.setValue(f3);
        n6.setValue(f4);
        //Checks that the value was updated
        assertEquals(i3, n1.getValue());
        assertEquals(i4, n2.getValue());
        assertEquals(f3, n5.getValue());
        assertEquals(f4, n6.getValue());
    }

    @Test
    void testGetAndSetParent() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets all the parents
        n1.setParent(n2);
        n2.setParent(n3);
        n3.setParent(n4);
        n5.setParent(n6);
        n6.setParent(n7);
        n7.setParent(n8);
        assertEquals(n2, n1.getParent());
        assertEquals(n3, n2.getParent());
        assertEquals(n4, n3.getParent());
        assertNull(n4.getParent());
        assertEquals(n6, n5.getParent());
        assertEquals(n7, n6.getParent());
        assertEquals(n8, n7.getParent());
        assertNull(n8.getParent());
    }

    @Test
    void testGetAndSetLeftAndRight() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets children
        n1.setLeft(n2);
        n1.setRight(n3);
        n2.setLeft(n4);
        n5.setLeft(n6);
        n5.setRight(n7);
        n6.setLeft(n8);
        //Checks that the nodes were set
        assertEquals(n2, n1.getLeft());
        assertEquals(n3, n1.getRight());
        assertEquals(n4, n1.getLeft().getLeft());
        assertEquals(n1, n3.getParent());
        assertEquals(n6, n5.getLeft());
        assertEquals(n7, n5.getRight());
        assertEquals(n8, n5.getLeft().getLeft());
        assertEquals(n5, n5.getLeft().getLeft().getParent().getParent());
    }

    @Test
    void testGetAndSetColor() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets colors
        n1.setColor(true);
        n2.setColor(false);
        n3.setColor(true);
        n4.setColor(false);
        n5.setColor(true);
        n6.setColor(true);
        n7.setColor(false);
        n8.setColor(false);
        //Check values
        assertTrue(n1.getColor());
        assertFalse(n2.getColor());
        assertTrue(n3.getColor());
        assertFalse(n4.getColor());
        assertTrue(n5.getColor());
        assertTrue(n6.getColor());
        assertFalse(n7.getColor());
        assertFalse(n8.getColor());

    }

    @Test
    void testAddAndGetChildren() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets children
        n1.setLeft(n2);
        n1.setRight(n3);
        n1.setLeft(n4);
        n5.setLeft(n6);
        n5.setRight(n7);
        n5.setRight(n8);
        //Creates the ArrayLists needed for comparison
        ArrayList<RedBlackTreeNode<IntType>> l1 = new ArrayList<>();
        ArrayList<RedBlackTreeNode<FracType>> l2 = new ArrayList<>();
        l1.add(n4);
        l1.add(n3);
        l2.add(n6);
        l2.add(n8);
        assertIterableEquals(l1, n1.getChildren());
        assertIterableEquals(l2, n5.getChildren());
    }

    @Test
    void getCountAndSetCount() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets the counts
        n1.setCount(10);
        n2.setCount(5);
        n3.setCount(2);
        n5.setCount(11);
        n6.setCount(1);
        n7.setCount(99);
        //Checks that the counts can be retrieved
        //Also checks the default value;
        assertEquals(10, n1.getCount());
        assertEquals(5, n2.getCount());
        assertEquals(2, n3.getCount());
        assertEquals(1, n4.getCount());
        assertEquals(11, n5.getCount());
        assertEquals(1, n6.getCount());
        assertEquals(99, n7.getCount());
        assertEquals(1, n8.getCount());
    }

    @Test
    void testCompareTo() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        assertTrue(n1.compareTo(n2) > 0);
        assertTrue(n1.compareTo(n3) > 0);
        assertTrue(n2.compareTo(n1) < 0);
        assertEquals(0, n1.compareTo(new RedBlackTreeNode<>(new IntType(10))));
    }

    @Test
    void testOverrideNeeds() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Creates the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(10, 4);
        FracType f3 = new FracType(11, 17);
        FracType f4 = new FracType(99, 33);
        //Create the IntType nodes
        RedBlackTreeNode<IntType> n1 = new RedBlackTreeNode<>(i1);
        RedBlackTreeNode<IntType> n2 = new RedBlackTreeNode<>(i2);
        RedBlackTreeNode<IntType> n3 = new RedBlackTreeNode<>(i3);
        RedBlackTreeNode<IntType> n4 = new RedBlackTreeNode<>(i4);
        //Create the FracType nodes
        RedBlackTreeNode<FracType> n5 = new RedBlackTreeNode<>(f1);
        RedBlackTreeNode<FracType> n6 = new RedBlackTreeNode<>(f2);
        RedBlackTreeNode<FracType> n7 = new RedBlackTreeNode<>(f3);
        RedBlackTreeNode<FracType> n8 = new RedBlackTreeNode<>(f4);
        //Sets all the parents
        n1.setParent(n2);
        n2.setParent(n3);
        n3.setParent(n4);
        n5.setParent(n6);
        n6.setParent(n7);
        n7.setParent(n8);
        //Sets children
        n2.setLeft(n4);
        n6.setLeft(n8);
        //Sets counts
        n4.setCount(12);
        n8.setCount(4);
        //Sets the colors
        n4.setColor(true);
        n8.setColor(false);
        //Checks that count can be retrieved
        assertEquals(12, n1.getParent().getLeft().getCount());
        assertEquals(4, n5.getParent().getLeft().getCount());
        //Checks that color can be retrieved
        assertTrue(n1.getParent().getLeft().getColor());
        assertFalse(n5.getParent().getLeft().getColor());
    }

    @Test
    void testBadConstructor() {
        assertThrows(IllegalArgumentException.class, RedBlackTreeNode::new);
    }
}

