package com.tristan.cracking.problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.tristan.cracking.problems.Chapter10.*;

class Chapter10Test {
    private int[] array;

    @BeforeEach
    void init() {
        array = new int[]{4,2,3,1,5,8,9,10,0,12,11,15,13,14,6,7};
    }

    @Test
    void testBubbleSort() {
        Chapter10.bubbleSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(array[i], i);
        }

    }

    @Test
    void testSelectionSort() {
        Chapter10.selectionSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(array[i], i);
        }

    }

    @Test
    void testMergeSort() {
        Chapter10.mergeSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(array[i], i);
        }
    }

    @Test
    void testQuickSort() {
        Chapter10.quickSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(array[i], i);
        }
    }

    @Test
    void testSortedMerge() {
        int[] sortedMergeArray = new int[10];
        java.util.Arrays.fill(sortedMergeArray, 0, 5, 10);
        int[] smallerArray = new int[]{1,7,12,14,15};
        Chapter10.sortedMerge(sortedMergeArray, smallerArray);
        assertEquals(sortedMergeArray[0], 1);
        assertEquals(sortedMergeArray[1], 7);
        assertEquals(sortedMergeArray[2], 10);
        assertEquals(sortedMergeArray[7], 12);
        assertEquals(sortedMergeArray[8], 14);
        assertEquals(sortedMergeArray[9], 15);
    }

    @Test
    void testGroupAnagrams() {
        String[] strings = new String[]{"a", "hello", "forge", "hell", "okay", "civil", "elloh", "aaaaa", "bbbbb", "lloeh"};
        Chapter10.groupAnagrams(strings);
    }

    @Test
    void testBinarySearch() {
        Chapter10.quickSort(array);
        assertEquals(Chapter10.binarySearch(array, 1), 1);
        assertEquals(Chapter10.binarySearch(array, 2), 2);
        assertEquals(Chapter10.binarySearch(array, 3), 3);
        assertEquals(Chapter10.binarySearch(array, 4), 4);
        assertEquals(Chapter10.binarySearch(array, 5), 5);
        assertEquals(Chapter10.binarySearch(array, 11), 11);
        assertEquals(Chapter10.binarySearch(array, 12), 12);
        assertEquals(Chapter10.binarySearch(array, 13), 13);
        assertEquals(Chapter10.binarySearch(array, 14), 14);
    }

    @Test
    void BinarySearchOnRotatedOrderedArray() {
        int[] arr = new int[]{10,12,14,15,16,17,18,0,1,2,3,4};
        assertEquals(binarySearchOnRotatedSortedArray(arr, 0, arr.length - 1, 0), 7);
    }
}
