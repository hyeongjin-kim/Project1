package edu.postech.csed409h.dao;

import edu.postech.csed409h.model.Country;

import java.util.List;
import java.util.Set;

public interface CountryDAO {
    Country findCountryByCode(String code);

    Country removeCountry(Country country);

    Country addCountry(Country country);

    Country updateCountry(Country country);

    List<Country> findAllCountries();

    List<Country> findCountriesByContinent(String continent);

    Set<String> getAllContinents();
}
