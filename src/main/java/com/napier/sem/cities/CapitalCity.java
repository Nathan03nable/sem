package com.napier.sem.cities;

import com.napier.sem.SqlManager;

public class CapitalCity {
    SqlManager sqlManager;

    public CapitalCity(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    //All the capital cities in the world organised by largest population to smallest
    public String worldCapitalCities(){
        String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id "
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the capital cities in a continent organised by largest population to smallest.
    //Replace 'Africa' with country continent argument
    public String continentCapitalCities(){
        String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id AND country.continent = 'Africa' "
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the capital cities in a region organised by largest to smallest.
    //Replace 'Middle East' with region argument
    public String regionCapitalCities(){
        String stmt = "SELECT city.Name AS City, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id AND country.region = 'Middle East' "
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }
}
