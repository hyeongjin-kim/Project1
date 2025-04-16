package edu.postech.csed409h.exercises;

import edu.postech.csed409h.model.City;

import java.util.Optional;

public class ContinentCityPair implements Comparable<ContinentCityPair> {
    private String continent;
    private City city;
    public ContinentCityPair(String continent, City city){
        this.continent = continent;
        this.city = city;
    }

    public String getContinent(){
        return continent;
    }

    public City getCity(){
        return city;
    }

    @Override
    public int compareTo(ContinentCityPair other) {
        return this.city.getPopulation()-other.city.getPopulation();
    }

    public static void printEntry(String continent, Optional<ContinentCityPair> pair) {
        System.out.printf("%s: %s\n",continent,pair.get().city);
    }
}
