package com.napier.sem;

import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    static final String limit = "5";
    private static final String COUNTRY = "'Ukraine'";
    private static final String DISTRICT = "'Kabol'";
    private static final String CITY = "'Edinburgh'";
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        String location;
        if(args.length < 1){
            location = "localhost:33060";
        }else{
            location = args[0];
        }
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location);

        SqlManager sqlManager = new SqlManager(databaseConnection);

        Population population = new Population(sqlManager);

        Country country = new Country(sqlManager);

        if(LOGGER.isLoggable(Level.INFO)){
            LOGGER.info(population.districtPopulation(DISTRICT));
            LOGGER.info(population.cityPopulation(CITY));
            LOGGER.info(population.countryPopulation(COUNTRY));

            LOGGER.info(country.worldCountries());
            LOGGER.info(country.continentCountries());
            LOGGER.info(country.regionCountries());
            LOGGER.info(country.topNPopulatedCountries(limit));
            LOGGER.info(country.topNPopulatedContinentCountries(limit));
            LOGGER.info(country.topNPopulatedRegionalCountries(limit));
        }


        databaseConnection.disconnect();
    }
}