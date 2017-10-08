package com.tristan.cracking.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tristan.cracking.datastructures.BSearchTree;

class BSearchTreeNodeTest {
    private BSearchTree<Integer> b;

    @BeforeEach
    void init() {
        b = new BSearchTree<Integer>();
    }

    @Test
    void testContainsRoot(){
        b.add(10);
        assertEquals(b.remove(10), true);
    }

    @Test
    void testSize(){
        b.add(10);
        b.add(12);
        b.add(8);
        assertEquals(b.getSize(), 3);
        b.remove(12);
        assertEquals(b.getSize(), 2);
    }

    @Test
    void testRemoveRoot(){
        b.add(5);
        b.add(4);
        b.add(6);
        b.remove(5);
        assertEquals(b.rootValue(), new Integer(6));
        assertEquals(b.getSize(), 2);
        b.remove(6);
        assertEquals(b.rootValue(), new Integer(4));
        assertEquals(b.getSize(), 1);
    }
}
