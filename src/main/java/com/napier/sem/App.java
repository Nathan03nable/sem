package com.napier.sem;

import com.napier.sem.cities.City;
import com.napier.sem.populations.Population;

public class App
{
    public static void main(String[] args) {
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
        Population population = new Population(databaseConnection);
        City city = new City(databaseConnection);

        System.out.println(city.worldCities());
        System.out.println(population.worldPopulation());
        System.out.println(population.continentPopulation());
        System.out.println(population.regionPopulation());
        System.out.println(population.countryPopulation());
        System.out.println(population.districtPopulation());
        System.out.println(population.cityPopulation());

        databaseConnection.disconnect();
    }
}