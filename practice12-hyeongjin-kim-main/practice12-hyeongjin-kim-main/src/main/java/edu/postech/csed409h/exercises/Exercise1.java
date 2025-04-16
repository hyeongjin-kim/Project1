package edu.postech.csed409h.exercises;

import edu.postech.csed409h.dao.CityDAO;
import edu.postech.csed409h.dao.CountryDAO;
import edu.postech.csed409h.model.City;
import edu.postech.csed409h.model.Country;

import java.util.*;

import static java.util.Comparator.comparing;

public class Exercise1 {
    private static final CountryDAO countryDao = InMemoryWorld.getInstance();
    private static final CityDAO cityDao = InMemoryWorld.getInstance();

    public static void main(String[] args) {
        // Find the highest populated capital city
        // Hint: Comparator.comparing, cityDao:findCityById
        var test = countryDao.findAllCountries().stream().map(Country::getCapital).filter(Objects::nonNull).map(cityDao::findCityById).filter(Objects::nonNull);
        var highPopulatedCapitalCity = test.sorted(comparing(City::getPopulation)).toList();
        System.out.println(highPopulatedCapitalCity.get(highPopulatedCapitalCity.size()-1));
    }
}
