package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.languages.Language;
import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;
import com.napier.sem.cities.City;
import java.util.logging.Logger;

public class App
{
    static final String limit = "5";
    static final String continent = "Africa";
    static final String countryName = "'Spain'";
    static final String district = "'Fujian'";
    static final String region = "Middle East";
    static final String cityName = "'London'";

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String location;
        if (args.length < 1) {
            location = "localhost:33060";
        } else {
            location = args[0];
        }
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);

        SqlManager sqlManager = new SqlManager(databaseConnection);

        Population population = new Population(sqlManager);
        Country country = new Country(sqlManager);
        City city = new City(sqlManager);
        CapitalCity capitalCity = new CapitalCity(sqlManager);
        Language language = new Language(sqlManager);

        LOGGER.info(population.worldPopulation());
        LOGGER.info(population.cityPopulation(cityName));
        LOGGER.info(population.countryPopulation(countryName));
        LOGGER.info(population.districtPopulation(region));
        LOGGER.info(population.continentPopulation(continent));
        LOGGER.info(population.everyContinentPopulation());
        LOGGER.info(population.everyCountryPopulation());
        LOGGER.info(population.everyRegionPopulation());

        LOGGER.info(country.continentCountries(continent));
        LOGGER.info(country.regionCountries(region));
        LOGGER.info(country.worldCountries());
        LOGGER.info(country.topNPopulatedContinentCountries(continent, limit));
        LOGGER.info(country.topNPopulatedCountries(limit));
        LOGGER.info(country.topNPopulatedRegionalCountries(region, limit));

        LOGGER.info(city.continentCities(continent));
        LOGGER.info(city.countryCities(countryName));
        LOGGER.info(city.districtCities(district));
        LOGGER.info(city.regionCities(region));
        LOGGER.info(city.topNPopulatedCitiesInARegion(region, limit));
        LOGGER.info(city.topNPopulatedCitiesInAContinent(continent, limit));
        LOGGER.info(city.topNPopulatedCitiesInACountry(countryName, limit));
        LOGGER.info(city.topNPopulatedCitiesInADistrict(district, limit));
        LOGGER.info(city.worldCities());

        LOGGER.info(capitalCity.worldCapitalCities());

        LOGGER.info(capitalCity.regionCapitalCities(region));
        LOGGER.info(capitalCity.continentCapitalCities(region));

        LOGGER.info(capitalCity.topNPopulatedCapitalCitiesInTheWorld(limit));
        LOGGER.info(capitalCity.topNPopulatedCapitalCitiesInARegion(region, limit));
        LOGGER.info(capitalCity.topNPopulatedCapitalCitiesInAContinent(continent, limit));

        String worldPop = population.extractWorldPopulation(population.worldPopulation());
        LOGGER.info(language.languagesReport(worldPop));

        databaseConnection.disconnect();
    }
}