package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.cities.City;
import com.napier.sem.countries.Country;
import com.napier.sem.populations.Population;

public class App
{
    //static final int limit = 5;
    static final String limit = "5";
  
    public static void main(String[] args) {
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
        SqlManager sqlManager = new SqlManager(databaseConnection);
        Population population = new Population(sqlManager);
        City city = new City(sqlManager);
        Country country = new Country(sqlManager);
        CapitalCity capitalCity = new CapitalCity(sqlManager);

        System.out.println(city.worldCities());
        System.out.println(city.continentCities());
        System.out.println(city.regionCities());
        System.out.println(city.countryCities());
        System.out.println(city.districtCities());

        System.out.println(population.worldPopulation());
        System.out.println(population.continentPopulation());
        System.out.println(population.regionPopulation());
        System.out.println(population.countryPopulation());
        System.out.println(population.districtPopulation());
        System.out.println(population.cityPopulation());

        System.out.println(country.worldCountries());
        System.out.println(country.continentCountries());
        System.out.println(country.regionCountries());
        System.out.println(country.topNPopulatedCountries((limit)));
        System.out.println(country.topNPopulatedContinentCountries((limit)));
        System.out.println(country.topNPopulatedRegionalCountries((limit)));
        //System.out.println(country.topNPopulatedRegionalCountries(String.valueOf(limit)));

        System.out.println(capitalCity.worldCapitalCities());
        System.out.println(capitalCity.continentCapitalCities());
        System.out.println(capitalCity.regionCapitalCities());


        databaseConnection.disconnect();
    }
}