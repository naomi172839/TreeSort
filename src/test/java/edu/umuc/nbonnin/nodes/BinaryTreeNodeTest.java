package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.FracType;
import edu.umuc.nbonnin.datatypes.IntType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeNodeTest {

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
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        BinaryTreeNode<IntType> n3 = new BinaryTreeNode<>(i3);
        BinaryTreeNode<IntType> n4 = new BinaryTreeNode<>(i4);
        //Create the FracType nodes
        BinaryTreeNode<FracType> n5 = new BinaryTreeNode<>(f1);
        BinaryTreeNode<FracType> n6 = new BinaryTreeNode<>(f2);
        BinaryTreeNode<FracType> n7 = new BinaryTreeNode<>(f3);
        BinaryTreeNode<FracType> n8 = new BinaryTreeNode<>(f4);
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
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        //Create the FracType nodes
        BinaryTreeNode<FracType> n5 = new BinaryTreeNode<>(f1);
        BinaryTreeNode<FracType> n6 = new BinaryTreeNode<>(f2);
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
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        BinaryTreeNode<IntType> n3 = new BinaryTreeNode<>(i3);
        BinaryTreeNode<IntType> n4 = new BinaryTreeNode<>(i4);
        //Create the FracType nodes
        BinaryTreeNode<FracType> n5 = new BinaryTreeNode<>(f1);
        BinaryTreeNode<FracType> n6 = new BinaryTreeNode<>(f2);
        BinaryTreeNode<FracType> n7 = new BinaryTreeNode<>(f3);
        BinaryTreeNode<FracType> n8 = new BinaryTreeNode<>(f4);
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
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        BinaryTreeNode<IntType> n3 = new BinaryTreeNode<>(i3);
        BinaryTreeNode<IntType> n4 = new BinaryTreeNode<>(i4);
        //Create the FracType nodes
        BinaryTreeNode<FracType> n5 = new BinaryTreeNode<>(f1);
        BinaryTreeNode<FracType> n6 = new BinaryTreeNode<>(f2);
        BinaryTreeNode<FracType> n7 = new BinaryTreeNode<>(f3);
        BinaryTreeNode<FracType> n8 = new BinaryTreeNode<>(f4);
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
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        BinaryTreeNode<IntType> n3 = new BinaryTreeNode<>(i3);
        BinaryTreeNode<IntType> n4 = new BinaryTreeNode<>(i4);
        //Create the FracType nodes
        BinaryTreeNode<FracType> n5 = new BinaryTreeNode<>(f1);
        BinaryTreeNode<FracType> n6 = new BinaryTreeNode<>(f2);
        BinaryTreeNode<FracType> n7 = new BinaryTreeNode<>(f3);
        BinaryTreeNode<FracType> n8 = new BinaryTreeNode<>(f4);
        //Sets children
        n1.setLeft(n2);
        n1.setRight(n3);
        n1.setLeft(n4);
        n5.setLeft(n6);
        n5.setRight(n7);
        n5.setRight(n8);
        ArrayList<BinaryTreeNode<IntType>> l1 = new ArrayList<>();
        ArrayList<BinaryTreeNode<FracType>> l2 = new ArrayList<>();
        l1.add(n4);
        l1.add(n3);
        l2.add(n6);
        l2.add(n8);
        assertIterableEquals(l1, n1.getChildren());
        assertIterableEquals(l2, n5.getChildren());
    }

    @Test
    void testCompareTo() {
        //Creates the IntType objects
        IntType i1 = new IntType(10);
        IntType i2 = new IntType(7);
        IntType i3 = new IntType(-8);
        IntType i4 = new IntType(-3);
        //Create the IntType nodes
        BinaryTreeNode<IntType> n1 = new BinaryTreeNode<>(i1);
        BinaryTreeNode<IntType> n2 = new BinaryTreeNode<>(i2);
        BinaryTreeNode<IntType> n3 = new BinaryTreeNode<>(i3);
        BinaryTreeNode<IntType> n4 = new BinaryTreeNode<>(i4);
        assertTrue(n1.compareTo(n2) > 0);
        assertTrue(n1.compareTo(n3) > 0);
        assertTrue(n2.compareTo(n1) < 0);
        assertEquals(0, n1.compareTo(new BinaryTreeNode<>(new IntType(10))));
    }

    @Test
    void testBadConstructor() {
        assertThrows(IllegalArgumentException.class, BinaryTreeNode::new);
    }
}
