package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.languages.Language;
import com.napier.sem.population.Population;
import com.napier.sem.countries.Country;
import com.napier.sem.cities.City;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class App
{
    static final String limit = "5";
    static final String continent = "'Oceania'";
    static final String countryName = "'Spain'";
    static final String district = "'Fujian'";
    static final String region = "'Caribbean'";
    static final String cityName = "'London'";

    public static void main(String[] args) {
        String location;
        if (args.length < 1) {
            location = "localhost:33060";
        } else {
            location = args[0];
        }
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance(location, 10);

        SqlManager sqlManager = new SqlManager(databaseConnection);

        Population population = new Population(sqlManager);
        Country country = new Country(sqlManager);
        City city = new City(sqlManager);
        CapitalCity capitalCity = new CapitalCity(sqlManager);
        Language language = new Language(sqlManager);

        System.out.println(country.worldCountries());
        System.out.println(country.continentCountries(continent));
        System.out.println(country.regionCountries(region));
        System.out.println(country.topNPopulatedCountries(limit));
        System.out.println(country.topNPopulatedContinentCountries(continent, limit));
        System.out.println(country.topNPopulatedRegionalCountries(region, limit));
        System.out.println(city.worldCities());
        System.out.println(city.continentCities(continent));

        System.out.println(capitalCity.worldCapitalCities());
        System.out.println(capitalCity.continentCapitalCities(continent));
        System.out.println(capitalCity.regionCapitalCities(region));
        System.out.println(capitalCity.topNPopulatedCapitalCitiesInTheWorld(limit));
        System.out.println(capitalCity.topNPopulatedCapitalCitiesInAContinent(continent, limit));
        System.out.println(capitalCity.topNPopulatedCapitalCitiesInARegion(region, limit));
        System.out.println(population.everyContinentPopulation());
        System.out.println(population.everyRegionPopulation());

        System.out.println(city.regionCities(region));
        System.out.println(city.countryCities(countryName));
        System.out.println(city.districtCities(district));
        System.out.println(city.topNPopulatedCitiesInTheWorld(limit));
        System.out.println(city.topNPopulatedCitiesInAContinent(continent, limit));
        System.out.println(city.topNPopulatedCitiesInARegion(region, limit));
        System.out.println(city.topNPopulatedCitiesInACountry(countryName, limit));
        System.out.println(city.topNPopulatedCitiesInADistrict(district, limit));


        System.out.println(population.everyCountryPopulation());
        System.out.println(population.worldPopulation());
        System.out.println(population.continentPopulation(continent));
        System.out.println(population.regionPopulation(region));
        System.out.println(population.countryPopulation(countryName));
        System.out.println(population.districtPopulation(district));
        System.out.println(population.cityPopulation(cityName));

        String worldPop = population.extractWorldPopulation(population.worldPopulation());
        System.out.println(language.languagesReport(worldPop));

        databaseConnection.disconnect();
    }
}