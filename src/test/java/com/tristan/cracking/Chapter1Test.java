package com.tristan.cracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Chapter1Test {

    @Test
    void testUrlify() {
        assertEquals(Chapter1.urlify("Mr John Smith    ", 13), "Mr%20John%20Smith");
    }

    @Test
    void testIsPermutation() {
        assertEquals(Chapter1.isPermutation("hello", "elloh"), true);
        assertEquals(Chapter1.isPermutation("hello", "elloy"), false);
    }

    @Test
    void testOneAway() {
        assertEquals(Chapter1.oneAway("pale", "ple"), true);
        assertEquals(Chapter1.oneAway("pales", "pale"), true);
        assertEquals(Chapter1.oneAway("pale", "bale"), true);
        assertEquals(Chapter1.oneAway("pale", "bake"), false);
    }
}
