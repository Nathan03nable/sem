package com.napier.sem.cities;
import com.napier.sem.SqlManager;

public class City {
    SqlManager sqlManager;

    public City(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    //All the cities in the world organised by largest population to smallest.
    public String worldCities(){
        String stmt = "SELECT Name, CountryCode, District, Population "
                + "FROM city "
                + "ORDER BY Population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the cities in a continent organised by largest population to smallest.
    // replace 'Africa' with the argument being passed
    public String continentCities(){
        String stmt = "SELECT name, code, continent, region, population, capital "
        + "FROM country "
        + "WHERE continent = 'Africa'"
        + "ORDER BY population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the cities in a region organised by largest population to smallest.
    //replace 'Middle East' with argument being passed
    public String regionCities(){
        String stmt = "SELECT name, code, continent, region, population, capital "
        + "FROM country "
        + "WHERE region = 'Middle East'"
        + "ORDER BY population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the cities in a country organised by largest population to smallest.
    //Replace 'Norway' with country argument
    public String countryCities(){
        String stmt = "SELECT city.Name, country.Name AS Country, District, city.Population"
        + "FROM city "
        + "JOIN country ON Code = CountryCode "
        + "WHERE country.Name = 'Norway'"
        + "ORDER BY city.Population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    //All the cities in a district organised by largest population to smallest.
    //replace 'Fujian' with city region argument in function
    public String districtCities(){
        String stmt = "SELECT city.Name, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE city.district = 'Fujian'"
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }






}



