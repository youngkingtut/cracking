package com.tristan.crackin.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tristan.cracking.datastructures.BSearchTreeNode;

public class BSearchTreeNodeTest {
    BSearchTreeNode<Integer> b;

    @BeforeEach
    void init() {
        b = new BSearchTreeNode<Integer>(10);
    }

    @Test
    void testContainsRoot(){
        assertEquals(b.remove(10), true);
    }
}
