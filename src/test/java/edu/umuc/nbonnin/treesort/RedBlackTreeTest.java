package edu.umuc.nbonnin.treesort;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RedBlackTreeTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testInsert() {
        RedBlackTree<Integer, Integer> treeOne = new RedBlackTree<>();
        for (int i = 0; i < 1000; i++) {
            treeOne.insert(i, i);
        }
        treeOne.delete(255);
        treeOne.delete(299);
        treeOne.delete(455);
        treeOne.delete(-3);
        RedBlackTree<FracType, FracType> treeTwo = new RedBlackTree<>();
        for (int i = -1000; i < 1000; i++) {
            FracType temp = new FracType(i, (int) Math.ceil((50 * Math.sin(i))));
            treeTwo.insert(temp, temp);
        }
    }
}
