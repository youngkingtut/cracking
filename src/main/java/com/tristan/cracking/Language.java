package com.tristan.cracking;

import java.util.List;
import java.util.ArrayList;
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


public class Language {
    static Integer getPopulation(List<Country> countries, final String countryName) {
        return countries.stream().filter(c -> c.name.equals(countryName)).mapToInt(Country::getPopulation).sum();
    }

//    static List<Integer> getRandomSubset(List<Integer> list) {
//
//    }

    public static void main(String[] args) {
        ArrayList<Country> cs = new ArrayList<>();
        cs.add(new Country("USA", 100));
        cs.add(new Country("USA", 100));
        cs.add(new Country("Ukraine", 100));

        System.out.println(getPopulation(cs, "USA"));
        System.out.println(getPopulation(cs, "Ukraine"));
    }
}