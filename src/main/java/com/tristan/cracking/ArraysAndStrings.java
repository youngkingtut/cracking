package com.tristan.cracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.lang.StrictMath.abs;

public class ArraysAndStrings {

    public static void main(String[] args) {
        System.out.println(isRotation("waterbottle", "erbottlewat"));
    }

    private static boolean isUnique(String string) {
        for(int i = 0; i < string.length(); i++) {
            for(int j = i+1; j < string.length(); j++) {
                if(string.charAt(i) == string.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPermutation(String first, String second) {
        boolean isPermutation = false;
        if(first.length() == second.length()) {
            int firstSum = 0;
            int secondSum = 0;
            for(int i=0; i < first.length(); i++) {
                firstSum += first.charAt(i);
                secondSum += second.charAt(i);
            }
            if(firstSum == secondSum) {
                isPermutation = true;
            }
        }
        return isPermutation;
    }

    private static String toURLSpaces(String string, int size) {
        char chars[] = string.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            size--;
            if(chars[i] == ' ') {
                System.arraycopy(chars, i + 1, chars, i + 3, size);
                chars[i] = '%';
                chars[i+1] = '2';
                chars[i+2] = '0';
                i += 2;
            }
        }

        return new String(chars);
    }


    private static boolean isPermutationOfPalindrome(String string) {
        boolean isPermutationOfPalindrome = true;
        boolean isOdd = false;
        boolean oneOdd = false;
        String sample = string.toLowerCase().trim().replaceAll(" ", "");
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();

        if(sample.length() % 2 == 1) {
            isOdd = true;
        }

        for(char c: sample.toCharArray()) {
            if(count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                count.put(c, 1);
            }
        }

        for(char c: count.keySet()) {
            if(count.get(c) % 2 == 1) {
                if(isOdd) {
                    if(oneOdd) {
                        isPermutationOfPalindrome = false;
                        break;
                    } else {
                        oneOdd = true;
                    }
                } else {
                    isPermutationOfPalindrome = false;
                    break;
                }
            }
        }

        return isPermutationOfPalindrome;
    }

    private static boolean oneAway(String first, String second) {
        boolean isOneAway = false;
        if(first.length() == second.length()){
            boolean oneReplace = false;
            for(int i = 0; i < first.length(); i++) {
                if(first.charAt(i) != second.charAt(i)) {
                    if(oneReplace) {
                        return false;
                    } else {
                        oneReplace = true;
                    }
                }
            }
        } else if (abs(first.length() - second.length()) == 1) {
            boolean oneChange = false;
            boolean firstBigger = first.length() > second.length();

            for(int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++){
                if(first.charAt(i) != second.charAt(j)) {
                    if(oneChange) {
                        return false;
                    } else {
                        oneChange = true;
                        if(firstBigger) {
                            i++;
                        } else {
                            j++;
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }


    private static String compress(String string) {
        StringBuilder compressed = new StringBuilder();
        Character currentChar = '\0';
        int count = 0;


        for(int i = 0; i < string.length(); i++) {
            if(i == 0) {
                currentChar = string.charAt(i);
            }
            if(string.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar);
                compressed.append(count);
                currentChar = string.charAt(i);
                count = 1;
            }
            if(i == string.length() - 1) {
                compressed.append(currentChar);
                compressed.append(count);
            }
        }

        String compressedString = compressed.toString();

        if(compressedString.length() < string.length()) {
            return compressedString;
        } else {
            return string;
        }
    }

    private static int[][] rotate90(int[][] image){
        int length = image.length;
        int width = image[0].length;

        int newImage[][] = new int[width][length];

        width--;
        length--;

        for(int i = 0; i <= length; i++) {
            for(int j = 0; j <= width; j++) {
                newImage[j][length - i] = image[i][j];
            }
        }

        return newImage;
    }

    private static int[][] zeroMatrix(int[][] matrix) {
        Set<Integer> row = new HashSet<Integer>();
        Set<Integer> column = new HashSet<Integer>();


        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    row.add(i);
                    column.add(j);
                }
            }
        }

        for(Integer i: row) {
            for(int j =0; j < matrix[0].length; j++){
                matrix[i][j] = 0;
            }
        }
        for(Integer j: column) {
            for(int i =0; i < matrix[0].length; i++){
                matrix[i][j] = 0;
            }
        }


        return matrix;
    }

    private static boolean isRotation(String first, String second) {
        if(first.length() != second.length()) {
            return false;
        }

        boolean match = false;
        int startSame = 0;
        int otherCounter = 0;

        for(int i=0; i < first.length(); i++) {
            if(first.charAt(otherCounter) == second.charAt(i)){
                otherCounter++;
                if(!match){
                    match = true;
                    startSame = i;
                }

            } else {
                otherCounter = 0;
                match = false;
            }
        }

        return match && (startSame == 0 || first.contains(second.substring(0, startSame)));
    }
}
