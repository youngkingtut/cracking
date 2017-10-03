package com.tristan.crackin.datastructures;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tristan.cracking.datastructures.LinkedList;


class LinkedListTest {
    private LinkedList<Integer> l;

    @BeforeEach
    void init() {
        l = new LinkedList<>();
    }

    @Test
    void testThatEmptyStackThrowsExceptionRemoveLast(){
        assertThrows(EmptyStackException.class, l::removeLast);
    }

    @Test
    void testThatEmptyStackThrowsExceptionRemoveFirst(){
        assertThrows(EmptyStackException.class, l::removeFirst);
    }

    @Test
    void testSimpleAddRemoveLast(){
        l.add(1);
        l.add(2);
        l.add(3);
        int i = l.removeFirst();
        assertEquals(i, 1);
    }

    @Test
    void testSimpleAddRemoveRemoveFirst(){
        l.add(1);
        l.add(2);
        l.add(3);
        int i = l.removeLast();
        assertEquals(i, 3);
    }
}
