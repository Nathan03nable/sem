package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.languages.Language;
import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;
import com.napier.sem.cities.City;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    static final String limit = "5";
    private static final String COUNTRY = "'Ukraine'";
    private static final String DISTRICT = "'Kabol'";
    private static final String CITY = "'Edinburgh'";
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final String WORLD_POPULATION = "6078547450";

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

        LOGGER.info(population.cityPopulation("London"));
        LOGGER.info(population.countryPopulation("France"));
        LOGGER.info(population.districtPopulation("Caribbean"));
        LOGGER.info(population.continentPopulation("Europe"));
        LOGGER.info(population.everyContinentPopulation());
        LOGGER.info(population.everyCountryPopulation());
        LOGGER.info(population.everyRegionPopulation());

        LOGGER.info(country.continentCountries());
        LOGGER.info(country.regionCountries());
        LOGGER.info(country.worldCountries());
        LOGGER.info(country.topNPopulatedContinentCountries(limit));
        LOGGER.info(country.topNPopulatedCountries(limit));
        LOGGER.info(country.topNPopulatedContinentCountries(limit));

        LOGGER.info(city.continentCities());
        LOGGER.info(city.countryCities());
        LOGGER.info(city.districtCities());
        LOGGER.info(city.regionCities());
        LOGGER.info(city.worldCities());

        LOGGER.info(capitalCity.worldCapitalCities());
        LOGGER.info(capitalCity.regionCapitalCities());
        LOGGER.info(capitalCity.continentCapitalCities());
        LOGGER.info(capitalCity.topNPopulatedCitiesInARegion(limit));
        LOGGER.info(capitalCity.topNPopulatedCitiesInAContinent(limit));
        LOGGER.info(capitalCity.topNPopulatedCapitalCitiesInTheWorld(limit));
        LOGGER.info(capitalCity.topNPopulatedCitiesInARegion(limit));
        LOGGER.info(capitalCity.topNPopulatedCitiesInAContinent(limit));

        String worldPop = population.extractWorldPopulation(population.worldPopulation());
        LOGGER.info(language.languagesReport(worldPop));

        LOGGER.info(population.worldPopulation());
        databaseConnection.disconnect();
    }
}