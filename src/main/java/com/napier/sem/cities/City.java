package com.napier.sem.cities;
import com.napier.sem.SqlManager;

public class City {
    SqlManager sqlManager;

    public City(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    //All the cities in the world organised by largest population to smallest.
    public String worldCities(){
        String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "ORDER BY Population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String continentCities(){
        String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE continent = 'Africa' "
                + "ORDER BY population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String regionCities(){
        String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE region = 'Middle East' "
                + "ORDER BY population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String countryCities(){
        String stmt = "SELECT city.Name, country.Name AS 'Country', District, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.Name = 'Norway' "
                + "ORDER BY city.Population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String districtCities(){
        String stmt = "SELECT city.Name, country.name AS Country, District, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE city.district = 'Fujian'"
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }
}
