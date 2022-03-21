package com.napier.sem;

import com.napier.sem.cities.CapitalCity;
import com.napier.sem.cities.City;
import com.napier.sem.populations.Population;

public class App
{
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
        City city = new City(sqlManager);
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

        System.out.println(capitalCity.worldCapitalCities());
        System.out.println(capitalCity.continentCapitalCities());
        System.out.println(capitalCity.regionCapitalCities());

        databaseConnection.disconnect();
    }
}