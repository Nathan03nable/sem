package com.napier.sem;

import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;

public class App
{
    static final String limit = "5";
    private static final String COUNTRY = "'Ukraine'";
    private static final String DISTRICT = "'Kabol'";
    private static final String CITY = "'Edinburgh'";

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

        System.out.println(population.districtPopulation(DISTRICT));
        System.out.println(population.cityPopulation(CITY));
        System.out.println(population.countryPopulation(COUNTRY));

        System.out.println(country.worldCountries());
        System.out.println(country.continentCountries());
        System.out.println(country.regionCountries());
        System.out.println(country.topNPopulatedCountries(limit));
        System.out.println(country.topNPopulatedContinentCountries(limit));
        System.out.println(country.topNPopulatedRegionalCountries(limit));

        databaseConnection.disconnect();
    }
}