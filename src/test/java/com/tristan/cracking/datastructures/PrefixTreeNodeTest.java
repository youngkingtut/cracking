package com.tristan.cracking.datastructures;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tristan.cracking.datastructures.PrefixTreeNode;

import java.util.Arrays;


class PrefixTreeNodeTest {
    private PrefixTreeNode r;
    private String[] dict = {"a", "man", "many", "my", "lie"};

    @BeforeEach
    void init() {
        r = new PrefixTreeNode();
    }

    @Test
    void testContains() {
        r.add("hello");
        assertEquals(r.contains("hello"), true);
    }

    @Test
    void testContainsFails() {
        r.add("hello");
        assertEquals(r.contains("hell"), false);
        assertEquals(r.contains("h"), false);
        assertEquals(r.contains("what"), false);
    }

    @Test
    void testAddSimilar(){
        r.add("hello");
        r.add("hell");
        assertEquals(r.contains("hell"), true);
    }

    @Test
    void testDictionary(){
        r.add(Arrays.asList(dict));
        assertEquals(r.contains("a"), true);
        assertEquals(r.contains("my"), true);
        assertEquals(r.contains("man"), true);
        assertEquals(r.contains("many"), true);
        assertEquals(r.contains("lie"), true);
        assertEquals(r.contains("nope"), false);
    }

}
