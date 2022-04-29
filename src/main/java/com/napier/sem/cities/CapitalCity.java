package com.napier.sem.cities;

import com.napier.sem.SqlManager;

public class CapitalCity {
    SqlManager sqlManager;

    public CapitalCity(SqlManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public String topNPopulatedCapitalCitiesInTheWorld(String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.capital = city.id "
                + "ORDER BY city.population DESC LIMIT %s;", limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCapitalCitiesInAContinent(String continent, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.capital = city.id AND country.continent = %s "
                + "ORDER BY city.population DESC LIMIT %s;", continent, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String topNPopulatedCapitalCitiesInARegion(String region, String limit){
        String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.capital = city.id AND country.region = %s "
                + "ORDER BY city.population DESC LIMIT %s;", region, limit);

        return sqlManager.executeStatement(stmt);
    }

    public String worldCapitalCities(){
        String stmt = "SELECT city.Name, country.name AS Country, city.Population "
        + "FROM city JOIN country ON (country.code=city.countrycode) "
        + "WHERE country.capital = city.id "
        + "ORDER BY city.population DESC;";

        return sqlManager.executeStatement(stmt);
    }

    public String continentCapitalCities(String continent){
        String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.capital = city.id AND country.continent = %s "
                + "ORDER BY city.population DESC;", continent);

        return sqlManager.executeStatement(stmt);
    }

    public String regionCapitalCities(String region){
        String stmt = String.format("SELECT city.Name, country.name AS Country, city.Population "
                + "FROM city JOIN country ON (country.code=city.countrycode) "
                + "WHERE country.capital = city.id AND country.region = %s "
                + "ORDER BY city.population DESC;", region);

        return sqlManager.executeStatement(stmt);
    }
}
