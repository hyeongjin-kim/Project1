package edu.postech.csed409h.exercises;

import edu.postech.csed409h.dao.WorldDAO;

import static java.util.stream.Collectors.summarizingLong;
import edu.postech.csed409h.model.Country;

import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {
    private static final WorldDAO worldDao = InMemoryWorld.getInstance();

    public static void main(String[] args) {
        // Find the minimum, the maximum and the average population of world countries
        // Hint: use summarizingLong
        List<Country> countrylist = worldDao.findAllCountries();
        var populationSummary = countrylist.stream().collect(Collectors.summarizingLong(Country::getPopulation));
        System.out.println(populationSummary);
    }
}
