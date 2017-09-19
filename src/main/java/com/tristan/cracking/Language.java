package com.tristan.cracking;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Country {
    String name;
    Integer population;

    public Country(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public Integer getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }
}


class SomeCollection {
    private HashSet<Integer> s;

    SomeCollection() {
        this.s = new HashSet<>();
    }

    void addValue(Integer val) {
        s.add(val);
    }

    boolean twoAdd(Integer val){
        for(Integer i: s){
            if(s.contains(val - i)) {
                return true;
            }
        }
        return false;
    }
}

public class Language {
    private static Integer getPopulation(List<Country> countries, final String countryName) {
        return countries.stream().filter(c -> c.name.equals(countryName)).mapToInt(Country::getPopulation).sum();
    }

    public static void main(String[] args) {
        SomeCollection c = new SomeCollection();
        c.addValue(1);
        c.addValue(3);

        System.out.println(c.twoAdd(4));
        System.out.println(c.twoAdd(3));
    }
}