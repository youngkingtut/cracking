package com.tristan.cracking.problems;

import org.omg.CORBA.INTERNAL;

public class Chapter5 {
    static int insertion(int n, int m, int i, int j) {
        int mask = 0;
        for(int z = i; z <= j; z++) {
            mask = mask << 1;
            mask += 0x1;
        }
        mask = mask << i;

        return (n & ~mask) | (m << i);
    }

    static String decimalToBinaryDecimal(double val) {
        StringBuilder s = new StringBuilder();
        s.append("0.");

        for(int i = 0; i < 32; i++) {
            val *= 2;
            if(val % 2 >= 1) {
                s.append("1");
                val -= 1;
            } else {
                s.append("0");
            }
            if(val == 0) {
                break;
            }
        }
        if(val > 0) {
            return "ERROR";
        } else {
            return s.toString();
        }
    }

}
