package com.tristan.cracking.problems;

import com.tristan.cracking.datastructures.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Chapter2Test {
    LinkedList<Integer> list;
    @BeforeEach
    void init() {
        list = new LinkedList<>();
    }


    @Test
    void testDedupLinkedList() {
        list.addLast(1);
        list.addLast(1);
        list.addLast(1);
        Chapter2.removeDups(list);
        assertEquals(list.getSize(), 1);
        assertEquals(list.removeFirst(), new Integer(1));
        assertEquals(list.isEmpty(), true);
    }

    @Test
    void testRemoveKthToLast() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        LinkedList<Integer> list1 = new LinkedList<>(list);
        LinkedList<Integer> list2 = new LinkedList<>(list);
        LinkedList<Integer> list3 = new LinkedList<>(list);

        assertEquals(Chapter2.removeKthToLast(list1, 0), new Integer(3));
        assertEquals(Chapter2.removeKthToLast(list2, 1), new Integer(2));
        assertEquals(Chapter2.removeKthToLast(list3, 2), new Integer(1));
        assertThrows(NoSuchElementException.class, () -> Chapter2.removeKthToLast(list, 3));
        assertThrows(NoSuchElementException.class, () -> Chapter2.removeKthToLast(list, -29));
    }
}
