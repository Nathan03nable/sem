package com.napier.sem;

import com.napier.sem.populations.Population;

public class App
{
    public static void main(String[] args) {
        IDatabaseConnection databaseConnection = DatabaseConnectionImpl.getInstance();
        Population population = new Population(databaseConnection);

        population.worldPopulation();
        population.continentPopulation();
        population.regionPopulation();
        population.countryPopulation();
        population.districtPopulation();
        population.cityPopulation();

        databaseConnection.disconnect();
    }
}