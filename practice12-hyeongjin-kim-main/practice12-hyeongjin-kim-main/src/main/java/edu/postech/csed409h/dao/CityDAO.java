package edu.postech.csed409h.dao;

import edu.postech.csed409h.model.City;

import java.util.List;
public interface CityDAO {
    City findCityById(int id);

    City removeCity(City city);

    City addCity(City city);

    City updateCity(City city);

    List<City> findAllCities();

    List<City> findCitiesByCountryCode(String countryCode);
}
