package com.tristan.cracking;


class Main {
    public static int fib(int n) {
        return tail(0, 1, n);
    }

    private static int tail(int a, int b, int n) {
        if (n <= 0) {
            return a;
        } else {
            return tail(b, a + b, n -1);
        }
    }

    private static String fizzbuzz() {
        StringBuilder s = new StringBuilder();

        for(int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                s.append("fizz");
            }
            if(i % 5 == 0) {
                s.append("buzz");
            }
            if(i % 5 != 0 && i % 3 != 0) {
                s.append(i);
            }
            s.append("\n");
        }

        return s.toString();
    }

    public static void main(String[] args) {
        System.out.println(fizzbuzz());

    }
}