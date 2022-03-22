package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.populations.Population;

public class App
{
    //static final int limit = 5;
    private static final String CONTINENT = "'Europe'";
    private static final String REGION = "'Caribbean'";
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

        System.out.println(population.districtPopulation(DISTRICT));
        System.out.println(population.cityPopulation(CITY));
        System.out.println(population.countryPopulation(COUNTRY));

        databaseConnection.disconnect();
}