package com.tristan.cracking.problems;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Chapter5Test {
    @Test
    void testInsertion() {
        int n = 0b10000000000;
        int m = 0b10011;
        assertEquals(Chapter5.insertion(n, m, 2, 6), 0b10001001100);
    }

    @Test
    void testDecimalToBinaryDecimalString() {
        assertEquals(Chapter5.decimalToBinaryDecimal(0.5), "0.1");
        assertEquals(Chapter5.decimalToBinaryDecimal(0.25), "0.01");
        assertEquals(Chapter5.decimalToBinaryDecimal(0.125), "0.001");
        assertEquals(Chapter5.decimalToBinaryDecimal(0.14159265), "ERROR");
    }
}
