package com.napier.sem.capitalCity;

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
}
