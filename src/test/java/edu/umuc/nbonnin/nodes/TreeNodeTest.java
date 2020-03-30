package edu.umuc.nbonnin.nodes;

import edu.umuc.nbonnin.datatypes.FracType;
import edu.umuc.nbonnin.datatypes.IntType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TreeNodeTest {

    @Test
    void testGetValue() {
        //Creation of IntType objects
        IntType i1 = new IntType(1);
        IntType i2 = new IntType(2);
        IntType i3 = new IntType(3);
        IntType i4 = new IntType(4);
        //Creation of FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(3, 2);
        FracType f3 = new FracType(2, 3);
        FracType f4 = new FracType(4, 1);
        //Creation of IntType TreeNodes
        TreeNode<IntType> n1 = new TreeNode<>(i1);
        TreeNode<IntType> n2 = new TreeNode<>(i2);
        TreeNode<IntType> n3 = new TreeNode<>(i3);
        TreeNode<IntType> n4 = new TreeNode<>(i4);
        //Creation of FracType TreeNodes
        TreeNode<FracType> n5 = new TreeNode<>(f1);
        TreeNode<FracType> n6 = new TreeNode<>(f2);
        TreeNode<FracType> n7 = new TreeNode<>(f3);
        TreeNode<FracType> n8 = new TreeNode<>(f4);
        //IntType assertions
        assertEquals(i1, n1.getValue());
        assertEquals(i2, n2.getValue());
        assertEquals(i3, n3.getValue());
        assertEquals(new IntType(4).getValue(), n4.getValue().getValue());           //Tests value comparison
        //FracType assertions
        assertEquals(f1, n5.getValue());
        assertEquals(f2, n6.getValue());
        assertEquals(f3, n7.getValue());
        assertEquals(new FracType(4, 1).getValue(), n8.getValue().getValue());
    }

    @Test
    void testSetValue() {
        //Creation of IntType objects
        IntType i1 = new IntType(1);
        IntType i2 = new IntType(2);
        IntType i3 = new IntType(3);
        IntType i4 = new IntType(4);
        //Creation of FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(3, 2);
        FracType f3 = new FracType(2, 3);
        FracType f4 = new FracType(4, 1);
        //Creation of IntType TreeNodes
        TreeNode<IntType> n1 = new TreeNode<>(i1);
        TreeNode<IntType> n2 = new TreeNode<>(i2);
        //Creation of FracType TreeNodes
        TreeNode<FracType> n5 = new TreeNode<>(f1);
        TreeNode<FracType> n6 = new TreeNode<>(f2);
        //IntType setValue
        n1.setValue(i3);
        n2.setValue(i4);
        //FracType setValue
        n5.setValue(f3);
        n6.setValue(f4);
        //IntType assertions
        assertEquals(i3, n1.getValue());
        assertEquals(i4, n2.getValue());
        //FracTypeAssertions
        assertEquals(f3, n5.getValue());
        assertEquals(f4, n6.getValue());
    }

    @Test
    void testGetAndSetParent() {
        //Creation of the IntType objects
        IntType i1 = new IntType(1);
        IntType i2 = new IntType(2);
        IntType i3 = new IntType(3);
        IntType i4 = new IntType(4);
        //Creation of the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(3, 2);
        FracType f3 = new FracType(2, 3);
        FracType f4 = new FracType(4, 1);
        //Creation of the IntType TreeNodes
        TreeNode<IntType> n1 = new TreeNode<>(i1);
        TreeNode<IntType> n2 = new TreeNode<>(i2);
        TreeNode<IntType> n3 = new TreeNode<>(i3);
        TreeNode<IntType> n4 = new TreeNode<>(i4);
        //Creation of the FracType TreeNodes
        TreeNode<FracType> n5 = new TreeNode<>(f1);
        TreeNode<FracType> n6 = new TreeNode<>(f2);
        TreeNode<FracType> n7 = new TreeNode<>(f3);
        TreeNode<FracType> n8 = new TreeNode<>(f4);
        //IntType set parents
        n1.setParent(n2);
        n2.setParent(n3);
        n3.setParent(n4);
        //FracType set parents
        n5.setParent(n6);
        n6.setParent(n7);
        n7.setParent(n8);
        //IntType tests
        assertEquals(n2, n1.getParent());
        assertEquals(n3, n2.getParent());
        assertEquals(n4, n3.getParent());
        assertNull(n4.getParent());
        //FracType tests
        assertEquals(n6, n5.getParent());
        assertEquals(n7, n6.getParent());
        assertEquals(n8, n7.getParent());
        assertNull(n8.getParent());
    }

    @Test
    void testGetAndAddChildren() {
        //Creation of the IntType objects
        IntType i1 = new IntType(1);
        IntType i2 = new IntType(2);
        IntType i3 = new IntType(3);
        IntType i4 = new IntType(4);
        //Creation of the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(3, 2);
        FracType f3 = new FracType(2, 3);
        FracType f4 = new FracType(4, 1);
        //Creation of the IntType TreeNodes
        TreeNode<IntType> n1 = new TreeNode<>(i1);
        TreeNode<IntType> n2 = new TreeNode<>(i2);
        TreeNode<IntType> n3 = new TreeNode<>(i3);
        TreeNode<IntType> n4 = new TreeNode<>(i4);
        //Creation of the FracType TreeNodes
        TreeNode<FracType> n5 = new TreeNode<>(f1);
        TreeNode<FracType> n6 = new TreeNode<>(f2);
        TreeNode<FracType> n7 = new TreeNode<>(f3);
        TreeNode<FracType> n8 = new TreeNode<>(f4);
        //ArrayList to compare IntType TreeNodes
        ArrayList<TreeNode<IntType>> l1 = new ArrayList<>();
        l1.add(n2);
        l1.add(n3);
        l1.add(n4);
        //ArrayList to compare FracType TreeNodes
        ArrayList<TreeNode<FracType>> l2 = new ArrayList<>();
        l2.add(n6);
        l2.add(n7);
        l2.add(n8);
        n1.addChild(n2);
        n1.addChild(n3);
        n1.addChild(n4);
        n5.addChild(n6);
        n5.addChild(n7);
        n5.addChild(n8);
        assertIterableEquals(l1, n1.getChildren());
        assertIterableEquals(l2, n5.getChildren());
    }

    @Test
    void testCompareTo() {
        //Creation of the IntType objects
        IntType i1 = new IntType(1);
        IntType i2 = new IntType(2);
        IntType i3 = new IntType(3);
        IntType i4 = new IntType(4);
        //Creation of the FracType objects
        FracType f1 = new FracType(1, 2);
        FracType f2 = new FracType(3, 2);
        FracType f3 = new FracType(2, 3);
        FracType f4 = new FracType(4, 1);
        //Creation of the IntType TreeNodes
        TreeNode<IntType> n1 = new TreeNode<>(i1);
        TreeNode<IntType> n2 = new TreeNode<>(i2);
        TreeNode<IntType> n3 = new TreeNode<>(i3);
        TreeNode<IntType> n4 = new TreeNode<>(i4);
        //Creation of the FracType TreeNodes
        TreeNode<FracType> n5 = new TreeNode<>(f1);
        TreeNode<FracType> n6 = new TreeNode<>(f2);
        TreeNode<FracType> n7 = new TreeNode<>(f3);
        TreeNode<FracType> n8 = new TreeNode<>(f4);
        //Comparison of IntType TreeNodes
        assertTrue(n1.compareTo(n2) < 0);
        assertTrue(n2.compareTo(n3) < 0);
        assertEquals(0, n3.compareTo(new TreeNode<>(i3)));
        assertTrue(n4.compareTo(n1) > 0);
        //Comparison of FracType TreeNodes
        assertTrue(n5.compareTo(n6) < 0);
        assertTrue(n6.compareTo(n7) > 0);
        assertEquals(0, n7.compareTo(new TreeNode<>(f3)));
        assertTrue(n8.compareTo(n6) > 0);
    }

    @Test
    void testBadConstructor() {
        assertThrows(IllegalArgumentException.class, TreeNode::new);
    }
}
