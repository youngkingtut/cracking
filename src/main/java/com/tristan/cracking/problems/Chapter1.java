package com.tristan.cracking.problems;

import java.util.HashMap;
import java.util.HashSet;

class Chapter1 {
    static boolean isUnique(String s){
        HashSet<Character> chars = new HashSet<>();
        for(Character c: s.toCharArray()){
            if(chars.contains(c)) {
                return false;
            } else {
                chars.add(c);
            }
        }
        return true;
    }

    static boolean isPermutation(String a, String b){
        if(a.length() == b.length()){
            int aTotal = 0;
            int bTotal = 0;
            for(int i = 0; i < a.length(); i++) {
                aTotal += a.charAt(i);
                bTotal += b.charAt(i);
            }
            return aTotal == bTotal;
        }
        return false;
    }

    static String urlify(String s, int trueLength){
        char[] chars = s.toCharArray();
        int current = trueLength - 1;

        for(int i = chars.length - 1; i >= 0; i--) {
            if(chars[current] == ' ') {
                chars[i--] = '0';
                chars[i--] = '2';
                chars[i] = '%';
            } else {
                chars[i] = chars[current];
            }
            current--;
        }

        return new String(chars);
    }

    static boolean palindronPermutation(String s) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        for(Character c: s.toCharArray()) {
            if(countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }
        }

        boolean oddFound = false;
        for(Integer count: countMap.values()) {
            if(count % 2 != 0){
                if(!oddFound) {
                    oddFound = true;
                } else {
                    return false;
                }
            }
        }

        return true;

    }

    static private boolean oneRemoveAway(String a, String b) {
        for(int aInc = 0, bInc = 0; aInc < a.length(); aInc++, bInc++) {
            if(a.charAt(aInc) != b.charAt(bInc)){
                if(aInc == bInc) {
                    bInc++;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    static boolean oneAway(String a, String b) {
        if(a.length() == b.length()) {
            boolean foundDifference = false;
            for(int i = 0; i < a.length(); i++){
                if(a.charAt(i) != b.charAt(i)) {
                    if(!foundDifference) {
                        foundDifference = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else if(Math.abs(a.length() - b.length()) < 2) {
            if(a.length() < b.length()) {
                return oneRemoveAway(a, b);
            } else {
                return oneRemoveAway(b, a);
            }
        }
        return false;
    }
}
