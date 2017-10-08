package com.tristan.cracking.datastructures;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        int i = l.removeFirst();
        assertEquals(i, 1);
    }

    @Test
    void testSimpleAddRemoveRemoveFirst(){
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        int i = l.removeLast();
        assertEquals(i, 3);
    }

    @Test void testAddFirst() {
        l.addFirst(2);
        l.addFirst(1);
        assertEquals(l.removeFirst(), new Integer(1));
        assertEquals(l.removeFirst(), new Integer(2));
    }
}
